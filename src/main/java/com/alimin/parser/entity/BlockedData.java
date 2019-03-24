package com.alimin.parser.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BlockedData {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;


    String ip;

    int count;

    String reason;

    public BlockedData(String ip,int count, String reason) {
        this.ip = ip;
        this.count=count;
        this.reason = reason;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return this.ip+" | "+this.count;
    }
}
