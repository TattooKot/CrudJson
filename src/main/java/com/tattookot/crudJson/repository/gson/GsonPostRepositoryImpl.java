package com.tattookot.crudJson.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tattookot.crudJson.model.Post;
import com.tattookot.crudJson.repository.PostRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class GsonPostRepositoryImpl implements PostRepository {

    private final Path path = Path.of("src/main/resources/posts.json");
    private final Gson gson = new Gson();

    @Override
    public List<Post> getAll() {
        return getAllPostsInternal();
    }

    @Override
    public Post getById(Integer id) {
        return getAllPostsInternal().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Post create(Post post) {
        List<Post> allPosts = getAllPostsInternal();
        Integer newMaxId = generateMaxId(allPosts);
        post.setId(newMaxId);
        allPosts.add(post);
        writePostsToFile(allPosts);
        return post;
    }

    @Override
    public Post update(Post post) {
        List<Post> postList = getAllPostsInternal();
        Post updated = postList.stream()
                .filter(p -> p.getId().equals(post.getId()))
                .findFirst()
                .orElse(null);
        if(updated == null){
            return null;
        }
        postList.set(postList.indexOf(updated), post);
        writePostsToFile(postList);
        return post;
    }

    @Override
    public void deleteById(Integer id) {
        List<Post> postList = getAllPostsInternal();
        postList.removeIf(p -> p.getId().equals(id));
        writePostsToFile(postList);
    }

    private List<Post> getAllPostsInternal(){
        List<Post> allPosts = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))){
            String json = reader.readLine();
            Type listType = new TypeToken<ArrayList<Post>>(){}.getType();
            allPosts = gson.fromJson(json, listType);
        } catch (IOException e) {
            System.out.println("In getAllPostsInternal - error occurred: " + e.getMessage());
        }
        return allPosts;
    }

    private int generateMaxId(List<Post> postList){
        Post post = postList.stream().max(Comparator.comparingInt(Post::getId)).orElse(null);
        return Objects.nonNull(post) ? post.getId() + 1 : 1;
    }

    private void writePostsToFile(List<Post> postList){
        try(FileWriter writer = new FileWriter(path.toFile())){
            writer.write(gson.toJson(postList));
        } catch (IOException e) {
            System.out.println("In writePostToFile - error occurred: " + e.getMessage());
        }
    }
}
