package com.boot.model.dto;

import com.boot.model.User;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * MessageDto.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 9/13/2020
 */
public class MessageDto {
    @Column(name = "id_message")
    private Long id;
    @NotEmpty(message = "Поле не может быть пустым")
    @Length(max = 2048, message = "Coобщение слишком длинное(больше чем 2kB)")
    private String text;
    @Length(max = 255, message = "Coобщение слишком длинное(больше чем  255)")
    @NotBlank(message = "Поле не может быть пустым")
    private String tag;

    public MessageDto(final String text, final String tag, User user) {
        this.text = text;
        this.tag = tag;
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

}
