package si.fri.rso.samples.chat.api.v1.resources;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;
import java.net.URI;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import si.fri.rso.samples.chat.lib.Chat;
import si.fri.rso.samples.chat.services.beans.ChatBean;

@ApplicationScoped
@Path("/chat")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ChatResource {

    private Logger log = Logger.getLogger(ChatResource.class.getName());

    @Inject
    private ChatBean chatBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response getChat() {

        List<Chat> chat = chatBean.getChat();

        return Response.status(Response.Status.OK).entity(chat).build();
    }

    @GET
    @Path("/filtered")
    public Response getChatFiltered() {

        List<Chat> chat;

        chat = chatBean.getChatFilter(uriInfo);

        return Response.status(Response.Status.OK).entity(chat).build();
    }

    @GET
    @Path("/{chatId}")
    public Response getChat(@PathParam("chatId") Integer chatId) {

        Chat chat = chatBean.getChat(chatId);

        if (chat == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(chat).build();
    }

    @POST
    public Response createChat(Chat chat) {

        if ((chat.getForeignKey() == null || chat.getEntity() == null || chat.getUri() == null)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        chat.setFaces(0);
        chat.setNsfw((float) 0);
        chat = chatBean.createChat(chat);

        return Response.status(Response.Status.OK).entity(chat).build();
    }

    @PUT
    @Path("{chatId}")
    public Response putChat(@PathParam("chatId") Integer chatId, Chat chat) {
        chat.setFaces(0);
        chat.setNsfw((float) 0);
        chat = chatBean.putChat(chatId, chat);

        if (chat == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.NOT_MODIFIED).build();
    }

    @DELETE
    @Path("{chatId}")
    public Response deleteChat(@PathParam("chatId") Integer chatId) {

        boolean deleted = chatBean.deleteChat(chatId);

        if (deleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
