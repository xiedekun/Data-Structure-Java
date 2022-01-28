import java.util.*;

public class Main {

    public static void main(String[] args) {

        int testCount = 10000;
        List<Integer> arr = new ArrayList<>();
        Random random = new Random();

        for(int i = 0; i < testCount; i++)
            arr.add(random.nextInt(testCount));

        Collections.sort(arr);

        AVLTree<Integer, Integer> avlTree = new AVLTree<>();
        long startTime1 = System.nanoTime();

        for (int a: arr)
            if (avlTree.contains(a))
                avlTree.set(a, avlTree.get(a) + 1);
            else
                avlTree.add(a, 1);
        System.out.println("Set:"+avlTree.getSize());

        for(int a : arr)
            avlTree.contains(a);

        long endTime1 = System.nanoTime();


        System.out.println("AVL:"+((endTime1 - startTime1)/1.0e9)+'s');
        //System.out.println("is BST Tree: " + avlTree.isBST());
        //System.out.println("is Balanced: " + avlTree.isBalanced());

        BST<Integer, Integer> bst = new BST<>();
        long startTime2 = System.nanoTime();

        for (int a: arr)
            if (bst.contains(a))
                bst.set(a, bst.get(a) + 1);
            else
                bst.add(a, 1);
        System.out.println("Set:"+bst.getSize());

        for(int a : arr)
            bst.contains(a);

        long endTime2 = System.nanoTime();
        System.out.println("BST:"+((endTime2 - startTime2)/1.0e9)+'s');
    }
}
