package zw.hitrac.hwosync.registry.mdpcz.data;



import zw.hitrac.hwosync.model.RegistryCredentials;
import zw.hitrac.hwosync.registry.common.HwoClientDataSource;
import zw.hitrac.hwosync.registry.common.ProviderQualifications;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProviderQualificationFactory {

  public static List<ProviderQualifications> getQualifications (String providerId, RegistryCredentials registryCredentials) throws SQLException {
    List<ProviderQualifications> qualification = new ArrayList<>();

    try (Connection connection = HwoClientDataSource.getDataSource(registryCredentials).getConnection()) {
      String selectSql = "select rq.id as qualificationId, rq.practitioner_id, q.name as qualification, tr.name as trainingInstitution, aw.name as awardingInstitution, rq.dateQualified as dateAwarded from qualification rq, programme q,institution tr, institution aw where rq.awardingInstitution_id=aw.id and rq.trainingInstitution_id=tr.id and rq.programme_id=q.id and rq.practitioner_id=" + providerId;

      try (Statement statement = connection.createStatement()) {
        ResultSet rs = statement.executeQuery(selectSql);
        while (rs.next()) {
          ProviderQualifications qualifications = new ProviderQualifications();
          qualifications.setQualificationId(rs.getString("qualificationId"));
          qualifications.setTrainingInstitution(rs.getString("trainingInstitution"));
          qualifications.setAwardingInstitution(rs.getString("awardingInstitution"));
          qualifications.setDateAwarded(rs.getDate("dateAwarded"));
          qualifications.setName(rs.getString("qualification"));
          qualifications.setRegistrantId(rs.getString("practitioner_id"));
          qualification.add(qualifications);

        }
      }
    }
    return qualification;
  }

}
