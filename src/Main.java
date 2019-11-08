public class Main {
    public static void main(String[] args) {
        Cube c = new Cube(3);
        c.move(Cube.MOVES.FRONT);
        c.move(Cube.MOVES.RIGHT);
        c.move(Cube.MOVES.UP);
        c.move(Cube.MOVES.BACK);
        c.move(Cube.MOVES.LEFT);
        c.move(Cube.MOVES.BACK);
        c.move(Cube.MOVES.RUP);
        c.move(Cube.MOVES.RIGHT);
        c.move(Cube.MOVES.RIGHT);
        c.print();
    }
}
