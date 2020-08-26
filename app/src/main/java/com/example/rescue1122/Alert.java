package com.example.rescue1122;

import java.util.HashMap;
import java.util.Map;

public class Alert {
    String Cnic,FullName,UserName,Password;
    String lat,lon;
    String status="";
    String id,servicetype,title;

    public Alert(String lat, String lon, String status, String id, String servicetype, String title) {
        this.lat = lat;
        this.lon = lon;
        this.status = status;
        this.id = id;
        this.servicetype = servicetype;
        this.title = title;
    }

    public Alert() {
    }
    @Override
    public String toString() {
        return "Alert{" +
                "Cnic='" + Cnic + '\'' +
                ", FullName='" + FullName + '\'' +
                ", UserName='" + UserName + '\'' +
                ", Password='" + Password + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", status='" + status + '\'' +
                ", id='" + id + '\'' +
                ", servicetype='" + servicetype + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public Alert(String cnic, String fullName, String userName, String password) {
        Cnic = cnic;
        FullName = fullName;
        UserName = userName;
        Password = password;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServicetype() {
        return servicetype;
    }

    public void setServicetype(String servicetype) {
        this.servicetype = servicetype;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCnic() {
        return Cnic;
    }

    public void setCnic(String cnic) {
        Cnic = cnic;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("lat", lat);
        result.put("lon", lon);
        result.put("Status",status);
        return result;
    }
}
