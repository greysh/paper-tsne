import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;

public class Step4 {
    public static HashMap<String, Integer> dict = new HashMap<String, Integer>();
    static String[] stopwords = {"“","”", "“", "/", "**", "（", "）", "(", ")", "《", "》", "？", "?", "。","-","\""};
    static String splitExpress = "；|，|；|、|:|\\,|\\:|\\;|\\.";
    public static void main(String[] args) throws Exception {
        loadDict();
        BufferedReader br = new BufferedReader(new FileReader("resource/3.txt"));
        PrintWriter pw = new PrintWriter(new File("resource/6.txt"));
        String line = br.readLine();
        int i = 1;
        while (line != null) {
            System.out.println("\r\n"+line);
            line = line.trim();
//            ，；:\,\:\
            String[] results = line.split(splitExpress);

            StringBuffer sb = new StringBuffer();
            for (String result : results) {
                System.out.println(result + "=>Line:" + i);
                for (String stopword : stopwords) {
                    result = result.replace(stopword, "");
                }
                result = result.trim();

                //==
                if(result.contains(" ")){
                    String[] a = line.split(" ");
                    if(isChinese(a[0])) {

                        String[] cresults = result.split(" ");

                        for (String cresult : cresults) {
                            System.out.println(cresult + "=>Line:" + i);
                            for (String stopword : stopwords) {
                                cresult = cresult.replace(stopword, "");
                            }
                            cresult = cresult.trim();

                            System.out.println(cresult);
                            Integer key = dict.get(cresult);
                            if (key == null) {
                                if(!cresult.equals("")) {
                                    sb.append("[" + cresult + "],");
                                }
                            } else {
                                sb.append(key + ",");
                            }
                        }
                    }
                    //==
                }else {
                    System.out.println(result);
                    Integer key = dict.get(result);
                    if (key == null) {
                        if(!result.equals("")) {
                            sb.append("[" + result + "],");
                        }
                    } else {
                        sb.append(key + ",");
                    }
                }
            }

            System.out.println(i + ";"+sb.toString());
            pw.println(sb.toString());
            pw.flush();
            i++;
            line = br.readLine();

        }
        br.close();
        pw.close();
    }

    public static void loadDict() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("resource/5.txt"));
        String line = br.readLine();
        int i = 1;
        while (line != null) {
            dict.put(line, i);
            i++;
            line = br.readLine();
        }
        br.close();
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
