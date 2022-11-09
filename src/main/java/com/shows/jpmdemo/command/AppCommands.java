package com.shows.jpmdemo.command;

import com.shows.jpmdemo.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class AppCommands {

    @Autowired
    AppService appService;

    // ADMIN COMMANDS
    @ShellMethod("Setup show with show number, number of rows, seats in row, cancellation time window")
    public String setup(@ShellOption({"-n", "--showNumber"}) int showNumber,
                            @ShellOption({"-r", "--numRows"}) int numRows,
                            @ShellOption({"-s", "--numSeatsPerRow"}) int numSeatsPerRow,
                            @ShellOption({"-c", "--cancellationWindow"}) int cancellationWindow) {

        return appService.createShow(showNumber, numRows, numSeatsPerRow, cancellationWindow);

    }

    @ShellMethod("View booking for a show for a given show number")
    public String view(@ShellOption({"-n", "--showNumber"}) int showNumber) {

        return appService.viewBooking(showNumber);

    }

    // BUYER COMMANDS
    @ShellMethod("Check availability of a show for a given show number")
    public String availability(@ShellOption({"-n", "--showNumber"}) int showNumber) {

        return appService.checkAvailability(showNumber);
    }

    @ShellMethod("Book ticket for a show")
    public String book(@ShellOption({"-n", "--showNumber"}) int showNumber,
                       @ShellOption({"-p", "--phoneNumber"}) String phoneNumber,
                       @ShellOption({"-s", "--seatSelection"}) String seatSelection) {

        return appService.bookSeats(showNumber, seatSelection, phoneNumber);

    }

    @ShellMethod("Cancel ticket for a show")
    public String cancel(@ShellOption({"-t", "--ticketNumber"}) String ticketNumber,
                         @ShellOption({"-p", "--phoneNumber"}) String phoneNumber) {

        return appService.freeSeats(ticketNumber);
    }


}
