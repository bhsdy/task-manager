package com.cn.taskManager.domain.service.backend;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface UploadService {
    Map<String,Object> uploadFile(MultipartFile file) throws Exception;
    boolean deleteFile(String fileUrl) throws Exception;
}
