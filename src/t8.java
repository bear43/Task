import java.util.*;

class t8
{
    private static final byte TABLE_SIZE = 8;//8x8
    private static final byte LOW_CASE_ALPHABET_UNICODE_OFFSET = 10;
    private static int[] greenHorse = new int[2];
    private static int[] redHorse = new int[2];
    private static int[][] table = new int[TABLE_SIZE][TABLE_SIZE];
    private static int[][] greenCosts = new int[TABLE_SIZE][TABLE_SIZE];//Red horse's turns costs
    private static int[][] redCosts = new int[TABLE_SIZE][TABLE_SIZE];//Green horst's turns costs
    private static void fillCosts(Queue<Integer[]> queue, int[][] tableCosts)
    {
        Integer[] current = null;
        Integer[] root = queue.peek();
        if(root == null) return;
        tableCosts[root[0]][root[1]] = 0;
        int currentCellCost = 1;
        while(!queue.isEmpty())
        {
            root = queue.poll();
            for (t6.HorseTurns turn : t6.HorseTurns.values())
            {
                current = t6.makeVertexByTurn_Object(turn, table, TABLE_SIZE, root);
                if(current != null && tableCosts[current[0]][current[1]] == -1)
                {
                    tableCosts[current[0]][current[1]] = currentCellCost++;
                    queue.add(current);
                }
            }
        }
    }
    static void run() throws Exception
    {
        File file = new File();
        String[] in = file.getReader().readLine().split(" ");
        file.getReader().close();
        redHorse[0] = Character.getNumericValue(in[0].charAt(0))-LOW_CASE_ALPHABET_UNICODE_OFFSET;
        redHorse[1] = Character.digit(in[0].charAt(1), TABLE_SIZE+1)-1;
        greenHorse[0] = Character.getNumericValue(in[1].charAt(0))-LOW_CASE_ALPHABET_UNICODE_OFFSET;
        greenHorse[1] = Character.digit(in[1].charAt(1), TABLE_SIZE+1)-1;
        Queue<Integer[]> redQueue = new ArrayDeque<Integer[]>();
        Queue<Integer[]> greenQueue = new ArrayDeque<Integer[]>();
        redQueue.add(new Integer[] { redHorse[0], redHorse[1] });
        greenQueue.add(new Integer[] { greenHorse[0], greenHorse[1] });
        if(redHorse[0] == greenHorse[0] && redHorse[1] == greenHorse[1])
        {
            file.getWriter().println(0);
            file.getWriter().flush();
            file.getWriter().close();
            return;
        }
        for(int[] i : redCosts) Arrays.fill(i, -1);
        for(int[] i : greenCosts) Arrays.fill(i, -1);
        fillCosts(redQueue, redCosts);
        fillCosts(greenQueue, greenCosts);
        List<Integer> turnsToGo = new ArrayList<>();
        for(int i = 0; i < TABLE_SIZE; i++)
        {
            for(int j = 0; j < TABLE_SIZE; j++)
            {
                for(int k = 0; k < TABLE_SIZE; k++)
                {
                    for(int l = 0; l < TABLE_SIZE; l++)
                    {
                        if(redCosts[i][j] == greenCosts[k][l])
                            turnsToGo.add(redCosts[i][j]);
                    }
                }
            }
        }
        turnsToGo.remove((Integer)0);
        file.getWriter().println(turnsToGo.stream().min((a, b) -> {
            if(a < b) return -1;
            if(a.equals(b)) return 0;
            return 1;
        }).get().intValue());
        file.getWriter().flush();
        file.getWriter().close();
    }
}
