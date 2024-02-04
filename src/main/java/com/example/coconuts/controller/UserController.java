package com.example.coconuts.controller;

import com.example.coconuts.code.ResponseCode;
import com.example.coconuts.dto.ResponseDTO;
import com.example.coconuts.dto.s3.S3FileDTO;
import com.example.coconuts.dto.user.UserLoginDTO;
import com.example.coconuts.dto.user.UserRegisterDTO;
import com.example.coconuts.dto.user.UserUpdateDTO;
import com.example.coconuts.dto.user.SearchUserRequestDto;
import com.example.coconuts.dto.user.UserListResponseDto;
import com.example.coconuts.entity.DataEntity;
import com.example.coconuts.entity.ScoreEntity;
import com.example.coconuts.entity.UserEntity;
import com.example.coconuts.projection.user.GetUser;
import com.example.coconuts.service.AmazonS3Service;
import com.example.coconuts.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController // JSON 형태의 결과값 반환
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final AmazonS3Service amazonS3Service;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@RequestBody UserRegisterDTO userRegisterDTO){
        GetUser res = userService.register(userRegisterDTO);

        return ResponseEntity
                .status(ResponseCode.SUCCESS_REGISTER.getStatus().value())
                .body(new ResponseDTO(ResponseCode.SUCCESS_REGISTER, res));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody UserLoginDTO userLoginDTO){
        GetUser res = userService.login(userLoginDTO);

        return ResponseEntity
                .status(ResponseCode.SUCCESS_LOGIN.getStatus().value())
                .body(new ResponseDTO(ResponseCode.SUCCESS_LOGIN, res));

    }

    @GetMapping("/logout")
    public ResponseEntity<ResponseDTO> logout(@RequestParam("loginId") String loginId) {
        userService.logout(loginId);

        return ResponseEntity
                .status(ResponseCode.SUCCESS_LOGOUT.getStatus().value())
                .body(new ResponseDTO(ResponseCode.SUCCESS_LOGOUT, null));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> update(
            @RequestParam(value = "userId") Integer userId,
            @RequestParam(value="image", required = false) MultipartFile profileImage,
            @RequestParam(value = "info") String userUpdateDTO) throws IOException {

        // mapper
        ObjectMapper mapper = new ObjectMapper();
        UserUpdateDTO mapperUserUpdateDTO = mapper.readValue(userUpdateDTO, UserUpdateDTO.class);

        // 이미지 등록
        if (profileImage != null && profileImage.getResource().contentLength() != 0){

            // 예전 유저의 프로필 이미지 삭제
            String res = amazonS3Service.deleteFile(userId);
            System.out.println(res);

            // 이미지 업로드
            S3FileDTO uploadFiles = amazonS3Service.uploadFile(profileImage);

            // updateUserProfileDTO 객체에 프로필 정보 설정
            mapperUserUpdateDTO.setImage(uploadFiles.getUploadFileUrl());
        }

        // 정보 업데이트
        UserEntity res = userService.updateProfile(userId, mapperUserUpdateDTO);

        return ResponseEntity
                .status(ResponseCode.SUCCESS_UPDATE_PROFILE.getStatus().value())
                .body(new ResponseDTO(ResponseCode.SUCCESS_UPDATE_PROFILE, res));
    }


    @GetMapping("/detail")
    public ResponseEntity<ResponseDTO> getProfile(@RequestParam(value = "userId")Integer userId){
        Optional<UserEntity> res = userService.getProfile(userId);

        return ResponseEntity
                .status(ResponseCode.SUCCESS_GET_PROFILE.getStatus().value())
                .body(new ResponseDTO(ResponseCode.SUCCESS_GET_PROFILE, res));
    }

    @GetMapping("/user-list")
    public ResponseEntity<ResponseDTO> getStudentList(){
        List<UserEntity> res = userService.getUserList();

        return ResponseEntity
                .status(ResponseCode.SUCCESS_GET_USER_LIST.getStatus().value())
                .body(new ResponseDTO(ResponseCode.SUCCESS_GET_USER_LIST, res));

    }

    @GetMapping("/scores")
    public ResponseEntity<ResponseDTO> getUserScoresList(@RequestParam(value = "userId")Integer userId){
        System.out.println(userId);
        List<ScoreEntity> res = userService.getUserScoreList(userId);


        return ResponseEntity
                .status(ResponseCode.SUCCESS_GET_USER_SCORES_LIST.getStatus().value())
                .body(new ResponseDTO(ResponseCode.SUCCESS_GET_USER_SCORES_LIST, res));

    }
  
   @GetMapping("/search")
    public ResponseEntity<ResponseDTO> searchUsers(@RequestParam("q") String query,
                                                   @RequestParam("userId") Integer userId) {
        List<UserListResponseDto> res = userService.searchUsers(userId, query);

        return ResponseEntity
                .status(ResponseCode.SUCCESS_SEARCH_USERS.getStatus().value())
                .body(new ResponseDTO(ResponseCode.SUCCESS_SEARCH_USERS, res));
    }


}
