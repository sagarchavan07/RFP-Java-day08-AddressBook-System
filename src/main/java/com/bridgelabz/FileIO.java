package com.bridgelabz;

import com.google.gson.Gson;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileIO {
    static final String FILE_PATH = System.getProperty("user.dir").concat("//AddressBooks//");

    static boolean read(File filePath) throws FileNotFoundException {
        for (File file : filePath.listFiles()) {
            System.out.println("AddressBook name: " + file.getName());
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        }
        return true;
    }

    static boolean writeTxtFile(ArrayList<ContactPerson> addressBook, String addressBookName) throws IOException {
        File file = new File(FILE_PATH + "txt//" + addressBookName + ".txt");
        boolean isCreated = file.createNewFile();
        if (!isCreated) {
            file.delete();
            file.createNewFile();
        }
        System.out.println("file created");
        FileWriter fileWriter = new FileWriter(file);
        String data = "";
        for (ContactPerson contactPerson : addressBook) {
            data = data.concat(contactPerson.toString()).concat("\n");
        }
        System.out.println(data);
        fileWriter.write(data);
        fileWriter.close();
        return true;
    }

    public static boolean writeCsvFile(ArrayList<ContactPerson> addressBook, String addressBookName) throws IOException {
        File file = new File(FILE_PATH + "csv//" + addressBookName + ".csv");
        boolean isCreated = file.createNewFile();
        if (!isCreated) {
            file.delete();
            file.createNewFile();
        }
        System.out.println("file created");
        FileWriter fileWriter = new FileWriter(file);
        CSVWriter csvWriter = new CSVWriter(fileWriter);
        List<String[]> data = new ArrayList<>();
        for (ContactPerson person : addressBook) {
            String[] contactData = new String[]{person.getFirstName(), person.getLastName(), person.getAddress(), person.getCity(), person.getState(), String.valueOf(person.getZipCode()), String.valueOf(person.getPhoneNumber()), person.getEmail()};
            data.add(contactData);
        }
        csvWriter.writeAll(data);
        fileWriter.close();
        return true;
    }

    public static void writeJsonFile(ArrayList<ContactPerson> addressBook, String addressBookName) throws IOException {
        File file = new File(FILE_PATH.concat("json//"+addressBookName+".json"));
        boolean isCreated = file.createNewFile();
        if (!isCreated) {
            file.delete();
            file.createNewFile();
        }
        FileWriter writer =new FileWriter(file);
        Gson gson=new Gson();
        String data="";
        for (ContactPerson person : addressBook) {
            data=data.concat(gson.toJson(person)+"\n");
        }
        writer.write(data);
        writer.close();
    }
}
