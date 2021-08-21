import cn.huaiguang.dao.AccountMapper;
import cn.huaiguang.domain.AccountUser;
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
import java.util.List;

public class AccountUserMapperTest {
    private InputStream is = null;
    private SqlSessionFactory factory = null;
    private SqlSession sqlSession = null;
    private AccountMapper accountMapper = null;

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
        accountMapper = sqlSession.getMapper(AccountMapper.class);
    }

    @After
    public void destroy() throws IOException {
        sqlSession.commit(); // 提交事务
        is.close(); // 释放资源
        sqlSession.close(); // 释放资源
    }

    @Test
    public void testFindAll() {
        List<AccountUser> accountUsers = accountMapper.findAll();
        for (AccountUser accountUser : accountUsers) {
            System.out.println(accountUser);
        }
    }

    @Test
    public void test02() {
        User user1 = new User();
        user1.setUsername("test");
        AccountUser accountUser = new AccountUser();
        accountUser.setUser(user1);
        User user = accountUser.getUser();
        System.out.println(user);

    }
}
