package pl.ice.kibi.kibil2.logic;


import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class DictionaryService {

    private final Set<String> dictionary;

    public DictionaryService() throws IOException {
        dictionary = new HashSet<String>(FileUtils.getFileAsArrayList("dictionary.txt"));
    }

    public boolean isAtLeastOneInDictionary(String message)
    {
        StringTokenizer st = new StringTokenizer(message);
        return dictionary.contains(st.nextToken());
    }

    public boolean isAtLeastOneInDictionary(StringTokenizer st)
    {
        return dictionary.contains(st.nextToken());
    }

    public boolean isAtLeastTwoInDictionary(String message)
    {
        StringTokenizer st = new StringTokenizer(message);
        if(!isAtLeastOneInDictionary(st)) return false;
        return isAtLeastOneInDictionary(st);
    }

    public boolean isAllInDictionary(String message)
    {
        message.split(" ");
        return dictionary.contains(message);
    }
}
