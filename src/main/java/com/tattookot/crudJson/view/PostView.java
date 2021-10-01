package com.tattookot.crudJson.view;

import com.tattookot.crudJson.controller.PostController;
import com.tattookot.crudJson.controller.TagController;
import com.tattookot.crudJson.model.Post;
import com.tattookot.crudJson.model.PostStatus;
import com.tattookot.crudJson.model.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PostView {
    private final PostController postController = new PostController();
    private final TagController tagController = new TagController();
    private final Scanner scanner = new Scanner(System.in);

    public void getAllPosts(){
        System.out.println("All posts:");
        postController.getAllPosts().forEach(System.out::println);
    }

    public void getPostById(){
        int id = getIdFromConsole();
        if(id == -1){
            return;
        }
        System.out.println(postController.getPostById(id));
    }

    public void createPost(){
        System.out.println("Write new post:");
        String content = getContentFromConsole();
        System.out.println("Enter tag ids for your post:");
        List<Tag> tags = getTagListFromConsole();
        System.out.println("Post created!\n" + postController.createPost(content,tags));
    }

    public void updatePost(){
        int id = getIdFromConsole();
        if(id == -1){
            return;
        }
        Post updatedPost = postController.getPostById(id);

        System.out.println("What you wanna change:\n1.Content; 2.Tags; 3.PostStatus\n(Enter number)");
        String changeNum = scanner.nextLine();

        switch (changeNum) {
            case "1" -> {
                String newContent = getContentFromConsole();
                updatedPost.setContent(newContent);
            }
            case "2" -> {
                List<Tag> tagList = getTagListFromConsole();
                updatedPost.setTagList(tagList);
            }
            case "3" -> {
                if (updatedPost.getStatus().equals(PostStatus.ACTIVE)) {
                    updatedPost.setStatus(PostStatus.DELETED);
                } else {
                    updatedPost.setStatus(PostStatus.ACTIVE);
                }
                System.out.println("PostStatus changed");
            }
            default -> {
                System.out.println("Wrong option, try again");
                return;
            }
        }
        System.out.println("Updated post: " + postController.updatePost(updatedPost));
    }

    public void deletePostById(){
        int id = getIdFromConsole();
        if(id == -1){
            return;
        }
        postController.deletePostById(id);
        System.out.println("Post with id " + id + " was deleted");
    }

    private int getIdFromConsole(){
        System.out.println("Enter id: ");
        String id = scanner.nextLine();

        if(!id.matches("\\d+")){
            System.out.println("Wrong id type");
            return -1;
        }

        int numId = Integer.parseInt(id);
        if(postController.getPostById(numId) == null){
            System.out.println("Post not found");
            return -1;
        }
        return numId;
    }

    private String getContentFromConsole(){
        System.out.println("(After writing the post, enter 'q' to exit)");
        StringBuilder content = new StringBuilder();
        String line;

        while(!(line = scanner.nextLine()).equals("q")){
            content.append(line).append("\\n");
        }

        return content.toString();
    }

    private List<Tag> getTagListFromConsole(){

        System.out.println("(After writing all ids, enter 'q' to exit)");

        List<Tag> tags = new ArrayList<>();
        String id;
        while(!(id = scanner.nextLine()).equals("q")){
            if(!id.matches("\\d+")){
                System.out.println("Wrong id type");
                continue;
            }

            Tag tag = tagController.getById(Integer.parseInt(id));
            if(tag == null){
                System.out.println("Tag with id " + id + " does not exist");
                continue;
            }

            tags.add(tag);
            System.out.println("Tag with id " + id + " added");
        }
        return tags;
    }
}
