package jp.co.sss.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DeleteController {

   
    @RequestMapping("/delete/{id}")
    public String showConfirmDeletePage(@PathVariable Long id) {
    
        return "delete_check"; 
    }

    @PostMapping("/delete/{id}")
    public String deleteRecord(@PathVariable Long id) {
    
        return "delete_complete"; 
    }
}