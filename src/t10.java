import java.util.Arrays;

class t10
{
    static void run() throws Exception
    {
        File file = new File();
        int verticesCount = Integer.parseInt(file.getReader().readLine());
        int[][] table = file.readMatrix(verticesCount, verticesCount);
        file.getReader().close();
        for(int i = 0; i < verticesCount; i++)
            for(int j = 0; j < verticesCount; j++)
                if(table[i][j] == -1)
                    table[i][j] = Integer.MAX_VALUE;
        t9.makeFloyd(table, verticesCount);
        int max = Integer.MIN_VALUE;
        for (int[] i : table)
            for(int j : i)
                if(j > max)
                    max = j;
        file.getWriter().println(max);
        file.getWriter().flush();
        file.getWriter().close();
    }
}
