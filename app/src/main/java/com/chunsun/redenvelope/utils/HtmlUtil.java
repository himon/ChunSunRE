package com.chunsun.redenvelope.utils;

import java.util.List;

public class HtmlUtil {

	public static String getHtml(String content) {
		final StringBuilder sb = new StringBuilder();
		sb.append("<!DOCTYPE html>");
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<meta name=\"viewport\" content=\"width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=1.0; user-scalable=no;\"/> ");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<p style=\"word-break: break-all; word-wrap:break-word; text-align: justify;\">");
		sb.append(content);
		sb.append("</p>");
		sb.append("</body>");
		sb.append("</html>");
		return sb.toString();
	}

	public static String replaceUrl(String content) {
		List<String> matcherHttp = RegexUtil.matcherHttp(content);
		content = content.replaceAll("\n", "<br>");
		content = content.replaceAll(" ", "&nbsp;");
		for (int i = 0; i < matcherHttp.size(); i++) {
			String str = matcherHttp.get(i);
			str = str.replace("?", "\\?");
			content = content.replaceAll(str, "<a href=\"" + matcherHttp.get(i)
					+ "\">" + matcherHttp.get(i) + "</a>");

		}
		return content;
	}

}