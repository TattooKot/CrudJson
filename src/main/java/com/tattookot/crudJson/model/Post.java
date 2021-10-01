package com.tattookot.crudJson.model;

import java.util.List;

public class Post {
    private Integer id;
    private String content;
    private List<Tag> tagList;
    private PostStatus status;


    public Post(Integer id, String content, List<Tag> tagList) {
        this.id = id;
        this.content = content;
        this.tagList = tagList;
        this.status = PostStatus.ACTIVE;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", tagList=" + tagList +
                ", status=" + status +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public PostStatus getStatus() {
        return status;
    }

    public void setStatus(PostStatus status) {
        this.status = status;
    }
}
