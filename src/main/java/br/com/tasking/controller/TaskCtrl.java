package br.com.tasking.controller;

import br.com.tasking.entity.Task;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
public class TaskCtrl {

    @PostMapping("/novatask")
    private ModelAndView novaTask(@Valid @ModelAttribute("task") Task task,
                                  BindingResult bindingResult) {
        System.out.println(task.toString());
        return new ModelAndView("/home");
    }
}
