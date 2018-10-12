package zw.hitrac.hwosync.registry.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HpaFacility {

  private String name;
  private HpaDistrict hpaDistrict;
  private String hpaId;
  private String hpaUuid;
  private String latitude;
  private String longitude;
  private String facilityType;
  private String uuid;
  private List<HpaFacilityContact> hpaFacilityContacts = new ArrayList<>();
  private List<HpaFacilityAddress> hpaFacilityAddresses = new ArrayList<>();
  private HpaPractitioner hpaPractitioner;
  private String registrationNumber;
  private Date renewalDate;
  private Date endDate;
  private String status;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public HpaDistrict getHpaDistrict() {
    return hpaDistrict;
  }

  public void setHpaDistrict(HpaDistrict hpaDistrict) {
    this.hpaDistrict = hpaDistrict;
  }

  public String getHpaId() {
    return hpaId;
  }

  public void setHpaId(String hpaId) {
    this.hpaId = hpaId;
  }

  public String getHpaUuid() {
    return hpaUuid;
  }

  public void setHpaUuid(String hpaUuid) {
    this.hpaUuid = hpaUuid;
  }

  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public String getFacilityType() {
    return facilityType;
  }

  public void setFacilityType(String facilityType) {
    this.facilityType = facilityType;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public List<HpaFacilityContact> getHpaFacilityContacts() {
    return hpaFacilityContacts;
  }

  public void setHpaFacilityContacts(List<HpaFacilityContact> hpaFacilityContacts) {
    this.hpaFacilityContacts = hpaFacilityContacts;
  }

  public List<HpaFacilityAddress> getHpaFacilityAddresses() {
    return hpaFacilityAddresses;
  }

  public void setHpaFacilityAddresses(List<HpaFacilityAddress> hpaFacilityAddresses) {
    this.hpaFacilityAddresses = hpaFacilityAddresses;
  }

  public HpaPractitioner getHpaPractitioner() {
    return hpaPractitioner;
  }

  public void setHpaPractitioner(HpaPractitioner hpaPractitioner) {
    this.hpaPractitioner = hpaPractitioner;
  }

  public String getRegistrationNumber() {
    return registrationNumber;
  }

  public void setRegistrationNumber(String registrationNumber) {
    this.registrationNumber = registrationNumber;
  }

  public Date getRenewalDate() {
    return renewalDate;
  }

  public void setRenewalDate(Date renewalDate) {
    this.renewalDate = renewalDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
