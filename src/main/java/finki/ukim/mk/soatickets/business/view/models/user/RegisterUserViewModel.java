package finki.ukim.mk.soatickets.business.view.models.user;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by DarkoM on 22.10.2017.
 */
public class RegisterUserViewModel {
    @NotEmpty
    @Size(min = 3)
    private String firstName;

    @NotEmpty @Size(min = 3)
    private String lastName;

    @NotEmpty @Email
    private String email;

    @NotEmpty @Size(min = 6)
    private String password;

    @NotEmpty @Size(min = 9, max = 9)
    private String phoneNumber;

    private Long id;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
