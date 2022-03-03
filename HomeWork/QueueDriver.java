package HomeWork;

public class QueueDriver {

    public static void main(String[] args) {
        GenericQueue<String> carWash = new GenericQueue<>(10);
        carWash.enqueue("Toyota-Corolla");
        carWash.enqueue("Toyota-Sienna");
        carWash.enqueue("Ford-F150");
        carWash.enqueue("Doge-Charger");
        carWash.enqueue("Tesla-S");
        carWash.enqueue("Nissan-Optus");
        System.out.println(carWash.size());
        System.out.println(carWash.dequeue());
        System.out.println(carWash.size());
        System.out.println(carWash.dequeue());
        System.out.println(carWash.size());
    }
}