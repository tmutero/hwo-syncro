package zw.hitrac.hwosync.registry.hpa.data;



import zw.hitrac.hwosync.model.RegistryCredentials;
import zw.hitrac.hwosync.registry.common.HpaProvince;
import zw.hitrac.hwosync.registry.common.HwoClientDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tafadzwa on 8/8/17.
 */
public class HpaFacilityProvinceFactory {

    public static List<HpaProvince> getHpaProvince(RegistryCredentials registryCredentials) throws SQLException {
        List<HpaProvince> hpaProvinces = new ArrayList<>();

        try (Connection connection = HwoClientDataSource.getDataSource(registryCredentials).getConnection()) {
            String selectSql = "select id,name from province";
            try (Statement statement = connection.createStatement()) {
                ResultSet rs = statement.executeQuery(selectSql);
                while (rs.next()) {
                    HpaProvince hpaProvince = new HpaProvince();
                    hpaProvince.setHpaId(String.valueOf(rs.getLong("id")));
                    hpaProvince.setName(rs.getString("name"));
                    hpaProvinces.add(hpaProvince);

                }
            }
        }
        return hpaProvinces;
    }
}
