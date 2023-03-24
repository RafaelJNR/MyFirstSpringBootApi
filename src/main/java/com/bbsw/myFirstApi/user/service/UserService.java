package com.bbsw.myFirstApi.user.service;

import com.bbsw.myFirstApi.user.dto.UserDTO;
import com.bbsw.myFirstApi.user.enums.RolEnum;
import com.bbsw.myFirstApi.user.model.UserData;
import com.bbsw.myFirstApi.user.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bbsw.myFirstApi.encoder.Encoder;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserDataRepository userDataRepository;

    public List<UserDTO> findAllUsers(){

        List<UserData> usersData = userDataRepository.findAll();
        List<UserDTO> usersDTO = new ArrayList<UserDTO>();
        Encoder encoder = new Encoder();

        for(int i =0; i <usersData.size(); i++){

            String username = encoder.getDecipher(usersData.get(i).getUsername());
            String password = encoder.getDecipher(usersData.get(i).getPassword());
            String rol = String.valueOf(usersData.get(i).getRol());
            UserDTO userDto = new UserDTO();
            userDto.setUsername(username);
            userDto.setPassword(password);
            userDto.setRol(RolEnum.valueOf(rol));
            usersDTO.add(userDto);

        }

        return usersDTO;
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

    public UserData createUser(UserDTO userDto) {

        String username = userDto.getUsername();
        UserData userData = new UserData();
        Encoder encoder = new Encoder();
        if (userDataRepository.findByUsername(username) == null){
        userData.setUsername(encoder.getCipher(userDto.getUsername()));
        userData.setPassword(encoder.getCipher(userDto.getPassword()));
        userData.setRol(userDto.getRol());
        return userDataRepository.save(userData);
        }else{
            return null;
        }

    }


    public UserData updateUser(UserDTO userDto){
        Encoder encoder = new Encoder();

        String username = userDto.getUsername();
        UserData userData = new UserData();
        userData =userDataRepository.findByUsername(encoder.getCipher(username));
        if (userData != null){
            userData.setUsername(encoder.getCipher(userDto.getUsername()));
            userData.setPassword(encoder.getCipher(userDto.getPassword()));
            userData.setRol(userDto.getRol());
            return userDataRepository.save(userData);
        }else{
            return null;
        }
    }

    public void deleteUserByUsername(UserDTO userDto){
        Encoder encoder = new Encoder();
        String username = userDto.getUsername();
        UserData userData = userDataRepository.findByUsername(encoder.getCipher(username));

        userDataRepository.delete(userData);}
}
