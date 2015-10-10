package br.com.ranz.combustivelbarato.utils;

import java.io.UnsupportedEncodingException;

/**
 * Created by Ricardo on 10/10/2015.
 */
public class StringUtils {

    private static String acentuado = "çÇáéíóúýÁÉÍÓÚÝàèìòùÀÈÌÒÙãõñäëïöüÿÄËÏÖÜÃÕÑâêîôûÂÊÎÔÛ";
    private static String semAcento = "cCaeiouyAEIOUYaeiouAEIOUaonaeiouyAEIOUAONaeiouAEIOU";
    private static char[] tabela;

    static {
        tabela = new char[256];
        for (int i = 0; i < tabela.length; ++i) {
            tabela [i] = (char) i;
        }
        for (int i = 0; i < acentuado.length(); ++i) {
            tabela [acentuado.charAt(i)] = semAcento.charAt(i);
        }
    }

    public static String removerAcentos(String acentuada) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < acentuada.length(); ++i) {
            char ch = acentuada.charAt (i);
            if (ch < 256) {
                sb.append (tabela [ch]);
            } else {
                sb.append (ch);
            }
        }
        return sb.toString();
    }

    @Deprecated
    public static String replaceInvalidChars( String conteudo ) {
        //returns safe code for preloading in the RTE

        if (conteudo == null) {
            return "";
        }

        String tmpString = conteudo;

        //convert all types of single quotes
        tmpString = tmpString.replace((char)145, (char)39);
        tmpString = tmpString.replace((char)146, (char)39);
        tmpString = tmpString.replace("'", "&39;");

        //convert all types of double quotes
        tmpString = tmpString.replace((char)147, (char)34);
        tmpString = tmpString.replace((char)148, (char)34);
        tmpString = tmpString.replaceAll("\"", "&quot;");

        //replace carriage returns & line feeds
        tmpString = tmpString.replace((char)10, (char)32);
        tmpString = tmpString.replace((char)13, (char)32);

        return tmpString;
    }

    public static String replaceBotherChars( String conteudo ) {
        //returns safe code for preloading in the RTE

        if (conteudo == null) {
            return "";
        }

        String tmpString = conteudo;

        //convert all types of single quotes
        tmpString = tmpString.replace((char)145, (char)39);
        tmpString = tmpString.replace((char)146, (char)39);
        tmpString = tmpString.replace("'", "&39;");

        //convert all types of double quotes
        tmpString = tmpString.replace((char)147, (char)34);
        tmpString = tmpString.replace((char)148, (char)34);
        tmpString = tmpString.replaceAll("\"", "&quot;");

        //replace carriage returns & line feeds
        tmpString = tmpString.replace((char)10, '%');
        tmpString = tmpString.replace((char)13, '*');

        return tmpString;
    }

    public static String removeHtmlTags(String htmlText) {
        if (htmlText == null) {
            return "";
        } else {
            return htmlText.replaceAll("<[^>]*>", "");
        }
    }

    public static boolean isEmpty(String text) {
        //160 &nbsp;
        // 32 espa�o
        text = text.replace((char)160,(char)32);
        return text.trim().length() == 0;
    }

    public static int length(String text) {
        //160 &nbsp;
        // 32 espa�o
        text = text.replace((char)160,(char)32);
        return text.trim().length();
    }

    /**
     * Converte o formato ISO8859-1 (default IE) para UTF-8
     */
    public static String toUTF8(String isoString) {
        String utf8String = null;
        if (null != isoString && !isoString.equals(""))
        {
            try
            {
                byte[] stringBytesISO = isoString.getBytes("ISO-8859-1");
                utf8String = new String(stringBytesISO, "UTF-8");
            }
            catch(UnsupportedEncodingException e)
            {
                utf8String = isoString;
            }
        }
        else
        {
            utf8String = isoString;
        }
        return utf8String;
    }

    public  String getRoman(int number) {

        String riman[] = {"M","XM","CM","D","XD","CD","C","XC","L","XL","X","IX","V","IV","I"};
        int arab[] = {1000, 990, 900, 500, 490, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (number > 0 || arab.length == (i - 1)) {
            while ((number - arab[i]) >= 0) {
                number -= arab[i];
                result.append(riman[i]);
            }
            i++;
        }
        return result.toString();
    }

}
