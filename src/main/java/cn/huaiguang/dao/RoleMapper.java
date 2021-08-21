package cn.huaiguang.dao;

import cn.huaiguang.domain.Role;

import java.util.List;

public interface RoleMapper {
    List<Role> findAllUserOfRole();
}
