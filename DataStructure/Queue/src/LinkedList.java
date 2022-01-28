public class LinkedList<E> {

    private class Node{
        public E e;
        public Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e, null);
        }

        public Node(){
            this(null, null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedList(){
        dummyHead = new Node(null, null);
        size = 0;
    }

    //获取链表中元素个数
    public int getSize(){
        return size;
    }

    //返回链表是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    //链表index处插入元素e
    //不常用，练习用
    public void add(int index, E e){
        if(index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index");

        Node prev = dummyHead;
        for(int i = 0; i < index ; i++)
            prev = prev.next;
//            Node node = new Node(e);
//            node.next = pre.next;
//            pre.next = node;

        prev.next = new Node(e, prev.next);
        size ++;
    }

    //链表开头插入元素
    public void addFirst(E e){
        add(0, e);
    }

    //链表末尾添加元素
    public void addLast(E e){
        add(size, e);
    }

    //获取第index元素
    //不常用，练习用
    public E get(int index){
        if(index < 0 || index > size)
            throw new IllegalArgumentException("Get failed. Illegal index");

        Node cur = dummyHead.next;
        for(int i = 0; i < index ; i++)
            cur = cur.next;
        return cur.e;
    }

    //获取链表第1个元素
    public E getFirst(){
        return get(0);
    }

    //获取最后一个元素
    public E getLast(){
        return get(size - 1);
    }

    //修改第index元素
    public void set(int index, E e){
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed. Illegal index");

        Node cur = dummyHead.next;
        for(int i = 0; i < index; i++)
            cur = cur.next;
        cur.e = e;
    }

    //查找是否存在e
    public boolean contains(E e){
        Node cur = dummyHead.next;
//        for(int i = 0; i < size; i++){
//            if(cur.equals(e))
//                return true;
//            cur = cur.next;
//        }
        while(cur != null){
            if(cur.e.equals(e))
                return true;
            cur = cur.next;
        }
        return false;
    }

    //删除index位置元素
    public E remove(int index){
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Illegal index");

        Node prev = dummyHead;
        for(int i = 0; i < index; i++)
            prev = prev.next;

        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;
        size --;

        return retNode.e;

    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size - 1);
    }

    public void removeElement(E e){

        Node prev = dummyHead;
        while(prev.next != null){
            if(prev.next.e.equals(e))
                break;
            prev = prev.next;
        }

        if (prev.next != null){
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size --;
        }
    }

    @Override
    public String toString(){

        StringBuilder res = new StringBuilder();

//        Node cur = dummyHead.next;
//        while (cur != null){
//            res.append(cur + "->");
//            cur = cur.next;
//        }
        for(Node cur = dummyHead.next; cur != null; cur = cur.next)
            res.append(cur + "->");
        res.append("NULL");

        return res.toString();
    }







}
