package ua.edu.ucu.collections.immutable;


public class ImmutableLinkedList implements ImmutableList {
    private Node head;
    private Node tail;

    public static class Node {
        private Object data;
        private Node next;

        public Node(Object data) {
            this.data = data;
            this.next = null;
        }

        public Object getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }
    }

    public ImmutableLinkedList(Object [] data) {
        if (data == null) {
            this.head = null;
            this.tail = null;
        }
        else {
            this.head = new Node(data[0]);
            Node currNode = this.head;

            for (int i = 1; i < data.length; i++) {
                currNode.next = new Node(data[i]);
                currNode = currNode.next;
            }

            this.tail = currNode;
        }
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    private void isValidIndex(int index) {
        if ((index < 0) || (index >= this.size())) {
            throw new IndexOutOfBoundsException();
        }
    }

    private ImmutableLinkedList copy() {
        if (this.size() == 0) {
            return new ImmutableLinkedList(null);
        }

        Object[] newArray = new Object[this.size()];
        System.arraycopy(this.toArray(), 0, newArray, 0, this.size());
        ImmutableLinkedList newList = new ImmutableLinkedList(newArray);
        return newList;
    }

    @Override
    public ImmutableList add(Object e) {
        ImmutableLinkedList newList = copy();
        if (newList.size() == 0) {
            newList.head = new Node(e);
            newList.tail = newList.head;
        }
        else {
            newList.tail.next = new Node(e);
            newList.tail = newList.tail.next;
        }
        return newList;
    }

    @Override
    public ImmutableList add(int index, Object e) {
        isValidIndex(index);

        ImmutableLinkedList newList = copy();

        if (index == 0) {
            Node temp = newList.head;
            newList.head = new Node(e);
            newList.head.next = temp;
        }

        Node currNode = newList.head;
        int counter = 1;

        while (currNode != newList.tail) {
            if (counter == index) {
                Node temp = currNode.next;
                currNode.next = new Node(e);
                currNode.next.next = temp;
                break;
            }
            counter++;
            currNode = currNode.next;
        }

        if (counter - 1 == index) {
            currNode.next = new Node(e);
            newList.tail = currNode.next;
        }

        return newList;
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        ImmutableLinkedList newList = copy();
        ImmutableLinkedList tempList = new ImmutableLinkedList(c);

        newList.tail.next = tempList.head;
        newList.tail = tempList.tail;
        return newList;
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        isValidIndex(index);

        ImmutableLinkedList newList = copy();
        ImmutableLinkedList tempList = new ImmutableLinkedList(c);

        if (index == 0) {
            Node temp = newList.head;
            newList.head = tempList.head;
            tempList.tail.next = temp;
            return newList;
        }

        Node currNode = newList.head;
        int counter = 1;

        while (currNode != null) {
            if (counter == index) {
                Node temp = currNode.next;
                currNode.next = tempList.head;
                tempList.tail.next = temp;
                return newList;
            }
            counter++;
            currNode = currNode.next;
        }

        return newList;
    }

    @Override
    public Object get(int index) {
        isValidIndex(index);

        int counter = 0;
        Node currNode = this.head;

        while (currNode != null) {
            if (counter == index) {
                return currNode.data;
            }
            counter++;
            currNode = currNode.next;
        }
        return null;
    }

    @Override
    public ImmutableList remove(int index) {
        isValidIndex(index);

        ImmutableLinkedList newList = copy();

        if (index == 0) {
            newList.head = newList.head.next;
            return newList;
        }

        Node currNode = newList.head;
        int counter = 1;

        while (currNode.next != null) {
            if (counter == index) {
                Node temp = currNode.next;
                currNode.next = temp.next;
                return newList;
            }
            counter++;
            currNode = currNode.next;
        }

        if (counter == index) {
            newList.tail = currNode;
        }

        return newList;
    }

    @Override
    public ImmutableList set(int index, Object e) {
        isValidIndex(index);

        ImmutableLinkedList newList = copy();

        if (index == 0) {
            Node temp = newList.head.next;
            newList.head = new Node(e);
            newList.head.next = temp;
        }

        Node currNode = newList.head;
        int counter = 1;

        while (currNode.next != newList.tail) {
            if (counter == index) {
                Node temp = currNode.next.next;
                currNode.next = new Node(e);
                currNode.next.next = temp;
                return newList;
            }
            counter++;
            currNode = currNode.next;
        }

        if (counter == index) {
            currNode.next = new Node(e);
            newList.tail = currNode.next;
        }

        return newList;
    }

    @Override
    public int indexOf(Object e) {
        Node currNode = this.head;
        int counter = 0;

        while (currNode != null) {
            if (currNode.data == e) {
                return counter;
            }
            counter++;
            currNode = currNode.next;
        }

        return -1;
    }

    @Override
    public int size() {
        if (this.isEmpty()) {
            return 0;
        }

        Node currNode = this.head;
        int counter = 0;


        while (currNode != null) {
            counter++;
            currNode = currNode.next;
        }

        return counter;
    }

    @Override
    public ImmutableList clear() {
        ImmutableLinkedList newList = copy();
        newList.head = null;
        newList.tail = null;
        return newList;
    }

    @Override
    public boolean isEmpty() {
        if (this.head == null) {
            return true;
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[this.size()];
        Node currNode = this.head;
        int counter = 0;

        while (counter < this.size()) {
            array[counter] = currNode.data;
            counter++;
            currNode = currNode.next;
        }

        return array;
    }

    @Override
    public String toString() {
        Node currNode = this.head;

        StringBuilder builder = new StringBuilder();
        builder.append("ImmutableLinkedList{");

        for (int i = 0; i < this.size(); i++) {
            builder.append(" " + currNode.data);
            currNode = currNode.next;
        }
        builder.append(" }");

        return builder.toString();
    }

    public ImmutableLinkedList addFirst(Object e) {
        return (ImmutableLinkedList) this.add(0, e);
    }

    public ImmutableLinkedList addLast(Object e) {
        ImmutableLinkedList newList = copy();

        if (newList.size() == 0) {
            newList.head = new Node(e);
            return newList;
        }

        Node currNode = newList.head;

        while (currNode.next != null) {
            currNode = currNode.next;
        }

        currNode.next = new Node(e);
        newList.tail = currNode.next;

        return newList;
    }

    public Object getFirst() {
        return this.get(0);
    }

    public Object getLast() {
        return this.get(this.size() - 1);
    }

    public ImmutableLinkedList removeFirst() {
        return (ImmutableLinkedList) this.remove(0);
    }

    public ImmutableLinkedList removeLast() {
        return (ImmutableLinkedList) this.remove(this.size() - 1);
    }
}
