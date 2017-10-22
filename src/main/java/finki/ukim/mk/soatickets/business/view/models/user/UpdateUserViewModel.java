package finki.ukim.mk.soatickets.business.view.models.user;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by DarkoM on 22.10.2017.
 */
public class UpdateUserViewModel extends RegisterUserViewModel {
    @NotNull
    @Min(1)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}