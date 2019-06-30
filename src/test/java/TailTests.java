import console.Main;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TailTests {
    private boolean assertFileContent(String actualOutputName, String expectedOutputName) throws FileNotFoundException {
        FileReader file1 = new FileReader(actualOutputName);
        FileReader file2 = new FileReader(expectedOutputName);
        Scanner scanner1 = new Scanner(file1);
        Scanner scanner2 = new Scanner(file2);
        List<String> text1 = new ArrayList<>();
        List<String> text2 = new ArrayList<>();

        while (scanner1.hasNextLine()) {
            text1.add(scanner1.nextLine());
        }
        while (scanner2.hasNextLine()) {
            text2.add(scanner2.nextLine());
        }
        return text1.equals(text2);
    }

    @Test
    public void testC() throws IOException {
        String[] args = {"-c", "8", "-o", "Output", "inputfile1"};
        Main.main(args);
        assertFileContent("Output", "Expected1");
    }

    @Test
    public void testN() throws IOException {
        String[] args = {"-n", "3", "-o", "Output", "inputfile2"};
        Main.main(args);
        assertFileContent("Output", "Expected2");
    }

    @Test
    public void testFromCmdToFile() throws IOException {
        String[] args = {"-c", "32", "-o", "Output", "Пейзаж в окне опять застыл\n" +
                "Туман как в фильме сайлент-хилл\n" +
                "Мне очень грустно\n" +
                "Я звоню тебе\n" +
                "Приходи ко мне\n" +
                "Слушать старые плстинки\n" +
                "Дивайн Камеди, Питера Габриела и Стинга\n" +
                "Мир сошёл с ума\n" +
                "Надоело все что там снаружи\n" +
                "Давай до утра\n" +
                "Старые пластинки слушать"};
        Main.main(args);
        assertFileContent("Output", "Expected3");
    }

    @Test
    public void testIncorrectArguments() throws IOException {
        String[] args = {"-c","2", "-n", "2", "qwertyuiop\n" +
                "asdfghjkl\n" +
                "zxcvbnm"};
        assertThrows(IllegalArgumentException.class, () ->
                Main.main(args), "'-c' and '-n' commands can't be together");
        String[] args2 = {"-c","qweer", "qwertyuiop\n" +
                "asdfghjkl\n" +
                "zxcvbnm"};
        assertThrows(IllegalArgumentException.class, () ->
                Main.main(args2), "Incorrect command format");
    }

    @Test
    public void testFileNotExist(){
        String[] args = {"-c", "4", "-o", "qwertyuiop\n" +
                "asdfghjkl\n" +
                "zxcvbnm"};
        assertThrows(FileNotFoundException.class, () ->
                Main.main(args));
        String[] args2 = {"-c", "4", "-o", "Output", "notexist"};
        assertThrows(FileNotFoundException.class, () ->
                Main.main(args2));
    }
}
