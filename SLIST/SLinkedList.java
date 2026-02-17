package SLIST;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SLinkedList<T> implements Iterable<T> {

    class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            next = null;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public SLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (current == null) {
                    throw new NoSuchElementException();
                }
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
        if (tail == null) { // list was empty
            tail = newNode;
        }
        size++;
    }

    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T deleteFirst() {
        if (head == null) throw new NoSuchElementException("List is empty");
        T data = head.data;
        head = head.next;
        size--;
        if (head == null) tail = null;
        return data;
    }

    public T deleteLast() {
        if (head == null) throw new NoSuchElementException("List is empty");
        if (head.next == null) { // one element
            T data = head.data;
            head = tail = null;
            size--;
            return data;
        }
        Node<T> current = head;
        while (current.next != tail) {
            current = current.next;
        }
        T data = tail.data;
        current.next = null;
        tail = current;
        size--;
        return data;
    }

    public void display() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public boolean contains(T data) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(data)) return true;
            current = current.next;
        }
        return false;
    }

    public void clear() {
        head = tail = null;
        size = 0;
    }

    public T getFirst() {
        if (head == null) throw new NoSuchElementException("List is empty");
        return head.data;
    }

    public T getLast() {
        if (tail == null) throw new NoSuchElementException("List is empty");
        return tail.data;
    }

    // Reverse the linked list
    public void reverse() {
        Node<T> prev = null;
        Node<T> current = head;
        tail = head; // old head becomes tail
        while (current != null) {
            Node<T> nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }
        head = prev;
    }

    // Delete consecutive duplicate elements
    public void deleteConsecutiveDuplicates() {
        if (head == null) return;
        Node<T> current = head;
        while (current.next != null) {
            if (current.data.equals(current.next.data)) {
                current.next = current.next.next;
                size--;
            } else {
                current = current.next;
            }
        }
        tail = current; // update tail
    }

    // Two lists are equal if they have same size and elements in order
       @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof SLinkedList<?>)) return false;

        SLinkedList<?> other = (SLinkedList<?>) obj;
        if (this.size != other.size) return false;

        Node<T> currentThis = this.head;
        // CHANGE THIS LINE BELOW:
        Node<?> currentOther = (Node<?>)other.head; 

        while (currentThis != null && currentOther != null) {
            if (!currentThis.data.equals(currentOther.data)) return false;
            currentThis = currentThis.next;
            currentOther = currentOther.next;
        }

        return true;
    }


}
