package main;
//package src;

public class Gate {

    String gateId;
    String location;

    public Gate(String gateId, String location) {
        this.gateId = gateId;
        this.location = location;
    }

    public String getGateId() {
        return gateId;
    }

    public void setGateId(String gateId) {
        this.gateId = gateId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
