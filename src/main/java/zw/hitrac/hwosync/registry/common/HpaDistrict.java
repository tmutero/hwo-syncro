package zw.hitrac.hwosync.registry.common;

public class HpaDistrict {

  private String name;
  private String hpaProvince;
  private String hpaId;
  private String hpaUuid;

  public String getName () {
    return name;
  }

  public void setName (String name) {
    this.name = name;
  }


  public String getHpaId () {
    return hpaId;
  }

  public void setHpaId (String hpaId) {
    this.hpaId = hpaId;
  }

  public String getHpaUuid () {
    return hpaUuid;
  }

  public void setHpaUuid (String hpaUuid) {
    this.hpaUuid = hpaUuid;
  }

  public String getHpaProvince() {
    return hpaProvince;
  }

  public void setHpaProvince(String hpaProvince) {
    this.hpaProvince = hpaProvince;
  }
}
