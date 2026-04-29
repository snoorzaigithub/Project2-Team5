package com.example.project2.demo.service;

import com.example.project2.demo.model.Features;
import com.example.project2.demo.data.FeaturesRepository;
import org.springframework.stereotype.Service;

@Service
public class FeaturesService {

    private final FeaturesRepository featuresRepository;

    public FeaturesService(FeaturesRepository featuresRepository) {
        this.featuresRepository = featuresRepository;
    }

    public Features save(Features features) {
        return featuresRepository.save(features);
    }
}