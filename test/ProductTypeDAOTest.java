import DAO.ProductDAO;
import org.junit.jupiter.api.Test;
import DAO.ProductTypeDAO;
import po.Product;
import po.ProductType;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ProductTypeDAOTest {

    @Test
    void insert() throws SQLException {
        assertNotNull(new ProductType(1,"测试类别"));
    }

    @Test
    void insertChildrenType() throws SQLException {
        assertTrue(new ProductTypeDAO().insertChildrenType("宠物","活体宠物"));
    }

    @Test
    void insertChildrenType1() throws SQLException {
        assertFalse(new ProductTypeDAO().insertChildrenType(845000,"错误类别"));
    }

    @Test
    void delete() throws SQLException {
        ProductType pt = new ProductType();
        pt.setProdutTypeId(2);
        assertTrue(new ProductTypeDAO().delete(pt));
        pt.setProdutTypeId(-21);
        assertFalse(new ProductTypeDAO().delete(pt));
    }

    @Test
    void update() throws SQLException {
        ProductType pt = new ProductType();
        assertTrue(new ProductTypeDAO().insertChildrenType("饰品","手表"));
        pt.setTypeName("手表");
        assertNotNull(pt = new ProductTypeDAO().get(pt));
        pt.setTypeName("翡翠手表");
        assertTrue(new ProductTypeDAO().update(pt));
    }

    @Test
    void get() throws SQLException {
        ProductType pt = new ProductType();
        pt.setProdutTypeId(-21);
        assertNull(new ProductTypeDAO().get(pt));
        pt.setProdutTypeId(0);
        pt.setTypeName("活体宠物");
        assertNotNull(new ProductTypeDAO().get(pt));
    }
}