public class ArrayDeque<T> {
    private T[] items;
    private int size, nextFirst, nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 0;
        size = 0;
    }

    /*
    public ArrayDeque(T t) {
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 2;
        items[1] = t;
        size = 1;
    }
    */
    private void resize(int capacity) {
        T[] copy = (T[]) new Object[capacity];
        if (nextFirst < nextLast) {
            System.arraycopy(items, nextFirst, copy, 0, size);
        } else {
            System.arraycopy(items, nextFirst, copy, 0, items.length - nextFirst);
            System.arraycopy(items, 0, copy, items.length - nextFirst, nextLast);
        }
        items = copy;
        nextFirst = 0;
        nextLast = size == capacity ? 0 : size;

    }

    private void ensureCapacityMax() {
        if (size == items.length) {
            resize(2 * items.length);
        }
    }

    private void ensureCapacityMin() {
        if (size <= items.length / 4) {
            resize(items.length / 2);
        }
    }

    private int minusOne(int index) {
        if (index - 1 < 0) {
            return index - 1 + items.length;
        } else {
            return index - 1;
        }
    }

    /** Adds item to the front of the list. */
    public void addFirst(T item) {
        ensureCapacityMax();
        items[minusOne(nextFirst)] = item;
        nextFirst = minusOne(nextFirst);
        size++;
    }

    /** Adds item to the end of the list. */
    public void addLast(T item) {
        ensureCapacityMax();
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size++;
    }

    /** Returns true if deque is empty. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Print all the items in the deque from front to end,
     *  seperated by a space.
     */

    public void printDeque() {
        for (int i = 1; i <= size; i++) {
            System.out.print(items[(nextFirst + i) % 8] + " ");
        }
    }

    /** Removes and returns the item at the front of the deque. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        ensureCapacityMin();
        T val = items[nextFirst];
        nextFirst = (nextFirst + 1) % items.length;
        size--;
        return val;
    }

    /** Removes and returns the item at the end of the deque.
     *  If no such item exists, returns null.
     * */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        ensureCapacityMin();
        T val = items[minusOne(nextLast)];
        nextLast = minusOne(nextLast);
        size--;
        return val;
    }

    /** Gets the item at the given index. */
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[(nextFirst + index) % items.length];
    }
}
