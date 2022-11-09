package com.shows.jpmdemo.model;

import com.shows.jpmdemo.utils.ShowUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Show {
    private final int showNumber;
    private final int numRows;
    private final int numSeatsPerRow;
    private final int cancellationWindow;
    private final Date expirationTime;
    private Set<String> availableSeatList;
    private Set<String> bookedSeatList;

    public Show(
            int showNumber,
            int numRows,
            int numSeatsPerRow,
            int cancellationWindow
    ) {
        this.showNumber = showNumber;
        this.numRows = numRows;
        this.numSeatsPerRow = numSeatsPerRow;
        this.cancellationWindow = cancellationWindow;
        this.expirationTime = new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(cancellationWindow));
        this.bookedSeatList = new LinkedHashSet<>();
        this.availableSeatList = ShowUtils.createSeatList(numRows, numSeatsPerRow);
    }

    public int getShowNumber() {
        return showNumber;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumSeatsPerRow() {
        return numSeatsPerRow;
    }

    public int getCancellationWindow() {
        return cancellationWindow;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public Set<String> getBookedSeatList() { return bookedSeatList; }

    public Set<String> getAvailableSeatList() { return availableSeatList; }

    public Boolean bookSeats(List<String> booking) {
        for (int i = 0; i < booking.size(); i++){
            if(availableSeatList.contains(booking.get(i))) {
                bookedSeatList.add(booking.get(i));
                availableSeatList.remove(booking.get(i));
            } else {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    public void cancelBooking(List<String> cancelSeats) {
        for (int i = 0; i < cancelSeats.size(); i++){
            bookedSeatList.remove(cancelSeats.get(i));
            availableSeatList.add(cancelSeats.get(i));
        }
    }







}
