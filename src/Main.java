package src;


public class Main{
    public static void main(String[] args){
        FileHierarchy FH = new FileHierarchy();
        // System.out.println(FH.getSourceFile("/Users/brunogomespascotto/FileTreeHierarchy/src/FileHierarchy.java"));
        // long start = System.nanoTime();
        // FH.createAbsoluteHierarchy("/Users/brunogomespascotto/FileTreeHierarchy/src/FileHierarchy.java");
        // System.out.println("AbsoluteHierarchy: " + (System.nanoTime() - start));
        
        // long start = System.nanoTime();

        // long files = FH.countFiles("/Users/brunogomespascotto/FileTreeHierarchy/src/FileHierarchy.java");
        // System.out.println("FileCount: " + (System.nanoTime() - start) + " - " + files);
        
        long folders = FH.countFolders("/Users/brunogomespascotto/FileTreeHierarchy/src/FileHierarchy.java");
        // System.out.println("FolderCount: " + (System.nanoTime() - start) + " - " + folders);
        // System.out.println("File/Folder = " + (files/folders));

        System.out.println("Average: " + FH.countAverage());

        System.out.println("Size: " + FH.currentMapSize());

        // System.out.println(FH.toString("/Users/brunogomespascotto/FileTreeHierarchy/src/FileHierarchy.java"));
    }
}