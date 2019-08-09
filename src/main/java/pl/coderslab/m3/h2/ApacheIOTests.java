package pl.coderslab.m3.h2;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Scanner;

public class ApacheIOTests {
    public static void main(String[] args) {
        try {
            String desktop=System.getProperty("user.home") + "/Desktop";
            ApacheIOTests ioTest = new ApacheIOTests();
            ioTest.fileUtilsRead();
            ioTest.filenameUtilsTests("test.txt");
//            ExecutorService executorService = Executors.newSingleThreadExecutor();
//            Future feature = executorService.submit(() -> ioTest.fileMonitorTest(desktop));
            FileAlterationMonitor monitor=ioTest.fileMonitorTest(desktop);
            monitor.start();

            File newFile = new File(desktop+"/test1");
            newFile.createNewFile();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Press CTRL-D to exit");
            while (scanner.hasNext()) {
                scanner.next();
            }
            monitor.stop();
        }catch(InterruptedException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }


    }
    private  void fileUtilsRead() {
        try {
            File file = FileUtils.getFile(getClass().getClassLoader()
                    .getResource("test.txt")
                    .getPath());
            File tempDir = FileUtils.getTempDirectory();
            FileUtils.copyFileToDirectory(file, tempDir);
            File newTempFile = FileUtils.getFile(tempDir, file.getName());
            String data = FileUtils.readFileToString(newTempFile,
                    Charset.defaultCharset());
            System.out.println(data);
        } catch (IOException e) {
            e.printStackTrace();;
        }
    }
    private  void filenameUtilsTests(String resource) {
        try {
            String data = IOUtils.resourceToString(resource, null, ApacheIOTests.class.getClassLoader());
            URL resUrl=IOUtils.resourceToURL(resource,getClass().getClassLoader());
            String path=resUrl.getPath();
            String content = FileUtils.readFileToString(new File(path),"UTF-8");
            String fullPath = FilenameUtils.getFullPath(path);
            String extension = FilenameUtils.getExtension(path);
            String baseName = FilenameUtils.getBaseName(path);
            System.out.println(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private FileAlterationMonitor fileMonitorTest(String folder) {
        try {
            FileAlterationObserver observer = new FileAlterationObserver(folder);
            FileAlterationMonitor monitor = new FileAlterationMonitor(5000);

            FileAlterationListener fal = new FileAlterationListenerAdaptor() {

                @Override
                public void onFileCreate(File file) {
                    System.out.println("file created "+file.getPath());
                    // on create action
                }

                @Override
                public void onFileDelete(File file) {
                    System.out.println("File deleted " + file.getPath());
                    // on delete action
                }
            };

            observer.addListener(fal);
            monitor.addObserver(observer);

            return monitor;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
