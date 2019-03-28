package Test;

import DAO.ordertableDAO;
import DAO.storeDAO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import po.opPrimaryKey;
import po.ordertable;
import po.ordertable_product;
import po.store;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class storeDAOTest {

    storeDAO sdao = new storeDAO();

    @BeforeAll
    static void begin(){
        System.out.println("商铺DAO测试开始");
    }

    @Test
    void insert() {
        System.out.println("插入方法测试开始");
        store s = new store();
        s.setOwnerinfo("lyq");
        s.setAddress("fengtai");
        try {
            assertTrue(sdao.insert(s));
            sdao.delete(s.getStoreid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("插入方法测试结束");
    }

    @Test
    void delete() {
        System.out.println("删除方法测试开始");
        store s = new store();
        s.setOwnerinfo("lyq");
        s.setAddress("fengtai");
        try {
            sdao.insert(s);
            assertTrue(sdao.delete(s.getStoreid()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("删除方法测试结束");
    }

    @Test
    void update() {
        System.out.println("更新方法测试开始");
        store s = new store();
        s.setOwnerinfo("lyq");
        s.setAddress("fengtai");
        try {
            sdao.insert(s);
            assertTrue(sdao.update(s.getStoreid(),null,"haidian"));
            assertTrue(sdao.update(s.getStoreid(),"zxq","hd"));
            assertTrue(sdao.update(s.getStoreid(),"lyq",null));
            sdao.delete(s.getStoreid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("更新方法测试结束");
    }

    @Test
    void get() {
        System.out.println("get方法测试开始");
        store s = new store();
        s.setOwnerinfo("lyq");
        s.setAddress("fengtai");
        try {
            sdao.insert(s);
            assertNotNull(sdao.get(s.getStoreid()));
            sdao.delete(s.getStoreid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("get方法测试结束");
    }

    @Test
    void search() {
        System.out.println("查询方法测试开始");
        store s = new store();
        s.setOwnerinfo("lyq");
        s.setAddress("fengtai");
        try {
            sdao.insert(s);
            assertNotNull(sdao.search(s.getStoreid(),null,null));
            assertNotNull(sdao.search(s.getStoreid(),s.getOwnerinfo(),null));
            assertNotNull(sdao.search(s.getStoreid(),null,s.getAddress()));
            assertNotNull(sdao.search(s.getStoreid(),s.getOwnerinfo(),s.getAddress()));
            assertNotNull(sdao.search(null,s.getOwnerinfo(),null));
            assertNotNull(sdao.search(null,null,s.getAddress()));
            assertNotNull(sdao.search(null,s.getOwnerinfo(),s.getAddress()));
            sdao.delete(s.getStoreid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("查询方法测试结束");
    }

    @AfterAll
    static void endwith(){
        System.out.println("商铺DAO测试结束");
    }
}