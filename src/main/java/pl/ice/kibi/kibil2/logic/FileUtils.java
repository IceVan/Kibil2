package pl.ice.kibi.kibil2.logic;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    private static final char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String readFile(String file) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuffer out = new StringBuffer();
        String line = null;
        while ((line = br.readLine()) != null) {
            out.append(line);
        }

        br.close();
        return out.toString();
    }

    public static String readFileWhenReady(String file)  {

        BufferedReader br = null;
        boolean retry = true;

        while(retry) {
            try {
                br = new BufferedReader(new FileReader(file));
                StringBuffer out = new StringBuffer();
                String line = null;
                while ((line = br.readLine()) != null) {
                    out.append(line);
                }

                br.close();
                return out.toString();
            } catch (FileNotFoundException e) {
                closeStream(br);
            } catch (IOException e)
            {
                e.printStackTrace();
                closeStream(br);
            }
        }
        return null;
    }

    public static String readFromInputStream(InputStream inputStream)
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        StringBuffer out = new StringBuffer();
        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                out.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            closeStream(inputStream);
            closeStream(br);
        }

        return out.toString();
    }

    public static List<String> getFileAsArrayList(String file) throws IOException {
        List<String> listToRet = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = null;
        while ((line = br.readLine()) != null) {
            listToRet.add(line);
        }

        return listToRet;
    }

    @org.jetbrains.annotations.NotNull
    public static String generateOutPath(BigInteger bigInteger)
    {
        return "out"+bigInteger.toString()+".out";
    }

    public static String generatePrefix(BigInteger counter, int size)
    {
        String hex = counter.toString(16);
        hex = CharBuffer.allocate(size-hex.length()).toString().replace( '\0', '0' ) + hex;
        return hex;
    }

    public static void closeStream(Closeable s){
        try{
            if(s!=null)s.close();
        }catch(IOException e){
            //Log or rethrow as unchecked (like RuntimException) ;)
        }
    }
}
