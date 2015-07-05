package mailer;

import java.io.*;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by Funker on 04.07.2015.
 */
public class ZipUtils {

    public static void main(String[] args) throws IOException {
        File folderToZip = new File(System.getProperty("user.dir") + "/target/site/allure-maven-plugin/");
        File zipFile = new File(System.getProperty("user.dir") + "/allure_report_" + getPrefixToFile() + ".zip");
        ZipUtils.zipFolder(folderToZip, zipFile);
    }

    public static void zipFolder(final File folder, final File zipFile) throws IOException {
        if (folder.exists()) {
            System.out.println("Output to Zip : " + zipFile);
            System.out.println("Folder to Zip : " + folder);
            zipFolder(folder, new FileOutputStream(zipFile));
            System.out.println("Zipping Done");
        } else {
            throw new RemoteException(String.format("Folder '%s' does not exist", folder.getPath()));
        }
    }

    public static String zipTargetFolder(final String folderToZip, final String zipFileName) throws IOException {
        File folder = new File(System.getProperty("user.dir") + folderToZip);
        String fileName = zipFileName + ZipUtils.getPrefixToFile() + ".zip";
        File zipFile = new File(System.getProperty("user.dir") + fileName);
        zipFolder(folder, zipFile);
        return fileName;
    }

    private static void zipFolder(final File folder, final OutputStream outputStream) throws IOException {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream)) {
            processFolder(folder, zipOutputStream, folder.getPath().length() + 1);
        }
    }

    public static String getPrefixToFile() {
        return new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss", Locale.US).format(new Date());
    }

    private static void processFolder(final File folder, final ZipOutputStream zipOutputStream, final int prefixLength) throws IOException {
        for (final File file : folder.listFiles()) {
            if (file.isFile()) {
                final ZipEntry zipEntry = new ZipEntry(file.getPath().substring(prefixLength));
                System.out.println("File Added : " + zipEntry.getName());
                zipOutputStream.putNextEntry(zipEntry);
                try (FileInputStream inputStream = new FileInputStream(file)) {
                    copyStreams(inputStream, zipOutputStream);
//                    IOUtils.copy(inputStream, zipOutputStream);
                }
                zipOutputStream.closeEntry();
            } else if (file.isDirectory()) {
                processFolder(file, zipOutputStream, prefixLength);
            }
        }
    }

    private static void copyStreams(FileInputStream inputStream, ZipOutputStream zipOutputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            zipOutputStream.write(buffer, 0, len);
        }
    }
}
