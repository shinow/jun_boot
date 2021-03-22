package com.hulk.Idempotent;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    public Order(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    private Long id;

    private String name;

    private String address;


}
