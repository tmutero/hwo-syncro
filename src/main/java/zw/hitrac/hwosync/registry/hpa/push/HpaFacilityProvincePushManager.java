package zw.hitrac.hwosync.registry.hpa.push;

import com.fasterxml.jackson.core.JsonProcessingException;


import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import zw.hitrac.hwosync.model.RegistryCredentials;
import zw.hitrac.hwosync.registry.common.HpaProvince;
import zw.hitrac.hwosync.registry.hpa.data.HpaFacilityProvinceFactory;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tafadzwa on 8/8/17.
 */
public class HpaFacilityProvincePushManager {

    public static void pushProvinces(RegistryCredentials registryCredentials) throws SQLException, UnirestException {

        List<HpaProvince> hpaProvinces = HpaFacilityProvinceFactory.getHpaProvince(registryCredentials);

        int count = hpaProvinces.size();

        int i;
        int j;
        for (i = 0; i < count; ) {

            List<HpaProvince> bufferHpaProvinces = new ArrayList<>();
            for (j = i; j < (i + 100) && j < count; j++) {
                System.out.println("j=" + j);
                bufferHpaProvinces.add(hpaProvinces.get(j));
            }
            i = j;
            System.out.println("  i=" + i);
            post(bufferHpaProvinces, registryCredentials);

        }


    }

    private static void post(List<HpaProvince> bufferHpaProvinces, RegistryCredentials registryCredentials) throws UnirestException {

        // Only one time
        Unirest.setObjectMapper(new ObjectMapper() {
            private final com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            @Override
            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public String writeValue(Object value) {
                try {
                    String strValue = jacksonObjectMapper.writeValueAsString(value);
                    return strValue;
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        String facilitiesUrl = registryCredentials.getUrl().concat("/provinces");
        System.out.println(Unirest.post(facilitiesUrl)
                .header("accept", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .body(bufferHpaProvinces)
                .asString().getBody());
    }

}