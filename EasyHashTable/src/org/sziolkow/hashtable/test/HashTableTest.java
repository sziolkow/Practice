package org.sziolkow.hashtable.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.sziolkow.hashtable.IEasyHashTable;
import org.sziolkow.hashtable.impl.EasyHashTable;

public class HashTableTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void initHashTable() {
		IEasyHashTable easyHashTable = new EasyHashTable();
		assertNotNull(easyHashTable);
	}

	@Test
	public void putNewObject() {
		IEasyHashTable easyHashTable = new EasyHashTable();
		assertTrue(easyHashTable.put(new Integer(1), new Integer(1)));
	}

	
	@Test
	public void putFiveNewObject() {
		IEasyHashTable easyHashTable = new EasyHashTable();
		assertTrue(easyHashTable.put(new Integer(1), new Integer(1)));
		assertTrue(easyHashTable.put(new Integer(2), new Integer(2)));
		assertTrue(easyHashTable.put(new Integer(3), new Integer(3)));
		assertTrue(easyHashTable.put(new Integer(4), new Integer(4)));
		assertTrue(easyHashTable.put(new Integer(5), new Integer(5)));
	}
	
	@Test
	public void putObjectJustInserted() {
		IEasyHashTable easyHashTable = new EasyHashTable();
		easyHashTable.put(easyHashTable, new Integer(1));
		assertTrue(easyHashTable.put(new Integer(1), new Integer(1)));
	}
	
	@Test
	public void getValueForJustPut() {
		IEasyHashTable easyHashTable = new EasyHashTable();
		Integer testKey =  new Integer(5);
		Integer testValue =  new Integer(5);
		easyHashTable.put(testKey, testValue);
		assertNotNull(easyHashTable.get(testKey));
	}
	
	@Test
	public void getValueforNull() {
		IEasyHashTable easyHashTable = new EasyHashTable();
		assertNull(easyHashTable.get(null));
	}
	
	@Test
	public void differentClassesSameHashCode(){
		IEasyHashTable easyHashTable = new EasyHashTable();
		TestClass tempKey = new TestClass();
		easyHashTable.put(new Integer(1), new Integer(1));
		easyHashTable.put(tempKey, new TestClass());
		assertNotNull((TestClass) easyHashTable.get(tempKey));
	}
}
