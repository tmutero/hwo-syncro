package zw.hitrac.hwosync.registry.hpa.data;


import zw.hitrac.hwosync.model.RegistryCredentials;
import zw.hitrac.hwosync.registry.common.HpaDistrict;
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
public class HpaFacilityDistrictFactory {

    public static List<HpaDistrict> getHpaDistrict (RegistryCredentials registryCredentials) throws SQLException {
        List<HpaDistrict> hpaDistricts = new ArrayList<>();

        try (Connection connection = HwoClientDataSource.getDataSource(registryCredentials).getConnection()) {
            String selectSql = "select id, name, province from district d ";
            try (Statement statement = connection.createStatement()) {
                ResultSet rs = statement.executeQuery(selectSql);
                while (rs.next()) {
                    HpaDistrict hpaDistrict = new HpaDistrict();
                    hpaDistrict.setHpaId(String.valueOf(rs.getLong("id")));
                    hpaDistrict.setName(rs.getString("name"));
                    hpaDistrict.setHpaProvince((rs.getString("province")));
                    hpaDistricts.add(hpaDistrict);

                }
            }
        }
        return hpaDistricts;
    }
}
