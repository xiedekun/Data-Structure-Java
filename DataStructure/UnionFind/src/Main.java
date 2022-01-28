import java.util.Random;

public class Main {

    private static double testUF(UF uf, int m){
        int size = uf.getSize();
        Random random = new Random();

        long startTime = System.nanoTime();
        for (int i = 0; i < m; i++){
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.unionElements(a, b);
        }

        for (int i = 0; i < m; i++){
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.isConnected(a, b);
        }
        long endTime = System.nanoTime();

        return (endTime - startTime) / 1.0e9;
    }

    public static void main(String[] args) {

        int size = 10000000;
        int m = 10000000;

        //UnionFind1 uf1 = new UnionFind1(size);
        //UnionFind2 uf2 = new UnionFind2(size);
        UnionFind3 uf3 = new UnionFind3(size);
        UnionFind4 uf4 = new UnionFind4(size);
        UnionFind5 uf5 = new UnionFind5(size);
        UnionFind6 uf6 = new UnionFind6(size);

        //System.out.println("uf1: "+ testUF(uf1, m));
        //System.out.println("uf2: "+ testUF(uf2, m));
        System.out.println("uf3: "+ testUF(uf3, m));
        System.out.println("uf4: "+ testUF(uf4, m));
        System.out.println("uf5: "+ testUF(uf5, m));
        System.out.println("uf6: "+ testUF(uf6, m));
    }
}
