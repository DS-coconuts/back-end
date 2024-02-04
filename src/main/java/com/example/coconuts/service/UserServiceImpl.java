package com.example.coconuts.service;

import com.example.coconuts.code.ErrorCode;
import com.example.coconuts.dto.user.UserLoginDTO;
import com.example.coconuts.dto.user.UserRegisterDTO;
import com.example.coconuts.dto.user.UserUpdateDTO;
import com.example.coconuts.dto.user.UserListResponseDto;
import com.example.coconuts.entity.DataEntity;
import com.example.coconuts.entity.ScoreEntity;
import com.example.coconuts.entity.UserEntity;
import com.example.coconuts.exception.LoginIdNotFoundException;
import com.example.coconuts.exception.LoginPasswordNotMatchException;
import com.example.coconuts.exception.ProfileNotFoundException;
import com.example.coconuts.projection.user.GetUser;
import com.example.coconuts.repository.DataRepository;
import com.example.coconuts.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final ScoreRe dataRepository;

    @Override
    public GetUser register(UserRegisterDTO userRegisterDTO) {
        UserEntity user = UserEntity.builder()
                .loginId(userRegisterDTO.getLoginId())
                .password(userRegisterDTO.getPassword())
                .introduction(userRegisterDTO.getIntroduction())
                .goalCpm(userRegisterDTO.getGoalCpm())
                .build();

        UserEntity createUser = userRepository.save(user);
        return EntityToProjectionUser(createUser);
    }


    @Override
    public GetUser login(UserLoginDTO userLoginDTO) {
        Optional<UserEntity> findUser = userRepository.findByLoginId(userLoginDTO.loginId);

        // 아이디 존재하는지 확인
        if(!findUser.isPresent()) throw new LoginIdNotFoundException(ErrorCode.USERID_NOT_FOUND);
            // 비밀번호가 같은지 확인
        else if(!findUser.get().getPassword().equals(userLoginDTO.password)) throw new LoginPasswordNotMatchException(ErrorCode.PASSWORD_NOT_MATCH);

        GetUser user = EntityToProjectionUser(findUser.get());

        return user;
    }

    @Override
    public void logout(String loginId) {
        Optional<UserEntity> optionalUser = userRepository.findByLoginId(loginId);
        optionalUser.ifPresent(user -> {
            userRepository.delete(user);
        });
    }

    @Override
    public UserEntity updateProfile(Integer userId, UserUpdateDTO userUpdateDTO) {
        // 특정 프로필 객체 업데이트
        Optional<UserEntity> profile = userRepository.findById(userId);

        if(!profile.isPresent())
            throw new ProfileNotFoundException(ErrorCode.PROFILE_NOT_FOUND);

        if(userUpdateDTO.getImage() != null)
            profile.get().setImage(userUpdateDTO.getImage());
        profile.get().setIntroduction(userUpdateDTO.getIntroduction());
        profile.get().setGoalCpm(userUpdateDTO.getGoalCpm());

        return userRepository.save(profile.get());
    }

    @Override
    public Optional<UserEntity> getProfile(Integer userId) {
        Optional<UserEntity> profile = userRepository.findById(userId);

        // 프로필이 존재하지 않는 경우(잘못된 userId 입력)
        if(!profile.isPresent())
            throw new ProfileNotFoundException(ErrorCode.PROFILE_NOT_FOUND);

        return profile;
    }

    @Override
    public List<UserEntity> getUserList() {
        List<UserEntity> users = userRepository.findAll();

        return users;
    }


 
    @Override
    public List<UserListResponseDto> searchUsers(Integer userId, String query) {

        // 검색어로 사용자 찾기
        List<UserEntity> searchResults = userRepository.findNonFriendUsersBySearchCriteria(userId, query);

        return convertToDtoList(searchResults);
    }

    @Override
    public List<ScoreEntity> getUserScoreList(Integer userId) {

        List<ScoreEntity> scores = dataRepository.
        return null;
    }

    private List<UserListResponseDto> convertToDtoList(List<UserEntity> userList) {
        return userList.stream()
                .map(UserListResponseDto::new)
                .collect(Collectors.toList());
    }

    private GetUser EntityToProjectionUser(UserEntity user){
        GetUser userInfo = new GetUser() {

            @Override
            public Integer getId() {
                return user.getId();
            }

            @Override
            public String getLoginId() {
                return user.getLoginId();
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getImage() {
                return user.getImage();
            }

            @Override
            public String getIntroduction() {
                return user.getIntroduction();
            }

            @Override
            public Integer getGoalCpm() {
                return user.getGoalCpm();
            }
        };

        return userInfo;
    }

}
