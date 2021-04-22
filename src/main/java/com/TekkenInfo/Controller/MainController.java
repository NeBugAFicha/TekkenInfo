package com.TekkenInfo.Controller;

import com.TekkenInfo.Domain.Char;
import com.TekkenInfo.Domain.Guide;
import com.TekkenInfo.Domain.Role;
import com.TekkenInfo.Domain.User;
import com.TekkenInfo.Repos.GuideRepo;
import com.TekkenInfo.Repos.UserRepo;
import com.TekkenInfo.Service.UserService;
import com.TekkenInfo.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.TekkenInfo.Controller.ControllerUtils.getErrors;

@Controller
public class MainController {
    @Autowired
    public UserService userService;
    private List<String> tierLvls = new ArrayList<String>(Arrays.asList("S", "A", "B", "C", "D", "EDDY"));
    private List<String> sortCriteries = new ArrayList<String>(Arrays.asList("Имя(возр.)", "Имя(убыв.)", "Стиль(возр.)", "Стиль(убыв.)","Тирность(возр.)", "Тирность(убыв.)"));
    private List<String> searchBy = new ArrayList<>(Arrays.asList("name","style","tier","author"));
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private GuideRepo guideRepo;
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
        if(!UserServiceImpl.sortWish.equals("None")) {
            allChars = userService.sortChars(UserServiceImpl.sortWish);
            model.addAttribute("selectedSortCritery",UserServiceImpl.sortWish);
        }
        else allChars = userService.findAll();
        model.addAttribute("sortCriteries",sortCriteries);
        model.addAttribute("tierLvls",tierLvls);
        model.addAttribute("searchBy",searchBy);
        model.addAttribute("chars",allChars);
        return "main";
    }
    @GetMapping("/dropAllFilters")
    public String dropAllFilters(){
        UserServiceImpl.sortWish = "None";
        return "redirect:/main";
    }
    @GetMapping("/sort")
    public String sortChars(@RequestParam String sortCritery){
        UserServiceImpl.sortWish = sortCritery;
        return "redirect:/main";
    }
     @GetMapping("/filter")
     public String filterChars(@RequestParam String searchByCritery, @RequestParam String filter, Model model){
        if(filter.equals("")) return "redirect:/main";
        Iterable<Char> allchars = userService.findAll();
        ArrayList<Char> allcharsFiltered = new ArrayList<Char>();
        if(searchByCritery.equals("name")) for(Char character: allchars) if(character.getName().equals(filter)) allcharsFiltered.add(character);
        if(searchByCritery.equals("style")) for(Char character: allchars) if(character.getFightingStyle().equals(filter)) allcharsFiltered.add(character);
        if(searchByCritery.equals("tier")) for(Char character: allchars) if(character.getTierLvl().toString().equals(filter)) allcharsFiltered.add(character);
        if(searchByCritery.equals("author")) for(Char character: allchars) if(character.getCharMakerName().equals(filter)) allcharsFiltered.add(character);
        model.addAttribute("filter",filter);
        model.addAttribute("sortCriteries",sortCriteries);
        model.addAttribute("tierLvls",tierLvls);
        model.addAttribute("selectedSearchByCritery",searchByCritery);
        model.addAttribute("searchBy",searchBy);
        model.addAttribute("chars",allcharsFiltered);
        return "main";
     }

    @PostMapping("main")
    public String addCharacter(
            @Valid Char character,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file,
            @RequestParam String charMakerName
            ) throws IOException {
        if(bindingResult.hasErrors()){
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("char",character);
            Iterable<Char> allChars = null;
            if(!UserServiceImpl.sortWish.equals("None")) {
                allChars = userService.sortChars(UserServiceImpl.sortWish);
                model.addAttribute("selectedSortCritery",UserServiceImpl.sortWish);
            }
            else allChars = userService.findAll();
            model.addAttribute("sortCriteries",sortCriteries);
            model.addAttribute("tierLvls",tierLvls);
            model.addAttribute("searchBy",searchBy);
            model.addAttribute("chars",allChars);
            return "main";
        }
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
            character.setCharMakerName(userRepo.findByUsername(charMakerName).getUsername());
            userService.addChar(character);
            model.addAttribute("char", null);
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
        model.addAttribute("tierLvls",tierLvls);
        model.addAttribute("char",character);
        return "update";
    }

    @PostMapping("/update/{charName}")
    public String updateCharacter(
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
    @GetMapping("/profile")
    public String getProfile(){
        return "profile";
    }
    @PostMapping("/profile")
    public String editProfile(@RequestParam String userName, @AuthenticationPrincipal User user, Model model){
        String oldName = user.getUsername();
        if(userName.length()==0||userName.length()>30){
            model.addAttribute("nameError","Некорректный ввод имени пользователя");
            return "profile";
        }
        user.setUsername(userName);
        userService.updateCharMakerNameForChars(oldName,user.getUsername());
        userRepo.save(user);
        return "redirect:/main";
    }

    @GetMapping("/guides")
    public String getGuides(Model model) {
        List<Guide> guides = guideRepo.findAll();
        model.addAttribute("guides", guides);
        return "guides";
    }
    @PostMapping("/guides")
    public String addGuide(
            @Valid Guide guide,
            BindingResult bindingResult,
            Model model,
            @AuthenticationPrincipal User user){
         if(bindingResult.hasErrors()) {
             Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
             model.mergeAttributes(errorsMap);
             model.addAttribute("newGuide", guide);
             model.addAttribute("guides", guideRepo.findAll());
             return "guides";
         }
         user.setGuieds(Arrays.asList(guide));
         userRepo.save(user);
         return "redirect:/guides";
    }
    @GetMapping("/deleteGuide/{guide}")
    public String deleteGuide(@PathVariable Guide guide){
        guideRepo.delete(guide);
        return "redirect:/guides";
    }
    @GetMapping("/updateGuide/{guide}")
    public String updateGuide(@PathVariable Guide guide, Model model){
        model.addAttribute("newGuide", guide);
        return "updateGuide";
    }
    @PostMapping("/updateGuide/{guide}")
    public String updateGuide(
            @Valid Guide newGuide,
            BindingResult bindingResult,
            Model model,
            @AuthenticationPrincipal User user){
        if(bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("newGuide", newGuide);
            return "updateGuide";
        }
        guideRepo.save(newGuide);
        return "redirect:/guides";

    }


}
