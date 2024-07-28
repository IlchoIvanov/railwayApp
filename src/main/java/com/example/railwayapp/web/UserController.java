package com.example.railwayapp.web;

import com.example.railwayapp.model.dto.UserInfoDto;
import com.example.railwayapp.model.dto.UserLoginDto;
import com.example.railwayapp.model.dto.UserRegisterDto;
import com.example.railwayapp.model.user.RailwayAppUserDetails;
import com.example.railwayapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @ModelAttribute("registerData")
    public UserRegisterDto userRegisterDto() {
        return new UserRegisterDto();
    }
    @PostMapping("/register")
    public String processRegister(@Valid UserRegisterDto data, BindingResult result, RedirectAttributes redirectAttributes) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        modelAndView.addObject("registerData", data);
        if (!data.getPassword().equals(data.getConfirmPassword())) {
            result.addError(
                    new FieldError(
                            "differentConfirmPassword",
                            "confirmPassword",
                            "Паролите трябва да са идентични."));
        }
        if(result.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerData", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerData", result);
            return "redirect:/register";
        }
        userService.registerUser(data);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.addObject("loginData", new UserLoginDto());

        return modelAndView;
    }
    @PostMapping("/login-error")
    public ModelAndView loginError() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.addObject("loginError", true);
        modelAndView.addObject("loginData", new UserLoginDto());
        return modelAndView;
    }
    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal RailwayAppUserDetails userDetails, Model model) {
        if(userDetails == null) {
            return "index";
        }
        UserInfoDto userInfo = userService.findUserInfoByEmail(userDetails.getUsername());
        model.addAttribute("user", userInfo);
        String visitedStations = String.join(",", userInfo.getVisitedStations());
        model.addAttribute("visitedStations", visitedStations);
        return "profile";
    }

    @GetMapping("/all-users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String allUsers(Model model) {
        List<UserInfoDto> allUsers = userService.getAllUsers();
        model.addAttribute("users", allUsers);
        return "profiles";

    }
}
