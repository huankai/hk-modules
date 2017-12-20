package com.hk.pms.core.service;

import java.util.Map;
import java.util.Optional;

import com.hk.core.service.BaseService;
import com.hk.core.service.StatusService;
import com.hk.pms.core.domain.User;

/**
 * @author huangkai
 * @date 2017年9月27日下午2:17:58
 */
public interface UserService extends BaseService<User,String>,StatusService {

    /**
     * @param loginName 登陆用户名，也可以是手机号、邮箱
     * @return
     */
    Optional<User> findUniqueByLoginName(final String loginName);

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    boolean saveUser(User user);
    
    /**
     * 是否存在登陆名
     * @param loginName
     * @return
     */
    boolean existsByLoginName(String loginName);
    
//    /**
//     * 组织机构是否存在登陆名
//     * @param loginName
//     * @param orgId
//     * @return
//     */
//    boolean existsByLoginName(String loginName,String orgId);

    /**
     * <pre>
     * Key :角色
     * Value:权限
     * </pre>
     *
     * @param userId
     * @return
     */
    Map<String, String> getRoleAndPermissions(String userId);

    /**
     * 根据用户id更新密码
     *
     * @param userId      用户id
     * @param oldPassword 原密码
     * @param newPassword 新密码
     */
    boolean updatePasswordByUserId(String userId, String oldPassword, String newPassword);

    /**
     * 根据用户手机号与发送的验证码更新密码
     *
     * @param phone            用户手机号
     * @param verificationCode 手机号验证码
     * @param newPassword      新密码
     * @return
     */
    boolean updatePasswordByPhone(String phone, String verificationCode, String newPassword);

    /**
     * 根据用户邮箱与发送的验证码更新密码
     *
     * @param email            用户邮箱
     * @param verificationCode 邮箱验证码
     * @param newPassword      新密码
     * @return
     */
    boolean updatePasswordByEmail(String email, String verificationCode, String newPassword);

}
