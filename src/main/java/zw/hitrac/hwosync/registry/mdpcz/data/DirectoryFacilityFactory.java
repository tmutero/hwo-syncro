package zw.hitrac.hwosync.registry.mdpcz.data;


import zw.hitrac.hwosync.model.RegistryCredentials;
import zw.hitrac.hwosync.registry.common.DirectoryFacility;
import zw.hitrac.hwosync.registry.common.HwoClientDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DirectoryFacilityFactory {

  public static List<DirectoryFacility> getFacilities (RegistryCredentials registryCredentials) throws SQLException {
    List<DirectoryFacility> facilities = new ArrayList<>();

    try (Connection connection = HwoClientDataSource.getDataSource(registryCredentials).getConnection()) {
      String selectSql = "select id,name,uuid from institution";
      try (Statement statement = connection.createStatement()) {
        ResultSet rs = statement.executeQuery(selectSql);
        while (rs.next()) {
          DirectoryFacility facility = new DirectoryFacility();
          facility.setFacilityId(String.valueOf(rs.getLong("id")));
          facility.setName(rs.getString("name"));
          facility.setUuid(rs.getString("uuid"));
          facilities.add(facility);
        }
      }
    }
    return facilities;
  }
}
