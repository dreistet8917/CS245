package lab08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class hashTable {

	String key;
	int value;
	double loadFactor;
	int numItems;
	int collisions;
	SLList<String>[] hashTable;

	public hashTable() {
		key = "";
		value = 0;
		hashTable = new SLList[1319];
	}

	public hashTable(String key, int value) {
		this.key = key;
		this.value = value;
		hashTable = new SLList[1319];
	}

	public int hashCode(String key, int tableSize) {
		int hashValue = 0;
		for (int i = 0; i < key.length(); i++) {
			hashValue = 37 * hashValue + key.charAt(i);
		}
		hashValue = hashValue % tableSize;
		if (hashValue < 0) {
			hashValue = hashValue + tableSize;
		}
		return hashValue;
	}

	public void insert(String key) {
		int hashValue = hashCode(key, hashTable.length);
		// System.out.println("HASHVALUE: " + hashValue);
		if (hashTable[hashValue] == null) {
			hashTable[hashValue] = new SLList(key);
		} else {
			SLList indexList = hashTable[hashValue];
			indexList.add(key);
			collisions++;
		}
		numItems++;
	}

	public String search(String key) {
		int searchCount = 0;
		boolean isEqual = false;
		int hashValue = hashCode(key, hashTable.length);
		if(hashTable[hashValue] != null) {
			for (int i = 0; i < hashTable[hashValue].size; i++) {
				searchCount++;
				String current = hashTable[hashValue].getValue(i);
				if (current.equals(key)) {
					System.out.println("The number of places in the list searched before FINDING " + key + " is "
							+ searchCount);
					isEqual = true;
					return key;
				} else {
					isEqual = false;
				}
			}
			if(isEqual == false) {
			System.out.println("The number of places in the list searched before NOT FINDING " + key + " is "
					+ searchCount);
			}
		}
		return null;
	}

	public boolean contains(String key) {
		return search(key) != null;
	}

	public void remove(String key) {
		//System.out.println("REMOVE");
		if (contains(key)) {
			//System.out.println("CONTAINS");
			int hashValue = hashCode(key, hashTable.length);
			if (hashTable[hashValue].size == 1) {
				//System.out.println("REMOVING AT 0");
				hashTable[hashValue].remove(0);
			} else {
				//System.out.println("REMOVING LAST INDEX");
				SLList indexList = hashTable[hashValue];
				indexList.remove(hashTable[hashValue].size - 1);
			}
			numItems--;
		} else {
			System.out.println(key + "is not in the table so can't remove it");
		}
	}

	public void rehash() {
		SLList<String>[] bigger = new SLList[hashTable.length * 2 + 1];
		for (int i = 0; i < hashTable.length; i++) {
			SLList<String> list = hashTable[i];
			Node<String> node = list.getHead();
			while (node != null) {
				String e = node.getElement();
				int pos = hashCode(e, bigger.length);
				bigger[pos].add(e);
			}
		}
		hashTable = bigger;
	}

	public Double getLoad() {
		loadFactor = (double) (numItems / hashTable.length);
		if (loadFactor >= 10) {
			rehash();
		}
		return loadFactor;
	}

}
