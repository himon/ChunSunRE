package com.chunsun.redenvelope.scanlibrary.scan;

public class ScanUtil {

	/**
	 * 验证扫描出的内容是否是url
	 * 
	 * @param result
	 * @return
	 */
	public static boolean urlValidate(String result) {
		String regExp = "((http|ftp|https):\\/\\/)?[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?";
		return result.matches(regExp);
	}
}
