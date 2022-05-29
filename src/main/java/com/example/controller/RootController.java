package com.example.controller;

import com.example.dao.AttachmentDao;
import com.example.dao.StoryDao;
import com.example.dao.UserDao;
import com.example.entity.Attachment;
import com.example.entity.Story;
import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RootController {

    @Autowired
    private AttachmentDao attachmentDao;

    @Autowired
    private StoryDao storyDao;

    @Autowired
    private UserDao userDao;

    private void initialize() {
        User user = new User();
        user.setEmail("rashed@gmail.com");
        user.setName("Rashed Alam");

        Attachment attachment = new Attachment();
        attachment.setAttachmentName("Profile Picture");
        attachment.setAttachmentPath("${user.home}/profile-pic-1");
        attachment.setType(".jpg");
        attachmentDao.save(attachment);

        user.setAttachment(attachment);
        userDao.save(user);

        Story story = new Story();
        story.setDescription("Story Description");
        story.setTitle("Story One");
        story.setUser(user);

        Attachment attachment1 = new Attachment();
        attachment1.setAttachmentName("Story pic 1");
        attachment1.setAttachmentPath("${user.home}/story-1");
        attachment1.setType(".jpg");
        attachmentDao.save(attachment1);

        Attachment attachment2 = new Attachment();
        attachment2.setAttachmentName("Story pic 2");
        attachment2.setAttachmentPath("${user.home}/story-2");
        attachment2.setType(".jpg");
        attachmentDao.save(attachment2);

        story.getStoryAttachments().add(attachment1);
        story.getStoryAttachments().add(attachment2);
        storyDao.save(story);


    }

    @GetMapping
    public String helloWorld(Model model) {
        initialize();
        return "hello";
    }
}
