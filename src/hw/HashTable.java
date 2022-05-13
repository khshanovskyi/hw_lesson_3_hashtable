package hw;

public class HashTable<K, V> {
    private int capacity = 16;
    private float loadFactor = 0.75F;
    private int size = 0;
    private int freeBuckets = 4;

    private Node<K, V>[] elements;

    @SuppressWarnings("unchecked")
    public HashTable() {
        elements = new Node[capacity];
    }

    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        this.capacity = capacity;
        elements = new Node[capacity];
    }

    @SuppressWarnings("unchecked")
    public HashTable(int capacity, float loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        elements = new Node[capacity];
    }

    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

         Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Puts a new element to the table by its key. If there is an existing element by such key then it gets replaced
     * with a new one, and the old value is returned from the method. If there is no such key then it gets added and
     * null value is returned.
     *
     * @param key   element key
     * @param value element value
     * @return old value or null
     */
    public V put(K key, V value) {
        int indexForKey = calculateIndex(hash(key));

        if (elements[indexForKey] == null) {
            elements[indexForKey] = new Node<>(key, value);
            size++;
            if (capacity - size == freeBuckets) {
                resize();
            }
        } else {
            Node<K, V> element = elements[indexForKey];
            if (elementKeyEqualsKey(element, key)) {
                return value;
            } else {
                if (element.next == null) {
                    element.next = new Node<>(key, value);
                } else {
                    boolean contains = false;
                    while (element.next != null) {
                        if (elementKeyEqualsKey(element, key)) {
                            contains = true;
                            break;
                        } else {
                            element = element.next;
                        }
                    }

                    if (!contains && !elementKeyEqualsKey(element, key)) {
                        element.next = new Node<>(key, value);
                    }
                }
            }
        }
        return value;
    }

    /**
     * Prints a content of the underlying table (array) according to the following format:
     * 0: key1:value1 -> key2:value2
     * 1:
     * 2: key3:value3
     * ...
     */
    public void printTable() {
        for (int i = 0; i < elements.length; i++) {
            System.out.print(i + ": ");
            if (elements[i] != null) {
                System.out.print(elements[i].key + " " + elements[i].value);
                if (elements[i].next != null) {
                    Node<K, V> current = elements[i];
                    while (current.next != null) {
                        System.out.print(" -> " + elements[i].next.key + " " + elements[i].next.value);
                        current = current.next;
                    }
                }
                System.out.println();
            } else {
                System.out.println();
            }
        }
    }

    public V getValue(K key){
        V value = null;
        Node<K, V> element = getNode(key);
        if (element != null){
            if (elementKeyEqualsKey(element, key)) {
                value = element.value;
            } else {
                boolean flag = false;
                while (!flag && element.next != null) {
                    element = element.next;
                    flag = elementKeyEqualsKey(element, key);
                }
                if (flag){
                    value = element.value;
                }
            }
        }
        return value;
    }

    public boolean containsKey(K key) {
        boolean flag = false;
        Node<K, V> element = getNode(key);
        if (element != null){
            if (elementKeyEqualsKey(element, key)) {
                flag = true;
            } else {
                while (!flag && element.next != null) {
                    element = element.next;
                    flag = elementKeyEqualsKey(element, key);
                }
            }
        }
        return flag;
    }

    private int hash(K key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    private int calculateIndex(int hash) {
        if (hash >= 0 && hash <= capacity) {
            return hash;
        }
        int result = Math.abs(hash);
        while (result >= capacity) {
            result = Math.abs((result % capacity) + 1);
        }
        return result;
    }

    private void resize() {
        Node<K, V>[] oldElements = elements;
        capacity = Math.round(capacity + (capacity * loadFactor));
        size = 0;
        freeBuckets = (int) Math.sqrt(capacity);
        elements = new Node[capacity];
        for (Node<K, V> oldElement : oldElements) {
            if (oldElement != null) {
                Node<K, V> current = oldElement;
                put(current.key, current.value);
                while (current.next != null) {
                    current = current.next;
                    put(current.key, current.value);
                }
            }
        }
    }

    private Node<K,V> getNode(K key){
        return elements[calculateIndex(hash(key))];
    }

    private boolean elementKeyEqualsKey(Node<K, V> element, K key) {
        int elementHashCode = 0;
        int keyHashCode = 0;
        boolean flag = false;

        if (element != null && element.key != null) {
            elementHashCode = element.key.hashCode();
        }
        if (key != null) {
            keyHashCode = key.hashCode();
        }
        if (elementHashCode == 0 && keyHashCode == 0 || elementHashCode == keyHashCode && element.key.equals(key)) {
            flag = true;
        }

        return flag;
    }


    public static void main(String[] args) {
        HashTable<Integer, String> map = new HashTable<>();
        map.put(null, "Pavlo");
        map.put(5436534, "Pavlo");
        map.put(7886879, "Yeva");
        map.put(1356345, "Ihor");
        map.put(8534567, "Nika");
        map.put(8534567, "Nika");
        map.put(5868732, "Olya");
        map.put(2058737, "Taras");
        map.put(6388274, "Yurii");
        map.put(1903455, "John");
        map.put(1346677, "Nick");
        map.put(8554667, "Liliya");
        map.put(5784783, "Ivan");
        map.put(1304867, "Petro");
        map.put(1895775, "Vitaliy");
        map.put(393947688, "Inna");
        map.put(56374664, "Tolya");
        map.put(4996578, "Tanya");
        map.put(4996578, "Tanya");
        map.put(null, null);
        map.put(7, "Illya");
        map.put(11, "Polya");
        map.put(12, "Irina");
        map.printTable();
        System.out.println();
        System.out.println(map.containsKey(56374664));
        System.out.println(map.containsKey(555));
        System.out.println(map.containsKey(null));
        System.out.println(map.getValue(4996578));
        System.out.println(map.getValue(null));
        System.out.println(map.getValue(444444));
    }

}