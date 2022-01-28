import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
/*        int[] nums = {3, 11, 20, 1, 9};
        //      3
        //      / \
        //     1   11
        //         / \
        //        9  20
        for(int num : nums)
            bst.add(num);*/

        Random random = new Random();
        int n = 1000;

        for (int i = 0; i < n; i ++)
            bst.add(random.nextInt(10000));

        ArrayList<Integer> nums = new ArrayList<>();
        while(!bst.isEmpty()){
            nums.add(bst.removeMin());
        }

        System.out.println(nums);

        for (int i = 1; i < nums.size(); i ++)
            if (nums.get(i - 1) > nums.get(i))
                throw new IllegalArgumentException("Error");
        System.out.println("removeMin test completed");




    }
}
