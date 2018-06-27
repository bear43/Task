import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class t4
{
    static int verticesCount;
    static int[][] weight;
    static int[][] t;
    public static void run() throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        PrintWriter pw = new PrintWriter("output.txt");
        verticesCount = Integer.parseInt(br.readLine());
        t = new int[verticesCount*verticesCount*verticesCount][4];
        weight = new int[verticesCount][verticesCount];
        String[] str;
        for(int i = 0; i < verticesCount; i++)
        {
            str = br.readLine().split(" ");
            for(int j = 0; j < verticesCount; j++)
                if(i != j)
                    weight[i][j] = Integer.parseInt(str[j]);
        }
        br.close();
        int counter = 0;
        for(int i = 0; i < verticesCount; i++)
        {
            for(int j = i; j < verticesCount; j++)
            {
                if(i != j)
                {
                    for(int k = 0; k < verticesCount; k++)
                    {
                        if(k != i && k != j)
                        {
                            t[counter] = new int[]{i, j, k, weight[i][j] + weight[j][k] + weight[k][i]};
                            counter++;
                        }
                    }
                }
            }
        }
        int index = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < counter; i++)
        {
            if(min > t[i][3])
            {
                index = i;
                min = t[i][3];
            }
        }
        pw.println(String.format("%d %d %d", ++t[index][0], ++t[index][1], ++t[index][2]));
        pw.flush();
        pw.close();
    }
}
