class Node {
    String value;
    Node left;
    Node right;

    Node(String value) {
        this.value = value;
        right = null;
        left = null;
    }

    //Retourner value à la place ?
    private Node addRecursive(Node current, String value) {
        if (current == null) {
            return new Node(value);
        }

        if (current.left == null) {
            current.left = addRecursive(current.left, value);
        } else if (current.right == null) {
            current.right = addRecursive(current.right, value);
        } else {
            return current;
        }

        return current;
    }
}