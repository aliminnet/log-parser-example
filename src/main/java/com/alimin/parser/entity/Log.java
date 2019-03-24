package com.alimin.parser.entity;


import javax.persistence.*;
import java.util.Date;


@Entity
public class Log {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private String ip;

	private String request;

	private String status;

	private String userAgent;

    public Integer getId() {
        return id;
    }

    public Log() {
       super();
    }

    public Log(Date date, String ip, String request, String status, String userAgent) {
        this.date = date;
        this.ip = ip;
        this.request = request;
        this.status = status;
        this.userAgent = userAgent;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return this.date+"|"+this.ip+"|"+this.request+"|"+this.status+"|"+this.userAgent;
    }
}

