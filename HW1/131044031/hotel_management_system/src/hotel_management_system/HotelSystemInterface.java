package hotel_management_system;

import java.util.Scanner;

/**
 *
 * @author sefanadir
 */
public interface HotelSystemInterface {

    /**
     * This abstract method gets identification number
     *
     * @return user identification number
     */
    public abstract String getIdNumber();

    /**
     * This abstract method sets identification number
     *
     * @param changeIdNumber is new id number
     */
    public abstract void setIdNumber(String changeIdNumber);

    /**
     * This abstract method gets user name
     *
     * @return user name
     */
    public abstract String getUserName();

    /**
     * This abstract method sets user name
     *
     * @param changeUserName is new user name
     */
    public abstract void setUserName(String changeUserName);

    /**
     * This abstract method gets phone number
     *
     * @return phone number
     */
    public abstract String getPhoneNumber();

    /**
     * This abstract method sets phone number
     *
     * @param changePhoneNumber is new phone number
     */
    public abstract void setPhoneNumber(String changePhoneNumber);

    /**
     * This abstract method gets password
     *
     * @return password
     */
    public abstract String getPassword();

    /**
     * This abstract method sets password
     *
     * @param changePassword is new password
     */
    public abstract void setPassword(String changePassword);

    /**
     * This abstract method prints start menu to screen
     *
     * @param scan get data from file
     * @return user
     */
    public abstract String hotelSystemMenu(Scanner scan);
}
