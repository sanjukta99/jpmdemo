package com.shows.jpmdemo;

import com.shows.jpmdemo.service.AppService;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static junit.framework.TestCase.assertEquals;

@SpringBootTest
public class AppServiceTest {

    private AppService appService = new AppService();

    // Admin Commands
    @Test
    public void invalidNumRowsReturnsErrorMessage(){
        String response = appService.createShow(1, 27, 1, 1);
        assertEquals("Invalid input: numRows max 26", response);
    }

    @Test
    public void invalidNumSeatsPerRowsReturnsErrorMessage(){
        String response = appService.createShow(1, 1, 11, 1);
        assertEquals("Invalid input: numSeatsPerRow max 10", response);
    }

    @Test
    public void duplicateShowCreationReturnsErrorMessage(){
        appService.createShow(1, 1, 1, 1);
        String response = appService.createShow(1, 1, 1, 1);
        assertEquals("Show number 1 already exists", response);
    }

    @Test
    public void viewShowBookingForShowThatDoesNotExistReturnsErrorMessage(){
        String response = appService.checkAvailability(2);
        assertEquals("Show number 2 does not exist", response);
    }

    // Buyer Commands
    @Test
    public void availabilityOfShowThatDoesNotExistReturnsErrorMessage(){
        String response = appService.checkAvailability(2);
        assertEquals("Show number 2 does not exist", response);
    }

    @Test
    public void bookShowThatDoesNotExistReturnsErrorMessage(){
        String response = appService.bookSeats(2, "A1,A2,A3", "12345678");
        assertEquals("Show number 2 does not exist", response);
    }

    @Test
    public void bookingShowUsingDuplicatePhoneNumberReturnsErrorMessage(){
        appService.createShow(1, 1, 1, 1);
        appService.bookSeats(1, "A1", "12345678");
        String response = appService.bookSeats(1, "A2", "12345678");
        assertEquals("There is an existing booking associated with your phone number for this show", response);
    }

    @Test
    public void bookingSeatsThatHaveAlreadyBeenBookedReturnsErrorMessage(){
        appService.createShow(1, 1, 1, 1);
        appService.bookSeats(1, "A1", "12345678");
        String response = appService.bookSeats(1, "A1", "87654321");
        assertEquals("The chosen seats are no longer available", response);
    }

    @Test
    public void bookingSeatsThatDoesNotExistReturnsErrorMessage(){
        appService.createShow(1, 1, 1, 1);
        String response = appService.bookSeats(1, "Z1", "12345678");
        assertEquals("The chosen seat selection is invalid", response);
    }

    @Test
    public void cancelShowThatDoesNotExistReturnsErrorMessage(){
        String response = appService.freeSeats("112345678");
        assertEquals("No such ticket number exists", response);
    }

    @Test
    public void cancelShowThatHasAlreadyPassedCancellationWindow() throws InterruptedException {
        appService.createShow(1, 1, 1, 1);
        appService.bookSeats(1, "A1", "12345678");
        Thread.sleep(60000);
        String response = appService.freeSeats("112345678");
        assertEquals("Unable to cancel booking, the cancellation window has already passed.", response);
    }

}
