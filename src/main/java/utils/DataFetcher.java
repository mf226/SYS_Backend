package utils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import dto.CarDTO;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Fen
 */
public class DataFetcher {

    class FetchCars implements Callable<String> {

        URL url;

        FetchCars(URL url) {
            this.url = url;
        }

        @Override
        public String call() throws Exception {
            return "gg";
        }
    }

//    public List<String> getSeveralSwappiData() throws Exception {
//        ExecutorService executor = Executors.newCachedThreadPool();
//        List<Future<String>> futures = new ArrayList<>();
//        for(int i = 1; i <= 5; i++) {
//            FetchSWAPI fs = new FetchSWAPI(i);
//            Future future = executor.submit(fs);
//            futures.add(future);
//        }
//        List<String> results = new ArrayList();
//        for(Future<String> f: futures) {
//            String status =  f.get(5, TimeUnit.SECONDS);
//            results.add(status);
//        }
//        executor.shutdown();
//        return results;
//    }
    public List<CarDTO> getAllCarsFromOneAPI(URL url) throws MalformedURLException, IOException {
        //URL url = new URL("https://swapi.co/api/people/" + id);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        con.setRequestProperty("User-Agent", "server");

        return readJsonStream(con.getInputStream());
//        Gson gson = new Gson();
//        JsonReader newJsonReader = gson.newJsonReader(new InputStreamReader(con.getInputStream()));
//        //Scanner scan = new Scanner(con.getInputStream());
//        //StringBuilder sb = new StringBuilder();
//        String jsonStr = null;
//        ArrayList<CarDTO> jsonarray = new ArrayList<>();
//        if (newJsonReader.hasNext()) {
//            //jsonStr = scan.nextLine();
//            newJsonReader.beginArray();
//            //jsonarray.add(newJsonReader.)
//        }
//        System.out.println(jsonStr);
//        //scan.close();
//        return jsonStr;
    }

    public List<CarDTO> readJsonStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readMessagesArray(reader);
        } finally {
            reader.close();
        }
    }

    public List<CarDTO> readMessagesArray(JsonReader reader) throws IOException {
        List<CarDTO> cars = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            cars.add(readMessage(reader));
        }
        reader.endArray();
        return cars;
    }

    public CarDTO readMessage(JsonReader reader) throws IOException {
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
        return new CarDTO(regno, price, manufactor, model, type, releaseYear, drivingDist, seats, drive, fuelType, longitude, latitude, address, country);
    }

}
