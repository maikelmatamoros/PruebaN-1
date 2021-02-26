package cr.ac.ucr.api.service;

import cr.ac.ucr.api.model.ServiceT;
import cr.ac.ucr.api.repository.ServiceTRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServiceTService {
    @Autowired
    private ServiceTRepository repository;

    public List<ServiceT> getServicesByClient(int id){
        return repository.getServicesByClient(id);
    }

}
