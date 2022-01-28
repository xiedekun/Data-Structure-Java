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
class NumArray2 {

    private int[] sum; //存储前i元素的和，sum[0] = 0;

    public NumArray2(int[] nums) {
        sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i < sum.length; i++){
            sum[i] = sum[i - 1] + nums[i - 1];
        }
    }

    public int sumRange(int left, int right) {

        return sum[right + 1] - sum[left];
    }
}
/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */