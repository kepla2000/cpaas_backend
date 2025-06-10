package com.example.demo.Main_controller;


import com.example.demo.Main_model.whatsapp_sender_model;
import com.example.demo.Main_service.UserService;
import com.example.demo.Main_service.User_whatsapp_functionality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/whatsapp-send")
public class ActionController {

    @Autowired
    private User_whatsapp_functionality myService;

    @PostMapping("/send")
    public String send_action(@RequestBody whatsapp_sender_model whatsapp_object){

        myService.whatsapp_send_function(whatsapp_object.getSender_number(), whatsapp_object.getReceiver_number());

        return "ProcessEnd";
    }

}
