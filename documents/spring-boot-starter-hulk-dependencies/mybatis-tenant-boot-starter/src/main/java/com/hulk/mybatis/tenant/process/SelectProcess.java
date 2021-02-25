package com.hulk.mybatis.tenant.process;

import com.hulk.mybatis.tenant.holder.TenantContextHolder;
import lombok.experimental.UtilityClass;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.util.TablesNamesFinder;


import java.util.List;

@Deprecated
@UtilityClass
public class SelectProcess {



    public String addWhere(Select select,String tenantIdColumn, TenantContextHolder tenantContextHolder) throws Exception {
        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
        List<String> tableList = tablesNamesFinder.getTableList(select);
        if (tableList.size() == 0) {
            return select.toString();
        }

        SubSelectProcess.addCondition(select.getSelectBody(),tenantIdColumn,tenantContextHolder);

        return select.toString();
    }


}
