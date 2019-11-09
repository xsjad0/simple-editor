package dev.swt.gui;

import java.util.HashMap;

public class MyFile {

    private HashMap<String, String> meta;
    private String content;

    public MyFile() {
        meta = new HashMap<>();
        content = "";
    }

    public void setMeta(String key, String value) {
        meta.put(key, value);
    }

    public void setMeta(HashMap<String, String> meta) {
        this.meta = meta;
    }

    public String getMeta(String key) {
        return meta.get(key);
    }

    public HashMap<String, String> getMeta() {
        return meta;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void addContent(String content) {
        this.content += content;
    }
}