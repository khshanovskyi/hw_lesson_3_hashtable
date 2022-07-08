package hw;

import java.util.Objects;

public class SmthLikeLinkedList {
    /**
     * Creates a linked list based on the input array and returns a head
     */
    public static <T> Node<T> createLinkedList(T... element) {
        Node<T> first = new Node<>(element[0]);
        Node<T> current = first;
        for (int i = 1; i < element.length; i++) {
            current.setNext(new Node<>(element[i]));
            current = current.getNext();
        }
        return first;
    }

    // make it also via recursion

    /**
     * Prints a linked list with arrows like this
     * head:5 -> 7 -> 1 -> 4
     *
     * @param head the first element of the list
     */
    public static void printLinkedList(Node<?> head) {
        var current = head;
        boolean flag = true;
        while (current.getNext() != null) {
            if (flag) {
                System.out.print(current.getElement() + " -> ");
                current = current.getNext();
            } else {
                System.out.print("head: " + current.getElement() + " -> ");
                current = current.getNext();
                flag = false;
            }
        }
        System.out.print(current.getElement());
    }

    public static <T> Node<T> reverse(Node<T> head) {
        System.out.println();
        Objects.requireNonNull(head, "The [head] should be not null");
        Node<T> current = head;
        Node<T> next;
        Node<T> previous = null;

        while (Objects.nonNull(current.getNext())) {
            next = current.getNext();
            current.setNext(previous);
            previous = current;
            current = next;
        }
        current.setNext(previous);
        previous = current;
        return previous;
    }

    public static <T> Node<T> recursiveReverse(Node<T> head){
        if (Objects.isNull(head.getNext())){
            return head;
        }

        Node<T> node = recursiveReverse(head.getNext());
        Node<T> last = getLast(node);
        Node<T> next = head;
        next.setNext(null);
        last.setNext(next);
        return node;
    }

    private  static <T> Node<T> getLast(Node<T> node){
        if (Objects.isNull(node.getNext())){
            return node;
        }
        return node.getNext();
    }
}
