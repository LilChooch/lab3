package com.example.filebrowser;

import java.io.File;
import java.util.Date;

public class FileEntities {
    private final File directory;
    private final File[] files;
    private final Date Time =  new Date();

    public FileEntities(String currentPath){
        this.directory = new File(currentPath);
        this.files = this.directory.listFiles();
    }

    public File[] getFiles() {
        return files;
    }

    public Date getTime() {
        return Time;
    }

    public String parentPath(){
        return directory.getParent();
    }
}
