/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author vothimaihoa
 */
public class Report {
    private int reportId;
    private KingRequest request;
    private Date writtenDate;
    private Timestamp deliveredDate;
    private String location;
    private String reportData;
    private Boolean link;
    private String title;
    private String status;
    private String comment;
    private Consul consul;

    public Report() {
    }

    public Report(KingRequest request, Date writtenDate, Timestamp deliveredDate, String location, String reportData, Boolean link, String title, String status, String comment, Consul consul) {
        this.request = request;
        this.writtenDate = writtenDate;
        this.deliveredDate = deliveredDate;
        this.location = location;
        this.reportData = reportData;
        this.link = link;
        this.title = title;
        this.status = status;
        this.comment = comment;
        this.consul = consul;
    }
    
    

    public Report(int reportId, KingRequest request, Date writtenDate, 
            Timestamp deliveredDate, String location, String reportData, Boolean link, String title) {
        this.reportId = reportId;
        this.request = request;
        this.writtenDate = writtenDate;
        this.deliveredDate = deliveredDate;
        this.location = location;
        this.reportData = reportData;
        this.link = link;
        this.title = title;
    }

    public Report(KingRequest request, Date writtenDate, Timestamp deliveredDate, String location, String reportData, Boolean link, String title) {
        this.request = request;
        this.writtenDate = writtenDate;
        this.deliveredDate = deliveredDate;
        this.location = location;
        this.reportData = reportData;
        this.link = link;
        this.title = title;
    }

    

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public Date getWrittenDate() {
        return writtenDate;
    }

    public void setWrittenDate(Date writtenDate) {
        this.writtenDate = writtenDate;
    }

    public Timestamp getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(Timestamp deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getReportData() {
        return reportData;
    }

    public void setReportData(String reportData) {
        this.reportData = reportData;
    }

    public Boolean getLink() {
        return link;
    }

    public void setLink(Boolean link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public KingRequest getRequest() {
        return request;
    }

    public void setRequest(KingRequest request) {
        this.request = request;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Consul getConsul() {
        return consul;
    }

    public void setConsul(Consul consul) {
        this.consul = consul;
    }

    @Override
    public String toString() {
        return "Report{" + "reportId=" + reportId + ", request=" + request + ", writtenDate=" + writtenDate + ", deliveredDate=" + deliveredDate + ", location=" + location + ", reportData=" + reportData + ", link=" + link + ", title=" + title + ", status=" + status + ", comment=" + comment + ", consul=" + consul + '}';
    }
        
}
