package com.example.coconuts.dto.data;

import com.example.coconuts.entity.DataEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DataResponseDto {

    private Integer dataId; // data id

    private String path;

    private Long size;   // 바이트 단위

    private String name;

    private String language;

    private String fileContent;

    public DataResponseDto(DataEntity data) {
        this.dataId = data.getId();
        this.path = data.getPath();
        this.size = data.getSize();
        this.name = data.getName();
        this.language = data.getLanguage();
    }

}
