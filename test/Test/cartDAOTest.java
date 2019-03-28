package Test;

import DAO.UserDAO;
import DAO.cartDAO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import po.User;
import po.cart;
import po.upPrimaryKey;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class cartDAOTest {

    cart c = new cart();
    cartDAO cdao = new cartDAO();

    @BeforeAll
    static void begin(){
        System.out.println("购物车DAO测试开始");
    }
    @org.junit.jupiter.api.Test
    void insert() {
        System.out.println("插入方法测试开始");
        UserDAO ud = new UserDAO();
        User u = new User();
        try {
            u = ud.getByPhone("12779");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        upPrimaryKey up = new upPrimaryKey();
        up.setUserid(u.getUserId());
        up.setProductid("15663bb6683e43cabcf476d1217cffe6");
        c.setKey(up);
        c.setProNum(5);
        try {
            assertTrue(cdao.insert(c));
            cdao.delete(c.getKey());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("插入方法测试结束");
    }


    @org.junit.jupiter.api.Test
    void delete() {
        System.out.println("删除方法测试开始");
        UserDAO ud = new UserDAO();
        User u = new User();
        try {
            u = ud.getByPhone("12779");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cart c = new cart();
        upPrimaryKey up = new upPrimaryKey();
        up.setUserid(u.getUserId());
        up.setProductid("45a90e48e6a745728c125e455db3a0d9");
        c.setKey(up);
        c.setProNum(3);
        try {
            cdao.insert(c);
            assertTrue(cdao.delete(c.getKey()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("删除方法测试结束");
    }

    @org.junit.jupiter.api.Test
    void update() {
        System.out.println("更新方法测试开始");
        UserDAO ud = new UserDAO();
        User u = new User();
        try {
            u = ud.getByPhone("12779");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cart c = new cart();
        upPrimaryKey up = new upPrimaryKey();
        up.setUserid(u.getUserId());
        up.setProductid("566eb2f132e54b58b3414b8216d710bd");
        c.setKey(up);
        c.setProNum(3);
        try {
            cdao.insert(c);
            c.setProNum(5);
            assertTrue(cdao.update(c));
            cdao.delete(c.getKey());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("更新方法测试结束");
    }

    @org.junit.jupiter.api.Test
    void get() {
        System.out.println("get方法测试开始");
        UserDAO ud = new UserDAO();
        User u = new User();
        try {
            u = ud.getByPhone("12779");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cart c = new cart();
        upPrimaryKey up = new upPrimaryKey();
        up.setUserid(u.getUserId());
        up.setProductid("6af2619fdc004114afa64774e1fa9904");
        c.setKey(up);
        c.setProNum(3);
        try {
            cdao.insert(c);
            assertNotNull(cdao.get(c.getKey()));
            cdao.delete(c.getKey());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("get方法测试结束");
    }

    @org.junit.jupiter.api.Test
    void search() {
        System.out.println("查询方法测试开始");
        UserDAO ud = new UserDAO();
        User u = new User();
        try {
            u = ud.getByPhone("12779");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cart c = new cart();
        upPrimaryKey up = new upPrimaryKey();
        up.setUserid(u.getUserId());
        up.setProductid("709e0581e562446497b8a5daadef6f03");
        c.setKey(up);
        c.setProNum(3);
        try {
            cdao.insert(c);
            assertNotNull(cdao.search(c.getKey().getUserid(),null,0));
            assertNotNull(cdao.search(c.getKey().getUserid(),c.getKey().getProductid(),0));
            assertNotNull(cdao.search(c.getKey().getUserid(),null,c.getProNum()));
            assertNotNull(cdao.search(null,c.getKey().getProductid(),c.getProNum()));
            assertNotNull(cdao.search(null,null,c.getProNum()));
            assertNotNull(cdao.search(c.getKey().getUserid(),c.getKey().getProductid(),c.getProNum()));
            cdao.delete(c.getKey());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("查询方法测试结束");
    }
    @AfterAll
    static void endwith(){
        System.out.println("购物车DAO测试结束");
    }
}