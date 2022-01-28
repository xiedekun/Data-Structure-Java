import java.util.Random;

public class Main {

    private static double testSet(Set<Integer> set, int[] arr){

        long startTime = System.nanoTime();

        for (int a: arr)
            set.add(a);
        System.out.println("Set:"+set.getSize());

        long endTime = System.nanoTime();

        return (endTime - startTime)/1.0e9;
    }

    public static void main(String[] args) {

        int testCount = 1000000;
        int[] arr = new int[testCount];
        Random random = new Random();

        for(int i = 0; i < arr.length; i++)
            arr[i] = random.nextInt(arr.length);

        BSTSet<Integer> bstSet = new BSTSet<>();
        LinkedListSet<Integer> linkedListSet = new LinkedListSet<>();
        AVLSet<Integer> avlSet = new AVLSet<>();

        System.out.println("BST:"+testSet(bstSet, arr)+'s');
        //System.out.println("LinkedList:"+testSet(linkedListSet, arr)+'s');
        System.out.println("AVL:"+testSet(avlSet, arr)+'s');


    }
}
