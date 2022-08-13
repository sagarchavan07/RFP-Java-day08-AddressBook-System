package com.bridgelabz;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AddressBookMain {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Address Book");

        AddressBook addressBook = new AddressBook();
        Scanner scanner = new Scanner(System.in);

        addressBook.addNewAddressBook();

        boolean flag1 = true;
        while (flag1) {
            System.out.println("*************\n" + AddressBook.addressBookList.keySet());
            System.out.println("current AddressBook Name: " + AddressBook.currentAddressBookName);
            System.out.println("Select option: \n" +
                    "1.Add Contact \n" +
                    "2.Edit Contact\n" +
                    "3.view Contact \n" +
                    "4.Delete Contact \n" +
                    "5.Add New AddressBook \n" +
                    "6.Select AddressBook \n" +
                    "7.Search Contact \n" +
                    "8.Show number of Contact \n" +
                    "9.Sort Contacts \n" +
                    "10.Write AddressBooks \n" +
                    "11.Read AddressBooks \n" +
                    "12.Exit");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    ContactPerson contact = addressBook.createContact();
                    addressBook.addContact(contact);
                    break;
                case 2:
                    addressBook.editContact();
                    break;
                case 3:
                    addressBook.viewContacts();
                    break;
                case 4:
                    addressBook.deleteContact();
                    break;
                case 5:
                    addressBook.addNewAddressBook();
                    break;
                case 6:
                    addressBook.selectAddressBook();
                    break;
                case 7:
                    addressBook.searchContact();
                    break;
                case 8:
                    addressBook.showContactCount();
                    break;
                case 9:
                    addressBook.sortContact();
                    break;
                case 10:
                    addressBook.writeAddressBook();
                    break;
                case 11:
                    addressBook.readAddressBook();
                    break;
                case 12:
                    flag1 = false;
                    break;
                default:
                    System.out.println(option + " is not valid option");
                    break;
            }
        }
    }
}
