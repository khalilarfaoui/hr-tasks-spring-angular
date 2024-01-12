package com.example.nexus.SErvices.Implimentations;

import com.example.nexus.Repositories.EventRepo;
import com.example.nexus.SErvices.Interfaces.IEvent;
import com.example.nexus.entities.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EventService implements IEvent {
@Autowired
        EventRepo eventRepo;
        @Override
        public int AjouterEvent(Event E) {
                eventRepo.save(E);
                return E.getIdEvent();
        }

        @Override
        public List<Event> getAllEvents() {
                return eventRepo.findAll();
        }

        @Override
        public void update(Event E) {
        eventRepo.save(E);
        }

        @Override
        public void delete(int id) {
                eventRepo.deleteById(id);

        }
}
