import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Step7 {


    static int DICT_MAX = 20000;

    public static void main(String[] args) throws Exception {
        int[] files = {11,12};
        for(int file : files) {
            load(file);
        }
    }

    public static void load(int file) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("resource/"+file+".csv"));
        PrintWriter pw = new PrintWriter(new File("resource/"+file+"t.csv"));
        String line = br.readLine();

        line = br.readLine();//pass header


//        String headerRes = header2string();
//        pw.println("label," +headerRes );

        while (line != null) {

            String factor = line.substring(0,4);
            String data = line.substring(5, line.length());
            int[] zero = new int[DICT_MAX];
            for (int i = 0; i < DICT_MAX; i++) {
                zero[i] = 0;
            }

            String[] dataArray = data.split(",");
            List<Integer> list = new ArrayList<Integer>();
            for (String d : dataArray) {
                int id = Integer.parseInt(d);
                try {
                    zero[id - 1] = 1;
                }catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("id:" + id);
                    System.out.println("data:" + data);
                    System.exit(0);

                }
            }
            String res = array2string(zero);
            pw.println(factor + ","+res);
            pw.flush();
            line = br.readLine();
        }

        br.close();
        pw.close();

    }

    public static String array2string(int[] a) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < a.length-1 ; i++) {
            sb.append(a[i] + ",");
        }
        sb.append(a[a.length-1]);
        return sb.toString();
    }

    public static String header2string() {
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i < DICT_MAX ; i++) {
            sb.append( "d"+i + ",");
        }
        sb.append(DICT_MAX);

        return sb.toString();
    }
}
