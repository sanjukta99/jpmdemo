# Getting Started
This application was built using spring-shell-starter which uses the spring boot framework. To run this application locally, build and run the project using an IDE / using standard Maven commands. The application will open an interactive shell which can be used to run the commands as described in the following section.

# User Guide
## Admin Commands
1. Setup 
`setup -n <showNumber> -r <numRows> -s <numSeatsPerRow> -c <cancellationWindow>`
2. View
`view -n <showNumber>`
   
## Buyer Commands
1. Availability
`availability -n <showNumber>`
2. Book
`book -n <showNumber> -p <phoneNumber> -s <seatSelection>`
NOTE: The seatSelection string should be comma separated with no additional spaces.
Example: `book -n 1 -p 12345678 -s A1,B2,C3`
3. Cancel
`cancel -t <ticketNumber> -p <phoneNumber>`

## Additional Commands
1. Help: to see the list of available commands and their arguments
`help` 
2. Exit: to exit the application
`exit`


## Assumptions
1. There is no additional command for login and no separate user profiles for admin and buyer. This was done to keep the application simple.
2. The user has some familiarity with executing commands in a shell.
## Further Scope
### 1. User roles
Since there are two clearly defined user personas for the application there could be restrictions placed on which commands can be executed by which kind of user based on IAM.
### 2. Integration testing spring-shell
Spring shell projects can be tested using an interactive shell that simulates the commands that are entered by users, and validated against the expected behaviour.
Due to time constraints I was not able to successfully look into this. 
### 3. Improved UI/UX of the shell window
Some additional improvements like colour coding of success and failure messages, auto-completion of commands, renaming the shell to something application specific.
