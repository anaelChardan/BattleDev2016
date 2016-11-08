package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Reader {
    private static Reader instance = new Reader();

    private Reader() {}

    public static Reader getInstance() {
        return instance;
    }

    public FileInputStream getFile(String name) throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        return new FileInputStream (classLoader.getResource("file/" + name).getPath());
    }
}
