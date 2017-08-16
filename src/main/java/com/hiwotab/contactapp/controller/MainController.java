package com.hiwotab.contactapp.controller;

import com.hiwotab.contactapp.model.Contact;
import com.hiwotab.contactapp.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
@Controller
public class MainController {

    @Autowired
    ContactRepository contactRepository;
    @GetMapping("/homePage")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/addContact")
    public String InputForm(Model model){
        model.addAttribute("contact", new Contact());
        return "addContact";
    }
    @PostMapping("/addContact")
    public String saveForm(@Valid Contact contact, BindingResult result){
        if (result.hasErrors()){
            return "addContact";
        }
        contactRepository.save(contact);
        return "redirect:/";
    }
    @RequestMapping("/update/{id}")
    public String updateCourse(@PathVariable("id") long id, Model model){
        model.addAttribute("contact", contactRepository.findOne(id));
        return "addContact";
    }

    @RequestMapping("/delete/{id}")
    public String delCourse(@PathVariable("id") long id){
        contactRepository.delete(id);
        return "redirect:/";
    }
    @RequestMapping("/detail/{id}")
    public String showCourse(@PathVariable("id") long id, Model model){
        model.addAttribute("contact", contactRepository.findOne(id));
        return "show";
    }
    @RequestMapping("/")
    public String listCourses(Model model){
        model.addAttribute("contacts", contactRepository.findAll());
        return "list";
    }
}
