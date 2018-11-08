package seedu.address.model.filereader;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Represents a FileReader.
 * Guarantees: file path is present and not null, validated, immutable.
 */
public class FileReader {
    public static final String CSV_HEADER_NAME = "Given Name";
    public static final String CSV_HEADER_PHONE = "Phone 1 - Value";
    public static final String CSV_HEADER_ADDRESS = "Address 1 - Street";
    public static final String CSV_HEADER_EMAIL = "E-mail 1 - Value";
    public static final String CSV_HEADER_FACULTY = "Organization 1 - Name";

    private final File csvFile;
    private final FilePath csvFilePath;
    private boolean isValidFile = false;

    private int nameIndex = -1;
    private int phoneIndex = -1;
    private int addressIndex = -1;
    private int emailIndex = -1;
    private int facultyIndex = -1;
    private ArrayList<String> contacts = new ArrayList<>();
    private int failCounter = 0;

    public FileReader(FilePath csvFilePath) {
        requireAllNonNull(csvFilePath);
        this.csvFilePath = csvFilePath;
        this.csvFile = new File(csvFilePath.toString());
        readImportContactsFile();
    }

    public FilePath getCsvFilePath() {
        return csvFilePath;
    }

    public boolean isValidFile() {
        return isValidFile;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public int getPhoneIndex() {
        return phoneIndex;
    }

    public int getAddressIndex() {
        return addressIndex;
    }

    public int getEmailIndex() {
        return emailIndex;
    }

    public int getFacultyIndex() {
        return facultyIndex;
    }

    public ArrayList<String> getContacts() {
        return contacts;
    }

    public int getNumberOfContacts() {
        return contacts.size();
    }

    public int getFailCounter() {
        return failCounter;
    }

    public void incrementFailCounter() {
        this.failCounter++;
    }

    /**
     * Reads contact information from given csv file.
     */
    public void readImportContactsFile() {
        try {
            Scanner sc = new Scanner(csvFile);
            if (!sc.hasNextLine()) {
                sc.close();
                return;
            }
            String header = sc.nextLine();
            String[] parts = header.split(",");
            isValidFile = setIndex(parts);
            if (isValidFile) {
                while (sc.hasNextLine()) {
                    String nextLine = sc.nextLine();
                    nextLine += " " + sc.nextLine();
                    contacts.add(nextLine);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            // will never happen, toImport is validated by parser
        }
    }

    private boolean setIndex(String[] parts) {
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].equals(CSV_HEADER_NAME)) {
                nameIndex = i;
            }
            if (parts[i].equals(CSV_HEADER_PHONE)) {
                phoneIndex = i;
            }

            if (parts[i].equals(CSV_HEADER_ADDRESS)) {
                addressIndex = i;
            }

            if (parts[i].equals(CSV_HEADER_EMAIL)) {
                emailIndex = i;
            }

            if (parts[i].equals(CSV_HEADER_FACULTY)) {
                facultyIndex = i;
            }
        }
        // return true if nameIndex and phoneIndex is valid
        return nameIndex != -1 && phoneIndex != -1 && addressIndex != -1 && emailIndex != -1 && facultyIndex != -1;
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof FileReader)) {
            return false;
        }

        FileReader otherFileReader = (FileReader) other;
        return otherFileReader.getCsvFilePath().equals(getCsvFilePath())
                && otherFileReader.getNameIndex() == getNameIndex()
                && otherFileReader.getPhoneIndex() == getPhoneIndex()
                && otherFileReader.getEmailIndex() == getEmailIndex()
                && otherFileReader.getAddressIndex() == getAddressIndex()
                && otherFileReader.getFacultyIndex() == getFacultyIndex();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(csvFilePath, nameIndex, phoneIndex, emailIndex, addressIndex, facultyIndex);
    }

    @Override
    public String toString() {
        return csvFilePath.toString();
    }

}
