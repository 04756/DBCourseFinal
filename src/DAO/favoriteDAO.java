package DAO;

import po.upPrimaryKey;
import po.favorite;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//没有update函数
public class favoriteDAO extends DAOBase implements DAOBaseOperate<favorite> {
    public Boolean delete(favorite o) throws SQLException{ return delete(o.getKey());}
    @Deprecated
    public Boolean update(favorite o) throws SQLException{ return false;}
    public favorite get(favorite o) throws SQLException{ return get(o.getKey());}

    public Boolean insert(favorite ufp) throws SQLException{
        Boolean flag = false;
        Connection conn = null;
        Statement st = null;
        try {
            conn = super.getConn();
            st = conn.createStatement();
            String sql = "insert into favorite values("+
                    "\'"+ufp.getKey().getUserid()+"\',"+
                    "\'"+ufp.getKey().getProductid()+"\'"+
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
            String sql = "delete from favorite "+
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

    public favorite get(upPrimaryKey up) throws SQLException{
        favorite umpt = new favorite();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn= super.getConn();
            st = conn.createStatement();
            String sql = "select * from favorite where userid=\'"
                    +up.getUserid()+"\' and pid=\'"
                    +up.getProductid()+"\'";
            rs = st.executeQuery(sql);
            while(rs.next()) {
                umpt.setKey(up);
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
    public List<favorite> search(String uid, String pid) throws SQLException{
        if(uid==null&&pid==null){
            return null;
        }
        List<favorite> list = new ArrayList<favorite>();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn= super.getConn();
            st = conn.createStatement();
            String sql = "select * from favorite where ";
            String and = " and ";
            if(uid!=null){
                sql = sql + "userid=\'"+uid+"\'"+and;
            }
            if(pid!=null){
                sql = sql + "pid=\'"+pid+"\'"+and;
            }
            sql = sql.substring(0,sql.length()-and.length());
            System.out.println(sql);
            rs = st.executeQuery(sql);
            while(rs.next()) {
                favorite umpt = new favorite();
                upPrimaryKey up = new upPrimaryKey();
                up.setUserid(rs.getString("userid"));
                up.setProductid(rs.getString("pid"));
                umpt.setKey(up);
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
