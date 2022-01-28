/**
 * https://leetcode-cn.com/problems/range-sum-query-immutable/
 * 输入：
 * ["NumArray", "sumRange", "sumRange", "sumRange"]
 * [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
 * 输出：
 * [null, 1, -1, -3]
 *
 * 解释：
 * NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
 * numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
 * numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1))
 * numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
 */
class NumArray {

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


    private SegmentTree<Integer>segmentTree;

    public NumArray(int[] nums) {

        if (nums.length >0){
            Integer[] arr = new Integer[nums.length];
            for (int i = 0; i < nums.length; i ++)
                arr[i] = nums[i];
            segmentTree = new SegmentTree<>(arr, (a,b)->a+b);
        }

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