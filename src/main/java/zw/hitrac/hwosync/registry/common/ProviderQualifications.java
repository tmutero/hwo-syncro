package zw.hitrac.hwosync.registry.common;

import java.util.Date;

public class ProviderQualifications {

  private String name;
  private Date dateAwarded;
  private String trainingInstitution;
  private String awardingInstitution;
  private String registrantId;
  private String qualificationId;

  public String getTrainingInstitution () {
    return trainingInstitution;
  }

  public void setTrainingInstitution (String trainingInstitution) {
    this.trainingInstitution = trainingInstitution;
  }


  public Date getDateAwarded () {
    return dateAwarded;
  }

  public void setDateAwarded (Date dateAwarded) {
    this.dateAwarded = dateAwarded;
  }

  public String getName () {
    return name;
  }

  public void setName (String name) {
    this.name = name;
  }

  public String getAwardingInstitution () {
    return awardingInstitution;
  }

  public void setAwardingInstitution (String awardingInstitution) {
    this.awardingInstitution = awardingInstitution;
  }

  public String getRegistrantId () {
    return registrantId;
  }

  public void setRegistrantId (String registrantId) {
    this.registrantId = registrantId;
  }

  public String getQualificationId () {
    return qualificationId;
  }

  public void setQualificationId (String qualificationId) {
    this.qualificationId = qualificationId;
  }


}
