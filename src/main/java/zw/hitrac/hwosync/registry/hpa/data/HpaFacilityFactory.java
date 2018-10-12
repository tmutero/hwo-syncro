package zw.hitrac.hwosync.registry.hpa.data;



import zw.hitrac.hwosync.model.RegistryCredentials;
import zw.hitrac.hwosync.registry.common.HpaDistrict;
import zw.hitrac.hwosync.registry.common.HpaFacility;
import zw.hitrac.hwosync.registry.common.HpaPractitioner;
import zw.hitrac.hwosync.registry.common.HwoClientDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HpaFacilityFactory {

  public static List<HpaFacility> getFacilities (RegistryCredentials registryCredentials) throws SQLException {
    List<HpaFacility> facilities = new ArrayList<>();

    try (Connection connection = HwoClientDataSource.getDataSource(registryCredentials).getConnection()) {
      String selectSql = "select * from facility f join facility_type ft on f.facility_type = ft.id  join district d on f.district =d.id";
      try (Statement statement = connection.createStatement()) {
        ResultSet rs = statement.executeQuery(selectSql);
        while (rs.next()) {
          HpaFacility facility = new HpaFacility();
          Long facilityId = rs.getLong("id");
          facility.setHpaId(String.valueOf(facilityId));
          facility.setName(rs.getString("name"));
          facility.setUuid(rs.getString("uuid"));
          facility.setLatitude(rs.getString("latitude"));
          facility.setLongitude(rs.getString("longitude"));
          facility.setFacilityType(rs.getString("ft.name"));
          facility.setRegistrationNumber(rs.getString("facility_reg_number"));

          String firstName = rs.getString("pfirst_name");
          String lastName = rs.getString("plast_name");
          HpaPractitioner hpaPractitioner = new HpaPractitioner();
          hpaPractitioner.setFirstName(firstName);
          hpaPractitioner.setLastName(lastName);
          hpaPractitioner.setHpaPractitionerId(String.valueOf(facilityId));

          HpaDistrict hpaDistrict = new HpaDistrict();
          hpaDistrict.setHpaId(String.valueOf(rs.getLong("d.id")));
          facility.setHpaDistrict(hpaDistrict);
          facility.setHpaPractitioner(hpaPractitioner);

          facilities.add(facility);

        }
      }
    }

    for (HpaFacility facility : facilities) {
        HpaFacilityPeriodFactory.setPeriod(facility, registryCredentials);
        facility.setHpaFacilityAddresses(HpaFacilityAddressFactory.setAddresses(facility.getHpaId(),registryCredentials));
        facility.setHpaFacilityContacts(HpaFacilityContactFactory.setContact(facility.getHpaId(),registryCredentials));

    }


    return facilities;
  }


}
