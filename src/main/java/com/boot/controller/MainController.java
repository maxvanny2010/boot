package com.boot.controller;

import com.boot.model.Message;
import com.boot.repos.MessageRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

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

    public MainController(final MessageRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        final Iterable<Message> messages = this.repo.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam("text") String text,
                      @RequestParam("tag") String tag,
                      Map<String, Object> model) {
        this.repo.save(new Message(text, tag));
        final Iterable<Message> messages = this.repo.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam("filter") String filter, Map<String, Object> model) {
        List<Message> messages = this.repo.findByTag(filter);
        if (messages.isEmpty()) {
            messages = (List<Message>) this.repo.findAll();
        }
        model.put("messages", messages);
        return "main";
    }
}
