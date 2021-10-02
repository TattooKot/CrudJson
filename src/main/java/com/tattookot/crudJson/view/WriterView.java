package com.tattookot.crudJson.view;

import com.tattookot.crudJson.controller.PostController;
import com.tattookot.crudJson.controller.WriterController;
import com.tattookot.crudJson.model.Post;
import com.tattookot.crudJson.model.PostStatus;
import com.tattookot.crudJson.model.Tag;
import com.tattookot.crudJson.model.Writer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class WriterView {
    private final Scanner scanner = new Scanner(System.in);
    private final WriterController writerController = new WriterController();
    private final PostController postController = new PostController();

    public void getAllWriters(){
        System.out.println("All writers:");
        writerController.getAllWriters().forEach(System.out::println);
    }

    public void getWriterById(){
        int id = getIdFromConsole();
        System.out.println(writerController.getWriterById(id));
    }

    public void createWriter(){
        System.out.println("Enter writers name:");
        String name = scanner.nextLine();
        List<Post> postList = getPostListFromConsole();
        System.out.println("Writer created: " + writerController.createWriter(name, postList));
    }

    public void updateWriter(){
        int id = getIdFromConsole();
        if(id == -1){
            return;
        }
        Writer updatedWriter = writerController.getWriterById(id);

        System.out.println("What you wanna change:\n1.Name; 2.PostList;\n(Enter number)");
        String changeNum = scanner.nextLine();

        switch (changeNum) {
            case "1" -> {
                System.out.println("Enter new name: ");
                String newName = scanner.nextLine();
                updatedWriter.setName(newName);
            }
            case "2" -> {
                List<Post> postList = getPostListFromConsole();
                updatedWriter.setPostList(postList);
            }
            default -> {
                System.out.println("Wrong option, try again");
                return;
            }
        }
        System.out.println("Updated writer: " + writerController.updateWriter(updatedWriter));
    }

    public void deleteWriterById(){
        int id = getIdFromConsole();
        if(id == -1){
            return;
        }
        writerController.deleteWriterById(id);
        System.out.println("Writer deleted");
    }

    private int getIdFromConsole(){
        System.out.println("Enter id:");
        String id = scanner.nextLine();
        if(!id.matches("\\d+")){
            System.out.println("Wrong id type");
            return -1;
        }
        int numId = Integer.parseInt(id);
        if(Objects.isNull(writerController.getWriterById(numId))){
            System.out.println("Writer does not exist");
            return -1;
        }
        return numId;
    }

    private List<Post> getPostListFromConsole(){
        System.out.println("Enter post id:\n(After writing all ids, enter 'q' to exit)");
        List<Post> postList = new ArrayList<>();
        String id;
        while(!(id = scanner.nextLine()).equals("q")){
            if(!id.matches("\\d+")){
                System.out.println("Wrong id type");
                continue;
            }
            int numId = Integer.parseInt(id);
            Post post = postController.getPostById(numId);
            if(Objects.isNull(post)){
                System.out.println("Post does not exist");
                continue;
            }
            postList.add(post);
            System.out.println("Post added!");
        }
        return postList;
    }
}
