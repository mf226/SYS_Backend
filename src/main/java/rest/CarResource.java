package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.BookinginformationDTO;
import dto.CarDTO;
import exceptions.GenericExceptionMapper;
import facade.CarFacade;
import java.util.List;
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
import facade.DataFetcher;
import javax.ws.rs.POST;
import utils.PuSelector;
import utils.SetupTestUsers;

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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/test")
    public Response test() {
        SetupTestUsers.createTestUsers();
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
            allCars = df.getAllCarsAllAPIs();
            return Response.ok().entity(gson.toJson(allCars)).build();
        } catch (Exception ex) {
            return gem.toResponse(ex);
        }

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/available/{start}/{end}")
    public Response getAllAvailableCars(@PathParam("start") String start, @PathParam("end") String end) {
        DataFetcher df = new DataFetcher();
        //List<CarDTO> allCars = DataFetcher;
        List<CarDTO> allCars;
        try {
            allCars = df.getAllAvailableCarsAllAPIs(start, end);
            return Response.ok().entity(gson.toJson(allCars)).build();
        } catch (Exception ex) {
            return gem.toResponse(ex);
        }

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/rent/{company}/{regno}/{start}/{end}")
    public Response rentAvailableCar(@PathParam("company") String company, @PathParam("regno") String regno, @PathParam("start") String start, @PathParam("end") String end) {
        DataFetcher df = new DataFetcher();
        //List<CarDTO> allCars = DataFetcher;
        BookinginformationDTO dto;
        try {
            dto = df.rentCar(company, regno, start, end);
            return Response.ok().entity(gson.toJson(dto)).build();
        } catch (Exception ex) {
            return gem.toResponse(ex);
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/listMyCar")
    public Response listOwnCar(String content) {
        //DataFetcher df = new DataFetcher();
        //List<CarDTO> allCars = DataFetcher;
        CarDTO dto;
        try {
            dto = facade.listOwnCar(gson.fromJson(content, CarDTO.class));
            return Response.ok().entity(gson.toJson(dto)).build();
        } catch (Exception ex) {
            return gem.toResponse(ex);
        }
    }
}
