package com.shows.jpmdemo.model;

import java.util.List;

public class Booking {
    private final int showNumber;
    private final String phoneNumber;
    private final List<String> seatSelection;

    public Booking(
            int showNumber,
            String phoneNumber,
            List<String> seatSelection
    ){
        this.showNumber = showNumber;
        this.phoneNumber = phoneNumber;
        this.seatSelection = seatSelection;
    }

    public int getShowNumber() {
        return showNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<String> getSeatSelection(){
        return seatSelection;
    }

    public String getTicketNumber(){
        return String.format("%s%s", showNumber, phoneNumber);
    }
}
