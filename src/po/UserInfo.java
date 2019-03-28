package po;

import java.sql.Timestamp;

public class UserInfo
{
    private String userId;
    private String nickName;
    private String protrait;
    private Character sex;
    private Timestamp birthday;
    private String IDNum;

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getNickName()
    {
        return nickName;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    public String getProtrait()
    {
        return protrait;
    }

    public void setProtrait(String protrait)
    {
        this.protrait = protrait;
    }

    public Character getSex()
    {
        return sex;
    }

    public void setSex(Character sex)
    {
        this.sex = sex;
    }

    public Timestamp getBirthday()
    {
        return birthday;
    }

    public void setBirthday(Timestamp birthday)
    {
        this.birthday = birthday;
    }

    public String getIDNum()
    {
        return IDNum;
    }

    public void setIDNum(String IDNum)
    {
        this.IDNum = IDNum;
    }

    public UserInfo(String userId, String nickName, String protrait, Character sex, Timestamp birthday, String IDNum)
    {
        this.userId = userId;
        this.nickName = nickName;
        this.protrait = protrait;
        this.sex = sex;
        this.birthday = birthday;
        this.IDNum = IDNum;
    }

    public UserInfo(){}

    public UserInfo(String userId)
    {
        this.userId = userId;
    }

    @Override
    public String toString()
    {
        return userId+" "+nickName+" "+sex;
    }
}
