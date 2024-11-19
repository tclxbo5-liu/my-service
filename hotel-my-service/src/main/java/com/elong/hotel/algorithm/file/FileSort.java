package com.elong.hotel.algorithm.file;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author bobo
 * @date 2024/11/19 15:23
 **/
public class FileSort {
    public static void fileSort(String filePath1, String filePath2) throws IOException {

        File fileInput = new File(filePath1);
        File fileOutput = new File(filePath2);


        try (BufferedReader reader = new BufferedReader(new FileReader(fileInput));
             BufferedWriter writer = new BufferedWriter(new FileWriter(fileOutput))) {
            List<String> list= new ArrayList<>();
            String readLine;
            while ((readLine = reader.readLine())!=null){
                list.add(readLine);
            }
            Collections.sort(list);
            for (String s : list) {
                writer.write(s);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        String file1Path = "/Users/bobo/Downloads/新郎问卷_刘波_10.2.doc";
        String file2Path = "/Users/bobo/Downloads/output";
        fileSort(file1Path,file2Path);
    }

}
