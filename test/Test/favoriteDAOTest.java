package Test;

import DAO.UserDAO;
import DAO.favoriteDAO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import po.User;
import po.favorite;
import po.upPrimaryKey;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class favoriteDAOTest {

    favoriteDAO fdao = new favoriteDAO();

    @BeforeAll
    static void begin(){
        System.out.println("收藏夹DAO测试开始");
    }

    @Test
    void get() {
        System.out.println("get方法测试开始");
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
        favorite f = new favorite();
        f.setKey(up);
        try {
            fdao.insert(f);
            assertNotNull(fdao.get(f.getKey()));
            fdao.delete(f.getKey());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("get方法测试结束");
    }

    @Test
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
        favorite f = new favorite();
        f.setKey(up);
        try {
            assertTrue(fdao.insert(f));
            fdao.delete(f.getKey());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("插入方法测试结束");
    }

    @Test
    void delete() {
        System.out.println("删除方法测试开始");
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
        favorite f = new favorite();
        f.setKey(up);
        try {
            fdao.insert(f);
            assertTrue(fdao.delete(f.getKey()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("删除方法测试结束");
    }


    @Test
    void search() {
        System.out.println("查询方法测试开始");
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
        favorite f = new favorite();
        f.setKey(up);
        try {
            fdao.insert(f);
            assertNotNull(fdao.search(f.getKey().getUserid(),null));
            assertNotNull(fdao.search(f.getKey().getUserid(),f.getKey().getProductid()));
            assertNotNull(fdao.search(null,f.getKey().getProductid()));
            fdao.delete(f.getKey());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("查询方法测试结束");
    }

    @AfterAll
    static void endwith(){
        System.out.println("收藏夹DAO测试结束");
    }
}