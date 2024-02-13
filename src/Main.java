package src;

import java.io.IOException;
import java.util.Random;

public class Main{
    public static void main(String[] args) throws IOException{
        FileHierarchy FH = new FileHierarchy("/Users/brunogomespascotto/Desktop/Classes/MATH-1210");
        // System.out.println(FH.getSourceFile("/Users/brunogomespascotto/FileTreeHierarchy/src/FileHierarchy.java"));
        long start = System.nanoTime();
        FH.createHierarchy("/Users/brunogomespascotto/FileTreeHierarchy/src/FileHierarchy.java");
        // System.out.println("AbsoluteHierarchy: " + (System.nanoTime() - start));
        
        long files = FH.countFiles("/Users/brunogomespascotto/FileTreeHierarchy/src/FileHierarchy.java");
        // System.out.println("FileCount: " + (System.nanoTime() - start) + " - " + files);

        FH.createDOT("Complete");

        // System.out.println("Size: " + FH.currentMapSize());

        // System.out.println(FH.toString("/Users/brunogomespascotto/FileTreeHierarchy/src/FileHierarchy.java"));
    }
}