package hotel_management_system;

import org.junit.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.*;

public class HotelGuestTest {
    HotelGuest guest= new HotelGuest();


    public HotelGuestTest() throws FileNotFoundException {
    }

    @Test
    public void testSignIn() throws FileNotFoundException {
        File file= new File("Tests/hotelGuestTestFile/login.txt");
        Scanner scan=new Scanner(file);
        boolean testSignIn=guest.signIn(true,scan);
        assertEquals(true,testSignIn);
    }

    @Test
    public void testSignUp() throws FileNotFoundException  {

        File file= new File("Tests/hotelGuestTestFile/signup.txt");
        Scanner scan=new Scanner(file);
        guest.signUp(true,scan);
        assertEquals("yücel",guest.getName());
        assertEquals("ceviz",guest.getSurName());
        assertEquals("yücelceviz", guest.getUserName());
        assertEquals("yücel.999",guest.getPassword());
    }

    @Test
    public void testBookARoom() throws IOException {
        File file= new File("Tests/hotelGuestTestFile/bookaroom.txt");
        Scanner scan=new Scanner(file);
        guest.bookARoom(scan);
        int doorNumber=guest.determineRoomNumber(guest.getRezervationRoom());
        assertEquals("R",guest.hotel[doorNumber]);
    }

    @Test
    public void testCancelReservation() throws IOException {
        File file= new File("Tests/hotelGuestTestFile/cancelreservation.txt");
        Scanner scan=new Scanner(file);
        guest.signIn(true,scan);
        boolean cancel=guest.cancelReservation(scan);
        assertEquals(true,cancel);
    }

    @Test
    public void testRecordPersonInformation() throws IOException {
        FileReader fileReader = new FileReader(guest.PERSON_INFORMATION_CSV);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        File file= new File("Tests/hotelGuestTestFile/recordpersoninformation.txt");
        Scanner scan=new Scanner(file);
        guest.signUp(true,scan);
        guest.recordPersonInformation();
        String old_line;
        String new_line = null;
        while ((old_line = bufferedReader.readLine()) != null) {
            new_line=old_line;
        }
        bufferedReader.close();
        System.err.println(new_line);
        assertEquals(guest.getIdNumber(),new_line.substring(0,new_line.indexOf(";")));
    }

}