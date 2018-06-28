class t9
{
    static void makeFloyd(int[][] table, int verticesCount)
    {
        for (int k = 0; k < verticesCount; k++)
        {
            for (int i = 0; i < verticesCount; i++)
            {
                for (int j = 0; j < verticesCount; j++)
                {
                    if (i != j && ((table[i][k] < Integer.MAX_VALUE && table[k][j] < Integer.MAX_VALUE && table[i][k] + table[k][j] < table[i][j]) || table[i][j] == 0))
                        table[i][j] = table[i][k] + table[k][j];
                }
            }
        }
    }
    static void makeFloydEx(int[][] table, int verticesCount)
    {
        for(int k = 0; k < verticesCount; k++)
            for(int i = 0; i < verticesCount; i++)
                for(int j = 0; j < verticesCount; j++)
                    if(table[i][k] < Integer.MAX_VALUE && table[k][j] < Integer.MAX_VALUE)
                        if (table[i][k] + table[k][j] < table[i][j]) table[i][j] = table[i][k] + table[k][j];
    }
    static void run() throws Exception
    {
        File file = new File();
        int verticesCount = Integer.parseInt(file.getReader().readLine());
        int[][] table;
        table = file.readMatrix(verticesCount, verticesCount);
        file.getReader().close();
        makeFloyd(table, verticesCount);
        for (int i = 0; i < verticesCount; i++)
        {
            for (int j = 0; j < verticesCount; j++)
                file.getWriter().print(table[i][j] + " ");
            file.getWriter().println();
        }
        file.getWriter().flush();
        file.getWriter().close();
    }
}