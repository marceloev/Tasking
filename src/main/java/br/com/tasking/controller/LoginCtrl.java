package br.com.tasking.controller;

import br.com.tasking.entity.User;
import br.com.tasking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@RestController
public class LoginCtrl {

    @Autowired
    UserService service;

    @RequestMapping("/")
    private ModelAndView none() {
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        return modelAndView;
    }

    @RequestMapping("/login")
    private ModelAndView login(@RequestParam(value = "error", required = false) String error,
                               @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView modelAndView = new ModelAndView("/login");
        if (error != null)
            modelAndView.getModelMap().addAttribute("error", "Usuário/Senha inválidos");
        else if (logout != null)
            modelAndView.getModelMap().addAttribute("mensagem", "Logout efetuado com sucesso");
        return modelAndView;
    }

    @RequestMapping("/cadastro")
    private ModelAndView cadastro(User user) {
        ModelAndView modelAndView = new ModelAndView("/cadastro");
        return modelAndView;
    }

    @PostMapping("/cadastro")
    private ModelAndView cadastro(@Valid @ModelAttribute("user") User user,
                                  BindingResult bindingResult,
                                  RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            return cadastro(user);
        }
        user.setTelefone(user.getTelefone().replaceAll("[^0-9]", ""));
        user.setSenha(new BCryptPasswordEncoder().encode(user.getSenha()));
        service.save(user);
        ModelAndView modelAndView = new ModelAndView("redirect:/login");
        attributes.addFlashAttribute("mensagem", "Usuário cadastrado com sucesso");
        return modelAndView;
    }
}
