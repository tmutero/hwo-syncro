package zw.hitrac.hwosync.registry.genericCouncil.data;



import zw.hitrac.hwosync.model.RegistryCredentials;
import zw.hitrac.hwosync.registry.common.DirectoryFacility;
import zw.hitrac.hwosync.registry.common.Employment;
import zw.hitrac.hwosync.registry.common.HwoClientDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tndangana on 7/19/17.
 */
public class EmployerFactory {

    public static List<Employment> getEmploymentList (String providerId, RegistryCredentials registryCredentials) throws SQLException {
        List<Employment> employmentArrayList = new ArrayList<>();

        try (Connection connection = HwoClientDataSource.getDataSource(registryCredentials).getConnection()) {

            String selectSql = "select  e.id, e.employmentType,e.startDate,e.institution_id,e.employer from employment e WHERE e.registrant_id =" + providerId;
            try (Statement statement = connection.createStatement()) {
                ResultSet rs = statement.executeQuery(selectSql);
                while (rs.next()) {
                    Employment employment = new Employment();
                    employment.setEmploymentId(rs.getString("id"));
                    employment.setDirectoryFacility(new DirectoryFacility(rs.getString("institution_id")));
                    employment.setStartDate(rs.getDate("startDate"));
                    employment.setEmploymentType(rs.getString("employmentType"));
                    employment.setEmployer(rs.getString("employer"));
                    employmentArrayList.add(employment);

                }
            }
        }
        return employmentArrayList;
    }

}
