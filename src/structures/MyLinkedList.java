package structures;

public class MyLinkedList<T> {
    private class Node {
        T data;
        Node next;
        Node(T data) { this.data = data; }
    }

    private Node head;

    public void add(T data) {
        Node newNode = new Node(data);
        if (head == null) head = newNode;
        else {
            Node curr = head;
            while (curr.next != null) curr = curr.next;
            curr.next = newNode;
        }
    }

    public boolean contains(java.util.function.Predicate<T> predicate) {
        Node curr = head;
        while (curr != null) {
            if (predicate.test(curr.data)) return true;
            curr = curr.next;
        }
        return false;
    }
}
