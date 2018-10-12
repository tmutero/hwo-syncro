package zw.hitrac.hwosync.registry.common;

public class HpaFacilityContact {

  private String type;
  private String contactDetail;
  private String HpaFacilityContactId;

  public String getType () {
    return type;
  }

  public void setType (String type) {
    this.type = type;
  }

  public String getContactDetail () {
    return contactDetail;
  }

  public void setContactDetail (String contactDetail) {
    this.contactDetail = contactDetail;
  }

  public String getHpaFacilityContactId () {
    return HpaFacilityContactId;
  }

  public void setHpaFacilityContactId (String HpaFacilityContactId) {
    this.HpaFacilityContactId = HpaFacilityContactId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    HpaFacilityContact that = (HpaFacilityContact) o;

    return HpaFacilityContactId != null ? HpaFacilityContactId.equals(that.HpaFacilityContactId) : that.HpaFacilityContactId == null;

  }

  @Override
  public int hashCode() {
    return HpaFacilityContactId != null ? HpaFacilityContactId.hashCode() : 0;
  }
}
