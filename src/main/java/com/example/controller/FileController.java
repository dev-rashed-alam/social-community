package com.example.controller;

import com.example.Properties;
import com.example.dao.AttachmentDao;
import com.example.entity.Attachment;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    AttachmentDao attachmentDao;

    @PostMapping("/upload")
    public Attachment uploadFile(@RequestParam("attachment") MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(fileName);
        String filePath = Properties.USER_UPLOAD_LOCATION + fileName;
        Path path = Paths.get(filePath);
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        Attachment attachment = new Attachment();
        attachment.setAttachmentPath(filePath);
        attachment.setType(extension);
        attachment.setAttachmentName(fileName);
        return attachment;
    }

    @PostMapping("/uploadMultiple")
    public List<Attachment> uploadFile(@RequestParam("attachment") MultipartFile[] files) throws IOException {
        List<Attachment> attachmentList = new ArrayList<>();
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            String extension = FilenameUtils.getExtension(fileName);
            String filePath = Properties.USER_UPLOAD_LOCATION + fileName;
            Path path = Paths.get(filePath);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            Attachment attachment = new Attachment();
            attachment.setAttachmentPath(filePath);
            attachment.setType(extension);
            attachment.setAttachmentName(fileName);
            attachmentList.add(attachment);
        }

        return attachmentList;
    }

    @GetMapping("/get/{id}")
    public void getFile(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {
        Attachment attachment = attachmentDao.findById(id);
        File file = new File(attachment.getAttachmentPath());
        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
        response.setContentLength((int) file.length());
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }

}
