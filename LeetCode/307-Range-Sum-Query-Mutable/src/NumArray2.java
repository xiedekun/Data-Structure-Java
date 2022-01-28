/**
 * 给你一个数组 nums ，请你完成两类查询，
 * 其中一类查询要求更新数组下标对应的值，另一类查询要求返回数组中某个范围内元素的总和。
 * https://leetcode-cn.com/problems/range-sum-query-mutable/
 * 输入：
 * ["NumArray", "sumRange", "update", "sumRange"]
 * [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
 * 输出：
 * [null, 9, null, 8]
 *
 * 解释：
 * NumArray numArray = new NumArray([1, 3, 5]);
 * numArray.sumRange(0, 2); // 返回 9 ，sum([1,3,5]) = 9
 * numArray.update(1, 2);   // nums = [1,2,5]
 * numArray.sumRange(0, 2); // 返回 8 ，sum([1,2,5]) = 8
 */
class NumArray2 {

    public interface Merger<E> {
        E merge(E a, E b);
    }

    public class SegmentTree<E>{

        private E[] tree;
        private E[] data;
        private Merger<E> merger;

        public SegmentTree(E[] arr, Merger<E> merger){
            this.merger = merger;

            data = (E[])new Object[arr.length];
            for (int i = 0; i < arr.length; i ++)
                data[i] = arr[i];

            tree = (E[])new Object[arr.length * 4];
            buildSegmentTree(0, 0, data.length - 1);
        }

        //在treeIndex的位置创建表示区间[l...r]的线段树
        private void buildSegmentTree(int treeIndex, int l, int r){

            if (l == r) {
                tree[treeIndex] = data[l];
                return;
            }
            int leftTreeIndex = leftChild(treeIndex);
            int rightTreeIndex = rightChild(treeIndex);

            int mid = l + (r - l) / 2; //(l + r) / 2 防止整形溢出
            buildSegmentTree(leftTreeIndex, l, mid);
            buildSegmentTree(rightTreeIndex, mid + 1, r);

            tree[treeIndex] = merger.merge(tree[rightTreeIndex], tree[leftTreeIndex]);
        }

        public int getSize(){
            return data.length;
        }

        public E get(int index){
            if (index < 0 || index > data.length)
                throw new IllegalArgumentException("Index is illegal");
            return data[index];
        }

        //返回左孩子索引
        private int leftChild(int index){
            return 2 * index + 1;
        }

        //返回右边孩子索引
        private int rightChild(int index){
            return 2 * index + 2;
        }

        //返回区间[queryL,queryR]的值
        public E query(int queryL, int queryR){
            if (queryL < 0 || queryR >= data.length ||
                    queryR < 0 || queryR >= data.length || queryL > queryR)
                throw new IllegalArgumentException("index is illegal");
            return query(0, 0, data.length - 1, queryL, queryR);
        }

        //以treeID为根的线段树中[l..r]范围里，搜索[queryL..queryR]的值
        private E query(int treeIndex, int l, int r, int queryL, int queryR){

            if(l == queryL && r ==queryR)
                return tree[treeIndex];

            int mid = l + (r - l) / 2;
            int leftTreeIndex = leftChild(treeIndex);
            int rightTreeIndex = rightChild(treeIndex);

            if (queryL >= mid + 1)
                return query(rightTreeIndex, mid + 1, r, queryL, queryR);
            else if (queryR <= mid)
                return query(leftTreeIndex, l, mid, queryL, queryR);

            E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
            E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
            return merger.merge(leftResult, rightResult);
        }

        //将index元素更新称e
        public void set(int index, E e){

            if (index < 0 || index > data.length)
                throw new IllegalArgumentException("Index is illegal");

            data[index] = e;
            set(0, 0, data.length - 1, index, e );
        }

        private void set(int treeIndex, int l, int r, int index, E e){

            if (l == r) {
                tree[treeIndex] = data[l];
                return;
            }
            int leftTreeIndex = leftChild(treeIndex);
            int rightTreeIndex = rightChild(treeIndex);
            int mid = l + (r - l) / 2;

            if (index >= mid + 1 )
                set(rightTreeIndex, mid + 1, r, index, e);
            else
                set(leftTreeIndex, l, mid, index, e);

            tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
        }
        @Override
        public String toString(){
            StringBuilder res = new StringBuilder();
            res.append('[');
            for(int i = 0; i < tree.length; i++){
                if (tree[i] != null)
                    res.append(tree[i]);
                else
                    res.append("null");

                if (i != tree.length -1)
                    res.append(", ");
                else
                    res.append(']');
            }
            return res.toString();
        }
    }

    SegmentTree<Integer> segmentTree;

    public NumArray2(int[] nums) {

        if (nums.length >0){
        Integer[] arr = new Integer[nums.length];
        for (int i = 0; i < nums.length; i ++)
        arr[i] = nums[i];
        segmentTree = new SegmentTree<>(arr, (a,b)->a+b);
        }

    }

    public void update(int index, int val) {
        if (segmentTree == null)
            throw new IllegalArgumentException("Segment Tree is null");
        segmentTree.set(index, val);
    }

    public int sumRange(int left, int right) {
        if (segmentTree == null)
            throw new IllegalArgumentException("SegmentTree is null");
        return segmentTree.query(left, right);
    }
}
/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */