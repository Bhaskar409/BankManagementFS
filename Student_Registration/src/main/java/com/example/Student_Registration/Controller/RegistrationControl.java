package com.example.Student_Registration.Controller;

import com.example.Student_Registration.Model.Event;
import com.example.Student_Registration.Service.RegistrationService;

import org.springframework.web.bind.annotation.*;       //telling the this is rest file
import org.springframework.http.HttpStatus;             //status 202,404
import org.springframework.http.ResponseEntity;         //get, put response

import java.util.List;


@RestController
@RequestMapping("/regs")
public class RegistrationControl {
    private final RegistrationService regser;

    public RegistrationControl(RegistrationService regservice)
    {
        this.regser = regservice;
    }

    @GetMapping         //("/regs")
    public List<Event> allStudent(){
        return regser.allStudent();
    }

    @GetMapping("/{id}")
    Event getStudentById(@PathVariable int id)
    {
        return regser.getStudentById(id);
    }


//    @PostMapping
//    public Event addEvent(@RequestBody Event event)
//    {
//        return regser.addEvent(event);
//    }


    @PostMapping
    public ResponseEntity<?> addEvent(@RequestBody Event event) {
        try {
            Event saved = regser.addEvent(event);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(saved);
        } catch (IllegalStateException ex) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(ex.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public boolean deleteEvent(@PathVariable int id)
    {
        return regser.deleteEvent(id);
    }

}