package sample;

import java.util.Date;

public class Rental {
    private int id;
    private Car car;
    private Client client;
    private Date dateFrom;
    private Date dateTo;
    private double cost;

    public Rental(){}

    public Rental(Car car, Client client, Date dateFrom, Date dateTo, double cost) {
        this.car = car;
        this.client = client;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
