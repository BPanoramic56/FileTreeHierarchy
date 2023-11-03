package src;

import java.io.File;
import java.util.Map;

/**
 * Main programs 
 */
public class FileHierarchy{

    private File sourceFile;

    /**
     * Creates a new instance of the Graph file
     * Utilized for traversing the file system finding adjecent files
     */
    public FileHierarchy(){
    }

    /**
     * Gets the highest possible file/folder accessible starting from the given filename
     * 
     * @param filename - the initial file that will be used as the source of the parent recursion 
     * @return - the last file/folder that the method could find
     */
    public File getSourceFile(Object file){

        File f = stringToAbsolute(fileNameArgument(file));

        while (f.getParentFile().getParentFile() != null){
            f = f.getParentFile();
        }

        return f;
    }

    /**
     * Creates the hierarchy of the upmost accessible folder of a given file/filename
     * Utilizing the getSourceFile method, the program finds the upmost accesible file and creates the hierarchy starting from there
     * 
     * @param filename - the file to start the hiearchy creation method
     * @return - the complete hierarchy, represented by a Map structure that can be printed
     */
    public Map<File, Vertex<File>> createAbsoluteHierarchy(Object file){
        String filename = fileNameArgument(file);

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

        String filename = fileNameArgument(file);

        this.sourceFile = getSourceFile(filename);
        return sourceFile;
    }

    public long countFiles(Object file){ 
        String filename = fileNameArgument(file);

        return Graph.fileCount(getSourceFile(filename), 0);
    }
    
    public long countFolders(Object file){ 
        String filename = fileNameArgument(file);

        long count = 0;

        return Graph.directoryCount(getSourceFile(filename), count);
    }

    public long countAverage(){
        return Graph.FileFolderAverage();
    }

    public int currentMapSize(){
        return Graph.size();
    }

    private File stringToAbsolute(String filename){
        return new File(new File(filename).getAbsolutePath());
    }

    /**
     * Helper method for transforming the given argument into a absolute path String
     * @param file 
     * @return
     */
    private String fileNameArgument(Object file){
        String filename = null;
        if(file instanceof String)
            filename = (String) file;
        
        else if (file instanceof File)
            filename = ((File)file).getAbsolutePath();

        return filename;
    }
}