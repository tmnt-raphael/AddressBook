package addressbook;

import java.io.IOException;

/**
 * @author Ken
 *
 * <p>
 * This class tests AddressBook.java by creating an instance of AddressBook and loads in entries
 * from the AddressBookData.txt data file. The data file uses the JSON format.
 * </p>
 */
public class AddressBookApp {

  /**
   * @param args
   * <p>
   * This method does not use its argument.
   * </p>
   * 
   * @throws IOException
   * Make sure the file name is correct.
   */
  public static void main(String[] args) throws IOException {
    AddressBook myAddressBook = new AddressBook("AddressBookData.txt");
    System.out.print(myAddressBook);
  }

}
