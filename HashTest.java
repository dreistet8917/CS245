package lab08;

/*
 * HasTest is the driver for the Hash assignment 
 */

import java.io.File;
import java.io.IOException;
/**
 * @author Lauren Diol
 *
 */
import java.util.Scanner;

public class HashTest {

	static final int HASHTABLE_INITIAL_SIZE = 1319; // initial size of the
													// hashTable
	static SLList<String>[] hashTable = new SLList[HASHTABLE_INITIAL_SIZE]; // hash
																			// table
	static int maxCols = 0; // max collisions in a chain
	static int colsTotal = 0; // total collisions - counter to keep track of any
								// words that hash to an index that already has
								// a node (so we count if chain has length > 1)

	/**
	 * main method for the Hash
	 * 
	 * @param args
	 *            is a string array; when invoking, element 0 is expect to be
	 *            the dictionary file and element 1 is expected to be the search
	 *            file
	 */
	public static void main(String[] args) {
		// check args; two expected 0) dictionary file 1)search file
		if (args.length != 2) {
			System.out.println("Incorrect arguments supplied. Usage is:HashText <dictionary file> <search file>");
			System.exit(-1);
		}
		// read and load the dictionary file
		SLList<String> slDictionary = readFile(args[0]);
		if (slDictionary == null) {
			System.out.println("Invalid or Empty Dictionary file. No processing will be done.");
			return;
		}
		// read and load the search file
		SLList<String> slSearch = readFile(args[1]);
		if (slSearch == null) {
			System.out.println("Invalid or Empty Search file. No processing will be done.");
			return;
		}

		// insert the dictionary into the hashTable
		HashSlingingSlasher.insert(hashTable, slDictionary);

		// search the hash table for the words
		System.out.println("Search for stuff*******************************"); // display
																				// the
																				// search
																				// banner
		HashSlingingSlasher.search(hashTable, slSearch);
		// remove the hash table for the words
		System.out.println("Removing stuff********************************"); // display
																				// the
																				// search
																				// banner
		HashSlingingSlasher.remove(hashTable, slSearch);

		// search the hash table for the words
		System.out.println("Look Again************************************"); // display
																				// the
																				// search
																				// banner
		HashSlingingSlasher.search(hashTable, slSearch);
		return;
	}

	/**
	 * Reads the file a word at a time and places them in a SLList which is
	 * returned
	 * 
	 * @param sFileName
	 *            File name with path info
	 * 
	 * @return list of words read from the file
	 */
	public static SLList<String> readFile(String sFileName) {
		try {
			SLList<String> sl = new SLList<String>();
			// read in a way that supports whitespaces and carriage returns
			Scanner scnInput = new Scanner(new File(sFileName)); // file scanner
			while (scnInput.hasNext()) { // while we have data
				String sData = scnInput.next(); // read it
				// System.out.println("read:"+sData);
				sl.add(sData); // add to the list in lower case
			}
			scnInput.close(); // close the scanner
			return sl; // return the list
		} catch (IOException e) { // show the exception
			System.out.println("Unable to read file " + sFileName + ". Error: " + e.getLocalizedMessage());
			return null; // and indicate failure
		}
	}

	/**
	 * Hash function for the hash table
	 * 
	 * @param key
	 *            the key that will be used to create the hash
	 * 
	 * @param tableSize
	 *            the size of the hash table (or buckets)
	 * 
	 * @return the hash value (that will be used to identify the bucket/index)
	 *         into the hash table array
	 */
	public static int hashCode(String key, int tableSize) {
		int hashValue = 0;
		for (int i = 0; i < key.length(); i++) {
			hashValue = 37 * hashValue + key.charAt(i);
		}
		hashValue %= tableSize;// take the mod of the tableSize
		if (hashValue < 0) {
			hashValue += tableSize;
		}
		// System.out.println(key +" has a hash value of "+ hashValue);//for
		// debugging
		return hashValue;
	}

	/**
	 * Inserts the dictionary list that in sl into the hash table ht while
	 * making sure the load factor is < 10.0. If the load factor is greater that
	 * 10.0, the hash table is rehashed. Statistics are displayed before
	 * rehashing and on when the hash table is finally loaded.
	 * 
	 * @param ht
	 *            hash table
	 * 
	 * @param sl
	 *            dictionary list that contains the words loaded from the
	 *            dictionary file
	 * 
	 * @return boolean indicating successful completion; false is returned if
	 *         the passed in args/params are null
	 */
}