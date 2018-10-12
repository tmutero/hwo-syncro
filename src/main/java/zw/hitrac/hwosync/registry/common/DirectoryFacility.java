package zw.hitrac.hwosync.registry.common;

public class DirectoryFacility {

  private String name;
  private String facilityId;
  private String uuid;
  private String description;

  public DirectoryFacility () {
  }

  public DirectoryFacility (String facilityId) {
    this.facilityId = facilityId;
  }

  public String getName () {
    return name;
  }

  public void setName (String name) {
    this.name = name;
  }

  public String getFacilityId () {
    return facilityId;
  }

  public void setFacilityId (String facilityId) {
    this.facilityId = facilityId;
  }

  public String getUuid () {
    return uuid;
  }

  public void setUuid (String uuid) {
    this.uuid = uuid;
  }

  public String getDescription () {
    return description;
  }

  public void setDescription (String description) {
    this.description = description;
  }


}
