package com.recruitDemo.recruitDemo.services;

import com.recruitDemo.recruitDemo.entity.ApiKey;
import com.recruitDemo.recruitDemo.entity.User;
import com.recruitDemo.recruitDemo.repository.ApiKeysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiKeysService {
    private final ApiKeysRepository apiKeysRepository;

    @Autowired
    public ApiKeysService(ApiKeysRepository apiKeysRepository) {
        this.apiKeysRepository = apiKeysRepository;
    }

    public void addNew(ApiKey apiKey){
        if(checkIfUserCanCreateKey(apiKey.getUserId())) apiKeysRepository.save(apiKey);
    }

    private boolean checkIfUserCanCreateKey(User user){
        List<ApiKey> apiKeyList = apiKeysRepository.findByUserId(user);

        if(apiKeyList.size()>=3){
            return false;
        }
        return true;
    }

    public void delete(ApiKey apiKey){
        apiKeysRepository.delete(apiKey);
    }
}
