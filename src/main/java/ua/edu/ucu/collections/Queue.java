package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;
import ua.edu.ucu.iterator.TrieIterator;

import java.util.Iterator;

public class Queue implements Iterable<String> {
    private ImmutableLinkedList data;

    public Queue() {
        this.data = new ImmutableLinkedList(null);
    }

    public ImmutableLinkedList getData() {
        return data;
    }

    public Object peek() {
        if (data.size() == 0) {
            return null;
        }
        return this.data.getFirst();
    }

    public Object dequeue() {
        Object temp = this.peek();
        this.data = this.data.removeFirst();
        return temp;
    }

    public void enqueue(Object e) {
        this.data = this.data.addLast(e);
    }

    @Override
    public Iterator<String> iterator() {
        TrieIterator iterator = new TrieIterator(this);
        return iterator.getIterator();
    }
}
