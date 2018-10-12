package zw.hitrac.hwosync.registry.mdpcz.data;


import zw.hitrac.hwosync.model.RegistryCredentials;
import zw.hitrac.hwosync.registry.common.HwoClientDataSource;
import zw.hitrac.hwosync.registry.common.ProviderContact;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProviderContactFactory {

  public static List<ProviderContact> getProviderContacts (String providerId, RegistryCredentials registryCredentials) throws SQLException {
    List<ProviderContact> contacts = new ArrayList<>();
    try (Connection connection = HwoClientDataSource.getDataSource(registryCredentials).getConnection()) {
      String selectSql = "select r.id,contactType_id,detail,c.name from contact r,contact_type c where r.contactType_id=c.id and practitioner_id=" + providerId;
      try (Statement statement = connection.createStatement()) {
        ResultSet rs = statement.executeQuery(selectSql);
        while (rs.next()) {
          ProviderContact contact = new ProviderContact();
          contact.setContactId(String.valueOf(rs.getLong("id")));
          contact.setContactType(rs.getString("name"));
          contact.setContactDetail(rs.getString("detail"));
          contacts.add(contact);
        }
      }
    }
    return contacts;
  }
}
