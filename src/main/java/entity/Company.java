package entity;

import java.net.URL;

/**
 *
 * @author Fen
 */
public class Company {

    private String name;
    private URL url;

    public Company(String name, URL url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

}
