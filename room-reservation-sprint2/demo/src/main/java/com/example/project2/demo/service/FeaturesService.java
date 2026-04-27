package com.example.project2.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.project2.demo.data.FeaturesRepository;
import com.example.project2.demo.model.Features;

@Service
public class FeaturesService {

    private final FeaturesRepository featuresRepository;

    public FeaturesService(FeaturesRepository featuresRepository) {
        this.featuresRepository = featuresRepository;
    }

    public List<Features> getAllFeatures() {
        return featuresRepository.findAll();
    }

    public List<Features> getFeaturesByRoomId(Long roomId) {
        return featuresRepository.findByRoom_Id(roomId);
    }

    public Features updateFeatures(Features updatedFeatures) {
        return featuresRepository.save(updatedFeatures);
    }

    public void deleteFeaturesByRoomId(Long roomId) {
        featuresRepository.deleteByRoom_Id(roomId);
    }
}