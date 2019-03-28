package DAO;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.*;

public class DAOBase implements DAO
{
    private static ComboPooledDataSource ds=null;

    static
    {
        try
        {
            ds=new ComboPooledDataSource("MySQL");
        }
        catch(Exception e)
        {
            System.out.println("error getting data source");
            e.printStackTrace();
        }
    }

    public Connection getConn() throws SQLException
    {
            return ds.getConnection();
    }

    public void closeConn(Connection conn, Statement stmt, PreparedStatement ps, ResultSet rs)
    {
        try
        {
            if(rs!=null)
                rs.close();
            if(ps!=null)
                ps.close();
            if(stmt!=null)
                stmt.close();
            conn.close();
        }
        catch(SQLException sqle)
        {
            System.out.println("failed to close connection");
        }
    }

    public void closeConn(Connection conn,Statement stmt)
    {
        closeConn(conn,stmt,null,null);
    }

    public void closeConn(Connection conn,Statement stmt,ResultSet rs)
    {
        closeConn(conn,stmt,null,rs);
    }

    public void closeConn(Connection conn,PreparedStatement ps)
    {
        closeConn(conn,null,ps,null);
    }

    public void closeConn(Connection conn,PreparedStatement ps,ResultSet rs)
    {
        closeConn(conn,null,ps,rs);
    }
}
