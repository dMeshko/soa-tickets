package finki.ukim.mk.soatickets.models.user;

import finki.ukim.mk.soatickets.models.BaseEntity;
import finki.ukim.mk.soatickets.models.events.Event;
import finki.ukim.mk.soatickets.models.tickets.BoughtTicket;
import finki.ukim.mk.soatickets.models.tickets.Invoice;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by DarkoM on 22.10.2017.
 */

@Indexed
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Field
    private String firstName;
    @Field
    private String lastName;

    private String password;
    @Column(unique = true)
    @Field
    private String email;
    @Field
    private String phoneNumber;
    private boolean isActive;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    @IndexedEmbedded
    private List<Event> ownedEvents;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<BoughtTicket> boughtTickets;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Invoice> invoices;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    protected User(){
        this.roles = new ArrayList<>();
    }

    public User(String firstName, String lastName, String email, String password, String phoneNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.isActive = true;
        this.boughtTickets = new ArrayList<>();
        this.ownedEvents = new ArrayList<>();
        this.roles = new ArrayList<>();
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

    public List<Event> getOwnedEvents() {
        return ownedEvents;
    }

    public void setOwnedEvents(List<Event> ownedEvents) {
        this.ownedEvents = ownedEvents;
    }

    public List<String> getRoles() {
        List<String> roleNames = roles.stream().map(Role::getName).collect(Collectors.toList());

        return roleNames;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role){
        this.roles.add(role);
    }

    public List<GrantedAuthority> getClaims(){
        List<GrantedAuthority> claims = roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

        return claims;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
}
