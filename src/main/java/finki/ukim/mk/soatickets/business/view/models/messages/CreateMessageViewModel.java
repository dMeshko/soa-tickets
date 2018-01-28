package finki.ukim.mk.soatickets.business.view.models.messages;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateMessageViewModel {
    private long senderId;
    @NotNull
    private long receiverId;
    @NotNull
    @Size(min = 5)
    private String subject;
    @NotNull
    @Size(min = 5)
    private String message;

    private Long id;

    public void setId(Long id) {
        this.id = id;
    }
    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(long receiverId) {
        this.receiverId = receiverId;
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
