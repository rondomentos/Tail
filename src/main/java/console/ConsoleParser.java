package console;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ConsoleParser {
    private List<String> inputFileName;
    private String outputFileName;
    private boolean processSymbols;
    private int numberOfSymbols = 0;
    private boolean processLines;
    private int numberOfLines;
    private boolean outputFile;
    private boolean inputFile;
    private List<String> inputText;
    ConsoleParser() {
        inputFileName = new ArrayList<String>();
        outputFileName = "";
        processSymbols = false;
        numberOfSymbols = 0;
        processLines = false;
        numberOfLines = 0;
        outputFile = false;
        inputFile = false;
        inputText = new ArrayList<String>();
    }

    public void parse(String[] args){
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-c":
                    processSymbols = true;
                    try {
                        numberOfSymbols = Integer.parseInt(args[++i]);
                    }
                    catch (IndexOutOfBoundsException e) {
                        System.out.println("Incorrect command format");
                    }
                    break;
                case "-n":
                    processLines = true;
                    try {
                        numberOfLines = Integer.parseInt(args[++i]);
                    }
                    catch (IndexOutOfBoundsException e) {
                        System.out.println("Incorrect command format");
                    }
                    break;
                case "-o":
                    try {
                        outputFileName = args[++i];
                        outputFile = true;
                    }
                    catch (IndexOutOfBoundsException e) {
                        System.out.println("Incorrect command format");
                    }
                    break;
                default:
                    if (args[i].matches("^*.*")) {
                        inputFileName.add(args[i]);
                        inputFile = true;
                    }
                    else inputText.add(args[i]);


            }
        }
        if (processLines && processSymbols) throw new IllegalArgumentException("Incorrect command format");
    }

    public List<String> getInputText() {
        return inputText;
    }

    public boolean isProcessLines() {
        return processLines;
    }

    public int getNumberOfLines() {
        return numberOfLines;
    }

    public boolean isProcessSymbols() {
        return processSymbols;
    }

    public int getNumberOfSymbols() {
        return numberOfSymbols;
    }

    public boolean isInputFile() {
        return inputFile;
    }

    public boolean isOutputFile() {
        return outputFile;
    }

    public List<String> getInputFileName() throws FileNotFoundException {
        if (!inputFile) throw new FileNotFoundException("Input file is not found");
        return inputFileName;
    }

    public String getOutputFileName() throws FileNotFoundException {
        if (!outputFile) throw new FileNotFoundException("Output file is not found");
        return outputFileName;
    }
}
