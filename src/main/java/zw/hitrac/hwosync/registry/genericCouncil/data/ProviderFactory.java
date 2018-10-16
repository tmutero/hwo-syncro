package zw.hitrac.hwosync.registry.genericCouncil.data;


import zw.hitrac.hwosync.model.RegistryCredentials;
import zw.hitrac.hwosync.registry.common.Credential;
import zw.hitrac.hwosync.registry.common.DirectoryOccupation;
import zw.hitrac.hwosync.registry.common.HwoClientDataSource;
import zw.hitrac.hwosync.registry.common.Provider;
import zw.hitrac.hwosync.registry.genericCouncil.Duration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProviderFactory {


  public static List<Provider> getProviders (RegistryCredentials registryCredentials) throws SQLException {
    List<Provider> providers = new ArrayList<>();

    try (Connection connection = HwoClientDataSource.getDataSource(registryCredentials).getConnection()) {
      String selectSql = "select r.id,r.gender,r.birthDate,r.firstname,r.lastname,r.maidenname,r.middlename,r.uid,r.idNumber,r.registrationNumber from registrant r where 1";
      try (Statement statement = connection.createStatement()) {

        ResultSet rs = statement.executeQuery(selectSql);
        while (rs.next()) {
          Provider provider = new Provider();
          provider.setFirstName(rs.getString("firstname"));
          provider.setLastName(rs.getString("lastname"));
          provider.setMiddleName(rs.getString("middlename"));
          provider.setMaidenName(rs.getString("maidenname"));
          provider.setDateOfBirth(rs.getDate("birthDate"));
          provider.setNationalId(rs.getString("idNumber"));
          provider.setGender(rs.getString("gender"));
          provider.setProviderId(String.valueOf(rs.getLong("id")));
          provider.setUuid(rs.getString("uid"));
          provider.setProviderNumber(rs.getString("registrationNumber"));


          providers.add(provider);

        }
      } catch (Exception e) {
        e.getMessage();
        System.out.print("------------------" +e.getMessage());
        e.printStackTrace();
      }
    }
    Integer employmentSizeTotal = 0;
    for (Provider provider : providers) {

      provider.setCredentials(getCredentials(provider.getProviderId(), provider.getProviderNumber(), provider.getNationalId(), registryCredentials));
      provider.setProviderAddresses(ProviderAddressFactory.getAddresses(provider.getProviderId(), registryCredentials));
      provider.setProviderContacts(ProviderContactFactory.getProviderContacts(provider.getProviderId(), registryCredentials));
      provider.setEmployments(EmployerFactory.getEmploymentList(provider.getProviderId(),registryCredentials));



      provider.setProviderQualifications(ProviderQualificationsFactory.getQualifications(provider.getProviderId(), registryCredentials));

    }




    return providers;
  }

  private static List<Credential> getCredentials (String providerId, String providerNumber, String idNumber, RegistryCredentials registryCredentials) throws SQLException {
    Duration duration = getDuration(providerId, registryCredentials);
    List<Credential> credentials = new ArrayList<>();
    if (duration != null)
        try (Connection connection = HwoClientDataSource.getDataSource(registryCredentials).getConnection()) {
            String selectSql = "select q.course_id,q.id from registrantqualification q where q.registrant_id=" + providerId;
            try (Statement statement = connection.createStatement()) {
                ResultSet rs = statement.executeQuery(selectSql);
                while (rs.next()) {
                    Credential credential = new Credential();
                    credential.setCredentialId(rs.getString("id"));

                    //credential.setDirectoryOccupation(new DirectoryOccupation(rs.getString("course_id")));

                    DirectoryOccupation dO = new DirectoryOccupation();
                    String course_id = rs.getString("course_id");
                    dO.setOccupationId(course_id);

                    credential.setNumber(providerNumber);
                    credential.setIssueDate(duration.getStartDate());
                    credential.setRenewalDate(duration.getEndDate());
                   credential.setDirectoryOccupation(dO);


                    credential.setIdNumber(Provider.getFormattedIDNumber(idNumber));
                    credentials.add(credential);


                }
            }
        }

    return credentials;
  }





  private static Duration getDuration (String providerId, RegistryCredentials registryCredentials) throws SQLException {
    Duration duration = null;
    try (Connection connection = HwoClientDataSource.getDataSource(registryCredentials).getConnection()) {
      String selectSql = "select d.id,d.startDate,d.endDate,d.active from registrantactivity a,duration d where a.registrant_id=? and d.id=a.duration_id and d.active = '1' and d.endDate>=now()  and a.registrantActivityType=? order by d.endDate desc";
      try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
        preparedStatement.setLong(1, Long.valueOf(providerId));
        preparedStatement.setString(2, "RENEWAL");
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
          duration = new Duration();
          duration.setId(rs.getLong("id"));
          duration.setActive(rs.getShort("active"));
          duration.setStartDate(rs.getDate("startDate"));
          duration.setEndDate(rs.getDate("endDate"));
          System.out.println("endDate++"+rs.getDate("endDate"));
        }
      }
    }
    return duration;
  }




}
