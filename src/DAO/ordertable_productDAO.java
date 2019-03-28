package DAO;

import po.opPrimaryKey;
import po.ordertable_product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ordertable_productDAO extends DAOBase implements DAOBaseOperate<ordertable_product> {
    public Boolean delete(ordertable_product o) throws SQLException{ return delete(o.getKey());}
    public ordertable_product get(ordertable_product o) throws SQLException{ return get(o.getKey());}

    public Boolean insert(ordertable_product op) throws SQLException{
        Boolean flag = false;
        Connection conn = null;
        Statement st = null;
        try {
            conn = super.getConn();
            st = conn.createStatement();
            String sql = "insert into ordertable_product values("+
                    "\'"+op.getKey().getOrderid()+"\',"+
                    "\'"+op.getKey().getPid()+"\',"+
                    "\'"+op.getProNum()+"\'"+
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

    public Boolean delete(opPrimaryKey opk) throws SQLException{
        Boolean flag = false;
        Connection conn = null;
        Statement st = null;
        try {
            conn= super.getConn();
            st = conn.createStatement();
            String sql = "delete from ordertable_product "+
                    "where orderid=\'"+opk.getOrderid()+"\' and pid=\'"+opk.getPid()+"\'";
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

    public Boolean update(ordertable_product op) throws SQLException{
        Boolean flag = false;
        Connection conn = null;
        Statement st = null;
        try {
            conn = super.getConn();
            st = conn.createStatement();
            String sql = "update ordertable_product set "+
                    "pnum=\'"+op.getProNum()+"\'"+
                    " where orderid=\'"+op.getKey().getOrderid()+"\' and pid=\'"+op.getKey().getPid()+"\'";
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

    public ordertable_product get(opPrimaryKey opk) throws SQLException{
        ordertable_product umpt = new ordertable_product();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn= super.getConn();
            st = conn.createStatement();
            String sql = "select * from ordertable_product where orderid=\'"
                    +opk.getOrderid()+"\' and pid=\'"
                    +opk.getPid()+"\'";
            rs = st.executeQuery(sql);
            while(rs.next()) {
                umpt.setKey(opk);
                umpt.setProNum(rs.getInt("pnum"));
            }

        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            super.closeConn(conn,st,rs);
            return  umpt;
        }
    }

    /*
     * UID和pid没有填null
     * pnum没有填-1
     */
    public List<ordertable_product> search(String oid, String pid, int pnum) throws SQLException{
        if(oid==null&&pid==null&&pnum<=0){
            return null;
        }
        List<ordertable_product> list = new ArrayList<ordertable_product>();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn= super.getConn();
            st = conn.createStatement();
            String sql = "select * from ordertable_product where ";
            String and = " and ";
            if(oid!=null){
                sql = sql + "orderid=\'"+oid+"\'"+and;
            }
            if(pid!=null){
                sql = sql + "pid=\'"+pid+"\'"+and;
            }
            if(pnum>0){
                sql = sql + "pnum=\'"+pnum+"\'"+and;
            }
            sql = sql.substring(0,sql.length()-and.length());
            System.out.println(sql);
            rs = st.executeQuery(sql);
            while(rs.next()) {
                ordertable_product umpt = new ordertable_product();
                opPrimaryKey up = new opPrimaryKey();
                up.setOrderid(rs.getString("orderid"));
                up.setPid(rs.getString("pid"));
                umpt.setKey(up);
                umpt.setProNum(rs.getInt("pnum"));
                list.add(umpt);
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
