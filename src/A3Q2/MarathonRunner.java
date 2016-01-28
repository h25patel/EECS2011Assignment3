/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A3Q2;

/**
 *
 * @author elder
 */
public class MarathonRunner {
    private int place, bibNum;
    private String chipTime;
    private String lastName;
    private String firstName;
    
    protected MarathonRunner(int place, String chipTime, int bibNum, String lastName, String firstName) {
        setPlace(place);
        setChipTime(chipTime);
        setBibNum(bibNum);
        setLastName(lastName);
        setFirstName(firstName);
    }

    protected int getPlace() {
        return place;
    }

    protected String getChipTime() {
        return chipTime;
    }

    protected String getBibNum() {
        return chipTime;
    }

    protected String getLastName() {
        return lastName;
    }

    protected String getFirstName() {
        return firstName;
    }

    protected void setPlace(int place) {
        this.place = place;
    }

    protected void setChipTime(String chipTime) {
        this.chipTime = chipTime;
    }

    protected void setBibNum(int bibNum) {
        this.bibNum = bibNum;
    }

    protected void setLastName(String lastName) {
        this.lastName = lastName;
    }

    protected void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "Place: " + getPlace() + " Chip Time: " + getChipTime()
                + " Name: " + getFirstName() + " " + getLastName();
    }
}
