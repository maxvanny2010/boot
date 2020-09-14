package com.boot.model;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * Message.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 9/2/2020
 */
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_message")
    private Long id;
    @NotEmpty(message = "Поле не может быть пустым")
    @Length(max = 2048, message = "Coобщение слишком длинное(больше чем 2kB)")
    private String text;
    @Length(max = 255, message = "Coобщение слишком длинное(больше чем  255)")
    @NotBlank(message = "Поле не может быть пустым")
    private String tag;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;
    @Type(type = "text")
    private String filename;

    public Message(final String text, final String tag, User user) {
        this.text = text;
        this.tag = tag;
        this.author = user;
    }

    public Message() {
    }

    public String getAuthorName() {
        return this.author != null ? this.author.getUsername() : "NONE";
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(final String tag) {
        this.tag = tag;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(final User author) {
        this.author = author;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(final String filename) {
        this.filename = filename;
    }
}
