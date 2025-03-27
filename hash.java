import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        // Step 1: Read the JSON file
        File inputFile = new File("input.json");
        FileReader fileReader = new FileReader(inputFile);
        StringBuilder jsonContent = new StringBuilder();
        int ch;
        while ((ch = fileReader.read()) != -1) {
            jsonContent.append((char) ch);
        }
        fileReader.close();

        // Step 2: Parse the JSON content
        JSONObject jsonObject = new JSONObject(jsonContent.toString());
        JSONObject student = jsonObject.getJSONObject("student");

        // Step 3: Extract values of first_name and roll_number
        String firstName = student.getString("first_name").toLowerCase();
        String rollNumber = student.getString("roll_number").toLowerCase();

        // Step 4: Concatenate first_name and roll_number
        String concatenatedString = firstName + rollNumber;

        // Step 5: Generate MD5 Hash
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashBytes = md.digest(concatenatedString.getBytes());
        StringBuilder hashStringBuilder = new StringBuilder();
        for (byte b : hashBytes) {
            hashStringBuilder.append(String.format("%02x", b));
        }
        String md5Hash = hashStringBuilder.toString();

        // Step 6: Write the output to output.txt
        File outputFile = new File("output.txt");
        FileWriter fileWriter = new FileWriter(outputFile);
        fileWriter.write(md5Hash);
        fileWriter.close();

        // Print the result (for testing purposes)
        System.out.println("MD5 Hash: " + md5Hash);
    }
}
 