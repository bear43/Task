import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class t7
{
    static void run() throws Exception
    {
        int width, height;
        int[][] table;
        String buffer;
        String[] splitedBuffer;
        File file = new File();
        buffer = file.getReader().readLine();
        splitedBuffer = buffer.split(" ");
        width = Integer.parseInt(splitedBuffer[1]);
        height = Integer.parseInt(splitedBuffer[0]);
        table = file.readMatrix(height, width);
        file.closeReader();
        List<Integer[]> vertices = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        for(int x = 0; x < height; x++)
            for (int y = 0; y < width; y++)
                if(table[x][y] == 1)
                    vertices.add(new Integer[]{x, y});
        for(int x = 0; x < height; x++)
        {
            for (int y = 0; y < width; y++)
            {
                if(table[x][y] == 0)
                {
                    for(Integer[] i : vertices)
                        tmp.add(Math.abs(x-i[0])+Math.abs(y-i[1]));
                    table[x][y] = tmp.stream().min(Comparator.comparingInt(Integer::intValue)).get();
                    tmp.clear();
                }
            }
        }
        for(Integer[] i : vertices)
            table[i[0]][i[1]] = 0;
        for(int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
                file.getWriter().print(table[i][j] + " ");
            file.getWriter().println();
        }
        file.closeWriter();
    }
}
