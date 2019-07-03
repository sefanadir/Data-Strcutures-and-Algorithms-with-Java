package hotel_management_system;

import org.junit.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.*;

public class HotelReceptionistTest {
    private HotelReceptionist receptionist = new HotelReceptionist();
    private HotelGuest guest= new HotelGuest();

    @Test
    public void testSignIn() throws FileNotFoundException {
        File file= new File("Tests/receptionistTestFile/signin.txt");
        Scanner scan=new Scanner(file);
        boolean testSignIn=receptionist.signIn(true ,scan);
        assertEquals(true,testSignIn);
    }

    @Test
    public void testBookARoom() throws IOException {
        File file= new File("Tests/receptionistTestFile/bookaroom.txt");
        Scanner scan=new Scanner(file);
        receptionist.bookARoom(scan);
        int doorNumber=guest.determineRoomNumber(receptionist.getRezervationRoom());
        assertEquals("R",receptionist.hotel[doorNumber]);
    }

    @Test
    public void testCancelReservation() throws IOException {
        File file= new File("Tests/receptionistTestFile/cancelreservation.txt");
        Scanner scan=new Scanner(file);
        boolean cancel=receptionist.cancelReservation(scan);
        assertEquals(true,cancel);
    }

    @Test
    public void testCheckIn() throws IOException {
        File file= new File("Tests/receptionistTestFile/checkin.txt");
        Scanner scan=new Scanner(file);
        boolean in=receptionist.checkIn(scan);
        assertEquals(true,in);
    }

    @Test
    public void testCheckOut() throws IOException {
        File file= new File("Tests/receptionistTestFile/checkout.txt");
        Scanner scan=new Scanner(file);
        boolean out=receptionist.checkOut(scan);
        assertEquals(true,out);
    }

}