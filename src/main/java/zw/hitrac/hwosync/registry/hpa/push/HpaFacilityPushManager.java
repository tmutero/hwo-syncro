package zw.hitrac.hwosync.registry.hpa.push;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import zw.hitrac.hwosync.model.RegistryCredentials;
import zw.hitrac.hwosync.registry.common.HpaFacility;
import zw.hitrac.hwosync.registry.hpa.data.HpaFacilityFactory;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HpaFacilityPushManager {

  public static void pushFacilities (RegistryCredentials registryCredentials) throws SQLException, UnirestException {

    List<HpaFacility> hpaFacilities = HpaFacilityFactory.getFacilities(registryCredentials);

    int count = hpaFacilities.size();

    int i;
    int j;
    for (i = 0; i < count; ) {

      List<HpaFacility> bufferHpaFacilities = new ArrayList<>();
      for (j = i; j < (i + 100) && j < count; j++) {
        System.out.println("j=" + j);
        bufferHpaFacilities.add(hpaFacilities.get(j));
      }
      i = j;
      System.out.println("i=" + i);
      post(bufferHpaFacilities, registryCredentials);

    }

  }

  private static void post (List<HpaFacility> bufferHpaFacilities, RegistryCredentials registryCredentials) throws UnirestException {

    // Only one time
    Unirest.setObjectMapper(new ObjectMapper() {
      private final com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
          = new com.fasterxml.jackson.databind.ObjectMapper();

      @Override
      public <T> T readValue (String value, Class<T> valueType) {
        try {
          return jacksonObjectMapper.readValue(value, valueType);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }

      @Override
      public String writeValue (Object value) {
        try {
          String strValue = jacksonObjectMapper.writeValueAsString(value);
          System.out.println("strValue=" + strValue);
          return strValue;
        } catch (JsonProcessingException e) {
          throw new RuntimeException(e);
        }
      }
    });

    String facilitiesUrl = registryCredentials.getUrl().concat("/facilities");

    System.out.println(Unirest.post(facilitiesUrl)
        .header("accept", "application/json")
        .header("Content-Type", "application/json; charset=UTF-8")
        .body(bufferHpaFacilities)
        .asString().getBody());
}

}
