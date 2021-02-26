package cr.ac.ucr.api.model;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "Comment")
@NamedStoredProcedureQuery(name = "Comment.GetCommentByReport",procedureName = "GetCommentByReport",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "r_id", type = int.class)})

public class Comment {
    private int comment_Id;
    private String description;
    private Date comment_Timestamp;
    private int report_Number;
	private boolean state;
    private Date creation_Date;
    private Date modify_Date;
    private int created_By;
    private int modified_By;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getComment_Id() {
        return comment_Id;
    }

    public void setComment_Id(int comment_Id) {
        this.comment_Id = comment_Id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getComment_Timestamp() {
        return comment_Timestamp;
    }

    public void setComment_Timestamp(Date comment_Timestamp) {
        this.comment_Timestamp = comment_Timestamp;
    }

    public int getReport_Number() {
        return report_Number;
    }

    public void setReport_Number(int report_Number) {
        this.report_Number = report_Number;
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

    @Override
    public String toString() {
        return "Comment{" +
                "comment_Id=" + comment_Id +
                ", description='" + description + '\'' +
                ", comment_Timestamp=" + comment_Timestamp +
                ", report_Number=" + report_Number +
                ", state=" + state +
                ", creation_Date=" + creation_Date +
                ", modify_Date='" + modify_Date + '\'' +
                ", created_By=" + created_By +
                ", modified_By=" + modified_By +
                '}';
    }
}
