package com.datastructure.hashmap;

import com.datastructure.Data;

public class CustomHashMap {

	private EntryLink[] entryLinks;
	int size = 5;
	
	public CustomHashMap(){
		entryLinks = new EntryLink[size];
	}
	
	public void put(Object key, Object value){
		int position = Math.abs(key.hashCode()%size);
		Entry entry = new Entry(key, value);
		
		if (entryLinks[position]!=null) {
			EntryLink entryLink = entryLinks[position];
			while (entryLink != null && entryLink.getNextEntryLink()!=null){
				 entryLink = entryLink.getNextEntryLink();
			}

			entryLink.setNextEntryLink(new EntryLink(entry));
		} else {
			entryLinks[position] = new EntryLink(entry);
		}

		System.out.println(entry.getKey() + " added.");
	}
	
	public Object get(Object key){
		EntryLink entryLink = entryLinks[Math.abs(key.hashCode()%size)];
		while (entryLink != null && !entryLink.getEntry().getKey().equals(key)){
			entryLink = entryLink.getNextEntryLink();
		}

		return (entryLink == null) ? null : entryLink.getEntry().getValue();
	}

	public void remove(Object key){
		EntryLink entryLink = entryLinks[Math.abs(key.hashCode()%size)];

		EntryLink parentEntryLink = null;
		while (entryLink != null) {
			if (entryLink.getEntry().getKey().equals(key)) {
				break;
			}

			parentEntryLink = entryLink;
			entryLink = entryLink.getNextEntryLink();
		}

		if (entryLink == null) {
			System.out.println("No entry available to remove from the map.");
			return;
		}

		if (parentEntryLink == null) {
			System.out.println("Removed the only entry available: " + entryLink.getEntry().getKey());
			entryLinks[Math.abs(key.hashCode()%size)] = null;
		} else {
			System.out.println("Removed the in between entry available" + entryLink.getEntry().getKey());
			EntryLink childEntryLink = entryLink.getNextEntryLink();
			parentEntryLink.setNextEntryLink(childEntryLink);
			entryLink.setNextEntryLink(null);
		}
	}

	public static void main(String args []) {

		CustomHashMap customHashMap = new CustomHashMap();

		Data data1 = new Data(1, 1.1f, "string1");
		Data data2 = new Data(1, 1.2f, "string1");
		Data data3 = new Data(1, 1.3f, "string1");

		Data data4 = new Data(4, 1.4f, "string-4");

		customHashMap.put(data1, data1);
		customHashMap.put(data2, data2);
		customHashMap.put(data3, data3);
		customHashMap.put(data4, data4);

		customHashMap.remove(data2);
		customHashMap.remove(data4);
		assert(customHashMap.get(data1) != null);
		assert(customHashMap.get(data2) == null);
		assert(customHashMap.get(data3) == null);
	}
}

