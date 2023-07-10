public class Main {
    public static void main(String[] args) {
        LinkedList<String> l1 = new LinkedList();
        l1.add("ABC");
        l1.add("12");

        l1.add("1.34");
        l1.add("HELLO");
        l1.add("bye-bye");
        l1.print();
        l1.quickSort();
        l1.print();
        l1.turn();
        l1.print();
        l1.turnBySwapsValues();
        l1.print();
    }
}