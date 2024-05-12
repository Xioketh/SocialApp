package org.example.backend.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.backend.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

//
//    final UserRepository userRepository;
//    final PrivilegeRepository privilegeRepository;
//    final UserPrivilegeRepository userPrivilegeRepository;
//
//    @Autowired
//    private ModelMapper modelMapper;
//    private final RoleRepository roleRepository;

//    @Override
//    public ResponseEntity<?> getUserList(CommonRequestDTO commonRequestDTO) throws Exception {

        //check and set default values
//        if (GeneralUtil.isNullOrEmpty(commonRequestDTO.getRole())) {
//            commonRequestDTO.setRole(null);
//        }
//        if (GeneralUtil.isNullOrEmpty(commonRequestDTO.getUserCode())) {
//            commonRequestDTO.setUserCode(null);
//        }
//        if (GeneralUtil.isNullOrEmpty(commonRequestDTO.getUserName())) {
//            commonRequestDTO.setUserName(null);
//        }
//        if (GeneralUtil.isNullOrEmpty(commonRequestDTO.getEmail())) {
//            commonRequestDTO.setEmail(null);
//        }
//        if (commonRequestDTO.getPageSize() <= 0) {
//            commonRequestDTO.setPageSize(50);
//        }

//
//
//        Pageable pageable = PageRequest.of(commonRequestDTO.getPageNo(), commonRequestDTO.getPageSize());
//        Page<User> userList = userRepository.getUserList(
//                commonRequestDTO.getRole(),
//                commonRequestDTO.getUserCode(),
//                commonRequestDTO.getUserName(),
//                commonRequestDTO.getEmail(),
//                commonRequestDTO.getIsActive(),
//                pageable
//        );
//        GeneralUtil.isPageEmpty(userList);
//        System.out.println(userList.getContent());
//        return new ResponseEntity<>(new ResponseMessage(HttpStatus.OK.value(), "success", userList.getContent(), userList.getTotalElements(), userList.getTotalPages()), HttpStatus.OK);
//    }
//
//    @Override
//    public ResponseEntity<?> updateUser(UserDTO userDTO) throws Exception {
//        GeneralUtil.isNullOrEmptyException(userDTO.getUserCode(), "User Code");
//        if (!GeneralUtil.hasNonNullField(userDTO)) {
//            throw new CustomException("No values to update");
//        }
//        userRepository.findAllByUserCode(userDTO.getUserCode()).ifPresentOrElse(
//                user -> updateExistingUser(user, userDTO),
//                () -> {
//                    try {
//                        throw new CustomException("No User Found to Update!");
//                    } catch (CustomException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//        );
//        return new ResponseEntity<>(new ResponseMessage(HttpStatus.OK.value(), "success", "User Successfully Updated!"), HttpStatus.OK);
//    }
//
//    private void updateExistingUser(User user, UserDTO userDTO) {
//        Class<?> dtoClass = userDTO.getClass();
//        Field[] dtoFields = dtoClass.getDeclaredFields();
//
//        for (Field dtoField : dtoFields) {
//            dtoField.setAccessible(true);
//
//            try {
//                Object dtoValue = dtoField.get(userDTO);
//                String fieldName = dtoField.getName();
//
//                // Exclude fields like password, username, and email from the update
//                if (!GeneralUtil.isExcludedField(fieldName)) {
//                    Field userField = User.class.getDeclaredField(fieldName);
//                    userField.setAccessible(true);
//                    userField.set(user, dtoValue);
//                }
//            } catch (NoSuchFieldException | IllegalAccessException e) {
//                throw new RuntimeException("Error updating user entity with DTO values", e);
//            }
//        }
//        user.setUpdateDateTime(DateUtil.getCurrentDateTime());
//        userRepository.save(user);
//    }

//    public void addPrivilege(User user) {
//        for (Privilege privilege : privilegeRepository.findAll()) {
//            UserPrivilege userPrivilege = new UserPrivilege();
//            userPrivilege.setUser(user);
//            userPrivilege.setPrivilege(privilege);
//            userPrivilege.setStatus(0);
//
//            userPrivilegeRepository.save(userPrivilege);
//        }
//
//    }

//    @Override
//    public ResponseEntity<?> getUserRolWiseUser(CommonRequestDTO commonRequestDTO) throws Exception {
//        if (!commonRequestDTO.getRoles().isEmpty()) {
//            List<ERole> eRoles = GeneralUtil.mapRolesToEnumList(commonRequestDTO.getRoles());
//
//            if (eRoles.contains(null)){
//                eRoles = GeneralUtil.mapRolesToEnums(commonRequestDTO.getRoles());
//            }
//
//            List<String> listDt = new ArrayList<>();
//            for (ERole role : eRoles) {
//                listDt.add(role.toString());
//            }
//            List<User> userList = userRepository.getUserRoleWiseUserList(listDt, 1);
//            if (!userList.isEmpty()) {
//                List<UserDTO> list = userList.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
//                return new ResponseEntity<>(new ResponseMessage(HttpStatus.OK.value(), "success", list), HttpStatus.OK);
//            }
//            throw new CustomException(commonRequestDTO.getRoles() + " details is empty");
//        }
//        throw new CustomException("user rol is empty");
//    }
//
//    @Override
//    public ResponseEntity<?> getRoles() throws Exception {
//        List<Role> roleList = roleRepository.findAll();
//        if (roleList.isEmpty()) {
//            throw new CustomException("Role details is empty");
//        }
//        return new ResponseEntity<>(new ResponseMessage(HttpStatus.OK.value(), "success", roleList), HttpStatus.OK);
//    }
//
//    @Override
//    public ResponseEntity<?> getUserWiseUserPrivilege(CommonRequestDTO commonRequestDTO) throws Exception {
//        if (!commonRequestDTO.getUserCode().isEmpty()) {
//            User user = userRepository.findAllByUserCode(commonRequestDTO.getUserCode())
//                    .orElseThrow(() -> new CustomException("user details is empty"));
//
//            List<PrivilegeDTO> privilegeDTOS = userPrivilegeRepository.findPrivilegeIdsByUserCode(user).stream()
//                    .map(this::toPrivilegeDto)
//                    .toList();
//            if (!privilegeDTOS.isEmpty()) {
//                return new ResponseEntity<>(new ResponseMessage(HttpStatus.OK.value(), "success", privilegeDTOS), HttpStatus.OK);
//            }
//            throw new CustomException("privilege details is empty");
//
//
//        }
//        throw new CustomException("user details is empty");
//    }
//
//    private PrivilegeDTO toPrivilegeDto(Privilege privilege) {
//        return modelMapper.map(privilege, PrivilegeDTO.class);
//    }

}
