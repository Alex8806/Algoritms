import java.util.ArrayList;
import java.util.List;

public class BinTree {
    Node root;

    public boolean add(int value) {
        if (root != null) {
            boolean result = addNode(root, value);
            root = rebalance(root);
            root.color = Color.black;
            return result;
        }
        else {
            root = new Node();
            root.color = Color.black;
            root.value = value;
            return true;
        }

        }

    public void print() {
        List<Node> nodes = new ArrayList<>();
        nodes.add(root);
        int j =0;
        while (!nodes.isEmpty()){
            List<Node> children = new ArrayList<>();
            for(Node node : nodes){
                System.out.print(node+"\t");
                if(node.left!= null) children.add(node.left) ;else System.out.printf(" left is null \t");
                if(node.right!= null)children.add(node.right);else System.out.printf(" right is null\t");
            }
            j++;
            System.out.println();
            nodes = children;
        }
    }

    private boolean addNode(Node node, int value) {
        if (node.value == value)
            return false;
        if (node.value > value) {
            if (node.left != null) {
                boolean result = addNode(node.left, value);
                node.left = rebalance(node.left);
                return result;
            } else{
                node.left = new Node();
                node.left.color = Color.red;
                node.left.value =value;
                return true;
            }
        } else {
            if (node.right != null) {
                boolean result = addNode(node.right,value);
                node.right = rebalance((node.right));
                return result;
            } else {
                node.right = new Node();
                node.right.color = Color.red;
                node.right.value =value;
                return true;
            }
        }
    }

    public boolean contain(int value) {
        Node currentNode = root;
        while (currentNode != null) {
            if (currentNode.value == value)
                return true;
            if (currentNode.value > value)
                currentNode = currentNode.left;
            else
                currentNode = currentNode.right;
        }
        return false;
    }

    private Node leftTurn(Node node) { //делается только в узле будет поворот
        Node leftCh = node.left;
        Node betweenChild = leftCh.right; // тот элемент который будет менять родителя
        leftCh.right = node; //для левосторонего поворота левая нода всегда красная так что рутовым становится элемен с которого мы поворачиваем
        node.left = betweenChild; // теперь тут черная нода которая между рутовой и красной нодой
        leftCh.color = node.color;
        node.color = Color.red;
        return leftCh;
    }

    private Node rightTurn(Node node) {
        Node rightCh = node.right;
        Node betweenChild = rightCh.left;
        rightCh.left = node;
        node.right = betweenChild;
        rightCh.color = node.color;
        node.color = Color.red;
        return rightCh;
    }

    private void colorSwap(Node node) {
        node.right.color = Color.black; // только для ситуации когда у ноды два красных ребенка (других ситаций нет)
        node.left.color = Color.black;
        node.color = Color.red;
    }

    private Node rebalance(Node node) {
        Node result = node;
        boolean needREbalance; // несколько циклов, чтобы проверить что точно все условия выполняются
        do {
            needREbalance = false;
            if (result.right != null && result.right.color == Color.red &&
                    (result.left == null || result.left.color == Color.black)) {
                needREbalance = true;
                result = rightTurn(result);
            }
            if (result.left != null && result.left.color == Color.red && result.left.left != null //если у красного  красный ребенок
                    && result.left.left.color == Color.red) {
                needREbalance = true;
                result = leftTurn(result);
            }
            if (result.left != null && result.left.color == Color.red && result.right != null // два левых ребенка
                    && result.right.color == Color.red){
                needREbalance = true;
                colorSwap(result);
            }
        }
        while (needREbalance);
        return result;
    }

    private class Node {
        int value;
        Node left;
        Node right;
        Color color;

        Node() {
            this.color = Color.red;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", color=" + color +
                    '}';
        }

        Node(int _value) {
            this.value = _value;
            this.color = Color.red;
        }
    }

    enum Color {red, black}


}
