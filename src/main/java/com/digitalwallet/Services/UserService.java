package com.digitalwallet.Services;

import com.digitalwallet.dtos.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserResponse getUserById(String userId);

    UserResponse getUserByUsername(String username);

    UserResponse getUserByEmail(String email);

    Page<UserResponse> getAllUsers(Pageable pageable);

    UserResponse updateUser(String userId, UserResponse userResponse);

    UserResponse updateUserEmail(String userId, String email);

    UserResponse updateUserPassword(String userId, String currentPassword, String newPassword);

    void deleteUser(String userId);

    void deactivateUser(String userId);

    void activateUser(String userId);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    long countActiveUsers();

    long countTotalUsers();
}