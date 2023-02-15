package xpoke.space.cuboids.utils;

import java.text.*;
import java.util.*;

public class DateUtil {

    public static String getDate(){
        String pattern = "dd.MM.yyyy HH:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Warsaw"));
        return dateFormat.format(new Date());
    }

}
