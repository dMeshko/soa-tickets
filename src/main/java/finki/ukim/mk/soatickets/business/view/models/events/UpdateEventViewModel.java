package finki.ukim.mk.soatickets.business.view.models.events;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class UpdateEventViewModel extends CreateEventViewModel {

    @NotNull
    @Min(1)
    private long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
