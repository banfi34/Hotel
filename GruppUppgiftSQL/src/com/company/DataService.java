package com.company;

import java.sql.*;
import java.util.ArrayList;

public class DataService {

    private Connection conn = null;
    private ResultSet resultSet;
    private Statement statement;

    public DataService() {
        connect();
    }


    public void connect() {

        try {
            conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\sebas\\Desktop\\test-darabass\\HotelGrupp.db");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void selectHotelByDate(String date1, String date2, int pool, int kidsClub, int eveningEntertainment, int restaurant) {

        try {
            PreparedStatement pstmt = conn.prepareStatement("""
                SELECT r.RoomID, h.HotelName, rs.RoomType, h.DistanceToBeach, h.DistanceToCenter, rs.NumberOfBeds, r.RoomPrice
                FROM Rooms r
                JOIN Hotel h USING (HotelID)
                JOIN RoomSpecs rs USING (RoomSpecID)
                WHERE RoomID NOT IN (
                    SELECT RoomID
                    FROM Bookings
                    WHERE ? BETWEEN CheckInDate AND CheckOutDate
                    OR ? BETWEEN CheckInDate AND CheckOutDate
                    )
                AND Pool IS ?  AND KidsClub IS ? AND EveningEntertainment IS ? AND Restaurant IS ?""");
            pstmt.setString(1, date1);
            pstmt.setString(2, date2);
            pstmt.setInt(3, pool);
            pstmt.setInt(4, kidsClub);
            pstmt.setInt(5, eveningEntertainment);
            pstmt.setInt(6, restaurant);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String customer =
                "| ID: "+ rs.getInt("RoomID") + " | " +
                "HotelName: " + rs.getString("HotelName")+ " | " +
                "\tRoomType: " + rs.getString("RoomType")+ " | " +
                "\tNumberOfBeds: " + rs.getInt("NumberOfBeds") + " | " +
                "\tDistanceToBeach: " + rs.getInt("DistanceToBeach")+ "km | "+
                "\tDistanceToCenter: " + rs.getInt("DistanceToCenter")+ "km | "+
                "\tRoomPrice: $" + rs.getInt("RoomPrice") + " | \n" +
                "--------------------------------------------------------------------------------------------------------------------------------------------------------";
                System.out.println(customer);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectCustomer(String name, String phoneNumber, String email, String birthday ){
        String sql = """
                INSERT INTO Customer (Name, PhoneNumber, Email, Birthday)
                VALUES(?,?,?,?)""";

        try {
             PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, phoneNumber);
            pstmt.setString(3, email);
            pstmt.setString(4, birthday);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void selectCompany(int customerID, String name, String email, String phoneNumber, String birthday){
        String sql = """
                INSERT INTO Company(CustomerID, Name, Email, PhoneNumber, Birthday)
                VALUES(?,?,?,?,?)""";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, customerID);
            pstmt.setString(2, name);
            pstmt.setString(3, email);
            pstmt.setString(4, phoneNumber);
            pstmt.setString(5, birthday);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void distanceToBeach(String date1, String date2, int pool, int kidsClub, int eveningEntertainment, int restaurant, int distance){
        try {
            PreparedStatement pstmt = conn.prepareStatement("""
                SELECT r.RoomID, h.HotelName, rs.RoomType, h.DistanceToBeach, h.DistanceToCenter, rs.NumberOfBeds, r.RoomPrice
                FROM Rooms r
                JOIN Hotel h USING (HotelID)
                JOIN RoomSpecs rs USING (RoomSpecID)
                WHERE RoomID NOT IN (
                    SELECT RoomID
                    FROM Bookings
                    WHERE ? BETWEEN CheckInDate AND CheckOutDate
                    OR ? BETWEEN CheckInDate AND CheckOutDate
                    )
                AND Pool IS ?  AND KidsClub IS ? AND EveningEntertainment IS ? AND Restaurant IS ?
                AND DistanceToBeach < ?""");

            pstmt.setString(1, date1);
            pstmt.setString(2, date2);
            pstmt.setInt(3, pool);
            pstmt.setInt(4, kidsClub);
            pstmt.setInt(5, eveningEntertainment);
            pstmt.setInt(6, restaurant);
            pstmt.setInt(7, distance);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String customer =
                        "| ID: "+ rs.getInt("RoomID") + " | " +
                                "HotelName: " + rs.getString("HotelName")+ " | " +
                                "\tRoomType: " + rs.getString("RoomType")+ " | " +
                                "\tNumberOfBeds: " + rs.getInt("NumberOfBeds") + " | " +
                                "\tDistanceToBeach: " + rs.getInt("DistanceToBeach")+ "km | "+
                                "\tDistanceToCenter: " + rs.getInt("DistanceToCenter")+ "km | "+
                                "\tRoomPrice: $" + rs.getInt("RoomPrice") + " | \n" +
                                "--------------------------------------------------------------------------------------------------------------------------------------------------------";
                System.out.println(customer);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void distanceToCenter(String date1, String date2, int pool, int kidsClub, int eveningEntertainment, int restaurant, int distance){
        try {
            PreparedStatement pstmt = conn.prepareStatement("""
                SELECT r.RoomID, h.HotelName, rs.RoomType, h.DistanceToBeach, h.DistanceToCenter, rs.NumberOfBeds, r.RoomPrice
                FROM Rooms r
                JOIN Hotel h USING (HotelID)
                JOIN RoomSpecs rs USING (RoomSpecID)
                WHERE RoomID NOT IN (
                    SELECT RoomID
                    FROM Bookings
                    WHERE ? BETWEEN CheckInDate AND CheckOutDate
                    OR ? BETWEEN CheckInDate AND CheckOutDate
                    )
                AND Pool IS ?  AND KidsClub IS ? AND EveningEntertainment IS ? AND Restaurant IS ?
                AND DistanceToCenter < ?""");

            pstmt.setString(1, date1);
            pstmt.setString(2, date2);
            pstmt.setInt(3, pool);
            pstmt.setInt(4, kidsClub);
            pstmt.setInt(5, eveningEntertainment);
            pstmt.setInt(6, restaurant);
            pstmt.setInt(7, distance);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String customer =
                        "| ID: "+ rs.getInt("RoomID") + " | " +
                                "HotelName: " + rs.getString("HotelName")+ " | " +
                                "\tRoomType: " + rs.getString("RoomType")+ " | " +
                                "\tNumberOfBeds: " + rs.getInt("NumberOfBeds") + " | " +
                                "\tDistanceToBeach: " + rs.getInt("DistanceToBeach")+ "km | "+
                                "\tDistanceToCenter: " + rs.getInt("DistanceToCenter")+ "km | "+
                                "\tRoomPrice: $" + rs.getInt("RoomPrice") + " | \n" +
                                "--------------------------------------------------------------------------------------------------------------------------------------------------------";
                System.out.println(customer);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void sortByPrice(String date1, String date2, int pool, int kidsClub, int eveningEntertainment, int restaurant){
        try {
            PreparedStatement pstmt = conn.prepareStatement("""
                    SELECT r.RoomID, h.HotelName, rs.RoomType, h.DistanceToBeach, h.DistanceToCenter, rs.NumberOfBeds, r.RoomPrice
                    FROM Rooms r
                    JOIN Hotel h USING (HotelID)
                    JOIN RoomSpecs rs USING (RoomSpecID)
                    WHERE RoomID NOT IN (
                        SELECT RoomID
                        FROM Bookings
                        WHERE ? BETWEEN CheckInDate AND CheckOutDate
                        OR ? BETWEEN CheckInDate AND CheckOutDate
                        )
                    AND Pool IS ?  AND KidsClub IS ? AND EveningEntertainment IS ? AND Restaurant IS ?
                    GROUP BY HotelID, RoomType
                    ORDER by RoomPrice""");

            pstmt.setString(1, date1);
            pstmt.setString(2, date2);
            pstmt.setInt(3, pool);
            pstmt.setInt(4, kidsClub);
            pstmt.setInt(5, eveningEntertainment);
            pstmt.setInt(6, restaurant);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String customer =
                        "| ID: "+ rs.getInt("RoomID") + " | " +
                                "HotelName: " + rs.getString("HotelName")+ " | " +
                                "\tRoomType: " + rs.getString("RoomType")+ " | " +
                                "\tNumberOfBeds: " + rs.getInt("NumberOfBeds") + " | " +
                                "\tDistanceToBeach: " + rs.getInt("DistanceToBeach")+ "km | "+
                                "\tDistanceToCenter: " + rs.getInt("DistanceToCenter")+ "km | "+
                                "\tRoomPrice: $" + rs.getInt("RoomPrice") + " | \n" +
                                "--------------------------------------------------------------------------------------------------------------------------------------------------------";
                System.out.println(customer);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void sortByRating(String date1, String date2, int pool, int kidsClub, int eveningEntertainment, int restaurant){
        try {
            PreparedStatement pstmt = conn.prepareStatement("""
                    SELECT r.RoomID, h.HotelName, rs.RoomType, h.DistanceToBeach, h.DistanceToCenter, rs.NumberOfBeds, r.RoomPrice
                    FROM Rooms r
                    JOIN Hotel h USING (HotelID)
                    JOIN RoomSpecs rs USING (RoomSpecID)
                    WHERE RoomID NOT IN (
                        SELECT RoomID
                        FROM Bookings
                        WHERE ? BETWEEN CheckInDate AND CheckOutDate
                        OR ? BETWEEN CheckInDate AND CheckOutDate
                        )
                    AND Pool IS ?  AND KidsClub IS ? AND EveningEntertainment IS ? AND Restaurant IS ?
                    GROUP BY HotelID, RoomType
                    ORDER by Rating DESC""");

            pstmt.setString(1, date1);
            pstmt.setString(2, date2);
            pstmt.setInt(3, pool);
            pstmt.setInt(4, kidsClub);
            pstmt.setInt(5, eveningEntertainment);
            pstmt.setInt(6, restaurant);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String customer =
                        "| ID: "+ rs.getInt("RoomID") + " | " +
                                "HotelName: " + rs.getString("HotelName")+ " | " +
                                "\tRoomType: " + rs.getString("RoomType")+ " | " +
                                "\tNumberOfBeds: " + rs.getInt("NumberOfBeds") + " | " +
                                "\tDistanceToBeach: " + rs.getInt("DistanceToBeach")+ "km | "+
                                "\tDistanceToCenter: " + rs.getInt("DistanceToCenter")+ "km | "+
                                "\tRoomPrice: $" + rs.getInt("RoomPrice") + " | \n" +
                                "--------------------------------------------------------------------------------------------------------------------------------------------------------";
                System.out.println(customer);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectHotelByAllDateToBeach(String date1, String date2, int distance) {

        try {
            PreparedStatement pstmt = conn.prepareStatement("""
                SELECT r.RoomID, h.HotelName, rs.RoomType, h.DistanceToBeach, h.DistanceToCenter, rs.NumberOfBeds, r.RoomPrice
                FROM Rooms r
                JOIN Hotel h USING (HotelID)
                JOIN RoomSpecs rs USING (RoomSpecID)
                WHERE RoomID NOT IN (
                    SELECT RoomID
                    FROM Bookings
                    WHERE ? BETWEEN CheckInDate AND CheckOutDate
                    OR ? BETWEEN CheckInDate AND CheckOutDate
                    )
                    AND DistanceToBeach < ?""");
            pstmt.setString(1, date1);
            pstmt.setString(2, date2);
            pstmt.setInt(3, distance);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String customer =
                        "| ID: "+ rs.getInt("RoomID") + " | " +
                                "HotelName: " + rs.getString("HotelName")+ " | " +
                                "\tRoomType: " + rs.getString("RoomType")+ " | " +
                                "\tNumberOfBeds: " + rs.getInt("NumberOfBeds") + " | " +
                                "\tDistanceToBeach: " + rs.getInt("DistanceToBeach")+ "km | "+
                                "\tDistanceToCenter: " + rs.getInt("DistanceToCenter")+ "km | "+
                                "\tRoomPrice: $" + rs.getInt("RoomPrice") + " | \n" +
                                "--------------------------------------------------------------------------------------------------------------------------------------------------------";
                System.out.println(customer);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectHotelByAllDateToCenter(String date1, String date2, int distance) {

        try {
            PreparedStatement pstmt = conn.prepareStatement("""
                SELECT r.RoomID, h.HotelName, rs.RoomType, h.DistanceToBeach, h.DistanceToCenter, rs.NumberOfBeds, r.RoomPrice
                FROM Rooms r
                JOIN Hotel h USING (HotelID)
                JOIN RoomSpecs rs USING (RoomSpecID)
                WHERE RoomID NOT IN (
                    SELECT RoomID
                    FROM Bookings
                    WHERE ? BETWEEN CheckInDate AND CheckOutDate
                    OR ? BETWEEN CheckInDate AND CheckOutDate
                    )
                    AND DistanceToCenter < ?""");
            pstmt.setString(1, date1);
            pstmt.setString(2, date2);
            pstmt.setInt(3, distance);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String customer =
                        "| ID: "+ rs.getInt("RoomID") + " | " +
                                "HotelName: " + rs.getString("HotelName")+ " | " +
                                "\tRoomType: " + rs.getString("RoomType")+ " | " +
                                "\tNumberOfBeds: " + rs.getInt("NumberOfBeds") + " | " +
                                "\tDistanceToBeach: " + rs.getInt("DistanceToBeach")+ "km | "+
                                "\tDistanceToCenter: " + rs.getInt("DistanceToCenter")+ "km | "+
                                "\tRoomPrice: $" + rs.getInt("RoomPrice") + " | \n" +
                                "--------------------------------------------------------------------------------------------------------------------------------------------------------";
                System.out.println(customer);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void sortByAllRating(String date1, String date2){
        try {
            PreparedStatement pstmt = conn.prepareStatement("""
                    SELECT r.RoomID, h.HotelName, rs.RoomType, h.DistanceToBeach, h.DistanceToCenter, rs.NumberOfBeds, r.RoomPrice
                    FROM Rooms r
                    JOIN Hotel h USING (HotelID)
                    JOIN RoomSpecs rs USING (RoomSpecID)
                    WHERE RoomID NOT IN (
                        SELECT RoomID
                        FROM Bookings
                        WHERE ? BETWEEN CheckInDate AND CheckOutDate
                        OR ? BETWEEN CheckInDate AND CheckOutDate
                        )
                    GROUP BY HotelID, RoomType
                    ORDER by Rating DESC""");

            pstmt.setString(1, date1);
            pstmt.setString(2, date2);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String customer =
                        "| ID: "+ rs.getInt("RoomID") + " | " +
                                "HotelName: " + rs.getString("HotelName")+ " | " +
                                "\tRoomType: " + rs.getString("RoomType")+ " | " +
                                "\tNumberOfBeds: " + rs.getInt("NumberOfBeds") + " | " +
                                "\tDistanceToBeach: " + rs.getInt("DistanceToBeach")+ "km | "+
                                "\tDistanceToCenter: " + rs.getInt("DistanceToCenter")+ "km | "+
                                "\tRoomPrice: $" + rs.getInt("RoomPrice") + " | \n" +
                                "--------------------------------------------------------------------------------------------------------------------------------------------------------";
                System.out.println(customer);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void sortByAllPrice(String date1, String date2){
        try {
            PreparedStatement pstmt = conn.prepareStatement("""
                    SELECT r.RoomID, h.HotelName, rs.RoomType, h.DistanceToBeach, h.DistanceToCenter, rs.NumberOfBeds, r.RoomPrice
                    FROM Rooms r
                    JOIN Hotel h USING (HotelID)
                    JOIN RoomSpecs rs USING (RoomSpecID)
                    WHERE RoomID NOT IN (
                        SELECT RoomID
                        FROM Bookings
                        WHERE ? BETWEEN CheckInDate AND CheckOutDate
                        OR ? BETWEEN CheckInDate AND CheckOutDate
                        )
                    GROUP BY HotelID, RoomType
                    ORDER by RoomPrice""");

            pstmt.setString(1, date1);
            pstmt.setString(2, date2);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String customer =
                        "| ID: "+ rs.getInt("RoomID") + " | " +
                                "HotelName: " + rs.getString("HotelName")+ " | " +
                                "\tRoomType: " + rs.getString("RoomType")+ " | " +
                                "\tNumberOfBeds: " + rs.getInt("NumberOfBeds") + " | " +
                                "\tDistanceToBeach: " + rs.getInt("DistanceToBeach")+ "km | "+
                                "\tDistanceToCenter: " + rs.getInt("DistanceToCenter")+ "km | "+
                                "\tRoomPrice: $" + rs.getInt("RoomPrice") + " | \n" +
                                "--------------------------------------------------------------------------------------------------------------------------------------------------------";
                System.out.println(customer);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectHotelByAll(String date1, String date2) {

        try {
            PreparedStatement pstmt = conn.prepareStatement("""
                SELECT r.RoomID, h.HotelName, rs.RoomType, h.DistanceToBeach, h.DistanceToCenter, rs.NumberOfBeds, r.RoomPrice
                FROM Rooms r
                JOIN Hotel h USING (HotelID)
                JOIN RoomSpecs rs USING (RoomSpecID)
                WHERE RoomID NOT IN (
                    SELECT RoomID
                    FROM Bookings
                    WHERE ? BETWEEN CheckInDate AND CheckOutDate
                    OR ? BETWEEN CheckInDate AND CheckOutDate
                    )""");
            pstmt.setString(1, date1);
            pstmt.setString(2, date2);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String customer =
                        "| ID: "+ rs.getInt("RoomID") + " | " +
                                "HotelName: " + rs.getString("HotelName")+ " | " +
                                "\tRoomType: " + rs.getString("RoomType")+ " | " +
                                "\tNumberOfBeds: " + rs.getInt("NumberOfBeds") + " | " +
                                "\tDistanceToBeach: " + rs.getInt("DistanceToBeach")+ "km | "+
                                "\tDistanceToCenter: " + rs.getInt("DistanceToCenter")+ "km | "+
                                "\tRoomPrice: $" + rs.getInt("RoomPrice") + " | \n" +
                                "--------------------------------------------------------------------------------------------------------------------------------------------------------";
                System.out.println(customer);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void booking(int customerID, int roomID, int mealPackageID, String checkInDate, String checkoutDate, int extraBed, int totPrice ){
        String sql = """
                INSERT INTO Bookings (CustomerID, RoomID, MealPackageID, CheckInDate, CheckOutDate, ExtraBed, TotalPrice)
                VALUES (?, ?, ?, ?, ?, ?, ?)""";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, customerID);
            pstmt.setInt(2, roomID);
            pstmt.setInt(3, mealPackageID);
            pstmt.setString(4, checkInDate);
            pstmt.setString(5, checkoutDate);
            pstmt.setInt(6, extraBed);
            pstmt.setInt(7, totPrice);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int RoomPrice(int roomid){
        int price = 0;
        String sql = """
                SELECT Rooms.RoomPrice
                FROM Rooms
                WHERE RoomID = ?""";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, roomid);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                price = rs.getInt("RoomPrice");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return price;
    }

    public int getDays(String checkIn, String checkOut){
        int days = 0;
        try {
            PreparedStatement pstmt = conn.prepareStatement(
                    "SELECT JULIANDAY(?) - JULIANDAY(?)AS Days");
            pstmt.setString(1, checkIn);
            pstmt.setString(2, checkOut);
            resultSet = pstmt.executeQuery();
            while (this.resultSet.next()) {
                days = resultSet.getInt("Days");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return days;
    }

    public void deleteBooking(int bookingID ){
        String sql = """
                DELETE FROM Bookings
                WHERE BookingID=?""";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bookingID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateBooking(String  checkIn, String checkOut, int bookingID ){
        String sql = """
                UPDATE Bookings
                SET CheckInDate = ?, CheckOutDate = ?
                WHERE BookingID = ?""";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, checkIn);
            pstmt.setString(2, checkOut);
            pstmt.setInt(3, bookingID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}