public class Main {
    public static void main(String[] args) {
        Cube c = new Cube(3);
        c.move("F R' U L' F' B L' B' F");
        c.print();
    }
}
