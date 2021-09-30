package com.tattookot.crudJson.controller;

import com.tattookot.crudJson.model.Tag;
import com.tattookot.crudJson.repository.gson.GsonTagRepositoryImpl;

public class TagController {

    private final GsonTagRepositoryImpl repository = new GsonTagRepositoryImpl();

    public Tag createTag(String name){
        Tag tag = new Tag(null, name);
        return repository.create(tag);
    }
}
