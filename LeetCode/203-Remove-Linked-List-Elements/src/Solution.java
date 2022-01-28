/**
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 * 该程序为了调试递归
 */
class Solution {

    //使用虚拟头结点方法
    public ListNode removeElements(ListNode head, int val, int depth) {
        String depthString = generateDepthString(depth);

        System.out.print(depthString);
        System.out.println("Call: remove " + val + " in " + head);
            if (head == null){
                System.out.print(depthString);
                System.out.println("Return: " + head);
                return head;
        }
        ListNode res = removeElements(head.next, val, depth + 1);
        System.out.print(depthString);
        System.out.println("After remove " + val + ": " + res);

        ListNode ret;
        if (head.val == val)
            ret = res;
        else {
            head.next = res;
            ret = head;
        }

        System.out.print(depthString);
        System.out.println("Return " + ret);
        return ret;
    }

    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < depth ; i++)
            res.append("--");
        return res.toString();
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 2, 5, 2};
        ListNode head = new ListNode(nums);
        System.out.println(head);
        ListNode res = (new Solution()).removeElements(head,2, 0);
        System.out.println(res);
    }
}