package com.tattookot.crudJson;

import com.tattookot.crudJson.view.PostView;
import com.tattookot.crudJson.view.TagView;
import com.tattookot.crudJson.view.WriterView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TagView tagView = new TagView();
        PostView postView = new PostView();
        WriterView writerView = new WriterView();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Write smth: ");

        while(true) {
            String text = scanner.nextLine();
            if (text.equals("qq")) break;
            if (text.equals("get all tags")) tagView.getAllTags();
            if (text.equals("tag id")) tagView.getTagById();
            if (text.equals("create tag")) tagView.createTag();
            if (text.equals("delete tag")) tagView.deleteTagById();
            if (text.equals("update tag")) tagView.updateTag();

            if (text.equals("get all posts")) postView.getAllPosts();
            if (text.equals("post id")) postView.getPostById();
            if (text.equals("create post")) postView.createPost();
            if (text.equals("delete post")) postView.deletePostById();
            if (text.equals("update post")) postView.updatePost();

            if (text.equals("get all writers")) writerView.getAllWriters();
            if (text.equals("writer id")) writerView.getWriterById();
            if (text.equals("create writer")) writerView.createWriter();
            if (text.equals("delete writer")) writerView.deleteWriterById();
            if (text.equals("update writer")) writerView.updateWriter();
        }
    }
}
