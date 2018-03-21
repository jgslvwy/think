package util;

import java.util.Date;

public class KeyUtil {
	/**
	 * 根据标识和时间戳进行处理,返回String类型字符串
	 * @return
	 */
	public static String addKey(Date date, String sign) {
		return String.valueOf(date.getTime()) + "|" + sign;
	}
}
