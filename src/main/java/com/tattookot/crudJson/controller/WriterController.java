package com.tattookot.crudJson.controller;

import com.tattookot.crudJson.model.Post;
import com.tattookot.crudJson.model.Writer;
import com.tattookot.crudJson.repository.gson.GsonWriterRepositoryImpl;

import java.util.List;

public class WriterController {
    private final GsonWriterRepositoryImpl repository = new GsonWriterRepositoryImpl();

    public List<Writer> getAllWriters(){
        return repository.getAll();
    }

    public Writer getWriterById(Integer id){
        return repository.getById(id);
    }

    public Writer createWriter(String name, List<Post> postList){
        Writer writer = new Writer(name, postList);
        return repository.create(writer);
    }

    public Writer updateWriter(Writer writer){
        return repository.update(writer);
    }

    public void deleteWriterById(Integer id){
        repository.deleteById(id);
    }
}
