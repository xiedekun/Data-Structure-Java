class Solution3 {

    //使用虚拟头结点方法
    public ListNode removeElements(ListNode head, int val) {
        if (head == null)
            return null;
        /*ListNode res = removeElements(head.next, val);
        if (head.val == val)
            return res;
        else {
            head.next = res;
            return head;
        }*/

        head.next = removeElements(head.next, val);
        return head.next.val == val ? head.next : head;
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 2, 5, 2};
        ListNode head = new ListNode(nums);
        System.out.println(head);
        ListNode res = (new Solution3()).removeElements(head,2);
        System.out.println(res);
    }
}