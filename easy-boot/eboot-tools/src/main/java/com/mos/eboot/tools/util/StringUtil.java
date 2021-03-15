package com.mos.eboot.tools.util;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class StringUtil extends StringUtils {

    /**
     * 字符串变成charList
     *
     * @param src
     * @return
     */
    public static List<String> string2CharList(String src) {
        if (isBlank(src)) return null;
        List<String> target = new ArrayList<>();
        for (int i = 0; i < src.toCharArray().length; i++) {
            target.add(String.valueOf(src.toCharArray()[i]));
        }
        return target;
    }

    /**
     * 检查是否有重复字符
     *
     * @param src
     * @return
     */
    public static boolean checkDuplicate(String src) {
        List<String> list = string2CharList(src);
        if (list == null) return false;
        Set<String> stringSet = new HashSet<>();
        stringSet.addAll(list);
        if (list.size() == stringSet.size()) {
            return false;
        }
        return true;
    }

    /**
     * 每个字符后添加逗号
     *
     * @param src
     * @return
     */
    public static String addComma(String src) {
        List<String> strings = string2CharList(src);
        return join(strings, ",");
    }

    /**
     * 检查s1是否包含在s2中
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean checkContain(String s1, String s2) {
        if (isBlank(s1) || isBlank(s2)) return false;
        List<String> list1 = string2CharList(s1);
        List<String> list2 = string2CharList(s2);
        for (String s : list1) {
            if (!list2.contains(s)) {
                return false;
            }
        }
        return true;
    }

    public static String genVerifyCode(int length) {
        String str = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(10);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 字符串排序
     * @param str
     * @return
     */
    public static String sortStr(String str){
        if (isBlank(str))
            return null;
        char [] chs=str.toCharArray();
        Arrays.sort(chs);
        return new String(chs);
    }

}
