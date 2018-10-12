package zw.hitrac.hwosync.registry.common;



import org.apache.commons.dbcp.BasicDataSource;
import zw.hitrac.hwosync.model.RegistryCredentials;

import javax.sql.DataSource;

public class HwoClientDataSource {

  private static BasicDataSource dataSource;

  public static DataSource getDataSource (RegistryCredentials registryCredentials) {
    if (dataSource == null) {
      dataSource = new BasicDataSource();
      dataSource.setDriverClassName("com.mysql.jdbc.Driver");
      dataSource.setUrl("jdbc:mysql://" + registryCredentials.getSourceUrl()+"/"+registryCredentials.getDbName());
      dataSource.setUsername(registryCredentials.getDbUserName());
      dataSource.setPassword(registryCredentials.getDbPassword());
      dataSource.setTestOnBorrow(true);
      dataSource.setValidationQuery("SELECT 1");
    }
    return dataSource;

  }
}
