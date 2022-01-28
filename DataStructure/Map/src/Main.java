import java.util.Random;

public class Main {

    private static double testMap(Map<Integer, Integer> map, int[] arr){

        long startTime = System.nanoTime();

        for (int a: arr)
            if (map.contains(a))
                map.set(a, map.get(a) + 1);
            else
                map.add(a, 1);
        System.out.println("Set:"+map.getSize());

        long endTime = System.nanoTime();

        return (endTime - startTime)/1.0e9;
    }

    public static void main(String[] args) {

        int testCount = 10000;
        int[] arr = new int[testCount];
        Random random = new Random();

        for(int i = 0; i < arr.length; i++)
            arr[i] = random.nextInt(testCount);

        BSTMap<Integer, Integer> bstMap = new BSTMap();
        LinkedListMap<Integer, Integer> linkedListMap = new LinkedListMap<>();
        AVLMap<Integer, Integer> avlMap = new AVLMap<>();

        System.out.println("BST:"+testMap(bstMap, arr)+'s');
        System.out.println("LinkedList:"+testMap(linkedListMap, arr)+'s');
        System.out.println("AVL:"+testMap(avlMap, arr)+'s');


    }
}
