package com.bridgelabz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JDBCService {
    static Scanner scanner = new Scanner(System.in);

    public static void readAddressBooks() throws SQLException {

        Connection connection = JDBCConnection.connectToDatabase();
        Statement statement = connection.createStatement();

        Statement statement1 = connection.createStatement();
        ResultSet tables = statement1.executeQuery("show tables");
        while (tables.next()) {
            String addressbookName = tables.getString(1);
            System.out.println("AddressBook Name: " + addressbookName);
            ResultSet resultSet = statement.executeQuery("Select * from " + addressbookName);
            while (resultSet.next()) {
                ContactPerson person = new ContactPerson(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getInt(6),
                        resultSet.getLong(7),
                        resultSet.getString(8)
                );
                System.out.println(person);
                if (AddressBook.addressBookList.containsKey(addressbookName)) {
                    AddressBook.addressBookList.get(addressbookName).add(person);
                    System.out.println(addressbookName);
                } else {
                    ArrayList<ContactPerson> list = new ArrayList<>();
                    list.add(person);
                    AddressBook.addressBookList.put(addressbookName, list);
                }
                AddressBook.currentAddressBookName = addressbookName;
                AddressBook.currentAddressBook = AddressBook.addressBookList.get(addressbookName);
            }
        }
        connection.close();
    }

    public static void updateContact(String contactName) throws SQLException {
        Connection connection = JDBCConnection.connectToDatabase();
        PreparedStatement preparedStatement = connection.prepareStatement("update friends " +
                " set firstName=?, lastName=?, address=?, city=?, state=?, zipCode=?, phoneNumber=?, email=?" +
                " where firstName=? ");

        System.out.println("enter firstName:");
        preparedStatement.setString(1, scanner.next());

        System.out.println("enter lastName:");
        preparedStatement.setString(2, scanner.next());

        System.out.println("enter Address:");
        preparedStatement.setString(3, scanner.next());

        System.out.println("enter City:");
        preparedStatement.setString(4, scanner.next());

        System.out.println("enter state:");
        preparedStatement.setString(5, scanner.next());
        System.out.println("enter zipcode:");
        preparedStatement.setString(6, scanner.next());

        System.out.println("enter phoneNumber:");
        preparedStatement.setString(7, scanner.next());

        System.out.println("enter email:");
        preparedStatement.setString(8, scanner.next());

        preparedStatement.setString(9, contactName);

        int rows = preparedStatement.executeUpdate();
        System.out.println(rows + " records updated successfully");
        connection.close();
    }

    public static int getContactCountByCity(String city) throws SQLException {
        Connection connection = JDBCConnection.connectToDatabase();
        int count = 0;

        Statement statement = connection.createStatement();
        ResultSet tables = statement.executeQuery("show tables");
        while (tables.next()) {
            String addressBookName = tables.getString(1);
            PreparedStatement preparedStatement = connection.prepareStatement("select count(*) from " + addressBookName + " where city = ?");
            preparedStatement.setString(1, city);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count += resultSet.getInt(1);
            }
        }
        return count;
    }

    public static int getContactCountByState(String state) throws SQLException {
        Connection connection = JDBCConnection.connectToDatabase();
        int count = 0;

        Statement statement = connection.createStatement();
        ResultSet tables = statement.executeQuery("show tables");
        while (tables.next()) {
            String addressBookName = tables.getString(1);
            PreparedStatement preparedStatement = connection.prepareStatement("select count(*) from " + addressBookName + " where state = ?");
            preparedStatement.setString(1, state);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count += resultSet.getInt(1);
            }
        }
        return count;
    }

    public static int addContact(ContactPerson person, String addressBookName) throws SQLException {
        Connection connection = JDBCConnection.connectToDatabase();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into "+addressBookName+" values(?,?,?,?,?,?,?,?)");
        preparedStatement.setString(1,person.getFirstName());
        preparedStatement.setString(2,person.getLastName());
        preparedStatement.setString(3,person.getAddress());
        preparedStatement.setString(4,person.getCity());
        preparedStatement.setString(5,person.getState());
        preparedStatement.setInt(6,person.getZipCode());
        preparedStatement.setLong(7,person.getPhoneNumber());
        preparedStatement.setString(8,person.getEmail());

        return preparedStatement.executeUpdate();
    }
}
