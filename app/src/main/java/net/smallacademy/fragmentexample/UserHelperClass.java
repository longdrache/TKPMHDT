package net.smallacademy.fragmentexample;

public class UserHelperClass extends AbstractUser{
    public UserHelperClass() {
    }

    public UserHelperClass(String name, String email, String mobile_number, String password) {
        this.name = name;
        this.email = email;
        this.mobile_number = mobile_number;
        this.password = password;
    }

    @Override
    public boolean isNull() {
        return false;
    }
    @Override
    public String getName() {
    return name;
    }

    public void setName(String name) {
    this.name = name;
    }
    @Override
    public String getEmail() {
    return email;
    }

    public void setEmail(String email) {
    this.email = email;
    }
    @Override
    public String getMobile_number() {
    return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
    this.mobile_number = mobile_number;
    }
    @Override
    public String getPassword() {
return password;
}

    public void setPassword(String password) {
        this.password = password;
    }
}
