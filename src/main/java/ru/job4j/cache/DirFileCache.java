package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DirFileCache extends AbstractCache<String, String> {
    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) throws IOException {
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader bwReader = new BufferedReader(new FileReader(cachingDir + key))) {
            String str;
            while ((str = bwReader.readLine()) != null) {
                rsl.append(str).append("\n");
            }
        }
        return rsl.toString();
    }
}
