package hotel_management_system;

import static hotel_management_system.HotelSystemInformation.PERSON_INFORMATION_CSV;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sefanadir
 */
public class HotelGuest extends HotelSystemInformation {

    String room;
    String info;
    Scanner scan = new Scanner(System.in);

    public HotelGuest() {

    }

    /**
     *
     * @param scan get data from file
     */
    @Override
    public void welcomeToHotelSystem(Scanner scan) {
        System.out.println("Welcome to Star Hotel");
        systemAuthorities(scan);
    }

    /**
     * This method allows sign up to hotel system
     * @param scan get data from file
     * @param option is changing state according to guest or receptionist
     */
    public void signUp(boolean option, Scanner scan) {

        do {
            System.out.print("Name: ");
            info = scan.next();
        } while ("".equals(info));
        info = info.replaceAll("\\s", "");
        info = info.toLowerCase();
        setName(info);

        do {
            System.out.print("Surname: ");
            info = scan.next();
        } while ("".equals(info));
        info = info.replaceAll("\\s", "");
        info = info.toLowerCase();
        setSurName(info);

        do {
            System.out.print("Phone: ");
            info = scan.next();
        } while ("".equals(info));
        info = info.replaceAll("\\s", "");
        setPhoneNumber(info);

        if (option == true) {
            do {
                System.out.print("Password: ");
                info = scan.next();
            } while ("".equals(info));
        } else {
            info = getName() + ".123";
        }
        info = info.replaceAll("\\s", "");
        info = info.toLowerCase();
        setPassword(info);

        createUserName();
        recordPersonInformation();
        System.out.println("username has been identified as " + getUserName());
        if (option == false) {
            System.out.println("password has been sended to your phone");
        }
    }

    /**
     * This method gets necessary information from user for sign in to system
     * @param scan data from file
     * @param option is a changing state according to guest or receptionist
     * @return if sign in is success return true, otherwise return false
     */
    @Override
    public boolean signIn(boolean option, Scanner scan) {
        String signInInfo_1 = null;
        String signInInfo_2 = null;
        boolean successSignIn = false;

        if (option == true) {
            System.out.print("Username: ");
            signInInfo_1 = scan.next();
            signInInfo_1 = signInInfo_1.replaceAll("\\s", "");

            System.out.print("Password: ");
            signInInfo_2 = scan.next();
            signInInfo_2 = signInInfo_2.replaceAll("\\s", "");
        } else {
            System.out.print("Name: ");
            signInInfo_1 = scan.next();
            signInInfo_1 = signInInfo_1.replaceAll("\\s", "");

            System.out.print("Surname: ");
            signInInfo_2 = scan.next();
            signInInfo_2 = signInInfo_2.replaceAll("\\s", "");
        }
        try {
            String line;
            FileReader fileReader = new FileReader(PERSON_INFORMATION_CSV);
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.indexOf(signInInfo_1) > 0) {
                        if (line.indexOf(signInInfo_2) > 0) {
                            successSignIn = true;
                            parsingPersonInformation(line);
                        }
                    }
                }
                bufferedReader.close();
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Unable to open file " + PERSON_INFORMATION_CSV);
        } catch (IOException ex) {
            System.err.println("Error reading file " + PERSON_INFORMATION_CSV);
        }

        if (successSignIn == false) {
            System.err.println("Wrong informations try again");
        }
        return successSignIn;
    }

    /**
     * This method allows to choose hotel room and checks errors
     *
     * @param scan get data from file
     * @throws IOException file error
     */
    @Override
    protected void bookARoom(Scanner scan) throws IOException {
        String room;
        showRooms();
        System.out.println("E:empty R:reserved F:Full");
        System.out.print("Please choose a room: \n" + "Ex: a4, b2, d5 etc.\n");
        do {
            do {
                room = scan.next();

            } while (checkRoomNo(room) == false);
        } while (checkRoomState(determineRoomNumber(room)) == false);
        updateRoomState(determineRoomNumber(room), "reservation");
        setRezervationRoom(room);
        updateHotelRooms();
        recordReservedRoom();
    }

    /**
     * This method allows to cancel reservation of hotel guest
     * @param scan get data from file
     * @return If guest have a reservation, check out is success
     * @throws IOException file error
     */
    @Override
    protected boolean cancelReservation(Scanner scan) throws IOException {
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

                    if (contents[0].equals(getIdNumber())) {
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
            System.out.println("Unable to open file " + RESERVATION_ROOMS_CSV);
        } catch (IOException ex) {
            System.out.println("Error reading file " + RESERVATION_ROOMS_CSV);
        }
        if (checkReservation == false) {
            System.err.println("You have no reserved room");
        }
        deleteFileContents();
        updateFileContents(fileContents);
        return checkReservation;
    }

    /**
     * This method shows authority of hotel guest
     * @param scan get data from file
     */
    @Override
    protected void systemAuthorities(Scanner scan) {
        String choosing;
        do {
            System.out.println("1- book a room");
            System.out.println("2- cancel reservation");
            System.out.println("3- exit");
            do {
                choosing = scan.next();
                if (!("1".equals(choosing) || "2".equals(choosing) || "3".equals(choosing))) {
                    System.err.println("Wrong choosing");
                }
            } while (!("1".equals(choosing) || "2".equals(choosing) || "3".equals(choosing)));

            if ("1".equals(choosing)) {
                try {
                    bookARoom(scan);
                } catch (IOException ex) {
                    Logger.getLogger(HotelGuest.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if ("2".equals(choosing)) {
                try {
                    cancelReservation(scan);
                } catch (IOException ex) {
                    Logger.getLogger(HotelGuest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } while (!"3".equals(choosing));
    }

    /**
     * This method records person informations to PERSON_INFORMATION_CSV file
     */
    protected void recordPersonInformation() {
        File personInfoFile = new File(PERSON_INFORMATION_CSV);
        FileWriter personInfoFileWriter = null;
        try {
            personInfoFileWriter = new FileWriter(personInfoFile, true);
        } catch (IOException ex) {
            Logger.getLogger(HotelGuest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (BufferedWriter personInfoWriter = new BufferedWriter(personInfoFileWriter)) {
            String id = determineIdNumber();
            personInfoWriter.write(id + ";");
            personInfoWriter.write(getName() + ";");
            personInfoWriter.write(getSurName() + ";");
            personInfoWriter.write(getPhoneNumber() + ";");
            personInfoWriter.write(getUserName() + ";");
            personInfoWriter.write(getPassword() + ";\n");
        } catch (IOException ex) {
            Logger.getLogger(HotelGuest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
