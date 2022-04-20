package bll.util;

import java.time.LocalTime;

public class InputCheck {

    public static LocalTime timeCheck(String string){
        try{
            String[] stringList= string.split(":");
            return LocalTime.of(Integer.parseInt(stringList[0]),Integer.parseInt(stringList[1]),0);
        } catch (Exception e) {
            return null;
        }
    }

}
