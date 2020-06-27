package com.datastructure.hashmap;

public class EntryLink {
    private Entry entry;
    private EntryLink nextEntryLink;

    EntryLink(Entry entry) {
        this.entry = entry;
    }

    public Entry getEntry() {
        return entry;
    }

    public void setNextEntryLink(EntryLink nextEntryLink) {
        this.nextEntryLink = nextEntryLink;
    }

    public EntryLink getNextEntryLink() {
        return nextEntryLink;
    }
}