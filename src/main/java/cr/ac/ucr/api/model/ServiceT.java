package cr.ac.ucr.api.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ServiceT")

@NamedStoredProcedureQuery(name = "ServiceT.getServicesByClient",procedureName = "Get_Services_By_Client",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "c_id", type = int.class)
        })
public class ServiceT {

    private int service_Id;
    private String name;

    public ServiceT() {
    }

    public ServiceT(int service_Id, String name) {
        this.service_Id = service_Id;
        this.name = name;
    }

    @Id
    public int getService_Id() {
        return service_Id;
    }

    public void setService_Id(int service_Id) {
        this.service_Id = service_Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
