package app.util;

import app.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Helper {


    public static void writeUser(User user){
        ObjectMapper mapper = new ObjectMapper();
        try (BufferedOutputStream bufferedWriter = new BufferedOutputStream(new FileOutputStream("src/main/resources/files/userData.txt", true))){
            mapper.writeValue(bufferedWriter, user);

            //it is work right but other way doesn't
            Files.write(Paths.get("src/main/resources/files/userData.txt"), " \n".getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static User readUserForName(String firstName, String lastName){
        String jsonLine = getUserFromTheFile(firstName, lastName);
        if (!jsonLine.isEmpty()) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                User user = mapper.readValue(jsonLine, User.class);
                return user;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static List<User> readUserFromTheFile(File file, boolean deleteFile){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return Files.lines(file.toPath(), Charset.forName("windows-1251"))
                    .filter(str-> str.trim().startsWith("{\"") && str.trim().endsWith("\"}"))
                    .map(line-> {
                        try {
                            return mapper.readValue(line, User.class);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }).filter(Objects::nonNull)
                    .filter(Checkings::checkUser)
                    .collect(Collectors.toList());
        } catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (deleteFile) Files.delete(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }


    private static String getUserFromTheFile(String firstName, String lastName) {
        try {
            List<String> lines = Files.lines(Paths.get("src/main/resources/files/userData.txt")).collect(Collectors.toList());
            for (String line: lines) {
                if (line.contains(String.format("\"firstName\":\"%s\"", firstName))
                    && line.contains(String.format("\"lastName\":\"%s\"", lastName))) return line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}



