package pl.ice.kibi.kibil2.logic;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.concurrent.BlockingQueue;

public class BruteForce extends Thread{

    private BlockingQueue<Key> blockingQueue;
    private String iv;
    private String suffix;
    private String file;
    private DictionaryService dictionaryService;

    public BruteForce(BlockingQueue<Key> blockingQueue, String iv, String suffix, String file, DictionaryService dictionaryService) {
        this.blockingQueue = blockingQueue;
        this.iv = iv;
        this.suffix = suffix;
        this.file = file;
        this.dictionaryService = dictionaryService;
    }

    public void run2()
    {
        while(!blockingQueue.isEmpty())
        {
            Runtime runtime = Runtime.getRuntime();
            try {
                Key key = blockingQueue.take();
                String path = System.getProperty("user.dir")+"\\out\\"+key.getPath();
                String decrypted = Decryption.deryptionOpenSSL(iv, key.getPrefix()+suffix, file, path);


                System.out.println(decrypted);
                Process proc = runtime.exec(decrypted);
                String out = FileUtils.readFileWhenReady(path);

                boolean inDictionary = dictionaryService.isAtLeastOneInDictionary(out);
                if(!inDictionary)
                {
                    File file = new File(path);

                    while(!file.delete())
                    {

                    }
                }
                else {
                    System.out.println(key.getPath() + " " + key.getPrefix() + " " + inDictionary);
                }
                //runtime.exit(0);


            } catch (InterruptedException e) {
                e.printStackTrace();
                runtime.exit(0);
            } catch (IOException e) {
                e.printStackTrace();
                runtime.exit(0);
            }
        }
    }

    @Override
    public void run()
    {
        while(!blockingQueue.isEmpty())
        {
            Runtime runtime = Runtime.getRuntime();
            try {
                Key key = blockingQueue.take();
                String decrypted = Decryption.deryptionOpenSSLtoConsole(iv, key.getPrefix()+suffix, file);



                Process proc = runtime.exec(decrypted);
                String out = FileUtils.readFromInputStream(proc.getInputStream());

                boolean inDictionary = dictionaryService.isAtLeastTwoInDictionary(out);
                System.out.println(key.getPrefix());
                if(inDictionary)
                {
                    String possibleKey = key.getPrefix() + suffix;
                    PrintWriter writer = new PrintWriter("out\\" + possibleKey + ".txt", "UTF-8");
                    writer.println(out);
                    writer.println("Key: " + possibleKey);
                    System.err.println(decrypted + " " + inDictionary);
                    System.err.println(out);
                    writer.close();
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
                runtime.exit(0);
            } catch (IOException e) {
                e.printStackTrace();
                runtime.exit(0);
            }
        }
    }

}
