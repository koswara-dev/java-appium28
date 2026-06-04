package com.example.utils;

public class Utils {

    public static Object[][] readCsvData(String filePath) {
        java.util.List<Object[]> dataList = new java.util.ArrayList<>();
        String line;
        
        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(filePath))) {
            // Read and skip the header line
            br.readLine();
            
            while ((line = br.readLine()) != null) {
                java.util.List<String> list = new java.util.ArrayList<>();
                boolean inQuotes = false;
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    if (c == '"') {
                        inQuotes = !inQuotes;
                    } else if (c == ',' && !inQuotes) {
                        list.add(sb.toString().trim());
                        sb.setLength(0);
                    } else {
                        sb.append(c);
                    }
                }
                list.add(sb.toString().trim());
                dataList.add(list.toArray(new String[0]));
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        
        return dataList.toArray(new Object[0][]);
    }
}
