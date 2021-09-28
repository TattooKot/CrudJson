package com;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TagRepository {

    Path path = Path.of("src/main/java/com/Tags.json");
    Gson gson = new Gson();

    public Tag getById(int id){
        List<Tag> tags = getAll();

        if(tags == null)
            return null;

        for(int i = 0; i<tags.size(); i++) {
            Tag tag = tags.get(i);
            if (tag.getId() == id)
                return tag;
        }

        return null;
    }

    public List<Tag> getAll(){
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

    public Tag create(Tag tag){

        if(tag.getName() == null)
            return null;

        List<Tag> tagList = getAll();

        if(tagList != null) {

            if(tagList.contains(tag))
                return tag;

            tagList.sort(Comparator.comparingInt(Tag::getId));
            int id = tagList.get(tagList.size() - 1).getId() + 1;
            tag.setId(id);

        } else {
            tagList = new ArrayList<>();
            tag.setId(1);
        }

        tagList.add(tag);
        try(FileWriter writer = new FileWriter(path.toFile())) {
            writer.write(gson.toJson(tagList));
            return tag;
        } catch (IOException e) {
            System.out.println("IOException");
        }
        return null;
    }

    public Tag save(Tag tag){

        if(tag == null)
            return null;

        List<Tag> tagList = getAll();

        if(tagList == null)
            return null;

        for(int i = 0; i<tagList.size(); i++){
            Tag element = tagList.get(i);

            if(element.getId() == tag.getId()){
                tagList.set(i, tag);

                try(FileWriter writer = new FileWriter(path.toFile())) {
                    writer.write(gson.toJson(tagList));
                    return tag;
                } catch (IOException e) {
                    System.out.println("IOException");
                }
            }

        }

        return null;
    }

    public void deleteById(int id){
        if(id == 0)
            throw new IllegalArgumentException();

        List<Tag> tagList = getAll();

        if(tagList == null)
            return;

        for(int i = 0; i<tagList.size(); i++){
            Tag element = tagList.get(i);

                if(element.getId() == id){
                    tagList.remove(element);

                    try(FileWriter writer = new FileWriter(path.toFile())) {
                        writer.write(gson.toJson(tagList));
                        break;
                    } catch (IOException e) {
                        System.out.println("IOException");
                    }
                }
        }

    }


}
