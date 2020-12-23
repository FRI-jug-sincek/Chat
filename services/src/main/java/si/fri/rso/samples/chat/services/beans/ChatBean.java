package si.fri.rso.samples.chat.services.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;

import si.fri.rso.samples.chat.lib.Chat;
import si.fri.rso.samples.chat.models.converters.ChatConverter;
import si.fri.rso.samples.chat.models.entities.ChatEntity;


@RequestScoped
public class ChatBean {

    private Logger log = Logger.getLogger(ChatBean.class.getName());

    @Inject
    private EntityManager em;

    public List<Chat> getChat() {

        TypedQuery<ChatEntity> query = em.createNamedQuery(
                "ChatEntity.getAll", ChatEntity.class);

        List<ChatEntity> resultList = query.getResultList();

        return resultList.stream().map(ChatConverter::toDto).collect(Collectors.toList());
    }

    public List<Chat> getChatFilter(UriInfo uriInfo) {

        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).defaultOffset(0)
                .build();

        return JPAUtils.queryEntities(em, ChatEntity.class, queryParameters).stream()
                .map(ChatConverter::toDto).collect(Collectors.toList());
    }

    public Chat getChat(Integer id) {

        ChatEntity chatEntity = em.find(ChatEntity.class, id);

        if (chatEntity == null) {
            throw new NotFoundException();
        }

        Chat chat = ChatConverter.toDto(chatEntity);

        return chat;
    }

    public Chat createChat(Chat chat) {

        ChatEntity chatEntity = ChatConverter.toEntity(chat);

        try {
            beginTx();
            em.persist(chatEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        if (chatEntity.getId() == null) {
            throw new RuntimeException("Entity was not persisted");
        }

        return ChatConverter.toDto(chatEntity);
    }

    public Chat putChat(Integer id, Chat chat) {

        ChatEntity c = em.find(ChatEntity.class, id);

        if (c == null) {
            return null;
        }

        ChatEntity updatedChatEntity = ChatConverter.toEntity(chat);

        try {
            beginTx();
            updatedChatEntity.setId(c.getId());
            updatedChatEntity = em.merge(updatedChatEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        return ChatConverter.toDto(updatedChatEntity);
    }

    public boolean deleteChat(Integer id) {

        ChatEntity chat = em.find(ChatEntity.class, id);

        if (chat != null) {
            try {
                beginTx();
                em.remove(chat);
                commitTx();
            }
            catch (Exception e) {
                rollbackTx();
            }
        }
        else {
            return false;
        }

        return true;
    }

    private void beginTx() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    private void commitTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}
