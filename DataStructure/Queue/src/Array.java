public class Array<E> {

    private E[] data;
    private int size;

    //构造函数
    public Array(int capacity){
        data = (E[])new Object[capacity];
        size = 0;
    }

    //无参数构造函数，默认容量10
    public Array(){
        this(10);

    }

    //获取数组大小
    public int getSize() {
        return size;
    }

    //获取数组容量
    public int getCapacity(){
        return data.length;
    }

    //数组是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    //数组末尾增加元素
    public void addLast(E e){
        add(size, e);
    }

    //数组开头添加元素
    public void addFirst(E e){
        add(0, e);
    }

    //指定位置index插入元素
    public void add(int index, E e){

        if(index < 0 || index > size)
            throw new IllegalArgumentException("AddLast failed. Index>=0 and index<=size");

        if(size == data.length)
            resize(2 * data.length);

        for(int i = size - 1 ; i >= index ; i--)
            data[i+1] = data[i];

        data[index] = e;
        size ++;
    }

    //取得index位置数据
    public E get(int index){
        if(index < 0 || index > size)
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        return data[index];
    }

    //取第一个元素
    public E getFirst(){
        return get(0);
    }

    //取最后一个元素
    public E getLast(){
        return get(size - 1);
    }

    //更新index数据
    public void set(int index, E e){
        if(index < 0 || index > size)
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        data[index] = e;
    }

    //数组是否包含元素e
    public boolean contains(E e){
        for(int i = 0 ; i < size; i++){
            if(data[i].equals(e))
                return true;
        }
        return false;
    }

    //寻找元素e的索引
    public int find(E e){
        for(int i = 0 ; i < size; i++){
            if(data[i].equals(e))
                return i;
        }
        return -1;
    }

    //删除index数据
    public E remove(int index){
        if(index < 0 || index > size)
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        E ret = data[index];
        for(int i = index + 1 ; i < size ; i ++){
            data[i - 1] = data[i];
        }
        size --;
        //data[size] = null; //释放内存,loitering object != memory leak

        if(size == data.length/4 && data.length/2 != 0)
            resize(data.length/2);
        return ret;
    }

    //删除第一个元素
    public E removeFirst(){
        return remove(0);
    }

    //删除最后一个元素
    public E removeLast(){
        return remove(size - 1 );
    }

    //删除指定元素
    public void removeElement(E e){
        int index = find(e);
        if(index != -1)
            remove(index);
    }


    @Override
    public String toString(){

        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity = %d \n", size, data.length));
        res.append('[');
        for(int i = 0; i < size; i++){
            res.append(data[i]);
            if(i != size - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }

    private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity];
        for(int i = 0 ; i < size; i++)
            newData[i] = data[i];
        data = newData;
    }
}
