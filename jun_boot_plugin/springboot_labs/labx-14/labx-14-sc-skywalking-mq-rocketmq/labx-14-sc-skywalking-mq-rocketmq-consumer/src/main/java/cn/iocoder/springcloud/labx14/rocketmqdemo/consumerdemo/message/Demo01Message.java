package cn.iocoder.springcloud.labx14.rocketmqdemo.consumerdemo.message;

/**
 * 示例 01 的 Message 消息
 */
public class Demo01Message {

    /**
     * 编号
     */
    private Integer id;

    public Demo01Message setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Demo01Message{" +
                "id=" + id +
                '}';
    }

}
