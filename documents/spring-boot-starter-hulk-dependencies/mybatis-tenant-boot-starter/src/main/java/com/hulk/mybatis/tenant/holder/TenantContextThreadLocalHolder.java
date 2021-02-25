package com.hulk.mybatis.tenant.holder;



/**
 * @author hulk
 * @date 2018/10/4
 * 租户工具类
 */
public class TenantContextThreadLocalHolder implements TenantContextHolder {

	private final static ThreadLocal<String> THREAD_LOCAL_TENANT = new InheritableThreadLocal<>();


	/**
	 * TTL 设置租户ID
	 *
	 * @param tenantId
	 */
	@Override
	public void setTenantId(String tenantId) {
		THREAD_LOCAL_TENANT.set(tenantId);
	}

	/**
	 * 获取TTL中的租户ID
	 *
	 * @return
	 */
	@Override
	public String getTenantId() {
		return THREAD_LOCAL_TENANT.get();
	}
	@Override
	public void clear() {
		THREAD_LOCAL_TENANT.remove();
	}
}
