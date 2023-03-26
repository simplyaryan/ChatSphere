package com.aryan.chatsphere;



public class chat extends person{
    
    private String time;
    private String date;
    private String message;
    public chat(String name , String time, String date, String message){
        super(name);
        this.time = time;
        this.date = date;
        this.message = message;
    }
    public String toString(){
        return super.getName() + " " + time +" "+ date + " " + message;
    }
    public String gettime(){
        return time;
    }
    public String getdate(){
        return date;
    }
    public String getmessage(){
        return message;
    }
    public String getname(){
        return super.getName();
    }
}
