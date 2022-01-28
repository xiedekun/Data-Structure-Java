class Solution1 {

    //不使用虚拟头结点方法
    public ListNode removeElements(ListNode head, int val) {

        while(head != null && head.val == val){
            /*ListNode delNode = head;
            head = head.next;
            delNode.next = null;*/

            head = head.next;
        }

        if(head == null)
            return null;

        ListNode prev = head;
        while(prev.next != null){
            if(prev.next.val == val){
              /*  ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;*/
                prev.next = prev.next.next;
            }
            else
                prev = prev.next;
        }

        return head;
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 2, 5, 2};
        ListNode head = new ListNode(nums);
        System.out.println(head);
        ListNode res = (new Solution1()).removeElements(head,2);
        System.out.println(res);
    }
}