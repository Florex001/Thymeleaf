package com.example.Thymeleaf.Controllers;

import com.example.Thymeleaf.Models.ToDoModel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TemplateController {

    private int id = 1;

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<ToDoModel> sessionToDoModels = (List<ToDoModel>) session.getAttribute("toDoModels");

        if (sessionToDoModels == null) {
            sessionToDoModels = new ArrayList<>();
            session.setAttribute("toDoModels", sessionToDoModels);
        }

        model.addAttribute("toDoModels", sessionToDoModels);
        return "index";
    }

    @PostMapping("/add")
    public String addTask(@RequestParam String task, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        List<ToDoModel> sessionToDoModels = (List<ToDoModel>) session.getAttribute("toDoModels");

        if (sessionToDoModels == null) {
            sessionToDoModels = new ArrayList<>();
            session.setAttribute("toDoModels", sessionToDoModels);
        }

        ToDoModel toDoModel = new ToDoModel();
        toDoModel.setId(id++);
        toDoModel.setName(task);
        sessionToDoModels.add(toDoModel);

        model.addAttribute("toDoModels", sessionToDoModels);
        return "redirect:/";
    }

    @PostMapping("/task-done/{id}")
    public String markTaskDone(@PathVariable("id") int id, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        List<ToDoModel> toDoModels = (List<ToDoModel>) session.getAttribute("toDoModels");

        if (toDoModels != null) {
            toDoModels = toDoModels.stream()
                    .filter(elem -> elem.getId() != id)
                    .collect(Collectors.toList());
            session.setAttribute("toDoModels", toDoModels);
        }

        model.addAttribute("toDoModels", toDoModels);
        return "redirect:/";
    }


}
