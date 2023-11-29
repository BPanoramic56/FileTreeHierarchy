package src;

import java.io.IOException;

public class Main{
    public static void main(String[] args) throws IOException{
        FileHierarchy FH = new FileHierarchy("/Users/brunogomespascotto/Desktop/Classes/CS-2420");
        System.out.println(FH.getSourceFile("/Users/brunogomespascotto/FileTreeHierarchy/src/FileHierarchy.java"));
        long start = System.nanoTime();
        FH.createAbsoluteHierarchy("/Users/brunogomespascotto/FileTreeHierarchy/src/FileHierarchy.java");
        System.out.println("AbsoluteHierarchy: " + (System.nanoTime() - start));
        
        long files = FH.countFiles("/Users/brunogomespascotto/FileTreeHierarchy/src/FileHierarchy.java");
        System.out.println("FileCount: " + (System.nanoTime() - start) + " - " + files);

        FH.createDOT();

        System.out.println("Size: " + FH.currentMapSize());

        // System.out.println(FH.toString("/Users/brunogomespascotto/FileTreeHierarchy/src/FileHierarchy.java"));
    }
}