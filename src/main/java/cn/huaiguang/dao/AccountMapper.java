package cn.huaiguang.dao;

import cn.huaiguang.domain.AccountUser;

import java.util.List;

public interface AccountMapper {
    List<AccountUser> findAll();
}
