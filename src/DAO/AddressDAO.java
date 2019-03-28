package DAO;

import po.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AddressDAO extends DAOBase implements DAOBaseOperate<Address>
{
    private static final String CREATE_ADDRESS_SQL="INSERT INTO address (addressid,userid,address,receiver,tel) VALUES (?,?,?,?,?)";

    @Override
    public Boolean insert(Address a) throws SQLException
    {
        Connection conn=super.getConn();
        PreparedStatement ps=conn.prepareStatement(CREATE_ADDRESS_SQL);
        ps.setString(1, UUID.randomUUID().toString().replace("-",""));
        ps.setString(2,a.getUserId());
        ps.setString(3,a.getAddress());
        ps.setString(4,a.getReceiver());
        ps.setString(5,a.getTel());
        int count=ps.executeUpdate();
        super.closeConn(conn,ps);
        System.out.println("insert address SQL lines executed: "+count);
        return true;
    }

    private static final String DELETE_ADDRESS_SQL="DELETE FROM ADDRESS WHERE addressid=?";

    /**
     * 根据Addreass a中的addressId删除
     * 所以a里必须有addressId
     * @param a 删除的数据
     * @return
     * @throws SQLException
     */
    @Override
    public Boolean delete(Address a) throws SQLException
    {
        Connection conn=super.getConn();
        PreparedStatement ps=conn.prepareStatement(DELETE_ADDRESS_SQL);
        ps.setString(1,a.getAddressId());
        int count=ps.executeUpdate();
        System.out.println("delete address SQL lines executed: "+count);
        super.closeConn(conn,ps);
        return true;
    }
    public Boolean delete(String addressId) throws SQLException
    {
        Connection conn=super.getConn();
        PreparedStatement ps=conn.prepareStatement(DELETE_ADDRESS_SQL);
        ps.setString(1,addressId);
        int count=ps.executeUpdate();
        System.out.println("delete address SQL lines executed: "+count);
        super.closeConn(conn,ps);
        return true;
    }

    private static final String UPDATE_ADDRESS_SQL="UPDATE address SET userid=?, address=?, receiver=?, tel=? WHERE addressid=?";

    /**
     * 用Address a中的内容更新addressid=a.addressId那条记录的所有内容
     * 所以如果有不想更新的属性 要设置为旧值
     * @param a 更新后的数据
     * @return
     */
    @Override
    public Boolean update(Address a) throws SQLException
    {
        Connection conn=super.getConn();
        PreparedStatement ps=conn.prepareStatement(UPDATE_ADDRESS_SQL);
        ps.setString(1,a.getUserId());
        ps.setString(2,a.getAddress());
        ps.setString(3,a.getReceiver());
        ps.setString(4,a.getTel());
        ps.setString(5,a.getAddressId());
        int count=ps.executeUpdate();
        super.closeConn(conn,ps);
        System.out.println("update address SQL lines executed: "+count);
        return true;
    }

    private static final String GET_ADDRESS_SQL="SELECT * FROM address WHERE addressid=?";

    @Override
    public Address get(Address a) throws SQLException
    {
        Connection conn=super.getConn();
        PreparedStatement ps=conn.prepareStatement(GET_ADDRESS_SQL);
        ps.setString(1,a.getAddressId());
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
            Address na=new Address(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
            super.closeConn(conn,ps,rs);
            return na;
        }
        super.closeConn(conn,ps,rs);
        return null;
    }
    public Address get(String addressId) throws SQLException
    {
        Connection conn=super.getConn();
        PreparedStatement ps=conn.prepareStatement(GET_ADDRESS_SQL);
        ps.setString(1,addressId);
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
            Address na=new Address(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
            super.closeConn(conn,ps,rs);
            return na;
        }
        super.closeConn(conn,ps,rs);
        return null;
    }

    /**
     * 根据地址模糊搜索
     * @param partOfAddress
     * @return
     * @throws SQLException
     */
    public List<Address> searchByAddress(String partOfAddress) throws SQLException
    {
        Connection conn=super.getConn();
        PreparedStatement ps=conn.prepareStatement("SELECT * FROM address WHERE address like ?");
        ps.setString(1,'%'+partOfAddress+'%');
        ResultSet rs=ps.executeQuery();
        List<Address> aList=null;
        boolean first=true;
        while(rs.next())
        {
            if(first)
            {
                aList=new ArrayList<>();
                first=false;
            }
            aList.add(new Address(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
        }
        super.closeConn(conn,ps,rs);
        return aList;
    }

    /**
     * 根据接收人姓名模糊查找
     * @param receiver
     * @return
     * @throws SQLException
     */
    public List<Address> searchByReceiver(String receiver) throws SQLException
    {
        Connection conn=super.getConn();
        PreparedStatement ps=conn.prepareStatement("SELECT * FROM address WHERE receiver like ?");
        ps.setString(1,'%'+receiver+'%');
        ResultSet rs=ps.executeQuery();
        List<Address> aList=new ArrayList<>();
        boolean first=true;
        while(rs.next())
        {
            if(first)
            {
                aList=new ArrayList<>();
                first=false;
            }
            aList.add(new Address(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
        }
        super.closeConn(conn,ps,rs);
        return aList;
    }

    /**
     * 根据电话模糊查找
     * @param tel
     * @return
     * @throws SQLException
     */
    public List<Address> searchByTel(String tel) throws SQLException
    {
        Connection conn=super.getConn();
        PreparedStatement ps=conn.prepareStatement("SELECT * FROM address WHERE tel like ?");
        ps.setString(1,'%'+tel+'%');
        ResultSet rs=ps.executeQuery();
        List<Address> aList=new ArrayList<>();
        boolean first=true;
        while(rs.next())
        {
            if(first)
            {
                aList=new ArrayList<>();
                first=false;
            }
            aList.add(new Address(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
        }
        super.closeConn(conn,ps,rs);
        return aList;
    }

    /**
     * 根据userId精确查找
     * @param userId
     * @return
     * @throws SQLException
     */
    public List<Address> searchByUserId(String userId) throws SQLException
    {
        Connection conn=super.getConn();
        PreparedStatement ps=conn.prepareStatement("SELECT * FROM address WHERE userid=?");
        ps.setString(1,userId);
        ResultSet rs=ps.executeQuery();
        List<Address> aList=new ArrayList<>();
        boolean first=true;
        while(rs.next())
        {
            if(first)
            {
                aList=new ArrayList<>();
                first=false;
            }
            aList.add(new Address(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
        }
        super.closeConn(conn,ps,rs);
        return aList;
    }
}
