package com.bridgelabz;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class AddressBook {
    static HashMap<String, ArrayList<ContactPerson>> addressBookList = new HashMap<>();
    static ArrayList<ContactPerson> currentAddressBook;
    static String currentAddressBookName;
    static HashMap<String, ArrayList<ContactPerson>> cityContactList = new HashMap<>();
    static HashMap<String, ArrayList<ContactPerson>> stateContactList = new HashMap<>();

    static Scanner scanner = new Scanner(System.in);

    ContactPerson createContact() {
        System.out.println("Enter first name");
        String firstName = scanner.next();
        System.out.println("Enter last name");
        String lastName = scanner.next();
        System.out.println("Enter address");
        String address = scanner.next();
        System.out.println("Enter city");
        String city = scanner.next();
        System.out.println("Enter state");
        String state = scanner.next();
        System.out.println("Enter ZipCode");
        int zipCode = scanner.nextInt();
        System.out.println("Enter phoneNumber");
        long phoneNumber = scanner.nextLong();
        System.out.println("Enter Email");
        String email = scanner.next();

        ContactPerson person = new ContactPerson(firstName, lastName, address, city, state, zipCode, phoneNumber, email);
        System.out.println("created new contact");
        return person;
    }

    void addContact(ContactPerson person) {
        boolean isDuplicate = checkDuplicateContact(person);
        if (isDuplicate) {
            System.out.println("Contact name already exists");
        } else {
            currentAddressBook.add(person);

            //add to city contact list
            String city = person.getCity();
            ArrayList<ContactPerson> list;
            if (cityContactList.containsKey(city)) {
                list = cityContactList.get(city);
                list.add(person);

            } else {
                list = new ArrayList<>();
                list.add(person);
                cityContactList.put(city, list);
            }

            //add to State contact list
            String state = person.getState();
            if (stateContactList.containsKey(state)) {
                list = stateContactList.get(state);
                list.add(person);

            } else {
                list = new ArrayList<>();
                list.add(person);
                stateContactList.put(state, list);
            }
            System.out.println("contact added to AddressBook " + currentAddressBookName);
            System.out.println(person);
        }
    }

    void editContact() {
        System.out.println("enter name to edit contact");
        String name = scanner.next();
        for (ContactPerson person : currentAddressBook) {
            if (person.getFirstName().equalsIgnoreCase(name)) {
                System.out.println("Enter first name");
                person.setFirstName(scanner.next());
                System.out.println("Enter last name");
                person.setLastName(scanner.next());
                System.out.println("Enter address");
                person.setAddress(scanner.next());
                System.out.println("Enter city");
                person.setCity(scanner.next());
                System.out.println("Enter state");
                person.setState(scanner.next());
                System.out.println("Enter ZipCode");
                person.setZipCode(scanner.nextInt());
                System.out.println("Enter phoneNumber");
                person.setPhoneNumber(scanner.nextLong());
                System.out.println("Enter Email");
                person.setEmail(scanner.next());
                System.out.println("contact updated successfully.");
                System.out.println(person);
                break;
            }
        }
    }

    void deleteContact() {
        System.out.println("enter name to delete contact");
        String name = scanner.next();
        for (ContactPerson contact : currentAddressBook) {
            if (contact.getFirstName().equalsIgnoreCase(name)) {
                System.out.println("contact found:");
                System.out.println(contact);
                System.out.println("confirm to delete (y/n)");
                if (scanner.next().equalsIgnoreCase("y")) {
                    currentAddressBook.remove(contact);
                    System.out.println("contact deleted");
                }
                return;
            }
        }
        System.out.println("Opps... contact not found");
    }

    void addNewAddressBook() {
        System.out.println("Enter name for AddressBook");
        String AddressBookName = scanner.next();
        ArrayList<ContactPerson> AddressBook = new ArrayList();
        addressBookList.put(AddressBookName, AddressBook);
        System.out.println("new AddressBook created");
        currentAddressBook = addressBookList.get(AddressBookName);
        currentAddressBookName = AddressBookName;
    }

    void selectAddressBook() {
        System.out.println(addressBookList.keySet());
        System.out.println("enter name of address book");
        String addressBookName = scanner.next();

        for (String key : addressBookList.keySet()) {
            if (key.equalsIgnoreCase(addressBookName)) {
                currentAddressBook = addressBookList.get(key);
                currentAddressBookName = key;
            }
        }
        System.out.println("current AddressBook is " + currentAddressBookName);

    }

    void viewContacts() {
        System.out.println("*****************************\n1.View by City \n2.View by State");
        switch (scanner.nextInt()) {
            case 1:
                viewContactByCity();
                break;
            case 2:
                viewContactByState();
                break;
            default:
                viewContacts();
                break;
        }
    }

    void viewContactByCity() {
        System.out.println("Enter City:");
        String city = scanner.next();
        for (String key : cityContactList.keySet()) {
            if (key.equalsIgnoreCase(city)) {
                stateContactList.get(city).forEach(person -> System.out.println(person));
            }
        }
    }

    void viewContactByState() {
        System.out.println("Enter State:");
        String state = scanner.next();
        for (String key : stateContactList.keySet()) {
            if (key.equalsIgnoreCase(state)) {
                stateContactList.get(state).forEach(person -> System.out.println(person));
            }
        }
    }

    boolean checkDuplicateContact(ContactPerson newPerson) {
        return currentAddressBook.stream().anyMatch((person) -> person.getFirstName().equalsIgnoreCase(newPerson.getFirstName()));
    }

    void searchContact() {
        System.out.println("1.Search by City \n2.Search by State");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                System.out.println("Enter city :");
                searchByCity(scanner.next());
                break;
            case 2:
                System.out.println("Enter State :");
                searchByState(scanner.next());
                break;
            default:
                searchContact();
                break;
        }
    }

    void searchByCity(String city) {
        System.out.println("Search Result: ");
        for (String addressBookName : addressBookList.keySet()) {
            addressBookList.get(addressBookName).forEach((person) -> {
                if (person.getCity().equalsIgnoreCase(city))
                    System.out.println(person);
            });
        }
    }

    void searchByState(String state) {
        System.out.println("Search Result: ");
        for (String addressBookName : addressBookList.keySet()) {
            addressBookList.get(addressBookName).forEach((person) -> {
                if (person.getState().equalsIgnoreCase(state))
                    System.out.println(person);
            });
        }
    }

    void showContactCount() {
        System.out.println("1.Count of City \n2.Count of State");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                System.out.println("Enter city :");
                String city = scanner.next();
                System.out.println("Count: " + cityContactList.get(city).size());
                break;
            case 2:
                System.out.println("Enter State :");
                String state = scanner.next();
                System.out.println("Count: " + stateContactList.get(state).size());
                break;
            default:
                showContactCount();
                break;
        }
    }

    void sortContact() {
        List<ContactPerson> allContacts=getAllContacts();
        List<ContactPerson> sortedContacts;

        System.out.println("1.Sort By Name \n2.Sort By CIty \n3.Sort By State \n4.Sort By Zipcode \n5.back");
        switch (scanner.nextInt()){
            case 1:
                sortedContacts = allContacts.stream().sorted((x,y) -> x.getFirstName().compareTo(y.getFirstName())).collect(Collectors.toList());
                sortedContacts.forEach(x-> System.out.println(x));
                break;
            case 2:
                sortedContacts = allContacts.stream().sorted((x,y) -> x.getCity().compareTo(y.getCity())).collect(Collectors.toList());
                sortedContacts.forEach(x-> System.out.println(x));
                break;
            case 3:
                sortedContacts = allContacts.stream().sorted((x,y) -> x.getState().compareTo(y.getState())).collect(Collectors.toList());
                sortedContacts.forEach(x-> System.out.println(x));
                break;
            case 4:
                sortedContacts = allContacts.stream().sorted((x,y) ->Integer.compare(x.getZipCode(),y.getZipCode())).collect(Collectors.toList());
                sortedContacts.forEach(x-> System.out.println(x));
                break;
            case 5:
                break;
            default:
                sortContact();
                break;
        }
    }

    List<ContactPerson> getAllContacts(){
        List<ContactPerson> allContacts=new ArrayList<>();
        for (String key: addressBookList.keySet()) {
            allContacts.addAll(addressBookList.get(key));
        }
        return allContacts;
    }

    void readAddressBook() throws FileNotFoundException {
        FileIO.read();
    }

    void writeAddressBook() throws IOException {
        for (String key: addressBookList.keySet()) {
            FileIO.write(addressBookList.get(key),key);
        }
    }
}
