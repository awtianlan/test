package main.java.com.test;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.hwpf.extractor.WordExtractor;

import java.io.*;

public class Test {

    public static void main(String[] args) throws IOException {
     //  String result= getTextFromWord("C:\\Users\\Administrator\\Documents\\Tencent Files\\1450056746\\FileRecv\\穗人社项办函〔2013〕8号 关于印发《设备到货检验和签收流程》的通知.doc");
       String result=getTextFromPdf("D:\\chrome\\筑医台投票评选小程序项目.pdf");
        System.out.println(result);
       byte[] r=result.getBytes();
        FileOutputStream out=new FileOutputStream("D:\\chrome\\筑医台投票评选小程序项目.doc");
        out.write(r);

    }

    /**
     *
     * @Title: getTextFromWord
     * @Description: 读取word
     * @param filePath
     *      文件路径
     * @return: String 读出的Word的内容
     */
    public static String getTextFromWord(String filePath) {
        String result = null;
        File file = new File(filePath);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            @SuppressWarnings("resource")
            WordExtractor wordExtractor = new WordExtractor(fis);
            result = wordExtractor.getText();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
      *
      * @Title: getTextFromPdf
      * @Description: 读取pdf文件内容
      * @param filePath
      * @return: 读出的pdf的内容
      */
    public static String getTextFromPdf(String filePath) {
        String result = null;
        FileInputStream is = null;
        PDDocument document = null;
        try {
            is = new FileInputStream(filePath);
            PDFParser parser = new PDFParser(is);
            parser.parse();
            document = parser.getPDDocument();
            PDFTextStripper stripper = new PDFTextStripper();
            result = stripper.getText(document);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (document != null) {
                try {
                    document.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
