package dictionarygenerator;

import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

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
    private int count = 1;
    
    public final Path dictionaryPath;
    public final BufferedWriter bufferedWriter;
    
    public Dictionary() throws IOException{
        this.fileName = PropertiesFile.getFileName();
        this.fileExtension = PropertiesFile.getFileExtension();
        this.dirPath = PropertiesFile.getDirectoryPath();

        dirCreate();
        this.dictionaryPath = fileCreate();
        this.bufferedWriter = Files.newBufferedWriter(dictionaryPath);
    }

    public static String choosePath (Stage primaryStage) {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Choose your dictionary directory");
        File defaultDirectory = new File(PropertiesFile.getDirectoryPath());
        chooser.setInitialDirectory(defaultDirectory);
        File selectedDirectory = chooser.showDialog(primaryStage);
        if (selectedDirectory != null) {
            return selectedDirectory.getPath();
        } else {
            return "";
        }
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
