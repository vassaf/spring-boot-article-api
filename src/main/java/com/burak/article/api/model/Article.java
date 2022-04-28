package com.burak.article.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotEmpty(message = "Author is mandatory")
    private String author;

    @Column
    @Length(max = 100, message = "Maximum title length is 100")
    @NotEmpty(message = "Title is mandatory")
    private String title;

    @Column
    @NotEmpty(message = "Content is mandatory")
    private String content;

    @Column
    @NotNull(message = "Date is mandatory")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date date;


}
