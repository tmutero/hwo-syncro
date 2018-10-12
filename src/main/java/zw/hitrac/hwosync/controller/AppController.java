package zw.hitrac.hwosync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;
import zw.hitrac.hwosync.model.User;
import zw.hitrac.hwosync.model.UserProfile;
import zw.hitrac.hwosync.model.UserProfileType;
import zw.hitrac.hwosync.service.UserProfileService;
import zw.hitrac.hwosync.service.UserService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;



@Controller

@SessionAttributes("roles")
public class AppController {

  @Autowired
  UserService userService;

  @Autowired
  UserProfileService userProfileService;

  @Autowired
  MessageSource messageSource;




  /**
   * This method will list all existing users.
   */
  @RequestMapping(value = {"/listUsers"}, method = RequestMethod.GET)
  public String listUsers (ModelMap model) {
    List<User> users = userService.findAll();
    model.addAttribute("users", users);
    model.addAttribute("loggedinuser", getPrincipal());
    return "user/list";
  }



  /**
   * This method will provide the medium to add a new user.
   */
  @RequestMapping(value = {"/newuser"}, method = RequestMethod.GET)
  public String newUser (ModelMap model) {
    User user = new User();
    model.addAttribute("user", user);
    model.addAttribute("edit", false);
    model.addAttribute("userProfiles", UserProfileType.values());
    model.addAttribute("loggedinuser", getPrincipal());
    return "registration";
  }

  /**
   * This method will be called on form submission, handling POST request for
   * saving user in database. It also validates the user input
   */
  @RequestMapping(value = {"/newuser"}, method = RequestMethod.POST)
  public String saveUser (@Valid User user, BindingResult result,
                          ModelMap model) {

    if (result.hasErrors()) {
      return "registration";
    }

		/*
     * Preferred way to achieve uniqueness of field [sso] should be implementing custom @Unique annotation
		 * and applying it on field [sso] of Model class [User].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */


    userService.save(user);

    model.addAttribute("success", "User " + user.getFirstName() + " " + user.getLastName() + " registered successfully");
    model.addAttribute("loggedinuser", getPrincipal());
    //return "success";
    return "registrationsuccess";
  }


  /**
   * This method will provide the medium to update an existing user.
   */




  /**
   * This method will delete an user by it's SSOID value.
   */
//  @RequestMapping(value = {"/delete-user-{ssoId}"}, method = RequestMethod.GET)
//  public String deleteUser (@PathVariable String ssoId) {
//    userService.deleteUserBySSO(ssoId);
//    return "redirect:/list";
//  }


  /**
   * This method will provide UserProfile list to templates
   */
  @ModelAttribute("roles")
  public List<UserProfile> initializeProfiles () {
    return userProfileService.findAll();
  }

  /**
   * This method handles Access-Denied redirect.
   */
  @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
  public String accessDeniedPage (ModelMap model) {
    model.addAttribute("loggedinuser", getPrincipal());
    return "accessDenied";
  }

  /**
   * This method handles login GET requests.
   * If users is already logged-in and tries to goto login page again, will be redirected to list page.
   */
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String loginPage () {

      return "redirect:/registry/registrylist";

  }

  /**
   * This method handles logout requests.
   * Toggle the handlers if you are RememberMe functionality is useless in your app.
   */
  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null) {
      new SecurityContextLogoutHandler().logout(request, response, auth);
      //persistentTokenBasedRememberMeServices.logout(request, response, auth);
      SecurityContextHolder.getContext().setAuthentication(null);
    }
    return "redirect:/login?logout";
  }

  /**
   * This method returns the principal[user-name] of logged-in user.
   */
  private String getPrincipal () {
    String userName = null;
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    if (principal instanceof UserDetails) {
      userName = ((UserDetails) principal).getUsername();
    } else {
      userName = principal.toString();
    }
    return userName;
  }

  /**
   * This method returns true if users is already authenticated [logged-in], else false.
   */

}