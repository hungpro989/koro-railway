package com.example.test.service;

import com.example.test.models.TestGhn;
import com.example.test.repository.TestGhnRepository;
import com.example.test.serviceImpl.ITestGhnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TestGhnService implements ITestGhnService {
    @Autowired
    private TestGhnRepository testGhnRepository;
    @Override
    public List<TestGhn> getAll() {
        List<TestGhn> list = testGhnRepository.findAll();
        return list;
    }

    @Override
    public TestGhn findById(Integer id) {
        TestGhn e = testGhnRepository.findById(id).orElse(null);
        return e;
    }

    @Override
    public boolean deleteById(Integer id) {
        testGhnRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean save(String e) {
        TestGhn t = new TestGhn();
        t.setData(e);
        testGhnRepository.save(t);
        return true;
    }
}
