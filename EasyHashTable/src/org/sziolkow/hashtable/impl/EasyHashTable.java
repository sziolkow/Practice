package org.sziolkow.hashtable.impl;

import org.sziolkow.hashtable.IEasyHashTable;

public class EasyHashTable implements IEasyHashTable {
    
	private static final double FULFILLMENT_FACTOR = 0.75;
	private static final int INITIAL_NUMBER_OF_BUCKETS = 2;
	private static final int INITAL_SIZE_OF_HASH_TABLE = 2;
	
	private KeyValue hashArray[][];
	private int numberOfPuts = 0;
	
	public EasyHashTable() {
		hashArray =  new KeyValue[INITAL_SIZE_OF_HASH_TABLE][INITIAL_NUMBER_OF_BUCKETS];
	}
	
	public boolean put(Object key, Object value) {
		 int index = calculateIndexForHashTable(key,hashArray.length);
		 KeyValue newBucket =  new KeyValue(key, value);
		 KeyValue[] bucketsForIndex = hashArray[index];
		 
		 if (!checkIfEmptyBuckets(bucketsForIndex)) {
			 bucketsForIndex=expandNumberOfBuckets(bucketsForIndex);
			 hashArray[index] = bucketsForIndex;
		 }

		 addNewBucket(newBucket, bucketsForIndex);
		
         if (isHashTableToExpand()) {
        	 hashArray=expandAndRehash();
         }
		 return true;
	}
	
	private boolean isHashTableToExpand() {
		if (numberOfPuts/hashArray.length>FULFILLMENT_FACTOR) return true;
		return false;
	}

	private void addNewBucket(KeyValue newBucket, KeyValue[] bucketList) {
		 for (int i = 0; i < bucketList.length; i++) {
			 if (bucketList[i] ==null) {
				 bucketList[i] = newBucket;
				 if (i==0) numberOfPuts++;
				 break;
			 }	
		 }
		
	}

	private boolean checkIfEmptyBuckets(KeyValue[] buckets) {
		boolean retValue = false;
		int indexToCheck = buckets.length-1;
		if (indexToCheck<0) indexToCheck = 0;
		
		if (buckets[indexToCheck]==null) {
			retValue = true;
		}
		return retValue;
	}

	private int calculateIndexForHashTable(Object key, int length) {
		int index = key.hashCode() % length;
		if (index < 0)  index=-index;
		return index;
	}
	
	
	
	private KeyValue[][] expandAndRehash() {
		KeyValue newHashArray[][] =  new KeyValue[hashArray.length+INITAL_SIZE_OF_HASH_TABLE][1];
		for (int i = 0; i < hashArray.length; i++) {
			KeyValue[] row = hashArray[i];
			int newIndex=calculateIndexForHashTable(row[0].getKey(),newHashArray.length);
			newHashArray[newIndex] = row;	
			hashArray[i] = null;
		}
		
		return newHashArray;
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
			int index = calculateIndexForHashTable(key,hashArray.length);
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
	

}
