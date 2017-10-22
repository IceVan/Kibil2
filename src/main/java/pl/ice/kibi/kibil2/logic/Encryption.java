package pl.ice.kibi.kibil2.logic;

public class Encryption {

    public static String encryptOpenSSL(String IV, String k, String in)
    {
        return encryptOpenSSL(IV,k,in, "out.enc");
    }

    public static String encryptOpenSSL(String IV, String k, String in, String out)
    {
        StringBuffer sb = new StringBuffer("openssl ");
        sb.append("enc -A -aes-256-cbc -base64 -K ").append(k);
        sb.append(" -iv ").append(IV);
        sb.append(" -in ").append(in);
        sb.append(" -out ").append(out);
        return sb.toString();
    }
}
