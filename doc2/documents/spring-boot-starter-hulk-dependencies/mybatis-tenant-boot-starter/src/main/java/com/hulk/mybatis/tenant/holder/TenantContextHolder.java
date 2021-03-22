package com.hulk.mybatis.tenant.holder;



/**
 * @author hulk
 * @date 2018/10/4
 * 租户工具类
 */

public interface TenantContextHolder {




	/**
	 * TTL 设置租户ID
	 *
	 * @param tenantId
	 */
	 void setTenantId(String tenantId) ;

	/**
	 * 获取TTL中的租户ID
	 *
	 * @return
	 */
	String getTenantId() ;

	 void clear();


}
