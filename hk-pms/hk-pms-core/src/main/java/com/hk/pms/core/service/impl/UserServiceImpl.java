package com.hk.pms.core.service.impl;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.hk.core.exception.ServiceException;
import com.hk.core.repository.BaseRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.pms.core.domain.User;
import com.hk.pms.core.repository.UserRepository;
import com.hk.pms.core.service.UserService;


/**
 * @author huangkai
 * @date 2017年9月27日下午2:16:45
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User,String> implements UserService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private PasswordEncrypt passwordEncrypt;
    
    @Override
    protected BaseRepository<User, String> getBaseRepository() {
    	return userRepository;
    }
    
    @Override
    protected Example<User> getExample(User t) {
    	if(null == t) {
    		t = new User();
    	}
    	return Example.of(t, ExampleMatcher.matching().withIgnoreNullValues().withIgnorePaths("passWord"));
    }

    @Override
    @Transactional(readOnly = false)
    public User disable(String userId) {
//        User user = getById(userId);
//        Integer order = EnumDisplayUtils.getDisplayOrder(User.UserStatus.DISABLE);
//        if (NumberUtils.nequals(order,user.getUserStatus())) {
//            user.setUserStatus(order);
//            updateSelective(user);
//        }
//        return user;
    	return null;
    }

    @Override
    @Transactional(readOnly = false)
    public User enable(String userId) {
//        User user = getById(userId);
//        Integer order = EnumDisplayUtils.getDisplayOrder(User.UserStatus.ENABLE);
//        if (NumberUtils.nequals(order,user.getUserStatus())) {
//            user.setUserStatus(order);
//            updateSelective(user);
//        }
//        return user;
    	return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findUniqueByLoginName(final String loginName) {
        return userRepository.findUniqueByLoginName(loginName);
    }

    @Override
    @Transactional(readOnly = false)
    public final boolean saveUser(User user) {
//        boolean exists = existsByLoginName(user.getUserName());
//        if (exists) {
//            throw new ServiceException("已存在的用户账号");
//        }
//        user.setPassWord(passwordEncrypt.asSha512HashToBase64(user.getPassWord(), user.getUserName()));
//        user.setUserStatus(EnumDisplayUtils.getDisplayOrder(User.UserStatus.ENABLE));//启用状态
//        int rows = insert(user);
        return false;
    }

    @Override
    @Transactional(readOnly = false)
    public boolean existsByLoginName(String loginName) {
        return false;
    }
    
    @Override
    @Transactional(readOnly = false)
    public boolean existsByLoginName(String loginName,String orgId) {
    	return false;
    }

    @Override
    @Transactional(readOnly = false)
    public boolean updatePasswordByUserId(String userId, String oldPassword, String newPassword) {
//        User user = findById(userId);
//        checkUser(user);
//        if (StringUtils.equals(user.getPassWord(), passwordEncrypt.asSha512HashToBase64(oldPassword, user.getUserName()))) {
//            throw new ServiceException("原密码不正确");
//        }
//        user.setPassWord(passwordEncrypt.asSha512HashToBase64(newPassword, user.getUserName()));
//        int rows = updateSelective(user);
//        return rows > 0;
        return false;
    }

    private void checkUser(Optional<User> optional) {
        if (!optional.isPresent()) {
            throw new ServiceException("不存在的用户");
        }
        if (optional.get().locked()) {
            throw new ServiceException("锁定的用户不能修改密码");
        }
    }

    @Override
    @Transactional(readOnly = false)
    public boolean updatePasswordByPhone(String phone, String verificationCode, String newPassword) {
        Optional<User> optional = findUniqueByLoginName(phone);
        checkUser(optional);
        return false;
    }

    @Override
    @Transactional(readOnly = false)
    public boolean updatePasswordByEmail(String email, String verificationCode, String newPassword) {
    	Optional<User> optional = findUniqueByLoginName(email);
        checkUser(optional);
        return false;
    }

    @Override
    public Map<String, String> getRoleAndPermissions(String userId) {
        return Maps.newHashMap();
    }

}
