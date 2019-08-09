package pl.coderslab.m3.h2;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.comparator.PathFileComparator;
import org.apache.commons.io.comparator.SizeFileComparator;
import org.apache.commons.io.filefilter.AndFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.commons.io.input.TeeInputStream;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.io.output.TeeOutputStream;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FilterOutputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ApacheIOUnitTests {
    @Test
    public void testByteArrayStreams() throws IOException {
        String str = "Hello World.";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(str.getBytes());
        ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
        ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream();

        FilterOutputStream teeOutputStream
                = new TeeOutputStream(outputStream1, outputStream2);
        new TeeInputStream(inputStream, teeOutputStream, true)
                .read(new byte[str.length()]);

        System.out.println(String.valueOf(outputStream1));
        System.out.println(String.valueOf(outputStream2));
        assertEquals(str, String.valueOf(outputStream1));
        assertEquals(str, String.valueOf(outputStream2));

    }

    @Test
    public void whenGetFilewith_ANDFileFilter_thenFind_sample_txt()
            throws IOException {

        String path = getClass().getClassLoader()
                .getResource("fileTest.txt")
                .getPath();
        File dir = FileUtils.getFile(FilenameUtils.getFullPath(path));
        String[] found = dir.list(new AndFileFilter(
                new WildcardFileFilter("*ple*", IOCase.INSENSITIVE),
                new SuffixFileFilter("txt")));
        assertEquals("sample.txt",
                found[0]);
    }

    @Test
    public void whenSortDirWithPathFileComparator_thenFirstFile_aaatxt()
            throws IOException {

        PathFileComparator pathFileComparator = new PathFileComparator(
                IOCase.INSENSITIVE);
        String path = FilenameUtils.getFullPath(getClass()
                .getClassLoader()
                .getResource("fileTest.txt")
                .getPath());
        File dir = new File(path);
        File[] files = dir.listFiles();

        pathFileComparator.sort(files);

        assertEquals("aaa.txt", files[0].getName());
    }

    @Test
    public void whenSizeFileComparator_thenLargerFile_large()
            throws IOException {

        SizeFileComparator sizeFileComparator = new SizeFileComparator();
        File largerFile = FileUtils.getFile(getClass().getClassLoader()
                .getResource("fileTest.txt")
                .getPath());
        File smallerFile = FileUtils.getFile(getClass().getClassLoader()
                .getResource("sample.txt")
                .getPath());

        int i = sizeFileComparator.compare(largerFile, smallerFile);

        Assert.assertTrue(i > 0);
    }
}
