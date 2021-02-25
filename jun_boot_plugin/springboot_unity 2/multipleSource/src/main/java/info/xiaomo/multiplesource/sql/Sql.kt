package info.xiaomo.multiplesource.sql

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author : xiaomo
 * github: https://github.com/xiaomoinfo
 * email: xiaomo@xiaomo.info
 *
 * Date: 2016/11/16 11:29
 * Description: 用户实体类
 * Copyright(©) 2015 by xiaomo.
 */

object Sql {

    var dropUser = "DELETE  FROM  user"
    var addUser = "insert into user(name,age) values(?, ?)"
    var selectUser = "select count(1) from user"

}
