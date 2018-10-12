package zw.hitrac.hwosync.registry.mdpcz.push;


import com.fasterxml.jackson.core.JsonProcessingException;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import zw.hitrac.hwosync.model.RegistryCredentials;
import zw.hitrac.hwosync.registry.common.DirectoryFacility;
import zw.hitrac.hwosync.registry.genericCouncil.data.DirectoryFacilityFactory;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DirectoryFacilityPushManager {

  public static void pushFacilities (RegistryCredentials registryCredentials) throws SQLException, UnirestException {

    List<DirectoryFacility> directoryFacilities = DirectoryFacilityFactory.getFacilities(registryCredentials);

    int count = directoryFacilities.size();

    int i;
    int j;
    for (i = 0; i < count; ) {

      List<DirectoryFacility> bufferDirectoryFacilities = new ArrayList<>();
      for (j = i; j < (i + 100) && j < count; j++) {
        System.out.println("j=" + j);
        bufferDirectoryFacilities.add(directoryFacilities.get(j));
      }
      i = j;
      System.out.println("i=" + i);
      post(bufferDirectoryFacilities, registryCredentials);

    }

  }

  private static void post (List<DirectoryFacility> bufferDirectoryFacilities, RegistryCredentials registryCredentials) throws UnirestException {

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

    HttpResponse<String> response = Unirest.post(facilitiesUrl)
        .basicAuth("admin", "admin")
        .header("accept", "application/json")
        .header("Content-Type", "application/json; charset=UTF-8")
        .body(bufferDirectoryFacilities)
        .asString();
    System.out.println("response.getStatus()=" + response.getStatus());
    System.out.println("response.getStatusText()=" + response.getStatusText());


  }

}
