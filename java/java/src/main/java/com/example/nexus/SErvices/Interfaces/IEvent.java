package com.example.nexus.SErvices.Interfaces;

import com.example.nexus.entities.Event;

import java.util.List;

public interface IEvent {
    public int AjouterEvent (Event E) ;



    public List<Event> getAllEvents();

    public void update (Event E);
    public void delete (int  idProject);
}
