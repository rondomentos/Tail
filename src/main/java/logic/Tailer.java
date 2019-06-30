package logic;

import java.util.ArrayList;
import java.util.List;

public class Tailer {
    private List<String> result = new ArrayList<String>();
    public List<String> symbolCut(int amountOfSymbols, List<String> linesFromFile) {

        int symbols = 0;
        int i = linesFromFile.size() - 1;
        String Line;
        while (symbols < amountOfSymbols && i >= 0) {
            Line = linesFromFile.get(i);
            int lengthOfLine = Line.length();
            if (lengthOfLine + symbols <= amountOfSymbols) result.add(0, Line);
            else result.add(0, Line.substring(lengthOfLine - amountOfSymbols + symbols));
            symbols += lengthOfLine;
            i--;
        }
        return result;
    }


    public List<String> lineCut(int amountOfLines, List<String> linesFromFile) {
        for (int i = linesFromFile.size() - amountOfLines; i < linesFromFile.size(); i++)
            if (linesFromFile.size() > Math.abs(i)) {
                result.add(linesFromFile.get(i));
            }
        return result;
    }
}
