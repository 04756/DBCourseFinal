package DAO;

import po.cart;
import po.upPrimaryKey;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class cartDAO extends DAOBase implements DAOBaseOperate<cart>{

    public Boolean delete(cart o) throws SQLException{ return delete(o.getKey());}
    public cart get(cart o) throws SQLException{ return get(o.getKey());}

    public Boolean insert(cart uwp) throws SQLException{
        Boolean flag = false;
        Connection conn = null;
        Statement st = null;
        try {
            conn = super.getConn();
            st = conn.createStatement();
            String sql = "insert into cart values("+
                    "\'"+uwp.getKey().getUserid()+"\',"+
                    "\'"+uwp.getKey().getProductid()+"\',"+
                    "\'"+uwp.getProNum()+"\'"+
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

    public Boolean delete(upPrimaryKey up) throws SQLException{
        Boolean flag = false;
        Connection conn = null;
        Statement st = null;
        try {
            conn= super.getConn();
            st = conn.createStatement();
            String sql = "delete from cart "+
                    "where userid=\'"+up.getUserid()+"\' and pid=\'"+up.getProductid()+"\'";
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

    public Boolean update(cart uwp) throws SQLException{
        Boolean flag = false;
        Connection conn = null;
        Statement st = null;
        try {
            conn = super.getConn();
            st = conn.createStatement();
            String sql = "update cart set "+
                    "proNum=\'"+uwp.getProNum()+
                    "\' where userid=\'"+uwp.getKey().getUserid()+"\' and pid=\'"+uwp.getKey().getProductid()+"\'";
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

    public cart get(upPrimaryKey up) throws SQLException{
        cart umpt = new cart();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn= super.getConn();
            st = conn.createStatement();
            String sql = "select * from cart where userid=\'"
                    +up.getUserid()+"\' and pid=\'"
                    +up.getProductid()+"\'";
            rs = st.executeQuery(sql);
            while(rs.next()) {
                umpt.setKey(up);
                umpt.setProNum(rs.getInt("proNum"));
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
    public List<cart> search(String uid, String pid, int pnum) throws SQLException{
        if(uid==null&&pid==null&&pnum<=0){
            return null;
        }
        List<cart> list = new ArrayList<>();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn= super.getConn();
            st = conn.createStatement();
            String sql = "select * from cart where ";
            String and = " and ";
            if(uid!=null){
                sql = sql + "userid=\'"+uid+"\'"+and;
            }
            if(pid!=null){
                sql = sql + "pid=\'"+pid+"\'"+and;
            }
            if(pnum>0){
                sql = sql + "proNum=\'"+pnum+"\'"+and;
            }
            sql = sql.substring(0,sql.length()-and.length());
            System.out.println(sql);
            rs = st.executeQuery(sql);
            while(rs.next()) {
                cart umpt = new cart();
                upPrimaryKey up = new upPrimaryKey();
                up.setUserid(rs.getString("userid"));
                up.setProductid(rs.getString("pid"));
                umpt.setKey(up);
                umpt.setProNum(rs.getInt("proNum"));
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
