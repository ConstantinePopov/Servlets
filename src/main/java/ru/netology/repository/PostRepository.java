package ru.netology.repository;

import ru.netology.model.Post;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

// Stub
public class PostRepository {
    private final ConcurrentHashMap<Long, Post> postDatabase;
    private final AtomicInteger numberOfPosts = new AtomicInteger(0);

    public PostRepository() {
        postDatabase = new ConcurrentHashMap<>();
    }

    public List<Post> all() {
        return postDatabase.values().stream().toList();
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(postDatabase.get(id));
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            long nextId = numberOfPosts.incrementAndGet();
            post.setId(nextId);
            postDatabase.put(nextId,post);
        } else if (post.getId() != 0 && post.getId() < numberOfPosts.longValue()) {
            postDatabase.put(post.getId(), post);
        }
        return post;
    }

    public void removeById(long id) {
        postDatabase.remove(id);
        numberOfPosts.decrementAndGet();
    }
}
