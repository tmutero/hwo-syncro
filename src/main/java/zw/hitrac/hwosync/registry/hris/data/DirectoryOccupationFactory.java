/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this templates file, choose Tools | Templates
 * and open the templates in the editor.
 */
package zw.hitrac.hwosync.registry.hris.data;



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
      String selectSql = "select post_id,name,description,uuid from post";
      try (Statement statement = connection.createStatement()) {
        ResultSet rs = statement.executeQuery(selectSql);
        while (rs.next()) {
          DirectoryOccupation occupation = new DirectoryOccupation();
          occupation.setOccupationId(rs.getString("post_id"));
          occupation.setName(rs.getString("name"));
          occupation.setDescription(rs.getString("description"));
          occupation.setUuid(rs.getString("uuid"));
          occupations.add(occupation);
        }
      }
    }
    return occupations;
  }


}
