import java.util.*;

public class Main {

    public static void main(String[] args) {


        int testCount = 1000000;
        List<Integer> arr = new ArrayList<>();
        Random random = new Random();

        for(int i = 0; i < testCount; i++)
            arr.add(random.nextInt(testCount));

        Collections.sort(arr);

//        AVLTree<Integer, Integer> avlTree = new AVLTree<>();
//        long startTime1 = System.nanoTime();
//
//        for (int a: arr)
//            if (avlTree.contains(a))
//                avlTree.set(a, avlTree.get(a) + 1);
//            else
//                avlTree.add(a, 1);
//        System.out.println("Set:"+avlTree.getSize());
//
//        for(int a : arr)
//            avlTree.contains(a);
//
//        long endTime1 = System.nanoTime();
//
//
//        System.out.println("AVL:"+((endTime1 - startTime1)/1.0e9)+'s');
//        //System.out.println("is BST Tree: " + avlTree.isBST());
//        //System.out.println("is Balanced: " + avlTree.isBalanced());
//
//        BST<Integer, Integer> bst = new BST<>();
//        long startTime2 = System.nanoTime();
//
//        for (int a: arr)
//            if (bst.contains(a))
//                bst.set(a, bst.get(a) + 1);
//            else
//                bst.add(a, 1);
//        System.out.println("Set:"+bst.getSize());
//
//        for(int a : arr)
//            bst.contains(a);
//
//        long endTime2 = System.nanoTime();
//        System.out.println("BST:"+((endTime2 - startTime2)/1.0e9)+'s');

        RBTree<Integer, Integer> rbTree = new RBTree<>();
        long startTime3 = System.nanoTime();

        for (int a: arr)
            if (rbTree.contains(a))
                rbTree.set(a, rbTree.get(a) + 1);
            else
                rbTree.add(a, 1);

        System.out.println("Set:"+rbTree.getSize());

        for(int a : arr)
            rbTree.contains(a);

        long endTime3 = System.nanoTime();
        System.out.println("RBTree:"+((endTime3 - startTime3)/1.0e9)+'s');

        HashTable<Integer, Integer> hashTable = new HashTable<>();
        long startTime4 = System.nanoTime();

        for (int a: arr)
            if (hashTable.contains(a))
                hashTable.set(a, hashTable.get(a) + 1);
            else
                hashTable.add(a, 1);

        System.out.println("Set:"+hashTable.getSize());

        for(int a : arr)
            hashTable.contains(a);

        long endTime4 = System.nanoTime();
        System.out.println("Hash:"+((endTime4 - startTime4)/1.0e9)+'s');
    }
}
