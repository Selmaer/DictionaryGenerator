package dictionarygenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Dictionary {

    private final String fileName;
    private final String dirPath;
    private final String fileExtension;

    private final File DICTIONARY;

    public Dictionary() throws IOException{
        this.fileName = PropertiesFile.getFileName();
        this.fileExtension = PropertiesFile.getFileExtension();
        this.dirPath = PropertiesFile.getDirectoryPath();

        dirCreate();
        this.DICTIONARY = fileCreate();
    }

    public File getDictionary() {
        return DICTIONARY;
    }

    private void dirCreate() {
        try {
            Files.createDirectories(Paths.get(dirPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File fileCreate() {
        String index = "";
        int count = 2;
        while (true) {
            try {
                Path filePath = Files.createFile(Paths.get(dirPath, fileName + index + fileExtension));
                File dictionary = filePath.toFile();
                return dictionary;
            } catch (FileAlreadyExistsException e) {
                index = " " + count++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
