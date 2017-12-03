package finki.ukim.mk.soatickets.business.view.models.tickets;

import finki.ukim.mk.soatickets.business.view.models.LookupViewModel;
import finki.ukim.mk.soatickets.models.tickets.PaymentMethod;
import finki.ukim.mk.soatickets.models.tickets.Ticket;
import finki.ukim.mk.soatickets.models.user.User;

/**
 * Created by DarkoM on 03.12.2017.
 */
public class InvoiceViewModel {
    private LookupViewModel<Long> user;
    private LookupViewModel<Long> ticket;
    private float amountPayed;
    private LookupViewModel<Integer> paymentMethod;

    public InvoiceViewModel(User user, Ticket ticket, float amountPayed, PaymentMethod paymentMethod) {
        this.user = new LookupViewModel<>(user.getId(), user.toString());
        this.ticket = new LookupViewModel<>(ticket.getId(), ticket.getEvent().getName());
        this.amountPayed = amountPayed;
        this.paymentMethod = new LookupViewModel<Integer>(paymentMethod.ordinal(), paymentMethod.toString());
    }

    public LookupViewModel<Long> getUser() {
        return user;
    }

    public void setUser(LookupViewModel<Long> user) {
        this.user = user;
    }

    public LookupViewModel<Long> getTicket() {
        return ticket;
    }

    public void setTicket(LookupViewModel<Long> ticket) {
        this.ticket = ticket;
    }

    public float getAmountPayed() {
        return amountPayed;
    }

    public void setAmountPayed(float amountPayed) {
        this.amountPayed = amountPayed;
    }

    public LookupViewModel<Integer> getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(LookupViewModel<Integer> paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}