package com.qk.applibrary.util;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * 配置文件工具
 */
public class SharedPreferencesUtil {

	/**
	 * 根据name获取保存到配置文件的字符串
	 * @param fileName 文件名
	 * @param context 上下文
	 * @param key
	 * @return
	 */
	public static String getSetting(String fileName,Context context, String key) {
		SharedPreferences setting = context.getSharedPreferences(fileName,
				Activity.MODE_PRIVATE);
		return setting.getString(key, "");
	}

	public static int getSettingInt(String fileName,Context context, String key) {
		SharedPreferences setting = context.getSharedPreferences(fileName,
				Activity.MODE_PRIVATE);
		return setting.getInt(key, 0);
	}
	
							

	public static float getSettingFloat(String fileName,Context context, String key) {
		SharedPreferences setting = context.getSharedPreferences(fileName,
				Activity.MODE_PRIVATE);
		return setting.getFloat(key, 0.0f);
	}

	public static long getSettingLong(String fileName,Context context, String key) {
		SharedPreferences setting = context.getSharedPreferences(fileName,
				Activity.MODE_PRIVATE);
		return setting.getLong(key, 0);
	}

	public static boolean getSettingBoolean(String fileName,Context context, String key,
			boolean defaultVal) {
		SharedPreferences setting = context.getSharedPreferences(fileName,
				Activity.MODE_PRIVATE);
		return setting.getBoolean(key, defaultVal);
	}

	public static void clearAll(String fileName,Context context) {
		SharedPreferences setting = context.getSharedPreferences(fileName,
				Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = setting.edit();
		editor.clear();
		editor.commit();
	}

	public static void removeSetting(String fileName,Context context, String key) {
		SharedPreferences setting = context.getSharedPreferences(fileName,
				Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = setting.edit();
		editor.remove(key);
		editor.commit();
	}

	public static String setSetting(String fileName, Context context, String key, String value) {
		SharedPreferences setting = context.getSharedPreferences(fileName,
				Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = setting.edit();
		editor.putString(key, value);
		editor.commit();
		return fileName;
	}

	public static void setSetting(String fileName,Context context, String key, int value) {
		SharedPreferences setting = context.getSharedPreferences(fileName,
				Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = setting.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	public static void setSetting(String fileName,Context context, String key, float value) {
		SharedPreferences setting = context.getSharedPreferences(fileName,
				Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = setting.edit();
		editor.putFloat(key, value);
		editor.commit();
	}

	public static void setSetting(String fileName,Context context, String key, long value) {
		SharedPreferences setting = context.getSharedPreferences(fileName,
				Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = setting.edit();
		editor.putLong(key, value);
		editor.commit();
	}

	public static void setSetting(String fileName,Context context, String key, boolean value) {
		SharedPreferences setting = context.getSharedPreferences(fileName,
				Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = setting.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}


}
