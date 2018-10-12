package zw.hitrac.hwosync.registry.mdpcz.data;



import zw.hitrac.hwosync.model.RegistryCredentials;
import zw.hitrac.hwosync.registry.common.DirectoryOccupation;
import zw.hitrac.hwosync.registry.common.HwoClientDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DirectoryOccupationFactory {

  public static List<DirectoryOccupation> getOccupations (RegistryCredentials registryCredentials) throws SQLException {
    List<DirectoryOccupation> occupations = new ArrayList<>();

    try (Connection connection = HwoClientDataSource.getDataSource(registryCredentials).getConnection()) {
      String selectSql = "select id,name,uuid,alias from practitioner_type";
      try (Statement statement = connection.createStatement()) {
        ResultSet rs = statement.executeQuery(selectSql);
        while (rs.next()) {
          DirectoryOccupation occupation = new DirectoryOccupation();
          occupation.setOccupationId(String.valueOf(rs.getLong("id")));
          occupation.setName(rs.getString("name"));
          occupation.setDescription(rs.getString("alias"));
          occupation.setUuid(rs.getString("uuid"));
          occupations.add(occupation);
        }
      }
    }
    return occupations;
  }
}
