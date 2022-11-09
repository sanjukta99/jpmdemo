package com.shows.jpmdemo.utils;

import java.util.*;

public class ShowUtils {
    public static Set<String> createSeatList(int numRows, int numSeatsPerRow) {

        Set<String> seatList = new LinkedHashSet<>();

        for(int i = 1; i <= numRows; i++){
            String row = String.valueOf((char)(i + 64));
            for(int j = 1; j <= numSeatsPerRow; j++){
                seatList.add(String.format("%s%s", row,j));
            }
        }
        return seatList;
    }

    public static List<String> parseSeatString(String seatSelection) {
        return Arrays.asList(seatSelection.split(","));
    }

    public static Boolean isCancellable(Date expirationTime) {
        Date currentTime = new Date(System.currentTimeMillis());
        if(currentTime.compareTo(expirationTime) > 0) {
            return  Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }

}
