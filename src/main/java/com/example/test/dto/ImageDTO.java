package com.example.test.dto;

import com.example.test.models.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {
    private Integer id;
    private String urlImage;
    private String fileName;
    public ImageDTO(Image image) {
        this.id = image.getId();
        this.urlImage = image.getUrlImage();
        this.fileName=image.getFileName();
    }
}
