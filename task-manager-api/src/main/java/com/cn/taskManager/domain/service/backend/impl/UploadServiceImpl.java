package com.cn.taskManager.domain.service.backend.impl;

import com.cn.taskManager.common.exptions.ValidateException;
import com.cn.taskManager.common.fastdfs.FastDFSClient;
import com.cn.taskManager.common.fastdfs.FastDFSFile;
import com.cn.taskManager.controller.backend.UploadController;
import com.cn.taskManager.domain.service.backend.UploadService;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Service
@PropertySource(value = "classpath:fdfs_client.conf")
public class UploadServiceImpl implements UploadService {
    private static Logger logger = LoggerFactory.getLogger(UploadController.class);

    @Value("${fastdfsNginxUrl}")
    private String fastdfsNginxUrl;

    @Override
    public Map<String,Object> uploadFile(MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new ValidateException("文件为空");
        }
        Map<String,Object> map = Maps.newHashMap();
        String path=saveFile(file);
        if(StringUtils.isEmpty(path)){
            throw new Exception("上传失败");
        }
        map.put("oldFileName",file.getOriginalFilename());
        map.put("url",path);
     return map;
    }
    /**
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public String saveFile(MultipartFile multipartFile) throws IOException {
        String[] fileAbsolutePath={};
        String fileName=multipartFile.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        byte[] file_buff = null;
        InputStream inputStream=multipartFile.getInputStream();
        if(inputStream!=null){
            int len1 = inputStream.available();
            file_buff = new byte[len1];
            inputStream.read(file_buff);
        }
        inputStream.close();
        FastDFSFile file = new FastDFSFile(fileName, file_buff, ext);
        try {
            fileAbsolutePath = FastDFSClient.upload(file);  //upload to fastdfs
        } catch (Exception e) {
            logger.error("upload file Exception!",e);
        }
        if (fileAbsolutePath==null) {
            logger.error("upload file failed,please upload again!");
        }
        String path=FastDFSClient.getTrackerUrl()+fileAbsolutePath[0]+ "/"+fileAbsolutePath[1];
        return path;
    }

    @Override
    public boolean deleteFile(String fileUrl) throws Exception {
        if (StringUtils.isEmpty(fileUrl)) {
            throw new ValidateException("路径为空");
        }
        String reg = ".*"+fastdfsNginxUrl+"\\/([^\\/]*).*";
        String group_name = fileUrl.replaceAll(reg, "$1");
        String reg2 = ".*\\/"+group_name+"\\/([^@]*).*";
        String remoteFileName = fileUrl.replaceAll(reg2, "$1");
        if(StringUtils.isEmpty(group_name) || StringUtils.isEmpty(remoteFileName)){
            throw new ValidateException("删除失败");
        }
        FastDFSClient.deleteFile(group_name,remoteFileName);
        return true;
    }
}
