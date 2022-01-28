import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main2 {

    public static void main(String[] args) {

        int testCount = 10000;
        List<Integer> arr = new ArrayList<>();
        Random random = new Random();

        for(int i = 0; i < testCount; i++)
            arr.add(random.nextInt(Integer.MAX_VALUE));

        Collections.sort(arr);

        AVLTree<Integer, Integer> avlTree = new AVLTree<>();
        long startTime1 = System.nanoTime();

        for (int a: arr)
            if (avlTree.contains(a))
                avlTree.set(a, avlTree.get(a) + 1);
            else
                avlTree.add(a, 1);
        System.out.println("Set:"+avlTree.getSize());

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

        long endTime2 = System.nanoTime();
        System.out.println("BST:"+((endTime2 - startTime2)/1.0e9)+'s');

        RBTree<Integer, Integer> rbTree = new RBTree<>();
        long startTime3 = System.nanoTime();

        for (int a: arr)
            if (rbTree.contains(a))
                rbTree.set(a, rbTree.get(a) + 1);
            else
                rbTree.add(a, 1);

        System.out.println("Set:"+rbTree.getSize());

        long endTime3 = System.nanoTime();
        System.out.println("RBTree:"+((endTime3 - startTime3)/1.0e9)+'s');
    }
}
