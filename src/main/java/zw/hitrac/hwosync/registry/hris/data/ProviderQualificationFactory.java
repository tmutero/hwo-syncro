package zw.hitrac.hwosync.registry.hris.data;



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
      String selectSql = "select eq.employee_qualification_id as qualificationId, eq.employee_id, q.name as qualification, tr.name as trainingInstitution, aw.name as awardingInstitution, eq.year_acquired as dateAwarded from employee_qualification eq, qualification q, institution tr, institution aw where eq.awarding_institution_id=aw.institution_id and eq.training_institution_id=tr.institution_id and eq.qualification_id=q.qualification_id and eq.employee_id=" + providerId;

      try (Statement statement = connection.createStatement()) {
        ResultSet rs = statement.executeQuery(selectSql);
        while (rs.next()) {
          ProviderQualifications qualifications = new ProviderQualifications();
          qualifications.setQualificationId(rs.getString("qualificationId"));
          qualifications.setTrainingInstitution(rs.getString("trainingInstitution"));
          qualifications.setAwardingInstitution(rs.getString("awardingInstitution"));
          qualifications.setDateAwarded(rs.getDate("dateAwarded"));
          qualifications.setName(rs.getString("qualification"));
          qualifications.setRegistrantId(rs.getString("employee_id"));
          qualification.add(qualifications);

        }
      }
    }
    return qualification;
  }

}
