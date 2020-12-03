package ua.edu.ucu.iterator;

import ua.edu.ucu.collections.Queue;

import java.util.NoSuchElementException;

public class TrieIterator implements Container {
    private Queue words;

    public TrieIterator(Queue queue) {
        this.words = queue;
    }

    @Override
    public java.util.Iterator<String> getIterator() {
        return new QueueIteratorQ(words);
    }

    private class QueueIteratorQ implements IteratorQ<String> {

        private Queue q;

        private QueueIteratorQ(Queue q) {
            this.q = q;
        }

        @Override
        public boolean hasNext() {
            if (q == null || q.getData().size() == 0) {
                return false;
            }
            return true;
        }

        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (String) q.dequeue();
        }
    }
}
