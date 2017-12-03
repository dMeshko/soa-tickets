package finki.ukim.mk.soatickets.business.view.models;

/**
 * Created by DarkoM on 03.12.2017.
 */
public class LookupViewModel<T> {
    private T id;
    private String name;

    public LookupViewModel(T id, String name) {
        this.id = id;
        this.name = name;
    }

    public T getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
