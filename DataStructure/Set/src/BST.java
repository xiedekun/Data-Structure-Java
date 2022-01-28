import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST<E extends Comparable<E>> {

    private class Node{
        public E e;
        public Node left, right;

        public Node(E e){
            this.e = e;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    private int size;

    public BST(){
        root = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(E e){
        root = add(root, e);
    }

    //向以node为根的二分搜索树插入元素e，递归算法
    //返回插入新节点后二分搜索树的根
    private Node add(Node node, E e){
        /*if(node.e.equals(e))
            return;
        else if(e.compareTo(node.e) < 0 && node.left == null){
            node.left = new Node(e);
            size ++;
            return;
        }
        else if(e.compareTo(node.e) > 0 && node.right == null){
            node.right = new Node(e);
            size ++;
            return;
        }*/
        if (node == null){
            size ++;
            return new Node(e);
        }

        if (e.compareTo(node.e)<0)
            node.left = add(node.left, e);
        else if(e.compareTo(node.e)>0)
            node.right = add(node.right, e);

        return node;
    }

    public boolean contains(E e){
        return contains(root, e);
    }

    private boolean contains(Node node, E e){
        if(node == null)
            return false;

        if (node.e.compareTo(e) == 0)
            return true;
        else if (node.e.compareTo(e)<0)
            return contains(node.left, e);
        else
            return contains(node.right, e);
    }

    //二分搜索树前序遍历
    public void preOrder(){
        preOrder(root);
    }

    //以node为根的二分搜索树递归遍历
    private void preOrder(Node node){
        if (node == null)
            return;

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    //非递归前序遍历
    public void preOrderNR(){
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.e);

            if (cur.right != null)
                stack.push(cur.right);

            if (cur.left != null)
                stack.push(cur.left);
        }
    }

    //二分搜索树中序遍历
    public void inOrder(){
        inOrder(root);
    }

    //以node为根的二分搜索树递归遍历
    private void inOrder(Node node){
        if (node == null)
            return;

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    //二分搜索树后序遍历
    public void postOrder(){
        postOrder(root);
    }

    //以node为根的二分搜索树递归遍历
    private void postOrder(Node node){
        if (node == null)
            return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    public void levelOrder(){
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()){
            Node cur = q.remove();
            System.out.println(cur.e);

            if(cur.left != null)
                q.add(cur.left);
            if(cur.right != null)
                q.add(cur.right);
        }
    }

    // 寻找二分搜索树最小元素
    public E minimum(){
        if (size == 0)
            throw new IllegalArgumentException("BST is empty!");
        return minimum(root).e;
    }

    //递归寻找最小元素结点
    private Node minimum(Node node){
        if(node.left == null)
            return node;

        return minimum(node.left);
    }

    // 寻找二分搜索树最大元素
    public E maximum(){
        if (size == 0)
            throw new IllegalArgumentException("BST is empty!");
        return maximum(root).e;
    }

    //递归寻找最大元素结点
    private Node maximum(Node node){
        if(node.right == null)
            return node;

        return maximum(node.right);
    }

    //删除最小的结点，并且返回
    public E removeMin(){
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    //删除掉以node为根二分搜索树的最小结点
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

    //删除最大的结点，并且返回
    public E removeMax(){
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    //删除掉以node为根二分搜索树的最大结点
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

    //二分搜索树删除结点e
    public void remove(E e){
        root = remove(root, e);
    }
    
    private Node remove(Node node, E e){
        if (node == null)
            return null;

        if (e.compareTo(node.e) < 0){
            node.left = remove(node.left, e);
            return node;
        }
        else if(e.compareTo(node.e) > 0){
            node.right = remove(node.right, e);
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
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder res){

        if (node == null){
            res.append(generateDepthString(depth)+"null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e + '\n');
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++)
            res.append("--");
        return res.toString();
    }

}
