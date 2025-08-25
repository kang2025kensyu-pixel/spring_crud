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
//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝削除押したらエラー＝＝＝＝＝＝＝＝＝＝＝＝
//Whitelabel Error Page
//This application has no explicit mapping for /error, so you are seeing this as a fallback.
//http://localhost:7777/spring_crud/regist_check
//Mon Aug 25 17:54:01 JST 2025
//There was an unexpected error (type=Not Found, status=404).
//No message available
//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
    @PostMapping("/delete/{id}")
    public String deleteRecord(@PathVariable Long id) {
    
        return "delete_complete"; 
    }
}