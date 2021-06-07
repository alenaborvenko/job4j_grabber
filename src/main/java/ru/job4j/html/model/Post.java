package ru.job4j.html.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

public class Post {
    private String autor;
    private String text;
    private String url;
    private LocalDateTime created;

    public Post(String autor, String text, String url, LocalDateTime created) {
        this.autor = autor;
        this.text = text;
        this.url = url;
        this.created = created;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return Objects.equals(autor, post.autor) && Objects.equals(text, post.text)
                && Objects.equals(url, post.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(autor, text, url);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Post.class.getSimpleName() + "[", "]")
                .add("autor='" + autor + "'")
                .add("text='" + text + "'")
                .add("url='" + url + "'")
                .add("created=" + created)
                .toString();
    }
}
