import java.util.List;

public class t8
{
    private static final byte TABLE_SIZE = 8;//8x8
    private static final byte LOW_CASE_ALPHABET_UNICODE_OFFSET = 10;
    private static int[] greenHorse = new int[2];
    private static int[] redHorse = new int[2];
    private static char toChar(int code)
    {
        return Character.toChars(code + 1)[0];
    }
    private static int[][] table = new int[TABLE_SIZE][TABLE_SIZE];
    private static boolean blocked = false;
    private static boolean coordsEqual(int[] first, int[] second)
    {
        for(int i = 0; i < first.length; i++)
            if(first[i] != second[i])
                return false;
        return true;
    }
    private static int findCommonPoint(int[] redCoords, int[] greenCoords, List<Integer> way, int counter)
    {
        counter++;
        if(redCoords == null || greenCoords == null || table[redCoords[0]][redCoords[1]] == 1 || table[greenCoords[0]][greenCoords[1]] == 1)
        {
            counter--;
            return 0;
        }
        int waysCounter = 1;
        if(coordsEqual(redCoords, greenCoords))
        {
            table[redCoords[0]][redCoords[1]] = 1;
            table[greenCoords[0]][greenCoords[1]] = 1;
            counter--;
            return 1;
        }
        table[redCoords[0]][redCoords[1]] = 1;
        table[greenCoords[0]][greenCoords[1]] = 1;
        int tmp;
        int min = Integer.MAX_VALUE;
        for(t6.HorseTurns dirr : t6.HorseTurns.values())
        {
            for (t6.HorseTurns dir : t6.HorseTurns.values())
            {
                tmp = 0;
                tmp += findCommonPoint(t6.makeVertexByTurn(dir, table, TABLE_SIZE, redCoords), t6.makeVertexByTurn(dirr, table, TABLE_SIZE, greenCoords), way, counter);
                if (tmp <= min)
                    min = tmp;
            }
        }
        table[redCoords[0]][redCoords[1]] = 0;
        table[greenCoords[0]][greenCoords[1]] = 0;
        counter--;
        return waysCounter + min;
    }
    static void run() throws Exception
    {
        File file = new File();
        String[] in = file.getReader().readLine().split(" ");
        redHorse[0] = Character.getNumericValue(in[0].charAt(0))-LOW_CASE_ALPHABET_UNICODE_OFFSET;
        redHorse[1] = Character.digit(in[0].charAt(1), TABLE_SIZE+1)-1;
        greenHorse[0] = Character.getNumericValue(in[1].charAt(0))-LOW_CASE_ALPHABET_UNICODE_OFFSET;
        greenHorse[1] = Character.digit(in[1].charAt(1), TABLE_SIZE+1)-1;
        int min = findCommonPoint(redHorse, greenHorse, null, 0);
    }
}
