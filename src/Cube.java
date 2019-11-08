public class Cube {
    int sideLen;
    int elemsOnSide;
    int[][] elements;

    public enum MOVES {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        FRONT,
        BACK,
        // reverse moves
        RUP,
        RDOWN,
        RLEFT,
        RRIGHT,
        RFRONT,
        RBACK,
    }

    private enum COLORS {
        WHITE,
        ORANGE,
        GREEN,
        RED,
        BLUE,
        YELLOW
    }

    private char getColorChar(int i)
    {
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

    public Cube(int sideLen)
    {
        this.sideLen = sideLen;
        this.elemsOnSide = sideLen * sideLen;

        // 6 sides
        this.elements = new int[6][elemsOnSide];

        for (int i = 0; i < 6; i++)
            for (int j = 0; j < elemsOnSide; j++)
                this.elements[i][j] = i;
    }

    public void print()
    {
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
        for (int i = 0; i < sideLen; i++)
        {
            for (int j = 0; j < sideLen; j++)
                System.out.print("  ");
            System.out.print("   ");

            for (int j = 0; j < sideLen; j++)
            {
                int elem = elements[0][i * sideLen + j];
                System.out.print(" " + getColorChar(elem));
            }
            System.out.println();
        }
        System.out.println();

        // 2, 3, 4, 5th sides
        for (int i = 0; i < sideLen; i++)
        {
            for (int j = 1; j < 5; j++)
            {
                for (int k = 0; k < sideLen; k++)
                {
                    int elem = elements[j][i * sideLen + k];
                    System.out.print(" " + getColorChar(elem));
                }
                System.out.print("   ");
            }
            System.out.println();
        }
        System.out.println();

        // 6th side
        for (int i = 0; i < sideLen; i++)
        {
            for (int j = 0; j < sideLen; j++)
                System.out.print("  ");
            System.out.print("   ");

            for (int j = 0; j < sideLen; j++)
            {
                int elem = elements[5][i * sideLen + j];
                System.out.print(" " + getColorChar(elem));
            }
            System.out.println();
        }

        System.out.println();
    }

    /*
    private void turnSide(int side)
    {
            int[] rotatingSide = new int[elemsOnSide];
            for (int j = 0; j < sideLen; j++)
                for (int k = 0; k < sideLen; k++)
                    rotatingSide[j * sideLen + k] = elements[side][j * sideLen + k];

            for (int j = 0; j < sideLen; j++)
                elements[side][j] = rotatingSide[sideLen * (sideLen - 1) - sideLen * j];

            for (int j = 0; j < sideLen; j++)
                elements[side][sideLen * j] = rotatingSide[sideLen * (sideLen - 1) + j];

            for (int j = 0; j < sideLen; j++)
                elements[side][sideLen * (sideLen - 1) + j] = rotatingSide[sideLen * (sideLen - 1) - sideLen * j + sideLen - 1];

            for (int j = 0; j < sideLen; j++)
                elements[side][sideLen * j + sideLen - 1] = rotatingSide[j];
    }
    */

    private void turnSide(int side)
    {
        int[] rotatingSide = new int[elemsOnSide];
        for (int i = 0; i < sideLen; i++)
            for (int j = 0; j < sideLen; j++)
                rotatingSide[i * sideLen + j] = elements[side][i * sideLen + j];

        for (int i = 0; i < sideLen; i++)
            for (int j = 0; j < sideLen; j++)
                elements[side][i * sideLen + j] = rotatingSide[(sideLen - j - 1) * sideLen + i];

    }

    private void move_UP(boolean reverse)
    {
        int count = (reverse ? 3 : 1);
        for (int i = 0; i < count; i++)
        {
            turnSide(0);

            int[] temp = new int[sideLen];
            for (int j = 0; j < sideLen; j++)
                temp[j] = elements[1][j];

            for (int j = 1; j < 4; j++)
            {
                for (int k = 0; k < sideLen; k++)
                {
                    elements[j][k] = elements[j + 1][k];
                }
            }

            for (int j = 0; j < sideLen; j++)
                elements[4][j] = temp[j];
        }
    }

    private void move_DOWN(boolean reverse)
    {
        int count = (reverse ? 3 : 1);
        for (int i = 0; i < count; i++)
        {
            turnSide(5);

            int[] temp = new int[sideLen];
            for (int j = 0; j < sideLen; j++)
                temp[j] = elements[4][sideLen * (sideLen - 1) + j];

            for (int j = 4; j >= 2; j--)
            {
                for (int k = 0; k < sideLen; k++)
                {
                    elements[j][sideLen * (sideLen - 1) + k] = elements[j - 1][sideLen * (sideLen - 1) + k];
                }
            }

            for (int j = 0; j < sideLen; j++)
                elements[1][sideLen * (sideLen - 1) + j] = temp[j];
        }
    }

    private void move_LEFT(boolean reverse)
    {
        int count = (reverse ? 3 : 1);
        for (int i = 0; i < count; i++)
        {
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

    private void move_RIGHT(boolean reverse)
    {
        int count = (reverse ? 3 : 1);
        for (int i = 0; i < count; i++)
        {
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

    private void move_FRONT(boolean reverse)
    {
        int count = (reverse ? 3 : 1);
        for (int i = 0; i < count; i++)
        {
            turnSide(2);

            int[] temp = new int[sideLen];
            for (int j = 0; j < sideLen; j++)
                temp[j] = elements[0][sideLen * (sideLen - 1) + j];

            for (int j = 0; j < sideLen; j++)
                elements[0][sideLen * (sideLen - 1) + j] = elements[1][j * sideLen + sideLen - 1];

            for (int j = 0; j < sideLen; j++)
                elements[1][j * sideLen + sideLen - 1] = elements[5][j];

            for (int j = 0; j < sideLen; j++)
                elements[5][j] = elements[3][j * sideLen];

            for (int j = 0; j < sideLen; j++)
                elements[3][j * sideLen] = temp[j];
        }
    }

    private void move_BACK(boolean reverse)
    {
        int count = (reverse ? 3 : 1);
        for (int i = 0; i < count; i++)
        {
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

    public void move(MOVES direction)
    {
        switch (direction)
        {
            case UP:
                move_UP(false);
                break;
            case DOWN:
                move_DOWN(false);
                break;
            case LEFT:
                move_LEFT(false);
                break;
            case RIGHT:
                move_RIGHT(false);
                break;
            case FRONT:
                move_FRONT(false);
                break;
            case BACK:
                move_BACK(false);
                break;
            case RUP:
                move_UP(true);
                break;
            case RDOWN:
                move_DOWN(true);
                break;
            case RLEFT:
                move_LEFT(true);
                break;
            case RRIGHT:
                move_RIGHT(true);
                break;
            case RFRONT:
                move_FRONT(true);
                break;
            case RBACK:
                move_BACK(true);
                break;
        }

    }
}
