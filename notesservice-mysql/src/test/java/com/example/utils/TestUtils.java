package com.example.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

// boiler-plate code
public class TestUtils {
		
	public static File businessTestFile;
	public static File boundaryTestFile;
	public static File exceptionTestFile;
	static {
		removeLineFromFile("../output_revised.txt", "post");
		removeLineFromFile("../output_boundary_revised.txt", "post");
		removeLineFromFile("../output_exception_revised.txt", "post");
		
		businessTestFile = new File("../output_revised.txt");
		//businessTestFile.delete();
		
		boundaryTestFile = new File("../output_boundary_revised.txt");
		///boundaryTestFile.delete();
		
		exceptionTestFile = new File("../output_exception_revised.txt");
		//exceptionTestFile.delete();
	}
	
	
	public static void yakshaAssert(String testName, Object result, File file) throws IOException {
		
		System.out.println("\n" + testName + "=" + result);
		FileWriter writer = new FileWriter(file,true);
		writer.append("\n" + testName + "=" + result);
		writer.flush();
		writer.close();
	}
			
			
	
	public static String currentTest() {
		return Thread.currentThread().getStackTrace()[2].getMethodName();
	}
	
	public static void removeLineFromFile(String file, String lineToRemove) {

	    try {

	      File inFile = new File(file);

	      if (!inFile.isFile()) {
	        System.out.println("Parameter is not an existing file");
	        return;
	      }

	      //Construct the new file that will later be renamed to the original filename.
	      File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

	      BufferedReader br = new BufferedReader(new FileReader(file));
	      PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

	      String line = null;

	      //Read from the original file and write to the new
	      //unless content matches data to be removed.
	      while ((line = br.readLine()) != null) {
	    	
	        if (!line.trim().startsWith(lineToRemove)) {

	          pw.println(line);
	          pw.flush();
	        }
	    	
	      }
	      pw.close();
	      br.close();

	      //Delete the original file
	      if (!inFile.delete()) {
	        System.out.println("Could not delete file");
	        return;
	      }

	      //Rename the new file to the filename the original file had.
	      if (!tempFile.renameTo(inFile))
	        System.out.println("Could not rename file");

	    }
	    catch (FileNotFoundException ex) {
	      ex.printStackTrace();
	    }
	    catch (IOException ex) {
	      ex.printStackTrace();
	    }
	  }



}
