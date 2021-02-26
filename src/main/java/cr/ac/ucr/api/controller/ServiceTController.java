package cr.ac.ucr.api.controller;

import cr.ac.ucr.api.model.ServiceT;
import cr.ac.ucr.api.service.ServiceTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/api/services")
public class ServiceTController {

    @Autowired
    private ServiceTService service;

    @GetMapping("/byClient/{id}")
    public List<ServiceT> getServicesByClient(@PathVariable Integer id) {
        return service.getServicesByClient(id);
    }
}
