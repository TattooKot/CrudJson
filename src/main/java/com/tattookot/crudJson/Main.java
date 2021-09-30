package com.tattookot.crudJson;

import com.tattookot.crudJson.model.Tag;
import com.tattookot.crudJson.repository.gson.GsonTagRepositoryImpl;

public class Main {
    public static void main(String[] args) {
        GsonTagRepositoryImpl repository = new GsonTagRepositoryImpl();


        System.out.println("Creating new tags:");
        Tag newTag = new Tag(5, "Name1");
        Tag newTag1 = new Tag(5, "Name2");
        Tag newTag2 = new Tag(5, "Name3");
        repository.create(newTag);
        repository.create(newTag1);
        repository.create(newTag2);


        System.out.println("All tags:");
        repository.getAll().forEach(System.out::println);
        System.out.println();


        System.out.println("First element from tags.json");
        System.out.println(repository.getById(1));
        System.out.println();


        System.out.println("Update element:");
        System.out.println(repository.update(new Tag(3, "Vasiliy")));
        System.out.println();

        System.out.println("All tags:");
        repository.getAll().forEach(System.out::println);
        System.out.println();

        System.out.println("Delete element with id 3:");
        repository.deleteById(3);
        System.out.println();

        System.out.println("All tags:");
        repository.getAll().forEach(System.out::println);
        System.out.println();


    }
}