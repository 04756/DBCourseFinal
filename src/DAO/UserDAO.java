package DAO;

import po.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDAO extends DAOBase implements DAOBaseOperate<User>
{
    private static final String CREATE_USER_SQL = "INSERT INTO user (userid,password,phone,email) VALUES (?,?,?,?)";

    @Override
    public Boolean insert(User u) throws SQLException
    {
        Connection conn = super.getConn();
        PreparedStatement ps = conn.prepareStatement(CREATE_USER_SQL);
        if (u.getUserId() == null || u.getUserId().trim().equals(""))
            ps.setString(1, UUID.randomUUID().toString().replace("-", ""));
        else
            ps.setString(1, u.getUserId());
        ps.setString(2, u.getPassword());
        ps.setString(3, u.getPhone());
        ps.setString(4, u.getEmail());
        int count = ps.executeUpdate();
        System.out.println("insert user SQL lines executed: " + count);
        super.closeConn(conn, ps);
        return true;
    }

    private static final String DELETE_USER_SQL = "DELETE FROM user where userid=?";

    /**
     * 假定这里传来的User o中有userId
     * 因为要根据userId删记录
     *
     * @param u 要删除的用户
     * @return
     * @throws SQLException
     */
    @Override
    public Boolean delete(User u) throws SQLException
    {
        Connection conn = super.getConn();
        PreparedStatement ps = conn.prepareStatement(DELETE_USER_SQL);
        ps.setString(1, u.getUserId());
        int count = ps.executeUpdate();
        System.out.println("delete user SQL lines executed: " + count);
        super.closeConn(conn, ps);
        return true;
    }
    public boolean delete(String userId) throws SQLException
    {
        Connection conn = super.getConn();
        PreparedStatement ps = conn.prepareStatement(DELETE_USER_SQL);
        ps.setString(1,userId);
        int count = ps.executeUpdate();
        System.out.println("delete user SQL lines executed: " + count);
        super.closeConn(conn, ps);
        return true;
    }

    private static final String UPDATE_USER_SQL = "UPDATE user SET password=?, phone=?, email=? WHERE userid=?";

    /**
     * 假定传来的User o中的userId就是要修改的userId
     * o中其他属性会覆盖表中已存在的属性
     * 所以如果有属性不想更新 就要提前设置为原有的值
     *
     * @param u 更新后的数据
     * @return
     * @throws SQLException
     */
    @Override
    public Boolean update(User u) throws SQLException
    {
        Connection conn = super.getConn();
        PreparedStatement ps = conn.prepareStatement(UPDATE_USER_SQL);
        ps.setString(1, u.getPassword());
        ps.setString(2, u.getPhone());
        ps.setString(3, u.getEmail());
        ps.setString(4, u.getUserId());
        int count = ps.executeUpdate();
        System.out.println("update user SQL lines executed: " + count);
        super.closeConn(conn, ps);
        return true;
    }

    private static final String GET_USER_SQL = "SELECT * FROM user WHERE userid=?";

    /**
     * 仅需要User u中有userId就可以
     * @param o 获取记录的主键数据
     * @return
     * @throws SQLException
     */
    @Override
    public User get(User o) throws SQLException
    {
        Connection conn=super.getConn();
        PreparedStatement ps=conn.prepareStatement(GET_USER_SQL);
        ps.setString(1,o.getUserId());
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
            User u=new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
            super.closeConn(conn,ps,rs);
            return u;
        }
        System.out.println("no user with id="+o.getUserId()+" found");
        super.closeConn(conn,ps,rs);
        return null;
    }
    public User get(String userId) throws SQLException
    {
        Connection conn=super.getConn();
        PreparedStatement ps=conn.prepareStatement(GET_USER_SQL);
        ps.setString(1,userId);
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
            User u=new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
            super.closeConn(conn,ps,rs);
            return u;
        }
        System.out.println("no user with id="+userId+" found");
        super.closeConn(conn,ps,rs);
        return null;
    }

    public User getByPhone(String phone) throws SQLException
    {
        Connection conn=super.getConn();
        PreparedStatement ps=conn.prepareStatement("SELECT * FROM user WHERE phone=?");
        ps.setString(1,phone);
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
            User u=new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
            super.closeConn(conn,ps,rs);
            return u;
        }
        System.out.println("no user with phone="+phone+" found");
        super.closeConn(conn,ps,rs);
        return null;
    }

    public User getByEmail(String email) throws SQLException
    {
        Connection conn=super.getConn();
        PreparedStatement ps=conn.prepareStatement("SELECT * FROM user WHERE email=?");
        ps.setString(1,email);
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
            User u=new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
            super.closeConn(conn,ps,rs);
            return u;
        }
        System.out.println("no user with phone="+email+" found");
        super.closeConn(conn,ps,rs);
        return null;
    }

    /**
     * 根据userid模糊查找
     * @param partOfId
     * @return
     * @throws SQLException
     */
    public List<User> searchByUserId(String partOfId) throws SQLException
    {
        Connection conn=super.getConn();
        PreparedStatement ps=conn.prepareStatement("SELECT * FROM user WHERE userid LIKE ?");
        ps.setString(1,'%'+partOfId+'%');
        ResultSet rs=ps.executeQuery();
        ArrayList<User> uList=null;
        boolean first=true;
        while(rs.next())
        {
            if(first)
            {
                uList=new ArrayList<>();
                first=false;
            }
            uList.add(new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
        }
        super.closeConn(conn,ps,rs);
        return uList;
    }

    /**
     * 根据email模糊查找
     * @param partOfEmail
     * @return
     * @throws SQLException
     */
    public List<User> searchByEmail(String partOfEmail) throws SQLException
    {
        Connection conn=super.getConn();
        PreparedStatement ps=conn.prepareStatement("SELECT * FROM user WHERE email LIKE ?");
        ps.setString(1,'%'+partOfEmail+'%');
        ResultSet rs=ps.executeQuery();
        ArrayList<User> uList=null;
        boolean first=true;
        while(rs.next())
        {
            if(first)
            {
                uList=new ArrayList<>();
                first=false;
            }
            uList.add(new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
        }
        super.closeConn(conn,ps,rs);
        return uList;
    }
}
