package com.example.test.service;

import com.example.test.entity.TestGhn;

import java.util.List;

public interface ITestGhnService {
    List<TestGhn> getAll();
    TestGhn findById(Integer id);
    boolean deleteById(Integer id);

    boolean save(String e);
}
