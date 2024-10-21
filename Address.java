
package ZivGohasi_OranTausi_LibiSpivak;

public class Address {
    private String state;
    private String city;
    private String street;
    private int buildingNumber;

    public Address(String state, String city, String street, int buildingNumber) {
        this.state = state;
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    @Override
    public String toString() {
        return "- Country: " + state +
                "\n- City: " + city +
                "\n- Street: " + street +
                "\n- Building Number: " + buildingNumber + "\n";
    }
}