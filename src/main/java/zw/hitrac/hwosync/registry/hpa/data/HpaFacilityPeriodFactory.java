package zw.hitrac.hwosync.registry.hpa.data;



import zw.hitrac.hwosync.model.RegistryCredentials;
import zw.hitrac.hwosync.registry.common.HpaFacility;
import zw.hitrac.hwosync.registry.common.HwoClientDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HpaFacilityPeriodFactory {

  public static void setPeriod (HpaFacility facility, RegistryCredentials registryCredentials) throws SQLException {

    try (Connection connection = HwoClientDataSource.getDataSource(registryCredentials).getConnection()) {
      String selectSql = "SELECT r.renewal_date,rp.end_date, rp.active FROM renewal r, renewal_period rp WHERE rp.id=r.renewal_period and r.facility=" + facility.getHpaId() + "";
      try (Statement statement = connection.createStatement()) {
        ResultSet rs = statement.executeQuery(selectSql);
        while (rs.next()) {
          facility.setRenewalDate(rs.getDate("renewal_date"));
          facility.setEndDate(rs.getDate("end_date"));
          Short active = rs.getShort("active");
          String status = active == 1 ? "Active" : "Inactive";
          facility.setStatus(status);
        }
      }
    }
  }

}
