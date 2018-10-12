package zw.hitrac.hwosync.registry.hris.data;



import zw.hitrac.hwosync.model.RegistryCredentials;
import zw.hitrac.hwosync.registry.common.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProviderFactory {

  public static List<Provider> getProviders (RegistryCredentials registryCredentials) throws SQLException {
    List<Provider> providers = new ArrayList<>();

    try (Connection connection = HwoClientDataSource.getDataSource(registryCredentials).getConnection()) {
      String selectSql = "select e.employee_id,e.ec_number,p.gender,p.birth_date,p.lastname,p.firstname,p.middlename,p.id_number,e.post_id,e.station_id,e.initial_employment_date from person p,employee e where e.employee_id=p.person_id";
      try (Statement statement = connection.createStatement()) {
        ResultSet rs = statement.executeQuery(selectSql);
        while (rs.next()) {
          Provider provider = new Provider();
          provider.setFirstName(rs.getString("firstname"));
          provider.setLastName(rs.getString("lastname"));
          provider.setMiddleName(rs.getString("middlename"));
          provider.setDateOfBirth(rs.getDate("birth_date"));
          provider.setNationalId(rs.getString("id_number"));
          provider.setGender(rs.getString("gender"));
          provider.setProviderId(rs.getString("employee_id"));
          provider.setProviderNumber(rs.getString("ec_number"));

          Employment employment = new Employment();
          employment.setDirectoryFacility(new DirectoryFacility(rs.getString("station_id")));
          employment.setDirectoryOccupation(new DirectoryOccupation(rs.getString("post_id")));
          employment.setStartDate(rs.getDate("initial_employment_date"));
          employment.setEmploymentId(rs.getString("employee_id"));
          provider.addEmployment(employment);
          providers.add(provider);
        }
      }
    }

    for (Provider provider : providers) {
      provider.setProviderAddresses(ProviderAddressFactory.getAddresses(provider.getProviderId(), registryCredentials));
      provider.setProviderContacts(ProviderContactFactory.getProviderContacts(provider.getProviderId(), registryCredentials));
      provider.setProviderQualifications(ProviderQualificationFactory.getQualifications(provider.getProviderId(), registryCredentials));
  }
    return providers;
  }
}
