package com.cn.taskManager.controller.backend;

import com.cn.taskManager.common.CommonController;
import com.cn.taskManager.common.utils.FastJsonUtils;
import com.cn.taskManager.common.utils.UploadUtils;
import com.cn.taskManager.domain.service.backend.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 文件上传控制器
 *
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:11:42
 */
@RestController
@Api(value = "文件上传接口", description = "文件上传接口")
@RequestMapping(value = "/upload", method = RequestMethod.POST)
public class UploadController extends CommonController {


	@Value("${spring.http.multipart.location}")
	private String multipartLocation;

	@Autowired
	private UploadService uploadService;



	// 上传文件(支持批量)
	@RequestMapping("/image")
	@ApiOperation(value = "上传图片", httpMethod="POST")
	public String uploadImage(MultipartFile file, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/html;charset=utf-8");
		String imageUrl = request.getParameter("imageUrl");
		 //上传文件
        String path = UploadUtils.saveMartipartFile(multipartLocation, request,file,"users","yyyyMM");
        return FastJsonUtils.resultSuccess("1", "上传成功",path);
	}

	/**
	 * 删除文件
	 * @param fileUrl 文件访问地址
	 * @return
	 */
	@RequestMapping("/deleteFile")
	@ApiOperation(value = "删除文件", httpMethod="POST")
	public String deleteFile(String fileUrl) {
		try {
			boolean b = uploadService.deleteFile(fileUrl);
			if(! b){
				return FastJsonUtils.resultSuccess("1001", "删除失败",null);
			}
		} catch (Exception e) {
			return FastJsonUtils.resultSuccess("1001", "删除失败",e.getMessage());
		}
		return FastJsonUtils.resultSuccess("200", "删除成功",null);
	}

	@RequestMapping("/uploadFile")
	@ApiOperation(value = "上传图片", httpMethod="POST")
	public String uploadFile(@RequestParam("file") MultipartFile file) {
		Map<String, Object> stringObjectMap;
		try {
			stringObjectMap = uploadService.uploadFile(file);
		} catch (Exception e) {
			return FastJsonUtils.resultSuccess("1001", "上传失败",e);
		}
		return FastJsonUtils.resultSuccess("200", "上传成功",stringObjectMap);
	}



}
