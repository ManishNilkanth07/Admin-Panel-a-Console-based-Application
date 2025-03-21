package User;

import java.util.List;

public class User {

    private String name;

    private String email;

    private List<String> mobile;

    public User()
    {

    }
    public User(String name, String email, List<String> mobile)
    {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getEmail()
    {
        return this.email;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return this.name;
    }
    public void setMobile(List<String> mobile)
    {
        this.mobile = mobile;
    }
    public List<String> getMobile()
    {
        return this.mobile;
    }
    @Override
    public String toString()
    {
        return null;
    }
}
