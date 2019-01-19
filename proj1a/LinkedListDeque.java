public class LinkedListDeque<T> {
    private Node sentinel;
    private int size;

    private class Node {
        private T item;
        private Node prev;
        private Node next;

        private Node(T t, Node p, Node n) {
            item = t;
            prev = p;
            next = n;
        }
    }

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /*
    public LinkedListDeque(T t) {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = new Node(t, sentinel, sentinel);
        size = 1;
    }
    */

    /** Adds item to the front of the list. */
    public void addFirst(T item) {
        sentinel.next = new Node(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        if (size == 0) {
            sentinel.prev = sentinel.next;
        }
        size += 1;
    }
    /** Adds item to the end of the list. */
    public void addLast(T item) {
        sentinel.prev.next = new Node(item, sentinel.prev, sentinel);
        sentinel.prev = sentinel.prev.next;
        size += 1;
    }

    /** checks if the list is empty. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** gets the size of the list. */
    public int size() {
        return size;
    }


    /** prints the list. */
    public void printDeque() {
        Node p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
    }


    /**  removes the first item from the list. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        T val = sentinel.next.item;
        Node n = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        n.prev = n.next = null;
        size -= 1;
        return val;
    }

    /** removes the last item from the list. */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T val = sentinel.prev.item;
        Node n = sentinel.prev;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        n.prev = n.next = null;
        size -= 1;
        return val;
    }

    /** gets the ith item from the list using iteration. */
    public T get(int index) {
        Node p = sentinel.next;
        for (int i = 0; i < index; i++) {
            if (p.next == sentinel) {
                return null;
            }
            p = p.next;
        }
        return p.item;
    }

    private T getRecursiveHelper(Node n, int index) {
        if (index == 0) {
            return n.item;
        }
        return getRecursiveHelper(n.next, index - 1);
    }
    /** gets the ith item from the list using recursion. */
    public T getRecursive(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }
}
