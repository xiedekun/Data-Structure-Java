public class Student {

    private String name;
    private int score;

    public Student(String name, int score){
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString(){
        return String.format("Student(name = %s, score= %d)",name, score);
    }

    public static void main(String[] args) {
        Array<Student> arr = new Array<>();
        arr.addLast(new Student("Jack",90));
        arr.addLast(new Student("Mack",93));
        arr.addLast(new Student("Sherry",20));

        System.out.println(arr);
    }
}

