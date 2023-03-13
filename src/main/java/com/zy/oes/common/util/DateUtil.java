package com.zy.oes.common.util;

import java.text.SimpleDateFormat;

/**
 * @projectName: OnlineExaminationSystem
 * @className: DateUtil
 * @author: MoZhu
 * @date: 2023/3/13 23:51
 * @description: <p> 日期格式工具类 </p>
 */
public class DateUtil {

    /**
     * 完整日期时间格式
     */
    public static final String FULL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 年月日格式
     */
    public static final String YMD_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 时分秒格式
     */
    public static final String HMS_DATE_FORMAT = "HH:mm:ss";

    /**
     * @title getFormat
     * @description <p> 获取日期格式转换器 </p>
     * @date 2023/3/13 23:58
     * @author MoZhu
     * @param format 日期格式字符串
     * @return {@link SimpleDateFormat}
     */
    public static SimpleDateFormat getFormat(String format) {
        return new SimpleDateFormat(format);
    }
}
