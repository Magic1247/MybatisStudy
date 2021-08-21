package cn.huaiguang.dao;

import cn.huaiguang.domain.QueryVo;
import cn.huaiguang.domain.User;

import java.util.List;

/**
 * @author HuaiGuang
 * 用户的持久层接口
 */
public interface UserMapper {
    /**
     * 查询所有用户
     *
     * @return 返回用户集合
     */
    List<User> findAll();

    /**
     * 新增用户
     */
    void add(User user);

    /**
     * 更新用户
     */
    void updateUser(User user);

    /**
     * 根据id删除用户
     */
    void deleteUserById(Integer id);

    /**
     * 对象删除用户
     */
    void deleteUser(User user);

    /**
     * 根据id查询用户
     */
    User findByid(Integer id);

    /**
     * 模糊查询
     */
    List<User> findByName(String name);

    /**
     * 查询用户总数
     */
    int findUserTotle();

    /**
     * 根据queryVo查询用户
     */

    List<User> findByQueryVo(QueryVo queryVo);

    /**
     * 动态条件查询
     */
    List<User> findByCondition(User user);

    /**
     * 根据id集合查询
     */
    List<User> findByIdList(List<Integer> list);

    /**
     * 查询所有用户的账户信息 一对多演示
     */

    List<User> findAllUserAccount();

    /**
     * 根据id查询用户下的所有角色
     */
    List<User> findAllRoleOfUser(Integer id);


}
