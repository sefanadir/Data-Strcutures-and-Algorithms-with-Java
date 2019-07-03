package hotel_management_system;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author sefanadir
 */
public class HotelSystemMain {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException file error
     */
    public static void main(String[] args) throws IOException {

        String choosing;
        File file = new File("deneme.txt");
        Scanner scan = new Scanner(file);
        HotelGuest guest = new HotelGuest();
        HotelReceptionist receptionist = new HotelReceptionist();
        System.out.println("**************************");
        System.out.println("*                        *");
        System.out.println("*       STAR HOTEL       *");
        System.out.println("*                        *");
        System.out.println("**************************");
        int testing=3;
        while(testing>0){
            choosing = guest.hotelSystemMenu(scan);

            switch (choosing) {
                case "1":
                    if (receptionist.signIn(true, scan)) {
                        receptionist.welcomeToHotelSystem(scan);
                    }
                    break;
                case "2":
                    if (guest.signIn(true,scan)) {
                        guest.welcomeToHotelSystem(scan);
                    }
                    break;
                case "3":
                    guest.signUp(true ,scan);
                    break;
            }
            --testing;
        }
    }

}
