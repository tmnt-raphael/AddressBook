import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 * @author Ken
 * <p>
 * This class represents an address book. The address book stores instances of Entry classes. Each
 * of these instances represent an entry within the address book.
 * </p>
 *
 * <p>
 * There are two ways to add entries to the address book: The first way is to load the entries from
 * a data file using this class' constructor that has the file's name as the argument. The second
 * way to add entries is to use the "addEntry" method.
 * </p>
 * 
 * <p>
 * To view an entry, use the "searchToGetEntries" or "searchToGetEntryIndices" methods to search for
 * a string. If the string is contained in any part of an entry, this would be considered a match.
 * The method will return an array of the search results, which will either have the entries or the
 * indices at which the entries are located in AddressBook's entryList instance field.
 * </p>
 */
public class AddressBook {
  
  // all of the address book's entries
  private ArrayList<Entry> entryList = new ArrayList<Entry>();
  
  /**
   *  This is the default constructor.
   */
  public AddressBook() {
  }
  
  /**
   * <p>
   * This constructor initializes the address book's entries using a data file. The data file shall
   * be of JSON format. The JSON shall be a JavaScript array. The elements of the array shall be
   * JavaScript associative arrays. Each associative array represents an entry of the address book.
   * The key-value pairs of the associative arrays represent the attributes and values of the
   * entries.
   * </p>
   * 
   * <p>
   * Here is an example of what a JSON would look like:
   * </p>
   * 
   * <p>
   * [
   *   {"name": "Ken",
   *    "postalAddress": "",
   *    "note": "NYU student"
   *   },
   *   {"name": "John Smith",
   *    "phoneNumber": "949-555-1234",
   *    "note": "software engineer"
   *   },
   *   {"name": "Jane Doe",
   *    "emailAddress": "jane.doe@gmail.com"
   *   }
   * ]
   * </p>
   * 
   * <p>
   * As you can see, this JSON has three entries.
   * <p>
   * 
   * @param fileName
   * The name of the file to be loaded.
   * 
   * @throws IOException
   * Make sure the file name is correct.
   */
  public AddressBook(String fileName) throws IOException {
    // loads the data file's text into a String
    String fileText = readFile(fileName, StandardCharsets.UTF_8);
    
    // parses the text of the data file and converts it to a JSONArray object
    JSONArray JSONofEntries = (JSONArray) JSONValue.parse(fileText);
    
    // adds the entries of the JSON to the address book
    addEntriesFromJSON(JSONofEntries);
  }
  
  // reads an address book data file and returns the file's text
  private String readFile(String fileName, Charset encoding) throws IOException {
    byte[] fileBytes = Files.readAllBytes(Paths.get(fileName));
    return new String(fileBytes, encoding);
  }
  
  // adds entries from a JSONArray object
  private void addEntriesFromJSON(JSONArray JSONofEntries) {
    int numOfEntries = JSONofEntries.size();

    // loops through the entries in the data file
    for(int i = 0; i < numOfEntries; i++) {
      Entry entryToAdd = new Entry.Builder("").build();
      JSONObject JSONofEntry = (JSONObject) JSONofEntries.get(i);
      Iterator<String> attributesOfEntry = JSONofEntry.keySet().iterator();

      // loops through the attributes of the entries
      while(attributesOfEntry.hasNext()) {
        String attribute = attributesOfEntry.next();
        String value = (String) JSONofEntry.get(attribute);
        entryToAdd.setter(attribute, value);
        attributesOfEntry.remove();
      }

      this.addEntry(entryToAdd);
    }
  }
  
  /**
   * This method adds an entry to the address book's entry list.
   * 
   * @param entryToAdd
   * The entry desired to be added.
   */
  public void addEntry(Entry entryToAdd) {
    entryList.add(entryToAdd);
  }
  
  /**
   * This method removes an entry given the entry's index.
   * 
   * @param entryIndex
   * The index of the entry desired to be removed.
   */
  public void removeEntry(int entryIndex) {
    entryList.remove(entryIndex);
  }
  
  /**
   * This method gets the entry by using a direct index lookup.
   * 
   * @param entryIndex
   * The index of the desired entry.
   * 
   * @return
   * The entry of the corresponding index.
   */
  public Entry getEntry(int entryIndex) {
    Entry sourceEntry = entryList.get(entryIndex);
    
    // creates a copy of the entry so that the user cannot modify the entry
    Entry entryCopy = new Entry.Builder(sourceEntry.name)
        .postalAddress(sourceEntry.postalAddress)
        .phoneNumber(sourceEntry.phoneNumber)
        .emailAddress(sourceEntry.emailAddress)
        .note(sourceEntry.note)
        .build();
    
    return entryCopy;
  }
  
  /**
   * This method searches through each attribute of the entries in this address book. The search is
   * case insensitive. The search result is returned. The search result contains the entries that
   * have the search string.
   * 
   * @param searchString
   * The string to be searched
   * 
   * @return
   * An array of the entries that have the desired search string.
   */
  public List<Entry> search(String searchString) {
    searchString = searchString.toLowerCase();
    List<Entry> searchResult =  new ArrayList<Entry>();
    
    // loops through each entry
    for(int i = 0; i < entryList.size(); i++) {
      Entry entryToAnalyze = entryList.get(i); 
      boolean matchFound = false;
      
      // searches each attribute until the string is found
      for (int j = 0; j < Entry.attributes.length; j++) {
        if (entryToAnalyze.getter(Entry.attributes[j]).toLowerCase().contains(searchString)) {
          matchFound = true;
          break;
        }
      }
      
      if(matchFound == true) {
        searchResult.add(getEntry(i));
      }
    }
    
    return searchResult;
  }
  
  /**
   * This method searches through each attribute of the entries in this address book. The search is
   * case insensitive. The returned result contains the entries that have the search string.
   * 
   * @param searchString
   * The string to be searched
   * 
   * @return
   * An array of the entries that have the desired search string.
   */
  public List<Entry> searchToGetEntries(String searchString) {
    searchString = searchString.toLowerCase();
    List<Entry> searchResult =  new ArrayList<Entry>();
    
    // loops through each entry
    for(int i = 0; i < entryList.size(); i++) {
      Entry entryToAnalyze = entryList.get(i); 
      boolean matchFound = false;
      
      // searches each attribute until the string is found
      for (int j = 0; j < Entry.attributes.length; j++) {
        if (entryToAnalyze.getter(Entry.attributes[j]).toLowerCase().contains(searchString)) {
          matchFound = true;
          break;
        }
      }
      
      if(matchFound == true) {
        searchResult.add(getEntry(i));
      }
    }
    
    return searchResult;
  }
  
  /**
   * This method searches through each attribute of the entries in this address book. The search is
   * case insensitive. The returned search result contains the indices of the entries that have the
   * search string.
   * 
   * @param searchString
   * The string to be searched
   * 
   * @return
   * An array of the indices of the entries that have the desired search string.
   */
  public List<Integer> searchToGetEntryIndices(String searchString) {
    searchString = searchString.toLowerCase();
    List<Integer> searchResult =  new ArrayList<Integer>();
    
    // loops through each entry
    for(int i = 0; i < entryList.size(); i++) {
      Entry entryToAnalyze = entryList.get(i); 
      boolean matchFound = false;
      
      // searches each attribute until the string is found
      for (int j = 0; j < Entry.attributes.length; j++) {
        if (entryToAnalyze.getter(Entry.attributes[j]).toLowerCase().contains(searchString)) {
          matchFound = true;
          break;
        }
      }
      
      if(matchFound == true) {
        searchResult.add(new Integer(i));
      }
    }
    
    return searchResult;
  }
  
  /**
   * This method saves the entries of the address book to a file. The format of the file will be
   * JSON. Spaces and newline characters are included in the output in order to make the JSON more
   * readable to humans.
   * 
   * @param fileName
   * The name of the file to save to. Includes the file extension.
   * 
   * @throws FileNotFoundException
   * Make sure the directory to write to is accessible.
   */
  public void saveToFile(String fileName) throws FileNotFoundException {
    String outputString = "[";
    
    // loops through the entries to add JavaScript objects
    for(int i = 0; i < entryList.size(); i++) {
      outputString = outputString + "{";
      
      // loops through the attributes to add key-value pairs
      for(int j = 0; j < Entry.attributes.length; j++) {
        String attribute = Entry.attributes[j];
        String value = entryList.get(i).getter(attribute);
        if(!value.equals("")) {
          outputString = outputString + "\"";
          outputString = outputString + attribute;
          outputString = outputString + "\": ";
          outputString = outputString + "\"";
          outputString = outputString + value;
          outputString = outputString + "\",\n ";
        }
      }
      
      // removes trailing comma and spaces
      outputString = outputString.substring(0, outputString.length()-3);
      
      outputString = outputString + "}\n ,\n ";
    }
    
    // removes trailing comma and spaces
    String lastCharOfOutputString = outputString.substring(outputString.length()-1);
    if (!lastCharOfOutputString.equals("[")) {
      outputString = outputString.substring(0, outputString.length()-5);
    }
    
    outputString = outputString + "\n]";
    
    // writes the output file
    PrintWriter outputFile = new PrintWriter(fileName);
    outputFile.println(outputString);
    outputFile.close();
  }
  
  /**
   * Generates and returns the string representation of the entries of the address book.
   */
  public String toString() {
    String output = "";
    for(int i = 0; i < entryList.size(); i++) {
      output = output + entryList.get(i).toString() + "\n";
    }
    return output;
  }
  
}
