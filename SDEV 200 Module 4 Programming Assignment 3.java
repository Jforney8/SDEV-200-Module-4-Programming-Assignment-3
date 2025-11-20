public class MyLinkedList<E> implements MyList<E> {
    private Node<E> head, tail;
    private int size = 0;

    /** Create an empty list */
    public MyLinkedList() {
    }

    /** Create a list from an array of objects */
    public MyLinkedList(E[] objects) {
        for (int i = 0; i < objects.length; i++)
            add(objects[i]);
    }

    /** Return the head element in the list */
    public E getFirst() {
        if (size == 0) {
            return null;
        } else {
            return head.element;
        }
    }

    /** Return the last element in the list */
    public E getLast() {
        if (size == 0) {
            return null;
        } else {
            return tail.element;
        }
    }

    /** Add an element to the beginning of the list */
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e); // Create a new node
        newNode.next = head;             // Link new node with the head

        if (head != null)
            head.previous = newNode;

        head = newNode;                  // head points to the new node
        
        if (tail == null)                // List was empty
            tail = newNode;
        
        size++;
    }

    /** Add an element to the end of the list */
    public void addLast(E e) {
        Node<E> newNode = new Node<>(e);

        if (tail == null) {  // List empty
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.previous = tail;  // NEW
            tail = newNode;
        }

        size++; // Increase size
    }

    /** Add a new element at the specified index */
    public void add(int index, E e) {
        if (index == 0) {
            addFirst(e);
        } else if (index >= size) {
            addLast(e);
        } else {
            Node<E> current = head;

            for (int i = 0; i < index; i++)
                current = current.next;

            Node<E> newNode = new Node<>(e);
            newNode.next = current;
            newNode.previous = current.previous;

            current.previous.next = newNode;
            current.previous = newNode;

            size++;
        }
    }

    /** Remove the head node and return the removed element */
    public E removeFirst() {
        if (size == 0) return null;

        Node<E> temp = head;
        head = head.next;

        if (head != null)
            head.previous = null;
        else
            tail = null; // List is now empty

        size--;

        return temp.element;
    }

    /** Remove the last node and return the removed element */
    public E removeLast() {
        if (size == 0) return null;

        Node<E> temp = tail;
        tail = tail.previous;

        if (tail != null)
            tail.next = null;
        else
            head = null;

        size--;

        return temp.element;
    }

    /** Remove the element at the specified position */
    public E remove(int index) {
        if (index < 0 || index >= size) return null;

        if (index == 0) return removeFirst();
        else if (index == size - 1) return removeLast();
        else {
            Node<E> current = head;

            for (int i = 0; i < index; i++)
                current = current.next;

            current.previous.next = current.next;
            current.next.previous = current.previous;

            size--;

            return current.element;
        }
    }

    /** Override toString() */
    public String toString() {
        StringBuilder result = new StringBuilder("[");

        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            result.append(current.element);
            current = current.next;
            if (current != null)
                result.append(", ");
        }

        result.append("]");
        return result.toString();
    }

    /** Clear the list */
    public void clear() {
        head = tail = null;
        size = 0;
    }

    /** Return true if this list contains the element o */
    public boolean contains(E e) {
        Node<E> current = head;

        while (current != null) {
            if (current.element.equals(e))
                return true;
            current = current.next;
        }

        return false;
    }

    /** Return the element at the specified index */
    public E get(int index) {
        if (index < 0 || index >= size) return null;

        Node<E> current = head;

        for (int i = 0; i < index; i++)
            current = current.next;

        return current.element;
    }

    /** Return the index of the first matching element */
    public int indexOf(E e) {
        Node<E> current = head;
        int index = 0;

        while (current != null) {
            if (current.element.equals(e))
                return index;
            current = current.next;
            index++;
        }

        return -1;
    }

    /** Return the index of the last matching element */
    public int lastIndexOf(E e) {
        Node<E> current = tail;
        int index = size - 1;

        while (current != null) {
            if (current.element.equals(e))
                return index;
            current = current.previous;
            index--;
        }

        return -1;
    }

    /** Replace the element at the specified index with a new element */
    public E set(int index, E e) {
        if (index < 0 || index >= size) return null;

        Node<E> current = head;

        for (int i = 0; i < index; i++)
            current = current.next;

        E old = current.element;
        current.element = e;

        return old;
    }

    /** Return the number of elements in this list */
    public int size() {
        return size;
    }

    /** Node class for a doubly linked list */
    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> previous; // NEW FIELD

        public Node(E element) {
            this.element = element;
        }
    }
}
