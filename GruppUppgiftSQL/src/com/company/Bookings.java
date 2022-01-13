package com.company;

import java.util.Scanner;

public class Bookings {

    private DataService dataService;
    private static Scanner sc = new Scanner(System.in);
    int companyAmount;
    String pool, kidsClub, eveningEntertainment, restaurant;
    String checkInDate, checkoutDate;

    public Bookings(){
        dataService = new DataService();
        dataService.connect();
    }

    public void registerCustomer(){
        System.out.println("Are you already registered(Y/N): ");
        String answer = sc.nextLine();
        if (answer.equalsIgnoreCase("y")){
            System.out.print("\nCustomer companyAmount: ");
            this.companyAmount = Integer.parseInt(sc.nextLine());
        }else if (answer.equalsIgnoreCase("n")){
            System.out.print("Customer name: ");
            String name = sc.nextLine();
            System.out.print("\nCustomers phoneNumber: ");
            String phoneNumber = sc.nextLine();
            System.out.print("\nCustomers email: ");
            String email = sc.nextLine();
            System.out.print("\nCustomer birthday: ");
            String birthday = sc.nextLine();
            System.out.print("\nCustomer companyAmount: ");
            this.companyAmount = Integer.parseInt(sc.nextLine());

            dataService.selectCustomer(name, phoneNumber, email, birthday);
        }
        if(companyAmount != 0){
            registerCompany();
        }
    }

    public void registerCompany(){
        System.out.print("customer ID: ");
        int companyID = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < companyAmount; i++){
            System.out.print("Company name: ");
            String companyName = sc.nextLine();
            System.out.print("Company Email: ");
            String companyEmail = sc.nextLine();
            System.out.print("Company PhoneNumber: ");
            String companyPhoneNumber = sc.nextLine();
            System.out.print("Company birthday: ");
            String companyBirthday = sc.nextLine();
            dataService.selectCompany(companyID, companyName, companyEmail, companyPhoneNumber, companyBirthday );
        }
    }
    public void getBookingByDate(){
        System.out.print("Company amount: ");
        this.companyAmount = Integer.parseInt(sc.nextLine());
        System.out.print("When is the checkInDate (yyyy-mm-dd): ");
        checkInDate = sc.nextLine();
        System.out.print("When is the checkOutDate (yyyy-mm-dd): ");
        checkoutDate = sc.nextLine();
        String answer;
        do {
            System.out.print("Want specific hotel search(Y/N): ");
            answer = sc.nextLine();
        } while (!(answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n")));
        if (answer.equalsIgnoreCase("n")){
            dataService.selectHotelByAll(checkInDate, checkoutDate);
            getBookingByAllDates();
        }else{
        do {
            System.out.print("Want hotel with pool(Y/N): ");
            pool = sc.nextLine();
        } while (!(pool.equalsIgnoreCase("y") || pool.equalsIgnoreCase("n")));
        do {
            System.out.print("Want hotel with KidsClub(Y/N): ");
            kidsClub = sc.nextLine();
        }while (!(kidsClub.equalsIgnoreCase("y") || kidsClub.equalsIgnoreCase("n")));
        do {
            System.out.print("Want hotel with EveningEntertainment(Y/N): ");
            eveningEntertainment = sc.nextLine();
        }while (!(eveningEntertainment.equalsIgnoreCase("y") || eveningEntertainment.equalsIgnoreCase("n")));
        do {
            System.out.print("Want hotel with restaurant(Y/N): ");
            restaurant = sc.nextLine();
        }while (!(restaurant.equalsIgnoreCase("y") || restaurant.equalsIgnoreCase("n")));
        int pool1, kids, evening, restaurant1;
        if (pool.equalsIgnoreCase("Y")){
            pool1 = 1;
        }else {
            pool1 = 0;
        }
        if (kidsClub.equalsIgnoreCase("Y")){
            kids = 1;
        }else {
            kids = 0;
        }
        if (eveningEntertainment.equalsIgnoreCase("Y")){
            evening = 1;
        }else {
            evening = 0;
        }
        if (restaurant.equalsIgnoreCase("Y")){
            restaurant1 = 1;
        }else {
            restaurant1 = 0;
        }
        dataService.selectHotelByDate(checkInDate, checkoutDate, pool1,kids,evening,restaurant1);
        boolean running = true;
        String choice = "";

        while (running) {
            System.out.println("""
                                        
                    Basic hotel search
                    [1] Search distance to beach
                    [2] Search distance to center
                    [3] Sort by price(low to high)
                    [4] sort by rating(high to low)
                    [5] book a room
                    [0] Back
                    """);
            try {
                choice = sc.nextLine();

                switch (choice) {
                    case "1" -> {
                        System.out.print("type preferred distance to beach: ");
                        int distanceBeach = Integer.parseInt(sc.nextLine());
                        dataService.distanceToBeach(checkInDate, checkoutDate, pool1, kids, evening, restaurant1, distanceBeach);
                    }

                    case "2" -> {
                        System.out.print("type preferred distance to center: ");
                        int distanceCenter = Integer.parseInt(sc.nextLine());
                        dataService.distanceToCenter(checkInDate, checkoutDate, pool1, kids, evening, restaurant1, distanceCenter);
                    }

                    case "3" -> dataService.sortByPrice(checkInDate, checkoutDate, pool1, kids, evening, restaurant1);

                    case "4" -> dataService.sortByRating(checkInDate, checkoutDate, pool1, kids, evening, restaurant1);

                    case "5" -> bookARoom();

                    case "0" -> running = false;
                }
            } catch (Exception e) {
                System.out.println("Error!" + e.getMessage());
            }
        }
        }
    }

    public void getBookingByAllDates(){
        boolean running = true;
        String choice = "";

        while (running) {
            System.out.println("""
                                        
                    Basic hotel search
                    [1] Search distance to beach
                    [2] Search distance to center
                    [3] Sort by price(low to high)
                    [4] sort by rating(high to low)
                    [5] book a room
                    [0] Back
                    """);
            try {
                choice = sc.nextLine();

                switch (choice) {
                    case "1" -> {
                        System.out.print("type preferred distance to beach: ");
                        int distanceBeach = Integer.parseInt(sc.nextLine());
                        dataService.selectHotelByAllDateToBeach(checkInDate, checkoutDate, distanceBeach);
                    }

                    case "2" -> {
                        System.out.print("type preferred distance to center: ");
                        int distanceCenter = Integer.parseInt(sc.nextLine());
                        dataService.selectHotelByAllDateToCenter(checkInDate, checkoutDate, distanceCenter);
                    }

                    case "3" -> dataService.sortByAllPrice(checkInDate, checkoutDate);

                    case "4" -> dataService.sortByAllRating(checkInDate, checkoutDate);

                    case "5" -> bookARoom();

                    case "0" -> running = false;
                }
            } catch (Exception e) {
                System.out.println("Error!" + e.getMessage());
            }
        }
    }

    public void bookARoom(){
        registerCustomer();
        int mealID = 0;
        String choice;
        int bed = 0;
        int totalPrice;
        System.out.println("\nBOOKING\n");
        System.out.print("Customers ID: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Room ID they want: ");
        int roomID = Integer.parseInt(sc.nextLine());
        do {
            System.out.print("What mealPackage? (FULL 50$/HALF 30$/NO): ");
            choice = sc.nextLine();
            if (choice.equalsIgnoreCase("full")) {
                mealID = 1;
            } else if (choice.equalsIgnoreCase("half")) {
                mealID = 2;
            }else if (choice.equalsIgnoreCase("no")){
                mealID = 3;
            }
        }while (!(choice.equalsIgnoreCase("full") || choice.equalsIgnoreCase("half") || choice.equalsIgnoreCase("no")));
        System.out.print("ExtraBed?(Y/N): ");
        String extraBed = sc.nextLine();
        if (extraBed.equalsIgnoreCase("y")){
            bed = 1;
        }
        if (mealID == 1 && bed == 1){
            totalPrice = 75;
        }else if (mealID == 1){
            totalPrice = 50;
        }else if (mealID == 2 && bed == 1){
            totalPrice = 55;
        }else if (mealID == 2){
            totalPrice = 30;
        }else if (mealID == 3 && bed == 1){
            totalPrice = 25;
        }else {
            totalPrice = 0;
        }
        int days = Math.abs(dataService.getDays(checkInDate, checkoutDate));
        int roomPrice = dataService.RoomPrice(roomID);
        int tot = roomPrice * days + totalPrice;
        dataService.booking(id, roomID, mealID, checkInDate, checkoutDate, bed,tot);
    }

    public void deleteBooking(){
        System.out.print("What bookingID do you want to delete: ");
        int booking = Dialogs.promptInt();
        dataService.deleteBooking(booking);
        System.out.println("Deleting... ");
    }

    public void updateBooking(){
        System.out.println("BookingID you want to change: ");
        int bookingID = Integer.parseInt(sc.nextLine());
        System.out.print("Change checkInDate: ");
        checkInDate = sc.nextLine();
        System.out.println("Change CheckOutDate: ");
        checkoutDate = sc.nextLine();
        dataService.updateBooking(checkInDate, checkoutDate, bookingID);
    }
}