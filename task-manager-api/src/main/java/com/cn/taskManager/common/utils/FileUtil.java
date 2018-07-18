package com.cn.taskManager.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 *
 * @author Evan
 * @date 2018/04/27
 */
public class FileUtil {
    private static final Logger LOG = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 写文件
     * @param path 路径
     * @param fileName 文件名称
     * @param content 内容
     */
    public static void writeFile(String path,String fileName,String content){
        Path filePath = Paths.get(path+fileName);
        if(!Files.exists(filePath.getParent())){
            try {
                Files.createDirectories(filePath.getParent());
            } catch (IOException e) {
                LOG.error("创建文件夹失败！", e);
            }
        }

        if (!Files.exists(filePath)) {
            try {
                Files.write(filePath, content.toString().getBytes("UTF-8"));
            } catch (IOException e) {
                LOG.error("首次写文件失败！", e);
                // e.printStackTrace();
            }
        } else {// 存在则追加
            try {
                Files.write(filePath, content.getBytes("UTF-8"),
                        StandardOpenOption.APPEND);
            } catch (IOException e) {
                LOG.error("追加写文件失败！", e);
                // e.printStackTrace();
            }
        }
    }

    /**
     * 下载文件到txt
     * @param response
     * @param title
     * @param content 内容
     */
    public static void exportToTxt(HttpServletResponse response, String title, StringBuilder content){
        response.setContentType("text/plain");
        BufferedOutputStream buff = null;
        ServletOutputStream outSTr = null;
        try{
            response.setHeader("Content-disposition", "attachment; filename=" + new String((title).getBytes("utf-8"), "utf-8") + ".txt");
            outSTr = response.getOutputStream();
            buff = new BufferedOutputStream(outSTr);
            buff.write(content.toString().getBytes("utf-8"));
            buff.flush();
            buff.close();

        }catch (Exception e){
            LOG.error("exportToTXT 导出异常!",e);
        }finally {
            try {
                buff.close();
                outSTr.close();
            } catch (Exception e) {
                LOG.info("exportToTXT close stream error!",e);
            }
        }

    }
}
