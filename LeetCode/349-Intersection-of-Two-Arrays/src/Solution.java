import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/**
 * https://leetcode-cn.com/problems/intersection-of-two-arrays/
 * 算交集
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 */
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new TreeSet<>();
        ArrayList<Integer> list = new ArrayList<>();

        for (int num : nums1)
            set.add(num);

        for (int num : nums2) {
            if (set.contains(num)) {
                list.add(num);
                set.remove(num);
            }
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++)
            res[i] = list.get(i);

        return res;
    }
}
