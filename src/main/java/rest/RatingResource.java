package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.GenericExceptionMapper;
import facade.RatingFacade;
import dto.RatingDTO;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import utils.PuSelector;

@Path("rating")
public class RatingResource {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private final EntityManagerFactory EMF = PuSelector.getEntityManagerFactory("pu");
    private final RatingFacade RF = RatingFacade.getInstance(EMF);
    private final GenericExceptionMapper GEM = new GenericExceptionMapper();

    @Context
    private UriInfo context;

    public RatingResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getRating/{userName}")
    public Response getRating(@PathParam("userName") String userName) {
        try {
            RatingDTO dto = RF.getRatingByUser(userName);
            return Response.ok().entity(GSON.toJson(dto)).build();
        } catch (Exception ex) {
            return GEM.toResponse(ex);

        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/createRating")
    public Response createRating(String content) {
        RatingDTO dto;
        try {
            dto = RF.createRating(GSON.fromJson(content, RatingDTO.class));
            return Response.ok().entity(GSON.toJson(dto)).build();
        } catch (Exception ex) {
            return GEM.toResponse(ex);
        }
    }
}
