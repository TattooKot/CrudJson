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
        System.out.println("Enter id: ");
        String id = scanner.nextLine();
        System.out.println("Tag with id " + id+ ": " + controller.getById(id));
    }

    public void updateTag(){
        System.out.println("Enter id: ");
        String id = scanner.nextLine();
        System.out.println("Enter changed name: ");
        String name = scanner.nextLine();
        Tag tag = controller.updateTag(id, name);
        System.out.printf("Tag with id %s %s updated\n", id, tag == null ? "wasn`t" : "was");
    }

    public void deleteTagById(){
        System.out.println("Enter id: ");
        String id = scanner.nextLine();
        if(!id.matches("\\d+")){ //перевіряти треба тут?
            System.out.println("Wrong id");
        }
        controller.deleteTagById(id);
        System.out.println("Tag with id %s was deleted");
    }

}
