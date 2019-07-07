import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

public class Step5 {

    public static HashMap<String, Integer> dict = new HashMap<String, Integer>();

    static int DICT_MAX = 230001;
    public static void main(String[]args)throws Exception {
//        loadDict();
        BufferedReader br = new BufferedReader(new FileReader("resource/6.csv"));
        PrintWriter pw = new PrintWriter(new File("resource/6m.csv"));
        String line = br.readLine();
        line = br.readLine();//pass header
        while(line !=null){
            int start = line.indexOf(",");
            try {
                String label = line.substring(0, start);
            }catch (StringIndexOutOfBoundsException e)
            {
                System.out.println(line);
            }
            String data = line.substring(0,line.length());
            int[] zero = new int[230001];
            for(int i=0;i<DICT_MAX;i++){
                zero[i]=0;
            }
            String []dataArray = data.split(",");
            List<Integer> list =new ArrayList<Integer>();
            for(String d : dataArray){
                int id  = Integer.parseInt(d);
                zero[id-1] = 1;
            }
            String res = array2string(zero);
            pw.println(res);
            pw.flush();
            line = br.readLine();
        }

        br.close();
        pw.close();


    }

    public static String array2string(int[] a){
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<a.length-1;i++){
            sb.append(a[i] +",");
        }
        sb.append(a[a.length-1]);
        return sb.toString();
    }

}
