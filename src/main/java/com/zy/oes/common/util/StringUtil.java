package com.zy.oes.common.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @projectName: OnlineExaminationSystem
 * @className: StringUtil
 * @author: MoZhu
 * @date: 2023/3/20 0:34
 * @description: <p> 字符串工具类 </p>
 */
public class StringUtil {

    /**
     * @title combineStringToList
     * @description <p> 组合字符串转换字符串列表 </p>
     * @date 2023/3/20 0:36
     * @author MoZhu
     * @param origin 组合字符串
     * @return {@link List<String>}
     */
    public static List<String> combineStringToList(String origin) {
        String[] strings = origin.split(" ");
        return Arrays.stream(strings).map(str -> str.replaceAll("<blank>", " ")).collect(Collectors.toList());
    }

    /**
     * @title listToCombineString
     * @description <p> 字符串列表转换组合字符串 </p>
     * @date 2023/3/20 1:03
     * @author MoZhu
     * @param list 字符串列表
     * @return {@link String}
     */
    public static String listToCombineString(List<String> list) {
        // 判空
        if (list == null || list.size() == 0) return null;
        return list.stream().map(str -> str.replaceAll(" ", "<blank>")).map(str -> str + " ").collect(Collectors.joining());
    }
}
