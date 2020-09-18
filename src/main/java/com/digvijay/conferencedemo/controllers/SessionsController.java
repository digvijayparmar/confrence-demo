package com.digvijay.conferencedemo.controllers;

import com.digvijay.conferencedemo.models.Session;
import com.digvijay.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionsController {
    @Autowired //when this class is created, spring will inject the instance of SessionRepository into this class
    private SessionRepository sessionRepository ;

    //create a list end point - Get all sessions
    @GetMapping //tells that which http verb would be used - in this case get
    public List<Session> list(){
     return  sessionRepository.findAll(); //This is how powerful jpa is - it builds this method for us
    }

    //create a list end point - Get single session
    @GetMapping
    @RequestMapping("{id}") // Adding an additional part to existing class level url on thr top
    public Session get(@PathVariable Long id){ //extract id off of the url
        return sessionRepository.getOne(id);
    }

    //Create Operation
    @PostMapping
    public Session put(@RequestBody final Session session){ //Spring will automatically convert session jSON data in to session Java object
        return sessionRepository.saveAndFlush(session);//saveAndFlush -> Save and Commit
    }

    //Delete Operation
    @RequestMapping(value ="{id}", method = RequestMethod.DELETE)
    public void delete (@PathVariable Long id){
        //Take care of children records
        sessionRepository.deleteById(id);
    }

    //Update Operation
    @RequestMapping(value = "{id}", method = RequestMethod.PUT) //PATCH for only attributes not whole session
    public void update(@PathVariable Long id, @RequestBody Session session){
        //Do the request validation else it will be 400 error
        //put the new primary key and save the record
        //get the existing session based on the session Id
        Session existingSession = sessionRepository.getOne(id);
        BeanUtils.copyProperties(session,existingSession,"session_id");
        sessionRepository.saveAndFlush(existingSession);

    }
}
