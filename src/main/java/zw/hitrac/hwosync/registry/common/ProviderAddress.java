package zw.hitrac.hwosync.registry.common;

public class ProviderAddress {

  private String type;
  private String address1;
  private String address2;
  private String number;
  private String street;
  private String city;
  private String country;
  private String addressId;
  private Double longitudes;
  private Double latitudes;

  public Double getLongitudes () {
    return longitudes;
  }

  public void setLongitudes (Double longitudes) {
    this.longitudes = longitudes;
  }

  public Double getLatitudes () {
    return latitudes;
  }

  public void setLatitudes (Double latitudes) {
    this.latitudes = latitudes;
  }

  public String getType () {
    return type;
  }

  public void setType (String type) {
    this.type = type;
  }

  public String getAddress1 () {
    return address1;
  }

  public void setAddress1 (String address1) {
    this.address1 = address1;
  }

  public String getAddress2 () {
    return address2;
  }

  public void setAddress2 (String address2) {
    this.address2 = address2;
  }

  public String getNumber () {
    return number;
  }

  public void setNumber (String number) {
    this.number = number;
  }

  public String getStreet () {
    return street;
  }

  public void setStreet (String street) {
    this.street = street;
  }

  public String getCity () {
    return city;
  }

  public void setCity (String city) {
    this.city = city;
  }

  public String getCountry () {
    return country;
  }

  public void setCountry (String country) {
    this.country = country;
  }

  public String getAddressId () {
    return addressId;
  }

  public void setAddressId (String addressId) {
    this.addressId = addressId;
  }

}
