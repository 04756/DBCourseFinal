package Test;

import DAO.UserDAO;
import DAO.ordertableDAO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import po.User;
import po.favorite;
import po.ordertable;
import po.upPrimaryKey;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ordertableDAOTest {

    ordertableDAO odao = new ordertableDAO();

    @BeforeAll
    static void begin(){
        System.out.println("订单DAO测试开始");
    }

    @Test
    void insert() {
        System.out.println("插入方法测试开始");
        ordertable o = new ordertable();
        o.setEstablishtime();
        o.setAddressid("18974cfe0889424fb9b315d3370b4a68");
        try {
            assertTrue(odao.insert(o));
            odao.delete(o.getOrderid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("插入方法测试结束");
    }

    @Test
    void delete() {
        System.out.println("删除方法测试开始");
        ordertable o = new ordertable();
        o.setEstablishtime();
        o.setAddressid("18974cfe0889424fb9b315d3370b4a68");
        try {
            odao.insert(o);
            assertTrue(odao.delete(o.getOrderid()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("删除方法测试结束");
    }

    @Test
    void update() {
        System.out.println("更新方法测试开始");
        ordertable o = new ordertable();
        o.setEstablishtime();
        o.setAddressid("18974cfe0889424fb9b315d3370b4a68");
        try {
            odao.insert(o);
            assertTrue(odao.update(o.getOrderid(),o.getTime(),null));
            assertTrue(odao.update(o.getOrderid(),o.getTime(),"e37b582c9c5c467a9ad3c9923bf87dbb"));
            assertTrue(odao.update(o.getOrderid(),null,"e37b582c9c5c467a9ad3c9923bf87dbd"));
            odao.delete(o.getOrderid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("更新方法测试结束");
    }

    @Test
    void get() {
        System.out.println("get方法测试开始");
        ordertable o = new ordertable();
        o.setEstablishtime();
        o.setAddressid("18974cfe0889424fb9b315d3370b4a68");
        try {
            odao.insert(o);
            assertNotNull(odao.get(o.getOrderid()));
            odao.delete(o.getOrderid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("get方法测试结束");
    }

    @Test
    void search() {
        System.out.println("搜索方法测试开始");
        ordertable o = new ordertable();
        o.setEstablishtime();
        o.setAddressid("18974cfe0889424fb9b315d3370b4a68");
        try {
            odao.insert(o);
            assertNotNull(odao.search(o.getOrderid(),null,null));
            assertNotNull(odao.search(null,o.getEstablishtime(),null));
            assertNotNull(odao.search(null,null,o.getAddressid()));
            assertNotNull(odao.search(o.getOrderid(),o.getEstablishtime(),null));
            assertNotNull(odao.search(o.getOrderid(),null,o.getAddressid()));
            assertNotNull(odao.search(null,o.getEstablishtime(),o.getAddressid()));
            assertNotNull(odao.search(o.getOrderid(),o.getEstablishtime(),o.getAddressid()));
            odao.delete(o.getOrderid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("搜索方法测试结束");
    }

    @AfterAll
    static void endwith(){
        System.out.println("订单DAO测试结束");
    }
}