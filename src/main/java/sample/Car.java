package sample;

public class Car {
    private int id;
    //required
    private Model carModel;
    private String registrationNumber;
    private double oneDayCost;
    private boolean availability;
    //optional
    private String originCountry;
    private int productionYear;
    private String advancedInformation;


    public Car() {}

    public Car(Model carModel, String registrationNumber, int productionYear, String originCountry, String advancedInformation, double oneDayCost) {
        this.carModel = carModel;
        this.registrationNumber = registrationNumber;
        this.productionYear = productionYear;
        this.originCountry = originCountry;
        this.advancedInformation = advancedInformation;
        this.oneDayCost = oneDayCost;
        this.availability = true;
    }

    public Car(CarBuilder carBuilder) {
        this.carModel = carBuilder.carModel;
        this.registrationNumber = carBuilder.registrationNumber;
        this.productionYear = carBuilder.productionYear;
        this.originCountry = carBuilder.originCountry;
        this.advancedInformation = carBuilder.advancedInformation;
        this.oneDayCost = carBuilder.oneDayCost;
        this.availability = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Model getCarModel() {
        return carModel;
    }

    public void setCarModel(Model carModel) {
        this.carModel = carModel;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public String getAdvancedInformation() {
        return advancedInformation;
    }

    public void setAdvancedInformation(String advancedInformation) {
        this.advancedInformation = advancedInformation;
    }

    public double getOneDayCost() {
        return oneDayCost;
    }

    public void setOneDayCost(double oneDayCost) {
        this.oneDayCost = oneDayCost;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public static class CarBuilder{

        private final Model carModel;
        private final String registrationNumber;
        private final double oneDayCost;
        //optional
        private String originCountry;
        private int productionYear;
        private String advancedInformation;

        public CarBuilder(Model carModel, String registrationNumber, double oneDayCost)
        {
            this.carModel = carModel;
            this.registrationNumber = registrationNumber;
            this.oneDayCost = oneDayCost;
        }

        public CarBuilder addOriginCountry(String originCountry)
        {
            this.originCountry = originCountry;
            return this;
        }

        public CarBuilder addProductionYear(int productionYear)
        {
            this.productionYear = productionYear;
            return this;
        }

        public CarBuilder addAdvancedInformation(String advancedInformation)
        {
            this.advancedInformation = advancedInformation;
            return this;
        }

        public Car build()
        {
            return new Car(this);
        }
    }
}
