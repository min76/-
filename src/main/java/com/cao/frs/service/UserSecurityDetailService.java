package com.cao.frs.service;

import cn.hutool.core.bean.BeanUtil;
import com.cao.frs.dao.UserMapper;
import org.springframework.security.core.userdetails.User;
import com.cao.frs.repos.UserService;
import com.cao.frs.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserSecurityDetailService implements UserDetailsService, UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userMapper.findByName(username);
        System.out.println(user);
        // 获得角色
        String role = String.valueOf(user.getIsAdmin());
        // 角色集合
        List<GrantedAuthority> authorities = new ArrayList<>();
        // 角色必须以`ROLE_`开头，数据库中没有，则在这里加
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        // 数据库密码是明文, 需要加密进行比对
        return new User(user.getUsername(), passwordEncoder.encode(user.getPassword()),authorities);
    }


    @Override
    public int add(Users user) {
        return userMapper.add(user);
    }

    @Override
    public int remove(int id) {
        return userMapper.remove(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(Users users) {
        return userMapper.update(BeanUtil.beanToMap(users));
    }

    @Override
    public List<Users> findByKeywords(String keyword) {
        return userMapper.findByKeyword(keyword);
    }

    @Override
    public Users findByName(String username) {
        return userMapper.findByName(username);
    }

    @Override
    public Users findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public Users findByNickName(String nickname) {
        return userMapper.findByNickName(nickname);
    }
}
