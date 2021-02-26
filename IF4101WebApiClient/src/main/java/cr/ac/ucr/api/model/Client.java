package cr.ac.ucr.api.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Client")
@NamedStoredProcedureQuery(name = "Client.insertClientServices",procedureName = "Insert_Client_Services",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "c_id", type = int.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "s_id", type = int.class)})

@NamedStoredProcedureQuery(name = "Client.loadByName",procedureName = "loadByName",
        parameters = {@StoredProcedureParameter(mode = ParameterMode.IN, name =
        "@email", type = String.class)})

public class Client {
    private int client_Id;
	private String name;
    private String first_Surname;
    private String second_Surname;
    private String adress;
    private String phone;
    private String email;
    private String password;
    private boolean state;
    private Date creation_Date;
    private Date modify_Date;
    private int created_By;
    private int modified_By;

    @Transient
    private List<String> services;
    @Transient
    public List<String> getServices() {
        return services;
    }
    @Transient
    public void setServices(List<String> services) {
        this.services = services;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getClient_Id() {
        return client_Id;
    }

    public void setClient_Id(int client_Id) {
        this.client_Id = client_Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst_Surname() {
        return first_Surname;
    }

    public void setFirst_Surname(String first_Surname) {
        this.first_Surname = first_Surname;
    }

    public String getSecond_Surname() {
        return second_Surname;
    }

    public void setSecond_Surname(String second_Surname) {
        this.second_Surname = second_Surname;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public int getCreated_By() {
        return created_By;
    }

    public void setCreated_By(int created_By) {
        this.created_By = created_By;
    }

    public int getModified_By() {
        return modified_By;
    }

    public void setModified_By(int modified_By) {
        this.modified_By = modified_By;
    }

    public Date getCreation_Date() {
        return creation_Date;
    }

    public void setCreation_Date(Date creation_Date) {
        this.creation_Date = creation_Date;
    }

    public Date getModify_Date() {
        return modify_Date;
    }

    public void setModify_Date(Date modify_Date) {
        this.modify_Date = modify_Date;
    }

    @Override
    public String toString() {
        return "Client{" +
                "client_Id=" + client_Id +
                ", name='" + name + '\'' +
                ", first_Surname='" + first_Surname + '\'' +
                ", second_Surname='" + second_Surname + '\'' +
                ", adress='" + adress + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", state=" + state +
                ", creation_Date='" + creation_Date + '\'' +
                ", modify_Date='" + modify_Date + '\'' +
                ", created_By=" + created_By +
                ", modified_By=" + modified_By +
                '}';
    }
}
