package zw.hitrac.hwosync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import zw.hitrac.hwosync.model.RegistryCredentials;
import zw.hitrac.hwosync.service.RegistryCredentialsService;
import zw.hitrac.hwosync.service.RegistrySyncService;


import javax.validation.Valid;
import java.util.List;
import java.util.Locale;


@Controller
@RequestMapping("/registry")
@SessionAttributes("roles")
public class RegistryController {

    @Autowired
    RegistryCredentialsService registryCredentialsService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    RegistrySyncService registrySyncService;

    /**
     * This method will list all existing registries.
     */
    @RequestMapping(value = {"/registrylist"}, method = RequestMethod.GET)
    public String listRegistries(ModelMap model) {

        List<RegistryCredentials> registryCredentialsList = registryCredentialsService.findAll();
        model.addAttribute("registryCredentialsList", registryCredentialsList);
        model.addAttribute("loggedinuser", getPrincipal());
        return "registry/list";
    }

    /**
     * This method will provide the medium to add a new registry.
     */
    @RequestMapping(value = {"/newregistry"}, method = RequestMethod.GET)
    public String newRegistry(ModelMap model) {
        RegistryCredentials registryCredentials = new RegistryCredentials();
        model.addAttribute("registryCredentials", registryCredentials);
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", getPrincipal());
        return "registryRegistration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving user in database. It also validates the user input
     */
    @RequestMapping(value = {"/newregistry"}, method = RequestMethod.POST)
    public String saveRegistry(@Valid RegistryCredentials registryCredentials, BindingResult result,
                               ModelMap model) {

        if (result.hasErrors()) {
            return "registryRegistration";
        }


        registryCredentialsService.save(registryCredentials);

        model.addAttribute("success", "Registry " + registryCredentials.getName() + " registered successfully");
        model.addAttribute("loggedinuser", getPrincipal());
        //return "success";
        return "registryRegistrationSuccess";
    }

    /**
     * This method will provide the medium to update an existing registry.
     */
    @RequestMapping(value = {"/edit-registry-{name}"}, method = RequestMethod.GET)
    public String editRegistry(@PathVariable String name, ModelMap model) {
        RegistryCredentials registryCredentials = registryCredentialsService.findByName(name);
        model.addAttribute("registryCredentials", registryCredentials);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", getPrincipal());
        return "registryRegistration";
    }

    @RequestMapping(value = {"/sync-registry/{name}"}, method = RequestMethod.GET)
    @ResponseBody
    public String singlePage(Model model, @PathVariable("name") String name) {

        RegistryCredentials registryCredentials = registryCredentialsService.findByName(name);

        registrySyncService.pushData(registryCredentials);
        return "registryPushSuccess for " + name;


    }


    /**
     * This method returns the principal[user-name] of logged-in user.
     */
    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}