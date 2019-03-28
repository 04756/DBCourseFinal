package po;

import DAO.DAOBase;
import DAO.DAOBaseOperate;

public class ProductClassify {

    private int productTypeid;
    private String pid;
    private String path;

    public ProductClassify(){}

    public ProductClassify(int productTypeid, String pid, String path) {
        this.productTypeid = productTypeid;
        this.pid = pid;
        this.path = path;
    }

    public int getProductTypeid() {
        return productTypeid;
    }

    public void setProductTypeid(int productTypeid) {
        this.productTypeid = productTypeid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
