package com.example.demo.Main_controller;


import com.example.demo.Main_model.text_message_model;
import com.example.demo.Main_model.whatsapp_sender_model;
import com.example.demo.Main_service.UserService;
import com.example.demo.Main_service.User_whatsapp_functionality;
import com.example.demo.Main_service.text_message_functionality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/whatsapp-send")
public class ActionController {

    @Autowired
    private User_whatsapp_functionality myService;

    @Autowired
    private text_message_functionality myService_text;

    @PostMapping("/send")
    public String send_action(@RequestBody whatsapp_sender_model whatsapp_object){

        myService.whatsapp_send_function(whatsapp_object.getSender_number(), whatsapp_object.getReceiver_number());

        return "ProcessEnd";
        }


    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/sendtext")
    public String send_text(@RequestBody text_message_model text_object)
    {
        try {
            myService_text.sendSms(text_object.getApiId(), text_object.getApiPassword(), text_object.getSmsType(), text_object.getEncoding(), text_object.getSenderId(), text_object.getPhoneNumber(), text_object.getTextMessage());
        }
        catch(Exception e){
            System.out.println("Error occurred " + e );


        }
        return "Message Processed successfully";

    }

}
