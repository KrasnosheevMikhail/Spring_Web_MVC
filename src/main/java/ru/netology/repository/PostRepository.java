package ru.netology.repository;

import ru.netology.model.Post;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;


public class PostRepository {

    private final List<Post> posts = new CopyOnWriteArrayList<>();
    private int idCounter = 0;

    public List<Post> all() {
        return posts;
    }

    public Optional<Post> getById(long id) {
        for (Post entry : posts) {
            if (entry.getId() == id)
                return Optional.of(entry);
        }
        return Optional.empty();
    }

    public Post save(Post post) {

        if (post.getId() == 0) {
            post.setId(++idCounter);
            if (!posts.contains(post)) posts.add(post);
            return post;
        }

        if (post.getId() == 1) {
            posts.add(post);
            idCounter++;
            return post;
        }

        for (Post entry : posts) {
            if (entry.getId() == post.getId()) {
                entry.setContent(post.getContent());
                return post;
            }
        }

        posts.add(post);
        idCounter++;
        return post;
    }

    public void removeById(long id) {
        posts.removeIf(post -> id == post.getId());

    }
}
