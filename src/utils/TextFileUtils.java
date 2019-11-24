package utils;

import java.io.*;


/**
 * CRUD method of text file stored in Repository package
 */

public class TextFileUtils {

    /**
     * Method that retrieves data stored in text file and returns as String
     * @param file
     * @return (String) a String of the content stored in the text file.
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
     * @return (boolean) success
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


    /**
     * Method to read data corresponding to input data through file path
     * @param content
     * @param file_path
     * @return (String) Total data corresponding to some input data
     */
    public String findData (String content, String file_path) {
        String output = "";
        try {
            FileReader fileReader = new FileReader(file_path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

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


    /**
     * Method to modify file data corresponding to file path
     * @param old_data
     * @param new_data
     * @param file_path
     * @return (boolean) success
     */
    public boolean updateFile(String old_data, String new_data, String file_path) {
        TextFileUtils textFileUtils = new TextFileUtils();
        String new_file_path = "C:\\Users\\최연우\\IdeaProjects\\com.beagle.java.project.starfucks\\src\\repository\\pseudoRepository.txt";
        File pseudoFile = new File(new_file_path);
        boolean success = false;

        try {
            if (pseudoFile.createNewFile()) {
                success = true;
            }
        } catch (IOException e) {
            success = false;
        }  catch (Exception e) {
            success = false;
        }

        if (success) {
            BufferedReader br = null;
            BufferedWriter bw = null;

            try {
                br = new BufferedReader(new FileReader(file_path));
                bw = new BufferedWriter(new FileWriter(new_file_path));
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.contains(old_data)) {
                        line = line.replace(old_data, new_data);
                    }
                    bw.write(line);
                }
                success = true;

            }  catch (Exception e) {
                success = false;
            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {
                    success = false;
                }
                try {
                    if (bw != null) {
                        bw.close();
                    }
                } catch (IOException e) {
                    success = false;
                }
            }
        }

        File oldFile = new File(file_path);
        File newFile = new File(new_file_path);

        oldFile.delete();
        newFile.renameTo(oldFile);
        return success;
    }



}
