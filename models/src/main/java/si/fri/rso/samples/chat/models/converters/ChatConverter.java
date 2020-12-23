package si.fri.rso.samples.chat.models.converters;

import si.fri.rso.samples.chat.lib.Chat;
import si.fri.rso.samples.chat.models.entities.ChatEntity;

public class ChatConverter {

    public static Chat toDto(ChatEntity entity) {

        Chat dto = new Chat();
        dto.setChatId(entity.getId());
        dto.setCreated(entity.getCreated());
        dto.setForeignKey(entity.getForeignKey());
        dto.setEntity(entity.getEntity());
        dto.setUri(entity.getUri());
        dto.setNsfw(entity.getNsfw());
        dto.setFaces(entity.getFaces());

        return dto;

    }

    public static ChatEntity toEntity(Chat dto) {

        ChatEntity entity = new ChatEntity();
        entity.setCreated(dto.getCreated());
        entity.setForeignKey(dto.getForeignKey());
        entity.setEntity(dto.getEntity());
        entity.setUri(dto.getUri());
        entity.setNsfw(dto.getNsfw());
        entity.setFaces(dto.getFaces());

        return entity;

    }

}
