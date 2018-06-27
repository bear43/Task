import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class t2
{
    public static void run() throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        PrintWriter pw = new PrintWriter("output.txt");
        String buffer = br.readLine();
        int n = Character.getNumericValue(buffer.charAt(0));
        int[] array = new int[n];
        buffer = br.readLine();
        while(buffer != null && !buffer.equals(""))
        {
            array[Character.getNumericValue(buffer.charAt(0))-1]++;
            array[Character.getNumericValue(buffer.charAt(2))-1]++;
            buffer = br.readLine();
        }
        for(int i : array)
            pw.printf("%d ", i);
        pw.flush();
        pw.close();
        br.close();
    }
}
