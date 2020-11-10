package com.mci.oop.anemicdomainmodel;

/**
    A typical Anemic Domain Model example
 */
////////// Controller+VO(View Object) //////////
public class UserController {
    private UserService userService; //通过构造函数或者IOC框架注入

    public UserVo getUserById(Long userId) {
        UserBo userBo = userService.getUserById(userId);
        UserVo userVo = null; //convert userBo to userVo
        return userVo;
    }
}

public class UserVo {//省略其他属性、get/set/construct方法
    private Long id;
    private String name;
    private String cellphone;
}

////////// Service+BO(Business Object) //////////
public class UserService {
    private UserRepository userRepository; //通过构造函数或者IOC框架注入

    public UserBo getUserById(Long userId) {
        UserEntity userEntity = userRepository.getUserById(userId);
        UserBo userBo = [...convert userEntity to userBo...];
        return userBo;
    }
}

public class UserBo {//省略其他属性、get/set/construct方法
    private Long id;
    private String name;
    private String cellphone;
}

////////// Repository+Entity //////////
public class UserRepository {
    public UserEntity getUserById(Long userId) { //... }
}

public class UserEntity {//省略其他属性、get/set/construct方法
    private Long id;
    private String name;
    private String cellphone;
}
