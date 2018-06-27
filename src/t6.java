import java.util.*;

public class t6
{
    private static File file;
    private static final int AXES_COUNT = 2;
    private static int[] start = new int[AXES_COUNT];
    static enum HorseTurns
    {
        FORWARD_RIGHT,
        FORWARD_LEFT,
        BACK_RIGHT,
        BACK_LEFT,
        RIGHT_FORWARD,
        RIGHT_BACK,
        LEFT_FORWARD,
        LEFT_BACK
    }

    private static int[] getNewCoord(HorseTurns turn, int[] startPosition)
    {
        int[] newCoords = new int[2];
        switch (turn)
        {
            case FORWARD_RIGHT:
                newCoords[0] = startPosition[0] + 1;
                newCoords[1] = startPosition[1] - 2;
                break;
            case FORWARD_LEFT:
                newCoords[0] = startPosition[0] - 1;
                newCoords[1] = startPosition[1] - 2;
                break;
            case BACK_RIGHT:
                newCoords[0] = startPosition[0] + 1;
                newCoords[1] = startPosition[1] + 2;
                break;
            case BACK_LEFT:
                newCoords[0] = startPosition[0] - 1;
                newCoords[1] = startPosition[1] + 2;
                break;
            case RIGHT_FORWARD:
                newCoords[0] = startPosition[0] + 2;
                newCoords[1] = startPosition[1] - 1;
                break;
            case RIGHT_BACK:
                newCoords[0] = startPosition[0] + 2;
                newCoords[1] = startPosition[1] + 1;
                break;
            case LEFT_FORWARD:
                newCoords[0] = startPosition[0] - 2;
                newCoords[1] = startPosition[1] - 1;
                break;
            case LEFT_BACK:
                newCoords[0] = startPosition[0] - 2;
                newCoords[1] = startPosition[1] + 1;
                break;
        }
        return newCoords;
    }

    private static int[] makeVertexByTurn(HorseTurns turn, int[][] pitch, int pitchSize, int[] startPoint)
    {
        int[] newCoords = getNewCoord(turn, startPoint);
        if(newCoords[0] >= 0 && newCoords[0] < pitchSize && newCoords[1] >= 0 && newCoords[1] < pitchSize)
        {
            return newCoords;
        }
        return null;
    }

    static Integer[] makeVertexByTurn_Object(HorseTurns turn, int[][] pitch, int pitchSize, Integer[] startPoint)
    {
        int[] newCoords = makeVertexByTurn(turn, pitch, pitchSize, new int[] { startPoint[0], startPoint[1] });
        return newCoords == null ? null : new Integer[] { newCoords[0], newCoords[1] };
    }

    private static void clear(int[][] arr, int length)
    {
        for(int i = 0; i < length; i++)
            for(int j = 0; j < length; j++)
                arr[i][j] = 0;
    }

    private static void remove(List<Integer[]> list, int[] coord)
    {
        boolean isEqual = true;
        for(int i = 0; i < list.size(); i++)
        {
            isEqual = true;
            for(int j = 0; j < AXES_COUNT; j++)
                if(coord[j] != list.get(i)[j])
                {
                    isEqual = false;
                    break;
                }
            if(isEqual)
                list.remove(i);
        }
    }

    /**
     *
     * @param pitch Pitch to create vertices.
     * @param point Start point. From this point creating vertices.
     * @param pitchSize (pitchSize)x(pitchSize) pitch size.
     */
    private static void createWay(int[][] pitch, int pitchSize, int[] point, int[] endPoint, List<List<Integer[]>> ways, List<Integer[]> turnsHistory)
    {
        int[] coord;
        turnsHistory.add(Arrays.stream(point).boxed().toArray(Integer[]::new));
        pitch[point[0]][point[1]] = 1;
        if(point[0] == endPoint[0] && point[1] == endPoint[1])
        {
            pitch[point[0]][point[1]] = 0;
            ways.add(new ArrayList<>(turnsHistory));
            remove(turnsHistory, point);
            return;
        }
        for(HorseTurns turn : HorseTurns.values())
        {
            coord = makeVertexByTurn(turn, pitch, pitchSize, point);
            if(coord != null && pitch[coord[0]][coord[1]] != 1)
                createWay(pitch, pitchSize, new int[] {coord[0], coord[1]}, endPoint, ways, turnsHistory);
        }
        pitch[point[0]][point[1]] = 0;
        remove(turnsHistory, point);
    }

    private static void fillPoint(int[] point, String[] param)
    {
        for(int i = 0; i < AXES_COUNT; i++)
            point[i] = Integer.parseInt(param[i])-1;
    }


    public static void run() throws Exception
    {
        file = new File();
        int pitchSize = Integer.parseInt(file.getReader().readLine());//Pitch size NxN
        int[][] pitch = new int[pitchSize][pitchSize];//Generate pitch with NxN size
        int[] fromPoint = new int[AXES_COUNT];//Start point (X, Y)
        int[] toPoint = new int[AXES_COUNT];//End point (X, Y)
        fillPoint(fromPoint, file.getReader().readLine().split(" "));
        fillPoint(toPoint, file.getReader().readLine().split(" "));
        start[0] = fromPoint[0];
        start[1] = fromPoint[1];
        pitch[start[0]][start[1]] = 1;
        file.closeReader();
        List<List<Integer[]>> ways = new ArrayList<List<Integer[]>>();
        createWay(pitch, pitchSize, fromPoint, toPoint, ways, new ArrayList<Integer[]>());
        int index = -1;
        int min = Integer.MAX_VALUE;
        int curr;
        for(int i = 0; i < ways.size(); i++)
        {
            curr = ways.get(i).size();
            if(min > curr)
            {
                min = curr;
                index = i;
            }
        }
        file.getWriter().println(min-1);
        List<Integer[]> shortestWay = ways.get(index);
        for(Integer[] i : ways.get(index))
            file.getWriter().println((i[0]+1) + " " + (i[1]+1));
        file.closeWriter();
    }
}
