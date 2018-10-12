package zw.hitrac.hwosync.registry.hris.push;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import zw.hitrac.hwosync.model.RegistryCredentials;
import zw.hitrac.hwosync.registry.common.DirectoryOccupation;
import zw.hitrac.hwosync.registry.hris.data.DirectoryOccupationFactory;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DirectoryOccupationPushManager {

  public static void pushOccupations (RegistryCredentials registryCredentials) throws SQLException, UnirestException {

    List<DirectoryOccupation> directoryOccupations = DirectoryOccupationFactory.getOccupations(registryCredentials);

    int count = directoryOccupations.size();

    int i;
    int j;
    for (i = 0; i < count; ) {

      List<DirectoryOccupation> bufferDirectoryOccupations = new ArrayList<>();
      for (j = i; j < (i + 100) && j < count; j++) {
        System.out.println("j=" + j);
        bufferDirectoryOccupations.add(directoryOccupations.get(j));
      }
      i = j;
      System.out.println("i=" + i);
      post(bufferDirectoryOccupations, registryCredentials);

    }

  }

  private static void post (List<DirectoryOccupation> bufferDirectoryOccupations, RegistryCredentials registryCredentials) throws UnirestException {

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

    String occupationsUrl = registryCredentials.getUrl().concat("/occupations");
    System.out.println(Unirest.post(occupationsUrl)
        .header("accept", "application/json")
        .header("Content-Type", "application/json; charset=UTF-8")
        .body(bufferDirectoryOccupations)
        .asString().getBody());
  }

}
