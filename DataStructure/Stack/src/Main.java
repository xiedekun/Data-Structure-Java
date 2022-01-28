import java.util.Random;
public class Main {

    //测试q运行opCount个enqueue和dequeue所需要时间，单位s
    private static double testStack(Stack<Integer> q, int opCount){

        long startTime = System.nanoTime();

        Random random = new Random();
        for(int i = 0 ; i < opCount ; i++)
            q.push(random.nextInt(Integer.MAX_VALUE));

        for(int i = 0 ; i < opCount ; i++)
            q.pop();

        long endTime = System.nanoTime();

        return (endTime - startTime)/ 1.0e9;
    }
    public static void main(String[] args) {

        int opCount = 100000;

        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        double time1 = testStack(arrayStack, opCount);
        System.out.println("ArrayStack:" + time1 + 's');

        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
        double time2 = testStack(linkedListStack, opCount);
        System.out.println("LinkedListStack:" + time2 + 's');

        //LinkedListStack 会需要很多new，可能会更耗时
    }
}
