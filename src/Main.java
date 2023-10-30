package src;

import java.math.BigInteger;

public class Main{
    public static void main(String[] args){
        FileHierarchy FH = new FileHierarchy();
        // System.out.println(FH.getSourceFile("/Users/brunogomespascotto/FileTreeHierarchy/src/FileHierarchy.java"));
        long start = System.nanoTime();
        // FH.createAbsoluteHierarchy("/Users/brunogomespascotto/FileTreeHierarchy/src/FileHierarchy.java");
        System.out.println("AbsoluteHierarchy: " + (System.nanoTime() - start));
        start = System.nanoTime();
        BigInteger files = FH.countFiles("/Users/brunogomespascotto/FileTreeHierarchy/src/FileHierarchy.java");
        System.out.println("FileCount: " + (System.nanoTime() - start) + " - " + files);
        int folders = FH.countFolders("/Users/brunogomespascotto/FileTreeHierarchy/src/FileHierarchy.java");
        System.out.println("FolderCount: " + (System.nanoTime() - start) + " - " + folders);
        // System.out.println("File/Folder = " + (files/folders));
        // System.out.println(FH.toString("/Users/brunogomespascotto/FileTreeHierarchy/src/FileHierarchy.java"));
    }
}