package net.smallacademy.fragmentexample;

public class RestaurentModel
{
    String purl;
    RestaurentModel()
    {
    }

    public RestaurentModel(String purl) {
        this.purl = purl;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }
}
