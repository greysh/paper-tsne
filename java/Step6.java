import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Step6 {

    public static HashMap<String, Integer> dict = new HashMap<String, Integer>();


    public static void main(String[] args) throws Exception {
        loadDict();
        BufferedReader br = new BufferedReader(new FileReader("resource/8.csv"));
        PrintWriter pw = new PrintWriter(new File("resource/8m.csv"));
        String line = br.readLine();
        line = br.readLine();//pass header
        int lineCount = 0;
        while (line != null) {
            int start = 13;
            lineCount++;
            if (line.length() < 13) {
                System.out.println(lineCount + "=>" + line);
                line = br.readLine();
                continue;
            }
            String data = line.substring(start, line.length());
            String[] dataArray = data.split(",");
            boolean isTop5000 = false;
            L:
            for (String d : dataArray) {
                if (dict.get(d) != null) {
                    isTop5000 = true;
                    break L;
                }
            }

            if (isTop5000) {
                String prefix = line.substring(0, 12);
                StringBuffer sb = new StringBuffer();
                List<Integer> list = new ArrayList<Integer>();
                for (String d : dataArray) {
                    if (!d.trim().equals("\"") ) {
                        int value = Integer.parseInt(d);
                        if(value <= 20000) {
                            list.add(value);
                        }
                    }
                }
                Collections.sort(list);
                for(int i=0;i<list.size();i++) {
                    sb.append(list.get(i) + ",");
                }
                pw.println(prefix + sb.toString());
                pw.flush();
            }

            line = br.readLine();
        }

        br.close();
        pw.close();
    }


    public static void loadDict() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("resource/dict-20000.txt"));
        String line = br.readLine();
        int i = 1;
        while (line != null) {
            dict.put(line, i);
            i++;
            line = br.readLine();
        }
        br.close();
    }

    public static String array2string(int[] a) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < a.length - 1; i++) {
            sb.append(a[i] + ",");
        }
        sb.append(a[a.length - 1]);
        return sb.toString();
    }

}
