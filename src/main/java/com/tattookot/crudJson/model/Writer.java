package com.tattookot.crudJson.model;

import java.util.List;

public class Writer {

    private Integer id;
    private String name;
    private List<Post> postList;

    public Writer(String name, List<Post> postList) {
        this.id = null;
        this.name = name;
        this.postList = postList;
    }

    @Override
    public String toString() {
        return "Writer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", postList=" + postList +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }
}
