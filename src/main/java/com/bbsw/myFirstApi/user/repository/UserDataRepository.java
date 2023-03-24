package com.bbsw.myFirstApi.user.repository;

import com.bbsw.myFirstApi.user.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {
    UserData findByUsername(String username);

    UserData findByPasswordAndUsername(String password, String username);


}