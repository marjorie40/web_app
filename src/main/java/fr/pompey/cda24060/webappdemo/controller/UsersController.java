package fr.pompey.cda24060.webappdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import fr.pompey.cda24060.webappdemo.model.Users;
import fr.pompey.cda24060.webappdemo.service.UsersService;


@Controller // pour HTML sinon - @RestController pour JSON
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping(value = {"/", "/home"})
    public String home(Model model) {
        Iterable<Users> listAllUsers = usersService.getAllUsers(); // iterable or list ?
        model.addAttribute("listAllUsers", listAllUsers);
        return "home";
    }

    @GetMapping(value = {"/createusers"})
    public String createUsers(Model model) {
        Users users = new Users();
        model.addAttribute("user", users); // name attribut user ou users ??
        return "createUsers";  // retourne le template (meme denommination.html) qui doit etre dans static avec le css et js
    }

    @GetMapping(value = {"/updateusers/{id}"}) // tests avec id_users XXX
    public String updateUsers(@PathVariable("id") final Integer id, Model model) {
        Users users = usersService.getUsers(id);
        model.addAttribute("users", users);
        return "updateUsers";
    }

    @GetMapping(value = {"/deleteusers/{id}"})
    public ModelAndView deleteUsers(@PathVariable("id") final Integer id) {
        usersService.deteleUsers(id);
        // redirection vers la page home
        return new ModelAndView("redirect:/");
    }

    @PostMapping(value = {"/saveusers"})
    public ModelAndView saveUsers(@ModelAttribute() Users users) {
        usersService.saveUsers(users);
        // redirection vers la page home
        return new ModelAndView("redirect:/");
    }

}
