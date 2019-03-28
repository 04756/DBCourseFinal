import DAO.CommentDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import po.Comment;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CommentDAOTest
{

    @BeforeEach
    void setUp()
    {
        System.out.println("开始测试CommentDAO");
    }

    @AfterEach
    void tearDown()
    {
        System.out.println("CommentDAO测试结束");
    }

    @Test
    @DisplayName("Boolean insert(Comment)")
    void insert()
    {
        try
        {
            assertTrue(new CommentDAO().insert(new Comment("testCommentInsert","45a90e48e6a745728c125e455db3a0d9","comment",'4')));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @DisplayName("Boolean delete(Comment)")
    void delete()
    {
        try
        {
            assertTrue(new CommentDAO().delete(new Comment("testCommentDelete")));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @DisplayName("Boolean deleteById(String commentId)")
    void deleteById()
    {
        try
        {
            assertTrue(new CommentDAO().deleteById("testCommentDeleteById"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @DisplayName("Boolean deleteByUser(String userId")
    void deleteByUser()
    {
        try
        {
            assertTrue(new CommentDAO().deleteByUser("testCommentDeleteByUser"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @DisplayName("Boolean update(Comment)")
    void update()
    {
        try
        {
            assertTrue(new CommentDAO().update(new Comment("testCommentUpdate","testCommentUpdate","566eb2f132e54b58b3414b8216d710bd","content update",'3')));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @DisplayName("Comment get(Comment)")
    void get()
    {
        try
        {
            assertNotNull(new CommentDAO().get(new Comment("6170c7571ec7447792ffdae39b0c2939")));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @DisplayName("Comment get(String commentId)")
    void getByCommentId()
    {
        try
        {
            assertNotNull(new CommentDAO().get("6170c7571ec7447792ffdae39b0c2939"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @DisplayName("List<Comment> searchByUserId(String userId)")
    void searchByUserId()
    {
        try
        {
            assertNotNull(new CommentDAO().searchByUserId("290adb9c84484f2cb6b763d9ef86befb"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @DisplayName("List<Comment> searchBypId(String pId)")
    void searchBypId()
    {
        try
        {
            assertNotNull(new CommentDAO().searchBypId("740a27a43a17421a942d2fff6ea3ea79"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @DisplayName("List<Comment> searchByContent(String partOfContent)")
    void searchByContent()
    {
        try
        {
            assertNotNull(new CommentDAO().searchByContent("comment"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail();
        }
    }
}