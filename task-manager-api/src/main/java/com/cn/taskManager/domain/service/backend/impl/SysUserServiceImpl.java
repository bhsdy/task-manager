package com.cn.taskManager.domain.service.backend.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cn.taskManager.common.constants.Constant;
import com.cn.taskManager.common.exptions.ValidateException;
import com.cn.taskManager.common.utils.EncryptUtil;
import com.cn.taskManager.common.utils.FastJsonUtils;
import com.cn.taskManager.domain.entity.SysUser;
import com.cn.taskManager.domain.mapper.backend.SysUserMapper;
import com.cn.taskManager.domain.service.backend.SysUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    /**
     * 修改密码
     *
     * @param currentUser 当前登录的用户信息
     * @param old_pwd
     * @param new_pwd
     * @return 修改失败返回错误信息，修改成功返回authKey信息。
     */
    public String setInfo(SysUser currentUser, String old_pwd, String new_pwd) {
        if (currentUser == null) {
            return FastJsonUtils.resultError("-400", "请先登录", null);
        }

        if (StringUtils.isNotBlank(old_pwd)) {
            return FastJsonUtils.resultError("-400", "旧密码必填", null);
        }

        if (StringUtils.isNotBlank(new_pwd)) {
            return FastJsonUtils.resultError("-400", "新密码必填", null);
        }

        if (old_pwd.equals(new_pwd)) {
            return FastJsonUtils.resultError("-400", "新旧密码不能一样", null);
        }

        if (!currentUser.getPassword().equals(DigestUtils.md5Hex(old_pwd))) {
            return FastJsonUtils.resultError("-400", "原密码错误", null);
        }

        if (!currentUser.getPassword().equals(DigestUtils.md5Hex(old_pwd))) {
            return FastJsonUtils.resultError("-400", "原密码错误", null);
        }
        SysUser record = new SysUser();
        record.setId(currentUser.getId());
        String md5NewPwd = DigestUtils.md5Hex(new_pwd);
        record.setPassword(md5NewPwd);
        boolean b = this.updateById(record);
        if (b) {
            String authKey = EncryptUtil.encryptBase64(currentUser.getUserName() + "|" + md5NewPwd, Constant.SECRET_KEY);
            //@TODO 更新缓存中auth_key
            return FastJsonUtils.resultError("200", "修改成功", authKey);
        }
        return FastJsonUtils.resultError("1001", "修改失败", null);
    }

    @Override
    public Page<SysUser> getDataList(SysUser record) throws Exception{
        EntityWrapper<SysUser> ew = new EntityWrapper<>();
        return this.selectPage(new Page<>(record.getPageNum(), record.getPageSize()), ew);
    }

    @Override
    public SysUser save(SysUser sysUser) throws ValidateException {
        if(sysUser == null){
            throw new ValidateException("传入为空");
        }
        if(StringUtils.isEmpty(sysUser.getId())){
            if(sysUser.getUserName() == null) {
                throw new ValidateException("用户名为空");
            }
            if(StringUtils.isEmpty(sysUser.getPassword())){
                throw new ValidateException("密码为空");
            }
            if(sysUser.getCreateTime() == null){
                throw new ValidateException("创建时间为空");
            }
            if(StringUtils.isEmpty(sysUser.getRealName())){
                throw new ValidateException("真实姓名为空");
            }
            if(StringUtils.isEmpty(sysUser.getStructureId())){
                throw new ValidateException("部门为空");
            }
            if(StringUtils.isEmpty(sysUser.getPostId())){
                throw new ValidateException("岗位为空");
            }
            sysUser.setId(UUID.randomUUID().toString().replace("-",""));
            super.insert(sysUser);
        }else {
            super.updateById(sysUser);
            sysUser = super.selectById(sysUser);
        }
        return sysUser;
    }

    @Override
    public Boolean delete(String id) throws Exception{
        boolean b = super.deleteById(id);
        if(b){
            return b;
        }
        throw new Exception("删除失败");
    }

    @Override
    public Boolean deleteBatch(List<String> ids) throws Exception{
        if(CollectionUtils.isEmpty(ids)){
            throw new ValidateException("ID为空");
        }
        boolean b = super.deleteBatchIds(ids);
        if(b){
            return super.deleteBatchIds(ids);
        }
        throw new ValidateException("删除失败");
    }
}
