package sample;

public class Client {
    private int id;
    private String firstName;
    private String lastName;
    private int identityCardNumber;
    private int drivingLicenseNumber;
    private Address address;

    public Client() {
    }

    public Client(String firstName, String lastName, int identityCardNumber, int drivingLicenseNumber, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.identityCardNumber = identityCardNumber;
        this.drivingLicenseNumber = drivingLicenseNumber;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getIdentityCardNumber() {
        return identityCardNumber;
    }

    public void setIdentityCardNumber(int identityCardNumber) {
        this.identityCardNumber = identityCardNumber;
    }

    public int getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }

    public void setDrivingLicenseNumber(int drivingLicenseNumber) {
        this.drivingLicenseNumber = drivingLicenseNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        //address.setClient(this);
        this.address = address;
    }
}
