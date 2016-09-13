package es.babel.guasapi;

import java.io.File;

/**
 * Created by BABEL Sistemas de Información.
 */

public class GCache {
    File directory;
    int size = 10; // MB

    public GCache setDirectory(File directory) {
        this.directory = directory;
        return this;
    }

    public GCache setSize(int size) {
        this.size = size;
        return this;
    }

    File getDirectory() {
        return directory;
    }

    int getSize() {
        return size * 1024 * 1024;
    }
}
