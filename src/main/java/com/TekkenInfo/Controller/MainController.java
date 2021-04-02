package com.TekkenInfo.Controller;

import com.TekkenInfo.Domain.Char;
import com.TekkenInfo.Domain.Role;
import com.TekkenInfo.Domain.Tier;
import com.TekkenInfo.Domain.User;
import com.TekkenInfo.Repos.UserRepo;
import com.TekkenInfo.Service.UserService;
import com.TekkenInfo.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static com.TekkenInfo.Controller.ControllerUtils.getErrors;

@Controller
public class MainController {
    @Autowired
    public UserServiceImpl userService;
    private List<String> tierLvls = new ArrayList<String>(Arrays.asList("S", "A", "B", "C", "D", "EDDY"));
    private List<String> sortCriteries = new ArrayList<String>(Arrays.asList("None", "Имя(возр.)", "Имя(убыв.)", "Стиль(возр.)", "Стиль(убыв.)","Тирность(возр.)", "Тирность(убыв.)"));
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping
    public String announce(
            @RequestParam(name="name",required = false ,defaultValue = "World") String name,
            Model model){
        model.addAttribute("name",name);
        return "greeting";
    }

    @GetMapping("main")
    public String hello(Model model) {
        Iterable<Char> allChars = null;
        if(!UserServiceImpl.sortWish.equals("None")) allChars = userService.sortChars(UserServiceImpl.sortWish);
        else allChars = userService.findAll();
        model.addAttribute("sortCriteries",sortCriteries);
        model.addAttribute("tierLvls",tierLvls);
        model.addAttribute("chars",allChars);
        return "main";
    }
    @GetMapping("/sort")
    public String sortChars(@RequestParam(name="critery") String critery, Model model){
        UserServiceImpl.sortWish = critery;
        return "redirect:/main";
    }

    @PostMapping("main")
    public String addCharacter(
            @Valid Char character,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file
            ) throws IOException {
        if(bindingResult.hasErrors()){
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("char",character);
            Iterable<Char> allChars = null;
            if(!UserServiceImpl.sortWish.equals("None")) allChars = userService.sortChars(UserServiceImpl.sortWish);
            else allChars = userService.findAll();
            model.addAttribute("sortCriteries",sortCriteries);
            model.addAttribute("tierLvls",tierLvls);
            model.addAttribute("chars",allChars);
            return "main";
        } else {
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile + "." + file.getOriginalFilename();

                file.transferTo(new File(uploadPath + "/" + resultFilename));

                character.setImage(resultFilename);
            }

            userService.addChar(character);
            model.addAttribute("char", null);
            return "redirect:/main";
        }

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
        model.addAttribute("tierLvls",tierLvls);
        model.addAttribute("char",character);
        return "update";
    }

    @PostMapping("/update/{charName}")
    public String addCharacter(
            @Valid Char character,
            BindingResult bindingResult,
            Model model,
            @PathVariable String charName) {
        if(bindingResult.hasErrors()){
            Map<String, String> errorsMap = getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("tierLvls",tierLvls);
            return "update";
        } else userService.updateChar(character,charName);
       return "redirect:/main";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            @Valid User user,
            BindingResult bindingResult,
            Model model) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if(bindingResult.hasErrors()){
            Map<String, String> errorsMap = getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            return "registration";
        }

        if (userFromDb != null) {
            model.addAttribute("message", "Пользователь уже существует!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepo.save(user);

        return "redirect:/login";
    }


}
