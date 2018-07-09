package br.com.tasking.controller;

import br.com.tasking.entity.Task;
import br.com.tasking.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
public class HomeCtrl {

    @Autowired
    private TaskService service;

    @RequestMapping("/home")
    private ModelAndView home() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView modelAndView = new ModelAndView("/home");
        modelAndView.getModelMap().addAttribute("tasks", service.findPendentes(auth.getName()));
        return modelAndView;
    }

    @RequestMapping("/home/all")
    private ModelAndView homeAll() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView modelAndView = new ModelAndView("/home");
        modelAndView.getModelMap().addAttribute("tasks", service.findAll(auth.getName()));
        return modelAndView;
    }

}
