package com.hulk.mybatis.tenant.process;

import lombok.experimental.UtilityClass;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.create.table.ColDataType;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;

/**
 * 针对create语句的处理
 */
@Deprecated
@UtilityClass
public class CreateProcess {

    public String addWhere(Statement statement) {
        CreateTable createTable = (CreateTable) statement;

        ColumnDefinition columnDefinition = new ColumnDefinition();
        columnDefinition.setColumnName("tenant_id");
        ColDataType colDataType = new ColDataType();
        colDataType.setDataType("varchar(36)");
        columnDefinition.setColDataType(colDataType);
        createTable.getColumnDefinitions().add(columnDefinition);
        return createTable.toString();
    }
}
