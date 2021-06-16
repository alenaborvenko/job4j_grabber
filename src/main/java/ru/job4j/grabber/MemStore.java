package ru.job4j.grabber;

import java.util.ArrayList;
import java.util.List;

public class MemStore implements Store {
    private int count = 0;
    private List<Post> posts = new ArrayList<>();

    @Override
    public void save(Post post) {
        post.setId(count++);
        posts.add(post);
    }

    @Override
    public List<Post> getAll() {
        return new ArrayList<>(posts);
    }

    @Override
    public Post findById(String id) {
        return posts.stream().filter(s -> s.getId() == Integer.parseInt(id)).findFirst()
                .orElse(null);
    }
}
