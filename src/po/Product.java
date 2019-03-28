package po;

public class Product extends Object{

    private String pid;//产品id
    private float price = 0;//产品价格
    private String title;//产品名
    private String type;//产品种族标识（例如：水壶的500ml、250ml,不同价格的产品族）
    private String content;//产品介绍
    private String imagePath;
    private String mark;//(产品价格一样的产品标签，鞋子的36，37，38)
    private String storeid;//商铺id

    public Product(){}

    public Product(String pid, float price, String title, String content, String type, String imagePath, String storeid, String mark) {
        this.pid = pid;
        this.price = price;
        this.title = title;
        this.type = type;
        this.content = content;
        this.imagePath = imagePath;
        this.mark = mark;
        this.storeid = storeid;
    }

    public Product(float price, String title, String content, String imagePath, String mark, String soreid) {
        this.price = price;
        this.title = title;
        this.content = content;
        this.imagePath = imagePath;
        this.mark = mark;
        this.storeid = soreid;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

}
