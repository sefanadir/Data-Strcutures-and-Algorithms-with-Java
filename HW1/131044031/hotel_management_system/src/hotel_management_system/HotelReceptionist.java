package hotel_management_system;

import static hotel_management_system.HotelSystemInformation.RESERVATION_ROOMS_CSV;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sefanadir
 */
public class HotelReceptionist extends HotelSystemInformation {

    protected static final String RECEPTIONIST_CSV = "./ receptionist.csv";
    HotelGuest guest = new HotelGuest();

    public HotelReceptionist() {

    }

    /**
     *
     * @param scan get data from file
     */
    @Override
    public void welcomeToHotelSystem(Scanner scan) {
        System.out.println("Welcome administrator to Star Hotel");
        systemAuthorities(scan);
    }

    /**
     * This method gets necessary information from receptionist
     *
     * @param option is a changing state according to guest or receptionist
     * @return if sign in is success return true, otherwise return false
     */
    @Override
    public boolean signIn(boolean option, Scanner scan) {

        String userName;
        String password;
        boolean successSignIn = false;

        System.out.print("Username: ");
        userName = scan.next();
        userName = userName.replaceAll("\\s", "");
        System.out.print("Password: ");
        password = scan.next();
        password = password.replaceAll("\\s", "");
        try {
            String line;
            FileReader fileReader = new FileReader(RECEPTIONIST_CSV);
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.indexOf(userName) > 0) {
                        if (line.indexOf(password) > 0) {
                            successSignIn = true;
                            return true;
                        }
                    }
                }
                bufferedReader.close();
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Unable to open file " + RECEPTIONIST_CSV);
        } catch (IOException ex) {
            System.err.println("Error reading file " + RECEPTIONIST_CSV);
        }
        if (successSignIn == false) {
            System.err.println("Wrong username or password");
        }
        return false;
    }

    /**
     *
     * @param scan get data from file
     * @throws IOException file error
     */
    @Override
    protected void bookARoom(Scanner scan) throws IOException {
        guest.signUp(false, scan);
        setGuestInformation();
        String room;
        showRooms();
        System.out.println("E:empty R:reserved F:Full");
        System.out.print("Please choose a room: \n" + "Ex: a4, b2, d7 etc.\n");
        do {
            do {
                room = scan.next();

            } while ("".equals(room) || checkRoomNo(room) == false);
        } while (checkRoomState(determineRoomNumber(room)) == false);
        updateRoomState(determineRoomNumber(room), "reservation");
        setRezervationRoom(room);
        updateHotelRooms();
        recordReservedRoom();
    }

    /**
     *
     * @param scan get data from file
     * @return if cancel reservation is success, return true
     * @throws IOException file error
     */
    @Override
    protected boolean cancelReservation(Scanner scan) throws IOException {
        guest.signIn(false, scan);
        setGuestInformation();
        boolean checkReservation = false;
        ArrayList<String> fileContents = new ArrayList<>();
        try {
            String line;
            FileReader fileReader = new FileReader(RESERVATION_ROOMS_CSV);
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                while ((line = bufferedReader.readLine()) != null) {
                    fileContents.add(line);
                }
                bufferedReader.close();

                for (int i = 0; i < fileContents.size(); ++i) {

                    String[] contents = fileContents.get(i).split(";");

                    if (contents[0].equals(guest.getIdNumber())) {
                        String newLine = "C;" + fileContents.get(i);
                        int roomNo = determineRoomNumber(contents[1]);
                        fileContents.remove(i);
                        fileContents.add(newLine);
                        updateRoomState(roomNo, "cancel");
                        updateHotelRooms();
                        checkReservation = true;
                        System.out.println("cancel reservation success ✓");
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Unable to open file " + RESERVATION_ROOMS_CSV);
        } catch (IOException ex) {
            System.err.println("Error reading file " + RESERVATION_ROOMS_CSV);
        }
        if (checkReservation == false) {
            System.err.println("You have no reserved room");
        }
        deleteFileContents();
        updateFileContents(fileContents);
        return checkReservation;
    }

    /**
     * This method shows authority of hotel receptionist
     * @param scan get data from file
     */

    @Override
    protected void systemAuthorities(Scanner scan) {
        String choosing;
        do {
            System.out.println("1- book a room");
            System.out.println("2- cancel reservation");
            System.out.println("3- check-in");
            System.out.println("4- check-out");
            System.out.println("5- exit");
            do {
                choosing = scan.next();
                if (!("1".equals(choosing) || "2".equals(choosing) || "3".equals(choosing)
                        || "4".equals(choosing) || "5".equals(choosing))) {
                    System.err.println("Wrong choosing");
                }
            } while (!("1".equals(choosing) || "2".equals(choosing) || "3".equals(choosing)
                    || "4".equals(choosing) || "5".equals(choosing)));

            if (null != choosing) {
                switch (choosing) {
                    case "1":
                        try {
                            bookARoom(scan);
                        } catch (IOException ex) {
                            Logger.getLogger(HotelReceptionist.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case "2":
                        try {
                            cancelReservation(scan);
                        } catch (IOException ex) {
                            Logger.getLogger(HotelReceptionist.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case "3":
                        checkIn(scan);
                        break;
                    case "4":
                        checkOut(scan);
                        break;
                }
            }
        } while (!"5".equals(choosing));
    }

    /**
     * This method confirms reservation
     * @param scan get data from file
     * @return if check is success, return true
     */

    protected boolean checkIn(Scanner scan) {
        boolean in = false;
        guest.signIn(false, scan);
        setGuestInformation();

        ArrayList<String> fileContents = new ArrayList<>();
        try {
            String line;
            FileReader fileReader = new FileReader(RESERVATION_ROOMS_CSV);
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                while ((line = bufferedReader.readLine()) != null) {
                    fileContents.add(line);
                }
                bufferedReader.close();

                for (int i = 0; i < fileContents.size(); ++i) {

                    String[] contents = fileContents.get(i).split(";");

                    if (contents[0].equals(guest.getIdNumber())) {
                        String newLine = "F;" + fileContents.get(i);
                        int roomNo = determineRoomNumber(contents[1]);
                        fileContents.remove(i);
                        fileContents.add(newLine);
                        updateRoomState(roomNo, "full");
                        updateHotelRooms();
                        in = true;
                        System.out.println("check in success ✓");
                    }
                }
                deleteFileContents();
                updateFileContents(fileContents);

            }
        } catch (FileNotFoundException ex) {
            System.err.println("Unable to open file " + RESERVATION_ROOMS_CSV);
        } catch (IOException ex) {
            System.err.println("Error reading file " + RESERVATION_ROOMS_CSV);
        }
        if (in == false) {
            System.err.println("you have no rezervation x");
        }
        return in;
    }

    /**
     * This method allows the time at which a lodger must vacate a room
     * @param scan get data from file
     * @return If check out is success, return true
     */
    protected boolean checkOut(Scanner scan) {
        boolean out = false;
        guest.signIn(false, scan);
        setGuestInformation();
        ArrayList<String> fileContents = new ArrayList<>();
        try {
            String line;
            FileReader fileReader = new FileReader(RESERVATION_ROOMS_CSV);
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                while ((line = bufferedReader.readLine()) != null) {
                    fileContents.add(line);
                }
                bufferedReader.close();

                for (int i = 0; i < fileContents.size(); ++i) {

                    String[] contents = fileContents.get(i).split(";");

                    if (contents[0].equals("F") && contents[1].equals(guest.getIdNumber())) {
                        String updateLine = fileContents.get(i).substring(2, fileContents.get(i).length());
                        String newLine = "E;" + updateLine;
                        int roomNo = determineRoomNumber(contents[2]);
                        fileContents.remove(i);
                        fileContents.add(newLine);
                        updateRoomState(roomNo, "cancel");
                        updateHotelRooms();
                        out = true;
                        System.out.println("check out success ✓");
                    }
                }
                deleteFileContents();
                updateFileContents(fileContents);
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Unable to open file " + RESERVATION_ROOMS_CSV);
        } catch (IOException ex) {
            System.err.println("Error reading file " + RESERVATION_ROOMS_CSV);
        }
        if (out == false) {
            System.err.println("You have no check in x");
        }
        return out;
    }

    /**
     * This method initializes informations of guest after log in to system
     */
    private void setGuestInformation() {
        setName(guest.getName());
        setSurName(guest.getSurName());
        setUserName(guest.getUserName());
        setPassword(guest.getPassword());
        setPhoneNumber(guest.getPhoneNumber());
        setIdNumber(guest.getIdNumber());
    }
}
