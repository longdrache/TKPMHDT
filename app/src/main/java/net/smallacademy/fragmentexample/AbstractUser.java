package net.smallacademy.fragmentexample;

public abstract class AbstractUser {
    protected String name, email, mobile_number, password;


    public abstract String getName();



    public abstract String getEmail();



    public abstract String getMobile_number();


    public abstract String getPassword();


    public abstract boolean isNull();
}
