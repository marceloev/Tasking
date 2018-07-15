package br.com.tasking.controller;

import br.com.tasking.entity.Task;
import br.com.tasking.service.TaskService;
import br.com.tasking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
public class HomeCtrl {

    @Autowired
    private TaskService service;
    @Autowired
    private UserService userService;

    @RequestMapping("/home")
    private ModelAndView home(@RequestParam(value = "all", required = false) String all,
                              Task task) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView modelAndView = new ModelAndView("/home");
        if (all != null) {
            modelAndView.getModelMap().addAttribute("tasks", service.findAll(auth.getName()));
        } else {
            modelAndView.getModelMap().addAttribute("tasks", service.findPendentes(auth.getName()));
            modelAndView.getModelMap().addAttribute("all", true);
        }
        modelAndView.getModelMap().addAttribute("task", task);
        return modelAndView;
    }

    @PostMapping("/novatask")
    private ModelAndView novaTask(@Valid @ModelAttribute("task") Task task,
                                  BindingResult bindingResult) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        task.setUsuario(userService.findByLoginWithoutThrows(auth.getName()));
        System.out.println(task.toString());
        service.save(task);
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        modelAndView.getModelMap().addAttribute("task", task);
        return modelAndView;
    }

}
