package zw.hitrac.hwosync.registry.common;

import java.util.Date;

public class Employment {

  private Date startDate;
  private Date endDate;
  private String employmentId;
  private Boolean inActive;
  private DirectoryFacility directoryFacility;
  private DirectoryOccupation directoryOccupation;
  private String employmentType;
  private String employer;

  public Date getStartDate () {
    return startDate;
  }

  public void setStartDate (Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate () {
    return endDate;
  }

  public void setEndDate (Date endDate) {
    this.endDate = endDate;
  }

  public String getEmploymentId () {
    return employmentId;
  }

  public void setEmploymentId (String employmentId) {
    this.employmentId = employmentId;
  }

  public Boolean getInActive () {
    return inActive;
  }

  public void setInActive (Boolean inActive) {
    this.inActive = inActive;
  }

  public DirectoryFacility getDirectoryFacility () {
    return directoryFacility;
  }

  public void setDirectoryFacility (DirectoryFacility directoryFacility) {
    this.directoryFacility = directoryFacility;
  }

  public DirectoryOccupation getDirectoryOccupation () {
    return directoryOccupation;
  }

  public void setDirectoryOccupation (DirectoryOccupation directoryOccupation) {
    this.directoryOccupation = directoryOccupation;
  }

  public String getEmploymentType() {
    return employmentType;
  }

  public void setEmploymentType(String employmentType) {
    this.employmentType = employmentType;
  }

  public String getEmployer() {
    return employer;
  }

  public void setEmployer(String employer) {
    this.employer = employer;
  }
}
