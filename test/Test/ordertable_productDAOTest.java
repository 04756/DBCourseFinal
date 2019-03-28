package Test;

import DAO.ordertableDAO;
import DAO.ordertable_productDAO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import po.opPrimaryKey;
import po.ordertable;
import po.ordertable_product;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ordertable_productDAOTest {

    ordertable_productDAO opdao = new ordertable_productDAO();

    @BeforeAll
    static void begin(){
        System.out.println("订单-商品DAO测试开始");
    }

    @Test
    void insert() {
        System.out.println("插入方法测试开始");
        ordertableDAO odao = new ordertableDAO();
        ordertable o = new ordertable();
        o.setEstablishtime();
        o.setAddressid("18974cfe0889424fb9b315d3370b4a68");
        ordertable_product op = new ordertable_product();
        opPrimaryKey opk = new opPrimaryKey();
        try {
            odao.insert(o);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        opk.setOrderid(o.getOrderid());
        opk.setPid("6af2619fdc004114afa64774e1fa9904");
        op.setProNum(5);
        op.setKey(opk);
        try {
            assertTrue(opdao.insert(op));
            opdao.delete(op.getKey());
            odao.delete(o.getOrderid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("插入方法测试结束");
    }

    @Test
    void delete() {
        System.out.println("删除方法测试开始");
        ordertableDAO odao = new ordertableDAO();
        ordertable o = new ordertable();
        o.setEstablishtime();
        o.setAddressid("18974cfe0889424fb9b315d3370b4a68");
        ordertable_product op = new ordertable_product();
        opPrimaryKey opk = new opPrimaryKey();
        try {
            odao.insert(o);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        opk.setOrderid(o.getOrderid());
        opk.setPid("6af2619fdc004114afa64774e1fa9904");
        op.setProNum(5);
        op.setKey(opk);
        try {
            opdao.insert(op);
            assertTrue(opdao.delete(op.getKey()));
            odao.delete(o.getOrderid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("删除方法测试结束");
    }

    @Test
    void update() {
        System.out.println("更新方法测试开始");
        ordertableDAO odao = new ordertableDAO();
        ordertable o = new ordertable();
        o.setEstablishtime();
        o.setAddressid("18974cfe0889424fb9b315d3370b4a68");
        ordertable_product op = new ordertable_product();
        opPrimaryKey opk = new opPrimaryKey();
        try {
            odao.insert(o);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        opk.setOrderid(o.getOrderid());
        opk.setPid("6af2619fdc004114afa64774e1fa9904");
        op.setProNum(5);
        op.setKey(opk);
        try {
            opdao.insert(op);
            op.setProNum(3);
            assertTrue(opdao.update(op));
            opdao.delete(op.getKey());
            odao.delete(o.getOrderid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("更新方法测试结束");
    }

    @Test
    void get() {
        System.out.println("get方法测试开始");
        ordertableDAO odao = new ordertableDAO();
        ordertable o = new ordertable();
        o.setEstablishtime();
        o.setAddressid("18974cfe0889424fb9b315d3370b4a68");
        ordertable_product op = new ordertable_product();
        opPrimaryKey opk = new opPrimaryKey();
        try {
            odao.insert(o);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        opk.setOrderid(o.getOrderid());
        opk.setPid("6af2619fdc004114afa64774e1fa9904");
        op.setProNum(5);
        op.setKey(opk);
        try {
            opdao.insert(op);
            assertNotNull(opdao.get(op.getKey()));
            opdao.delete(op.getKey());
            odao.delete(o.getOrderid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("get方法测试结束");
    }

    @Test
    void search() {
        System.out.println("查询方法测试开始");
        ordertableDAO odao = new ordertableDAO();
        ordertable o = new ordertable();
        o.setEstablishtime();
        o.setAddressid("18974cfe0889424fb9b315d3370b4a68");
        ordertable_product op = new ordertable_product();
        opPrimaryKey opk = new opPrimaryKey();
        try {
            odao.insert(o);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        opk.setOrderid(o.getOrderid());
        opk.setPid("6af2619fdc004114afa64774e1fa9904");
        op.setProNum(5);
        op.setKey(opk);
        try {
            opdao.insert(op);
            assertNotNull(opdao.search(op.getKey().getOrderid(),null,0));
            assertNotNull(opdao.search(null,null,op.getProNum()));
            assertNotNull(opdao.search(null,op.getKey().getPid(),0));
            assertNotNull(opdao.search(null,op.getKey().getPid(),op.getProNum()));
            assertNotNull(opdao.search(op.getKey().getOrderid(),null,op.getProNum()));
            assertNotNull(opdao.search(op.getKey().getOrderid(),op.getKey().getPid(),0));
            assertNotNull(opdao.search(op.getKey().getOrderid(),op.getKey().getPid(),op.getProNum()));
            opdao.delete(op.getKey());
            odao.delete(o.getOrderid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("查询方法测试结束");
    }

    @AfterAll
    static void endwith(){
        System.out.println("订单-商品DAO测试结束");
    }
}