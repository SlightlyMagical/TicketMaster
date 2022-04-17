package bll.util;

import java.time.LocalTime;

public class InputCheck {
    public static LocalTime timeCheck(String string){
        try{
            String[] stringList= string.split(":");
            LocalTime time = LocalTime.of(Integer.parseInt(stringList[0]),Integer.parseInt(stringList[1]),0);
            return time;
        } catch (NumberFormatException e) {
            return null;
        }
    }


}
