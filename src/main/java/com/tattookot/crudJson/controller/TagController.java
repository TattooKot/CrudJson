package com.tattookot.crudJson.controller;

import com.tattookot.crudJson.model.Tag;
import com.tattookot.crudJson.repository.gson.GsonTagRepositoryImpl;

import java.util.List;

public class TagController {

    private final GsonTagRepositoryImpl repository = new GsonTagRepositoryImpl();

    public Tag createTag(String name){
        Tag tag = new Tag(null, name);
        return repository.create(tag);
    }

    public List<Tag> getAllTags(){
        return repository.getAll();
    }

    public Tag getById(String id){

        // чи тут?
        return id.matches("\\d+") ? repository.getById(Integer.parseInt(id)) : null;
    }

    public Tag updateTag(String id, String name){
        if(!id.matches("\\d+")){ // або так
            return null;
        }
        Integer numId = Integer.parseInt(id);
        Tag tag = new Tag(numId, name);
        return repository.update(tag);
    }

    public void deleteTagById(String id){
        repository.deleteById(Integer.parseInt(id));
    }
}
