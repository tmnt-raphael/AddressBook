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
    
    // sample address book to show how to add an entry
    AddressBook sampleAddressBook = new AddressBook();
    Entry sampleEntry = new Entry.Builder("John Doe")
        .postalAddress("123 Maple Street, Beverly Hills, CA 90210")
        .phoneNumber("818-555-1234")
        .note("sample address book entry")
        .build();
    sampleAddressBook.addEntry(sampleEntry);
    System.out.print("First address book:\n" + sampleAddressBook);
    
    // an example of how to load entries from a text file
    AddressBook loadedAddressBook = new AddressBook("AddressBookData.txt");
    System.out.print("Second address book:\n" + loadedAddressBook);
    
  }

}
