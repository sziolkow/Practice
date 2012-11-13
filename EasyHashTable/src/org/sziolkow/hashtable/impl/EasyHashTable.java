package org.sziolkow.hashtable.impl;

import org.sziolkow.hashtable.IEasyHashTable;

public class EasyHashTable implements IEasyHashTable {
    
	private static final int INITIAL_NUMBER_OF_BUCKETS = 2;
	private static final int INITAL_SIZE_OF_HASH_TABLE = 100;
	private KeyValue hashArray[][];
	private int numberOfPuts = 0;
	
	public EasyHashTable() {
		hashArray =  new KeyValue[INITAL_SIZE_OF_HASH_TABLE][INITIAL_NUMBER_OF_BUCKETS];
	}
	
	public boolean put(Object key, Object value) {
		 int index = countIndex(key);
		 KeyValue keyValue =  new KeyValue(key, value);
		 KeyValue[] keyValueArray = hashArray[index];
		 expandNumberofBucketsIfNecessary(keyValueArray);
		 for (int i = 0; i < keyValueArray.length; i++) {
			 if (keyValueArray[i] ==null) {
				 keyValueArray[i] = keyValue;
				 numberOfPuts++;
				 break;
			 }	
		 }
		 if (numberOfPuts/hashArray.length>0.75) {
			 expandHashArray();
		 }
		 return true;
	}
	
	private void expandHashArray() {
		//TODO
		/*KeyValue newHashArray[] =  new KeyValue[hashArray.length+INITAL_SIZE_OF_HASH_TABLE];
		for (int i = 0; i < hashArray.length; i++) {
			newHashArray[i] = hashArray[i][1];
			
		}*/
		
	}

	private void expandNumberofBucketsIfNecessary(KeyValue[] keyValueArray) {
		if (keyValueArray[keyValueArray.length-1]!=null) {
			createNewTableAndCopyValues(keyValueArray);
		}
		
	}

	private void createNewTableAndCopyValues(KeyValue[] keyValueArray) {
		KeyValue[] newKeyValueArray = new KeyValue[keyValueArray.length+INITIAL_NUMBER_OF_BUCKETS];
		for (int i = 0; i < keyValueArray.length; i++) {
			newKeyValueArray[i] = keyValueArray[i];
			keyValueArray[i]=null;
		}
		keyValueArray = newKeyValueArray;
	}

	public Object get(Object key) {
		Object retValue =null;
		if (key!=null) {
			int index = countIndex(key);
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
	
	private int countIndex(Object key) {
		int index = key.hashCode() % hashArray.length;
		if (index < 0)  index=-index;
		return index;
	}

}
