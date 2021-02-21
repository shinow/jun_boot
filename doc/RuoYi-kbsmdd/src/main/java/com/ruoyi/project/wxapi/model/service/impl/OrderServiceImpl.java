package com.ruoyi.project.wxapi.model.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ruoyi.project.wxapi.controller.util.JWTUtil;
import com.ruoyi.project.wxapi.controller.util.MyDateUtils;
import com.ruoyi.project.wxapi.exception.StockException;
import com.ruoyi.project.wxapi.model.bean.*;
import com.ruoyi.project.wxapi.model.mapper.*;
import com.ruoyi.project.wxapi.model.qo.BaseParamQO;
import com.ruoyi.project.wxapi.model.qo.OrderQO;
import com.ruoyi.project.wxapi.model.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hnkb
 * @since 2020-07-14
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
    @Autowired
    OrderGoodsMapper orderGoodsMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GoodsSpecMapper goodsSpecMapper;
    @Autowired
    WxapiUserMapper userMapper;
    @Autowired
    UserAddressMapper userAddressMapper;
    @Autowired
    OrderAddressMapper orderAddressMapper;

    @Transactional
    @Override
    public Order addOrder(List<Map.Entry<String, Cart>> list, BaseParamQO baseParamQO, OrderQO orderQO) {
        Integer userId = JWTUtil.parseJWT(baseParamQO.getToken());
        // 生成订单
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        long orderNo = snowflake.nextId();
        Order order = new Order();
        order.setOrderNo(orderNo);
        order.setPeople(orderQO.getPeople());
        order.setWarePrice(new BigDecimal(orderQO.getWare_price()));

        Date arriveDate = null;
        if (orderQO.getArrive_time().equals("现在")) {
            String now = DateUtil.now();
            now = now.substring(0, now.length() - 2) + "00";
            arriveDate = DateUtil.parseDate(now);
        } else {
            String today = DateUtil.today();
            today = today + " " + orderQO.getArrive_time();
            arriveDate = DateUtil.parseDate(today);
        }
        order.setArriveTime(MyDateUtils.asLocalDateTime(arriveDate));
        order.setMessage(orderQO.getMessage());
        order.setFlavor(orderQO.getFlavor());

        BigDecimal totalPrice = new BigDecimal(0);

        for (Map.Entry<String, Cart> getCartEntry : list) {
            if (getCartEntry.getValue().getAttr() != null) {//多规格
                Goods goods = goodsMapper.getByGoodsId(getCartEntry.getValue().getGoodsId());
                for (String attrKey : getCartEntry.getValue().getAttr().keySet()) {
                    OrderGoods orderGoods = new OrderGoods();
                    BeanUtil.copyProperties(goods, orderGoods);
                    orderGoods.setId(null);
                    orderGoods.setFileName(null);
                    orderGoods.setFileUrl(null);
                    Integer num = getCartEntry.getValue().getAttr().get(attrKey);

                    if (baseParamQO.getFood_mode() == 3) {
                        //外卖
                        orderGoods.setGoodsPrice(goods.getOutPrice());
                    } else {
                        orderGoods.setGoodsPrice(goods.getGoodsPrice());
                    }

                    orderGoods.setTotalNum(num);
                    orderGoods.setGoodsAttr(attrKey);
                    orderGoods.setOrderNo(orderNo);
                    orderGoods.setGoodsId(goods.getId());
                    orderGoods.setUserId(userId);

                    orderGoodsMapper.insertOrderGoods(orderGoods);
                    goods.setStockNum(goods.getStockNum() - num);
                    if (goods.getStockNum() < 0) {
                        throw new StockException("库存不足");
                    }
                    Integer updateNum = goodsSpecMapper.reduceStockByGoods(goods);

                    if (baseParamQO.getFood_mode() == 3) {
                        // 外卖价格
                        totalPrice = totalPrice.add(goods.getOutPrice().multiply(new BigDecimal(num)));
                    } else {
                        totalPrice = totalPrice.add(goods.getGoodsPrice().multiply(new BigDecimal(num)));
                    }

                }

            } else {
                Goods goods = goodsMapper.getByGoodsId(getCartEntry.getValue().getGoodsId());
                OrderGoods orderGoods = new OrderGoods();
                BeanUtil.copyProperties(goods, orderGoods);
                orderGoods.setId(null);
                orderGoods.setFileName(null);
                orderGoods.setFileUrl(null);

                if (baseParamQO.getFood_mode() == 3) {
                    //外卖
                    orderGoods.setGoodsPrice(goods.getOutPrice());
                } else {
                    orderGoods.setGoodsPrice(goods.getGoodsPrice());
                }

                orderGoods.setTotalNum(getCartEntry.getValue().getGoodsNum());
                orderGoods.setOrderNo(orderNo);
                orderGoods.setGoodsId(goods.getId());
                orderGoods.setUserId(userId);

                orderGoodsMapper.insertOrderGoods(orderGoods);
                goods.setStockNum(goods.getStockNum() - getCartEntry.getValue().getGoodsNum());
                if (goods.getStockNum() < 0) {
                    throw new StockException("库存不足");
                }
                Integer updateNum = goodsSpecMapper.reduceStockByGoods(goods);

                if (baseParamQO.getFood_mode() == 3) {
                    // 外卖价格
                    totalPrice = totalPrice.add(goods.getOutPrice().multiply(new BigDecimal(getCartEntry.getValue().getGoodsNum())));
                } else {
                    totalPrice = totalPrice.add(goods.getGoodsPrice().multiply(new BigDecimal(getCartEntry.getValue().getGoodsNum())));
                }

            }
        }
        User user = userMapper.selectByUserId(JWTUtil.parseJWT(baseParamQO.getToken()));
        UserAddress userAddress = userAddressMapper.getByAddressId(user.getAddressId());
        OrderAddress orderAddress = new OrderAddress();
        BeanUtil.copyProperties(userAddress, orderAddress);
        orderAddress.setId(null);
        orderAddress.setOrderNo(orderNo);
        orderAddressMapper.insertOrderAdress(orderAddress);

        order.setTotalPrice(totalPrice);
        order.setPayPrice(totalPrice);
        order.setTableId(baseParamQO.getFood_mode());
        order.setWxappId(baseParamQO.getWxapp_id());
        order.setShopId(baseParamQO.getShop_id());
        order.setUserId(JWTUtil.parseJWT(baseParamQO.getToken()));

        Integer num = this.getBaseMapper().insertSelective(order);
        if (num > 0) {
            return order;
        } else {
            return null;
        }

    }

    @Override
    public Integer updatePayStatusByOrderId(Long orderNo, Integer payStatus) {

        return this.getBaseMapper().updatePayStatusByOrderId(orderNo, payStatus);
    }

    @Override
    public List<Order> selectLargeByTableId(Integer tableId) {

        return this.getBaseMapper().selectLargeByTableId(tableId);
    }

    @Override
    public List<Order> selectByTableId(Integer tableId) {
        return this.getBaseMapper().selectByTableId(tableId);
    }

    @Override
    public List<Order> selectByTableIdAndUserId(Integer tableId, Integer userId) {
        return this.getBaseMapper().selectByTableIdAndUserId(tableId, userId);
    }


    @Override
    public List<Order> selectLargeByTableIdAndUserId(Integer tableId, Integer userId) {
        return this.getBaseMapper().selectLargeByTableIdAndUserId(tableId, userId);
    }

    @Override
    public Order selectByOrderId(Integer orderId) {
        return this.getBaseMapper().selectByOrderId(orderId);
    }

    @Override
    public Order selectByOrderIdAndUserId(Integer orderId, Integer userId) {
        return this.getBaseMapper().selectByOrderIdAndUserId(orderId, userId);
    }
}
