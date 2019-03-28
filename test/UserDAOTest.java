import DAO.UserDAO;
import org.junit.jupiter.api.*;
import po.User;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UserDAO测试")
class UserDAOTest
{
    @BeforeAll
    static void displayTestName()
    {
        System.out.println("现在开始测试UserDAO");
    }

    @Test
    @DisplayName("Boolean insert(User)")
    void insert()
    {
        User u=new User("testInsert","123","1111111111","testInsert");
        try
        {
            assertTrue(new UserDAO().insert(u));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail("failed to insert user");
        }
    }

    @Test
    @DisplayName("Boolean delete(User)")
    void deleteUser()
    {
        User u=new User("testDelete");
        try
        {
            assertTrue(new UserDAO().delete(u));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail("failed to delete user");
        }
    }

    @Test
    @DisplayName("Boolean delete(String userId)")
    void deleteUserById()
    {
        try
        {
            assertTrue(new UserDAO().delete("deleteById"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @DisplayName("Boolean update(User)")
    void update()
    {
        try
        {
            assertTrue(new UserDAO().update(new User("testUpdate","123","9999999999","testInsert")));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @DisplayName("User get(User)")
    void get()
    {
        try
        {
            assertNotNull(new UserDAO().get(new User("testExist","123","123","213")));
            User u=new UserDAO().get(new User("testExist","123","123","213"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @DisplayName("User get(String userId")
    void getByUserId()
    {
        try
        {
            assertNotNull(new UserDAO().get("testGetById"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @DisplayName("User getByPhone(String phone)")
    void getByPhone()
    {
        try
        {
            assertNotNull(new UserDAO().getByPhone("13264306681"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @DisplayName("User getByEmail(String email")
    void getByEmail()
    {
        try
        {
            assertNotNull(new UserDAO().getByEmail("garage@qq.com"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @DisplayName("List<User> searchByUserId(String)")
    void searchByUserId()
    {
        try
        {
            assertNotNull(new UserDAO().searchByUserId("e"),"根据用户名模糊查找无结果");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @DisplayName("List<User> searchByEmail(String)")
    void searchByEmail()
    {
        try
        {
            assertNotNull(new UserDAO().searchByEmail("com"),"根据邮箱模糊查找无结果");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail();
        }
    }

    @AfterAll
    static void displayTestEnd()
    {
        System.out.println("UserDAO测试结束");
    }
}