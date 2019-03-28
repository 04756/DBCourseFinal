package po;

public class Address
{
    private String addressId;
    private String userId;
    private String address;
    private String receiver;
    private String tel;

    public String getAddressId()
    {
        return addressId;
    }

    public void setAddressId(String addressId)
    {
        this.addressId = addressId;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getReceiver()
    {
        return receiver;
    }

    public void setReceiver(String receiver)
    {
        this.receiver = receiver;
    }

    public String getTel()
    {
        return tel;
    }

    public void setTel(String tel)
    {
        this.tel = tel;
    }

    public Address(String addressId, String userId, String address, String receiver, String tel)
    {
        this.addressId = addressId;
        this.userId = userId;
        this.address = address;
        this.receiver = receiver;
        this.tel = tel;
    }

    public Address(String userId, String address, String receiver, String tel)
    {
        this.userId = userId;
        this.address = address;
        this.receiver = receiver;
        this.tel = tel;
    }

    public Address()
    {
    }

    public Address(String addressId)
    {
        this.addressId = addressId;
    }

    @Override
    public String toString()
    {
        return addressId+" "+userId+" "+receiver;
    }
}
