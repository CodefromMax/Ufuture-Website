package com.example.cms.controller;
import com.example.cms.controller.dto.EventDto;
import com.example.cms.controller.exceptions.UniversityNotFoundException;
import com.example.cms.controller.exceptions.UserNotFoundException;
import com.example.cms.controller.vo.UserVO;
import com.example.cms.model.entity.All_universities;
import com.example.cms.model.entity.Events;
import com.example.cms.model.entity.StudentUser;
import com.example.cms.model.entity.UniversityUser;
import com.example.cms.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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
    List<Events> showAllEvents(@RequestParam( required = false) String userId) {

        List<Events> events = repository.showAllEvents();
        ArrayList<Events> result = new ArrayList<>();
        if (userId != null && !"".equals(userId)) {
            for (Events event : events) {
                if (Objects.equals(event.getUniversityUser().getUserId(), userId)) {
                    result.add(event);
                }
            }
            return result;
        }
        return events;
    }

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

        if (eventDto.getUniversityUserId() == null || Objects.equals("", eventDto.getUniversityUserId())) {
            return null;
        }

        eventDto.setUniversityId(eventDto.getUniversityUserId());

        Events newEvent = new Events();
        newEvent.setEventCode(repository.count()+1);
        newEvent.setEventName(eventDto.getEventName());
        newEvent.setEventDate(eventDto.getEventDate());
        newEvent.setLocation(eventDto.getLocation());
//        All_universities university = universityRepository.findById(eventDto.getUniversityId()).orElseThrow(
//                () -> new UniversityNotFoundException(eventDto.getUniversityId()));
//        newEvent.setUniversity(university);
        UniversityUser universityUser = univeristyUserRepository.findById(eventDto.getUniversityUserId()).orElseThrow(
                () -> new UserNotFoundException(eventDto.getUniversityUserId()));
        newEvent.setUniversityUser(universityUser);
        return repository.save(newEvent);
    }
    @PutMapping("/events/{eventId}")
    Events updateEvent(@RequestBody EventDto eventDto,
                       @PathVariable("eventId") long eventId){

        return repository.findById(eventId).map(event -> {
            event.setEventName(eventDto.getEventName());
            event.setEventDate(eventDto.getEventDate());
            event.setLocation(eventDto.getLocation());
            return repository.save(event);
        }).orElseGet(()->{
            return this.createNewEvent(eventDto);
        });
    }
    @DeleteMapping("/events/{eventId}")
    void deleteEvent(@PathVariable("eventId") long ID) {
        repository.deleteById(ID);
    }





}

