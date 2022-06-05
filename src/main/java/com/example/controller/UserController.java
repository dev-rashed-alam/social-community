package com.example.controller;

import com.example.dao.LocationDao;
import com.example.dao.UserDao;
import com.example.dto.requestDto.UserRequestDto;
import com.example.entity.Location;
import com.example.entity.User;
import com.example.model.UserModal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    UserDao userDao;

    @Autowired
    LocationDao locationDao;


    @GetMapping("/users")
    public String getUsers(Model model) {
        List<User> users = userDao.findAll();
        log.info(String.valueOf(users));
        model.addAttribute("users", users);
        return "user/users";
    }

    @GetMapping("/addUser")
    public String addNewUser(Model model) {
        UserModal userModal = new UserModal();
        List<Location> locations = locationDao.locations();
        model.addAttribute("user", userModal);
        model.addAttribute("locations", locations);
        return "user/addUser";
    }

    @PostMapping("/saveUser")
    public String saveNewUser(@ModelAttribute("user") UserRequestDto userRequestDto, Model model) {
        Location location = locationDao.findById(userRequestDto.getLocationId());
        var user = new User();
        BeanUtils.copyProperties(userRequestDto, user);
        user.setLocation(location);
        userDao.save(user);
        return getUsers(model);
    }
}
