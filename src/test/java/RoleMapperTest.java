import cn.huaiguang.dao.RoleMapper;
import cn.huaiguang.domain.Role;
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

public class RoleMapperTest {
    private InputStream is = null;
    private SqlSessionFactory factory = null;
    private SqlSession sqlSession = null;
    private RoleMapper roleMapper = null;

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
        roleMapper = sqlSession.getMapper(RoleMapper.class);
    }

    @After
    public void destroy() throws IOException {
        sqlSession.commit(); // 提交事务
        is.close(); // 释放资源
        sqlSession.close(); // 释放资源
    }

    @Test
    public void testfindAllUserOfRole() {
        List<Role> allUserOfRole = roleMapper.findAllUserOfRole();
        for (Role role : allUserOfRole) {
            System.out.println(role);
        }
    }
}
