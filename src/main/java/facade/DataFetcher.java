package facade;

import exceptions.APIErrorException;
import com.google.gson.stream.JsonReader;
import dto.BookinginformationDTO;
import dto.CarDTO;
import dto.Company;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fen
 */
public class DataFetcher {

    class FetchCars implements Callable<List<CarDTO>> {

        String url;
        String start, end;

        FetchCars(String url) {
            this.url = url;
            this.start = null;
            this.end = null;
        }

        FetchCars(String url, String start, String end) {
            this.url = url;
            this.start = start;
            this.end = end;
        }

        @Override
        public List<CarDTO> call() throws Exception {
            DataFetcher df = new DataFetcher();
            if (start != null && end != null) {
                return df.getAllAvailableCarsFromOneAPI(url, start, end);
            }
            return df.getAllCarsFromOneAPI(url);
        }
    }

    private List<Company> companies;

    public DataFetcher() {
        companies = new ArrayList<>();
        companies.add(new Company("dueinator", "https://www.dueinator.dk/jwtbackend/api/car/"));

        //Add more companies here to add them to the list of API calls.
    }

    public BookinginformationDTO rentCar(String company, String regno, String start, String end) throws APIErrorException {
        BookinginformationDTO dto;
        switch (company) {
            case "dueinator":
                String baseURL = companies.get(companies.indexOf(new Company(company, ""))).getUrl();
                String url = baseURL + regno + "/" + start + "/" + end;
                dto = postRentCarToAPI(url, company);
                break;
//            case "ttt":
//                price = reader.nextDouble();
//                break;
            default:
                throw new APIErrorException("Company doesn't exist");
        }
        return dto;
    }

    private BookinginformationDTO postRentCarToAPI(String url, String company) throws APIErrorException {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept", "application/json;charset=UTF-8");
            con.setRequestProperty("User-Agent", "server");
            if (con.getResponseCode() != 200) {
                throw new APIErrorException(con.getResponseMessage());
            }

            JsonReader reader = new JsonReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            try {
                return readMessageObjectBooking(reader, company);
            } finally {
                reader.close();
            }
        } catch (IOException ey) {
            throw new APIErrorException(ey.getMessage());
        }
    }

    public CarDTO getSpecificCar(String regno, String company) throws APIErrorException {

        try {
            String url = companies.get(companies.indexOf(new Company(company, ""))).getUrl();
            HttpURLConnection con = (HttpURLConnection) new URL(url.concat(regno)).openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json;charset=UTF-8");
            con.setRequestProperty("User-Agent", "server");
            if (con.getResponseCode() != 200) {
                throw new APIErrorException(con.getResponseMessage());
            }

            JsonReader reader = new JsonReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            try {
                return readMessageObjectCar(reader, company);
            } finally {
                reader.close();
            }
        } catch (IndexOutOfBoundsException ex) {
            throw new APIErrorException("Company name doesn't exist");
        } catch (IOException ey) {
            throw new APIErrorException(ey.getMessage());
        }
    }

    public List<CarDTO> getAllCarsAllAPIs() throws APIErrorException {
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future<List<CarDTO>>> futures = new ArrayList<>();
        for (Company c : companies) {
            FetchCars fs = new FetchCars(c.getUrl());
            Future future = executor.submit(fs);
            futures.add(future);
        }
        List<CarDTO> results = new ArrayList();
        for (Future<List<CarDTO>> f : futures) {
            try {
                List<CarDTO> list = f.get(5, TimeUnit.SECONDS);
                results.addAll(list);
            } catch (ExecutionException ex) {
                throw new APIErrorException(ex.getMessage());
            } catch (InterruptedException ex) {
                throw new APIErrorException("Interrupted");
            } catch (TimeoutException ex) {
                throw new APIErrorException("Timeout");
            }
        }
        executor.shutdown();
        return results;
    }

    public List<CarDTO> getAllAvailableCarsAllAPIs(String start, String end) throws APIErrorException {
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future<List<CarDTO>>> futures = new ArrayList<>();
        for (Company c : companies) {
            FetchCars fs = new FetchCars(c.getUrl(), start, end);
            Future future = executor.submit(fs);
            futures.add(future);
        }
        List<CarDTO> results = new ArrayList();
        for (Future<List<CarDTO>> f : futures) {
            try {
                List<CarDTO> list = f.get(5, TimeUnit.SECONDS);
                results.addAll(list);
            } catch (ExecutionException ex) {
                throw new APIErrorException(ex.getMessage());
            } catch (InterruptedException ex) {
                throw new APIErrorException("Interrupted");
            } catch (TimeoutException ex) {
                throw new APIErrorException("Timeout");
            }
        }
        executor.shutdown();
        return results;
    }

    private List<CarDTO> getAllAvailableCarsFromOneAPI(String url, String start, String end) throws APIErrorException {
        try {
            String urlParamaters = "available/" + start + "/" + end;
            HttpURLConnection con = (HttpURLConnection) new URL(url.concat(urlParamaters)).openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json;charset=UTF-8");
            con.setRequestProperty("User-Agent", "server");
            return readJsonStream(con.getInputStream(), companies.get(0).getName());
        } catch (MalformedURLException ex) {
            throw new APIErrorException("Something wrong with the URL");
        } catch (IOException ex) {
            throw new APIErrorException(ex.getMessage());
        }

    }

    private List<CarDTO> getAllCarsFromOneAPI(String URL) throws APIErrorException {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(URL.concat("all")).openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json;charset=UTF-8");
            con.setRequestProperty("User-Agent", "server");

            return readJsonStream(con.getInputStream(), companies.get(0).getName());
        } catch (MalformedURLException ex) {
            throw new APIErrorException("Something wrong with the URL");
        } catch (IOException ex) {
            throw new APIErrorException(ex.getMessage());
        }
    }

    private List<CarDTO> readJsonStream(InputStream in, String company) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readMessagesArray(reader, company);
        } finally {
            reader.close();
        }
    }

    private List<CarDTO> readMessagesArray(JsonReader reader, String company) throws IOException {
        List<CarDTO> cars = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            cars.add(readMessageCar(reader, company));
        }
        reader.endArray();
        return cars;
    }

    private CarDTO readMessageObjectCar(JsonReader reader, String company) throws IOException {
        CarDTO car = null;
        //reader.beginObject();
        if (reader.hasNext()) {
            car = readMessageCar(reader, company);
        }
        //reader.endObject();
        return car;
    }

    private CarDTO readMessageCar(JsonReader reader, String company) throws IOException {
        String regno = null;
        double price = 0;
        String manufactor = null;
        String model = null;
        String type = null;
        int releaseYear = 0;
        int drivingDist = 0;
        int seats = 0;
        String drive = null;
        String fuelType = null;
        String longitude = null;
        String latitude = null;
        String address = null;
        String country = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "regno":
                    regno = reader.nextString();
                    break;
                case "price":
                    price = reader.nextDouble();
                    break;
                case "manufactor":
                    manufactor = reader.nextString();
                    break;
                case "model":
                    model = reader.nextString();
                    break;
                case "type":
                    type = reader.nextString();
                    break;
                case "releaseYear":
                    releaseYear = reader.nextInt();
                    break;
                case "drivingDist":
                    drivingDist = reader.nextInt();
                    break;
                case "seats":
                    seats = reader.nextInt();
                    break;
                case "drive":
                    drive = reader.nextString();
                    break;
                case "fuelType":
                    fuelType = reader.nextString();
                    break;
                case "longitude":
                    longitude = reader.nextString();
                    break;
                case "latitude":
                    latitude = reader.nextString();
                    break;
                case "address":
                    address = reader.nextString();
                    break;
                case "country":
                    country = reader.nextString();
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return new CarDTO(regno, price, manufactor, model, type, releaseYear, drivingDist, seats, drive, fuelType, longitude, latitude, address, country, company);
    }

    private BookinginformationDTO readMessageObjectBooking(JsonReader reader, String company) throws IOException {
        BookinginformationDTO dto = null;
        //reader.beginObject();
        if (reader.hasNext()) {
            dto = readMessageBooking(reader, company);
        }
        //reader.endObject();
        return dto;
    }

    private BookinginformationDTO readMessageBooking(JsonReader reader, String company) throws IOException {

        String startPeriod;
        String endPeriod;
        String created;
        double price;
        CarDTO car;
        String userName;

//        reader.beginObject();
//        while (reader.hasNext()) {
//            String name = reader.nextName();
//            switch (name) {
//                case "startPeriod":
//                    regno = reader.nextString();
//                    break;
//                case "price":
//                    price = reader.nextDouble();
//                    break;
//                case "manufactor":
//                    manufactor = reader.nextString();
//                    break;
//                case "model":
//                    model = reader.nextString();
//                    break;
//                case "type":
//                    type = reader.nextString();
//                    break;
//                case "releaseYear":
//                    releaseYear = reader.nextInt();
//                    break;
//                case "drivingDist":
//                    drivingDist = reader.nextInt();
//                    break;
//                case "seats":
//                    seats = reader.nextInt();
//                    break;
//                case "drive":
//                    drive = reader.nextString();
//                    break;
//                case "fuelType":
//                    fuelType = reader.nextString();
//                    break;
//                case "longitude":
//                    longitude = reader.nextString();
//                    break;
//                case "latitude":
//                    latitude = reader.nextString();
//                    break;
//                case "address":
//                    address = reader.nextString();
//                    break;
//                case "country":
//                    country = reader.nextString();
//                    break;
//                default:
//                    reader.skipValue();
//                    break;
//            }
//        }
//        reader.endObject();

        return null;
    }
}
