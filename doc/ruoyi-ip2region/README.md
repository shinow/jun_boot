## 工具简介

根据ip查询归属地

整合纯真、百度、离线文件[ip2region](https://gitee.com/lionsoul/ip2region)

#### 使用方法
- 将doc文件中文件放到自己的位置
- 修改application.yml配置位置
- 访问ip:port

访问示例 http://region.zmrit.com?ip=218.104.58.21

查询参数 ip, type(可选值：db,qqwry,baidu,默认db)
返回示例
```
{
    "msg": "success",
    "code": 0,
    "data": {
    	"address": "江苏省常州市" 	-- 地址
        "area": "0",	-- 区域 可能是华东、华南等 -- db方式
        "country": "中国",	-- 国家-- db方式
        "province": "浙江省",	 -- 省份-- db方式
        "city": "杭州市",   	-- 城市-- db方式
        "isp": "电信"  	 -- ISP运营商-- db方式
        "remark": "(技师院旁)心桥网吧" 	-- 纯真方式特有
    }
}
```


