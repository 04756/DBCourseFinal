import org.junit.jupiter.api.Test;
import po.Product;
import DAO.ProductDAO;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ProductDAOTest {

    @Test
    void insert() throws SQLException {
        Product pt = new Product(new Float(9999.9), "product测试,这里是一个手机产品", "这里是手机的介绍", "D:/", "黑色/红色", "6");
        assertTrue(new ProductDAO().insert(pt));
        pt.setPid("");
        pt.setStoreid("89");
        assertFalse(new ProductDAO().insert(pt));
    }

    @Test
    void delete() throws SQLException {
        Product pt = new Product(new Float(9999.9), "product测试,这里是一个手机产品", "这里是手机的介绍", "D:/", "黑色/红色", "6");
        assertTrue(new ProductDAO().insert(pt));
        assertTrue(new ProductDAO().delete(pt));
        pt.setPid("89898");
        assertFalse(new ProductDAO().delete(pt));
    }

    @Test
    void update() throws SQLException {
        Product pt = new Product(new Float(9999.9), "product测试,这里是一个手机产品", "这里是手机的介绍", "D:/", "黑色/红色", "6");

        assertTrue(new ProductDAO().insert(pt));

        pt.setImagePath("C:/");
        pt.setMark("华为/4.5寸屏幕");
        assertTrue(new ProductDAO().update(pt));

        pt.setStoreid("9999");
        assertFalse(new ProductDAO().update(pt));
    }

    @Test
    void get() throws SQLException {
        assertNotNull(new ProductDAO().get("45a90e48e6a745728c125e455db3a0d9"));
        assertNull(new ProductDAO().get("8888888888"));
    }

    @Test
    void findProductByType() throws SQLException {
        assertNotNull(new ProductDAO().findProductByType("华为"));
        assertNotNull(new ProductDAO().findProductByType("单反相机"));
        assertEquals(new ProductDAO().findProductByType("999").size(),0);
    }
}