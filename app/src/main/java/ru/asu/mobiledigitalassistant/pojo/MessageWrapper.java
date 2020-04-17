package ru.asu.mobiledigitalassistant.pojo;

import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.commons.models.IUser;

import java.util.Date;
import java.util.UUID;

public class MessageWrapper implements IMessage {

    private BotQuestion message;
    private IUser user;

    public MessageWrapper(IUser user, BotQuestion message) {
        this.user = user;
        this.message = message;
    }

    @Override
    public String getId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    @Override
    public String getText() {
        return message.getMessage();
    }

    @Override
    public IUser getUser() {
        return this.user;
    }

    @Override
    public Date getCreatedAt() {
        // TODO: 16.04.2020 Сделать адекватную дату, чтоб она приходила с сервера 
        return new Date();
    }
}
