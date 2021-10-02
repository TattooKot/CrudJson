package com.tattookot.crudJson.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tattookot.crudJson.model.Writer;
import com.tattookot.crudJson.repository.WriterRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class GsonWriterRepositoryImpl implements WriterRepository {
    private final Path path = Path.of("src/main/resources/writers.json");
    private final Gson gson = new Gson();

    @Override
    public List<Writer> getAll() {
        return getAllWritersInternal();
    }

    @Override
    public Writer getById(Integer id) {
        List<Writer> writers = getAllWritersInternal();
        return writers.stream()
                .filter(w -> w.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Writer create(Writer writer) {
        List<Writer> writers = getAllWritersInternal();
        writer.setId(generateMaxId(writers));
        writers.add(writer);
        writeWritersToFile(writers);
        return writer;
    }

    @Override
    public Writer update(Writer writer) {
        List<Writer> writerList = getAllWritersInternal();
        Writer updated = writerList.stream()
                .filter(w -> w.getId().equals(writer.getId()))
                .findFirst()
                .orElse(null);
        if(updated == null){
            return null;
        }
        writerList.set(writerList.indexOf(updated), writer);
        writeWritersToFile(writerList);
        return writer;
    }

    @Override
    public void deleteById(Integer id) {
        List<Writer> writerList = getAllWritersInternal();
        writerList.removeIf(w -> w.getId().equals(id));
        writeWritersToFile(writerList);
    }

    private List<Writer> getAllWritersInternal(){
        List<Writer> postList = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))){
            String json = reader.readLine();
            Type listType = new TypeToken<List<Writer>>(){}.getType();
            postList = gson.fromJson(json, listType);
        } catch (IOException e) {
            System.out.println("In getAllPostsInternal - error occurred: " + e.getMessage());
        }
        return postList;
    }

    private int generateMaxId(List<Writer> writers){
        Writer writer = writers.stream().max(Comparator.comparingInt(Writer::getId)).orElse(null);
        return Objects.nonNull(writer) ? writer.getId() + 1 : 1 ;
    }

    private void writeWritersToFile(List<Writer> writers){
        try(FileWriter fileWriter = new FileWriter(path.toFile())){
            fileWriter.write(gson.toJson(writers));
        } catch (IOException e) {
            System.out.println("In writeWritersToFile - error occurred: " + e.getMessage());
        }
    }
}
