package cr.ac.ucr.api.controller;

import cr.ac.ucr.api.model.Client;

import cr.ac.ucr.api.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin()
@RestController
@RequestMapping(path = "/api/client")
public class ClientController {
    @Autowired
    private ClientService service;

    @GetMapping("/clients")
    public List<Client> list() {
        return service.listAll();
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> get(@PathVariable Integer id) {
        try {
            Client client = service.get(id);
            client.setPassword(client.getPassword());
            return new ResponseEntity<Client>(client, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<Client> getByName(@PathVariable String name) {
        try {
            Client client = service.getByEmail(name);
            return new ResponseEntity<Client>(client, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public void add(@RequestBody Client client) {

        service.save(client);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Client> update(@PathVariable Integer id,@RequestBody Client client) {
        try {
            System.out.println(client.toString() + " ID:"+id);
            Client aux= service.get(id);
            aux.setFirst_Surname(client.getFirst_Surname());
            aux.setAdress(client.getAdress());
            aux.setSecond_Surname(client.getSecond_Surname());
            aux.setPhone(client.getPhone());
            aux.setName(client.getName());
            aux.setModified_By(id);
            aux.setModify_Date(new Date());
            System.out.println(aux.toString());
            service.update(aux);
            return new ResponseEntity<Client>(client, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
