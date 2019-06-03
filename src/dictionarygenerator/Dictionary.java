package dictionarygenerator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Dictionary {

    private final String fileName;
    private final String dirPath;
    private final String fileExtension;

    private final Path dictionaryPath;
    private BufferedWriter bufferedWriter;
    
    public Dictionary() throws IOException{
        this.fileName = PropertiesFile.getFileName();
        this.fileExtension = PropertiesFile.getFileExtension();
        this.dirPath = PropertiesFile.getDirectoryPath();

        dirCreate();
        this.dictionaryPath = fileCreate();
        this.bufferedWriter = Files.newBufferedWriter(dictionaryPath);
    }

    public Path getDictionaryPath() {
        return dictionaryPath;
    }
    public BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }

    private void dirCreate() {
        try {
            Files.createDirectories(Paths.get(dirPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Path fileCreate() {
        String index = "";
        int count = 2;
        while (true) {
            try {
                Path filePath = Files.createFile(Paths.get(dirPath, fileName + index + fileExtension));
                return filePath;
            } catch (FileAlreadyExistsException e) {
                index = " " + count++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void append (String s) throws IOException {
        bufferedWriter.append(s);
    }

}
