package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.GenericExceptionMapper;
import facade.BookingFacade;
import facade.CarFacade;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.SecurityContext;
import utils.PuSelector;

@Path("user")
public class UserRessource {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private final BookingFacade BF = BookingFacade.getInstance(PuSelector.getEntityManagerFactory("pu"));
    private final CarFacade CF = CarFacade.getInstance(PuSelector.getEntityManagerFactory("pu"));
    private final GenericExceptionMapper GEM = new GenericExceptionMapper();

    @Context
    private UriInfo context;

    @Context
    private SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/orders")
    @RolesAllowed("user")
    public Response getAllOrdersByUser() {
        try {
            String userName = securityContext.getUserPrincipal().getName();
            return Response.ok().entity(GSON.toJson(BF.getAllBookingsByUserName(userName))).build();
        } catch (Exception ex) {
            return GEM.toResponse(ex);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cars")
    @RolesAllowed("user")
    public Response getAllOwnCarsByUser() {
        try {
            String userName = securityContext.getUserPrincipal().getName();
            return Response.ok().entity(GSON.toJson(CF.getAllOwnCarsByUserName(userName))).build();
        } catch (Exception ex) {
            return GEM.toResponse(ex);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cancel/{orderId}")
    @RolesAllowed("user")
    public Response getAllOwnCarsByUser(@PathParam("orderId") String orderId) {
        try {
            String userName = securityContext.getUserPrincipal().getName();
            return Response.ok().entity(GSON.toJson(BF.cancelOrderByOrderId(userName, orderId))).build();
        } catch (Exception ex) {
            return GEM.toResponse(ex);
        }
    }
}