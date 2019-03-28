package DAO;

import po.ordertable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ordertableDAO extends DAOBase implements DAOBaseOperate<ordertable> {
    public Boolean delete(ordertable o) throws SQLException{ return delete(o.getOrderid());}
    public Boolean update(ordertable o) throws SQLException{ return update(o.getOrderid(),o.getEstablishtime(),o.getAddressid());}
    public ordertable get(ordertable o) throws SQLException{ return get(o.getOrderid());}

    public Boolean insert(ordertable o) throws SQLException{
        Boolean flag = false;
        Connection conn = null;
        Statement st = null;
        if(o.getOrderid()==null||o.getOrderid().equals(""))
            o.setOrderid(UUID.randomUUID().toString().replace("-",""));
        try {
            conn = super.getConn();
            st = conn.createStatement();
            String sql = "insert into ordertable values("+
                    "\'"+o.getOrderid()+"\',"+
                    "\'"+o.getEstablishtime()+"\',"+
                    "\'"+o.getAddressid()+"\'"+
                    ")";
            st.executeUpdate(sql);
            flag = true;
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            super.closeConn(conn,st);
            return flag;
        }
    }

    public Boolean delete(String oid) throws SQLException{
        Boolean flag = false;
        Connection conn = null;
        Statement st = null;
        try {
            conn= super.getConn();
            st = conn.createStatement();
            String sql = "delete from ordertable "+
                    "where orderid=\'"+oid+"\'";
            st.executeUpdate(sql);
            System.out.println("删除执行成功");
            flag = true;
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            super.closeConn(conn,st);
            return flag;
        }
    }

    public Boolean update(String oid,String etime,String aid) throws SQLException{
        if(oid == null||(etime==null&&aid==null)) return false;
        Boolean flag = false;
        Connection conn = null;
        Statement st = null;
        try {
            conn = super.getConn();
            st = conn.createStatement();
            String sql = "update ordertable set ";
            String dot = " , ";
            if(etime!=null)
                sql = sql + "establishtime=\'"+etime+"\'"+dot;
            if(aid!=null)
                sql = sql + "addressid=\'"+aid+"\'"+dot;
            sql = sql.substring(0,sql.length()-dot.length());
            String where = " where orderid=\'"+oid+"\'";
            sql = sql + where;
            st.executeUpdate(sql);
            System.out.println("更新执行成功");
            flag = true;
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            super.closeConn(conn,st);
            return flag;
        }
    }

    public ordertable get(String oid) throws SQLException{
        ordertable o = new ordertable();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn= super.getConn();
            st = conn.createStatement();
            String sql = "select * from ordertable where orderid=\'" +oid+"\'";
            rs = st.executeQuery(sql);
            while(rs.next()) {
                o.setOrderid(oid);
                o.setAddressid(rs.getString("addressid"));
                o.setEstablishtime(rs.getString("establishtime"));
            }

        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            super.closeConn(conn,st,rs);
            return  o;
        }
    }

    /*
     * UID和pid没有填null
     * pnum没有填-1
     */
    public List<ordertable> search(String oid,String etime,String aid) throws SQLException{
        if(oid == null&&(etime==null&&aid==null)) return null;
        List<ordertable> list = new ArrayList<ordertable>();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn= super.getConn();
            st = conn.createStatement();
            String sql = "select * from ordertable where ";
            String and = " and ";
            if(oid!=null)
                sql = sql + "orderid=\'"+oid+"\'"+and;
            if(etime!=null)
                sql = sql + "establishtime=\'"+etime+"\'"+and;
            if(aid!=null)
                sql = sql + "addressid=\'"+aid+"\'"+and;
            sql = sql.substring(0,sql.length()-and.length());
            System.out.println(sql);
            rs = st.executeQuery(sql);
            while(rs.next()) {
                ordertable o =new ordertable();
                o.setAddressid(rs.getString("addressid"));
                o.setEstablishtime(rs.getString("establishtime"));
                o.setOrderid(rs.getString("orderid"));
                list.add(o);
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            super.closeConn(conn,st,rs);
            if(list.size()>0) return  list;
            else return null;
        }
    }
}
