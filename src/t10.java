import java.util.Arrays;

class t10
{
    static void run() throws Exception
    {
        File file = new File();
        int verticesCount = Integer.parseInt(file.getReader().readLine());
        int[][] table = file.readMatrix(verticesCount, verticesCount);
        for(int i = 0; i < verticesCount; i++)
            for(int j = 0; j < verticesCount; j++)
                if(table[i][j] == -1)
                    table[i][j] = Integer.MAX_VALUE;
        t9.makeFloyd(table, verticesCount);
        file.getWriter().flush();
        file.getWriter().close();
    }
}
