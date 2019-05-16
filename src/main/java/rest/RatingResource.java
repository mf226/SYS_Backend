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
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import utils.PuSelector;

/**
 * REST Web Service
 *
 * @author Fen
 */
@Path("rating")
public class RatingResource {
    
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    EntityManagerFactory emf = PuSelector.getEntityManagerFactory("pu");
    RatingFacade rf = RatingFacade.getInstance(emf);
    GenericExceptionMapper gem = new GenericExceptionMapper();

    @Context
    private UriInfo context;

    public RatingResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getRating/{userName}")
    public Response getRating(@PathParam("userName") String userName) {
        try {
            RatingDTO dto = rf.getRatingByUser(userName);
            return Response.ok().entity(gson.toJson(dto)).build();
        } catch (Exception ex) {
            return gem.toResponse(ex);

        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/createRating")
    public Response createRating(String content) {
        RatingDTO dto;
        try {
            dto = rf.createRating(gson.fromJson(content, RatingDTO.class));
            return Response.ok().entity(gson.toJson(dto)).build();
        } catch (Exception ex) {
            return gem.toResponse(ex);
        }
    }
}
