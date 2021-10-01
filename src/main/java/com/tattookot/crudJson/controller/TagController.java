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

    public Tag getById(Integer id){
        return repository.getById(id);
    }

    public Tag updateTag(Integer id, String name){
        Tag tag = new Tag(id, name);
        return repository.update(tag);
    }

    public void deleteTagById(Integer id){
        repository.deleteById(id);
    }
}
