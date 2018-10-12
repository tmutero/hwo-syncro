package zw.hitrac.hwosync.registry.hpa.data;


import zw.hitrac.hwosync.model.RegistryCredentials;
import zw.hitrac.hwosync.registry.common.HpaFacilityContact;
import zw.hitrac.hwosync.registry.common.HwoClientDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tafadzwa on 6/19/17.
 */
public class HpaFacilityContactFactory {

    public static List<HpaFacilityContact> setContact(String facilityId, RegistryCredentials registryCredentials) throws SQLException {
        List<HpaFacilityContact> hpaFacilityContacts = new ArrayList<>();

        try (Connection connection = HwoClientDataSource.getDataSource(registryCredentials).getConnection()) {
            String selectSql = "SELECT fc.id, fc.email, fc.mobile_number, fc.telephone FROM facility_contact fc WHERE fc.facility=" + facilityId;
            ;
            try (Statement statement = connection.createStatement()) {
                ResultSet rs = statement.executeQuery(selectSql);
                while (rs.next()) {

                    String mobileContact = rs.getString("mobile_number");
                    String telephoneContact = rs.getString("telephone");
                    String emailContact = rs.getString("email");

                    if (emailContact != null) {
                        HpaFacilityContact hpaFacilityContact = new HpaFacilityContact();
                        hpaFacilityContact.setHpaFacilityContactId(rs.getString("id"));
                        hpaFacilityContact.setContactDetail(emailContact);
                        hpaFacilityContact.setType("Email");
                        if (emailContact.length() > 0) {
                            hpaFacilityContacts.add(hpaFacilityContact);
                        }

                    }
                    if (mobileContact != null) {
                        HpaFacilityContact hpaFacilityContact = new HpaFacilityContact();
                        hpaFacilityContact.setHpaFacilityContactId(rs.getString("id"));
                        hpaFacilityContact.setContactDetail(mobileContact);
                        hpaFacilityContact.setType("Mobile Number");
                        if (mobileContact.length() > 0) {
                            hpaFacilityContacts.add(hpaFacilityContact);
                        }

                    }

                    if (telephoneContact != null) {
                        HpaFacilityContact hpaFacilityContact = new HpaFacilityContact();
                        hpaFacilityContact.setHpaFacilityContactId(rs.getString("id"));
                        hpaFacilityContact.setContactDetail(telephoneContact);
                        hpaFacilityContact.setType("Telephone Number");
                        if (telephoneContact.length() > 0) {
                            hpaFacilityContacts.add(hpaFacilityContact);
                        }
                    }


                }
            }

            return hpaFacilityContacts;
        }
    }
}