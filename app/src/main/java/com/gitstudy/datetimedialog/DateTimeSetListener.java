package com.gitstudy.datetimedialog;
/**
 * 日期时间接口
 * @author clk
 *
 */
public interface DateTimeSetListener {

	/**
	 * 返回前台年,月,日,时,分
	 * @param year
	 * @param monthOfYear
	 * @param dayOfMonth
	 * @param hourOfDay
	 * @param minute
	 */
	public void onDateChanged(int year, int monthOfYear,
							  int dayOfMonth, int hourOfDay, int minute);

}
