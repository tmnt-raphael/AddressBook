/**
 * @author Ken
 * 
 * <p>
 * This class represents an address book's entry. The entry contains the attributes, such as name
 * postal address, phone number, etc. The address book, represented by an instance of AddressBook,
 * will generally contain multiple instances of the Entry class.
 * </p>
 */
public class Entry {
  /**
   * This is an array of the allowed attributes of an entry.
   */
  public static String[] attributes = {"name", "postalAddress", "phoneNumber", "emailAddress",
      "note"};
  
  /**
   * This is an array of the labels corresponding to the attributes. These labels are used by the
   * toString() method of this class.
   */
  private String[] attributeLabels = {"Name", "Postal address", "Phone number", "Email address",
      "Note"};
  
  public String name;
  public String postalAddress;
  public String phoneNumber;
  public String emailAddress;
  public String note;
  
  /**
   * This class initializes the attributes of the entry.
   * 
   * @author Ken
   *
   */
  public static class Builder {
    // required parameter
    private String name;
    
    // optional parameters
    private String postalAddress = "";
    private String phoneNumber = "";
    private String emailAddress = "";
    private String note = "";
    
    /**
     * This constructor sets the required parameters of the entry.
     * 
     * @param name
     * The name desired to be set to.
     */
    public Builder(String name) {
      this.name = name;
    }
    
    /**
     * This method sets the postal address.
     * 
     * @param postalAddress
     * The postal address desired to be set to.
     * 
     * @return
     * The builder object that is initializing the entry.
     */
    public Builder postalAddress(String postalAddress) {
      this.postalAddress = postalAddress;
      return this;
    }
    
    /**
     * This method sets the phone number.
     * 
     * @param phoneNumber
     * The phone number desired to be set to.
     * 
     * @return
     * The builder object that is initializing the entry.
     */
    public Builder phoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
      return this;
    }
    
    /**
     * This method sets the email address.
     * 
     * @param emailAddress
     * The email address desired to be set to.
     * 
     * @return
     * The builder object that is initializing the entry.
     */
    public Builder emailAddress(String emailAddress) {
      this.emailAddress = emailAddress;
      return this;
    }
    
    /**
     * This method sets the note.
     * 
     * @param note
     * The note desired to be set to.
     * 
     * @return
     * The builder object that is initializing the entry.
     */
    public Builder note(String note) {
      this.note = note;
      return this;
    }
    
    /**
     * This method returns the object that invokes this method.
     * 
     * @return
     * The entry desired to be created.
     */
    public Entry build() {
      return new Entry(this);
    }
  }
  
  private Entry(Builder builder) {
    name = builder.name;
    postalAddress = builder.postalAddress;
    phoneNumber = builder.phoneNumber;
    emailAddress = builder.emailAddress;
    note = builder.note;
  }
  
  /**
   * This method sets the value corresponding to the attribute.
   * 
   * @param attribute
   * The attribute desired to be set.
   * 
   * @param value
   * The value desired to be set to.
   */
  public void setter(String attribute, String value) {
    if(attribute.equals("name")) {
      name = value;
    } else if(attribute.equals("postalAddress")) {
      postalAddress = value;
    } else if(attribute.equals("phoneNumber")) {
      phoneNumber = value;
    } else if(attribute.equals("emailAddress")) {
      emailAddress = value;
    } else if(attribute.equals("note")) {
      note = value;
    }
  }
  
  /**
   * This method gets the value corresponding to the attribute in question.
   * 
   * @param attribute
   * The attribute desired to be looked up.
   * 
   * @return
   * The value corresponding to the attribute
   */
  public String getter(String attribute) {
    String output = "";
    if(attribute.equals("name")) {
      output = name;
    } else if(attribute.equals("postalAddress")) {
      output = postalAddress;
    } else if(attribute.equals("phoneNumber")) {
      output = phoneNumber;
    } else if(attribute.equals("emailAddress")) {
      output = emailAddress;
    } else if(attribute.equals("note")) {
      output = note;
    }
    return output;
  }
  
  /**
   * Generates and returns the string representation of the attributes and values of the entry.
   */
  public String toString() {
    String output = "";
    for(int i = 0; i < attributes.length; i++) {
      String attribute = attributes[i];
      String value = getter(attribute);
      if(!value.equals("")) {
        output = output + attributeLabels[i] + ": " + value + "\n";
      }
    }
    return output;
  }
}
