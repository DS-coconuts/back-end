package com.example.coconuts.service;

import com.example.coconuts.code.ErrorCode;
import com.example.coconuts.dto.data.DataResponseDto;
import com.example.coconuts.entity.DataEntity;
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

@Service
@RequiredArgsConstructor
public class DataServiceImpl implements DataService{

    private final DataRepository dataRepository;

    @Override
    public DataResponseDto getRandomData(String language) {
        // 관련 언어에 대한 모든 코드 가져오기
        List<DataEntity> dataList = dataRepository.findByLanguage(language);

        if(dataList == null || dataList.isEmpty()) {
            throw new DataNotFoundException(ErrorCode.DATA_NOT_FOUND);
        }

        // 가져온 데이터 리스트 중 랜던으로 하나 선택
        Random random = new Random();
        DataEntity randomData = dataList.get(random.nextInt(dataList.size()));

        // 파일 내용을 읽어와서 DataResponseDto에 추가
        String fileContent = readFileContent(randomData.getPath());
        DataResponseDto responseDto = new DataResponseDto(randomData);
        responseDto.setFileContent(fileContent);

        return responseDto;
    }

    // 파일 내용을 읽어 문자열(이스케이프 문자가 추가된 상황)로 반환하기
    private String readFileContent(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder contentBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n"); // 파일의 각 줄을 추가
            }
            String fileContent = contentBuilder.toString();
            return fileContent;
        } catch (IOException e) {
            e.printStackTrace();
            return "파일이 존재하지 않습니다.";
        }
    }


}
