package zw.hitrac.hwosync.registry.mdpcz.push;

import com.fasterxml.jackson.core.JsonProcessingException;


import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import zw.hitrac.hwosync.model.RegistryCredentials;
import zw.hitrac.hwosync.registry.common.Provider;
import zw.hitrac.hwosync.registry.mdpcz.data.ProviderFactory;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProviderPushManager {

  public static void pushProviders (RegistryCredentials registryCredentials) throws SQLException, UnirestException {

    List<Provider> providers = ProviderFactory.getProviders(registryCredentials);

    int count = providers.size();

    int i;
    int j;
    for (i = 0; i < count; ) {

      List<Provider> bufferProviders = new ArrayList<>();
      for (j = i; j < (i + 500) && j < count; j++) {
        System.out.println("j=" + j);
        bufferProviders.add(providers.get(j));
      }
      i = j;
      System.out.println("i=" + i);
      post(bufferProviders, registryCredentials);

    }

  }

  private static void post (List<Provider> bufferProviders, RegistryCredentials registryCredentials) throws UnirestException {

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

    String providersUrl = registryCredentials.getUrl().concat("/providers");

   Unirest.post(providersUrl)
        .header("accept", "application/json")
        .header("Content-Type", "application/json; charset=UTF-8")
        .body(bufferProviders)
        .asString().getBody();
  }

}
