package lv.javaguru.java2.domain;

/**
 * Created by Pavel on 30.11.2016..
 */

import java.util.Date;

public class UserMessageBuilder {

    private Long id;
    private String sender;
    private String recipient;
    private Date created;
    private String text;
    private Boolean isRead;



    private UserMessageBuilder() {}

    public static UserMessageBuilder createUserMessage() {
        return new UserMessageBuilder();
    }

    public UserMessage build() {
        UserMessage userMessage = new UserMessage();
        userMessage.setId(id);
        userMessage.setSender(sender);
        userMessage.setRecipient(recipient);
        userMessage.setCreated(created);
        userMessage.setText(text);
        userMessage.setIsRead(isRead);

        return userMessage;
    }

    public UserMessageBuilder withMsgId(Long id) {
        this.id = id;
        return this;
    }

    public UserMessageBuilder withSender(String sender) {
        this.sender = sender;
        return this;
    }

    public UserMessageBuilder withRecipient(String recipient) {
        this.recipient = recipient;
        return this;
    }

    public UserMessageBuilder withCreated(Date created) {
        this.created = created;
        return this;
    }
    public UserMessageBuilder withText(String text) {
        this.text = text;
        return this;
    }
    public UserMessageBuilder withIsRead(Boolean isRead) {
        this.isRead = isRead;
        return this;
    }

}

