/**
 * Copyright (c) 2012-2013, Michael Yang 杨福海 (www.yangfuhai.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.qk.applibrary.util;

import com.qk.applibrary.db.sqlite.annotation.Id;
import com.qk.applibrary.db.sqlite.annotation.Table;
import com.qk.applibrary.db.table.Property;
import com.qk.applibrary.exception.DbException;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class ClassUtils {
	
	
	/**
	 * 根据实体类 获得 实体类对应的表名
	 * @return
	 */
	public static String getTableName(Class<?> clazz) {
		Table table = clazz.getAnnotation(Table.class);
		if(table == null || table.name().trim().length() == 0 ){
			//当没有注解的时候默认用类的名称作为表名,并把点（.）替换为下划线(_)
			return clazz.getName().replace('.', '_');
		}
		return table.name();
	}

	/**
	 * 根据实体类 获得主键字段
	 * @return
	 */
	public static Field getPrimaryKeyField(Class<?> clazz) {
		Field primaryKeyField = null ;
		Field[] fields = clazz.getDeclaredFields();
		if(fields != null){

			for(Field field : fields){ //获取ID注解
				if(field.getAnnotation(Id.class) != null){
					primaryKeyField = field;
					break;
				}
			}

			if(primaryKeyField == null){ //没有ID注解
				for(Field field : fields){
					if("_id".equals(field.getName())){
						primaryKeyField = field;
						break;
					}
				}
			}

			if(primaryKeyField == null){ // 如果没有_id的字段
				for(Field field : fields){
					if("id".equals(field.getName())){
						primaryKeyField = field;
						break;
					}
				}
			}

		}else{
			throw new RuntimeException("this model["+clazz+"] has no field");
		}
		return primaryKeyField;
	}

	/**
	 * 根据实体类 获得主键字段名字
	 * @return
	 */
	public static String getPrimaryKeyFieldName(Class<?> clazz) {
		Field f = getPrimaryKeyField(clazz);
		return f==null ? null:f.getName();
	}



	/**
	 * 根据实体类 除了主键 获取类的其他字段
	 * @return
	 */
	public static List<Property> getPropertyList(Class<?> clazz) {

		List<Property> plist = new ArrayList<Property>();
		try {
			Field[] fs = clazz.getDeclaredFields();
			String primaryKeyFieldName = getPrimaryKeyFieldName(clazz);
			for (Field f : fs) {
				//必须是基本数据类型和没有标瞬时态的字段
				if(!FieldUtils.isTransient(f)){
					if (FieldUtils.isBaseDateType(f)) {

						if(f.getName().equals(primaryKeyFieldName)) //过滤主键
							continue;

						Property property = new Property();

						property.setColumn(FieldUtils.getColumnByField(f));
						property.setFieldName(f.getName());
						property.setDataType(f.getType());
						property.setDefaultValue(FieldUtils.getPropertyDefaultValue(f));
						property.setSet(FieldUtils.getFieldSetMethod(clazz, f));
						property.setGet(FieldUtils.getFieldGetMethod(clazz, f));
						property.setField(f);

						plist.add(property);
					}
				}
			}
			return plist;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

}
