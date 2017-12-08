package finki.ukim.mk.soatickets.business.view.models.messages;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateMessageViewModel {
    @NotNull
    private long senderId;
    @NotNull
    private long recieverId;
    @NotNull
    @Size(min = 5)
    private String subject;
    @NotNull
    @Size(min = 5)
    private String message;

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public long getRecieverId() {
        return recieverId;
    }

    public void setRecieverId(long recieverId) {
        this.recieverId = recieverId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
