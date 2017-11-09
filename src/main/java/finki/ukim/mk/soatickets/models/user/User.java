package finki.ukim.mk.soatickets.models.user;

import finki.ukim.mk.soatickets.models.BaseEntity;
import finki.ukim.mk.soatickets.models.tickets.BoughtTicket;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DarkoM on 22.10.2017.
 */
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String firstName;
    private String lastName;
    private String password;
    @Column(unique = true)
    private String email;
    private String phoneNumber;
    private boolean isActive;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<BoughtTicket> boughtTickets;

    protected User(){}

    public User(String firstName, String lastName, String email, String password, String phoneNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.isActive = true;
        this.boughtTickets = new ArrayList<>();
    }

    @Override
    public String toString(){
        return getFirstName() + " " + getLastName();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<BoughtTicket> getBoughtTickets() {
        return boughtTickets;
    }

    public void setBoughtTickets(List<BoughtTicket> boughtTickets) {
        this.boughtTickets = boughtTickets;
    }
}
