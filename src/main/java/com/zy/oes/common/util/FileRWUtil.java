package com.zy.oes.common.util;

import cn.hutool.core.io.FileUtil;
import com.zy.oes.common.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import java.io.*;
import java.util.Date;

/**
 * @projectName: OnlineExaminationSystem
 * @className: FileRWUtil
 * @author: MoZhu
 * @date: 2023/4/24 0:55
 * @description: <p> 文件工具类（区别于hutool的FileUtil） </p>
 */
@Slf4j
public class FileRWUtil {

    /**
     * 缓冲区大小(KB)
     */
    private static final int BUFFER_SIZE = 1000;

    /**
     * @title storeFile
     * @description <p> 存储文件 </p>
     * @date 2023/4/24 1:01
     * @author MoZhu
     * @param file 文件对象
     * @param path 文件存储路径
     * @param fileName 文件名
     * @return {@link boolean}
     */
    public static boolean storeFile(MultipartFile file, String path, String fileName) {
        if (file == null || file.isEmpty()) {
            return false;
        }

        log.info("存储文件：{}", fileName);

        File fullFilePath = new File(path + File.separator + fileName);

        if (!FileUtil.exist(path)) {
            // 创建文件夹
            FileUtil.mkParentDirs(fullFilePath.toString());
            log.info("创建文件夹：{}", path);
        }
        try {
            file.transferTo(fullFilePath);
        } catch (IOException e) {
            log.error("文件存储失败：{}", e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * @title loadFile
     * @description <p> 读取文件 </p>
     * @date 2023/5/3 22:10
     * @author MoZhu
     * @param path 路径
     * @param fileName 文件名
     * @param stream 输出流
     * @return {@link boolean}
     */
    public static boolean loadFile(String path, String fileName, OutputStream stream) {
        log.info("加载文件：{}", fileName);

        File fullFilePath = new File(path + File.separator + fileName);

        if (!FileUtil.exist(path)) {
            // 文件不存在
            log.error("文件不存在，路径：{}", fullFilePath);
            return false;
        }

        // 读取文件
        BufferedInputStream inputStream = FileUtil.getInputStream(fullFilePath);
        byte[] buffer;
        int len;
        try {
            // 循环读取文件
            do {
                buffer = new byte[BUFFER_SIZE];
                len = inputStream.read(buffer);
                stream.write(buffer);
            } while (len == BUFFER_SIZE);
        }catch (IOException e) {
            log.error("文件读取失败：{}", fullFilePath);
        }
        return true;
    }

    /**
     * @title generateFileName
     * @description <p> 通过文件对象生成文件名 </p>
     * @date 2023/4/24 1:23
     * @author MoZhu
     * @param file 文件对象
     * @param name 文件名称（不包含文件后缀名）
     * @return {@link String}
     */
    public static String generateFileName(MultipartFile file, String name) {
        if (file == null || file.isEmpty()) {
            return "";
        }
        assert file.getOriginalFilename() != null;
        // 获取原始文件名称
        String originFileName = file.getOriginalFilename();
        // 获取文件后缀
        String suffixFileName = originFileName.substring(originFileName.lastIndexOf("."));
        // 构造完整文件名
        return name + new Date().getTime() + suffixFileName;
    }
}
