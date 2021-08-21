import cn.huaiguang.dao.UserMapper;
import cn.huaiguang.domain.QueryVo;
import cn.huaiguang.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MybatisTest {
    private InputStream is = null;
    private SqlSessionFactory factory = null;
    private SqlSession sqlSession = null;
    private UserMapper userMapper = null;

    @Before
    public void init() throws IOException {
        // 读取SqlMapConfig配置文件
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 获取创建工厂对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        // 通过创建工厂对象创建工厂，传入配置文件流对象
        factory = builder.build(is);
        // 通过工厂创建SqlSession
        sqlSession = factory.openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @After
    public void destroy() throws IOException {
        sqlSession.commit(); // 提交事务
        is.close(); // 释放资源
        sqlSession.close(); // 释放资源
    }

    /**
     * 测试查询所有方法
     *
     * @throws Exception
     */
    @Test
    public void test01() throws Exception {
        // 读取SqlMapConfig配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 获取创建工厂对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        // 通过创建工厂对象创建工厂，传入配置文件流对象
        SqlSessionFactory factory = builder.build(is);
        // 通过工厂创建SqlSession
        SqlSession sqlSession = factory.openSession();
        // 通过动态代理的方式创建代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 调用实现类的方法
        List<User> userlist = userMapper.findAll();
        for (User user : userlist) {
            System.out.println(user);
        }
        // 释放资源
        sqlSession.close();
        is.close();
    }

    /**
     * 测试新增方法
     */
    @Test
    public void test02() throws IOException {
        User user = new User();  // 创建需要插入的对象
        user.setSex('男'); // 设置对象属性
        user.setAddress("中国大陆");
        user.setBirthday(new Date());
        user.setUsername("姜云升");
        System.out.println(user);
        userMapper.add(user);
        System.out.println(user);
    }

    /**
     * 测试更新用户方法
     */
    @Test
    public void test03() {
        User user = new User();
        user.setId(50);
        user.setUsername("杨幂");
        user.setBirthday(new Date());
        user.setAddress("上海");
        user.setSex('女');
        userMapper.updateUser(user);
    }


    /**
     * 测试删除用户方法
     */
    @Test
    public void test04() {
        User user = new User();
        user.setId(51);
        userMapper.deleteUser(user);
    }

    /**
     * 测试根据id删除用户
     */
    @Test
    public void test05() {
        userMapper.deleteUserById(45);
    }


    /**
     * 测试根据id查询用户
     */
    @Test
    public void test06() {
        User user = userMapper.findByid(50);
        System.out.println(user);
    }

    /**
     * 测试根据Name模糊查询
     */
    @Test
    public void test07() {
        List<User> users = userMapper.findByName("%姜云升%");
//        System.out.println(user);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 测试查询用户总条数
     */
    @Test
    public void test08() {
        int userTotle = userMapper.findUserTotle();
        System.out.println(userTotle);

    }

    /**
     * 根据queryVo模糊查询
     */
    @Test
    public void test09() {
        QueryVo queryVo = new QueryVo();
        User user = new User();
        user.setUsername("%姜%");
        queryVo.setUser(user);
        List<User> users = userMapper.findByQueryVo(queryVo);
        for (User user1 : users) {
            System.out.println(user1);
        }
    }

    /**
     * 根据传入条件动态查询
     */
    @Test
    public void test10() {
        User user = new User();
        user.setSex('女');
        user.setUsername("%杨%");
        List<User> users = userMapper.findByCondition(user);
        for (User user1 : users) {
            System.out.println(user1);
        }
    }

    /**
     * 根据根据id集合查询
     */
    @Test
    public void test11() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(50);
        list.add(51);
        list.add(52);
        List<User> userList = userMapper.findByIdList(list);
        for (User user : userList) {
            System.out.println(user);
        }
    }
    /**
     * 一对多演示
     */
    @Test
    public void testfindAllUserAccount(){
        List<User> userAccount = userMapper.findAllUserAccount();
        for (User user : userAccount) {
            System.out.println(user);
        }
    }
    /**
     * 多对多演示
     */
    @Test
    public void testfinadAllUserRole(){
        List<User> users = userMapper.findAllRoleOfUser(50);
        for (User user : users) {
            System.out.println(user);
        }
    }
}