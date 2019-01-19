public class ArrayDeque<T> {
    private T[] items;
    private int size, nextFirst, nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
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
    public void addFirst(T item) {
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1) < 0 ? nextFirst + 7 : nextFirst - 1;
        size++;
    }

    public void addLast(T item) {
        items[nextLast] = item;
        nextLast = (nextLast + 1) > 7 ? nextLast - 7 : nextLast + 1;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for(int i = 1; i <= size; i++) {
            System.out.print(items[(nextFirst + i) % 8] + " ");
        }
    }

    public T removeFirst() {
        if (size == 0) return null;
        T val = items[(nextFirst + 1) % 8];
        nextFirst = (nextFirst + 1) % 8;
        size--;
        return val;
    }

    public T removeLast() {
        if (size == 0) return null;
        T val = items[(nextLast - 1) < 0? nextLast + 7 : nextLast - 1];
        nextLast = (nextLast - 1) < 0? nextLast + 7 : nextLast - 1;
        size--;
        return val;
    }

    public T get(int index) {
        if(index < 0 || index > 7) return null;
        return items[(nextFirst + 1 + index) % 8];
    }
}
