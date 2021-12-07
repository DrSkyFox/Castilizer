package oba.castilizer.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;

@Slf4j
@Service
public class FileChangeService implements IFileChange {


    @Override
    public Boolean changeFileParam(String paramName, String paramSet) {
        log.info("Call changeFileParam, Input param method {}, {}", paramName, paramSet);
        String fileStr = "D:\\Sborka\\__FITNESS\\EFT2\\EFT2\\Eft.ini";
        var inputBuffer = readFile(paramName,paramSet,fileStr);


        log.info("strTemp: {}", inputBuffer.toString());
        try(var writer = new OutputStreamWriter(new FileOutputStream(fileStr))) {

            log.info("---------------------------WRITING CHANGES TO FILE---------------------------------");
            writer.write(inputBuffer.toString());

        } catch (IOException e) {
            log.error("Error {}", e.getMessage());
            return false;
        }
        return true;
    }

    private StringBuffer readFile(String paramName, String paramSet, String fileStr) {
        var inputBuffer = new StringBuffer();
        String strTemp;
        try (
                var reader = new BufferedReader(new FileReader(fileStr));
        ) {
            while ((strTemp = reader.readLine()) != null) {
                log.info("Get line - {} - from file {}", fileStr, strTemp);
                if (strTemp.contains(paramName)) {
                    strTemp = changeLine(strTemp, paramSet);
                }
                inputBuffer.append(strTemp);
                inputBuffer.append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return inputBuffer;
    }

    private String changeLine(String line, String param) {
        String[] tmp = line.split("=");
        log.info("tmp line length: {}", tmp.length);
        tmp[tmp.length - 1] = param;
        line = new StringBuilder().append(tmp[0]).append("=").append(tmp[tmp.length - 1]).toString();
        log.info("Change line {} param to {}", line, param);
        return line;
    }

}
