package com.digvijay.conferencedemo.controllers;

import com.digvijay.conferencedemo.models.Session;
import com.digvijay.conferencedemo.models.Speaker;
import com.digvijay.conferencedemo.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakersController {
    @Autowired
    private SpeakerRepository speakerRepository;
    //Get All
    @GetMapping
    @RequestMapping
    public List<Speaker> list(){
        return speakerRepository.findAll();
    }

    //Get One
    @GetMapping
    @RequestMapping("{id}")
    public Speaker get(@PathVariable Long id){
    return speakerRepository.getOne(id);
    }

    //Create
    @PostMapping
    public void create(@RequestBody Speaker speaker){
        speakerRepository.saveAndFlush(speaker);
    }

    //Delete
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        //Handle children records;
        speakerRepository.deleteById(id);
    }

    //Update
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public void update(@RequestBody Speaker speaker, @PathVariable Long id){
        Speaker existingSpeaker = speakerRepository.getOne(id);
        BeanUtils.copyProperties(speaker,existingSpeaker,"speaker_id");
        speakerRepository.saveAndFlush(existingSpeaker);
    }
}
