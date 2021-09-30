package com.tattookot.crudJson;

import com.tattookot.crudJson.view.TagView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TagView tagView = new TagView();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Write smth: ");

        while(true) {
            String text = scanner.nextLine();
            if (text.equals("q")) break;
            if (text.equals("get all")) tagView.getAllTags();
            if (text.equals("id")) tagView.getTagById();
            if (text.equals("create")) tagView.createTag();
            if (text.equals("delete")) tagView.deleteTagById();
            if (text.equals("update")) tagView.updateTag();
        }
    }
}
