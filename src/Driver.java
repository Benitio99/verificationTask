package src;

public class Driver {

    String name;

    final int PIN;
    CarParkKind kind;
    Boolean allowed;

    public Driver() {
        this.name = "";
        this.PIN = 0000;
        this.kind = CarParkKind.VISITOR;
        this.allowed = false;
    }

    public Driver(String name, int pIN, CarParkKind kind, Boolean allowed) {
        this.name = name;
        PIN = pIN;
        this.kind = kind;
        this.allowed = allowed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPIN() {
        return PIN;
    }

    public CarParkKind getKind() {
        return kind;
    }

    public void setKind(CarParkKind kind) {
        this.kind = kind;
    }

    public Boolean getAllowed() {
        return allowed;
    }

    public void setAllowed(Boolean allowed) {
        this.allowed = allowed;
    }

}
