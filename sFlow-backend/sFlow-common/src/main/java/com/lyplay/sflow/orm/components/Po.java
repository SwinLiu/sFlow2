package com.lyplay.sflow.orm.components;

import com.lyplay.sflow.orm.utils.PoUtil;

public class Po<T> {

	private String tableName;
	private String columns;
	
	public Po(Class<T> clazz) {
		this.tableName = PoUtil.getTableName(clazz);
		this.columns = PoUtil.getTableColumns(clazz);
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumns() {
		return columns;
	}

	public void setColumns(String columns) {
		this.columns = columns;
	}

	
	
	
	

}
