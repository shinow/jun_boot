package com.ruoyi.common.utils;

import java.util.Map;

public class DataModel {
    private int count;
    private Map<String,String> head;
    private Map<Integer,Map<String,String>> body;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Map<String, String> getHead() {
        return head;
    }

    public void setHead(Map<String, String> head) {
        this.head = head;
    }

    public Map<Integer, Map<String, String>> getBody() {
        return body;
    }

    public void setBody(Map<Integer, Map<String, String>> body) {
        this.body = body;
    }
}
