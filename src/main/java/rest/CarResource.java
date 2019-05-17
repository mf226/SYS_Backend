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
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import facade.DataFetcher;
import facade.UserFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.POST;
import utils.PuSelector;
import utils.SetupTestUsers;

@Path("car")
public class CarResource {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private final EntityManagerFactory EMF = PuSelector.getEntityManagerFactory("pu");
    private final CarFacade CF = CarFacade.getInstance(EMF);
    private final DataFetcher DF = DataFetcher.getInstance(EMF);
    private final GenericExceptionMapper GEM = new GenericExceptionMapper();

    @Context
    private UriInfo context;

    public CarResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/test")
    public Response test() {
        if (UserFacade.getInstance(PuSelector.getEntityManagerFactory("pu")).getAllUsers().isEmpty()) {
            SetupTestUsers.createTestUsers();
        }
        return Response.ok().entity(GSON.toJson("You are connected")).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{regno}/{company}")
    public Response getCar(@PathParam("regno") String regno, @PathParam("company") String company) {

        try {
            CarDTO car = DF.getSpecificCar(regno, company);
            return Response.ok().entity(GSON.toJson(car)).build();
        } catch (Exception ex) {
            return GEM.toResponse(ex);

        }

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAllCars() {
        //List<CarDTO> allCars = DataFetcher;
        List<CarDTO> allCars;
        try {
            allCars = DF.getAllCarsAllAPIs();
            return Response.ok().entity(GSON.toJson(allCars)).build();
        } catch (Exception ex) {
            return GEM.toResponse(ex);
        }

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/available/{start}/{end}")
    public Response getAllAvailableCars(@PathParam("start") String start, @PathParam("end") String end) {
        List<CarDTO> allCars;
        try {
            allCars = DF.getAllAvailableCarsAllAPIs(start, end);
            return Response.ok().entity(GSON.toJson(allCars)).build();
        } catch (Exception ex) {
            return GEM.toResponse(ex);
        }

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/rent/{company}/{regno}/{start}/{end}")
    public Response rentAvailableCar(@PathParam("company") String company, @PathParam("regno") String regno, @PathParam("start") String start, @PathParam("end") String end) {
        BookinginformationDTO dto;
        try {
            dto = DF.rentCar(company, regno, start, end);
            return Response.ok().entity(GSON.toJson(dto)).build();
        } catch (Exception ex) {
            return GEM.toResponse(ex);
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/listMyCar")
    public Response listOwnCar(String content) {
        CarDTO dto;
        try {
            dto = CF.listOwnCar(GSON.fromJson(content, CarDTO.class));
            return Response.ok().entity(GSON.toJson(dto)).build();
        } catch (Exception ex) {
            return GEM.toResponse(ex);
        }
    }
}
