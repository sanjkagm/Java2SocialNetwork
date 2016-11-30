package lv.javaguru.java2.domain;

import java.util.Date;

/**
 * Created by Pavel on 29.11.2016..
 */
public class UserMessage {

    private Long id;
    private String sender;
    private String recipient;
    private String text;
    private Boolean isRead;
    private Date created;


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!UserMessage.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final UserMessage other = (UserMessage) obj;
        return (this.id == other.getId()) &&
                (this.sender.equals(other.getSender())) &&
                (this.recipient.equals(other.getRecipient())) &&
                (this.text.equals(other.getText())) &&
                (this.isRead.equals(other.getIsRead()));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.id.intValue();
        hash = 53 * hash + this.sender.hashCode();
        hash = 53 * hash + this.recipient.hashCode();
        hash = 53 * hash + this.created.hashCode();
        hash = 53 * hash + this.text.hashCode();
        hash = 53 * hash + this.isRead.hashCode();
        return hash;
    }



    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public boolean getIsRead() { return isRead; }
    public void setIsRead(boolean read) { isRead = read; }

    public Date getCreated() { return created; }
    public void setCreated(Date created) { this.created = created; }
}
