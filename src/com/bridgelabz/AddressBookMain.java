package com.bridgelabz;

import java.util.Scanner;

public class AddressBookMain {
    Contact contact;

    public static void main(String[] args) {
        System.out.println("Welcome to Address Book");

        AddressBookMain addressBook=new AddressBookMain();
        Contact contact1=addressBook.createContact();

        System.out.println(contact1);
    }

    Contact createContact(){
        Scanner scanner=new Scanner(System.in);
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

        System.out.println("created new contact\n");
        Contact contact=new Contact(firstName,lastName,address,city,state,zipCode,phoneNumber,email);
        return contact;
    }
}
