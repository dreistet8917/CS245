/**
 * 
 * @author emadreist
 *
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/**
 * This class creates an instance of the object
 * sequence, by instantiating a string of the type
 * of sequence, a singly linked list which shows the bases
 * in the sequence, and an integer of the size. 
 *
 */
public class Sequence {
	private String type;
	private SLList<Character> bases;
	private int size;

	/**
	 * This method creates an instance of a sequence
	 * by setting the parameter string to the instance
	 * variable, setting the bases singly linked list 
	 * equal to an empty list, and setting the size to 0.
	 * @param String: sType
	 */
	public Sequence(String sType) {
		type = sType;
		bases = new SLList<Character>();
		size = 0;
	}

	/**
	 * This method returns the singly linked list
	 * @return returns a singly linked list
	 */
	public SLList<Character> getBases() {
		return bases;
	}
	
	/**
	 * This method sets the instance variable bases equal
	 * to the singly linked list passed through as a parameter
	 * 
	 * @param Single Linked List: bases
	 */
	public void setBases(SLList<Character> bases) {
		this.bases = bases;
	}
	
	/**
	 * This method sets the instance variable type equal
	 * to the string passed through as a parameter
	 * @param String: type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * This method returns the string type
	 * @return returns a string of the instance
	 * variable type
	 */
	public String getType() {
		return type;
	}

	/**
	 * This method returns the integer size
	 * @return returns an integer of the 
	 * instance variable size
	 */
	public int length() {
		return size;
	}
}
