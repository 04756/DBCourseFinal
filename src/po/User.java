package po;

public class User
{
    private String userId;
    private String password;
    private String phone;
    private String email;

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public User(String id,String passwd,String phone,String email)
    {
        this.userId=id;
        this.password=passwd;
        this.phone=phone;
        this.email=email;
    }

    public User(){}

    public User(String userId)
    {
        this.userId = userId;
    }

    @Override
    public String toString()
    {
        return userId+" "+password+" "+phone+" "+email;
    }
}
