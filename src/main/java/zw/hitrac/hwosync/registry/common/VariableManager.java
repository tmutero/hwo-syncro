package zw.hitrac.hwosync.registry.common;


/**
 * @author Charles Chigoriwa
 */
public interface VariableManager {

  public Profile getProfile();

  public String getDatabaseName(Profile profile);

  public String getOccupationsUrl(Profile profile);

  public String getFacilitiesUrl(Profile profile);

  public String getProvidersUrl(Profile profile);


}
