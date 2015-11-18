# AddressBook
This project is a Java API that could be used to create address books. The API is generalized to allow for it to be the back-end of clients or GUIs that may have rich features or specific data access optimizations to match user needs.

To create an address book, create an instance of the AddressBook.java class:

```java
AddressBook myAddressBook = new AddressBook();
```

To add an entry into the address book, first create an "Entry" object using the builder Java design pattern:

```java
Entry myEntry = new Entry.Builder("John Doe")
    .postalAddress("123 Maple Street, Beverly Hills, CA 90210")
    .phoneNumber("818-555-1234")
    .note("sample address book entry")
    .build();
```

Finally, add the entry to the address book using AddressBook.java's "addEntry" method:

```java
myAddressBook.addEntry(myEntry);
```

The documentation for more methods of AddressBook.java are in that file, which uses the Javadoc code documentation format. The corresponding Javadoc cn be viewed on this webpage: http://tmnt-raphael.github.io/AddressBook.
