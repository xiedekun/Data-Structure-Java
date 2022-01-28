public class BST<K extends Comparable<K>, V>{

    private class Node{
        public K key;
        public V value;
        public Node left, right;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    Node root;
    int size;

    public BST(){
        root = null;
        size = 0;
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
}
