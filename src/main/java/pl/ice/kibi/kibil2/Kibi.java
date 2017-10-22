package pl.ice.kibi.kibil2;

import pl.ice.kibi.kibil2.logic.*;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/*
String iv = "c2bcabc6eac93d2e57189626fc85ded9";
            //String suffix = "19c3bcec5920d1a4a23ea49105b4f2f072dc3f21af2656e177b8035b11f98916";
            String suffix = "3bcec5920d1a4a23ea49105b4f2f072dc3f21af2656e177b8035b11f98916";
            String file = System.getProperty("user.dir")+"\\test.txt";
 */

public class Kibi {
    public static void  main(String [] args)
    {
        try {
            DictionaryService dictionaryService = new DictionaryService();
            BlockingQueue<Key> keys = new ArrayBlockingQueue<Key>(8);

            String iv = "ca8db4764cf0d24d78dbf90f832021a1";
            //String suffix = "19c3bcec5920d1a4a23ea49105b4f2f072dc3f21af2656e177b8035b11f98916";
            String suffix = "3df6e78cf743e6d0e52e075430ab97dc6c7163b36be37d4816e09cbc";
            String file = System.getProperty("user.dir")+"\\in\\ad1.txt";

            int prefixSize = 8;

            Thread keyProducer = new KeyProducer(keys, prefixSize);


            for(int i = 0; i<8; i++)
            {
                keys.put(new Key(prefixSize));
            }

            for(int i = 0; i < 4; i++)
            {
                Thread t = new BruteForce(keys, iv, suffix, file, dictionaryService);
                t.start();
            }

            keyProducer.start();
            keyProducer.join();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doNotUse()
    {
        Runtime runtime = Runtime.getRuntime();

        String command = Encryption.encryptOpenSSL("c2bcabc6eac93d2e57189626fc85ded9", "19c3bcec5920d1a4a23ea49105b4f2f072dc3f21af2656e177b8035b11f98916", System.getProperty("user.dir")+"\\test.txt");
        System.out.println(command);


        try {
            Process proc = runtime.exec(command);

            System.out.println(FileUtils.readFile("out.enc"));

            command = Decryption.deryptionOpenSSL("c2bcabc6eac93d2e57189626fc85ded9", "19c3bcec5920d1a4a23ea49105b4f2f072dc3f21af2656e177b8035b11f98916", System.getProperty("user.dir")+"\\out.enc");
            System.out.println(command);

            proc = runtime.exec(command);

            String out = FileUtils.readFile(System.getProperty("user.dir")+"\\out.dec");
            System.out.println(out);

            DictionaryService dictionaryService = new DictionaryService();
            System.out.println(dictionaryService.isAtLeastOneInDictionary(out));


            for(int i = 0; i< 2000; i++)
            {
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
