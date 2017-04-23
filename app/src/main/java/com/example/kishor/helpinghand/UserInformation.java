package com.example.kishor.helpinghand;

public class UserInformation {


    public String profession;
    public String location;
    public double latitude;
    public double longitude;


    public UserInformation() {

    }

    public UserInformation(String profession, String location, double latitude, double longitude) {
        this.profession = profession;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public double getLongitude(){
        return  longitude;
    }
    public void setLongitude(double longitude){
        this.longitude=longitude;
    }
    public double getLatitude(){
        return latitude;
    }
   public void setLatitude(double latitude){
       this.latitude=latitude;
   }

}

