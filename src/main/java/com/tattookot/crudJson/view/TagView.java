package com.tattookot.crudJson.view;

import com.tattookot.crudJson.controller.TagController;
import com.tattookot.crudJson.model.Tag;

import java.util.Scanner;

public class TagView {

    private final Scanner scanner = new Scanner(System.in);
    private final TagController controller = new TagController();

    public void createTag(){
        System.out.println("Enter name for tag: ");
        String name = scanner.nextLine();
        Tag newTag = controller.createTag(name);
        System.out.println("Tag: " + newTag + " was created!");
    }

    public void getAllTags(){
        System.out.println("All tags: ");
        controller.getAllTags().forEach(System.out::println);
    }

    public void getTagById(){
        int id = getIdFromConsole();
        if(id == -1){
            return;
        }
        System.out.printf("Tag with id %s: %s\n", id, id>0 ? controller.getById(id) : "does not exist");
    }

    public void updateTag(){
        int id = getIdFromConsole();
        if(id == -1){
            return;
        }

        System.out.println("Enter changed name: ");
        String name = scanner.nextLine();
        Tag tag = controller.updateTag(id, name);
        System.out.printf("Tag with id %s %s updated\n", id, tag == null ? "wasn`t" : "was");
    }

    public void deleteTagById(){
        int id = getIdFromConsole();
        if(id == -1){
            return;
        }
        controller.deleteTagById(id);
        System.out.println("Tag with id " + id +" was deleted");
    }

    private int getIdFromConsole(){
        System.out.println("Enter id: ");
        String id = scanner.nextLine();

        if(!id.matches("\\d+")){
            System.out.println("Wrong id type");
            return -1;
        }

        int numId = Integer.parseInt(id);
        if(controller.getById(numId) == null){
            System.out.println("Tag not found");
            return -1;
        }
        return numId;
    }
}
