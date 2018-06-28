class t11
{
    static void run() throws Exception
    {
        File file = new File();
        int verticesCount = Integer.parseInt(file.getReader().readLine());
        int[][] table = file.readMatrix(verticesCount, verticesCount);
        file.getReader().close();
        for (int i = 0; i < verticesCount; i++)
            for (int j = 0; j < verticesCount; j++)
                if (i != j && table[i][j] == 0)
                    table[i][j] = Integer.MAX_VALUE;
        t9.makeFloydEx(table, verticesCount);
        int[][] out = new int[verticesCount][verticesCount];
        for (int i = 0; i < verticesCount; i++)
            for (int j = 0; j < verticesCount; j++)
            {
                if (table[i][j] == Integer.MAX_VALUE)
                    out[i][j] = 0;
                else
                {
                    out[i][j] = 1;
                    for (int k = 0; k < verticesCount; k++)
                        if (table[k][k] < 0 && table[i][k] < Integer.MAX_VALUE && table[k][j] < Integer.MAX_VALUE)
                            out[i][k] = out[i][j] = out[k][j] = 2;
                }
            }
        for (int[] a : out)
        {
            for (int i : a)
                file.getWriter().print(i + " ");
            file.getWriter().println();
        }
        file.getWriter().flush();
        file.getWriter().close();
    }
}
