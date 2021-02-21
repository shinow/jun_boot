package com.ruoyi.project.wxapi.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CategoryDTO {
    /**
     * 商品分类id
     */
    @JsonProperty("category_id")
    private Integer id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 上级分类id
     */
    @JsonProperty("parent_id")
    private Integer parentId;

    /**
     * 分类图片id
     */
    @JsonProperty("image_id")
    private Integer imageId;

    /**
     * 排序方式(数字越小越靠前)
     */
    private Integer sort;

    /**
     * 门店id
     */
    @JsonProperty("shop_id")
    private Integer shopId;


}
