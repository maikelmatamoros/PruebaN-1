package cr.ac.ucr.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;

@Entity
@NamedStoredProcedureQuery(name = "Report.GetReportData",procedureName = "GetReportData",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "@r_id", type = int.class)})
public class Report {

    private Integer ReportNumber;
    private String NameClient;
    private String EmailClient;
    private Integer PhoneClient;
    private String Address;
    private String EmailSecondContact;
    private Integer PhoneSecondContact;

    public Report() { }

    @Id
    public Integer getReportNumber() {
        return ReportNumber;
    }

    public void setReportNumber(Integer reportNumber) {
        ReportNumber = reportNumber;
    }

    public String getNameClient() {
        return NameClient;
    }

    public void setNameClient(String nameClient) {
        NameClient = nameClient;
    }

    public String getEmailClient() {
        return EmailClient;
    }

    public void setEmailClient(String emailClient) {
        EmailClient = emailClient;
    }

    public Integer getPhoneClient() {
        return PhoneClient;
    }

    public void setPhoneClient(Integer phoneClient) {
        PhoneClient = phoneClient;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmailSecondContact() {
        return EmailSecondContact;
    }

    public void setEmailSecondContact(String emailSecondContact) {
        EmailSecondContact = emailSecondContact;
    }

    public Integer getPhoneSecondContact() {
        return PhoneSecondContact;
    }

    public void setPhoneSecondContact(Integer phoneSecondContact) {
        PhoneSecondContact = phoneSecondContact;
    }
}
