package com.example.test.service;

import com.example.test.dto.ProvidersDTO;
import com.example.test.models.ProvidersNCC;
import com.example.test.repository.ProviderNCCRepository;
import com.example.test.serviceImpl.IProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProviderNCCService implements IProviderService {
    @Autowired
    ProviderNCCRepository providerRepository;
    @Override
    public List<ProvidersDTO> getAll() {
        List<ProvidersDTO> listDto = new ArrayList<>();
        List<ProvidersNCC> list = providerRepository.findAll();
        for(ProvidersNCC var: list){
            listDto.add((new ProvidersDTO(var)));
        }
        return listDto;
    }

    @Override
    public ProvidersDTO getById(Integer id) {
        ProvidersDTO dto = new ProvidersDTO();
        ProvidersNCC provider = providerRepository.findById(id).orElse(null);
        if(provider!=null){
            dto = new ProvidersDTO(provider);
            return dto;
        }
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        try{
            providerRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean save(ProvidersNCC providers) {
        try{
            providerRepository.save(providers);
            return  true;
        }catch (Exception e) {
            return false;
        }
    }
}
