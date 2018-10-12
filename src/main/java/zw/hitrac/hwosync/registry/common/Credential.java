package zw.hitrac.hwosync.registry.common;

import java.util.Date;

public class Credential {

  private String number;
  private Date issueDate;
  private Date renewalDate;
  private String credentialId;
  private Boolean inActive;
  private DirectoryOccupation directoryOccupation;
  private String idNumber;
  private Date endDate;
  private Date startDate;

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public String getNumber () {
    return number;
  }

  public void setNumber (String number) {
    this.number = number;
  }

  public Date getIssueDate () {
    return issueDate;
  }

  public void setIssueDate (Date issueDate) {
    this.issueDate = issueDate;
  }

  public Date getRenewalDate () {
    return renewalDate;
  }

  public void setRenewalDate (Date renewalDate) {
    this.renewalDate = renewalDate;
  }

  public String getCredentialId () {
    return credentialId;
  }

  public void setCredentialId (String credentialId) {
    this.credentialId = credentialId;
  }

  public Boolean getInActive () {
    return inActive;
  }

  public void setInActive (Boolean inActive) {
    this.inActive = inActive;
  }

  public DirectoryOccupation getDirectoryOccupation () {
    return directoryOccupation;
  }

  public void setDirectoryOccupation (DirectoryOccupation directoryOccupation) {
    this.directoryOccupation = directoryOccupation;
  }

  public String getIdNumber () {
    return idNumber;
  }

  public void setIdNumber (String idNumber) {
    this.idNumber = idNumber;
  }
}
