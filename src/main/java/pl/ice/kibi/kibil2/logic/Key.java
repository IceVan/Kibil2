package pl.ice.kibi.kibil2.logic;

import pl.ice.kibi.kibil2.logic.exceptions.EndOfKeysException;

import java.math.BigInteger;

public class Key {

    private static BigInteger counter = new BigInteger("126b200",16);
    private BigInteger insideCounter;
    private String prefix;
    private String path;

    public Key(int prefixSize) {
        insideCounter = counter;
        this.prefix = FileUtils.generatePrefix(insideCounter, prefixSize);
        this.path = FileUtils.generateOutPath(insideCounter);
        counter = counter.add(BigInteger.ONE);
        if(prefix.length()>prefixSize) throw new EndOfKeysException();
    }

    public String getPrefix() {
        return prefix;
    }

    public String getPath() {
        return path;
    }

    public String toString()
    {
        return counter.toString() + " " + insideCounter.toString()+ " " + prefix + " " + path;
    }

    public BigInteger getInsideCounter() {
        return insideCounter;
    }

}
