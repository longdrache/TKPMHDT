package net.smallacademy.fragmentexample;

public abstract class NullUser extends AbstractUser  {
    @Override
    public String getName() {
        return "Not available";
    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public String getMobile_number() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isNull() {
        return true;
    }
}
