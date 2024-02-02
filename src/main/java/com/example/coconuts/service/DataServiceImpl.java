package com.example.coconuts.service;

import com.example.coconuts.code.ErrorCode;
import com.example.coconuts.dto.data.DataResponseDto;
import com.example.coconuts.dto.friend.FriendListResponseDto;
import com.example.coconuts.entity.DataEntity;
import com.example.coconuts.entity.FriendEntity;
import com.example.coconuts.exception.DataNotFoundException;
import com.example.coconuts.repository.DataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DataServiceImpl implements DataService{

    private final DataRepository dataRepository;

    @Override
    public DataResponseDto getRandomData(String language) {
        List<DataEntity> dataList = dataRepository.findByLanguage(language);

        if(dataList == null || dataList.isEmpty()) {
            throw new DataNotFoundException(ErrorCode.DATA_NOT_FOUND);
        }

        Random random = new Random();
        DataEntity randomData = dataList.get(random.nextInt(dataList.size()));

        // 파일 내용을 읽어와서 DataResponseDto에 추가
        String fileContent = readFileContent(randomData.getPath());
        DataResponseDto responseDto = new DataResponseDto(randomData);
        responseDto.setFileContent(fileContent);

        return responseDto;
    }

    private String readFileContent(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder contentBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n"); // 파일의 각 줄을 출력
            }
            String fileContent = contentBuilder.toString();
            return fileContent;
        } catch (IOException e) {
            e.printStackTrace();
            return "파일이 존재하지 않습니다.";
        }
    }


}
