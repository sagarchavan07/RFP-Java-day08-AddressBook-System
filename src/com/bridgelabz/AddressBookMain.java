package com.bridgelabz;

import java.util.ArrayList;
import java.util.Scanner;

public class AddressBookMain {
    static ArrayList <Contact> AddressBook=new ArrayList();
    static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Welcome to Address Book");

        AddressBookMain addressBook=new AddressBookMain();

        boolean flag=true;
        while (flag){
            System.out.println("**************\nSelect option: \n1.Add Contact \n2.Edit Contact \n3.Delete Contact \n4.Exit");
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
                    flag=false;
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
        AddressBook.add(contact);
        System.out.println("contact added to AddressBook");
    }
    void editContact(){
        System.out.println("enter name to edit contact");
        String name=scanner.next();
        for (Contact contact : AddressBook){
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
        System.out.println("enter name to delete contact");
        String name=scanner.next();
        for (Contact contact : AddressBook){
            if (contact.firstName.equalsIgnoreCase(name)) {
                System.out.println("contact found:");
                System.out.println(contact);
                System.out.println("confirm to delete (y/n)");
                if (scanner.next().equalsIgnoreCase("y")) {
                    AddressBook.remove(contact);
                    System.out.println("contact deleted");
                }
                break;
            }
        }
    }
}
