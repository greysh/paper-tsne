import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class Step3 {
    public static HashMap<String, Integer> dict = new HashMap<String, Integer>();
    static String[] stopwords = {"“","”", "“", "/", "**", "（", "）", "(", ")", "《", "》", "？", "?", "。","-","\""};
    static String splitExpress = "；|，|；|、|:|\\,|\\:|\\;|\\.";
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("resource/3.txt"));
        PrintWriter pw = new PrintWriter(new File("resource/4.txt"));
        String line = br.readLine();
        br.readLine();// PASS HEADER
        int i = 2;
        while (line != null) {
            System.out.println("\r\n"+line);
            line = line.trim();
            String[] results = line.split(splitExpress);
            for (String result : results) {
                System.out.println("Line:" + i + " => " + result  );
                for (String stopword : stopwords) {
                    result = result.replace(stopword, "");
                }
                result = result.trim();
                if(result.contains(" ")){
                    String[] a = line.split(" ");
                    if(isChinese(a[0])) {
                        String[] cresults = result.split(" ");
                        for (String cresult : cresults) {
                            System.out.println("Line:" + i + " => " + cresult  );
                            for (String stopword : stopwords) {
                                cresult = cresult.replace(stopword, "");
                            }
                            cresult = cresult.trim();
                            System.out.println(cresult);
                            Integer key = dict.get(cresult);
                            if (key == null) {
                                dict.put(cresult,1);
                            } else {
                                key++;
                                dict.put(cresult,key);
                            }
                        }
                    }
                    //==
                }else {
                    System.out.println(result);
                    Integer key = dict.get(result);
                    if (key == null) {
                        dict.put(result,1);
                    } else {
                        key++;
                        dict.put(result,key);
                    }
                }
            }

            i++;
            line = br.readLine();

        }

        for (Map.Entry<String, Integer> entry : dict.entrySet()) {
            String key = entry.getKey();
            int count = entry.getValue();
            pw.println(key + ";" + count);
        }
        pw.flush();

        br.close();
        System.out.println("DONE");
    }




    public static boolean isChinese(char c) {
        return c >= 0x4E00 && c <= 0x9FA5;// 根据字节码判断
    }
    // 判断一个字符串是否含有中文
    public static boolean isChinese(String str) {
        if (str == null) return false;
        for (char c : str.toCharArray()) {
            if (isChinese(c)) return true;// 有一个中文字符就返回
        }
        return false;
    }
}
