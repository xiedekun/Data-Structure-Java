public class Main {

    public static void main(String[] args) {

        Integer[] nums = {1,2,3,4,5,6,7};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, (a, b) -> a + b);
        System.out.println(segmentTree);

        System.out.println(segmentTree.query(0,2));
    }
}
