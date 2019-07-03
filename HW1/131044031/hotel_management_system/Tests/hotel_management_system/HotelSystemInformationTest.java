package hotel_management_system;

import org.junit.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.*;

public class HotelSystemInformationTest {
    HotelGuest guest=new HotelGuest();

    @Test
    public void testCreateUserName() throws FileNotFoundException {
        File file= new File("Tests/hotelSystemInformationTestFile/signup.txt");
        Scanner scan=new Scanner(file);
        guest.signUp(true,scan);
        assertEquals("aslıkarzan",guest.getUserName());
    }

    @Test
    public void showRooms() {
        guest.showRooms();
    }

    @Test
    public void testUpdateRoomState() {
        guest.updateRoomState(25,"reservation");
        assertEquals("R",guest.hotel[25]);
    }

    @Test
    public void testDetermineRoomNumber() {
        int roomNo=guest.determineRoomNumber("a2");
        assertEquals(10,roomNo);
    }

    @Test
    public void testUpdateHotelRooms() throws IOException {
        guest.hotel[0] ="F";
        guest.hotel[10]="E";
        guest.hotel[20]="F";
        guest.hotel[30]="R";
        guest.hotel[40]="E";
        guest.updateHotelRooms();
        // check hotel_rooms.csv file
        guest.showRooms();
    }

    @Test
    public void testRecordReservedRoom() throws IOException {
        guest.setIdNumber("12");
        guest.setRezervationRoom("c4");
        guest.recordReservedRoom();
        //check reservation_rooms.csv file contents
    }

    @Test
    public void testDetermineIdNumber() throws IOException {
        int lineNumber = 0;
        String line;
        FileReader fileReader = new FileReader(guest.PERSON_INFORMATION_CSV);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        while ((line = bufferedReader.readLine()) != null) {
                ++lineNumber;
        }
        bufferedReader.close();
        String checkId=guest.determineIdNumber();
        assertEquals(lineNumber+"",checkId);
    }


    @Test
    public void testParsingPersonInformation() {
        guest.parsingPersonInformation("0;sefa;yıldız;05458781478;sefayıldız;sefa.123;");
        assertEquals("sefa",guest.getName());
        assertEquals("sefa.123", guest.getPassword());
        assertEquals("05458781478",guest.getPhoneNumber());
    }

    @Test
    public void testCheckRoomNo() {
        boolean room=guest.checkRoomNo("a5");
        assertEquals(true,room);
    }

    @Test
    public void testCheckRoomState() {
        guest.hotel[20]="E";
        boolean state=guest.checkRoomState(20);
        assertEquals(true,state);
    }

}