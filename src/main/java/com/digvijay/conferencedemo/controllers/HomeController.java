package com.digvijay.conferencedemo.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {
    //Spring will get the value from the property file
    @Value("{app.version}")
    private String appVersion;

    @GetMapping
    @RequestMapping("/")
    public Map getStatus(){
        Map customPropertiesMap = new HashMap<String, String>();
        customPropertiesMap.put("app.version",appVersion);
        //Get the custom property from application properties file
        //Jackson library will auto-marshal map into JSON
        return customPropertiesMap;
    }
}
