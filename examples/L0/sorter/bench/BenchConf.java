package privmem.sorter.bench;

public class BenchConf {
    public static final int FRAMES = 1 * 1000;
    public static final int SIZE = 1 * 1000;

    public static long          PERIOD              = 50;
    public static long          MISSION_SCOPE_SIZE           = 5*1000*1000;// v 245*1000   250     //5*1000*1000;
    public static long          HANDLER_SCOPE_SIZE           =5*1000*1000;     //63  50     //5*1000*1000;

    public static int   recordedRuns = 0;

    private static long[]    traceTime                   = new long[SIZE];

    static public long[]           timesBefore  = new long[FRAMES];;
    static public long[]           timesAfter = new long[FRAMES];;

    //public BenchConf() {
    //    timesBefore = new long[FRAMES];
    //    timesAfter = new long[FRAMES];
    //}


    public static void set(long time, int index) {
        traceTime[index] = time;
    }

    public static void dump() {
        /*
        String space = " ";

        System.out.println("=====DETECTOR-STATS-START-BELOW====");
        for (int i = 0; i < recordedRuns; i++) {
            System.out.print(timesBefore[i]);
            System.out.print(space);
            System.out.print(timesAfter[i]);
            System.out.print(space);
            System.out.println(i);
        }

        System.out.println("=====DETECTOR-STATS-END-ABOVE====");

        */
    }




}
