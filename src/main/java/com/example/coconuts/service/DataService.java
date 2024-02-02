package com.example.coconuts.service;

import com.example.coconuts.dto.data.DataResponseDto;

import java.util.List;

public interface DataService {

    DataResponseDto getRandomData(String language);

}
