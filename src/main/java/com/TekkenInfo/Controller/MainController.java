package com.TekkenInfo.Controller;

import com.TekkenInfo.Domain.Char;
import com.TekkenInfo.Domain.Tier;
import com.TekkenInfo.Domain.User;
import com.TekkenInfo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    public UserService userService;

    @GetMapping
    public String announce(
            @RequestParam(name="name",required = false ,defaultValue = "World") String name,
            Model model){
        model.addAttribute("name",name);
        return "greeting";
    }

    @GetMapping("main")
    public String hello(Model model) {
        Iterable<Char> allChars = userService.findAll();
        model.addAttribute("chars",allChars);
        return "main";
    }

    @PostMapping("main")
    public String addCharacter(
            @RequestParam String name,
            @RequestParam String style,
            @RequestParam String lvl,
            Model model) {
        userService.addChar(new Char(name,style, Tier.valueOf(lvl)));
        Iterable<Char> allChars = userService.findAll();
        model.addAttribute("chars",allChars);
        return "redirect:/main";
    }

    @GetMapping("/delete/{charName}")
    public String deleteCharacter(
            @PathVariable("charName") String charName,
            Model model){
        userService.deleteChar(charName);
        return "redirect:/main";

    }


    @GetMapping("/update/{charName}")
    public String updateCharacter(
            @PathVariable("charName") String charName,
            Model model){
        Char character = userService.findByName(charName);
        model.addAttribute("char",character);
        return "update";
    }

    @PostMapping("/update/{charName}")
    public String addCharacter(
            @RequestParam String name,
            @RequestParam String style,
            @RequestParam String lvl,
            @PathVariable("charName") String charName,
            Model model) {
       userService.updateChar(new Char(name,style,Tier.valueOf(lvl)),charName);
       return "redirect:/main";
    }

    @GetMapping("registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("registration")
    public String addUser(
            User user,
            Model model
    ){
        com.TekkenInfo.Domain.User userFromDb = userService.findByUsername(user.getUsername());
        if(userFromDb!=null) {
            model.addAttribute("message","User already exists");
            return "registration";
        }else{
            user.setActive(true);
            userService.addUser(user);
            return "redirect:/login";
        }
    }


}
