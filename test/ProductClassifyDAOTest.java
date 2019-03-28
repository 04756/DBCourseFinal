import DAO.ProductDAO;
import org.junit.jupiter.api.Test;
import DAO.ProductClassifyDAO;
import org.junit.runners.Suite;
import po.ProductClassify;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@Suite.SuiteClasses(ProductDAO.class)
class ProductClassifyDAOTest {

    @Test
    void insert() throws SQLException {
        ProductClassify pc = new ProductClassify(1,"45a90e48e6a745728c125e455db3a0d9","");
        assertTrue(new ProductClassifyDAO().insert(pc));
        pc.setPid("666666");
        assertFalse(new ProductClassifyDAO().insert(pc));
    }

    @Test
    void delete() throws SQLException {
        ProductClassify pc = new ProductClassify(1,"45a90e48e6a745728c125e455db3a0d9","");
        assertTrue(new ProductClassifyDAO().delete(pc));
        assertFalse(new ProductClassifyDAO().delete(pc));
    }

    @Test
    void update() throws SQLException {
        ProductClassify pc = new ProductClassify(1,"45a90e48e6a745728c125e455db3a0d9","");
        new ProductClassifyDAO().insert(pc);
        pc.setPath("测试细分类别");
        assertTrue(new ProductClassifyDAO().update(pc));
        pc.setPid("56465");
        assertFalse(new ProductClassifyDAO().update(pc));
    }

    @Test
    void get() throws SQLException {
        ProductClassify pc = new ProductClassify();

        //只有pid参数
        pc.setPid("cfd6cb18223a461799ef4b3e8ba347d4");
        assertNotNull(new ProductClassifyDAO().get(pc));

        //pid参数以及typeId参数
        pc.setProductTypeid(4368);
        assertNotNull(new ProductClassifyDAO().get(pc));

        //只有typeId参数
        pc.setPid(null);
        assertNotNull(new ProductClassifyDAO().get(pc));

        //错误typeId
        pc.setProductTypeid(-21);
        assertNull(new ProductClassifyDAO().get(pc));
    }

    @Test
    void findProductByPath() throws SQLException {
        assertNotNull(new ProductClassifyDAO().findProductByPath("华为"));
        assertEquals(new ProductClassifyDAO().findProductByPath("123").size(), 0);
    }
}