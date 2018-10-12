package zw.hitrac.hwosync.service;
import com.mashape.unirest.http.exceptions.UnirestException;
import zw.hitrac.hwosync.model.RegistryCredentials;

import java.sql.SQLException;

public interface RegistrySyncService {

  public void syncAll();

  public void pushData(RegistryCredentials registryCredentials);

  public void pushFacilities(RegistryCredentials registryCredentials) throws SQLException, UnirestException;

  public void pushProviders(RegistryCredentials registryCredentials) throws SQLException, UnirestException;

  public void pushOcupations(RegistryCredentials registryCredentials) throws SQLException, UnirestException;

}