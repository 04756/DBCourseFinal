import DAO.UserInfoDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import po.UserInfo;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UserInfoDAOTest
{

    @BeforeEach
    void setUp()
    {
        System.out.println("开始测试UserInfoDAO");
    }

    @AfterEach
    void tearDown()
    {
        System.out.println("UserInfoDAO测试结束");
    }

    @Test
    @DisplayName("Boolean insert(UserInfo)")
    void insert()
    {
        UserInfo ui=new UserInfo("testUserInfo","testUserInfo","userInfoPortrait",'M',new Timestamp(new Date().getTime()),"110");
        try
        {
            assertTrue(new UserInfoDAO().insert(ui));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @DisplayName("Boolean delete(UserInfo)")
    void delete()
    {
        UserInfo ui=new UserInfo("testDelete");
        try
        {
            assertTrue(new UserInfoDAO().delete(ui));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail();
        }

    }

    @Test
    @DisplayName("Boolean delete(String userId)")
    void deleteByuserId()
    {
        try
        {
            assertTrue(new UserInfoDAO().delete("deleteById"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @DisplayName("Boolean update(UserInfo)")
    void update()
    {
        UserInfo ui=new UserInfo("testUpdate","update","update.jpg",'F',new Timestamp(new Date().getTime()),"220");
        try
        {
            assertTrue(new UserInfoDAO().update(ui));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @DisplayName("UserInfo get(UserInfo)")
    void get()
    {
        try
        {
            assertNotNull(new UserInfoDAO().get(new UserInfo("zzzzd")),"没有获取到zzzzd详细信息");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @DisplayName("List<UserInfo> searchByUserId(String)")
    void searchByUserId()
    {
        try
        {
            assertNotNull(new UserInfoDAO().searchByUserId("bell"),"没有获取到用户名中包含bell的任何用户详细信息");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @DisplayName("List<UserInfo> searchByUserNick(String)")
    void searchByUserNick()
    {
        try
        {
            assertNotNull(new UserInfoDAO().searchByUserNick("秋"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void searchBySex()
    {
        try
        {
            assertNotNull(new UserInfoDAO().searchBySex('M'));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail();
        }
    }
}