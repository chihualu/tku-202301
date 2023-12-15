package org.tku.web.entity;

import lombok.Data;

@Data
public class FileItem {
    public FileItem(String fileName) {
        this.fileName = fileName;
    }
    private String fileName;
}
