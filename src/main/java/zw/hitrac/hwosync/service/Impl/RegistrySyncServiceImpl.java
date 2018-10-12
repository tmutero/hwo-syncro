package zw.hitrac.hwosync.service.Impl;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.hitrac.hwosync.model.RegistryCredentials;
import zw.hitrac.hwosync.registry.genericCouncil.push.DirectoryFacilityPushManager;
import zw.hitrac.hwosync.registry.genericCouncil.push.DirectoryOccupationPushManager;
import zw.hitrac.hwosync.registry.genericCouncil.push.ProviderPushManager;
import zw.hitrac.hwosync.registry.hpa.push.HpaFacilityDistrictPushManager;
import zw.hitrac.hwosync.registry.hpa.push.HpaFacilityProvincePushManager;
import zw.hitrac.hwosync.registry.hpa.push.HpaFacilityPushManager;
import zw.hitrac.hwosync.service.RegistryCredentialsService;
import zw.hitrac.hwosync.service.RegistrySyncService;

import javax.inject.Inject;
import java.util.List;


import java.sql.SQLException;


@Service

public class RegistrySyncServiceImpl implements RegistrySyncService {

    @Autowired
    RegistryCredentialsService registryCredentialsService;

    public void syncAll() {
        List<RegistryCredentials> registryCredentialList = registryCredentialsService.findAll();
        for (RegistryCredentials registryCredentials : registryCredentialList) {
            pushData(registryCredentials);
        }
    }

    public void pushData(RegistryCredentials registryCredentials) {
        try {
            if (registryCredentials.isFacilityRegistry()) {
                pushFacilities(registryCredentials);
            } else {
                pushOcupations(registryCredentials);
                pushProviders(registryCredentials);
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void pushFacilities(RegistryCredentials registryCredentials) throws SQLException, UnirestException {

        if (registryCredentials.isFacilityRegistry()) {
            HpaFacilityProvincePushManager.pushProvinces(registryCredentials);
            HpaFacilityDistrictPushManager.pushDistricts(registryCredentials);
            HpaFacilityPushManager.pushFacilities(registryCredentials);
        } else if (registryCredentials.isCouncil()) {
            DirectoryFacilityPushManager.push(registryCredentials);
        } else if (registryCredentials.isMinistry()) {
            zw.hitrac.hwosync.registry.hris.push.DirectoryFacilityPushManager.pushFacilities(registryCredentials);
        } else {
            zw.hitrac.hwosync.registry.mdpcz.push.DirectoryFacilityPushManager.pushFacilities(registryCredentials);
        }
    }

    public void pushProviders(RegistryCredentials registryCredentials) throws SQLException, UnirestException {
        if (registryCredentials.isCouncil()) {
            ProviderPushManager.push(registryCredentials);
        } else if (registryCredentials.isMinistry()) {
            zw.hitrac.hwosync.registry.hris.push.ProviderPushManager.pushProviders(registryCredentials);
        } else {
            zw.hitrac.hwosync.registry.mdpcz.push.ProviderPushManager.pushProviders(registryCredentials);
        }
    }

    public void pushOcupations(RegistryCredentials registryCredentials) throws SQLException, UnirestException {
        if (registryCredentials.isCouncil()) {
            DirectoryOccupationPushManager.push(registryCredentials);
        } else if (registryCredentials.isMinistry()) {
            zw.hitrac.hwosync.registry.hris.push.DirectoryOccupationPushManager.pushOccupations(registryCredentials);
        } else {
            zw.hitrac.hwosync.registry.mdpcz.push.DirectoryOccupationPushManager.pushOccupations(registryCredentials);
        }
    }
}
