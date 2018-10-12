package zw.hitrac.hwosync.registry.hris.data;



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
      String selectSql = "select station_id,name,description,uuid from station";
      try (Statement statement = connection.createStatement()) {
        ResultSet rs = statement.executeQuery(selectSql);
        while (rs.next()) {
          DirectoryFacility facility = new DirectoryFacility();
          facility.setFacilityId(rs.getString("station_id"));
          facility.setName(rs.getString("name"));
          facility.setDescription(rs.getString("description"));
          facility.setUuid(rs.getString("uuid"));
          facilities.add(facility);
        }
      }
    }
    return facilities;
  }


}
