package com.hulk.mybatis.tenant.config;


import com.hulk.mybatis.tenant.holder.TenantContextHolder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;


/**
 * tenant properties
 *
 * @author hulk
 * @version 1.0.0
 */
@Data
@ConfigurationProperties(prefix = "mybatis.tenant")
public class TenantProperties {

    private String tenantIdColumn ="tenant_id";

    private List<String> ignoreTenantTables = new ArrayList<>();

    private List<String> mappedStatementIds = new ArrayList<>();

    private   Class<? extends TenantContextHolder> tenantContextHolder;


}
