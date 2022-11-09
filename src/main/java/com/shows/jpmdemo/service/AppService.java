package com.shows.jpmdemo.service;

import com.shows.jpmdemo.model.Booking;
import com.shows.jpmdemo.model.Show;
import com.shows.jpmdemo.utils.ShowUtils;
import com.shows.jpmdemo.utils.ValidateInputs;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Service
public class AppService {

    private HashMap<Integer, Show> showsList = new HashMap<Integer, Show>();
    private HashMap<String, Booking> bookingList = new HashMap<>();

    public Boolean showExists(int showNumber) {
        return showsList.containsKey(showNumber);
    }

    public HashMap<Integer, Show> viewAllShows() {
        return showsList;
    }

    public Show getShow(int showNumber) {
        return showsList.get(showNumber);
    }

    public Booking getBooking(String ticketNumber) {
        return bookingList.get(ticketNumber);
    }

    public Boolean isUniquePhoneNumber(int showNumber, String phoneNumber) {
        if(bookingList.containsKey(String.format("%s%s", showNumber, phoneNumber))){
            return false;
        } else {
            return true;
        }
    }

    public boolean seatsAvailable(int showNumber, List<String> seatList) {
        Show show = showsList.get(showNumber);
        for(int i = 0; i < seatList.size(); i++){
            if(show.getBookedSeatList().contains(seatList.get(i))){
                return false;
            }
        }
        return true;
    }

    public boolean bookingExists(String ticketNumber) {
        if(bookingList.containsKey(ticketNumber)){
            return true;
        }else{
            return false;
        }
    }

    public String createShow(int showNumber, int numRows, int numSeatsPerRow, int cancellationWindow) {
        if(showExists(showNumber)){
            return String.format("Show number %s already exists", showNumber);
        }

        if(!ValidateInputs.isValidNumRows(numRows)) {
            return "Invalid input: numRows max 26";
        }
        if(!ValidateInputs.isValidNumSeatsPerRow(numSeatsPerRow)) {
            return "Invalid input: numSeatsPerRow max 10";
        }
        if(!ValidateInputs.isValidCancellationWindow(cancellationWindow)) {
            return "Invalid input: cancellation window should be a positive number";
        }
        Show newShow = new Show(showNumber, numRows, numSeatsPerRow, cancellationWindow);
        showsList.put(showNumber, newShow);
        return printNewShow(newShow);
    }

    private String printNewShow(Show newShow) {
        return String.format("Show created! Show number: %s || Num rows: %s || Num seats per row: %s || Cancellation window: %s",
                newShow.getShowNumber(), newShow.getNumRows(), newShow.getNumSeatsPerRow(), newShow.getCancellationWindow());
    }

    public String checkAvailability(int showNumber) {
        if (showExists(showNumber)) {
            Set<String> availableSeats = showsList.get(showNumber).getAvailableSeatList();
            return availableSeats.toString();
        } else {
            return String.format("Show number %s does not exist", showNumber);
        }

    }

    public String bookSeats(int showNumber, String seatSelection, String phoneNumber){

        List<String> seatList = ShowUtils.parseSeatString(seatSelection);

        if(!showExists(showNumber)){
            return String.format("Show number %s does not exist", showNumber);
        }
        if(!isUniquePhoneNumber(showNumber, phoneNumber)){
            return String.format("There is an existing booking associated with your phone number for this show");
        }
        if(!seatsAvailable(showNumber, seatList)) {
            return String.format("The chosen seats are no longer available");
        }

        Show show = showsList.get(showNumber);

        if(show.bookSeats(seatList)) {
            return createBooking(showNumber, phoneNumber, seatList);
        }else {
            return String.format("The chosen seat selection is invalid");
        }
    }

    public String createBooking(int showNumber, String phoneNumber, List<String> seatSelection) {
        Booking newBooking = new Booking(showNumber, phoneNumber, seatSelection);
        bookingList.put(newBooking.getTicketNumber(), newBooking);
        return printTicketNumber(newBooking.getTicketNumber());
    }

    public String freeSeats(String ticketNumber) {

        if(!bookingExists(ticketNumber)){
            return "No such ticket number exists";
        }

        Booking booking = getBooking(ticketNumber);
        Show show = getShow(booking.getShowNumber());

        if (!ShowUtils.isCancellable(show.getExpirationTime())) {
            return "Unable to cancel booking, the cancellation window has already passed.";
        } else {
            show.cancelBooking(booking.getSeatSelection());
            return cancelBooking(ticketNumber);
        }
    }

    public String cancelBooking(String ticketNumber) {
        bookingList.remove(ticketNumber);
        return String.format("Successfully cancelled booking for ticket number %s", ticketNumber);
    }


    private String printTicketNumber(String ticketNumber) {
        return String.format("Booking confirmed! Your ticket number is: %s", ticketNumber);
    }


    public String viewBooking(int showNumber) {
        if (showExists(showNumber)) {
            String returnString = "";
            for(Booking booking: bookingList.values()) {
                if(booking.getShowNumber() == showNumber){
                    returnString += formatBooking(booking);
                }
            }
            return returnString;
        } else {
            return String.format("Show number %s does not exist", showNumber);
        }
    }

    private String formatBooking(Booking booking) {
        return String.format("Show number: %s || Ticket number: %s || Phone number: %s || Seat numbers: %s\n",
                booking.getShowNumber(), booking.getTicketNumber(), booking.getPhoneNumber(), booking.getSeatSelection().toString());
    }




}
