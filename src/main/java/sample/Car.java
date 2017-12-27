package sample;

public class Car {
    private int id;
    private Model carBrand;
    private String registrationNumber;
    private int productionYear;
    private String originCountry;
    private String advancedInformation;
    private double oneDayCost;
    private boolean availability;

    public Car() {}

    public Car(Model carBrand, String registrationNumber, int productionYear, String originCountry, String advancedInformation, double oneDayCost) {
        this.carBrand = carBrand;
        this.registrationNumber = registrationNumber;
        this.productionYear = productionYear;
        this.originCountry = originCountry;
        this.advancedInformation = advancedInformation;
        this.oneDayCost = oneDayCost;
        this.availability = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Model getCarModel() {
        return carBrand;
    }

    public void setCarModel(Model carBrand) {
        this.carBrand = carBrand;
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
}
