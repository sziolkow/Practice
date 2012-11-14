package org.sziolkow.hashtable.impl;

import org.sziolkow.hashtable.IEasyHashTable;

public class EasyHashTable implements IEasyHashTable {
    
	private static final int INITIAL_NUMBER_OF_BUCKETS = 2;
	private static final int INITAL_SIZE_OF_HASH_TABLE = 2;
	
	private KeyValue hashArray[][];
	private int numberOfPuts = 0;
	
	public EasyHashTable() {
		hashArray =  new KeyValue[INITAL_SIZE_OF_HASH_TABLE][INITIAL_NUMBER_OF_BUCKETS];
	}
	
	public boolean put(Object key, Object value) {
		 int index = countIndex(key,hashArray.length);
		 KeyValue keyValue =  new KeyValue(key, value);
		 KeyValue[] keyValueArray = hashArray[index];
		 keyValueArray = getBuckets(keyValueArray);
		 hashArray[index] = keyValueArray;
		 for (int i = 0; i < keyValueArray.length; i++) {
			 if (keyValueArray[i] ==null) {
				 keyValueArray[i] = keyValue;
				 if (i==0) numberOfPuts++;
				 break;
			 }	
		 }
		 if (numberOfPuts/hashArray.length>0.75) {
			 expandAndRehash();
		 }
		 return true;
	}
	
	
	private KeyValue[] getBuckets(KeyValue[] keyValueArray) {
		int indexToCheck = keyValueArray.length-1;
		if (indexToCheck<0) indexToCheck = 0;
		
		if (keyValueArray[indexToCheck]!=null) {
			return expandNumberOfBuckets(keyValueArray);
		}
		return keyValueArray;	
	}
	
	private void expandAndRehash() {
		KeyValue newHashArray[][] =  new KeyValue[hashArray.length+INITAL_SIZE_OF_HASH_TABLE][1];
		for (int i = 0; i < hashArray.length; i++) {
			KeyValue[] row = hashArray[i];
			int newIndex=countIndex(row[0].getKey(),newHashArray.length);
			newHashArray[newIndex] = row;	
			hashArray[i] = null;
		}
		
		hashArray=newHashArray;
	}


	private KeyValue[] expandNumberOfBuckets(KeyValue[] keyValueArray) {
		KeyValue[] newKeyValueArray = new KeyValue[keyValueArray.length+INITIAL_NUMBER_OF_BUCKETS];
		for (int i = 0; i < keyValueArray.length; i++) {
			newKeyValueArray[i] = keyValueArray[i];
			keyValueArray[i]=null;
		}
		return newKeyValueArray;
	}

	public Object get(Object key) {
		Object retValue =null;
		if (key!=null) {
			int index = countIndex(key,hashArray.length);
			KeyValue[] keyValueArray = hashArray[index];
			for (int i = 0; i < keyValueArray.length; i++) {
				Object tempKey = keyValueArray[i].getKey();
				if (key.equals(tempKey)) {
					retValue = keyValueArray[i].getValue();
					break;
				}
			}
		}	
		return retValue;
	}
	
	private int countIndex(Object key, int length) {
		int index = key.hashCode() % length;
		if (index < 0)  index=-index;
		return index;
	}

}
