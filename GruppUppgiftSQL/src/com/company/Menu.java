package com.company;

import java.util.Scanner;

public class Menu {

    private Scanner input = new Scanner(System.in);
    private Bookings bookings;

    public Menu(){
        bookings = new Bookings();
        mainMenu();
    }

    private void mainMenu(){
        String choice = "";
        while(true) {

            System.out.println("""
                    
                    Main Menu
                    [1] Admin Menu
                    [0] Logout""");

            try{
                choice = input.nextLine();

                switch (choice) {
                    case "1" -> bookingMenu();

                    case "0" -> {
                        System.out.println("logging out...");
                        System.exit(0);
                    }
                    default -> {
                    }
                }
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    private void bookingMenu(){
        boolean running = true;

        String choice = "";

        while (running){
            System.out.println("""
                                        
                    Admin Menu
                    [1] Register customer
                    [2] Search free rooms by date
                    [3] Cancel a booking
                    [4] Update Booking
                    [0] Back
                    """);
            try {
                choice = input.nextLine();

                switch (choice){
                    case "1" -> bookings.registerCustomer();

                    case "2" -> bookings.getBookingByDate();

                    case "3" -> bookings.deleteBooking();

                    case "4" -> bookings.updateBooking();

                    case "0" -> running = false;
                }
            }
            catch (Exception e){
                System.out.println("Error!"+e.getMessage());
            }
        }
    }
}
