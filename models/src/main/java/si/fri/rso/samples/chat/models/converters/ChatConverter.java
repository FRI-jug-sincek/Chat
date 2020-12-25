package si.fri.rso.samples.chat.models.converters;

import si.fri.rso.samples.chat.lib.Chat;
import si.fri.rso.samples.chat.models.entities.ChatEntity;

public class ChatConverter {

    public static Chat toDto(ChatEntity entity) {

        Chat dto = new Chat();
        dto.setChatId(entity.getId());
        dto.setTimestamp(entity.getTimestamp());
        dto.setApartmentId(entity.getApartmentId());
        dto.setUserId(entity.getUserId());
        dto.setMessage(entity.getMessage());

        return dto;
    }

    public static ChatEntity toEntity(Chat dto) {

        ChatEntity entity = new ChatEntity();
        entity.setTimestamp(dto.getTimestamp());
        entity.setApartmentId(dto.getApartmentId());
        entity.setUserId(dto.getUserId());
        entity.setMessage(dto.getMessage());

        return entity;
    }

}
