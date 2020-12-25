package si.fri.rso.samples.chat.models.entities;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "chat")
@NamedQueries(value =
        {
                @NamedQuery(name = "ChatEntity.getAll",
                        query = "SELECT u FROM ChatEntity u")
        })
public class ChatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "apartmentId")
    private Integer apartmentId;

    @Column(name = "userId")
    private Integer userId;

    @Column(name = "message", length = 1024)
    private String message;

    @Column(name = "timestamp")
    private Instant timestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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