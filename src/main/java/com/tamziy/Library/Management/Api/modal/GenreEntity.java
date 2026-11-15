package com.tamziy.Library.Management.Api.modal;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Genre code is mandatory")
    private String code;

    @NotBlank(message = "Genre name is mandatory")
    private String name;

    @Size(max = 500, message = "desription must not exceed 500 characters")
    private String description;

    @Min(value = 0, message = "Display order cannot be negative")
    private Integer displayOrder;

    @Column(nullable = false)
    private Boolean active=true;

    @ManyToOne
    private GenreEntity parentGenre;

    @OneToMany
    private List<GenreEntity> subGenres = new ArrayList<GenreEntity>();

    //@OneToMany(mappedBy = "genre", cascade = CascadeType.PERSIST)
    //private List<Book> books = new ArrayList<Book>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @CreationTimestamp
    private LocalDateTime updatedAt;
}
