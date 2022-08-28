package com.springboot.app;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerController {
	@GetMapping("/hash")
	public String myHash() {
	    MessageDigest messageDigest = null; // Create an object of MessageDigest class
	    String data = "JuanVel";
	    String checkSum = null;
	    try {
	        // Initialize MessageDigest object with SHA-256 
	        messageDigest = MessageDigest.getInstance("SHA-256");
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }
	    // Use the digest() method of the class to generate a hash value of byte type from the unique data string
	    messageDigest.update(data.getBytes()); // pass data to messageDigest
	    byte[] digest = messageDigest.digest(); // compute messageDigest
	    checkSum = this.bytesToHex(digest); // create hash value
	   
	    // return formatted string
	    return "<p>data:Hello "+data+":SHA-256" +checkSum+ "</p>";
	    }

	    // Converts a byte array to a hexadecimal string
	    public String bytesToHex(byte[] bytes) {
	       StringBuilder springBuilder = new StringBuilder();
	       // loop through byte array
	       for (byte hashByte : bytes) {
	           int intVal = 0xff & hashByte;
	           if (intVal < 0x10) {
	               springBuilder.append('0');   // append elements
	           }
	           springBuilder.append(Integer.toHexString(intVal));
	        }
	        return springBuilder.toString();        // return hexadecimal string
	    }
}
