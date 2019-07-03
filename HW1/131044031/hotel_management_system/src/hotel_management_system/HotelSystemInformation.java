package hotel_management_system;

import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sefanadir
 */
public abstract class HotelSystemInformation implements HotelSystemInterface {

    private String name;
    private String surname;
    private String idNumber;
    private String username;
    private String password;
    private String phoneNumber;

    private String rezervationRoom;
    private final int numberOfRooms;
    protected String hotel[];
    private static final Integer ROOM_NUMBERS_AT_A_FLOOR = 10;
    protected static final String HOTEL_ROOMS_CSV = "./hotel_rooms.csv";
    protected static final String RESERVATION_ROOMS_CSV = "./reservation_rooms.csv";
    protected static final String PERSON_INFORMATION_CSV = "./person_informations.csv";

    /**
     * No parameter constructor initializes number of room, room state and
     * create hotel rooms
     *
     */
    public HotelSystemInformation() {
        numberOfRooms = 50;
        hotel = new String[numberOfRooms];
        initializeHotelRoom();
    }

    /**
     * This method returns hotel guest name
     *
     * @return hotel guest name
     */
    public String getName() {
        return name;
    }

    /**
     * This method changes hotel guest name
     *
     * @param changeName is new guest name
     */
    public void setName(String changeName) {
        name = changeName;
    }

    /**
     * This method returns hotel guest surname
     *
     * @return hotel guest surname
     */
    public String getSurName() {
        return surname;
    }

    /**
     * This method changes hotel guest surname
     *
     * @param changeSurName is new surname of hotel guest
     */
    public void setSurName(String changeSurName) {
        surname = changeSurName;
    }

    /**
     * This method sets identification number
     *
     * @param changeIdNumber is new identification number
     */
    @Override
    public void setIdNumber(String changeIdNumber) {
        idNumber = changeIdNumber;
    }

    /**
     * This method returns identification number
     *
     * @return user identification number
     */
    @Override
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * This method returns user name
     *
     * @return hotel guest user name
     */
    @Override
    public String getUserName() {
        return username;
    }

    /**
     * This method changes hotel guest user name
     *
     * @param changeUserName is new user name of hotel guest
     */
    @Override
    public void setUserName(String changeUserName) {
        this.surname = changeUserName;
    }

    /**
     * This method returns phone number of hotel guest
     *
     * @return hotel guest phone number
     */
    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * This method changes phone number
     *
     * @param changePhoneNumber is new phone number of hotel guest
     */
    @Override
    public void setPhoneNumber(String changePhoneNumber) {
        this.phoneNumber = changePhoneNumber;
    }

    /**
     * This method returns password of hotel guest
     *
     * @return hotel guest phone number
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * This method changes password of hotel guest
     *
     * @param changePassword is new password hotel guest
     */
    @Override
    public void setPassword(String changePassword) {
        this.password = changePassword;

    }

    /**
     * This method changes reservation room
     *
     * @param reservationRoom is new reservation room
     */
    public void setRezervationRoom(String reservationRoom) {
        this.rezervationRoom = reservationRoom;
    }

    /**
     * This method returns reservation room
     *
     * @return reservation room
     *
     */
    public String getRezervationRoom() {
        return rezervationRoom;
    }

    /**
     * This method creates a username by combining the first and last name
     */
    protected void createUserName() {
        username = name + surname;
    }

    /**
     * This method prints starting menu and gets option from user
     * @param scan get data from file
     * @return menu option
     */
    @Override
    public String hotelSystemMenu(Scanner scan) {
        System.out.println("1- receptionist ");
        System.out.println("2- hotel guest");
        System.out.println("3- sign up");
        System.out.println("4- exit");
        String choosing;
        do {
            choosing = scan.next();
            if (!("1".equals(choosing) || "2".equals(choosing) || "3".equals(choosing) || "4".equals(choosing))) {
                System.err.println("Wrong choosing");
            }
        } while (!("1".equals(choosing) || "2".equals(choosing) || "3".equals(choosing) || "4".equals(choosing)));
        return choosing;
    }

    /**
     * This method reads state of rooms from HOTEL_ROOMS_CSV file and
     * initializes hotel rooms array
     */
    private void initializeHotelRoom() {
        try {
            String line;
            FileReader fileReader = new FileReader(HOTEL_ROOMS_CSV);
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                while ((line = bufferedReader.readLine()) != null) {
                    for (int i = 0; i < line.length(); ++i) {
                        hotel = line.split(";");
                    }
                }
                bufferedReader.close();
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + HOTEL_ROOMS_CSV);
        } catch (IOException ex) {
            System.out.println("Error reading file '" + HOTEL_ROOMS_CSV);
        }
    }

    /**
     * This method prints state of hotel rooms to screen
     */
    protected void showRooms() {
        int index = 0;
        char column = 'a';
        int row = 1;
        System.out.print(" ");
        while (index < ROOM_NUMBERS_AT_A_FLOOR) {
            System.out.print(" " + column);
            ++column;
            ++index;
        }
        for (int i = 0; i < hotel.length; ++i) {
            if (i % 10 == 0) {
                System.out.print("\n");
                System.out.print(row + " ");
                ++row;
            }
            System.out.print(hotel[i] + " ");
        }
        System.out.print("\n");
    }

    /**
     * This method updates state of hotel rooms according to booking, cancel
     * reservation , check out and check in
     *
     * @param roomNo is door number of hotel room
     * @param updateState is booking or cancel reservation or check out etc...
     */
    protected void updateRoomState(int roomNo, String updateState) {
        if (null != updateState) {
            switch (updateState) {
                case "cancel":
                    hotel[roomNo] = "E";
                    break;
                case "reservation":
                    hotel[roomNo] = "R";
                    break;
                case "full":
                    hotel[roomNo] = "F";
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * This method calculates door number of hotel number according to room
     * option of user
     *
     * @param roomNo is room option of user
     * @return door number of hotel room
     */
    protected int determineRoomNumber(String roomNo) {
        int roomNumber = 0;
        char column = roomNo.charAt(0);
        int columnNo = column;
        columnNo -= 97;

        char row = roomNo.charAt(1);
        int rowNo = row;
        rowNo -= 48;

        roomNumber = ROOM_NUMBERS_AT_A_FLOOR * (rowNo - 1) + columnNo;

        return roomNumber;
    }

    /**
     * This method updates HOTEL_ROOMS_CSV file after booking, cancel
     * reservation, check in and check out
     *
     * @throws IOException file error
     */
    protected void updateHotelRooms() throws IOException {
        File roomFile = new File(HOTEL_ROOMS_CSV);
        FileWriter roomFileWriter = new FileWriter(roomFile, false);

        try (BufferedWriter roomWriter = new BufferedWriter(roomFileWriter)) {
            for (int i = 0; i < hotel.length; ++i) {
                roomWriter.write(hotel[i] + ";");
            }
        }
    }

    /**
     * This method records room reservation and identification number of hotel
     * guest to RESERVATION_ROOMS_CSV file
     *
     * @throws IOException file error
     */
    protected void recordReservedRoom() throws IOException {
        File reservationFile = new File(RESERVATION_ROOMS_CSV);
        FileWriter reservationFileWriter = new FileWriter(reservationFile, true);

        try (BufferedWriter roomWriter = new BufferedWriter(reservationFileWriter)) {
            roomWriter.write(idNumber + ";");
            roomWriter.write(getRezervationRoom() + ";\n");
        } catch (IOException ex) {
            Logger.getLogger(HotelSystemInformation.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Your reservation was received ✔");
    }

    /**
     * This method determines id number of new hotel guest that counts lines of
     * PERSON_INFORMATION_CSV file
     *
     * @return id number of hotel guest
     */
    protected String determineIdNumber() {
        int lineNumber = 0;
        String idNumber;
        try {
            String line;
            FileReader fileReader = new FileReader(PERSON_INFORMATION_CSV);
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                while ((line = bufferedReader.readLine()) != null) {
                    ++lineNumber;
                }
                bufferedReader.close();
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + PERSON_INFORMATION_CSV);
        } catch (IOException ex) {
            System.out.println("Error reading file '" + PERSON_INFORMATION_CSV);
        }
        idNumber = lineNumber + "";
        this.idNumber = idNumber;
        return idNumber;
    }

    /**
     * This method parses hotel guest information and sets id number, name,
     * surname, phone number, user name, password
     *
     * @param line is hotel guest informations that by reads from
     * PERSON_INFORMATION_CSV file
     */
    protected void parsingPersonInformation(String line) {
        String information = "";
        int index = line.indexOf(";");
        information = line.substring(0, index);
        setIdNumber(information);

        line = line.substring(index + 1, line.length());
        index = line.indexOf(";");
        information = line.substring(0, index);
        setName(information);

        line = line.substring(index + 1, line.length());
        index = line.indexOf(";");
        information = line.substring(0, index);
        setSurName(information);

        line = line.substring(index + 1, line.length());
        index = line.indexOf(";");
        information = line.substring(0, index);
        setPhoneNumber(information);

        line = line.substring(index + 1, line.length());
        index = line.indexOf(";");
        information = line.substring(0, index);
        setUserName(information);

        line = line.substring(index + 1, line.length());
        index = line.indexOf(";");
        information = line.substring(0, index);
        setPassword(information);
    }

    /**
     * This method deletes contents of RESERVATION_ROOMS_CSV file when update
     * states of hotel rooms
     *
     * @throws IOException file error
     */
    protected void deleteFileContents() throws IOException {
        try (FileOutputStream deleteContent = new FileOutputStream(RESERVATION_ROOMS_CSV)) {
            deleteContent.write(("").getBytes());
        }
    }

    /**
     * This method updates state of hotel rooms at RESERVATION_ROOMS_CSV file
     *
     * @param currentContent contains end states of hotel rooms
     * @throws IOException file error
     */
    protected void updateFileContents(ArrayList<String> currentContent) throws IOException {
        File updateFile = new File(RESERVATION_ROOMS_CSV);
        FileWriter reservationFileWriter = new FileWriter(updateFile, true);

        try (BufferedWriter updateWriter = new BufferedWriter(reservationFileWriter)) {
            for (int i = 0; i < currentContent.size(); ++i) {
                updateWriter.write(currentContent.get(i) + "\n");
            }
        }
    }

    /**
     * This method checks whether the room to be reserved is available
     *
     * @param roomInfo is door number
     * @return if room is available, return true. If room is not available
     * return false
     */
    protected boolean checkRoomNo(String roomInfo) {

        if ((roomInfo.charAt(0) >= 'a' && roomInfo.charAt(0) <= 'j')
                && (roomInfo.charAt(1) >= '1' && roomInfo.charAt(1) <= '5')
                && roomInfo.length() == 2) {
            return true;
        } else {
            System.err.println("Wrong room choosing");
            return false;
        }

    }

    /**
     * This method checks whether the room to be reserved is available
     *
     * @param roomNo is door number
     * @return if room is empty, return true. If room is not empty return false
     */
    protected boolean checkRoomState(int roomNo) {
        if (null == hotel[roomNo]) {
            System.err.println("This room is reserved");
            return false;
        } else {
            switch (hotel[roomNo]) {
                case "E":
                    return true;
                case "F":
                    System.err.println("This room is full");
                    return false;
                default:
                    System.err.println("This room is reserved");
                    return false;
            }
        }
    }

    /**
     * This method shows starting menu of hotel system
     * @param scan get data from file
     */
    public abstract void welcomeToHotelSystem(Scanner scan);

    /**
     * This method shows authority of hotel guest or receptionist
     * @param scan get data from file
     */
    protected abstract void systemAuthorities(Scanner scan);

    /**
     * This method allows entry into the system
     *
     * @param option is a changing state according to user or receptionist login
     * @param scan get data from file
     * @return if sign in is success return true, otherwise return false
     */
    public abstract boolean signIn(boolean option, Scanner scan);

    /**
     * This method allows booking a room
     *
     * @param scan get data from file
     * @throws IOException file error
     */
    protected abstract void bookARoom(Scanner scan) throws IOException;

    /**
     * This method allows canceling reservation
     *
     * @param scan get data from file
     * @return If guest have a reservation, check out is success
     * @throws IOException file error
     */
    protected abstract boolean cancelReservation(Scanner scan) throws IOException;

}
