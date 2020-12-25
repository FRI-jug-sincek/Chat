package si.fri.rso.samples.chat.lib;

import java.time.Instant;

public class Chat {

    private Integer chatId;
    private Integer apartmentId;
    private Integer userId;
    private String message;
    private Instant timestamp;

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public Integer getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Integer apartmentId) {
        this.apartmentId = apartmentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
