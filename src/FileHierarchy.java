package src;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Main programs 
 */
public class FileHierarchy{

    private File sourceFile;
    /**
     * Creates an instance of the hierarchy starting from the given file
     * Utilized for traversing the file system finding adjecent files
     */
    public FileHierarchy(Object sourceFile){
        new Graph(fileNameArgument(sourceFile));
    }

    /**
     * Gets the highest possible file/folder accessible starting from the given filename
     * 
     * @param filename - the initial file that will be used as the source of the parent recursion 
     * @return - the last file/folder that the method could find
     */
    public File getSourceFile(Object file){

        File f = stringToAbsolute(file);

        while (f.getParentFile().getParentFile() != null){
            f = f.getParentFile();
        }

        return f;
    }

    /**
     * Creates the hierarchy of the given folder and it's children
     * 
     * @param filename - the file to start the hiearchy creation method
     * @return - the complete hierarchy, represented by a Map structure that can be printed
     */
    public Map<File, Vertex<File>> createHierarchy(Object file){
        String filename = (String) file;

       return Graph.createHierarchy(fileNameArgument(filename));
    }

    /**
     * Creates the hierarchy of the upmost accessible folder of a given file/filename
     * Utilizing the getSourceFile method, the program finds the upmost accesible file and creates the hierarchy starting from there
     * 
     * @param filename - the file to start the hiearchy creation method
     * @return - the complete hierarchy, represented by a Map structure that can be printed
     */
    public Map<File, Vertex<File>> createAbsoluteHierarchy(Object file){
        String filename = (String) file;

       return Graph.createHierarchy(getSourceFile(filename));
    }

    /**
     * Sets the source for future iterations
     * The source file is the absolute parent of the given file or filename, which is converted to a absolute path 
     * String via the helper method fileNameArgument()
     * 
     * @param filename
     * @return
     */
    public File setSourceFile(Object file){

        File filename = fileNameArgument(file);

        this.sourceFile = getSourceFile(filename);
        return sourceFile;
    }

public long countFiles(Object file){ 
        File filename = fileNameArgument(file);

        return Graph.fileCount(filename, 0);
    }

    public long countFilesAbsolute(Object file){ 
        File filename = fileNameArgument(file);

        return Graph.fileCount(getSourceFile(filename), 0);
    }

    public int currentMapSize(){
        return Graph.size();
    }

    public void createDOT(String name) throws IOException{
        Graph.writeDot(name);
    }

    private File stringToAbsolute(Object filename){
        return fileNameArgument(filename).getAbsoluteFile();
    }

    /**
     * Helper method for transforming the given argument into a absolute path String
     * @param file 
     * @return
     */
    private File fileNameArgument(Object file){
        File filename = null;

        if(file instanceof String){
            filename = new File( (String)file);
        }
        
        else if (file instanceof File)
            filename = (File) file;

        return filename;
    }
}