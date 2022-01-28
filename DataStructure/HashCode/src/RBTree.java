import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RBTree<K extends Comparable<K>, V>{

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node{
        public K key;
        public V value;
        public Node left, right;
        public boolean color;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.color = RED;
        }
    }

    Node root;
    int size;

    public RBTree(){
        root = null;
        size = 0;
    }

    public boolean isRed(Node node){
        if (node == null)
            return BLACK;
        return node.color;
    }

    //左旋转
    //        node                          x
    //       / \                           / \
    //      T1   x      向左旋转          node T3
    //          / \      -------------->  / \
    //         T2  T3                   T1 T2
    private Node leftRotate(Node node){

        Node x = node.right;

        node.right = x.left;
        x.left = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    //颜色翻转
    private void flipColors(Node node){

        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    //右旋转
    //       node                           x
    //       / \                           / \
    //      x   T2      向右旋转           y   node
    //     / \      -------------->           / \
    //    y   T3                             T1 T2
    private Node rightRotate(Node node){

        Node x = node.left;

        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    //向二分搜索树添加元素(key, value)
    public void add(K key, V value){
        root = add(root, key, value);
        root.color = BLACK;
    }

    //向以node为根的红黑树插入元素(key, value)，递归算法
    //返回插入新节点后红黑树的根
    private Node add(Node node, K key, V value){

        if (node == null){
            size ++;
            return new Node(key, value); //默认插入红色结点
        }

        if (key.compareTo(node.key)<0)
            node.left = add(node.left, key, value);
        else if(key.compareTo(node.key)>0)
            node.right = add(node.right, key, value);
        else //相等
            node.value = value;

        //维护红黑树
        if(isRed(node.right) && !isRed(node.left))
            node = leftRotate(node);

        if(isRed(node.left) && isRed(node.left.left))
            node = rightRotate(node);

        if (isRed(node.left) && isRed(node.right))
            flipColors(node);

        return node;
    }

    // 寻找二分搜索树key最小元素
    public K minimum(){
        if (size == 0)
            throw new IllegalArgumentException("BST is empty!");
        return minimum(root).key;
    }

    //递归寻找key最小元素结点
    private Node minimum(Node node){
        if(node.left == null)
            return node;

        return minimum(node.left);
    }

    // 寻找二分搜索树key最大元素
    public K maximum(){
        if (size == 0)
            throw new IllegalArgumentException("BST is empty!");
        return maximum(root).key;
    }

    //递归寻找key最大元素结点
    private Node maximum(Node node){
        if(node.right == null)
            return node;

        return maximum(node.right);
    }

    //删除key最小的结点，并且返回
    public K removeMin(){
        K ret = minimum();
        root = removeMin(root);
        return ret;
    }

    //删除掉以node为根二分搜索树的key最小结点
    //删除后返回新的二分搜索的根
    private Node removeMin(Node node){
        if(node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    //删除key最大的结点，并且返回
    public K removeMax(){
        K ret = maximum();
        root = removeMax(root);
        return ret;
    }

    //删除掉以node为根二分搜索树的key最大结点
    //删除后返回新的二分搜索的根
    private Node removeMax(Node node){
        if(node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size --;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    //二分搜索树删除结点key对应结点
    public V remove(K key){
        Node node = getNode(root, key);
        if (node != null){
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    //删除键为key
    private Node remove(Node node, K key){
        if (node == null)
            return null;

        if (key.compareTo(node.key) < 0){
            node.left = remove(node.left, key);
            return node;
        }
        else if(key.compareTo(node.key) > 0){
            node.right = remove(node.right, key);
            return node;
        }
        else{
            //待删结点左子树为空
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }
            //待删结点右子树为空
            else if(node.right ==null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }
            //找到右子树的最小值替换删除结点
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;

            return successor;
        }
    }


    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    //返回node为根节点二分搜索树中，key所在的结点
    private Node getNode(Node node, K key){
        if(node == null)
            return null;

        if (key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else if (key.compareTo(node.key) > 0)
            return getNode(node.right, key);
        else
            return node;

    }

    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null)
            throw new IllegalArgumentException(key + "doesn't exist");

        node.value = newValue;

    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        int testCount = 10000;
        List<Integer> arr = new ArrayList<>();
        Random random = new Random();

        for(int i = 0; i < testCount; i++)
            arr.add(random.nextInt(testCount));

        RBTree<Integer, Integer> rbTree = new RBTree<>();
        long startTime1 = System.nanoTime();

        for (int a: arr)
            if (rbTree.contains(a))
                rbTree.set(a, rbTree.get(a) + 1);
            else
                rbTree.add(a, 1);
        System.out.println("Set:"+rbTree.getSize());

        for(int a : arr)
            rbTree.contains(a);

        long endTime1 = System.nanoTime();


        System.out.println("RBTree:"+((endTime1 - startTime1)/1.0e9)+'s');

    }
}
