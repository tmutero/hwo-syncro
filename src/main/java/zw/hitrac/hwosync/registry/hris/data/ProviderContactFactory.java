package zw.hitrac.hwosync.registry.hris.data;



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
      String selectSql = "select p.person_contact_id,p.contact_type_id,contact_detail,c.name from person_contact p,contact_type c where p.contact_type_id=c.contact_type_id and person_id='" + providerId + "'";
      try (Statement statement = connection.createStatement()) {
        ResultSet rs = statement.executeQuery(selectSql);
        while (rs.next()) {
          ProviderContact contact = new ProviderContact();
          contact.setContactId(rs.getString("person_contact_id"));
          contact.setContactType(rs.getString("name"));
          contact.setContactDetail(rs.getString("contact_detail"));
          contacts.add(contact);
        }
      }
    }
    return contacts;
  }
}
