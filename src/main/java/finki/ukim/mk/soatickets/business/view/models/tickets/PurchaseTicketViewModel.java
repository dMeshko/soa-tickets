package finki.ukim.mk.soatickets.business.view.models.tickets;

import finki.ukim.mk.soatickets.models.tickets.PaymentMethod;

import javax.validation.constraints.Min;

/**
 * Created by DarkoM on 03.12.2017.
 */
public class PurchaseTicketViewModel {
    private long userId;
    @Min(1)
    private long ticketId;
    @Min(0)
    private int paymentMethod;
    private float amountPayed;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTicketId() {
        return ticketId;
    }

    public void setTicketId(long ticketId) {
        this.ticketId = ticketId;
    }

    public PaymentMethod getPaymentMethod() {
        return PaymentMethod.values()[paymentMethod];
    }

    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public float getAmountPayed() {
        return amountPayed;
    }

    public void setAmountPayed(float amountPayed) {
        this.amountPayed = amountPayed;
    }
}
