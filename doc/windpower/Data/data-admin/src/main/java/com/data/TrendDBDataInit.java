package com.data;

import com.data.common.biz.util.TrendDBUtil;

import TrendDB.DB_Exception;

public class TrendDBDataInit {
	
	public static void main( String[] args ) throws DB_Exception
    {
    	connect();
    	//创建标签
//    	TrendDBUtil.createTag();
    	//插入数据
//    	TrendDBUtil.insertData();
    	//查询数据
//    	TrendDBUtil.getWindDirection();
    	//创建风机属性
    	Integer[] names = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17};
    	for (Integer name : names) {
    		TrendDBUtil.insertTag(name+"");
		}
    	
    	close();
    }
    
    public static void connect() {
    	TrendDBUtil.createConnection();
    	int status = TrendDBUtil.connectStatus();
    	System.out.println("连接状态："+status);
    }
    public static void close() {
    	TrendDBUtil.closeConnection();
//    	int status1 = TrendDBUtil.connectStatus();
//    	System.out.println("连接状态："+status1);
    }
}
