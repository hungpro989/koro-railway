package com.example.test.dto;

import com.example.test.entity.Image;
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
