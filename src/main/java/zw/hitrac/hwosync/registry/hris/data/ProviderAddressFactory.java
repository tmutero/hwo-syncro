package zw.hitrac.hwosync.registry.hris.data;



import zw.hitrac.hwosync.model.RegistryCredentials;
import zw.hitrac.hwosync.registry.common.HwoClientDataSource;
import zw.hitrac.hwosync.registry.common.ProviderAddress;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProviderAddressFactory {

  public static List<ProviderAddress> getAddresses (String providerId, RegistryCredentials registryCredentials) throws SQLException {
    List<ProviderAddress> addresses = new ArrayList<>();

    try (Connection connection = HwoClientDataSource.getDataSource(registryCredentials).getConnection()) {
      String selectSql = "select p.person_address_id,p.address_type_id,city,address1,address2,a.name from person_address p,address_type a where p.address_type_id = a.address_type_id and  person_id='" + providerId + "'";
      try (Statement statement = connection.createStatement()) {
        ResultSet rs = statement.executeQuery(selectSql);
        while (rs.next()) {
          ProviderAddress address = new ProviderAddress();
          address.setAddressId(rs.getString("person_address_id"));
          address.setType(rs.getString("name"));
          address.setCity(rs.getString("city"));
          address.setAddress1(rs.getString("address1"));
          address.setAddress2(rs.getString("address2"));
          addresses.add(address);
        }
      }
    }
    return addresses;
  }
}
