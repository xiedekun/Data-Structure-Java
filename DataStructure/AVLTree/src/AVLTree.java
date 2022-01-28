import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AVLTree<K extends Comparable<K>, V>{

    private class Node{
        public K key;
        public V value;
        public Node left, right;
        public int height;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    Node root;
    int size;

    public AVLTree(){
        root = null;
        size = 0;
    }

    //获得结点高度
    public int getHeight(Node node){
        if (node == null)
            return 0;

        return node.height;
    }

    //获得平衡因子
    public int getBalanceFactor(Node node){
        if (node == null)
            return 0;

        return getHeight(node.left) - getHeight(node.right);
    }

    //检查是否符合二分搜索树
    public boolean isBST(){
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for(int i = 1; i < keys.size(); i++)
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0)
                return false;
        return true;
    }

    //判断是否是平衡二叉树
    public boolean isBalanced(){
        return isBalanced(root);
    }

    //判断以node为根的二叉树是否为平衡二叉树，递归算法。
    private boolean isBalanced(Node node){
        if (node == null)
            return true;

        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1)
            return false;
        return isBalanced(node.left) && isBalanced(node.right);
    }

    //以node为根的二分搜索树递归遍历
    private void inOrder(Node node, ArrayList<K> keys){
        if (node == null)
            return;

        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    //向二分搜索树添加元素(key, value)

    public void add(K key, V value){
        root = add(root, key, value);
    }

    //向以node为根的二分搜索树插入元素(key, value)，递归算法
    //返回插入新节点后二分搜索树的根
    private Node add(Node node, K key, V value){

        if (node == null){
            size ++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key)<0)
            node.left = add(node.left, key, value);
        else if(key.compareTo(node.key)>0)
            node.right = add(node.right, key, value);
        else //相等
            node.value = value;

        //更新height值
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        //计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        //if(Math.abs(balanceFactor) > 1)
        //   System.out.println("unbalanced: " + balanceFactor);

        //平衡维护

        //LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0)
            return rightRotate(node);

        //RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0)
            return leftRotate(node);

        //LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0){
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        //RL
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0){
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    //右旋转
    //        y                             x
    //       / \                           / \
    //      x   T4      向右旋转(y)         z    y
    //     / \      -------------->      / \   / \
    //    z   T3                        T1 T2 T3 T4
    //   / \
    //  T1 T2
    private Node rightRotate(Node y){

        Node x = y.left;
        Node T3 = x.right;

        //向右旋转
        x.right = y;
        y.left = T3;

        //更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    //左旋转
    //        y                             x
    //       / \                           / \
    //      T1   x      向左旋转(y)         z    y
    //          / \      -------------->  / \   / \
    //         T2  z                   T1 T2 T3 T4
    //             / \
    //            T3 T4
    private Node leftRotate(Node y){

        Node x = y.right;
        Node T2 = x.left;

        //向右旋转
        x.left = y;
        y.right = T2;

        //更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
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

        Node retNode;

        if (key.compareTo(node.key) < 0){
            node.left = remove(node.left, key);
            retNode = node;
        }
        else if(key.compareTo(node.key) > 0){
            node.right = remove(node.right, key);
            retNode = node;
        }
        else{
            //待删结点左子树为空
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                retNode = rightNode;
            }
            //待删结点右子树为空
            else if(node.right ==null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                retNode = leftNode;
            }
            else{
                //找到右子树的最小值替换删除结点
                Node successor = minimum(node.right);
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;
                node.left = node.right = null;

                retNode = successor;
            }
        }

            if (retNode == null)
                return null;

            //更新height值
            retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));
            //计算平衡因子
            int balanceFactor = getBalanceFactor(retNode);

            //平衡维护

            //LL
            if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0)
                return rightRotate(retNode);

            //RR
            if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0)
                return leftRotate(retNode);

            //LR
            if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0){
                retNode.left = leftRotate(retNode.left);
                return rightRotate(retNode);
            }

            //RL
            if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0){
                retNode.right = rightRotate(retNode.right);
                return leftRotate(retNode);
            }

            return retNode;

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

        AVLTree<Integer, Integer> avlTree = new AVLTree<>();
        long startTime1 = System.nanoTime();

        for (int a: arr)
            if (avlTree.contains(a))
                avlTree.set(a, avlTree.get(a) + 1);
            else
                avlTree.add(a, 1);
        System.out.println("Set:"+avlTree.getSize());

        for(int a : arr)
            avlTree.contains(a);

        long endTime1 = System.nanoTime();


        System.out.println("AVL:"+((endTime1 - startTime1)/1.0e9)+'s');
        System.out.println("is BST Tree: " + avlTree.isBST());
        System.out.println("is Balanced: " + avlTree.isBalanced());

        for (int a : arr){
            avlTree.remove(a);
            if (!avlTree.isBalanced() || !avlTree.isBST())
                throw new RuntimeException("Error");
        }
    }

}
