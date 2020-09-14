package com.boot.controller;

import com.boot.model.Message;
import com.boot.model.User;
import com.boot.model.dto.MessageDto;
import com.boot.repos.MessageRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

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
                      @Valid Message message,
                      BindingResult bindingResult,
                      Model model,
                      @RequestParam("file") MultipartFile file) throws IOException {
        message.setAuthor(user);
        if (bindingResult.hasErrors()) {
            final Map<String, String> map = ControllerUtils.getErrors(bindingResult);
            model.addAttribute("map", map);
            model.addAttribute("message", message);
        } else {
            if (Objects.nonNull(file) && !file.isEmpty()) {
                final String resultFileName = ControllerUtils.savePhoto(file);
                message.setFilename(resultFileName);
            }
            this.repo.save(message);

        }
        model.addAttribute("messages", this.repo.findAll());
        return "main";
    }

    @GetMapping("/user-messages/{user}")
    public String userMessages(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            @RequestParam(value = "message", required = false) Message message,
            Model model) {
        final Set<Message> messages = user.getMessages();
        model.addAttribute("isCurrentUser", currentUser.equals(user));
        if (Objects.nonNull(message)) {
            model.addAttribute("message", message);
            return "update";
        }
        model.addAttribute("userChannel", user);
        model.addAttribute("messages", messages);
        model.addAttribute("subscribersCount", user.getSubscribers().size());
        model.addAttribute("subscriptionsCount", user.getSubscriptions().size());
        model.addAttribute("isSubscriber", user.getSubscribers().contains(currentUser));
        return "userMessages";
    }

    @PostMapping("/user-messages/{user}")
    public String updateMessage(@AuthenticationPrincipal User currentUser,
                                @Valid MessageDto messageDto,
                                BindingResult bindingResult,
                                Model model,
                                @PathVariable Long user,
                                @RequestParam("file") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            final Map<String, String> map = ControllerUtils.getErrors(bindingResult);
            model.addAttribute("map", map);
            model.addAttribute("message", messageDto);
            return "update";
        }
        this.repo.findById(messageDto.getId()).ifPresent(message ->
        {
            if (message.getAuthor().equals(currentUser)) {
                if (Objects.nonNull(file) && !file.isEmpty()) {
                    final String resultFileName;
                    try {
                        resultFileName = ControllerUtils.savePhoto(file);
                        message.setFilename(resultFileName);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                message.setText(messageDto.getText());
                message.setTag(messageDto.getTag());
                this.repo.save(message);
            }
        });

        return String.format("redirect:/user-messages/%d", user);
    }
}
