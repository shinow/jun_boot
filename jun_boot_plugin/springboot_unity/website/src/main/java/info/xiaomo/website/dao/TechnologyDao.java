package info.xiaomo.website.dao;

import info.xiaomo.website.model.TechnologyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * │＼＿＿╭╭╭╭╭＿＿／│
 * │　　　　　　　　　│
 * │　　　　　　　　　│
 * │　－　　　　　　－│
 * │≡　　　　ｏ　≡   │
 * │　　　　　　　　　│
 * ╰——┬Ｏ◤▽◥Ｏ┬——╯
 * ｜　　ｏ　　｜
 * ｜╭－－－╮把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author Wujun
 * github: https://github.com/xiaomoinfo
 * email: xiaomo@xiaomo.info
 * <p>
 * Date: 16/4/2 13:08
 * Copyright(©) 2015 by xiaomo.
 */
@Repository
public interface TechnologyDao extends JpaRepository<TechnologyModel, Long> {

    /**
     * 根据名字查
     *
     * @param name
     * @return
     */
    TechnologyModel findTechnologyByName(String name);


}
