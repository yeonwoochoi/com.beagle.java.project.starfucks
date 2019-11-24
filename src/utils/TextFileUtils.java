package utils;

import java.io.*;

public class TextFileUtils {

    /**
     * Method that retrieves data stored in text file and returns as String
     * @param file
     * @return a String of the content stored in the text file.
     */
    public String readFile(File file) {
        String output ="";
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                output += line;
            }
            bufferedReader.close();
            return output;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            output += String.valueOf(e);
        }
        return output;
    }


    /**
     * Method to save the input data to the input file path
     * @param input_str
     * @param file_path
     * @return boolean success
     */
    public boolean saveToFile(String input_str, String file_path) {
        File SW_file = new File(file_path);
        boolean success;

        try {
            FileWriter f_writer = new FileWriter(SW_file, true);
            BufferedWriter SW_writer = new BufferedWriter(f_writer);

            if (SW_file.isFile() && SW_file.canWrite()) {
                SW_writer.write(input_str);
                SW_writer.close();
                success = true;
            } else {
                success = false;
            }


        } catch (FileNotFoundException e) {
            success = false;
        } catch (IOException e) {
            success = false;
        }
        return success;
        // BufferWriter FileWriter 로 SW_employee.txt 에 저장하는 method
    }



    public String findData (String content, String file_path) {
        String output = "";
        try {
            FileReader SW_reader = new FileReader(file_path);
            BufferedReader bufferedReader = new BufferedReader(SW_reader);

            String line = "";
            String[] stringArr;

            while ((line = bufferedReader.readLine()) != null) {
                stringArr = line.split(";");
                for (int i = 0 ; i < stringArr.length; i++) {
                    if (stringArr[i].contains(content)) {
                        output += stringArr[i];
                    }
                }
            }
            bufferedReader.close();
            return output;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            output += String.valueOf(e);
        }
        return output;
    }


}
