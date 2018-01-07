package sample;

public class Client {
    private int id;
    //required
    private String firstName;
    private String lastName;
    private int identityCardNumber;
    //optional
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

    public Client(ClientBuilder clientBuilder)
    {
        this.constructClient(clientBuilder);
    }

    public void constructClient(ClientBuilder clientBuilder)
    {
        this.firstName = clientBuilder.firstName;
        this.lastName = clientBuilder.lastName;
        this.identityCardNumber = clientBuilder.identityCardNumber;
        this.drivingLicenseNumber = clientBuilder.drivingLicenseNumber;
        this.address = clientBuilder.address;
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

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", identityCardNumber=" + identityCardNumber +
                ", drivingLicenseNumber=" + drivingLicenseNumber +
                ", address=" + address +
                '}';
    }

    public static class ClientBuilder{
        //required
        private final String firstName;
        private final String lastName;
        private final int identityCardNumber;
        //optional
        private int drivingLicenseNumber;
        private Address address;

        public ClientBuilder(String firstName, String lastName, int identityCardNumber)
        {
            this.firstName = firstName;
            this.lastName = lastName;
            this.identityCardNumber = identityCardNumber;
        }

        public ClientBuilder addDrivingLicenseNumber(int drivingLicenseNumber)
        {
            this.drivingLicenseNumber = drivingLicenseNumber;
            return this;
        }

        public ClientBuilder addAddress(Address address)
        {
            this.address = address;
            return this;
        }

        public Client build()
        {
            return new Client(this);
        }
    }
}
