public class LinkedList<T extends Comparable<T>> {
    private Node root;
    private int size;

    public void turn() {
        if (this.size <= 1) {
            System.out.println("No need to make turn, size is too small");
            return;
        } else swapNodes(root.next, root);
    }

    public void swapNodes(Node thisNode, Node previousNode) {
        if (thisNode.next == null) {
            root = thisNode;
            thisNode.next = previousNode;
        } else {
            swapNodes(thisNode.next, thisNode);
        }
        thisNode.next = previousNode;
        previousNode.next = null;
    }

    public void turnBySwapsValues() {
        for (int i = 0; i <size - 1; i++) {
            for (int j = 0; j < size - 1 -i; j++) {
                swap(j, j + 1);
            }

        }

    }

    private void swap(int index1, int index2) {
        if (index1 < 0 || index1 >= size)
            return;
        if (index2 < 0 || index2 >= size)
            return;
        Node node1 = getNode(index1);
        Node node2 = getNode(index2);
        T temp = node1.value;
        node1.value = node2.value;
        node2.value = temp;
    }

    public void add(T value) {
        if (root == null) {
            root = new Node(value);
            size = 1;
            return;
        }
        Node currentNode = root;
        while (currentNode.next != null)
            currentNode = currentNode.next;
        currentNode.next = new Node(value);
        size++;
    }

    public void addAt(T value, int index) {
        if (index < 0 || index > size)
            return;
        if (index == 0) {
            Node newNode = new Node(value);
            newNode.next = root;
            root = newNode;
            size++;
            return;
        }
        Node currentNode = getNode(index - 1);
        Node newNode = new Node(value, currentNode.next);
        currentNode.next = newNode;
        size++;
    }

    public boolean remove(T value) {
        if (root == null)
            return false;
        if (root.value.compareTo(value) == 0) {
            root = root.next;
            size--;
            return true;
        }
        Node currentNode = root;
        while (currentNode.next != null) {
            if (currentNode.next.value.compareTo(value) == 0) {
                currentNode.next = currentNode.next.next;
                size--;
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    public void removeAt(int index) {
        if (index < 0 || index >= size)
            return;
        if (index == 0) {
            root = root.next;
            size--;
            return;
        }
        Node currentNode = getNode(index - 1);
        currentNode.next = currentNode.next.next;
        size--;
    }

    public T getValue(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        Node currentNode = root;
        for (int i = 0; i < index; i++)
            currentNode = currentNode.next;
        return currentNode.value;
    }

    private Node getNode(int index) {
        if (index < 0 || index >= size)
            return null;
        if (index == 0)
            return root;
        Node currentNode = root;
        for (int i = 0; i < index; i++)
            currentNode = currentNode.next;
        return currentNode;
    }

    public int getSize() {
        return size;
    }

    public void clear() {
        root = null;
        size = 0;
    }


    public void quickSort() {
        quickSort(0, size - 1);
    }

    private void quickSort(int leftBorder, int rightBorder) {
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        T pivot = getValue((leftMarker + rightMarker) / 2);
        do {
            while (getValue(leftMarker).compareTo(pivot) < 0) {
                leftMarker++;
            }
            while (getValue(rightMarker).compareTo(pivot) > 0) {
                rightMarker--;
            }
            if (leftMarker <= rightMarker) {
                if (leftMarker < rightMarker)
                    swap(leftMarker, rightMarker);
                leftMarker++;
                rightMarker--;
            }
        } while (leftMarker <= rightMarker);
        if (leftMarker < rightBorder)
            quickSort(leftMarker, rightBorder);
        if (leftBorder < rightMarker)
            quickSort(leftBorder, rightMarker);
    }

    public void print() {
        Node currentNode = root;
        while (currentNode != null) {
            System.out.print(currentNode.value + " ");
            currentNode = currentNode.next;
        }
        System.out.println();
    }

    private class Node {
        T value;
        Node next;

        Node() {
        }

        Node(T _value) {
            this.value = _value;
        }

        Node(T _value, Node _next) {
            this.value = _value;
            this.next = _next;
        }
    }
}