package ru.project.sivanov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.project.sivanov.models.Message;
import ru.project.sivanov.repository.MessageRepo;

import java.util.List;
import java.util.Map;

@Controller
public class GreetingController {

    private final MessageRepo messageRepo;

    public GreetingController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name = "name", required = false, defaultValue = "World") String name,
            Map<String, Object> model) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping
    public String main(Map<String, Object> model) {
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        Message message = new Message(text, tag);

        messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model){
        Iterable<Message> messages;
        if(filter != null && filter.isEmpty()){
            messages = messageRepo.findByTag(filter);
            model.put("messages", messages);
        }else{
            messages = messageRepo.findAll();
        }
        return "main";
    }

    @PostMapping("clear")
    public String clear(Map<String, Object> model){
        messageRepo.deleteAll();
        return "main";
    }
}
