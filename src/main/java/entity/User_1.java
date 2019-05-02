package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Fen
 */
@Entity
@Table(name = "user", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"driverLicenseNumber"})
    , @UniqueConstraint(columnNames = {"email"})
    , @UniqueConstraint(columnNames = {"id"})})
@NamedQueries({
    @NamedQuery(name = "User_1.findAll", query = "SELECT u FROM User_1 u")
    , @NamedQuery(name = "User_1.findById", query = "SELECT u FROM User_1 u WHERE u.id = :id")
    , @NamedQuery(name = "User_1.findByFirstName", query = "SELECT u FROM User_1 u WHERE u.firstName = :firstName")
    , @NamedQuery(name = "User_1.findByLastName", query = "SELECT u FROM User_1 u WHERE u.lastName = :lastName")
    , @NamedQuery(name = "User_1.findByEmail", query = "SELECT u FROM User_1 u WHERE u.email = :email")
    , @NamedQuery(name = "User_1.findByPassword", query = "SELECT u FROM User_1 u WHERE u.password = :password")
    , @NamedQuery(name = "User_1.findByBirth", query = "SELECT u FROM User_1 u WHERE u.birth = :birth")
    , @NamedQuery(name = "User_1.findByGender", query = "SELECT u FROM User_1 u WHERE u.gender = :gender")
    , @NamedQuery(name = "User_1.findByPhone", query = "SELECT u FROM User_1 u WHERE u.phone = :phone")
    , @NamedQuery(name = "User_1.findByStatus", query = "SELECT u FROM User_1 u WHERE u.status = :status")
    , @NamedQuery(name = "User_1.findByDriverLicenseNumber", query = "SELECT u FROM User_1 u WHERE u.driverLicenseNumber = :driverLicenseNumber")})
public class User_1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "firstName", nullable = false, length = 45)
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "lastName", nullable = false, length = 45)
    private String lastName;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "email", nullable = false, length = 45)
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "password", nullable = false, length = 45)
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "birth", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birth;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "gender", nullable = false, length = 6)
    private String gender;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "phone", nullable = false, length = 45)
    private String phone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "status", nullable = false, length = 11)
    private String status;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "driverLicenseNumber", nullable = false, length = 45)
    private String driverLicenseNumber;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<Car> carCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<Rating> ratingCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<Bookinginformation> bookinginformationCollection;

    public User_1() {
    }

    public User_1(Integer id) {
        this.id = id;
    }

    public User_1(Integer id, String firstName, String lastName, String email, String password, Date birth, String gender, String phone, String status, String driverLicenseNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birth = birth;
        this.gender = gender;
        this.phone = phone;
        this.status = status;
        this.driverLicenseNumber = driverLicenseNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

    public void setDriverLicenseNumber(String driverLicenseNumber) {
        this.driverLicenseNumber = driverLicenseNumber;
    }

    public Collection<Car> getCarCollection() {
        return carCollection;
    }

    public void setCarCollection(Collection<Car> carCollection) {
        this.carCollection = carCollection;
    }

    public Collection<Rating> getRatingCollection() {
        return ratingCollection;
    }

    public void setRatingCollection(Collection<Rating> ratingCollection) {
        this.ratingCollection = ratingCollection;
    }

    public Collection<Bookinginformation> getBookinginformationCollection() {
        return bookinginformationCollection;
    }

    public void setBookinginformationCollection(Collection<Bookinginformation> bookinginformationCollection) {
        this.bookinginformationCollection = bookinginformationCollection;
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
        if (!(object instanceof User_1)) {
            return false;
        }
        User_1 other = (User_1) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.User_1[ id=" + id + " ]";
    }
    
}
