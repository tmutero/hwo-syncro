package zw.hitrac.hwosync.registry.hpa.data;




import zw.hitrac.hwosync.model.RegistryCredentials;
import zw.hitrac.hwosync.registry.common.HpaFacilityAddress;
import zw.hitrac.hwosync.registry.common.HwoClientDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tafadzwa on 6/19/17.
 */
public class HpaFacilityAddressFactory {
    public static List<HpaFacilityAddress> setAddresses (String facilityId, RegistryCredentials registryCredentials) throws SQLException {
        List<HpaFacilityAddress> hpaFacilityAddresses = new ArrayList<>();

        try (Connection connection = HwoClientDataSource.getDataSource(registryCredentials).getConnection()) {
            String selectSql = "SELECT id, physical_address FROM facility_contact WHERE facility=" + facilityId;;
            try (Statement statement = connection.createStatement()) {
                ResultSet rs = statement.executeQuery(selectSql);
                while (rs.next()) {
                    String physicalAddress = rs.getString("physical_address");

                    HpaFacilityAddress hpaFacilityAddress = new HpaFacilityAddress();
                    hpaFacilityAddress.setHpaFacilityAddressId(rs.getString("id"));
                    hpaFacilityAddress.setAddress1(rs.getString("physical_address"));
                    hpaFacilityAddress.setAddressType("Physical Address");
                    hpaFacilityAddresses.add(hpaFacilityAddress);

                }
            }
        }
        return hpaFacilityAddresses;
    }

}
