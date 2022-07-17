package com.bridgelabz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AddressBookMain {
    static HashMap<String,ArrayList> AddressBookList=new HashMap<String, ArrayList>();
    static ArrayList <Contact> currentAddressBook;
    static String currentAddressBookName;
    static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Welcome to Address Book");

        AddressBookMain addressBook=new AddressBookMain();

        addressBook.addNewAddressBook();

        boolean flag1=true;
        while (flag1){
            System.out.println("*************\ncurrent AddressBook Name: "+currentAddressBookName);
            System.out.println("Select option: \n1.Add Contact \n2.Edit Contact \n3.Delete Contact \n4.Add New AddressBook\n5.Select AddressBook \n6.Exit");
            int option=scanner.nextInt();
            switch (option){
                case 1:
                    Contact contact=addressBook.createContact();
                    addressBook.addContact(contact);
                    break;
                case 2:
                    addressBook.editContact();
                    break;
                case 3:
                    addressBook.deleteContact();
                    break;
                case 4:
                    addressBook.addNewAddressBook();
                    break;
                case 5:
                    addressBook.selectAddressBook();
                    break;
                case 6:
                    flag1=false;
                    break;
                default:
                    System.out.println(option+" is not valid option");
                    break;
            }
        }
    }

    Contact createContact(){
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

        Contact contact=new Contact(firstName,lastName,address,city,state,zipCode,phoneNumber,email);
        System.out.println("created new contact");
        return contact;
    }

    void addContact(Contact contact){
        currentAddressBook.add(contact);
        System.out.println("contact added to AddressBook");
        System.out.println(contact);
    }
    void editContact(){
        System.out.println("enter name to edit contact");
        String name=scanner.next();
        for (Contact contact : currentAddressBook){
            if (contact.firstName.equalsIgnoreCase(name)) {
                System.out.println("Enter first name");
                contact.firstName=scanner.next();
                System.out.println("Enter last name");
                contact.lastName=scanner.next();
                System.out.println("Enter address");
                contact.address=scanner.next();
                System.out.println("Enter city");
                contact.city=scanner.next();
                System.out.println("Enter state");
                contact.state=scanner.next();
                System.out.println("Enter ZipCode");
                contact.zipCode=scanner.nextInt();
                System.out.println("Enter phoneNumber");
                contact.phoneNumber=scanner.nextLong();
                System.out.println("Enter Email");
                contact.email=scanner.next();
                System.out.println("contact updated successfully.");
                System.out.println(contact);
                break;
            }
        }
    }

    void deleteContact(){
        boolean isContactFound=false;
        System.out.println("enter name to delete contact");
        String name=scanner.next();
        for (Contact contact : currentAddressBook){
            if (contact.firstName.equalsIgnoreCase(name)) {
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
        ArrayList <Contact> AddressBook=new ArrayList();
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
                System.out.println("current AddressBook is "+currentAddressBookName);
            }else {
                System.out.println("AddressBook not found");
            }
        }

    }
}
