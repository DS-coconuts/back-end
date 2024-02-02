package com.example.coconuts.controller;

import com.example.coconuts.code.ResponseCode;
import com.example.coconuts.dto.ResponseDTO;
import com.example.coconuts.dto.data.DataResponseDto;
import com.example.coconuts.service.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // JSON 형태의 결과값 반환
@RequiredArgsConstructor
@RequestMapping("/api/data")
public class DataController {

    private final DataService dataService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getData(@RequestParam("language") String language) {

        DataResponseDto res = dataService.getRandomData(language);

        return ResponseEntity
                .status(ResponseCode.SUCCESS_GET_DATA.getStatus().value())
                .body(new ResponseDTO(ResponseCode.SUCCESS_GET_DATA, res));
    }
}
