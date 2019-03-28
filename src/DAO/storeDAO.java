package DAO;

import po.store;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class storeDAO extends DAOBase implements DAOBaseOperate<store> {
    public Boolean delete(store o) throws SQLException{ return delete(o.getStoreid());}
    public Boolean update(store o) throws SQLException{ return update(o.getStoreid(),o.getOwnerinfo(),o.getAddress());}
    public store get(store o) throws SQLException{ return get(o.getStoreid());}

    public Boolean insert(store s) throws SQLException{
        Boolean flag = false;
        Connection conn = null;
        Statement st = null;
        if(s.getStoreid()==null||s.getStoreid().equals(""))
            s.setStoreid(UUID.randomUUID().toString().replace("-",""));
        try {
            conn = super.getConn();
            st = conn.createStatement();
            String sql = "insert into store values("+
                    "\'"+s.getStoreid()+"\',"+
                    "\'"+s.getOwnerinfo()+"\',"+
                    "\'"+s.getAddress()+"\'"+
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

    public Boolean delete(String sid) throws SQLException{
        Boolean flag = false;
        Connection conn = null;
        Statement st = null;
        try {
            conn= super.getConn();
            st = conn.createStatement();
            String sql = "delete from store "+
                    "where storeid=\'"+sid+"\'";
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

    public Boolean update(String sid,String info,String ad) throws SQLException{
        if(sid == null||(info==null&&ad==null)) return false;
        Boolean flag = false;
        Connection conn = null;
        Statement st = null;
        try {
            conn = super.getConn();
            st = conn.createStatement();
            String sql = "update store set ";
            String dot = " , ";
            if(info!=null)
                sql = sql + "ownerinfo=\'"+info+"\'"+dot;
            if(ad!=null)
                sql = sql + "address=\'"+ad+"\'"+dot;
            sql = sql.substring(0,sql.length()-dot.length());
            String where = " where storeid=\'"+sid+"\'";
            sql = sql + where;
            System.out.println(sql);
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

    public store get(String sid) throws SQLException{
        store s = new store();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn= super.getConn();
            st = conn.createStatement();
            String sql = "select * from store where storeid=\'" +sid+"\'";
            rs = st.executeQuery(sql);
            while(rs.next()) {
                s.setStoreid(sid);
                s.setAddress(rs.getString("address"));
                s.setOwnerinfo(rs.getString("ownerinfo"));
            }

        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            super.closeConn(conn,st,rs);
            return  s;
        }
    }

    public List<store> search(String sid, String info, String ad) throws SQLException{
        if(sid == null&&(info==null&&ad==null)) return null;
        List<store> list = new ArrayList<store>();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn= super.getConn();
            st = conn.createStatement();
            String sql = "select * from store where ";
            String and = " and ";
            if(sid!=null)
                sql = sql + "storeid=\'"+sid+"\'"+and;
            if(info!=null)
                sql = sql + "ownerinfo=\'"+info+"\'"+and;
            if(ad!=null)
                sql = sql + "address=\'"+ad+"\'"+and;
            sql = sql.substring(0,sql.length()-and.length());
            System.out.println(sql);
            rs = st.executeQuery(sql);
            while(rs.next()) {
                store s =new store();
                s.setAddress(rs.getString("address"));
                s.setOwnerinfo(rs.getString("ownerinfo"));
                s.setStoreid(rs.getString("storeid"));
                list.add(s);
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
