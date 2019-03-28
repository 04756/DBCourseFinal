package DAO;

import po.UserInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserInfoDAO extends DAOBase implements DAOBaseOperate<UserInfo>
{
    private static final String CREATE_USERINFO_SQL="INSERT INTO userinfo (userid,nickname,portrait,sex,birthday,IDNum) VALUES (?,?,?,?,?,?)";

    @Override
    public Boolean insert(UserInfo ui) throws SQLException
    {
        Connection conn=super.getConn();
        PreparedStatement ps=conn.prepareStatement(CREATE_USERINFO_SQL);
        ps.setString(1,ui.getUserId());
        ps.setString(2,ui.getNickName());
        ps.setString(3,ui.getProtrait());
        ps.setString(4,ui.getSex().toString());
        ps.setTimestamp(5,ui.getBirthday());
        ps.setString(6,ui.getIDNum());
        int count=ps.executeUpdate();
        System.out.println("insert userinfo SQL lines executed: "+count);
        super.closeConn(conn,ps);
        return true;
    }

    private static final String DELETE_USERINFO_SQL="DELETE FROM userinfo WHERE userid=?";

    /**
     * 根据userId删除详细信息
     * UserInfo ui中必须要有userId
     * @param ui
     * @return
     * @throws SQLException
     */
    @Override
    public Boolean delete(UserInfo ui) throws SQLException
    {
        Connection conn=super.getConn();
        PreparedStatement ps=conn.prepareStatement(DELETE_USERINFO_SQL);
        ps.setString(1,ui.getUserId());
        int count=ps.executeUpdate();
        System.out.println("delete userinfo SQL lines executed: "+count);
        super.closeConn(conn,ps);
        return true;
    }
    public Boolean delete(String userId) throws SQLException
    {
        Connection conn=super.getConn();
        PreparedStatement ps=conn.prepareStatement(DELETE_USERINFO_SQL);
        ps.setString(1,userId);
        int count=ps.executeUpdate();
        System.out.println("delete userinfo SQL lines executed: "+count);
        super.closeConn(conn,ps);
        return true;
    }

    private static final String UPDATE_USERINFO_SQL="UPDATE userinfo SET nickname=?, portrait=?, sex=?, birthday=?, IDNum=? WHERE userid=?";

    @Override
    public Boolean update(UserInfo ui) throws SQLException
    {
        Connection conn=super.getConn();
        PreparedStatement ps=conn.prepareStatement(UPDATE_USERINFO_SQL);
        ps.setString(1,ui.getNickName());
        ps.setString(2,ui.getProtrait());
        ps.setString(3,ui.getSex().toString());
        ps.setTimestamp(4,ui.getBirthday());
        ps.setString(5,ui.getIDNum());
        ps.setString(6,ui.getUserId());
        int count=ps.executeUpdate();
        System.out.println("update userinfo SQL lines executed: "+count);
        return true;
    }

    private static final String GET_USERINFO_SQL="SELECT * FROM userinfo WHERE userid=?";

    @Override
    public UserInfo get(UserInfo ui) throws SQLException
    {
        Connection conn=super.getConn();
        PreparedStatement ps=conn.prepareStatement(GET_USERINFO_SQL);
        ps.setString(1,ui.getUserId());
        ResultSet rs =ps.executeQuery();
        if(rs.next())
        {
            UserInfo nui=new UserInfo(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4).charAt(0),rs.getTimestamp(5),rs.getString(6));
            super.closeConn(conn,ps,rs);
            return nui;
        }
        return null;
    }

    /**
     * 根据userid模糊查找
     * @param partOfId
     * @return
     * @throws SQLException
     */
    public List<UserInfo> searchByUserId(String partOfId) throws SQLException
    {
        Connection conn=super.getConn();
        PreparedStatement ps=conn.prepareStatement("SELECT * FROM userinfo WHERE userid LIKE ?");
        ps.setString(1,'%'+partOfId+'%');
        ResultSet rs=ps.executeQuery();
        ArrayList<UserInfo> uiList=null;
        boolean first=true;
        while(rs.next())
        {
            if(first)
            {
                uiList=new ArrayList<>();
                first=false;
            }
            uiList.add(new UserInfo(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4).charAt(0),rs.getTimestamp(5),rs.getString(6)));
        }
        super.closeConn(conn,ps,rs);
        return uiList;
    }

    public List<UserInfo> searchByUserNick(String partOfNick) throws SQLException
    {
        Connection conn=super.getConn();
        PreparedStatement ps=conn.prepareStatement("SELECT * FROM userinfo WHERE nickname LIKE ?");
        ps.setString(1,'%'+partOfNick+'%');
        ResultSet rs=ps.executeQuery();
        ArrayList<UserInfo> uiList=null;
        boolean first=true;
        while(rs.next())
        {
            if(first)
            {
                uiList=new ArrayList<>();
                first=false;
            }
            uiList.add(new UserInfo(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4).charAt(0),rs.getTimestamp(5),rs.getString(6)));
        }
        super.closeConn(conn,ps,rs);
        return uiList;
    }

    public List<UserInfo> searchBySex(Character s) throws SQLException
    {
        Connection conn=super.getConn();
        PreparedStatement ps=conn.prepareStatement("SELECT * FROM userinfo WHERE sex=? OR sex=?");
        ps.setString(1,s.toString());
        if(Character.isUpperCase(s))
            ps.setString(2,s.toString().toLowerCase());
        else
            ps.setString(2,s.toString().toUpperCase());
        ArrayList<UserInfo> uiList=null;
        ResultSet rs=ps.executeQuery();
        boolean first=true;
        while(rs.next())
        {
            if(first)
            {
                uiList=new ArrayList<>();
                first=false;
            }
            uiList.add(new UserInfo(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4).charAt(0),rs.getTimestamp(5),rs.getString(6)));
        }
        super.closeConn(conn,ps,rs);
        return uiList;
    }
}
