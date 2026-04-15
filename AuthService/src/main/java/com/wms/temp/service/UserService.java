package com.wms.temp.service;

import com.wms.temp.dto.LoginRequestDto;
import com.wms.temp.dto.UserRequestDto;
import com.wms.temp.entity.UserEntity;

public interface UserService {
	
UserEntity register(UserRequestDto request);

    String login(LoginRequestDto request);

}
