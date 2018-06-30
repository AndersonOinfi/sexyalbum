package com.sexyalbum.utils;

import java.io.*;

public class FileSaver {
    public static void Save(byte[] bytes, String path) throws IOException {
        BufferedOutputStream outputStream=new BufferedOutputStream(new FileOutputStream(path));
        outputStream.write(bytes);
        outputStream.close();
        return;
    }
}
