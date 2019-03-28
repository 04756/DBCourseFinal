package po;

import java.util.Calendar;
import java.util.UUID;

public class ordertable {

    private String orderid;
    private String addressid;
    private String establishtime;

    public void setOrderid(String oid) {
        this.orderid = oid;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setAddressid(String addressid) {
        this.addressid = addressid;
    }

    public String getAddressid() {
        return addressid;
    }

    public  void setEstablishtime(String e){
        this.establishtime = e;
    }

    public void setEstablishtime() {
        Calendar cl = Calendar.getInstance();
        int year = cl.get(Calendar.YEAR);
        int month = cl.get(Calendar.MONTH)+1;
        int day = cl.get(Calendar.DAY_OF_MONTH);
        int hour = cl.get(Calendar.HOUR);
        int minute = cl.get(Calendar.MINUTE);
        int second = cl.get(Calendar.SECOND);
        establishtime =  year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
    }

    public String getEstablishtime() {
        return establishtime;
    }

    public String getTime(){
        Calendar cl = Calendar.getInstance();
        int year = cl.get(Calendar.YEAR);
        int month = cl.get(Calendar.MONTH)+1;
        int day = cl.get(Calendar.DAY_OF_MONTH);
        int hour = cl.get(Calendar.HOUR);
        int minute = cl.get(Calendar.MINUTE);
        int second = cl.get(Calendar.SECOND);
        return  year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
    }
}
