package com.shows.jpmdemo.utils;

public class ValidateInputs {

    public static Boolean isValidNumRows(int numRows) {
        if(numRows > 0 && numRows <=26){
            return Boolean.TRUE;
        }else {
            return Boolean.FALSE;
        }
    }

    public static Boolean isValidNumSeatsPerRow(int numSeatsPerRow) {
        if(numSeatsPerRow > 0 && numSeatsPerRow <=10){
            return Boolean.TRUE;
        }else {
            return Boolean.FALSE;
        }
    }

    public static Boolean isValidCancellationWindow(int cancellationWindow){
        if(cancellationWindow < 0){
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }
}
