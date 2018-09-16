package com.cn.taskManager.controller.backend;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cn.taskManager.common.CommonController;
import com.cn.taskManager.common.constants.Constant;
import com.cn.taskManager.common.utils.EncryptUtil;
import com.cn.taskManager.common.utils.FastJsonUtils;
import com.cn.taskManager.domain.entity.*;
import com.cn.taskManager.domain.service.backend.*;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 * 登录控制层
 * @author leo.aqing
 *
 */
@RestController
@RequestMapping("/admin")
@Api(value = "LoginController", description="登录接口")
public class LoginController extends CommonController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserDetailService sysUserDetailService;
	@Autowired
	private SysRuleService sysRuleService;
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private DefaultKaptcha captchaProducer;
	@Autowired
	private SysPostService sysPostService;
	@Autowired
	private SysStructureService sysStructureService;

	/**
	 * 登录
	 * @param record
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "登录", notes = "登录")
	@ApiImplicitParams({@ApiImplicitParam(name = "record", required=true, dataType = "SysUser")	})
	@PostMapping(value = "/login", produces = {"application/json;charset=UTF-8"})
	public String login(@RequestBody SysUser record, HttpServletRequest request) {
		Map<String, Object> data = new HashMap<String, Object>();
		if(StringUtils.isBlank(record.getUserName())) {
			return FastJsonUtils.resultError("-100", "账号不能为空", null);
		}
		record.setPassword(DigestUtils.md5Hex(record.getPassword()));
		EntityWrapper<SysUser> ew = new EntityWrapper<>();
		ew.where("user_name={0} and password={1}",record.getUserName(),record.getPassword());
		List<SysUser> sysUserList = sysUserService.selectList(ew);

		if(CollectionUtils.isEmpty(sysUserList)) {
			return FastJsonUtils.resultError("-100", "帐号与密码错误不正确", null);
		}
		if(sysUserList.get(0).getStatus().equals(0)){
			return FastJsonUtils.resultError("-400", "该用户已作废", null);
		}
		SysUser sysUser = sysUserList.get(0);
		if(!sysUser.getStatus().equals(Byte.valueOf("1"))) {
			return FastJsonUtils.resultError("-100", "帐号已被禁用", null);
		}
		//查找用户详情
		EntityWrapper<SysUserDetail> ew2 = new EntityWrapper<>();
		ew2.where("user_id={0}",sysUser.getId());
		List<SysUserDetail> sysUserDetailList = sysUserDetailService.selectList(ew2);
		if(!CollectionUtils.isEmpty(sysUserDetailList)){
			sysUser.setSysUserDetail(sysUserDetailList.get(0));
		}
		//查找用户岗位
		SysPost sysPost = sysPostService.selectById(sysUser.getPostId());
		if(sysPost != null){
			sysUser.setSysPost(sysPost);
			//查找用户部门
			SysStructure sysStructure = sysStructureService.selectById(sysPost.getStructureId());
			sysUser.setSysStructure(sysStructure);
		}

		String authKey = EncryptUtil.encryptBase64(sysUser.getUserName()+"|"+sysUser.getPassword(), Constant.SECRET_KEY);
		// 返回信息
		data.put("rememberKey", authKey);
		data.put("authKey", authKey);
		data.put("sessionId", request.getSession().getId());
		data.put("userInfo", sysUser);
		List<SysRule> rulesTreeList = sysRuleService.getTreeRuleByUserId(sysUser.getId());
		List<String> rulesList = sysRuleService.rulesDeal(rulesTreeList);
		data.put("rulesList", rulesList);
		data.put("menusList", sysMenuService.getTreeMenuByUserId(sysUser.getId()));

		return FastJsonUtils.resultSuccess("200", "登录成功", data);
	}


	/**
	 * 重新登录
	 * @param rememberKey
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "重新登录", notes = "", httpMethod = "POST")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "rememberKey", value ="登录成功后的授权码", required = true, dataType = "String")
	})
	@RequestMapping(value = "/relogin", produces = {"application/json;charset=UTF-8"})
	public String relogin(String rememberKey,HttpServletRequest request) {
		String rememberValue = EncryptUtil.decryptBase64(rememberKey, Constant.SECRET_KEY);
		String[] splits = rememberValue.split("|");
		EntityWrapper<SysUser> ew = new EntityWrapper<>();
		ew.where("user_name={0} and password={1}",splits[0],splits[1]);

		List<SysUser> sysUserList = sysUserService.selectList(ew);
		if(CollectionUtils.isEmpty(sysUserList)) {
			return FastJsonUtils.resultError("-400", "重新登录失败", null);
		}
		if(sysUserList.get(0).getStatus().equals(0)){
			return FastJsonUtils.resultError("-400", "该用户已作废", null);
		}
		return FastJsonUtils.resultSuccess("200", "重新登录成功", null);
	}

	/**
	 * 登出
	 * @param session
	 * @return
	 */
	@ApiOperation(value = "登出", notes = "")
	@PostMapping(value = "/logout", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String logout(HttpSession session){
		session.invalidate();
		return FastJsonUtils.resultSuccess("200", "退出成功", null);
	}

	/***
	 * 验证码
	 */
	@ApiOperation(value = "验证码", notes = "")
	@GetMapping(value = "/verify")
	public void verify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control",
                "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        String capText = captchaProducer.createText();
        System.out.println("capText: " + capText);

        try {
            String uuid=UUID.randomUUID().toString();
            //redisTemplate.opsForValue().set(uuid, capText,60*5,TimeUnit.SECONDS);
            Cookie cookie = new Cookie("captchaCode",uuid);
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }

        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
	}

	/**
	 * 修改密码
	 * @param old_pwd
	 * @param new_pwd
	 */
	@PostMapping(value = "/setInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@ApiOperation(value = "修改密码", notes = "")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "old_pwd", value ="旧密码", required = true, dataType = "String"),
		@ApiImplicitParam(name = "new_pwd", value ="新密码", required = true, dataType = "String")
	})
	public String setInfo(String old_pwd, String new_pwd){
		try {
			sysUserService.setInfo(this.getCurrentUser(), old_pwd, new_pwd);
		} catch (Exception e) {
			return FastJsonUtils.resultSuccess("1001", "修改失败", e.getMessage());
		}
		return FastJsonUtils.resultSuccess("200", "修改成功", null);
	}
}
