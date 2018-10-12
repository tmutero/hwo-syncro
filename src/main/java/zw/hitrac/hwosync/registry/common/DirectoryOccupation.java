package zw.hitrac.hwosync.registry.common;

public class DirectoryOccupation {

  private String name;
  private String occupationId;
  private String uuid;
  private String description;

  public DirectoryOccupation () {
  }

  public DirectoryOccupation (String occupationId) {
    this.occupationId = occupationId;
  }

  public String getName () {
    return name;
  }

  public void setName (String name) {
    this.name = name;
  }

  public String getOccupationId () {
    return occupationId;
  }

  public void setOccupationId (String occupationId) {
    this.occupationId = occupationId;
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
