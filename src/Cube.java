public class Cube {
    private int sideLen;
    private int elemsOnSide;
    private int[][] elements;

    private enum COLORS {
        WHITE,
        ORANGE,
        GREEN,
        RED,
        BLUE,
        YELLOW
    }

    private char getColorChar(int i) {
        if (i == COLORS.WHITE.ordinal())
            return 'W';
        if (i == COLORS.ORANGE.ordinal())
            return 'O';
        if (i == COLORS.GREEN.ordinal())
            return 'G';
        if (i == COLORS.RED.ordinal())
            return 'R';
        if (i == COLORS.BLUE.ordinal())
            return 'B';
        if (i == COLORS.YELLOW.ordinal())
            return 'Y';
        return '?';
    }

    public Cube(int sideLen) {
        this.sideLen = sideLen;
        this.elemsOnSide = sideLen * sideLen;

        // 6 sides
        this.elements = new int[6][elemsOnSide];

        for (int i = 0; i < 6; i++)
            for (int j = 0; j < elemsOnSide; j++)
                this.elements[i][j] = i;
    }

    public void print() {
        /*
        Sides' numeration:
             0
           1 2 3 4
             5
        */

        /*
        Sides' positions:
             U
           L F R B
             D
        */

        /*
        Sides' colors:
             W
           O G R B
             Y
        */

        // 1st side
        for (int i = 0; i < sideLen; i++) {
            for (int j = 0; j < sideLen; j++)
                System.out.print("  ");
            System.out.print("   ");

            for (int j = 0; j < sideLen; j++) {
                int elem = elements[0][i * sideLen + j];
                System.out.print(" " + getColorChar(elem));
            }
            System.out.println();
        }
        System.out.println();

        // 2, 3, 4, 5th sides
        for (int i = 0; i < sideLen; i++) {
            for (int j = 1; j < 5; j++) {
                for (int k = 0; k < sideLen; k++) {
                    int elem = elements[j][i * sideLen + k];
                    System.out.print(" " + getColorChar(elem));
                }
                System.out.print("   ");
            }
            System.out.println();
        }
        System.out.println();

        // 6th side
        for (int i = 0; i < sideLen; i++) {
            for (int j = 0; j < sideLen; j++)
                System.out.print("  ");
            System.out.print("   ");

            for (int j = 0; j < sideLen; j++) {
                int elem = elements[5][i * sideLen + j];
                System.out.print(" " + getColorChar(elem));
            }
            System.out.println();
        }
        System.out.println();
    }

    private void turnSide(int side) {
        // Clockwise rotation
        int[] rotatingSide = new int[elemsOnSide];
        for (int i = 0; i < sideLen; i++)
            for (int j = 0; j < sideLen; j++)
                rotatingSide[i * sideLen + j] = elements[side][i * sideLen + j];

        for (int i = 0; i < sideLen; i++)
            for (int j = 0; j < sideLen; j++)
                elements[side][i * sideLen + j] = rotatingSide[(sideLen - j - 1) * sideLen + i];

    }

    private void moveUp(boolean reverse) {
        int count = (reverse ? 3 : 1);
        for (int i = 0; i < count; i++) {
            turnSide(0);

            int[] temp = new int[sideLen];
            for (int j = 0; j < sideLen; j++)
                temp[j] = elements[1][j];

            for (int j = 1; j < 4; j++) {
                for (int k = 0; k < sideLen; k++) {
                    elements[j][k] = elements[j + 1][k];
                }
            }

            for (int j = 0; j < sideLen; j++)
                elements[4][j] = temp[j];
        }
    }

    private void moveDown(boolean reverse) {
        int count = (reverse ? 3 : 1);
        for (int i = 0; i < count; i++) {
            turnSide(5);

            int[] temp = new int[sideLen];
            for (int j = 0; j < sideLen; j++)
                temp[j] = elements[4][sideLen * (sideLen - 1) + j];

            for (int j = 4; j >= 2; j--) {
                for (int k = 0; k < sideLen; k++) {
                    elements[j][sideLen * (sideLen - 1) + k] = elements[j - 1][sideLen * (sideLen - 1) + k];
                }
            }

            for (int j = 0; j < sideLen; j++)
                elements[1][sideLen * (sideLen - 1) + j] = temp[j];
        }
    }

    private void moveLeft(boolean reverse) {
        int count = (reverse ? 3 : 1);
        for (int i = 0; i < count; i++) {
            turnSide(1);

            int[] temp = new int[sideLen];
            for (int j = 0; j < sideLen; j++)
                temp[j] = elements[5][j * sideLen];

            for (int j = 0; j < sideLen; j++)
                elements[5][j * sideLen] = elements[2][j * sideLen];

            for (int j = 0; j < sideLen; j++)
                elements[2][j * sideLen] = elements[0][j * sideLen];

            for (int j = 0; j < sideLen; j++)
                elements[0][sideLen * (sideLen - 1) - j * sideLen] = elements[4][j * sideLen + (sideLen - 1)];

            for (int j = 0; j < sideLen; j++)
                elements[4][j * sideLen + sideLen - 1] = temp[sideLen - 1 - j];
        }
    }

    private void moveRight(boolean reverse) {
        int count = (reverse ? 3 : 1);
        for (int i = 0; i < count; i++) {
            turnSide(3);

            int[] temp = new int[sideLen];
            for (int j = 0; j < sideLen; j++)
                temp[j] = elements[0][j * sideLen + sideLen - 1];

            for (int j = 0; j < sideLen; j++)
                elements[0][j * sideLen + sideLen - 1] = elements[2][j * sideLen + sideLen - 1];

            for (int j = 0; j < sideLen; j++)
                elements[2][j * sideLen + sideLen - 1] = elements[5][j * sideLen + sideLen - 1];

            for (int j = 0; j < sideLen; j++)
                elements[5][j * sideLen + sideLen - 1] = elements[4][sideLen * (sideLen - 1) - sideLen * j];

            for (int j = 0; j < sideLen; j++)
                elements[4][j * sideLen] = temp[sideLen - 1 - j];
        }
    }

    private void moveFront(boolean reverse) {
        int count = (reverse ? 3 : 1);
        for (int i = 0; i < count; i++) {
            turnSide(2);

            int[] temp = new int[sideLen];
            for (int j = 0; j < sideLen; j++)
                temp[j] = elements[0][sideLen * (sideLen - 1) + j];

            for (int j = 0; j < sideLen; j++)
                elements[0][sideLen * (sideLen - 1) + sideLen - 1 - j] = elements[1][j * sideLen + sideLen - 1];

            for (int j = 0; j < sideLen; j++)
                elements[1][j * sideLen + sideLen - 1] = elements[5][j];

            for (int j = 0; j < sideLen; j++)
                elements[5][j] = elements[3][sideLen * (sideLen - 1) - j * sideLen];

            for (int j = 0; j < sideLen; j++)
                elements[3][j * sideLen] = temp[j];
        }
    }

    private void moveBottom(boolean reverse) {
        int count = (reverse ? 3 : 1);
        for (int i = 0; i < count; i++) {
            turnSide(4);

            int[] temp = new int[sideLen];
            for (int j = 0; j < sideLen; j++)
                temp[j] = elements[0][j];

            for (int j = 0; j < sideLen; j++)
                elements[0][j] = elements[3][j * sideLen + sideLen - 1];

            for (int j = 0; j < sideLen; j++)
                elements[3][sideLen * (sideLen - 1) - j * sideLen + sideLen - 1] = elements[5][sideLen * (sideLen - 1) + j];

            for (int j = 0; j < sideLen; j++)
                elements[5][sideLen * (sideLen - 1) + j] = elements[1][j * sideLen];

            for (int j = 0; j < sideLen; j++)
                elements[1][j * sideLen] = temp[sideLen - 1 - j];
        }
    }

    public void move(String moves) {
        // Format: "{move0} {move1} ..."
        // Possible moves: U, D, L, R, F, B and their reverse versions (U', D' etc)

        String[] movesArr = moves.split(" ");

        for (String move : movesArr)
        {
            if (move.equalsIgnoreCase("U"))
                moveUp(false);
             else if (move.equalsIgnoreCase("D"))
                moveDown(false);
             else if (move.equalsIgnoreCase("L"))
                moveLeft(false);
             else if (move.equalsIgnoreCase("R"))
                moveRight(false);
             else if (move.equalsIgnoreCase("F"))
                moveFront(false);
             else if (move.equalsIgnoreCase("B"))
                moveBottom(false);
             else if (move.equalsIgnoreCase("U'"))
                moveUp(true);
             else if (move.equalsIgnoreCase("D'"))
                moveDown(true);
             else if (move.equalsIgnoreCase("L'"))
                moveLeft(true);
             else if (move.equalsIgnoreCase("R'"))
                moveRight(true);
             else if (move.equalsIgnoreCase("F'"))
                moveFront(true);
             else if (move.equalsIgnoreCase("B'"))
                moveBottom(true);
        }
    }
}
