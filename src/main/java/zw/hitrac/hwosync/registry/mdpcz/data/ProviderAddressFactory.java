package zw.hitrac.hwosync.registry.mdpcz.data;



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
      String selectSql = "select ad.id,ad.addressLine1,ad.addressLine2,ad.addressType from address ad where ad.practitioner_id=" + providerId;
      try (Statement statement = connection.createStatement()) {
        ResultSet rs = statement.executeQuery(selectSql);
        while (rs.next()) {
          ProviderAddress address = new ProviderAddress();
          address.setAddressId(String.valueOf(rs.getLong("id")));
          address.setType(rs.getString("addressType"));
          address.setAddress1(rs.getString("addressLine1"));
          address.setAddress2(rs.getString("addressLine2"));
          addresses.add(address);
        }
      }
    }
    return addresses;
  }
}
