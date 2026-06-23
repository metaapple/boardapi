package org.scoula.sample.service;

import org.springframework.stereotype.Service;

@Service
class SampleServiceImpl implements SampleService {


    @Override
    public Integer doAdd(String str1, String str2) {
        return Integer.parseInt(str1) + Integer.parseInt(str2);
    }
}
