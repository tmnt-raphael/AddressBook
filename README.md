# AddressBook
This project is a Java developer API used to create address books. It focuses on being production quality, following good coding practices, and being maintainable.

This API aims to be barebones. Consequently, this API is meant to be the back-end of clients or GUIs that may have rich features or data access optimizations for specific user needs.

To create an address book, create an instance of the AddressBook.java:

```bash
AddressBook myAddressBook = new AddressBook();
```

To add an entry into the address book, first create an Entry object using the builder Java design pattern:

```bash
Entry myEntry = new Entry.Builder("John Doe")
    .postalAddress("123 Maple Street, Beverly Hills, CA 90210")
    .phoneNumber("818-555-1234")
    .note("sample address book entry")
    .build();
```

Finally, add the entry to the address book using AddressBook.java's addEntry method:

```bash
myAddressBook.addEntry(myEntry);
```

The documentation for more methods of AddressBook.java are in that file, which uses the Javadoc code documentation format.
