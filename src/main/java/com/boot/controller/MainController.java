package com.boot.controller;

import com.boot.model.Message;
import com.boot.model.User;
import com.boot.repos.MessageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * GreetingController.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 9/2/2020
 */
@Controller
public class MainController {
    private final MessageRepository repo;
    @Value("${upload.path}")
    private String uploadPath;

    public MainController(final MessageRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Model model,
                       @RequestParam(name = "filter", required = false, defaultValue = "") String filter) {
        Iterable<Message> messages;
        if (!Objects.isNull(filter) && !filter.isEmpty()) {
            messages = this.repo.findByTag(filter);
        } else {
            messages = this.repo.findAll();
        }
        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("/main")
    public String add(@AuthenticationPrincipal User user,
                      @RequestParam("text") String text,
                      @RequestParam("tag") String tag,
                      @RequestParam("file") MultipartFile file,
                      Map<String, Object> model) throws IOException {
        final Message message = new Message(text, tag, user);
        if (Objects.nonNull(file) && !file.isEmpty()) {
            final File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                final boolean dir = uploadDir.mkdirs();
            }
            final String uuidFile = UUID.randomUUID().toString();
            final String resultFileName = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFileName));
            message.setFilename(resultFileName);
        }
        this.repo.save(message);
        final Iterable<Message> messages = this.repo.findAll();
        model.put("messages", messages);
        return "main";
    }
}
