package sample;

public class Address {
    private int id;
    private String townName;
    private String postCode;
    private String country;
    private String streetAddress;

    public Address() {}

    public Address(String townName, String postCode, String country, String flatNumber) {
        this.townName = townName;
        this.postCode = postCode;
        this.country = country;
        this.streetAddress = flatNumber;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
