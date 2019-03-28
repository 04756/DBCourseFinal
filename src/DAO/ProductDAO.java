package DAO;

import com.mysql.cj.Query;
import po.Product;
import po.ProductClassify;
import po.ProductType;
import po.TypeTree;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductDAO extends DAOBase implements DAOBaseOperate <Product> {

    @Override
    public Boolean insert(Product o) throws SQLException {
        Connection conn = super.getConn();
        PreparedStatement pst = null;
        int flag = 0;

        try {
            String sql = "insert into product (pid,price,title,content, type, imagePath, mark, storeid) values(?,?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            if (o.getPid() == null)
                o.setPid(UUID.randomUUID().toString().replace("-", ""));

            pst.setString(1, o.getPid());
            pst.setString(2, o.getPrice() + "");
            pst.setString(3, o.getTitle());
            pst.setString(4, o.getContent());
            pst.setString(5, o.getType());
            pst.setString(6, o.getImagePath());
            pst.setString(7, o.getMark());
            pst.setString(8, o.getStoreid());

            flag = pst.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

        super.closeConn(conn,pst);

        if(flag <= 0)
            return false;
        else
            return true;
    }

    @Override
    public Boolean delete(Product o) throws SQLException {
        return deleteProduct(o.getPid());
    }

    private Boolean deleteProduct(String pid) throws SQLException{

        Connection conn = null;
        int flag = 0;
        PreparedStatement pst = null;

        try {
            conn = super.getConn();

            if (pid == null)
                return false;

            String sql = "delete from product where pid=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, pid);

            flag = pst.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            super.closeConn(conn,pst);

            if(flag <= 0)
                return false;
            else
                return true;
        }

    }

   @Override
    public Boolean update(Product o) throws SQLException {
        Connection conn = super.getConn();
        PreparedStatement pst = null;
        int flag = 0;

        try {
            String sql = "update product set ";
            if (o.getTitle() != null)
                sql += "title = '" + o.getTitle() + "', ";
            if (o.getContent() != null)
                sql += "content = '" + o.getContent() + "', ";
            if (o.getPrice() != 0)
                sql += "price = '" + o.getPrice() + "', ";
            if (o.getMark() != null)
                sql += "mark = '" + o.getMark() + "', ";
            if (o.getImagePath() != null)
                sql += "imagePath = '" + o.getImagePath() + "', ";
            if (o.getType() != null)
                sql += "type = '" + o.getType() + "', ";
            if (o.getStoreid() != null)
                sql += "storeid = '" + o.getStoreid() + "', ";
            sql = sql.substring(0, sql.length() - 2);
            sql += " where pid = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, o.getPid());

            flag = pst.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            super.closeConn(conn,pst);
            if(flag <= 0)
                return false;
            else
                return true;
        }
    }

    @Override
    public Product get(Product o) throws SQLException {
        return get(o.getPid());
    }

    public Product get(String pid) throws SQLException{
        Connection conn = super.getConn();
        PreparedStatement pst = null;
        ResultSet rset = null;
        Product pro = null;

        try {
            String sql = "select * from product where pid = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, pid);
            rset = pst.executeQuery();

            if(rset.next())
            pro = new Product(rset.getString(1),Float.parseFloat(rset.getString(2)),rset.getString(3),rset.getString(4),rset.getString(5),rset.getString(6),rset.getString(7),rset.getString(8));

        }catch (Exception e){
            e.printStackTrace();
        }

        super.closeConn(conn, pst, rset);

        return pro;
    }

   public List<Product> findProductByType(String Type) throws SQLException{
        //先通过productType获取到类别id
        //构建类别树，知晓类别下分范围
        //获取类别下分范围的所有产品
       TypeTree tt = new TypeTree();
       tt.createTree(tt,4,16,"");
       List<Product> lpo = new ArrayList<Product>();
       ProductTypeDAO ptdao = new ProductTypeDAO();
       ProductType pt = new ProductType();
       pt.setTypeName(Type);
       pt = ptdao.get(pt);//获取类别id

       if(pt == null){
           List<ProductClassify> list = new ProductClassifyDAO().findProductByPath(Type);
           for(ProductClassify i : list){
               lpo.add(new ProductDAO().get(i.getPid()));
           }
       }
       else {
           TypeTree treeNode = tt.getRange(pt.getProdutTypeId());
           if (treeNode != null) {
               List<ProductClassify> list = new ArrayList<ProductClassify>();
               if (treeNode.getRangenex() == 0)
                   list = new ProductClassifyDAO().findProductByType(treeNode.getTypeIndex());
               else
                   list = new ProductClassifyDAO().findProductInTypeRange(treeNode);
               for (ProductClassify i : list) {
                   lpo.add(new ProductDAO().get(i.getPid()));
               }
           }
       }
        return lpo;
    }
}
