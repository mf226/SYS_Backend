package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Fen
 */
@Entity
@Table(name = "bookinginformation")
@NamedQueries({
    @NamedQuery(name = "Bookinginformation.findAll", query = "SELECT b FROM Bookinginformation b")
    , @NamedQuery(name = "Bookinginformation.findById", query = "SELECT b FROM Bookinginformation b WHERE b.id = :id")
    , @NamedQuery(name = "Bookinginformation.findByStartPeriod", query = "SELECT b FROM Bookinginformation b WHERE b.startPeriod = :startPeriod")
    , @NamedQuery(name = "Bookinginformation.findByEndPeriod", query = "SELECT b FROM Bookinginformation b WHERE b.endPeriod = :endPeriod")
    , @NamedQuery(name = "Bookinginformation.findByCreated", query = "SELECT b FROM Bookinginformation b WHERE b.created = :created")
    , @NamedQuery(name = "Bookinginformation.findByPrice", query = "SELECT b FROM Bookinginformation b WHERE b.price = :price")
    , @NamedQuery(name = "Bookinginformation.findByCarRefUrl", query = "SELECT b FROM Bookinginformation b WHERE b.carRefUrl = :carRefUrl")
    , @NamedQuery(name = "Bookinginformation.findByCarRegNo", query = "SELECT b FROM Bookinginformation b WHERE b.carRegNo = :carRegNo")
    , @NamedQuery(name = "Bookinginformation.findByManufactor", query = "SELECT b FROM Bookinginformation b WHERE b.manufactor = :manufactor")
    , @NamedQuery(name = "Bookinginformation.findByModel", query = "SELECT b FROM Bookinginformation b WHERE b.model = :model")
    , @NamedQuery(name = "Bookinginformation.findByType", query = "SELECT b FROM Bookinginformation b WHERE b.type = :type")
    , @NamedQuery(name = "Bookinginformation.findByReleaseYear", query = "SELECT b FROM Bookinginformation b WHERE b.releaseYear = :releaseYear")
    , @NamedQuery(name = "Bookinginformation.findByDrivingDist", query = "SELECT b FROM Bookinginformation b WHERE b.drivingDist = :drivingDist")
    , @NamedQuery(name = "Bookinginformation.findBySeats", query = "SELECT b FROM Bookinginformation b WHERE b.seats = :seats")
    , @NamedQuery(name = "Bookinginformation.findByDrive", query = "SELECT b FROM Bookinginformation b WHERE b.drive = :drive")
    , @NamedQuery(name = "Bookinginformation.findByFuelType", query = "SELECT b FROM Bookinginformation b WHERE b.fuelType = :fuelType")})
public class Bookinginformation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "startPeriod", nullable = false, length = 45)
    private Date startPeriod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "endPeriod", nullable = false, length = 45)
    private Date endPeriod;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date created;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price", nullable = false)
    private double price;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "carRegNo", nullable = false, length = 45)
    private String carRegNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "company", nullable = false, length = 45)
    private String company;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "manufactor", nullable = false, length = 45)
    private String manufactor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "model", nullable = false, length = 45)
    private String model;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "type", nullable = false, length = 45)
    private String type;
    @Basic(optional = false)
    @NotNull
    @Column(name = "releaseYear", nullable = false)
    private int releaseYear;
    @Basic(optional = false)
    @NotNull
    @Column(name = "drivingDist", nullable = false)
    private int drivingDist;
    @Basic(optional = false)
    @NotNull
    @Column(name = "seats", nullable = false)
    private int seats;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "drive", nullable = false, length = 45)
    private String drive;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "fuelType", nullable = false, length = 45)
    private String fuelType;
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private User user;

    public Bookinginformation() {
    }

    public Bookinginformation(Integer id) {
        this.id = id;
    }

    public Bookinginformation(Integer id, Date startPeriod, Date endPeriod, Date created, double price, String company, String carRegNo, String manufactor, String model, String type, int releaseYear, int drivingDist, int seats, String drive, String fuelType) {
        this.id = id;
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
        this.created = created;
        this.price = price;
        this.carRegNo = carRegNo;
        this.company = company;
        this.manufactor = manufactor;
        this.model = model;
        this.type = type;
        this.releaseYear = releaseYear;
        this.drivingDist = drivingDist;
        this.seats = seats;
        this.drive = drive;
        this.fuelType = fuelType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(Date startPeriod) {
        this.startPeriod = startPeriod;
    }

    public Date getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(Date endPeriod) {
        this.endPeriod = endPeriod;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCarRegNo() {
        return carRegNo;
    }

    public void setCarRegNo(String carRegNo) {
        this.carRegNo = carRegNo;
    }

    public String getManufactor() {
        return manufactor;
    }

    public void setManufactor(String manufactor) {
        this.manufactor = manufactor;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getDrivingDist() {
        return drivingDist;
    }

    public void setDrivingDist(int drivingDist) {
        this.drivingDist = drivingDist;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getDrive() {
        return drive;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bookinginformation)) {
            return false;
        }
        Bookinginformation other = (Bookinginformation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Bookinginformation[ id=" + id + " ]";
    }
    
}
