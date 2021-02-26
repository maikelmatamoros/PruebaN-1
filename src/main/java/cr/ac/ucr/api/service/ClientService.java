package cr.ac.ucr.api.service;

import cr.ac.ucr.api.model.Client;
import cr.ac.ucr.api.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ClientService {

    @Autowired
    private ClientRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Client> listAll() {
        return repository.findAll();
    }

    public void save(Client client) {
        client.setCreation_Date(new Date());
        client.setState(true);
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        repository.save(client);
        int id = client.getClient_Id();
        for (int i=0;i<client.getServices().size();i++){
            repository.insertClientServices(id,Integer.parseInt(client.getServices().get(i)));
        }
    }

    public void update(Client client) {
        repository.save(client);
    }

    public Client getByEmail(String emal) {
        return repository.loadByName(emal);
    }


    public Client get(int id) {
        return repository.findById(id).get();
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

}
