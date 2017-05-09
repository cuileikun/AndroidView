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
package com.qk.applibrary.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.qk.applibrary.db.sqlite.CursorUtils;
import com.qk.applibrary.db.sqlite.SqlBuilder;
import com.qk.applibrary.db.sqlite.SqlInfo;
import com.qk.applibrary.db.table.KeyValue;
import com.qk.applibrary.db.table.TableInfo;
import com.qk.applibrary.exception.DbException;
import com.qk.applibrary.listener.DbUpdateListener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对数据库增删查改
 */
public class QkDb {

	private static final String TAG = "QkDb";

	private static HashMap<String, QkDb> daoMap = new HashMap<String, QkDb>();

	private SQLiteDatabase db;
	private DaoConfig config;

	private QkDb(DaoConfig config) {
		if (config == null)
			throw new DbException("daoConfig is null");
		if (config.getContext() == null)
			throw new DbException("android context is null");
		if (config.getTargetDirectory() != null
				&& config.getTargetDirectory().trim().length() > 0) {
			this.db = createDbFileOnSDCard(config.getTargetDirectory(),
					config.getDbName());
		} else {
			this.db = new SqliteDbHelper(config.getContext()
					.getApplicationContext(), config.getDbName(),
					config.getDbVersion(), config.getDbUpdateListener())
					.getWritableDatabase();
		}
		this.config = config;
	}

	private synchronized static QkDb getInstance(DaoConfig daoConfig) {
		QkDb dao = daoMap.get(daoConfig.getDbName());
		if (dao == null) {
			dao = new QkDb(daoConfig);
			daoMap.put(daoConfig.getDbName(), dao);
		}
		return dao;
	}

	/**
	 * 创建数据库
	 * @param context 上下文
	 */
	public static QkDb create(Context context) {
		DaoConfig config = new DaoConfig();
		config.setContext(context);
		return create(config);
	}

	/**
	 * 创建数据库
	 * @param context 上下文
	 * @param isDebug
	 *            是否是debug模式（debug模式进行数据库操作的时候将会打印sql语句）
	 */
	public static QkDb create(Context context, boolean isDebug) {
		DaoConfig config = new DaoConfig();
		config.setContext(context);
		config.setDebug(isDebug);
		return create(config);

	}

	/**
	 * 创建数据库
	 * @param context 上下文
	 * @param dbName
	 *            数据库名称
	 */
	public static QkDb create(Context context, String dbName) {
		DaoConfig config = new DaoConfig();
		config.setContext(context);
		config.setDbName(dbName);
		return create(config);
	}

	/**
	 * 创建数据库
	 * @param context 上下文
	 * @param dbName 数据库名称
	 * @param isDebug 是否为debug模式（debug模式进行数据库操作的时候将会打印sql语句）
	 */
	public static QkDb create(Context context, String dbName, boolean isDebug) {
		DaoConfig config = new DaoConfig();
		config.setContext(context);
		config.setDbName(dbName);
		config.setDebug(isDebug);
		return create(config);
	}

	/**
	 * 创建数据库
	 * @param context 上下文
	 * @param targetDirectory  保存数据库到sd卡路径
	 * @param dbName 数据库名字
	 * @return
	 */
	public static QkDb create(Context context, String targetDirectory,
			String dbName) {
		DaoConfig config = new DaoConfig();
		config.setContext(context);
		config.setDbName(dbName);
		config.setTargetDirectory(targetDirectory);
		return create(config);
	}

	/**
	 * 创建数据库
	 * @param context 上下文
	 * @param targetDirectory  保存数据库到sd卡路径
	 * @param dbName
	 *            数据库名称
	 * @param isDebug
	 *            是否为debug模式（debug模式进行数据库操作的时候将会打印sql语句）
	 */
	public static QkDb create(Context context, String targetDirectory,
			String dbName, boolean isDebug) {
		DaoConfig config = new DaoConfig();
		config.setContext(context);
		config.setTargetDirectory(targetDirectory);
		config.setDbName(dbName);
		config.setDebug(isDebug);
		return create(config);
	}

	/**
	 * 创建数据库
	 * @param context 上下文
	 * @param dbName
	 *            数据库名字
	 * @param isDebug
	 *            是否是调试模式：调试模式会log出sql信息
	 * @param dbVersion
	 *            数据库版本信息
	 * @param dbUpdateListener
	 *            数据库升级监听器：如果监听器为null，升级的时候将会清空所所有的数据
	 * @return
	 */
	public static QkDb create(Context context, String dbName,
			boolean isDebug, int dbVersion, DbUpdateListener dbUpdateListener) {
		DaoConfig config = new DaoConfig();
		config.setContext(context);
		config.setDbName(dbName);
		config.setDebug(isDebug);
		config.setDbVersion(dbVersion);
		config.setDbUpdateListener(dbUpdateListener);
		return create(config);
	}

	/**
	 * 创建数据库
	 * @param context
	 *            上下文
	 * @param targetDirectory
	 *            db文件路径，可以配置为sdcard的路径
	 * @param dbName
	 *            数据库名字
	 * @param isDebug
	 *            是否是调试模式：调试模式会log出sql信息
	 * @param dbVersion
	 *            数据库版本信息
	 * @param dbUpdateListener 数据库升级监听器
	 *            ：如果监听器为null，升级的时候将会清空所所有的数据
	 * @return
	 */
	public static QkDb create(Context context, String targetDirectory,
			String dbName, boolean isDebug, int dbVersion,
			DbUpdateListener dbUpdateListener) {
		DaoConfig config = new DaoConfig();
		config.setContext(context);
		config.setTargetDirectory(targetDirectory);
		config.setDbName(dbName);
		config.setDebug(isDebug);
		config.setDbVersion(dbVersion);
		config.setDbUpdateListener(dbUpdateListener);
		return create(config);
	}

	/**
	 * 创建数据库
	 * 
	 * @param daoConfig
	 * @return
	 */
	public static QkDb create(DaoConfig daoConfig) {
		return getInstance(daoConfig);
	}

	/**
	 * 先检查是否有这张表,如果没有表先创建表 再插入数据
	 * @param entity 表对应的对像
	 */
	public void save(Object entity) {
		checkTableExist(entity.getClass());
		exeSqlInfo(SqlBuilder.buildInsertSql(entity));
	}

	/**
	 * 保存数据到数据库<br />
	 * <b>注意：</b><br />
	 * 保存成功后，entity的主键将被赋值（或更新）为数据库的主键， 只针对自增长的id有效
	 * 
	 * @param entity
	 *            要保存的数据
	 * @return ture： 保存成功 false:保存失败
	 */
	public boolean saveBindId(Object entity) {
		checkTableExist(entity.getClass());
		List<KeyValue> entityKvList = SqlBuilder
				.getSaveKeyValueListByEntity(entity);
		if (entityKvList != null && entityKvList.size() > 0) {
			TableInfo tf = TableInfo.get(entity.getClass());
			ContentValues cv = new ContentValues();
			insertContentValues(entityKvList, cv);
			Long id = db.insert(tf.getTableName(), null, cv);
			if (id == -1)
				return false;
			tf.getId().setValue(entity, id);
			return true;
		}
		return false;
	}

	/**
	 * 把List<KeyValue>数据存储到ContentValues
	 *
	 * @param list
	 * @param cv
	 */
	private void insertContentValues(List<KeyValue> list, ContentValues cv) {
		if (list != null && cv != null) {
			for (KeyValue kv : list) {
				cv.put(kv.getKey(), kv.getValue().toString());
			}
		} else {
			Log.w(TAG,
					"insertContentValues: List<KeyValue> is empty or ContentValues is empty!");
		}

	}

	/**
	 * 更新数据 （主键ID必须不能为空）
	 * 
	 * @param entity  表对应的对像
	 */
	public void update(Object entity) {
		checkTableExist(entity.getClass());
		exeSqlInfo(SqlBuilder.getUpdateSqlAsSqlInfo(entity));
	}

	/**
	 * 根据条件更新数据
	 * 
	 * @param entity 表对应的对像
	 * @param strWhere
	 *            条件为空的时候，将会更新所有的数据
	 */
	public void update(Object entity, String strWhere) {
		checkTableExist(entity.getClass());
		exeSqlInfo(SqlBuilder.getUpdateSqlAsSqlInfo(entity, strWhere));
	}

	/**
	 * 删除数据
	 * 
	 * @param entity
	 *            表对应的对像
	 */
	public void delete(Object entity) {
		checkTableExist(entity.getClass());
		exeSqlInfo(SqlBuilder.buildDeleteSql(entity));
	}

	/**
	 * 根据主键删除数据
	 * 
	 * @param clazz
	 *            表对应的对像
	 * @param id
	 *            主键值
	 */
	public void deleteById(Class<?> clazz, Object id) {
		checkTableExist(clazz);
		exeSqlInfo(SqlBuilder.buildDeleteSql(clazz, id));
	}

	/**
	 * 根据条件删除数据
	 * @param clazz 表对应的对像
	 * @param strWhere
	 *            条件为空的时候 将会删除所有的数据
	 */
	public void deleteByWhere(Class<?> clazz, String strWhere) {
		checkTableExist(clazz);
		String sql = SqlBuilder.buildDeleteSql(clazz, strWhere);
		debugSql(sql);
		db.execSQL(sql);
	}

	/**
	 * 删除表的所有数据
	 * @param clazz 表对应的对像
	 */
	public void deleteAll(Class<?> clazz) {
		checkTableExist(clazz);
		String sql = SqlBuilder.buildDeleteSql(clazz, null);
		debugSql(sql);
		db.execSQL(sql);
	}

	/**
	 * 删除指定的表
	 * @param clazz 表对应的对像
	 */
	public void dropTable(Class<?> clazz) {
		checkTableExist(clazz);
		TableInfo table = TableInfo.get(clazz);
		String sql = "DROP TABLE " + table.getTableName();
		debugSql(sql);
		db.execSQL(sql);
	}

	/**
	 * 删除所有数据表
	 */
	public void dropDb() {
		Cursor cursor = db.rawQuery(
				"SELECT name FROM sqlite_master WHERE type ='table' AND name != 'sqlite_sequence'", null);
		if (cursor != null) {
			while (cursor.moveToNext()) {
				db.execSQL("DROP TABLE " + cursor.getString(0));
			}
		}
		if (cursor != null) {
			cursor.close();
			cursor = null;
		}
	}

	/**
	 * 执行sql语句
	 * @param sqlInfo sql对像
	 */
	private void exeSqlInfo(SqlInfo sqlInfo) {
		if (sqlInfo != null) {
			debugSql(sqlInfo.getSql());
			db.execSQL(sqlInfo.getSql(), sqlInfo.getBindArgsAsArray());
		} else {
			Log.e(TAG, "sava error:sqlInfo is null");
		}
	}

	/**
	 * 根据主键查找数据
	 * 
	 * @param id 主键id
	 * @param clazz 表对应的对像
	 * @return 符合条件值对像列表
	 */
	public <T> T findById(Object id, Class<T> clazz) {
		checkTableExist(clazz);
		SqlInfo sqlInfo = SqlBuilder.getSelectSqlAsSqlInfo(clazz, id);
		if (sqlInfo != null) {
			debugSql(sqlInfo.getSql());
			Cursor cursor = db.rawQuery(sqlInfo.getSql(),
					sqlInfo.getBindArgsAsStringArray());
			try {
				if (cursor.moveToNext()) {
					return CursorUtils.getEntity(cursor, clazz, this);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				cursor.close();
			}
		}
		return null;
	}



	/**
	 * 查找所有的数据
	 * @param clazz 表对应的对像
	 * @return 对像列表
	 */
	public <T> List<T> findAll(Class<T> clazz) {
		checkTableExist(clazz);
		return findAllBySql(clazz, SqlBuilder.getSelectSQL(clazz));
	}

	/**
	 * 排序查找所有数据
	 * 
	 * @param clazz 表对应的对像
	 * @param orderBy 排序的字段
	 * @return 对像列表
	 */
	public <T> List<T> findAll(Class<T> clazz, String orderBy) {
		checkTableExist(clazz);
		return findAllBySql(clazz, SqlBuilder.getSelectSQL(clazz)
				+ " ORDER BY " + orderBy);
	}

	/**
	 * 根据条件查找所有数据
	 * 
	 * @param clazz 表对应的对像
	 * @param strWhere 条件为空的时候查找所有数据
	 * @return 符合条件值对像列表
	 */
	public <T> List<T> findAllByWhere(Class<T> clazz, String strWhere) {
		checkTableExist(clazz);
		return findAllBySql(clazz,
				SqlBuilder.getSelectSQLByWhere(clazz, strWhere));
	}

	/**
	 * 根据条件查找所有数据
	 *
	 * @param clazz 表对应的对像
	 * @param map 条件集合
	 * @return 符合条件值对像列表
	 */
	public <T> List<T> findAllByWhere(Class<T> clazz, Map<String,String> map) {
		checkTableExist(clazz);
		SqlInfo sqlInfo = SqlBuilder.getSelectSqlInfo(clazz, map);
		return findAllBySql(clazz,sqlInfo.getSql(),sqlInfo.getWheres());
	}

	/**
	 * 根据条件查找所有数据
	 * 
	 * @param clazz 表对应的对像
	 * @param strWhere	条件为空的时候查找所有数据
	 * @param orderBy	 排序字段
	 * @return 符合条件值对像列表
	 */
	public <T> List<T> findAllByWhere(Class<T> clazz, String strWhere,
			String orderBy) {
		checkTableExist(clazz);
		return findAllBySql(clazz,
				SqlBuilder.getSelectSQLByWhere(clazz, strWhere) + " ORDER BY "
						+ orderBy);
	}

	/**
	 * 根据条件查找所有数据
	 * @param clazz 表对应的对像
	 * @param strSQL 查询sql语句
	 * @return 符合条件值对像列表
	 */
	private <T> List<T> findAllBySql(Class<T> clazz, String strSQL) {
		checkTableExist(clazz);
		debugSql(strSQL);
		Cursor cursor = db.rawQuery(strSQL, null);
		try {
			List<T> list = new ArrayList<T>();
			while (cursor.moveToNext()) {
				T t = CursorUtils.getEntity(cursor, clazz, this);
				list.add(t);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cursor != null)
				cursor.close();
			cursor = null;
		}
		return null;
	}

	/**
	 * 根据条件查找数据
	 * @param clazz 表对应的对像
	 * @param strSQL 查询sql语句
	 * @param wheres 条件数组值
	 * @return 符合条件值对像列表
	 */
	private <T> List<T> findAllBySql(Class<T> clazz, String strSQL,String[] wheres) {
		checkTableExist(clazz);
		debugSql(strSQL);
		Cursor cursor = db.rawQuery(strSQL, wheres);
		try {
			List<T> list = new ArrayList<T>();
			while (cursor.moveToNext()) {
				T t = CursorUtils.getEntity(cursor, clazz, this);
				list.add(t);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cursor != null)
				cursor.close();
			cursor = null;
		}
		return null;
	}

	/**
	 * 检查表是否存在,如果不存在就创建表
	 * @param clazz 表对应的对像
	 */
	private void checkTableExist(Class<?> clazz) {
		if (!tableIsExist(TableInfo.get(clazz))) {
			/**
			 * 表不存 创建表
			 */
			String sql = SqlBuilder.getCreatTableSQL(clazz);
			debugSql(sql);
			db.execSQL(sql);
		}
	}

	/**
	 * 检查表是否存在
	 * @param table
	 * @return
	 */
	private boolean tableIsExist(TableInfo table) {
		if (table.isCheckDatabese())
			return true;

		Cursor cursor = null;
		try {
			String sql = "SELECT COUNT(*) AS c FROM sqlite_master WHERE type ='table' AND name ='"
					+ table.getTableName() + "' ";
			debugSql(sql);
			cursor = db.rawQuery(sql, null);
			if (cursor != null && cursor.moveToNext()) {
				int count = cursor.getInt(0);
				if (count > 0) {
					table.setCheckDatabese(true);
					return true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cursor != null)
				cursor.close();
			cursor = null;
		}

		return false;
	}

	/**
	 * 打印sql语句
	 * @param sql
	 */
	private void debugSql(String sql) {
		if (config != null && config.isDebug())
			Log.d("Debug SQL", ">>>>>>  " + sql);
	}

	/**
	 * 数据库配置
	 */
	public static class DaoConfig {
		private Context mContext = null; // android上下文
		private String mDbName = "afinal.db"; // 数据库名字
		private int dbVersion = 1; // 数据库版本
		private boolean debug = true; // 是否是调试模式（调试模式 增删改查的时候显示SQL语句）
		private DbUpdateListener dbUpdateListener;
		// private boolean saveOnSDCard = false;//是否保存到SD卡
		private String targetDirectory;// 数据库文件在sd卡中的目录

		public Context getContext() {
			return mContext;
		}

		public void setContext(Context context) {
			this.mContext = context;
		}

		public String getDbName() {
			return mDbName;
		}

		public void setDbName(String dbName) {
			this.mDbName = dbName;
		}

		public int getDbVersion() {
			return dbVersion;
		}

		public void setDbVersion(int dbVersion) {
			this.dbVersion = dbVersion;
		}

		public boolean isDebug() {
			return debug;
		}

		public void setDebug(boolean debug) {
			this.debug = debug;
		}

		public DbUpdateListener getDbUpdateListener() {
			return dbUpdateListener;
		}

		public void setDbUpdateListener(DbUpdateListener dbUpdateListener) {
			this.dbUpdateListener = dbUpdateListener;
		}

		// public boolean isSaveOnSDCard() {
		// return saveOnSDCard;
		// }
		//
		// public void setSaveOnSDCard(boolean saveOnSDCard) {
		// this.saveOnSDCard = saveOnSDCard;
		// }

		public String getTargetDirectory() {
			return targetDirectory;
		}

		public void setTargetDirectory(String targetDirectory) {
			this.targetDirectory = targetDirectory;
		}
	}

	/**
	 * 在SD卡的指定目录上创建文件
	 * 
	 * @param sdcardPath
	 * @param dbfilename
	 * @return
	 */
	private SQLiteDatabase createDbFileOnSDCard(String sdcardPath,
			String dbfilename) {
		File dbf = new File(sdcardPath, dbfilename);
		if (!dbf.exists()) {
			try {
				if (dbf.createNewFile()) {
					return SQLiteDatabase.openOrCreateDatabase(dbf, null);
				}
			} catch (IOException ioex) {
				throw new DbException("数据库文件创建失败", ioex);
			}
		} else {
			return SQLiteDatabase.openOrCreateDatabase(dbf, null);
		}

		return null;
	}

	class SqliteDbHelper extends SQLiteOpenHelper {

		private DbUpdateListener mDbUpdateListener;

		public SqliteDbHelper(Context context, String name, int version,
				DbUpdateListener dbUpdateListener) {
			super(context, name, null, version);
			this.mDbUpdateListener = dbUpdateListener;
		}

		@Override
        public void onCreate(SQLiteDatabase db) {
		}

		@Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			if (mDbUpdateListener != null) {
				mDbUpdateListener.onUpgrade(db, oldVersion, newVersion);
			} else { // 清空所有的数据信息
				dropDb();
			}
		}

	}

}
