package com.example.controller;

import com.example.config.Role;
import com.example.dao.LocationDao;
import com.example.dao.UserDao;
import com.example.dto.requestDto.UserRequestDto;
import com.example.entity.Attachment;
import com.example.entity.Location;
import com.example.entity.User;
import com.example.model.UserModal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserDao userDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    FileController fileController;

    @Autowired
    PasswordEncoder passwordEncoder;


    @GetMapping("/all")
    public String getUsers(Model model) {
        List<User> users = userDao.findAll();
        model.addAttribute("users", users);
        return "user/users";
    }

    @GetMapping("/add")
    public String addNewUser(Model model) {
        UserModal userModal = new UserModal();
        List<Location> locations = locationDao.locations();
        model.addAttribute("user", userModal);
        model.addAttribute("locations", locations);
        return "user/addUser";
    }

    @PostMapping("/save")
    public String saveNewUser(@ModelAttribute("user") UserRequestDto userRequestDto, Model model, @RequestParam("attachment") MultipartFile file) throws IOException {
        Location location = locationDao.findById(userRequestDto.getLocationId());
        var user = new User();
        BeanUtils.copyProperties(userRequestDto, user);
        Attachment attachment = fileController.uploadFile(file);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAttachment(attachment);
        user.setLocation(location);
        user.setRole(Role.ROLE_ADMIN);
        userDao.save(user);
        return getUsers(model);
    }
}
