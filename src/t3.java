import java.io.*;

public class t3
{
    public static void run() throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        PrintWriter pw = new PrintWriter("output.txt");
        int invalidBoundCount = 0;
        int verticesCount = Integer.parseInt(br.readLine());
        //int[][] weight = new int[verticesCount][verticesCount];
        int[] vertexColor = new int[verticesCount];
        int skipCount = (2*verticesCount*verticesCount) + (verticesCount > 1 ? verticesCount : 0);
        br.skip(skipCount);
        String[] str = br.readLine().split(" ");
        for(int i = 0; i < verticesCount; i++)
            vertexColor[i] = Integer.parseInt(str[i]);
        br.close();
        br = new BufferedReader(new FileReader("input.txt"));
        br.readLine();
        for(int i = 0; i < verticesCount; i++)
        {
            str = br.readLine().split(" ");
            for(int j = i; j < verticesCount; j++)
                if(str[j].equals("1") && vertexColor[i] != vertexColor[j])
                    invalidBoundCount++;
        }
        br.close();
        pw.println(invalidBoundCount);
        pw.flush();
        pw.close();
    }
}
