package pl.ice.kibi.kibil2.logic;

import pl.ice.kibi.kibil2.logic.exceptions.EndOfKeysException;

import java.util.concurrent.BlockingQueue;

public class KeyProducer extends Thread {
    private BlockingQueue<Key> blockingQueue;
    private int keySize;

    public KeyProducer(BlockingQueue<Key> blockingQueue, int keySize) {
        this.blockingQueue = blockingQueue;
        this.keySize = keySize;
    }

    @Override
    public void  run()
    {
        boolean run = true;
        while(run)
        {
            try
            {
                blockingQueue.put(new Key(keySize));
            }
            catch (EndOfKeysException e)
            {
                run = false;
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
