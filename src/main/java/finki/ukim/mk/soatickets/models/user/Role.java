package finki.ukim.mk.soatickets.models.user;

import finki.ukim.mk.soatickets.models.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DarkoM on 24.11.2017.
 */
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    protected Role(){ }

    public Role(String name){
        this.name = name;
        this.users = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
