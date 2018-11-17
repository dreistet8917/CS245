
/**
 * @author Emma Dreist
 * 
 * Worked with: Lauren Diol
 *  
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * This class reads in a text file that is passed in through the command line,
 * and takes the data from the text file, converts the strings into singly
 * linked lists, and then manipulates the list based on the given instruction,
 * also found in the text file. The class also declares the variable for an
 * array of sequences.
 *
 */

public class Bioinfo {
	private Sequence[] seqArray = null;

	/**
	 * The main method of this class first checks to make sure that the correct
	 * amount of arguments are passed, and if so, it then creates a new instance
	 * of the class and reads in a text file. Based on what is passed through
	 * the text file, it uses if else statements to determine which method to
	 * call to perform the given job.
	 * 
	 * @param Arguments
	 *            passed through the command line: array size, and command file
	 * @throws IOException
	 */

	public static void main(String[] args) throws IOException {
		/**
		 * check if valid arguments
		 */
		if (args.length > 2 || args.length < 2) {
			System.out.println("Invalid amount of aguments passed");
			return;
		}

		Bioinfo bio = new Bioinfo();

		int size = Integer.parseInt(args[0]);
		String inputFile = args[1];
		bio.seqArray = new Sequence[size];

		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		String line;
		while ((line = reader.readLine()) != null) {
			String[] action = line.split("\\s+");
			if (action[0].equals("insert")) {
				bio.insert(action);
			} else if (action[0].equals("remove")) {
				bio.remove(action);
			} else if (action[0].equals("print")) {
				bio.print(action);
			} else if (action[0].equals("splice")) {
				bio.splice(action);
			} else if (action[0].equals("clip")) {
				bio.clip(action);
			} else if (action[0].equals("copy")) {
				bio.copy(action);
			} else if (action[0].equals("swap")) {
				bio.swap(action);
			} else if (action[0].equals("overlap")) {
				bio.overlap(action);
			} else if (action[0].equals("transcribe")) {
				bio.transcribe(action);
			} else if (action[0].equals("translate")) {
				bio.translate(action);
			} else {

			}
		}

	}

	/**
	 * This method takes in a string array which is given from the text file. It
	 * creates a variable of position which is the given action at the second
	 * index of the array, and converts that string into an integer. It then
	 * checks to make sure that the position found is a valid position, and if
	 * it is it creates a new sequence. It then goes to the next string in the
	 * parameter array, and checks the type, either RNA or DNA, which it them
	 * creates a new sequence with that given type. Based on the type, it checks
	 * to make sure the sequence contains the correct characters for the given
	 * type, and then it adds each character to the singly linked list.
	 * 
	 * @param String
	 *            array passed through the text file
	 * 
	 * @return returns a boolean, false if the action cannot be carried out such
	 *         that the base types do not match, there is an invalid position,
	 *         or a sequence does not exist at the given position or else
	 *         returns true.
	 */

	public boolean insert(String[] action) {

		int pos = Integer.parseInt(action[1]);
		if (pos > seqArray.length || pos < 0) {
			System.out.println("Invalid position to insert at position: " + pos);
			return false;
		}
		Sequence sequence = null;

		if (action[2].equalsIgnoreCase("DNA")) {
			sequence = new Sequence("DNA");
			if (action.length < 4) {
				seqArray[pos] = new Sequence("DNA");
				System.out.println("Invalid position to insert at position: " + action[1]);
			} else {

				for (int i = 0; i < action[3].length(); i++) {
					char cBase = action[3].charAt(i);
					if (cBase != 'A' && cBase != 'T' && cBase != 'C' && cBase != 'G') {
						System.out.println("Given bases were not correct for given type to insert at position " + pos);
						return false;
					} else {
						SLList baseList = sequence.getBases();
						baseList.add(cBase);
					}
				}
			}
		} else if (action[2].equalsIgnoreCase("RNA")) {
			sequence = new Sequence("RNA");
			if (action.length < 4) {
				seqArray[pos] = new Sequence("RNA");
			} else {

				for (int i = 0; i < action[3].length(); i++) {
					char cBase = action[3].charAt(i);

					if (cBase != 'A' && cBase != 'U' && cBase != 'C' && cBase != 'G') {
						System.out.println("Given bases were not correct for given type to insert at position: " + pos);
						return false;

					} else {
						SLList baseList = sequence.getBases();
						baseList.add(cBase);
					}
				}
			}
		}

		seqArray[pos] = sequence;
		return true;
	}

	/**
	 * This method clears the singular linked list at the given position, and
	 * the type. This makes the list empty, and sets the type to "", it then
	 * increments the position.
	 * 
	 * @param String
	 *            array read in from the text file
	 * 
	 * @return returns a boolean, false if the action cannot be carried out such
	 *         that an invalid amount of arguments are passed of that there is
	 *         no sequence at the given position or else returns true.
	 */
	public boolean remove(String[] action) {

		int pos = Integer.parseInt(action[1]);
		if (pos >= seqArray.length || pos < 0) {
			System.out.println("Invalid position to insert at position: " + pos);
			return false;
		}

		if (seqArray[pos].getType().isEmpty()) {
			System.out.println("There is no sequence to remove at position: " + pos);
			return false;
		}

		seqArray[pos].getBases().clear();
		seqArray[pos].setType("");
		System.out.println(pos + 1);
		return true;
	}

	/**
	 * This method prints out the given position, type, and DNA/RNA sequence, as
	 * long as there are a valid amount of arguments passed, and there is a
	 * sequence to print out, if there is not sequence it prints only the
	 * position number.
	 * 
	 * @param String
	 *            array given through the text file
	 */
	public void print(String[] action) {
		int pos;
		if (action.length > 1) {
			pos = Integer.parseInt(action[1]);

			if (seqArray[pos] == null) {
				System.out.println("There is no sequence in position: " + pos);
			} else {

				System.out.println(pos + " " + seqArray[pos].getType() + " " + seqArray[pos].getBases().toString());
			}
		} else {

			for (int i = 0; i < seqArray.length; i++) {

				if (seqArray[i] == null) {
					System.out.println(i);
					continue;
				} else {

					System.out.println(i + " " + seqArray[i].getType() + " " + seqArray[i].getBases().toString());
				}
			}
		}
	}

	/**
	 * This method adds a string of characters into the sequence at the given
	 * position. It first checks to make sure the types are the same, then it
	 * checks to make sure the position to be added is valid, and if so it loops
	 * through the linked list, and adds each character one by one until all are
	 * added into the linked list.
	 * 
	 * @param String
	 *            array taken in through the text file
	 * 
	 * @return returns a boolean, false if the action cannot be carried out such
	 *         that the base types do not match, there is an invalid position,
	 *         or a sequence does not exist at the given position or else
	 *         returns true.
	 */
	public boolean splice(String[] action) {

		int pos = Integer.parseInt(action[1]);
		int num = Integer.parseInt(action[4]);

		String bases = action[3].toString();
		if (seqArray[pos].getType().equalsIgnoreCase("DNA")) {
			for (int i = 0; i < bases.length(); i++) {
				char chbase = bases.charAt(i);
				if (chbase != 'A' && chbase != 'T' && chbase != 'C' && chbase != 'G') {
					/**
					 * doesn't insert if the bases are invalid and returns
					 */
					System.out.println("The sequence you want to splice in is not made of valid bases");
					return false;

				} else {
					continue;
				}
			}
		}

		if (seqArray[pos].getType().equalsIgnoreCase("RNA")) {
			for (int i = 0; i < bases.length(); i++) {
				char chbase = bases.charAt(i);
				if (chbase != 'A' && chbase != 'U' && chbase != 'C' && chbase != 'G') {
					/**
					 * doesn't insert if the bases are invalid and returns
					 */
					System.out.println("The sequence you want to splice in is not made of valid bases");
					return false;

				} else {
					continue;
				}
			}
		}
		if (pos < 0 || pos >= seqArray.length - 1) {
			System.out.println("Invalid position to insert at position: " + pos);
			return false;
		}
		if (seqArray[pos].getType().isEmpty()) {
			System.out.println("Invalid position to insert at position: " + pos);
			return false;
		}

		if (action.length != 5) {
			System.out.println("Invalid position to insert at position: " + pos);
			return false;
		}

		if (seqArray[pos].getBases().equals("")) {
			System.out.println("The sequence you want to splice in is not made of valid bases.");
			return false;
		}
		if (!seqArray[pos].getType().equalsIgnoreCase(action[2])) {
			System.out.println("Invalid position to insert at position: " + pos);
			return false;
		}
		if (num > seqArray[pos].getBases().length() - 1) {
			System.out.println("Invalid position to insert at position: " + pos);
			return false;
		}

		if (seqArray[pos].getType().equalsIgnoreCase("DNA") || seqArray[pos].getType().equalsIgnoreCase("RNA")) {
			for (int i = 0; i < action[3].length(); i++) {
				SLList baseList = seqArray[pos].getBases();
				baseList.insert(Integer.parseInt(action[4]) + i, action[3].charAt(i));
			}
		}

		return true;
	}

	/**
	 * This method clips the singly linked list at a given start position to the
	 * end or from a given start position to a given end position. It does this
	 * by looping through the list and adds each character between the start and
	 * end position to the linked list. It first to make sure the position given
	 * is a valid position to clip at, and that the indexes given as the start
	 * and end indexes are valid. It continues to check if the number of
	 * arguments is valid, and if the end index is within the length of the
	 * list. However if the start index is greater than the end index it clears
	 * the whole list.
	 * 
	 * @param String
	 *            array read in through the text file
	 * 
	 * @return returns a boolean, false if the action cannot be carried out such
	 *         that there is an invalid position, a sequence does not exist at
	 *         the given position, the end position is greater than the length,
	 *         of if there are too many arguments passed. It returns true if the
	 *         start and end indexes are valid, and if the start index is
	 *         greater than the end index.
	 */
	public boolean clip(String[] action) {
		int pos = Integer.parseInt(action[1]);
		if (pos >= 0 && pos < seqArray.length) {
			if (seqArray[pos].getType().equals("")) {
				System.out.println("There is no sequence to clip at position: " + pos);
				return false;
			} else {
				int start = Integer.parseInt(action[2]);
				if (start >= 0 && start < seqArray[pos].getBases().length()) {
					if (action.length == 3) {
						// clip start only
						SLList<Character> newBase = new SLList<Character>();
						SLList<Character> oldBase = seqArray[pos].getBases();
						for(int i = start; i <= seqArray[pos].getBases().length() -1; i++) {
							newBase.add(oldBase.getValue(i));
						}
						seqArray[pos].setBases(newBase);
						return true;
					} else {
						int end = Integer.parseInt(action[3]);
						if (end > seqArray[pos].getBases().length()) {
							System.out.println("Stop cannot be greater than list length");
							return false;
						} else {
							if (end < start) {
								seqArray[pos].getBases().clear();
							} else {
								// clip start-end
								SLList<Character> newBase = new SLList<Character>();
								SLList<Character> oldBase = seqArray[pos].getBases();
								for(int i = start; i <= end; i++) { 
									newBase.add(oldBase.getValue(i));
								}
								seqArray[pos].setBases(newBase);
								return true;
							}
						}
					}
				} else {
					System.out.println("Invalid clip indexes passed");
					return false;
				}
			}
		} else {
			System.out.println("Invalid position to insert: " + pos);
			return false;
		}
		return false;
	}
	// int pos = Integer.parseInt(action[1]);
	// int start = Integer.parseInt(action[2]);
	// int end = Integer.parseInt(action[3]);
	// if (start < 0) {
	// System.err.println("Please enter a valid start index");
	// }
	//
	// if(start > seqArray[pos].getBases().length() -1 || end >
	// seqArray[pos].getBases().length() -1) {
	// System.err.println("");
	// }
	//
	// if(end < start) {
	//
	// }
	//
	// if(seqArray[pos] == null || seqArray[pos].getBases().length() == 0) {
	//
	// }
	// SLList<Character> newBase = new SLList<Character>();
	// SLList<Character> oldBase = new SLList<Character>();
	// for(int i = start; i < end; i++) {
	// newBase.add(oldBase.getValue(i));
	// }
	// seqArray[pos].setBases(newBase);
	// return true;

	// if (pos < 0 || pos >= seqArray.length) {
	// System.err.println("This position is invalid: " + pos);
	// return false;
	// }
	// if (start < 0 || start >= seqArray[pos].getBases().length()) {
	// System.err.println("Invalid clip indexes passed");
	// return false;
	// }
	// if (!(action.length == 3 || action.length == 4)) {
	// System.err.println("Invalid number of arguments");
	// return false;
	// }
	// int end = 0;
	// if (action.length == 3) {
	// end = seqArray[pos].getBases().length() - 1;
	// } else {
	// end = Integer.parseInt(action[3]);
	// }
	// if (end > seqArray[pos].getBases().length() || end < 0) {
	// System.err.println("Stop cannot be greater than list length");
	// return false;
	// }
	//
	// for (int i = start; i <= end; i++) {
	// seqArray[pos].getBases().add(seqArray[pos].getBases().getValue(i));
	// }
	// for (int i = 0; i < seqArray[pos].getBases().length(); i++) {
	// seqArray[pos].getBases().remove(0);
	// }
	// if (end < start) {
	// seqArray[pos].getBases().clear();
	// return true;
	// }
	// return true;
	// }

	/**
	 * This method creates a new sequence of the same type, and loops through
	 * the original singly linked list and adds each node from that list into
	 * the new sequence, it then clears the old list and sets the old list equal
	 * to new copied list. If first however, checks to make sure the correct
	 * amount of arguments are passed through and that there is an array to copy
	 * at the given position.
	 * 
	 * @param String
	 *            array taken from the text file
	 * 
	 * @return returns a boolean, false if the action cannot be carried out such
	 *         there is an invalid, amount of arguments passed, or a sequence
	 *         does not exist at the given position, or else returns true.
	 */
	public boolean copy(String[] action) {

		if (action.length != 3) {
			System.out.println("copy: Invalid arguments ");
			return false;
		}
		int pos = Integer.parseInt(action[1]);
		int pos2 = Integer.parseInt(action[2]);

		if (seqArray[pos] == null) {
			System.out.println("There is no sequence to copy at position: " + pos);
			return false;
		}

		Sequence newBaseList = new Sequence(seqArray[pos].getType());
		for (int i = 0; i < seqArray[pos].getBases().length(); i++) {
			newBaseList.getBases().add(seqArray[pos].getBases().getValue(i));
		}

		if (seqArray[pos2] != null) {
			seqArray[pos2].getBases().clear();
		}

		seqArray[pos2] = newBaseList;
		return true;
	}

	/**
	 * This method first creates four variables, one of each position of the two
	 * lists being swapped, and one for each of the start positions to be passed
	 * from both lists. It then checks to make sure there are valid sequences at
	 * both of the positions given, and checks to make sure the two sequences to
	 * be swapped are of the same type. It then checks the start positions of
	 * each list, making sure they are within the list themselves, and if they
	 * are it creates two new empty sequences. It loops through each of the
	 * original lists starting at the given start position, and adds each
	 * character to the empty list, and loops through the original and removes
	 * everything from the original list starting at the given start value. It
	 * does this same process for each list so the beginning of the first list
	 * matches up with the end of the second, and vice verse. It then updates
	 * each list.
	 * 
	 * @param String
	 *            array taken from the text file
	 * 
	 * @return returns a boolean, false if the action cannot be carried out such
	 *         that the base types do not match, there is an invalid position,
	 *         the start values of either list are not valid, or a sequence does
	 *         not exist at the given position, or else returns true.
	 */

	public boolean swap(String[] action) {

		if (action.length != 5) {
			System.out.println("Invalid arguments, cannot swap");
			return false;
		}
		int pos = Integer.parseInt(action[1]);
		int pos2 = Integer.parseInt(action[3]);
		int start = Integer.parseInt(action[2]);
		int start2 = Integer.parseInt(action[4]);

		if (seqArray[pos] == null) {
			System.out.println("There is no sequence to swap at position: " + pos);
			return false;
		}
		if (seqArray[pos2] == null) {
			System.out.println("There is no sequence to swap at position: " + pos2);
			return false;
		}

		if (seqArray[pos].getType().equalsIgnoreCase(seqArray[pos2].getType()) == false) {
			System.out.println("The sequences you want to swap do not have matching bases");
			return false;
		}

		if (start > seqArray[pos].getBases().length() || start < 0) {
			System.out.println("Start position of swap is out of bounds");
			return false;
		}
		if (start2 > seqArray[pos2].getBases().length() || start < 0) {
			System.out.println("Start 2 position of swap is out of bounds");
			return false;

		}

		SLList<Character> base1Tail = new SLList<Character>();
		SLList<Character> base2Tail = new SLList<Character>();

		if (start == seqArray[pos].getBases().length()) {
			for (int i = start2; i < seqArray[pos2].getBases().length(); i++) {
				base2Tail.add(seqArray[pos2].getBases().getValue(i));
			}
			int size = seqArray[pos2].getBases().length();

			for (int i = start2; i <= size; i++) {
				seqArray[pos2].getBases().remove(start2);
			}

			for (int i = 0; i < base2Tail.length(); i++) {
				seqArray[pos].getBases().add(base2Tail.getValue(i));
			}
			return true;
		}
		if (start2 == seqArray[pos2].getBases().length()) {
			for (int i = start; i < seqArray[pos].getBases().length(); i++) {
				base1Tail.add(seqArray[pos].getBases().getValue(i));
			}
			int length = seqArray[pos].getBases().length();

			for (int i = start; i <= length; i++) {
				seqArray[pos].getBases().remove(start);
			}

			seqArray[pos2].getBases().insertList(base1Tail, seqArray[pos2].getBases().length());
			return true;
		}
		for (int i = pos; i < seqArray[pos].getBases().length(); i++) {
			base1Tail.add(seqArray[pos].getBases().getValue(i));
		}
		for (int i = pos2; i < seqArray[pos2].getBases().length(); i++) {
			base1Tail.add(seqArray[pos2].getBases().getValue(i));
		}
		for (int i = pos; i < seqArray[pos].getBases().length(); i++) {
			seqArray[pos].getBases().remove(i);
		}
		for (int i = pos2; i < seqArray[pos2].getBases().length(); i++) {
			seqArray[pos2].getBases().remove(i);
		}
		seqArray[pos].getBases().insertList(base2Tail, seqArray[pos].getBases().length());
		seqArray[pos2].getBases().insertList(base1Tail, seqArray[pos2].getBases().length());

		return true;
	}

	/**
	 * This method returns the overlap between two lists at the given positions.
	 * It creates variables for the start and end of the singular linked list,
	 * and then creates an empty linked list for the the suffix and prefix. It
	 * firsts loops as long as through the length of the shortest of the two
	 * sequences. Then loops through the same sequence, but adds each character
	 * from the second sequence to the empty prefix sequence, and repeats this
	 * loops but adds the characters from the first sequence to the suffix
	 * sequence. It then compares the two lists, and if they are even it assigns
	 * the suffix list to the empty list of the last matched characters, and
	 * sets the index of the last suffix start index to one less than the length
	 * of the original minus the index in the loop 'i'.
	 * 
	 * @param String
	 *            array read in from the text file
	 * 
	 * @return returns a boolean, false if the action cannot be carried out such
	 *         that the base types do not match, there is an invalid amount of
	 *         arguments passed, either sequence is null, or else it returns
	 *         true.
	 */

	public boolean overlap(String[] action) {

		if (action.length != 3) {
			System.out.println("Invalid arguments passed cannot overlap");
			return false;
		}
		int pos = Integer.parseInt(action[1]);
		int pos2 = Integer.parseInt(action[2]);
		int counter = 0;

		if (seqArray[pos].getType().equalsIgnoreCase(seqArray[pos2].getType()) == false) {
			System.out.println("Sequences must be of the same type for overlap method.");
			return false;
		}

		if (seqArray[pos] == null || seqArray[pos2] == null) {
			System.out.println("There is no overlap in these sequences.");
			return false;
		}

		boolean prefixFound[] = new boolean[seqArray[pos2].getBases().length()];

		SLList<Character> prefixSLList = null;

		SLList<Character> suffixSLList = null;

		SLList<Character> lastMatchSSList = null;

		int lastprefixI = 0;

		int lastSuffixStartI = 0;

		for (int i = 0; i < seqArray[pos].getBases().length() && i < seqArray[pos2].getBases().length(); i++) {
			prefixFound[i] = false;

			prefixSLList = new SLList<Character>();
			for (int n = 0; n <= i; n++) {
				prefixSLList.add(seqArray[pos2].getBases().getValue(n));
			}

			suffixSLList = new SLList<Character>();
			for (int n = seqArray[pos].getBases().length() - 1 - i; n < seqArray[pos].getBases().length(); n++) {
				suffixSLList.add(seqArray[pos].getBases().getValue(n));
			}

			prefixFound[i] = compareLists(prefixSLList, suffixSLList);

			if (prefixFound[i] == true) {
				lastMatchSSList = suffixSLList;
				lastSuffixStartI = seqArray[pos].getBases().length() - 1 - i;
			}
		}

		if (lastMatchSSList != null) {
			System.out.println("Overlap starts a position " + lastSuffixStartI + "; bases that overlap "
					+ lastMatchSSList.toString());
		} else {
			System.out.println("There is no overlap in these sequences.");
		}

		return true;
	}

	/**
	 * This method takes in two singly linked lists and loops through the first
	 * and checks each value to see if it matches the second exactly, if they do
	 * math then it returns true, it not it returns false.
	 * 
	 * @param First
	 *            singly linked list to be compared
	 * @param Seocnd
	 *            singly linked list to be compared
	 * 
	 * @return returns a boolean, it returns true if all nodes in the first
	 *         singly linked list matches the nodes in the second list, returns
	 *         false if not.
	 */
	public boolean compareLists(SLList<Character> first, SLList<Character> second) {
		boolean isMatch = false;

		for (int i = 0; i < first.length(); i++) {
			if (first.getValue(i).charValue() == second.getValue(i).charValue()) {
				isMatch = true;
			} else {
				isMatch = false;
				break;
			}
		}
		return isMatch;
	}

	/**
	 * This method takes in a sequence of either RNA or DNA type, and changes
	 * one into the other. It does this by creating a new sequence of type RNA
	 * if, if original is DNA, and loops through the given list and finds each
	 * instance of T then adds U to the new sequence, but if the value is not a
	 * T is just copies the original. It does the same thing again by creating
	 * another new sequence, looping through the one you just created, but this
	 * time it looks for instances of A adding U to the newest sequence,
	 * instances of U adding A to the newest sequence, instances of C adding G
	 * to the newest sequence, and finding G adding C to the newest sequence, if
	 * none of these instances are present it just adds the character from the
	 * first sequence created. After looping, reverses the newest sequences
	 * creates and it clears the original list, then finally updates it to be
	 * equal to the newest sequence.
	 * 
	 * @param String
	 *            array passed in through the text file
	 * 
	 * @return returns a boolean, false if the action cannot be carried out such
	 *         that the base types do not match, or an invalid amount of
	 *         arguments are passed, or else returns true.
	 */

	public boolean transcribe(String[] action) {
		if (action.length != 2) {
			System.out.println("Invalid arguments passed cannot transcribe");
			return false;
		}
		int pos = Integer.parseInt(action[1]);

		if (seqArray[pos] == null) {
			System.out.println("Sequence type must be DNA.");
			return false;
		}
		if (seqArray[pos].getType().equalsIgnoreCase("DNA") == false) {
			System.out.println("Sequence type must be DNA.");
			return false;
		}

		Sequence newSeq = new Sequence("RNA");
		for (int i = 0; i < seqArray[pos].getBases().length(); i++) {
			if ((seqArray[pos].getBases().getValue(i)).charValue() == 'T') {
				newSeq.getBases().add('U');
			} else
				newSeq.getBases().add(seqArray[pos].getBases().getValue(i));
		}

		Sequence newSeq2 = new Sequence("RNA");
		for (int i = 0; i < newSeq.getBases().length(); i++) {
			if (newSeq.getBases().getValue(i).charValue() == 'A') {
				newSeq2.getBases().add('U');
			} else if (newSeq.getBases().getValue(i).charValue() == 'U') {
				newSeq2.getBases().add('A');
			} else if (newSeq.getBases().getValue(i).charValue() == 'C') {
				newSeq2.getBases().add('G');
			} else if (newSeq.getBases().getValue(i).charValue() == 'G') {
				newSeq2.getBases().add('C');
			} else {
				newSeq2.getBases().add(newSeq.getBases().getValue(i));
			}
		}

		newSeq2.getBases().reverse();

		seqArray[pos].getBases().clear();
		seqArray[pos] = newSeq2;

		return true;
	}

	/**
	 * This method translates the given RNA sequence into an amino acid
	 * sequence. It does this by first making sure there are a valid amount of
	 * arguments passed, there is a sequence at the given position, and the type
	 * of the original sequence is RNA. It then finds the start codon and gets
	 * the index of it by looping through original sequence, and if the sequence
	 * contains an A, U, and G then it sets i to the startCondon position if not
	 * start codon is found it returns false. It then tries to find the end
	 * codon, by again looping through the original list, starting 3 indexes
	 * after the start codon incrementing by 3. It tries to find U, A, A or U,
	 * A, G or U, G, A. If any of these sequences are found then the index of
	 * the end codon is set to i. It then creates a new sequence of amino acids,
	 * and uses the helper method to translate RNA into amino acids, it finally
	 * clears the original list, and sets it equal to the new amino acid
	 * sequence.
	 * 
	 * @param String
	 *            array taken from the text file
	 * 
	 * @return returns a boolean, false if the action cannot be carried out such
	 *         that the base type is not RNA, there is an invalid position,
	 *         there is not start or end codon, or if there are an invalid
	 *         amount of arguments passed, or else returns true.
	 */
	public boolean translate(String[] action) {
		if (action.length != 2) {
			System.out.println("Invalid amount of arguments passed cannot translate");
			return false;
		}
		int pos = Integer.parseInt(action[1]);

		if (seqArray[pos] == null) {
			System.out.println("No sequence to translate at position: " + pos);
			return false;
		}

		if (seqArray[pos].getType().equalsIgnoreCase("RNA") == false) {
			System.out.println("Base type is not valid");
			return false;
		}

		boolean isMatchStart = false;
		int listSize = seqArray[pos].getBases().length();
		int startCodonI = 0;
		for (int i = 0; i < listSize; i++) {
			if ((seqArray[pos].getBases().getValue(i).charValue() == 'A')
					&& ((i + 1) < listSize && seqArray[pos].getBases().getValue(i + 1).charValue() == 'U')
					&& ((i + 2) < listSize && seqArray[pos].getBases().getValue(i + 2).charValue() == 'G')) {
				isMatchStart = true;
				startCodonI = i;
				break;
			} else {
				isMatchStart = false;
			}
		}

		if (isMatchStart == false) {
			System.out.println("No start condon found");
			return false;
		}

		boolean isMatchEnd = false;
		int endCodonI = 0;
		for (int i = startCodonI + 3; i < listSize; i += 3) {
			if (((seqArray[pos].getBases().getValue(i).charValue() == 'U')
					&& (seqArray[pos].getBases().getValue(i + 1).charValue() == 'A')
					&& (seqArray[pos].getBases().getValue(i + 2).charValue() == 'A'))
					|| ((seqArray[pos].getBases().getValue(i).charValue() == 'U')
							&& (seqArray[pos].getBases().getValue(i + 1).charValue() == 'A')
							&& (seqArray[pos].getBases().getValue(i + 2).charValue() == 'G'))
					|| ((seqArray[pos].getBases().getValue(i).charValue() == 'U')
							&& (seqArray[pos].getBases().getValue(i + 1).charValue() == 'G')
							&& (seqArray[pos].getBases().getValue(i + 2).charValue() == 'A'))) {
				isMatchEnd = true;
				endCodonI = i;

				break;

			} else {
				isMatchEnd = false;
			}
		}

		if (isMatchEnd == false) {
			System.out.println("There is no open reading frame in this sequence.");
			return false;
		}

		Sequence AASeq = new Sequence("AA");

		boolean isTranslated = translateCodons(seqArray[pos].getBases(), startCodonI, endCodonI, AASeq.getBases());
		if (isTranslated == false) {
			System.out.println("Invalid sequence no transaltion made");
			return false;
		}

		seqArray[pos].getBases().clear();

		seqArray[pos] = AASeq;

		return true;
	}

	/**
	 * This method is a helper method that goes through the given RNA sequence
	 * and loops incrementing by 3, and finds all the RNA codons, and matches
	 * them up with the amino acid abbreviation. It then adds that abbreviation
	 * to the empty AAList given as a parameter.
	 * 
	 * @param rnaList:
	 *            Singly linked list of type RNA
	 * @param start:
	 *            Integer of the start codon index
	 * @param end:
	 *            Integer of the end codon index
	 * @param aaList:
	 * 
	 * @return returns a boolean, false if none of the given codons are found
	 *         meaning the given sequence cannot be translated, or else returns
	 *         true
	 */
	public boolean translateCodons(SLList<Character> rnaList, int start, int end, SLList<Character> AAList) {
		// list of valid base sequences; AA list is updated as matched
		// if an invalid seq is found, returns false
		for (int i = start; i < end; i += 3) {// process 3 bases at a time
			if ((rnaList.getValue(i).charValue() == 'U')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'U')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'U')) {
				AAList.add('F');
			} else if ((rnaList.getValue(i).charValue() == 'U')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'U')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'C')) {
				AAList.add('F');
			} else if ((rnaList.getValue(i).charValue() == 'U')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'U')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'A')) {
				AAList.add('L');
			} else if ((rnaList.getValue(i).charValue() == 'U')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'U')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'G')) {
				AAList.add('L');
			} else if ((rnaList.getValue(i).charValue() == 'C')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'U')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'U')) {
				AAList.add('L');
			} else if ((rnaList.getValue(i).charValue() == 'C')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'U')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'C')) {
				AAList.add('L');
			} else if ((rnaList.getValue(i).charValue() == 'C')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'U')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'A')) {
				AAList.add('L');
			} else if ((rnaList.getValue(i).charValue() == 'C')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'U')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'G')) {
				AAList.add('L');
			} else if ((rnaList.getValue(i).charValue() == 'A')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'U')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'U')) {
				AAList.add('I');
			} else if ((rnaList.getValue(i).charValue() == 'A')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'U')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'C')) {
				AAList.add('I');
			} else if ((rnaList.getValue(i).charValue() == 'A')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'U')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'A')) {
				AAList.add('I');
			} else if ((rnaList.getValue(i).charValue() == 'A')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'U')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'G')) {
				AAList.add('M');
			} else if ((rnaList.getValue(i).charValue() == 'G')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'U')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'U')) {
				AAList.add('V');
			} else if ((rnaList.getValue(i).charValue() == 'G')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'U')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'C')) {
				AAList.add('V');
			} else if ((rnaList.getValue(i).charValue() == 'G')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'U')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'A')) {
				AAList.add('V');
			} else if ((rnaList.getValue(i).charValue() == 'G')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'U')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'G')) {
				AAList.add('V');
			} else if ((rnaList.getValue(i).charValue() == 'U')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'C')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'U')) {
				AAList.add('S');
			} else if ((rnaList.getValue(i).charValue() == 'U')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'C')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'C')) {
				AAList.add('S');
			} else if ((rnaList.getValue(i).charValue() == 'U')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'C')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'A')) {
				AAList.add('S');
			} else if ((rnaList.getValue(i).charValue() == 'U')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'C')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'G')) {
				AAList.add('S');
			} else if ((rnaList.getValue(i).charValue() == 'C')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'C')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'U')) {
				AAList.add('P');
			} else if ((rnaList.getValue(i).charValue() == 'C')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'C')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'C')) {
				AAList.add('P');
			} else if ((rnaList.getValue(i).charValue() == 'C')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'C')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'A')) {
				AAList.add('P');
			} else if ((rnaList.getValue(i).charValue() == 'C')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'C')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'G')) {
				AAList.add('P');
			} else if ((rnaList.getValue(i).charValue() == 'A')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'C')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'U')) {
				AAList.add('T');
			} else if ((rnaList.getValue(i).charValue() == 'A')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'C')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'C')) {
				AAList.add('T');
			} else if ((rnaList.getValue(i).charValue() == 'A')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'C')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'A')) {
				AAList.add('T');
			} else if ((rnaList.getValue(i).charValue() == 'A')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'C')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'G')) {
				AAList.add('T');
			} else if ((rnaList.getValue(i).charValue() == 'G')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'C')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'U')) {
				AAList.add('A');
			} else if ((rnaList.getValue(i).charValue() == 'G')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'C')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'C')) {
				AAList.add('A');
			} else if ((rnaList.getValue(i).charValue() == 'G')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'C')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'A')) {
				AAList.add('A');
			} else if ((rnaList.getValue(i).charValue() == 'G')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'C')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'G')) {
				AAList.add('A');
			} else if ((rnaList.getValue(i).charValue() == 'U')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'A')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'U')) {
				AAList.add('Y');
			} else if ((rnaList.getValue(i).charValue() == 'U')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'A')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'C')) {
				AAList.add('Y');
			} else if ((rnaList.getValue(i).charValue() == 'C')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'A')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'U')) {
				AAList.add('H');
			} else if ((rnaList.getValue(i).charValue() == 'C')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'A')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'C')) {
				AAList.add('H');
			} else if ((rnaList.getValue(i).charValue() == 'C')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'A')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'A')) {
				AAList.add('Q');
			} else if ((rnaList.getValue(i).charValue() == 'C')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'A')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'G')) {
				AAList.add('Q');
			} else if ((rnaList.getValue(i).charValue() == 'A')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'A')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'U')) {
				AAList.add('N');
			} else if ((rnaList.getValue(i).charValue() == 'A')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'A')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'C')) {
				AAList.add('N');
			} else if ((rnaList.getValue(i).charValue() == 'A')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'A')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'A')) {
				AAList.add('K');
			} else if ((rnaList.getValue(i).charValue() == 'A')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'A')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'G')) {
				AAList.add('K');
			} else if ((rnaList.getValue(i).charValue() == 'G')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'A')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'U')) {
				AAList.add('D');
			} else if ((rnaList.getValue(i).charValue() == 'G')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'A')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'C')) {
				AAList.add('D');
			} else if ((rnaList.getValue(i).charValue() == 'G')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'A')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'A')) {
				AAList.add('E');
			} else if ((rnaList.getValue(i).charValue() == 'G')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'A')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'G')) {
				AAList.add('E');
			} else if ((rnaList.getValue(i).charValue() == 'U')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'G')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'U')) {
				AAList.add('C');
			} else if ((rnaList.getValue(i).charValue() == 'U')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'G')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'C')) {
				AAList.add('C');
			} else if ((rnaList.getValue(i).charValue() == 'U')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'G')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'G')) {
				AAList.add('W');
			} else if ((rnaList.getValue(i).charValue() == 'C')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'G')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'U')) {
				AAList.add('R');
			} else if ((rnaList.getValue(i).charValue() == 'C')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'G')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'C')) {
				AAList.add('R');
			} else if ((rnaList.getValue(i).charValue() == 'C')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'G')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'A')) {
				AAList.add('R');
			} else if ((rnaList.getValue(i).charValue() == 'C')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'G')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'G')) {
				AAList.add('R');
			} else if ((rnaList.getValue(i).charValue() == 'A')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'G')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'U')) {
				AAList.add('S');
			} else if ((rnaList.getValue(i).charValue() == 'A')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'G')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'C')) {
				AAList.add('S');
			} else if ((rnaList.getValue(i).charValue() == 'A')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'G')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'A')) {
				AAList.add('R');
			} else if ((rnaList.getValue(i).charValue() == 'A')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'G')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'G')) {
				AAList.add('R');
			} else if ((rnaList.getValue(i).charValue() == 'G')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'G')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'U')) {
				AAList.add('G');
			} else if ((rnaList.getValue(i).charValue() == 'G')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'G')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'C')) {
				AAList.add('G');
			} else if ((rnaList.getValue(i).charValue() == 'G')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'G')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'A')) {
				AAList.add('G');
			} else if ((rnaList.getValue(i).charValue() == 'G')
					&& ((i + 1) < end && rnaList.getValue(i + 1).charValue() == 'G')
					&& ((i + 2) < end && rnaList.getValue(i + 2).charValue() == 'G')) {
				AAList.add('G');
			} else {
				return false;
			}
		}

		return true;
	}

}