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
        return new QueueIterator(words);
    }

    private class QueueIterator implements Iterator<String> {

        private Queue q;

        private QueueIterator(Queue q) {
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
