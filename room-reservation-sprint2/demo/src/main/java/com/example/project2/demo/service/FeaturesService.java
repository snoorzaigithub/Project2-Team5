package com.example.project2.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.project2.demo.model.Features;
import com.example.project2.demo.data.FeaturesRepository;

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
        return featuresRepository.findByRoomId(roomId);
    }

    public Features updateFeatures(Features updatedFeatures) {
        return featuresRepository.save(updatedFeatures);
    }

    public void deleteFeaturesByRoomId(Long roomId) {
        featuresRepository.deleteByRoomId(roomId);
    }
}
