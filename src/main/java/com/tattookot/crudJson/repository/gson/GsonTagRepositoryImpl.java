package com.tattookot.crudJson.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tattookot.crudJson.model.Tag;
import com.tattookot.crudJson.repository.TagRepository;

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

public class GsonTagRepositoryImpl implements TagRepository {

    public final Path path = Path.of("src/main/resources/tags.json");
    public final Gson gson = new Gson();

    public Tag getById(Integer id){
        return getAllTagsInternal().stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Tag> getAll(){
        return getAllTagsInternal();
    }

    public Tag create(Tag tag){
        List<Tag> tagList = getAllTagsInternal();
        Integer newMaxId = generateMax(tagList);
        tag.setId(newMaxId);
        tagList.add(tag);
        writeTagsToFIle(tagList);
        return tag;
    }

    public Tag update(Tag tag){
        List<Tag> tagList = getAllTagsInternal();
        Tag update = tagList.stream()
                .filter(t -> t.getId().equals(tag.getId()))
                .findFirst()
                .orElse(null);

        if(update == null){
            return null;
        }

        tagList.set(tagList.indexOf(update), tag);
        writeTagsToFIle(tagList);
        return tag;
    }

    public void deleteById(Integer id){
        List<Tag> tagList = getAllTagsInternal();
        tagList.removeIf(t -> t.getId().equals(id));
        writeTagsToFIle(tagList);
    }

    private List<Tag> getAllTagsInternal(){
        List<Tag> allTags = null;

        try(BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            String jsonString = reader.readLine();
            Type listType = new TypeToken<ArrayList<Tag>>() { }.getType();
            allTags = gson.fromJson(jsonString,listType);

        } catch (IOException e) {
            System.out.println("IOException");
        }

        return allTags;
    }

    private Integer generateMax(List<Tag> tags){
        Tag tag = getAllTagsInternal().stream().max(Comparator.comparingInt(Tag::getId)).orElse(null);
        return Objects.nonNull(tag) ? tag.getId() + 1 : 1;
    }

    private void writeTagsToFIle(List<Tag> tagList){
        try(FileWriter writer = new FileWriter(path.toFile())) {
            writer.write(gson.toJson(tagList));
        } catch (IOException e) {
            System.out.println("In writeTagsToFile - error occurred: " + e.getMessage());
        }
    }
}