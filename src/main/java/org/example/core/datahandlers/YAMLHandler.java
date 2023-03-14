package org.example.core.datahandlers;

import org.example.core.models.MusicBand;
import org.example.interfaces.IDataLoader;
import org.example.interfaces.IDataSaver;
import org.example.interfaces.IPrinter;
import org.yaml.snakeyaml.Yaml;
import java.io.*;

/**
 * Contains logic for work with source YAML file.
 */
public class YAMLHandler implements IDataSaver, IDataLoader {
    private String filePath;
    private IPrinter printer;

    public YAMLHandler(String path, IPrinter printer){
        filePath = path;
        this.printer = printer;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public <T> T load(Class<T> clazz) {
        Yaml yaml = new Yaml();
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            return yaml.loadAs(reader, clazz);
        }
        catch (Exception ex){
            printer.print("Exception log!\nSomething went wrong while reading from the file.\nCheck file path and file validity...");
            return null;
        }
    }

    @Override
    public <T> boolean save(T data) {
        Yaml yaml = new Yaml();

        try(OutputStreamWriter streamWriter = new OutputStreamWriter(new FileOutputStream(filePath))){
            yaml.dump(data, streamWriter);
            return true;
        }
        catch (Exception ex){
            printer.print("Exception log!\nSomething went wrong while file was writing.\nCheck file path and file validity...");
            return  false;
        }
    }
}
