package com.termos.scooterrental.time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public  class TimeUtils {

    public static Timestamp NowTimeStamp() {
        LocalDateTime ldt = LocalDateTime.now();
        Timestamp t = Timestamp.valueOf(ldt);
        return t;
    }

}
