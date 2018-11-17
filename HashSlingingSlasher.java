package lab08;

public class HashSlingingSlasher {
	public static boolean insert(SLList<String>[] ht, SLList<String> sl) {
		// for each string in sl, get the hashCode or index into the ht; if the
		// index has no SLList / chain, create it; add string to the chain

		if (ht == null || sl == null) { // check to make sure we can do
										// something useful
			return false;
		}
		HashTest.colsTotal = 0; // collisions total - if a chain has more than
								// one word, we increment this counter
		HashTest.maxCols = 0; // max collisions - this is the longest chain
		int rehashCount = 0;// the number of times we had to rehash
		for (int i = 0; sl != null && i < sl.length(); i++) { // we are looping
																// over the list
																// to load
			if ((double) i / (double) ht.length > 10.0) { // is the load factor
															// > 10.0?
				// we need to rehash; display the current stats
				System.out.println("Rehashing with number of items: " + i + " and load factor of "
						+ (double) i / (double) ht.length);
				System.out.println("The maximum number of collisions before the rehashing: " + HashTest.maxCols);
				System.out.println("The number of collisions before rehashing: " + HashTest.colsTotal + "\n");
				// re-initialize the stats for the next go and rehash
				HashTest.colsTotal = 0;
				HashTest.maxCols = 0;
				ht = rehash(ht); // rehash also updates the total and max collisions
				rehashCount++; // and increment the rehash counter
			}
			// get the word and find where to put it incrementing collision
			// counts are needed
			String sData = sl.getValue(i).toLowerCase(); // make sure it's lower
													// cased (as that's what we
													// search for
			int index = HashTest.hashCode(sData, ht.length); // get the index
																// for the
																// hashTable
			if (ht[index] == null) { // do we have a chain in this spot of the
										// hash table?
				ht[index] = new SLList<String>(); // no, so let's do that
				// System.out.println("insert: Creating chain for index: " +
				// index);
			} else {
				HashTest.colsTotal++; // this isn't the first element so it adds
										// to the collision total
				if (HashTest.maxCols < ht[index].length()) {
					HashTest.maxCols = ht[index].length();
				}
			}
			ht[index].add(sData); // now add the word to the SLList at this spot
			// System.out.println("insert: Added " + sData + " to index: " +
			// index);
		}
		// everything is loaded, show stats
		System.out.println("The number of rehashing required with initial table size: "
				+ HashTest.HASHTABLE_INITIAL_SIZE + " is " + rehashCount);
		System.out.println("The load factor of the final table " + (double) sl.length() / (ht.length));
		System.out.println("The number of collisions in the final table is " + HashTest.colsTotal);
		System.out.println("The longest collision chain is " + HashTest.maxCols);

		HashTest.hashTable = ht;
		return true;
	}

	/**
	 * Rehashes the passed in hash table and returns a new one. The new hash
	 * table is double the old hash table size + 1. The old hash table is
	 * traversed and loaded into the new hash table. Counts for max collision
	 * and total collisions as updated
	 * 
	 * @param ht
	 *            hash table that will be used to generate the new hash table
	 * 
	 * @return new hash table
	 */
	public static SLList<String>[] rehash(SLList<String>[] ht) {
		SLList<String>[] htNew = new SLList[2 * ht.length + 1]; // resize the
																// hash table;
																// we're going
																// to double it
																// + 1
		for (int i = 0; i < ht.length; i++) { // loop through the current hash
												// table
			if (ht[i] != null) { // if this position has a list
				for (int j = 0; j < ht[i].length(); j++) { // loop over the list
					String sData = ht[i].getValue(j); // get the word saved
					int index = HashTest.hashCode(sData, htNew.length); // and
																		// find
																		// the
																		// index
																		// in
																		// the
																		// new
																		// list
					if (htNew[index] == null) { // do we have a chain in this
												// spot of the hash table?
						htNew[index] = new SLList<String>(); // no, so let's do
																// that
						// System.out.println("insert: Creating chain for index:
						// " + index);
					} else {
						HashTest.colsTotal++; // increment the total collision counter
						if (HashTest.maxCols < htNew[index].length()) {
							HashTest.maxCols = htNew[index].length();
						}
					}
					htNew[index].add(sData); // and add the word into the new
												// list
				}
			}
		}
		return htNew; // return the new list
	}

	/**
	 * search the hash table for the list of words read from the search file
	 * 
	 * @param ht
	 *            hash table
	 * 
	 * @param sl
	 *            search list that contains the words from the search file
	 * 
	 * @return boolean indicating successful completion; false is returned if
	 *         the passed in args/params are null
	 */
	public static boolean search(SLList<String>[] ht, SLList<String> sl) {
		for (int i = 0; sl != null && i < sl.length(); i++) { // we are looping
																// over the
																// search list
																// to search
																// words in the
																// hash table
			String sData = sl.getValue(i).toLowerCase(); // lower case the search
													// words
			int index = HashTest.hashCode(sData, ht.length); // get the hash
																// code
			boolean found = false;
			int foundIndex = 0;
			if (ht[index] != null) { // if there is a list
				for (int j = 0; j < ht[index].length(); j++) { // look in the
																// list for
					if (sData.equalsIgnoreCase(ht[index].getValue(j))) { // a match
						found = true; // if found, record location
						foundIndex = j;
						break;
					}
				}
			}
			if (found) { // if found
				System.out.println("The number of places in the list searched before FINDING the word " + sData + " is "
						+ foundIndex + "\n");
			} else { // if not found, handle case when the list may be empty
				if (ht[index] != null) {
					System.out.println("The number of places in the list searched before NOT FINDING the word " + sData
							+ " is " + ht[index].length() + "\n");
				} else {
					System.out.println("The number of places in the list searched before NOT FINDING the word " + sData
							+ " is " + 0 + "\n");
				}
			}
		}

		return true;
	}

	/**
	 * search the hash table for the list of words read from the search file and
	 * remove them
	 * 
	 * @param ht
	 *            hash table
	 * 
	 * @param sl
	 *            search list that contains the words from the search file
	 * 
	 * @return boolean indicating successful completion; false is returned if
	 *         the passed in args/params are null
	 */
	public static boolean remove(SLList<String>[] ht, SLList<String> sl) {
		for (int i = 0; sl != null && i < sl.length(); i++) { // we are looping
																// over the
																// search list
																// to search
																// words in the
																// hash table
			String sData = sl.getValue(i).toLowerCase(); // lower case the search
													// words
			int index = HashTest.hashCode(sData, ht.length); // get the hash
																// code
			boolean found = false;
			int foundIndex = 0;
			if (ht[index] != null) { // if a list exists, see if we can find a
										// match in the list
				for (int j = 0; j < ht[index].length(); j++) { // loop over the
																// list
					if (sData.equalsIgnoreCase(ht[index].getValue(j))) { // find the
																	// exact
																	// match
						found = true; // record the location
						foundIndex = j;
						break;
					}
				}
			}
			if (found) { // if we found it, remove it
				ht[index].remove(foundIndex);
				if (foundIndex == 0) { // if we removed the only node, indicate
										// it's empty
					ht[index] = null;
				}
			} else { // not found banner
				System.out.println(sData + " is not in the table so can't remove it" + "\n");
			}
		}

		HashTest.hashTable = ht;
		return true;
	}

}