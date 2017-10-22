package pl.ice.kibi.kibil2.logic;

public class Decryption {

    public static String deryptionOpenSSL(String IV, String k, String in)
    {
        return deryptionOpenSSL(IV,k,in, "out.dec");
    }

    public static String deryptionOpenSSL(String IV, String k, String in, String out)
    {
        StringBuffer sb = new StringBuffer("openssl ");
        sb.append("enc -d -A -aes-256-cbc -base64 -K ").append(k);
        sb.append(" -iv ").append(IV);
        sb.append(" -in ").append(in);
        sb.append(" -out ").append(out);
        return sb.toString();
    }

    public static String deryptionOpenSSLtoConsole(String IV, String k, String in)
    {
        StringBuffer sb = new StringBuffer("openssl ");
        sb.append("enc -d -A -aes-256-cbc -base64 -K ").append(k);
        sb.append(" -iv ").append(IV);
        sb.append(" -in ").append(in);
        return sb.toString();
    }
}
