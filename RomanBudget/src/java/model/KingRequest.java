package model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author vothimaihoa
 */
public class KingRequest {
    private int id;
    private int regionId;
    private Timestamp requestDate;
    private String requestContent;
    private Date deadline;
    private boolean done;

    public KingRequest() {
    }

    public KingRequest(int id, int regionId, Timestamp requestDate, String requestContent, Date deadline, boolean isDone) {
        this.id = id;
        this.regionId = regionId;
        this.requestDate = requestDate;
        this.requestContent = requestContent;
        this.deadline = deadline;
        this.done = isDone;
    }    

    public KingRequest(int regionId, Timestamp requestDate, String requestContent, Date deadline) {
        this.regionId = regionId;
        this.requestDate = requestDate;
        this.requestContent = requestContent;
        this.deadline = deadline;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public Timestamp getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Timestamp requestDate) {
        this.requestDate = requestDate;
    }

    public String getRequestContent() {
        return requestContent;
    }

    public void setRequestContent(String requestContent) {
        this.requestContent = requestContent;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
    
    @Override
    public String toString() {
        return "KingRequest{" + "id=" + id + ", regionId=" + regionId + ", requestDate=" + requestDate + ", requestContent=" + requestContent + ", deadline=" + deadline + ", isDone=" + done + '}';
    }
    
}
