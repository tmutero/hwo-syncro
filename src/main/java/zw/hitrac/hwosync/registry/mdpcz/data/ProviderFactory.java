package zw.hitrac.hwosync.registry.mdpcz.data;



import org.joda.time.DateTime;
import zw.hitrac.hwosync.model.RegistryCredentials;
import zw.hitrac.hwosync.registry.common.Credential;
import zw.hitrac.hwosync.registry.common.DirectoryOccupation;
import zw.hitrac.hwosync.registry.common.HwoClientDataSource;
import zw.hitrac.hwosync.registry.common.Provider;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProviderFactory {

  public static List<Provider> getProviders (RegistryCredentials registryCredentials) throws SQLException {
    List<Provider> providers = new ArrayList<>();

    try (Connection connection = zw.hitrac.hwosync.registry.common.HwoClientDataSource.getDataSource(registryCredentials).getConnection()) {
      String selectSql = "select p.id,p.firstName,p.lastName,p.middleName,p.dateOfBirth,p.idNumber,p.registrationNumber,g.name as gender from practitioner p,gender g where p.gender_id=g.id";
      try (Statement statement = connection.createStatement()) {
        ResultSet rs = statement.executeQuery(selectSql);
        while (rs.next()) {
          Provider provider = new Provider();
          provider.setFirstName(rs.getString("firstName"));
          provider.setLastName(rs.getString("lastName"));
          provider.setMiddleName(rs.getString("middleName"));
          provider.setDateOfBirth(rs.getDate("dateOfBirth"));
          provider.setNationalId(rs.getString("idNumber"));
          provider.setGender(rs.getString("gender"));
          provider.setProviderId(String.valueOf(rs.getLong("id")));
          provider.setProviderNumber(rs.getString("registrationNumber"));
          providers.add(provider);
        }
      }
    }

    for (Provider provider : providers) {
      provider.setCredentials(getCredentials(provider.getProviderId(), provider.getProviderNumber(), registryCredentials));
      provider.setProviderAddresses(ProviderAddressFactory.getAddresses(provider.getProviderId(), registryCredentials));
      provider.setProviderContacts(ProviderContactFactory.getProviderContacts(provider.getProviderId(), registryCredentials));
      provider.setProviderQualifications(ProviderQualificationFactory.getQualifications(provider.getProviderId(), registryCredentials));

    }

    return providers;
  }

  private static List<Credential> getCredentials (String providerId, String providerNumber, RegistryCredentials registryCredentials) throws SQLException {

    Date startDate = new DateTime().dayOfYear().withMinimumValue().withTimeAtStartOfDay().toDate();
    Date endDate = new DateTime().dayOfYear().withMaximumValue().withTimeAtStartOfDay().toDate();

    List<Credential> credentials = new ArrayList<>();

    try (Connection connection = HwoClientDataSource.getDataSource(registryCredentials).getConnection()) {
      String selectSql = "select rn.id,rn.renewalDate,rg.practitionerType_id,cp.endDate from  renewal rn,council_period cp,registration rg,practitioner_type pt where rn.councilPeriod_id=cp.id  and rg.id=rn.registration_id and pt.id=rg.practitionerType_id and cp.councilPeriodType='REGISTRATION' and rn.renewalStatus='ACTIVE' and rg.practitioner_id=? and cp.startDate >= ? and cp.endDate <=?";
      try (PreparedStatement statement = connection.prepareStatement(selectSql)) {
        statement.setLong(1, Long.valueOf(providerId));
        statement.setDate(2, new java.sql.Date(startDate.getTime()));
        statement.setDate(3, new java.sql.Date(endDate.getTime()));
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
          Credential credential = new Credential();
          credential.setCredentialId(rs.getString("id"));
          credential.setDirectoryOccupation(new DirectoryOccupation(rs.getString("practitionerType_id")));
          credential.setNumber(providerNumber);
          credential.setIssueDate(rs.getDate("renewalDate"));
          credential.setRenewalDate(rs.getDate("endDate"));
          credentials.add(credential);
        }
      }
    }

    return credentials;
  }

}
