package com.example.nexus.controllers;

import com.example.nexus.SErvices.Implimentations.EventService;
import com.example.nexus.entities.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/event")

public class EventController {
    @Autowired
    EventService eventService;
    @PostMapping("/addevent")
    public void ajouteevent(@RequestBody Event event){
        eventService.AjouterEvent(event);
    }
    @GetMapping(value = "/events")
    @ResponseBody
    public List<Event> getAllevent() {
        return eventService.getAllEvents();
    }


    @PutMapping("/updateevent ")
    @ResponseBody
    public void updateevent(@RequestBody Event R){
        eventService.update(R);
    }

    @DeleteMapping("/deleteproject/{id}")
    @ResponseBody
    public void deleteevent (@PathVariable  int  idEvent  ){
        eventService.delete(idEvent);
    }

}
