package zw.hitrac.hwosync.registry.common;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Provider {

  private String firstName;
  private String lastName;
  private String maidenName;
  private String middleName;
  private Date dateOfBirth;
  private String gender;
  private String nationalId;
  private String providerId;
  private String uuid;
  private String providerNumber;
  private List<Credential> credentials = new ArrayList<>();
  private List<Employment> employments = new ArrayList<>();
  private List<ProviderAddress> providerAddresses = new ArrayList<>();
  private List<ProviderContact> providerContacts = new ArrayList<>();
  private List<ProviderQualifications> providerQualifications = new ArrayList<>();

  public static String getFormattedIDNumber (String value) {
    if(StringUtils.isEmpty(value)){
      return "";
    }
    else{
        return value.replace("\\s+", "")
                .replace("-", "").trim().toUpperCase();
        }

  }

  public List<ProviderQualifications> getProviderQualifications () {
    return providerQualifications;
  }

  public void setProviderQualifications (List<ProviderQualifications> providerQualifications) {
    this.providerQualifications = providerQualifications;
  }

  public String getFirstName () {
    return firstName;
  }

  public void setFirstName (String firstName) {
    this.firstName = firstName;
  }

  public String getLastName () {
    return lastName;
  }

  public void setLastName (String lastName) {
    this.lastName = lastName;
  }

  public Date getDateOfBirth () {
    return dateOfBirth;
  }

  public void setDateOfBirth (Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getGender () {
    return gender;
  }

  public void setGender (String gender) {
    this.gender = gender;
  }

  public String getNationalId () {
    return nationalId;
  }

  public void setNationalId (String nationalId) {
    this.nationalId = nationalId;
  }

  public String getProviderId () {
    return providerId;
  }

  public void setProviderId (String providerId) {
    this.providerId = providerId;
  }

  public List<Credential> getCredentials () {
    return credentials;
  }

  public void setCredentials (List<Credential> credentials) {
    this.credentials = credentials;
  }

  public List<Employment> getEmployments () {
    return employments;
  }

  public void setEmployments (List<Employment> employments) {
    this.employments = employments;
  }

  public void addEmployment (Employment employment) {
    this.getEmployments().add(employment);
  }

  public void addCredential (Credential credential) {
    this.credentials.add(credential);
  }

  public String getUuid () {
    return uuid;
  }

  public void setUuid (String uuid) {
    this.uuid = uuid;
  }

  public String getProviderNumber () {
    return providerNumber;
  }

  public void setProviderNumber (String providerNumber) {
    this.providerNumber = providerNumber;
  }

  public List<ProviderAddress> getProviderAddresses () {
    return providerAddresses;
  }

  public void setProviderAddresses (List<ProviderAddress> providerAddresses) {
    this.providerAddresses = providerAddresses;
  }

  public List<ProviderContact> getProviderContacts () {
    return providerContacts;
  }

  public void setProviderContacts (List<ProviderContact> providerContacts) {
    this.providerContacts = providerContacts;
  }

  public String getMaidenName () {
    return maidenName;
  }

  public void setMaidenName (String maidenName) {
    this.maidenName = maidenName;
  }

  public String getMiddleName () {
    return middleName;
  }

  public void setMiddleName (String middleName) {
    this.middleName = middleName;
  }

  public void setProviderQualifications () {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}
