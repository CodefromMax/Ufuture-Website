package com.example.cms.controller;
import com.example.cms.controller.dto.EventDto;
import com.example.cms.controller.exceptions.UniversityNotFoundException;
import com.example.cms.controller.exceptions.UserNotFoundException;
import com.example.cms.model.entity.All_universities;
import com.example.cms.model.entity.Events;
import com.example.cms.model.entity.UniversityUser;
import com.example.cms.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController


public class EventsController {

    @Autowired
    private final EventsRepository repository;

    @Autowired
    private UniversityUserRepository univeristyUserRepository;

    @Autowired
    private All_universitiesRepository universityRepository;

    public EventsController(EventsRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/events")
    List<Events> showAllEvents() {return repository.showAllEvents();}

    @GetMapping("/events/{uniId}")
    List<Events> showAllEventsForUniversity(@PathVariable("uniId") String uniId){
        return repository.showAllEventsForUniversity(uniId);
    }

    @GetMapping("/events/count/{uniId}")
    Long countEventsForUniversity(@PathVariable ("uniId") String uniId){
        return repository.CountAllEventsForUniversity(uniId);
    }

    @PostMapping ("/events/post")
    Events createNewEvent(@RequestBody EventDto eventDto){
        Events newEvent = new Events();
        newEvent.setEventCode(repository.count()+1);
        newEvent.setEventName(eventDto.getEventName());
        newEvent.setEventDate(eventDto.getEventDate());
        newEvent.setLocation(eventDto.getLocation());
        All_universities university = universityRepository.findById(eventDto.getUniversityId()).orElseThrow(
                () -> new UniversityNotFoundException(eventDto.getUniversityId()));
        newEvent.setUniversity(university);
        UniversityUser universityUser = univeristyUserRepository.findById(eventDto.getUniversityUserId()).orElseThrow(
                () -> new UserNotFoundException(eventDto.getUniversityUserId()));
        newEvent.setUniversityUser(universityUser);
        return repository.save(newEvent);
    }
    @DeleteMapping("/discussion/{eventId}")
    void deleteEvent(@PathVariable("eventId") long ID) { repository.deleteById(ID);
    }





}

