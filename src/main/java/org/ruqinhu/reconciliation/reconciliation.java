package org.ruqinhu.reconciliation;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class reconciliation {

    public static void main(String[] args) throws IOException {
//        File input = new File("input.csv");
//        File output = new File("output.json");

        File f1 = new File("C:\\Users\\runze\\Desktop\\sss");
        File[] files = f1.listFiles();

        for (File file : files) {
            CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true).build();
            CsvMapper csvMapper = new CsvMapper();

            // Read data from CSV file
            List<Object> readAll = csvMapper.readerFor(Map.class).with(csvSchema).readValues(file).readAll();

            ObjectMapper mapper = new ObjectMapper();

            // Write JSON formated data to output.json file
//            mapper.writerWithDefaultPrettyPrinter().writeValue(output, readAll);

            // Write JSON formated data to stdout
//            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(readAll));

            com.alibaba.fastjson.JSON jsonArray = JSON.parseObject(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(readAll));
            System.out.println(jsonArray);
        }



    }

}
