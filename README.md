AddressBook
====

## Introduction

This project provides a Java API that could be used to create address books. The API is generalized to allow for it to be the back-end of clients or GUIs. The project's website is located here: http://tmnt-raphael.github.io/AddressBook/.

## Creating an Address Book

To create an address book, create an instance of the AddressBook.java class:

```java
AddressBook sampleAddressBook = new AddressBook();
```

To add an entry into the address book, first create an "Entry" object using the builder Java design pattern:

```java
Entry sampleEntry = new Entry.Builder("John Doe")
    .postalAddress("123 Maple Street, Beverly Hills, CA 90210")
    .phoneNumber("818-555-1234")
    .note("sample address book entry")
    .build();
```

Finally, add the entry to the address book using AddressBook.java's "addEntry" method:

```java
sampleAddressBook.addEntry(sampleEntry);
```

## Loading an Address Book from a Data File

Another way to create an address book is to load a file containing address book entry data:

```java
AddressBook loadedAddressBook = new AddressBook("AddressBookData.txt");
```

Now, an address book with initial entries has been created. The format of the input data that is used is JSON, which would look like this:

```json
[
  {"name": "Ken",
   "postalAddress": "",
   "note": "NYU student"
  },
  {"name": "John Smith",
   "phoneNumber": "949-555-1234",
   "note": "software engineer"
  },
  {"name": "Jane Doe",
   "emailAddress": "jane.doe@gmail.com"
  }
]
```

The "name" data field is required for each object in the JSON. The find out what the other data fields' names are, look at the methods of the "Builder" class of the "Entry" class.

## Further Documentaion

The documentation for more methods of the AddressBook class are in the AddressBook.java file, which uses the Javadoc code documentation format. The corresponding Javadoc can be viewed on this webpage: http://tmnt-raphael.github.io/AddressBook/Javadoc.html.
