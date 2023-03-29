package com.bbsw.myFirstApi.user.service;

import com.bbsw.myFirstApi.user.dto.UserDTO;
import com.bbsw.myFirstApi.user.model.UserData;
import com.bbsw.myFirstApi.user.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bbsw.myFirstApi.encoder.Encoder;
import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserDataRepository userDataRepository;

    public List<UserDTO> findAllUsers(){

        List<UserData> usersData = userDataRepository.findAll();
        Encoder encoder = new Encoder();

        return usersData.stream().map(userData -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(encoder.getDecipher(userData.getUsername()));
            userDTO.setPassword(encoder.getDecipher(userData.getPassword()));
            userDTO.setRol(userData.getRol());
            return userDTO;
        }).toList();

    }
    public UserDTO findUser(String username){

        UserDTO userDto = new UserDTO();
        Encoder encoder = new Encoder();
        UserData usersData = userDataRepository.findByUsername(encoder.getCipher(username));
        userDto.setUsername(encoder.getDecipher(usersData.getUsername()));
        userDto.setPassword(encoder.getDecipher(usersData.getPassword()));
        userDto.setRol(usersData.getRol());
        return userDto;
    }

    public UserDTO findUserByPasswordAndUsername(String username, String password){

        UserDTO userDto = new UserDTO();
        Encoder encoder = new Encoder();
        UserData usersData = userDataRepository.findByPasswordAndUsername(encoder.getCipher(username), encoder.getCipher(password));
        userDto.setUsername(encoder.getDecipher(usersData.getUsername()));
        userDto.setPassword(encoder.getDecipher(usersData.getPassword()));
        userDto.setRol(usersData.getRol());

        return userDto;

    }

    public UserDTO createUser(UserDTO userDto) {

        String username = userDto.getUsername();
        UserData userData = new UserData();
        Encoder encoder = new Encoder();
        if(userDataRepository.findByUsername(username) == null){
        userData.setUsername(encoder.getCipher(userDto.getUsername()));
        userData.setPassword(encoder.getCipher(userDto.getPassword()));
        userData.setRol(userDto.getRol());
        userDataRepository.save(userData);
        return userDto;
        }else{
        return userDto;
        }

    }


    public UserDTO updateUser(UserDTO userDto){
        Encoder encoder = new Encoder();

        String username = userDto.getUsername();
        UserData userData = new UserData();
        userData =userDataRepository.findByUsername(encoder.getCipher(username));
        if (userData != null){
            userData.setUsername(encoder.getCipher(userDto.getUsername()));
            userData.setPassword(encoder.getCipher(userDto.getPassword()));
            userData.setRol(userDto.getRol());
            userDataRepository.save(userData);
            return userDto;
        }else{
            return userDto;
        }
    }

    public void deleteUserByUsername(UserDTO userDto){
        Encoder encoder = new Encoder();
        String username = userDto.getUsername();
        UserData userData = userDataRepository.findByUsername(encoder.getCipher(username));

        userDataRepository.delete(userData);}
}
