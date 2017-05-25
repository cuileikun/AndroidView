package com.qk.applibrary.db.sqlite;

import java.util.LinkedList;
import java.util.List;

/**
 * 封装sql语句
 */
public class SqlInfo {
	
	private String sql;
	private LinkedList<Object> bindArgs;
	private String[] wheres;
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	
	public LinkedList<Object> getBindArgs() {
		return bindArgs;
	}
	public void setBindArgs(LinkedList<Object> bindArgs) {
		this.bindArgs = bindArgs;
	}
	
	public Object[] getBindArgsAsArray() {
		if(bindArgs!=null)
			return bindArgs.toArray();
		return null;
	}
	
	public String[] getBindArgsAsStringArray() {
		if(bindArgs!=null){
			String[] strings = new String[bindArgs.size()];
			for(int i = 0;i<bindArgs.size();i++){
				strings[i]=bindArgs.get(i).toString();
			}
			return strings;
		}
		return null;
	}
	
	public void addValue(Object obj){
		if(bindArgs == null)
			bindArgs = new LinkedList<Object>();
		bindArgs.add(obj);
	}

	/**
	 * 添加条件
	 */
	public void addWhere(String[] values){
		wheres = values;
	}

	public String[] getWheres() {
		return wheres;
	}
}
