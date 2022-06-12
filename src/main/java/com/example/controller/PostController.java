package com.example.controller;

import com.example.dao.LocationDao;
import com.example.dao.StoryDao;
import com.example.dto.requestDto.StoryRequestDto;
import com.example.dto.requestDto.UserRequestDto;
import com.example.entity.Attachment;
import com.example.entity.Location;
import com.example.entity.Story;
import com.example.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class PostController {

    @Autowired
    LocationDao locationDao;

    @Autowired
    FileController fileController;

    @Autowired
    StoryDao storyDao;

    @GetMapping("/posts")
    public String getPosts(Model model) {
        List<Story> posts = storyDao.findAll();
        model.addAttribute("posts", posts);
        return "post/posts";
    }

    @GetMapping("/addPost")
    public String addNewPost(Model model) {
        Story story = new Story();
        List<Location> locations = locationDao.locations();
        model.addAttribute("locations", locations);
        model.addAttribute("story", story);
        return "post/addPost";
    }

    @PostMapping("/savePost")
    public String saveNewPost(@ModelAttribute("post") StoryRequestDto storyRequestDto, Model model, @RequestParam("attachment") MultipartFile[] file) throws IOException {
        Location location = locationDao.findById(storyRequestDto.getLocationId());
        Story story = new Story();
        BeanUtils.copyProperties(storyRequestDto, story);
        List<Attachment> attachments = fileController.uploadFile(file);
        story.setPrivacy("Public");
        story.setLocationId(location.getId());
        story.setStoryAttachments(attachments);
        storyDao.save(story);
        return getPosts(model);
    }
}
