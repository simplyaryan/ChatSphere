package com.aryan.chatsphere;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class dateandtime {

    public static String getTime(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = now.format(timeFormatter);
        return formattedTime;
    }
    public static String getDate(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = now.format(dateFormatter);
        return formattedDate;
    }
}
