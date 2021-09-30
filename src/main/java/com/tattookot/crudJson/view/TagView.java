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
}
