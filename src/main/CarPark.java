package main;

public class CarPark {

    String id;
    String name;
    int capacity;
    int spacesAvailable;
    CarParkKind kind;
    CarParkStatus status;
    Rate rate;

    public CarPark(String id, String name, int capacity, int spacesAvailable, CarParkKind kind, CarParkStatus status) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.spacesAvailable = spacesAvailable;
        this.kind = kind;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getSpacesAvailable() {
        return spacesAvailable;
    }

    public void setSpacesAvailable(int spacesAvailable) {
        this.spacesAvailable = spacesAvailable;
    }

    public CarParkKind getKind() {
        return kind;
    }

    public void setKind(CarParkKind kind) {
        this.kind = kind;
    }

    public CarParkStatus getStatus() {
        return status;
    }

    public void setStatus(CarParkStatus status) {
        this.status = status;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

}
