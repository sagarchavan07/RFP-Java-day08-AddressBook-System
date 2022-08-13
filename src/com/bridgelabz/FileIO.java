package com.bridgelabz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {
    static final String FILE_PATH = System.getProperty("user.dir").concat("//AddressBooks//");

    static boolean read() throws FileNotFoundException {
        File filePath=new File(FILE_PATH);
        for (File file:filePath.listFiles()) {
            System.out.println("AddressBook name: "+file.getName());
            Scanner scanner=new Scanner(file);
            while (scanner.hasNextLine()){
                System.out.println(scanner.nextLine());
            }
        }
        return true;
    }

    static boolean write(ArrayList<ContactPerson> addressBook, String addressBookName) throws IOException {
        File file = new File(FILE_PATH + addressBookName + ".txt");
        boolean isCreated = file.createNewFile();
        if (!isCreated) {
            file.delete();
            file.createNewFile();
        }
        System.out.println("file created");
        FileWriter fileWriter = new FileWriter(file);
        String data="";
        for (ContactPerson contactPerson : addressBook) {
            data=data.concat(contactPerson.toString()).concat("\n");
        }
        System.out.println(data);
        fileWriter.write(data);
        fileWriter.close();
        return true;
    }
}
