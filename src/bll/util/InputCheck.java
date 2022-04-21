package bll.util;

import java.time.LocalTime;

public class InputCheck {

    /**
     * Attempts to convert a string to a LocalTime object. Format of string must be HH:MM.
     * Returns LocalTime if successful, or null if any kind of error was encountered
     */
    public static LocalTime timeCheck(String string){
        try{
            String[] stringList= string.split(":");
            return LocalTime.of(Integer.parseInt(stringList[0]),Integer.parseInt(stringList[1]),0);
        } catch (Exception e) {
            return null;
        }
    }

}
