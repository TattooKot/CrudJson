package com.tattookot.crudJson.controller;

import com.tattookot.crudJson.model.Post;
import com.tattookot.crudJson.model.Tag;
import com.tattookot.crudJson.repository.gson.GsonPostRepositoryImpl;

import java.util.List;

public class PostController {
    private final GsonPostRepositoryImpl repository = new GsonPostRepositoryImpl();

    public List<Post> getAllPosts(){
        return repository.getAll();
    }

    public Post getPostById(Integer id){
        return repository.getById(id);
    }

    public Post createPost(String content, List<Tag> tagList){
        Post post = new Post(content, tagList);
        return repository.create(post);
    }

    public Post updatePost(Post post){
        return repository.update(post);
    }

    public void deletePostById(Integer id){
        repository.deleteById(id);
    }
}
