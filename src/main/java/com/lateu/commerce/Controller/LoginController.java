/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.Controller;

import com.lateu.commerce.entites.UserRole;
import com.lateu.commerce.metier.ServiceException;
import com.lateu.commerce.metier.sPersonnel;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author lateu
 */

@Controller
public class LoginController implements Serializable {

    private String conncte;
    private String name;
    private ApplicationContext ctx = new ClassPathXmlApplicationContext("Spring-conf.xml");
    private sPersonnel servper = (sPersonnel) ctx.getBean("sPersonnel");

    public LoginController() {
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) throws ServiceException {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        name = user.getUsername();

        model.addAttribute("username", name);
        //  model.addAttribute("message", "Spring Security login + database example");
        //return "hello";
        UserRole client = new UserRole();
        client = servper.findRole(name);
        if (client.getAutorié().equals("ROLE_ADMIN")) {
            return "DG/index";

        } else if (client.getAutorié().equals("ROLE_CAISSIER")) {
            return "CAISSIER/index";

        } else {
            return null;
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConncte() {
        //conncte = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return conncte;

    }

    public void setConncte(String conncte) {
        this.conncte = conncte;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model) {

        return "login";

    }

    @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
    public String loginerror(ModelMap model) {

        model.addAttribute("error", "true");
        return "login";

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ModelMap model) {
        name = null;
        return "login";

    }

    public ApplicationContext getCtx() {
        return ctx;
    }

    public void setCtx(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    public sPersonnel getServper() {
        return servper;
    }

    public void setServper(sPersonnel servper) {
        this.servper = servper;
    }
}
