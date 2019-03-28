import DAO.AddressDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import po.Address;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AddressDAOTest
{

    @BeforeEach
    void setUp()
    {
        System.out.println("开始测试AddressDAO");
    }

    @AfterEach
    void tearDown()
    {
        System.out.println("AddressDAO测试结束");
    }

    @Test
    @DisplayName("Boolean insert(Address)")
    void insert()
    {
        Address a=new Address("testAddressInsert","address","王","1938292893");
        try
        {
            assertTrue(new AddressDAO().insert(a));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @DisplayName("Boolean delete(Address)")
    void delete()
    {
        try
        {
            assertTrue(new AddressDAO().delete(new Address("testAddressDelete")));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @DisplayName("Boolean delete(String addressId)")
    void deleteByString()
    {
        try
        {
            assertTrue(new AddressDAO().delete("testAddressDeleteById"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @DisplayName("Boolean update(Address)")
    void update()
    {
        try
        {
            assertTrue(new AddressDAO().update(new Address("testAddressUpdate","testAddressUpdate","testUpdate","address","4817947573")));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @DisplayName("Address get(Address)")
    void get()
    {
        try
        {
            assertNotNull(new AddressDAO().get(new Address("e09b5b937a0e467197ccc86920835b1e")));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void getByAddressId()
    {
        try
        {
            assertNotNull(new AddressDAO().get("e09b5b937a0e467197ccc86920835b1e"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @DisplayName("Address searchByAddress(String)")
    void searchByAddress()
    {
        try
        {
            assertNotNull(new AddressDAO().searchByAddress("市"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @DisplayName("List<Address> searchByReceiver(String)")
    void searchByReceiver()
    {
        try
        {
            assertNotNull(new AddressDAO().searchByReceiver("许骏霖"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @DisplayName("List<Address> searchByTel(String)")
    void searchByTel()
    {
        try
        {
            assertNotNull(new AddressDAO().searchByTel("111"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @DisplayName("List<Address> searchByUserId(String)")
    void searchByUserId()
    {
        try
        {
            assertNotNull(new AddressDAO().searchByUserId("290adb9c84484f2cb6b763d9ef86befb"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail();
        }
    }
}