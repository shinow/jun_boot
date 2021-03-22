package com.mos.eboot.es;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: 朱建柯
 * Date: 2019/2/13
 * Time: 9:20
 */
public class Entity implements Serializable {

    private static final long serialVersionUID = -3852092864588999820L;

    public static final String INDEX_NAME = "index_entity";

    public static final String TYPE = "tstype";

    private Long id;
    private String name;

    public Entity() {
        super();
    }

    public Entity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
