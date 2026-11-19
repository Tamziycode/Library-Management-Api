package com.tamziy.Library.Management.Api.payload.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO {

    private Long id;

    @NotBlank(message = "ISBN is required")
    private String isbn;

    @NotBlank(message = "Title is required")
    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    private String title;

    @NotBlank(message = "Author is required")
    @Size(min = 1, max = 255, message = "Author Name must be between 1 and 255 characters")
    private String author;

    @NotNull(message = "Genre is required")
    private Long genreId;

    private String genreName;

    private String genreCode;

    @Size(max = 100 , message = "Publisher name must not exceed 100 characters")
    private String publisher;

    private LocalDate publishedDate;

    @Size(max = 20, message = "Language must not exceed 20 characters")
    private String language;

    @Min(value = 1, message = "Must be at least 1 page")
    @Max(value = 50000, message = "Must not be more than 50000 pages")
    private Integer pages;

    @Size(max = 2000, message = "Description must not exceed 2000 characters")
    private String description;

    @Min(value = 0 , message = "Total copies cannot be negative")
    @NotNull(message = "Total copies is required")
    private Integer totalCopies;

    @Min(value = 0, message = "Available copies cannot be negative")
    @NotNull(message = "Available copies is required")
    private Integer availableCopies;

    @DecimalMin(value = "0.0", inclusive = true, message = "Price cannot be negative")
    @Digits(integer = 8, fraction = 2, message = "Price must have at most 8 integer digits")
    private BigDecimal price;

    private String coverImageUrl;

    @NotNull(message = "Active status is required")
    private Boolean active = true;

    private Boolean alreadyHaveReservation;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @AssertTrue(message = "Available copies cannot exceed total copies")
    public Boolean isAvailableCopiesValid() {
        if (totalCopies == null || availableCopies == null) {
            return true;
        }
        return availableCopies <= totalCopies;
    }
}
