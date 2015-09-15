package com.chunsun.redenvelope.utils;

import android.text.InputFilter;
import android.text.Spanned;
import android.widget.EditText;

public class EditUtils {
    /**
     * 禁止输入空格
     *
     * @param edit
     */
    public static void forbidInputSpace(EditText edit, int length) {
        edit.setFilters(new InputFilter[]{new InputFilter() {

            @Override
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                if (source.toString().equals(" ")) {
                    return "";
                } else {
                    return source;
                }
            }
        }, new InputFilter.LengthFilter(length)});
    }

    public static void forbidInputSpace(EditText edit) {
        edit.setFilters(new InputFilter[]{new InputFilter() {

            @Override
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                if (source.toString().equals(" ")) {
                    return "";
                } else {
                    return source;
                }
            }
        }});
    }

    /**
     * 禁止输入非字母数字
     *
     * @param edit
     */
    public static void limitCharAndNumber(EditText edit) {
        final String digitsFilter = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        edit.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                boolean isHasOther = false;
                if (source != null && source.toString().length() > 0) {
                    char[] array = source.toString().toCharArray();
                    if (array != null && array.length > 0) {
                        for (char ch : array) {
                            if (digitsFilter.indexOf(ch) == -1) {
                                isHasOther = true;
                                break;
                            }
                        }
                    }
                }

                if (!isHasOther) {
                    return source;
                } else {
                    return "";
                }
            }
        }});
    }

    /**
     * 限制特殊字符
     *
     * @param edit
     */
    public static void limitNotInputHanzi(EditText edit) {
        final String digitsFilter = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~!@#$%^&*()_+-=[]{}\\|/`<>,.;':\"";
        edit.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                boolean isHasOther = false;
                if (source != null && source.toString().length() > 0) {
                    char[] array = source.toString().toCharArray();
                    if (array != null && array.length > 0) {
                        for (char ch : array) {
                            if (digitsFilter.indexOf(ch) == -1) {
                                isHasOther = true;
                                break;
                            }
                        }
                    }
                }

                if (!isHasOther) {
                    return source;
                } else {
                    return "";
                }
            }
        }});
    }
}
