package ru.asu.mobiledigitalassistant.pojo;

import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.commons.models.IUser;

import java.util.Date;

public class MessageWrapper implements IMessage {

    private String message;
    private IUser user;
    private String id;
    private Date date;

    public MessageWrapper(String id, IUser user, String message, Date date) {
        this.user = user;
        this.message = message;
        this.id = id;
        this.date = date;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getText() {
        return message;
    }

    @Override
    public IUser getUser() {
        return this.user;
    }

    @Override
    public Date getCreatedAt() {
        return this.date;
    }
}
