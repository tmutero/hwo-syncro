package zw.hitrac.hwosync.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import zw.hitrac.hwosync.model.GeneralParameter;
import zw.hitrac.hwosync.service.GeneralParametersService;
import zw.hitrac.hwosync.service.RegistryCredentialsService;


@Controller
@RequestMapping("/config")
public class ConfigurationsController {

    private final Logger logger = LoggerFactory.getLogger(ConfigurationsController.class);
    @Autowired
    private GeneralParametersService generalParametersService;
    @Autowired
    private RegistryCredentialsService registryCredentialsService;


    @RequestMapping(value = {"/add", "/add/{id}"}, method = RequestMethod.GET)
    public String add(@RequestParam(required = false) Long id, Model model) {
        final GeneralParameter generalParameter;
        if (id != null) {
            generalParameter = generalParametersService.findOne(id).get();
        } else {
            generalParameter = new GeneralParameter();
        }
        logger.debug("city - add() is executed!");
        model.addAttribute("generalParameter", generalParameter);
        model.addAttribute("title", "Create/ Edit GeneralParameter");
        return "/config/add";
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("city") @Validated GeneralParameter generalParameter,
                       BindingResult result, SessionStatus status,
                       final RedirectAttributes redirectAttributes) throws Exception {
        if (result.hasErrors()) {
            return "/generalParameter/list";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            if (generalParameter.isNew()) {
                redirectAttributes.addFlashAttribute("msg", "General Parameter added successfully!");
            } else {
                redirectAttributes.addFlashAttribute("msg", "General Parameter updated successfully!");
            }
        }
        generalParametersService.save(generalParameter);
        return "redirect:/config/list";
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        GeneralParameter generalParameter = generalParametersService.findByCouncil("ahpcz");
        model.addAttribute("generalParameter", generalParameter);
        logger.debug("General Parameters - add() is executed!");
        return "config/list";
    }


    @RequestMapping(value = {"/delete/{id}"}, method = RequestMethod.GET)
    public String delete(Model model, @PathVariable("id") Long id) {
        generalParametersService.delete(id);
        return "redirect:/config/list";
    }


}


