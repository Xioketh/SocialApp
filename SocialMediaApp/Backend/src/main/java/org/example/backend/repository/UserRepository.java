package org.example.backend.repository;

import org.example.backend.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);
    Boolean existsByuserCode(String userCode);

    Boolean existsByEmail(String email);

    Optional<User> findTopByOrderByIdDesc();

    @Query(value = "SELECT u.* FROM users u " +
            "INNER JOIN user_roles ur ON u.id = ur.user_id " +
            "INNER JOIN roles r ON ur.role_id = r.id " +
            "WHERE " +
            "(:role IS NULL OR r.name = :role) AND " +
            "(:userCode IS NULL OR u.user_code LIKE %:userCode%) AND " +
            "(:userName IS NULL OR u.username LIKE %:userName%) AND " +
            "(:email IS NULL OR u.email LIKE %:email%) AND " +
            "(:isActive IS NULL OR u.is_active = :isActive)",
            countQuery = "SELECT count(*) FROM users u " +
                    "INNER JOIN user_roles ur ON u.id = ur.user_id " +
                    "INNER JOIN roles r ON ur.role_id = r.id " +
                    "WHERE " +
                    "(:role IS NULL OR r.name = :role) AND " +
                    "(:userCode IS NULL OR u.user_code LIKE %:userCode%) AND " +
                    "(:userName IS NULL OR u.username LIKE %:userName%) AND " +
                    "(:email IS NULL OR u.email LIKE %:email%) AND " +
                    "(:isActive IS NULL OR u.is_active = :isActive)",
            nativeQuery = true)
    Page<User> getUserList(
            @Param("role") String role,
            @Param("userCode") String userCode,
            @Param("userName") String userName,
            @Param("email") String email,
            @Param("isActive") int isActive,
            Pageable page
    );


    @Query(value = "SELECT u.* FROM larocher_erp.users u inner join user_roles ur ON u.id = ur.user_id " +
            "JOIN larocher_erp.roles r ON ur.role_id = r.id " +
            "WHERE r.name in( :roles) AND u.is_active = :isActive", nativeQuery = true)
    List<User> getUserRoleWiseUserList(
            @Param("roles") List<String> roles,
            @Param("isActive") Integer isActive);


    Optional<User> findAllByUserCode(String userCode);
}

