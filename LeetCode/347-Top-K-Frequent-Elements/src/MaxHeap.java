public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap(int capacity){
        data = new Array<>(capacity);
    }

    public MaxHeap(){
        data = new Array<>();
    }

    public MaxHeap(E[] arr){

        data = new Array<>(arr);
        for(int i = parent(arr.length - 1); i >= 0 ; i --)
            siftDown(i);
    }

    //返回堆中的元素
    public int size(){
        return data.getSize();
    }

    //返回布尔值，是否为空
    public boolean isEmpty(){
        return data.isEmpty();
    }

    //返回完全二叉树，一个索引的父亲结点索引
    private int parent(int index){
        if (index == 0)
            throw new IllegalArgumentException("index-0 doesn't have parent");
        return (index - 1) / 2;
    }

    //返回左孩子结点索引
    private int leftChild(int index){
        return index * 2 + 1;
    }

    //返回右孩子结点索引
    private int rightChild(int index){
        return index * 2 + 2;
    }

    //添加元素 sift up上浮
    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    private void siftUp(int k){

        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0){
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    //看堆中最大值
    public E findMax(){
        if (data.isEmpty())
            throw new IllegalArgumentException("The heap is empty");
        return data.get(0);
    }

    //取出堆中最大值
    public E extractMax(){
        E ret = data.get(0);

        data.set(0, data.get(data.getSize() - 1));
        data.removeLast();

        siftDown(0);

        return ret;
    }

    private void siftDown(int k){

        while(leftChild(k) < data.getSize()){

            int j = leftChild(k);
            if (j + 1 < data.getSize() &&
                    data.get(j + 1).compareTo(data.get(j)) > 0)
                j = rightChild(k);

            if (data.get(k).compareTo(data.get(j)) >= 0)
                break;

            data.swap(k, j);
            k = j;
        }
    }

    public E replace(E e){
        E ret = findMax();

        data.set(0, e);
        siftDown(0);
        return ret;
    }
}
