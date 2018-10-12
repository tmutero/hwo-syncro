package zw.hitrac.hwosync.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "registry_credentials")
public class RegistryCredentials implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotEmpty
  @Column(name = "NAME", unique = true, nullable = false)
  private String name;

  @NotEmpty
  @Column(name = "URL", nullable = false)
  private String url;

  @NotEmpty
  @Column(name = "SOURCE_URL", nullable = false)
  private String sourceUrl;

  @NotEmpty
  @Column(name = "DB_NAME", nullable = false)
  private String dbName;

  @NotEmpty
  @Column(name = "DB_USERNAME", nullable = false)
  private String dbUserName;

  @NotEmpty
  @Column(name = "DB_PASSWORD", nullable = false)
  private String dbPassword;

  @Column(name = "IS_FACILITY_REGISTRY")
  private boolean facilityRegistry;

  @Column(name = "IS_GENERIC_COUNCIL")
  private boolean council;

  @Column(name = "IS_MINISTRY")
  private boolean ministry;

  public String getSourceUrl () {
    return sourceUrl;
  }

  public void setSourceUrl (String sourceUrl) {
    this.sourceUrl = sourceUrl;
  }

  public boolean isFacilityRegistry () {
    return facilityRegistry;
  }

  public void setFacilityRegistry (boolean facilityRegistry) {
    this.facilityRegistry = facilityRegistry;
  }

  public boolean isCouncil () {
    return council;
  }

  public void setCouncil (boolean council) {
    this.council = council;
  }

  public boolean isMinistry () {
    return ministry;
  }

  public void setMinistry (boolean ministry) {
    this.ministry = ministry;
  }

  public Integer getId () {
    return id;
  }

  public void setId (Integer id) {
    this.id = id;
  }

  public String getName () {
    return name;
  }

  public void setName (String name) {
    this.name = name;
  }

  public String getUrl () {
    return url;
  }

  public void setUrl (String url) {
    this.url = url;
  }

  public String getDbName () {
    return dbName;
  }

  public void setDbName (String dbName) {
    this.dbName = dbName;
  }

  public String getDbUserName () {
    return dbUserName;
  }

  public void setDbUserName (String dbUserName) {
    this.dbUserName = dbUserName;
  }

  public String getDbPassword () {
    return dbPassword;
  }

  public void setDbPassword (String dbPassword) {
    this.dbPassword = dbPassword;
  }

  @Override
  public int hashCode () {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  @Override
  public boolean equals (Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (!(obj instanceof RegistryCredentials))
      return false;
    RegistryCredentials other = (RegistryCredentials) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    return true;
  }

  /*
   * DO-NOT-INCLUDE passwords in toString function.
   */
  @Override
  public String toString () {
    return "User [id=" + id + ", name=" + name + " ]";
  }


}
