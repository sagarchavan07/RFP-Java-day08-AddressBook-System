package com.bridgelabz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AddressBook {
    static HashMap<String,ArrayList> AddressBookList=new HashMap<String, ArrayList>();
    static ArrayList <ContactPerson> currentAddressBook;
    static String currentAddressBookName;
    static Scanner scanner=new Scanner(System.in);

    ContactPerson createContact(){
        System.out.println("Enter first name");
        String firstName=scanner.next();
        System.out.println("Enter last name");
        String lastName=scanner.next();
        System.out.println("Enter address");
        String address=scanner.next();
        System.out.println("Enter city");
        String city=scanner.next();
        System.out.println("Enter state");
        String state=scanner.next();
        System.out.println("Enter ZipCode");
        int zipCode=scanner.nextInt();
        System.out.println("Enter phoneNumber");
        long phoneNumber=scanner.nextLong();
        System.out.println("Enter Email");
        String email=scanner.next();

        ContactPerson person=new ContactPerson(firstName,lastName,address,city,state,zipCode,phoneNumber,email);
        System.out.println("created new contact");
        return person;
    }

    void addContact(ContactPerson person){
        currentAddressBook.add(person);
        System.out.println("contact added to AddressBook "+currentAddressBookName);
        System.out.println(person);
    }
    void editContact(){
        System.out.println("enter name to edit contact");
        String name=scanner.next();
        for (ContactPerson person : currentAddressBook){
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

    void deleteContact(){
        boolean isContactFound=false;
        System.out.println("enter name to delete contact");
        String name=scanner.next();
        for (ContactPerson contact : currentAddressBook){
            if (contact.getFirstName().equalsIgnoreCase(name)) {
                System.out.println("contact found:");
                isContactFound=true;
                System.out.println(contact);
                System.out.println("confirm to delete (y/n)");
                if (scanner.next().equalsIgnoreCase("y")) {
                    currentAddressBook.remove(contact);
                    System.out.println("contact deleted");
                }
                break;
            }
        }
        if (isContactFound == false) {
            System.out.println("Opps... contact not found");
        }
    }
    void addNewAddressBook(){
        System.out.println("Enter name for AddressBook");
        String AddressBookName=scanner.next();
        ArrayList <ContactPerson> AddressBook=new ArrayList();
        AddressBookList.put(AddressBookName,AddressBook);
        System.out.println("new AddressBook created");
        currentAddressBook=AddressBookList.get(AddressBookName);
        currentAddressBookName=AddressBookName;
    }
    void selectAddressBook(){
        System.out.println(AddressBookList.keySet());
        System.out.println("enter name of address book");
        String addressBookName=scanner.next();

        for (String key :AddressBookList.keySet()) {
            if (key.equalsIgnoreCase(addressBookName)){
                currentAddressBook=AddressBookList.get(key);
                currentAddressBookName=key;
            }
        }
        System.out.println("current AddressBook is "+currentAddressBookName);

    }
    void showContacts(ArrayList addressBook){
        System.out.println("Contacts: ");
        for (Object p : addressBook) {
            ContactPerson person= (ContactPerson) p;
            System.out.println(person);
        }
    }
}
