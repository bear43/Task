import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class t1
{
    public static void run() throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        PrintWriter pw = new PrintWriter("output.txt");
        int citiesCount = 0;
        citiesCount = Integer.parseInt(br.readLine());
        if(citiesCount == 0) pw.println(0);
        else
        {
            int roadCounter = 0;
            String currentLine = null;
            for(int i = 0; i < citiesCount; i++)
            {
                currentLine = br.readLine();
                for(int j = i; j < citiesCount; j++)
                    if(currentLine.charAt(2*j) == '1')
                        roadCounter++;
            }
            pw.println(roadCounter);
        }
        pw.flush();
        pw.close();
        br.close();
    }
}
