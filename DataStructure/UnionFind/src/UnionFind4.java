public class UnionFind4 implements UF{

    private int[] parent;
    private int[] rank; //rank[i]表示以i为根集合树高度；

    public UnionFind4(int size){
        parent = new int[size];
        rank = new int[size];

        for (int i = 0; i < size; i++){
            parent[i] = i;
            rank[i] = 1;
        }
    }
    @Override
    public int getSize() {
        return parent.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    //查找过程，查询p所对应的集合编号
    //O(h)复杂度，h为树的高度
    private int find(int p){

        if (p < 0 || p > parent.length)
            throw new IllegalArgumentException("index is illegal");

        while(parent[p] != p)
            p = parent[p];

        return p;
    }

    //合并元素p和元素q的集合
    //O(h)复杂度，h为树的高度
    @Override
    public void unionElements(int p, int q) {

        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot)
            return;

        if (rank[pRoot] < rank[qRoot]){
            parent[pRoot] = qRoot;
        }
        else if (rank[qRoot] < rank[pRoot]){
            parent[qRoot] = pRoot;
        }
        else{ //相等
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }
    }
}
