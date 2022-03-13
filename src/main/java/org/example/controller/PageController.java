package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("${api-base-url}/")
public class PageController {
    @GetMapping("/mainAuthorized")
    String mainAuthorized(){
        return "mainAuthorized";
    }

    @GetMapping("/authorization")
    String authorization() {
        return "authorization";
    }

    @GetMapping("/registration")
    String registration() {
        return "registration";
    }

    @GetMapping("/mainUnauthorized")
    String mainUnauthorized(){
        return "mainUnauthorized";
    }

    @GetMapping("/listOfNotes")
    String listOfNotes(){
        return "listOfNotes";
    }

    @GetMapping("/addNote")
    String addNote(){
        return "addNote";
    }
}
