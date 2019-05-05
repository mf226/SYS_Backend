package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CarDTO;
import exceptions.GenericExceptionMapper;
import facade.CarFacade;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import utils.DataFetcher;
import utils.PuSelector;

/**
 * REST Web Service
 *
 * @author Fen
 */
@Path("car")
public class CarResource {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    CarFacade facade = CarFacade.getInstance(PuSelector.getEntityManagerFactory("pu"));
    GenericExceptionMapper gem = new GenericExceptionMapper();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CarResource
     */
    public CarResource() {
    }

    /**
     * Retrieves representation of an instance of rest.CarResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/test")
    public Response test() {
        //TODO return proper representation object
        return Response.ok().entity(gson.toJson("You are connected")).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{regno}/{company}")
    public Response getCar(@PathParam("regno") String regno, @PathParam("company") String company) {
        DataFetcher df = new DataFetcher();
        try {
            CarDTO car = df.getSpecificCar(regno, company);
            return Response.ok().entity(gson.toJson(car)).build();
        } catch (Exception ex) {
            return gem.toResponse(ex);
            
        }

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAllCars() {
        DataFetcher df = new DataFetcher();
        //List<CarDTO> allCars = DataFetcher;
        List<CarDTO> allCars;
        try {
            allCars = df.getAllCarsFromOneAPI();
            return Response.ok().entity(gson.toJson(allCars)).build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }

    }
    
        @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/available/{start}/{end}")
    public Response getAllAvailableCars(@PathParam("regno") String start, @PathParam("company") String end) {
        DataFetcher df = new DataFetcher();
        //List<CarDTO> allCars = DataFetcher;
        List<CarDTO> allCars;
        try {
            allCars = df.getAllAvailableCars(start, end);
            return Response.ok().entity(gson.toJson(allCars)).build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }

    }

    /**
     * PUT method for updating or creating an instance of CarResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
