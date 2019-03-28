package DAO;


import java.sql.*;

public interface DAO
{
    Connection getConn() throws SQLException;
    void closeConn(Connection conn, Statement stmt, PreparedStatement ps, ResultSet rs);
}
