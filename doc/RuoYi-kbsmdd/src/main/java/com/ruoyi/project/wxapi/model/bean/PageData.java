package com.ruoyi.project.wxapi.model.bean;

import lombok.Data;

import java.util.List;

@Data
public class PageData {
    private Integer pageType;
    private Page page;
    private List<Item> items;

    @Data
    public static class Page {
        private String type;
        private String name;
        private Param params;
        private Style style;
        private String id;
        @Data
        public static class Param {
            private String name;
            private String title;
            private String shareTitle;
            private String shareImage;
        }
        @Data
        public static class Style {
            private String titleTextColor;
            private String titleBackgroundColor;
        }
    }

    @Data
    public static class Item {
        private String name;
        private String type;
        private Style style;
        private Param params;
        private List<data> data;

        @Data
        public static class Style {
            private String btnColor;
            private String btnShape;
        }
        @Data
        public static class Param {
            private String interval;
        }

        @Data
        public static class data{
            private String imgUrl;
            private String linkUrl;
        }
    }
}
