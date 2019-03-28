package DAO;

import po.Comment;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CommentDAO extends DAOBase implements DAOBaseOperate<Comment>
{
    private static final String INSERT_COMMENT_SQL="INSERT INTO comment (commentid,userid,pid,content,score) VALUES (?,?,?,?,?)";

    @Override
    public Boolean insert(Comment c) throws SQLException
    {
        Connection conn=super.getConn();
        PreparedStatement ps=conn.prepareStatement(INSERT_COMMENT_SQL);
        ps.setString(1, UUID.randomUUID().toString().replace("-",""));
        ps.setString(2,c.getUserId());
        ps.setString(3,c.getpId());
        ps.setString(4,c.getContent());
        ps.setString(5,c.getScore().toString());
        int count=ps.executeUpdate();
        System.out.println("insert comment SQL executed: "+count);
        super.closeConn(conn,ps);
        return true;
    }

    private static final String DELETE_COMMENT_SQL="DELETE FROM comment WHERE commentid=?";

    /**
     * 根据Comment c中的commentId删
     * Comment c中必须有commentId
     * @param c
     * @return
     * @throws SQLException
     */
    @Override
    public Boolean delete(Comment c) throws SQLException
    {
        Connection conn=super.getConn();
        PreparedStatement ps=conn.prepareStatement(DELETE_COMMENT_SQL);
        ps.setString(1,c.getCommentId());
        int count=ps.executeUpdate();
        System.out.println("delete comment SQL lines executed: "+count);
        super.closeConn(conn,ps);
        return true;
    }

    public Boolean deleteById(String commentId) throws SQLException
    {
        Connection conn=super.getConn();
        PreparedStatement ps=conn.prepareStatement("DELETE FROM comment WHERE  commentid=?");
        ps.setString(1,commentId);
        int count=ps.executeUpdate();
        System.out.println("delete comment SQL lines executed: "+count);
        super.closeConn(conn,ps);
        return true;
    }

    public Boolean deleteByUser(String userId) throws SQLException
    {
        Connection conn=super.getConn();
        PreparedStatement ps=conn.prepareStatement("DELETE FROM comment where userid=?");
        ps.setString(1,userId);
        int count=ps.executeUpdate();
        System.out.println("delete comment SQL lines executed: "+count);
        super.closeConn(conn,ps);
        return true;
    }

    private static final String UPDATE_COMMENT_SQL="UPDATE comment SET  userid=?, pid=?, content=?, score=? WHERE commentid=?";

    /**
     * 更新c中commentId标识的留言记录
     * @param c
     * @return
     * @throws SQLException
     */
    @Override
    public Boolean update(Comment c) throws SQLException
    {
        Connection conn=super.getConn();
        PreparedStatement ps=conn.prepareStatement(UPDATE_COMMENT_SQL);
        ps.setString(1,c.getUserId());
        ps.setString(2,c.getpId());
        ps.setString(3,c.getContent());
        ps.setString(4,c.getScore().toString());
        ps.setString(5,c.getCommentId());
        int count=ps.executeUpdate();
        System.out.println("update comment SQL lines executed: "+count);
        super.closeConn(conn,ps);
        return true;
    }

    private static final String GET_COMMENT_SQL="SELECT * FROM comment WHERE commentid=?";

    /**
     * 根据c中的commentId获取一条评论记录
     * @param c
     * @return
     * @throws SQLException
     */
    @Override
    public Comment get(Comment c) throws SQLException
    {
        Connection conn=super.getConn();
        PreparedStatement ps=conn.prepareStatement(GET_COMMENT_SQL);
        ps.setString(1,c.getCommentId());
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
            Comment nc=new Comment(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5).charAt(0));
            super.closeConn(conn,ps,rs);
            return nc;
        }
        super.closeConn(conn,ps,rs);
        return null;
    }

    public Comment get(String commentId) throws SQLException
    {
        Connection conn=super.getConn();
        PreparedStatement ps=conn.prepareStatement("SELECT * FROM comment WHERE commentid=?");
        ps.setString(1,commentId);
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
            Comment nc=new Comment(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5).charAt(0));
            super.closeConn(conn,ps,rs);
            return nc;
        }
        super.closeConn(conn,ps,rs);
        return null;
    }

    public List<Comment> searchByUserId(String userId) throws SQLException
    {
        Connection conn=super.getConn();
        PreparedStatement ps=conn.prepareStatement("SELECT * FROM comment WHERE userid=?");
        ps.setString(1,userId);
        ResultSet rs=ps.executeQuery();
        List<Comment> cList=null;
        boolean first=true;
        while(rs.next())
        {
            if(first)
            {
                cList=new ArrayList<>();
                first=false;
            }
            cList.add(new Comment(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5).charAt(0)));
        }
        super.closeConn(conn,ps,rs);
        return cList;
    }

    public List<Comment> searchBypId(String pId) throws SQLException
    {
        Connection conn=super.getConn();
        PreparedStatement ps= conn.prepareStatement("SELECT * FROM comment WHERE pid=?");
        ps.setString(1,pId);
        ResultSet rs=ps.executeQuery();
        List<Comment> cList=new ArrayList<>();
        boolean first=true;
        while(rs.next())
        {
            if(first)
            {
                cList=new ArrayList<>();
                first=false;
            }
            cList.add(new Comment(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5).charAt(0)));
        }
        super.closeConn(conn,ps,rs);
        return cList;
    }

    /**
     * 根据内容模糊查找
     * @param partOfContent
     * @return
     * @throws SQLException
     */
    public List<Comment> searchByContent(String partOfContent) throws SQLException
    {
        Connection conn=super.getConn();
        PreparedStatement ps=conn.prepareStatement("SELECT * FROM comment WHERE content LIKE ?");
        ps.setString(1,'%'+partOfContent+'%');
        ResultSet rs=ps.executeQuery();
        List<Comment> cList=new ArrayList<>();
        boolean first=true;
        while(rs.next())
        {
            if(first)
            {
                cList=new ArrayList<>();
                first=false;
            }
            cList.add(new Comment(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5).charAt(0)));
        }
        super.closeConn(conn,ps,rs);
        return cList;
    }

}