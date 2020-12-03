package ua.edu.ucu.iterator;

import ua.edu.ucu.collections.Queue;

public class trieIterator implements Container {
    public Queue words;

    public trieIterator(Queue queue) {
        this.words = queue;
    }

    @Override
    public java.util.Iterator<String> getIterator() {
        return new queueIterator(words);
    }

    private class queueIterator implements Iterator<String> {

        private Queue q;

        private queueIterator(Queue q) {
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
            return (String) q.dequeue();
        }
    }
}
