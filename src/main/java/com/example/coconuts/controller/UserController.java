package com.example.coconuts.controller;

import com.example.coconuts.code.ResponseCode;
import com.example.coconuts.dto.ResponseDTO;
import com.example.coconuts.dto.user.UserLoginDTO;
import com.example.coconuts.dto.user.UserRegisterDTO;
import com.example.coconuts.dto.user.UserUpdateDTO;
import com.example.coconuts.entity.UserEntity;
import com.example.coconuts.projection.user.GetUser;
import com.example.coconuts.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController // JSON 형태의 결과값 반환
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

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
            @RequestParam(value="image", required = false) String profileImage,
            @RequestBody UserUpdateDTO userUpdateDTO) throws IOException {

        // 이미지 등록
        if (profileImage != null){
            userUpdateDTO.setImage(profileImage);
        }

        // 정보 업데이트
        UserEntity res = userService.updateProfile(userId, userUpdateDTO);

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

        if(res.isEmpty()) {
            return ResponseEntity
                    .status(ResponseCode.SUCCESS_GET_USER_LIST.getStatus().value())
                    .body(new ResponseDTO(ResponseCode.SUCCESS_GET_USER_LIST, "No users found"));
        }

        return ResponseEntity
                .status(ResponseCode.SUCCESS_GET_USER_LIST.getStatus().value())
                .body(new ResponseDTO(ResponseCode.SUCCESS_GET_USER_LIST, res));

    }


}
