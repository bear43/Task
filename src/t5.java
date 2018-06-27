import java.util.*;

public class t5
{
    private static int[] mark;
    private static File file;
    private static boolean canGoToFrom(int[][] matrix, int from, int to)
    {
        return matrix[from][to] != 0;
    }
    private static int indexOf(int[] array, int i)
    {
        for(int a = 0; a < array.length; a++)
            if(array[a] == i)
                return a;
        return -1;
    }
    private static void add(int[] array, int value)
    {
        int freeIndex = indexOf(array, -1);
        if(freeIndex != -1)
            array[freeIndex] = value;
    }
    private static final int END_SEQUENCE = -1;
    private static int[] variantsToGoFrom(int[][] matrix, int from, int max)
    {
        int[] ret = new int[max];
        Arrays.fill(ret, -1);
        for(int i = 0, j = 0; i < max; i++)
            if(i != from && canGoToFrom(matrix, from, i))
            {
                ret[j] = i;
                j++;
            }
        int realsize = indexOf(ret, -1);
        if(realsize != -1)
        {
            int[] real = new int[realsize];
            System.arraycopy(ret, 0, real, 0, realsize);
            return real;
        }
        return new int[0];
    }
    private static void remove(int[] array, int value)
    {
        for(int i = 0; i < array.length; i++)
            if(array[i] == value)
                array[i] = END_SEQUENCE;
    }
    private static List<Integer> getMinWay(int[][] matrix, int from, int to, int max, List<Integer> allWay)
    {
        ArrayList<Integer> all = new ArrayList<>();
        allWay.add(from);
        if(from == to)
        {
            ArrayList<Integer> list = new ArrayList<Integer>(allWay);
            list.add(END_SEQUENCE);
            allWay.remove((Integer)from);
            return list;
        }
        add(mark, from);
        for(int vertex : variantsToGoFrom(matrix, from, max))
        {
            if(indexOf(mark, vertex) == -1)
            {
                all.addAll(getMinWay(matrix, vertex, to, max, allWay));
                remove(mark, from);
                remove(mark, vertex);
                allWay.remove((Integer)from);
            }
        }
        remove(mark, from);
        return all;
    }
    private static List<Object[]> getWays(List<Integer> rawWays)
    {
        List<Integer> sharedList = new ArrayList<>();
        List<Object[]> retList = new ArrayList<>();
        for(int current : rawWays)
        {
            if(current != END_SEQUENCE)
            {
                sharedList.add(current);
            }
            else
            {
                retList.add(sharedList.toArray());
                sharedList.clear();
            }
        }
        return retList;
    }
    static void run() throws Exception
    {
        file = new File();
        int verticesCount = Integer.parseInt(file.getReader().readLine());
        int[][] matrix = file.readMatrix(verticesCount, verticesCount);
        String[] rowData = file.getReader().readLine().split(" ");
        int fromVertex = Integer.parseInt(rowData[0]) - 1;
        int toVertex = Integer.parseInt(rowData[1]) - 1;
        file.closeReader();
        mark = new int[verticesCount];
        Arrays.fill(mark, -1);
        //add(mark, fromVertex);
        List<Integer> turns = getMinWay(matrix, fromVertex, toVertex, verticesCount, new ArrayList<Integer>());
        List<Object[]> ways = getWays(turns);
        int min = Integer.MAX_VALUE;
        int index = 0;
        int c = 0;
        for(Object[] way : ways)
        {
            if(way.length < min)
            {
                min = way.length;
                index = c;
            }
            c++;
        }
        file.getWriter().println(min-1);
        for(Object i : ways.get(index))
            file.getWriter().print(((int)i+1) + " ");
        file.getWriter().flush();
        file.closeWriter();
    }
}
