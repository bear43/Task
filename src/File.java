import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class File
{
    private BufferedReader br;
    private PrintWriter pw;
    private static final String DEFAULT_IN = "input.txt";
    private static final String DEFAULT_OUT = "output.txt";
    public File() throws IOException
    {
        this(DEFAULT_IN, DEFAULT_OUT);
    }

    public File(String in, String out) throws IOException
    {
        br = new BufferedReader(new FileReader(in));
        pw = new PrintWriter(out);
    }

    public int[][] readMatrix(int m, int n) throws Exception
    {
        int[][] ret = new int[m][n];
        String[] str;
        for(int i = 0; i < m; i++)
        {
            str = br.readLine().split(" ");
            for (int j = 0; j < n; j++)
                ret[i][j] = Integer.parseInt(str[j]);
        }
        return ret;
    }

    public void closeReader() throws IOException
    {
        br.close();
    }

    public void closeWriter()
    {
        pw.close();
    }

    public BufferedReader getReader()
    {
        return br;
    }

    public PrintWriter getWriter()
    {
        return pw;
    }

    public void setReader(BufferedReader br)
    {
        this.br = br;
    }

    public void setWriter()
    {
        this.pw = pw;
    }
}
