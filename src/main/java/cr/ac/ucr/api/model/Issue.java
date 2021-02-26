package cr.ac.ucr.api.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Issue")
@NamedStoredProcedureQuery(name = "Issue.listIssue",procedureName = "listIssue",
parameters = {
@StoredProcedureParameter(mode = ParameterMode.IN, name = "@id", type = int.class)})
public class Issue {

    private int report_Number;
    private String description;
    private Date register_Timestamp;
    private String adress;
    private int contact_Phone;
    private String contact_Email;
    private char status;
    private int supporter_Assigned;
    private int service_Id;
    private int client_Id;
    private boolean state;
    private Date creation_Date;
    private Date modify_Date;
    private int created_By;
    private int modified_By;

    public Issue(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getReport_Number() {
        return report_Number;
    }

    public void setReport_Number(int report_Number) {
        this.report_Number = report_Number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getRegister_Timestamp() {
        return register_Timestamp;
    }

    public void setRegister_Timestamp(Date register_Timestamp) {
        this.register_Timestamp = register_Timestamp;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getContact_Phone() {
        return contact_Phone;
    }

    public void setContact_Phone(int contact_Phone) {
        this.contact_Phone = contact_Phone;
    }

    public String getContact_Email() {
        return contact_Email;
    }

    public void setContact_Email(String contact_Email) {
        this.contact_Email = contact_Email;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public int getSupporter_Assigned() {
        return supporter_Assigned;
    }

    public void setSupporter_Assigned(int supporter_Assigned) {
        this.supporter_Assigned = supporter_Assigned;
    }

    public int getService_Id() {
        return service_Id;
    }

    public void setService_Id(int service_Id) {
        this.service_Id = service_Id;
    }

    public int getClient_Id() {
        return client_Id;
    }

    public void setClient_Id(int client_Id) {
        this.client_Id = client_Id;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
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
}
