import java.util.Random;
public class Main {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, String> table = new MyHashTable<>(50);
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            int id = random.nextInt(100000);
            String name = "name" + random.nextInt(1000);
            table.put(new MyTestingClass(id, name), "student" + i);
        }
        table.printBucketSize();
    }
}