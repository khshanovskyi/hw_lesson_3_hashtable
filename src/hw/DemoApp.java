package hw;

import static hw.SmthLikeLinkedList.*;

public class DemoApp {

    public static void main(String[] args) {
        Node<String> nodeList = createLinkedList("1", "2", "3", "4", "5");
        printLinkedList(nodeList);
        System.out.println();
        nodeList = reverse(nodeList);
        printLinkedList(nodeList);
        System.out.println();
        nodeList = recursiveReverse(nodeList);
        printLinkedList(nodeList);
    }
}
