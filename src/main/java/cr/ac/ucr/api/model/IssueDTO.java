package cr.ac.ucr.api.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

@Entity
public class IssueDTO {

    private int ReportNumber;
    private char Status;
    private String ReportTimestamp;
    private int Service;
    private boolean State;
    private String CreationDate;
    private String ModifyDate;
    private int CreatedBy;
    private int ModifiedBy;

    private String NameClient;
    private String EmailClient;
    private int PhoneClient;
    private String Address;
    private String EmailSecondContact;
    private int PhoneSecondContact;

    public IssueDTO(){}

    public IssueDTO(int reportNumber, String nameClient, String emailClient, int phoneClient, String address, String emailSecondContact, int phoneSecondContact) {
        ReportNumber = reportNumber;
        NameClient = nameClient;
        EmailClient = emailClient;
        PhoneClient = phoneClient;
        Address = address;
        EmailSecondContact = emailSecondContact;
        PhoneSecondContact = phoneSecondContact;
    }

    public IssueDTO(int reportNumber, char status, Date reportTimestamp, int service, boolean state, Date creationDate, Date modifyDate, int createdBy, int modifiedBy) {
        ReportNumber = reportNumber;
        Status = status;

        ReportTimestamp = LocalDateTime.of(
                reportTimestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                LocalTime.MIDNIGHT).toString();
        Service = service;
        State = state;
        CreationDate = LocalDateTime.of(
                creationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                LocalTime.MIDNIGHT).toString();
        ModifyDate = LocalDateTime.of(
                modifyDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                LocalTime.MIDNIGHT).toString();
        CreatedBy = createdBy;
        ModifiedBy = modifiedBy;
    }

    @Id
    public int getReportNumber() {
        return ReportNumber;
    }

    public void setReportNumber(int reportNumber) {
        ReportNumber = reportNumber;
    }

    public char getStatus() {
        return Status;
    }

    public void setStatus(char status) {
        Status = status;
    }

    public String getReportTimestamp() {
        return ReportTimestamp;
    }

    public void setReportTimestamp(String reportTimestamp) {
        ReportTimestamp = reportTimestamp;
    }

    public int getService() {
        return Service;
    }

    public void setService(int service) {
        Service = service;
    }

    public boolean isState() {
        return State;
    }

    public void setState(boolean state) {
        State = state;
    }

    public String getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(String creationDate) {
        CreationDate = creationDate;
    }

    public String getModifyDate() {
        return ModifyDate;
    }

    public void setModifyDate(String modifyDate) {
        ModifyDate = modifyDate;
    }

    public int getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(int createdBy) {
        CreatedBy = createdBy;
    }

    public int getModifiedBy() {
        return ModifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        ModifiedBy = modifiedBy;
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

    public int getPhoneClient() {
        return PhoneClient;
    }

    public void setPhoneClient(int phoneClient) {
        this.PhoneClient = phoneClient;
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

    public int getPhoneSecondContact() {
        return PhoneSecondContact;
    }

    public void setPhoneSecondContact(int phoneSecondContact) {
        PhoneSecondContact = phoneSecondContact;
    }
}
