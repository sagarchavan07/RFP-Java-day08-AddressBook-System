package com.bridgelabz;

public class AddressBookMain {
    public static void main(String[] args) {
        System.out.println("Welcome to Address Book");

        Contact contact=new Contact("Sagar","Chavan","address","Sangli","Maharashtra",415001,123456789,"sagar@exmple.com");
        System.out.println(contact.toString());
    }
}
