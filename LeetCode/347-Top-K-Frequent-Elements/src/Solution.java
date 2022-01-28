import java.util.Comparator;
import java.util.TreeMap;
import java.util.PriorityQueue;

/**
 * https://leetcode-cn.com/problems/top-k-frequent-elements/
 * 给你一个整数数组 nums 和一个整数 k ，
 * 请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 */
class Solution {

    public int[] topKFrequent(int[] nums, int k) {

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int num: nums){
            if (map.containsKey(num))
                map.put(num, map.get(num) + 1);
            else
                map.put(num, 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(
                (a, b) -> map.get(a) - map.get(b)
        );
        for (int key : map.keySet()){
            if (pq.size() < k)
                pq.add(key);
            else if (map.get(key) > map.get(pq.peek())){
                pq.remove();
                pq.add(key);
            }
        }
        int[] arr = new int[k];
        for (int i = 0; !pq.isEmpty() ; i ++){
            arr[i] = pq.remove();
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {3,0,1,0};
        Solution solution = new Solution();
        int[] res =solution.topKFrequent(arr, 1);
        for (int i = 0; i < res.length ; i ++)
            System.out.println(res[i]);
    }
}