package finki.ukim.mk.soatickets.business.view.models.notifications;

import finki.ukim.mk.soatickets.business.view.models.user.UserViewModel;

public class NotificationViewModel {

    private Long id;
    private UserViewModel sender;
    private UserViewModel receiver;
    private String content;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public UserViewModel getSender() { return sender; }

    public void setSender(UserViewModel sender) { this.sender = sender; }

    public UserViewModel getReceiver() { return receiver; }

    public void setReceiver(UserViewModel receiver) { this.receiver = receiver; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

}
