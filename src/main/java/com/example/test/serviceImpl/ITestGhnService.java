package com.example.test.serviceImpl;

import com.example.test.models.TestGhn;

import java.util.List;

public interface ITestGhnService {
    List<TestGhn> getAll();
    TestGhn findById(Integer id);
    boolean deleteById(Integer id);

    boolean save(String e);
}
