package zw.hitrac.hwosync.registry.genericCouncil.data;



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
      String selectSql = "select r.id,addressType_id,c.name as cityName,address1,address2,a.name as addressTypeName from registrantaddress r,addresstype a,city c where c.id=r.city_id and r.addressType_id=a.id and a.name='BUSINESS ADDRESS' and registrant_id=" + providerId;
      try (Statement statement = connection.createStatement()) {
        ResultSet rs = statement.executeQuery(selectSql);
        while (rs.next()) {
          ProviderAddress address = new ProviderAddress();
          address.setAddressId(String.valueOf(rs.getLong("id")));
          address.setType(rs.getString("addressTypeName"));
          address.setCity(rs.getString("cityName"));
          address.setAddress1(rs.getString("address1"));
          address.setAddress2(rs.getString("address2"));
          addresses.add(address);
        }
      }
    }
    return addresses;
  }


}
