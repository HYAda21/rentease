package com.rentease.rentease.service;


import com.rentease.rentease.entity.Property;
import com.rentease.rentease.entity.User;
import com.rentease.rentease.repository.PropertyRepository;
import com.rentease.rentease.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;
    public PropertyService(PropertyRepository propertyRepository , UserRepository userRepository) {
        this.propertyRepository = propertyRepository;
        this.userRepository = userRepository;
    }

    public Property createProperty(Property property, Long ownerid){
        User owner = userRepository.findById(ownerid)
                .orElseThrow(() ->
                        new RuntimeException("Owner not found"));

        property.setOwner(owner);


        return propertyRepository.save(property);
    }

    public List<Property> getAll(){
        return propertyRepository.findAll();
    }

    public Optional<Property> getPropertyById(Long id){
        return propertyRepository.findById(id);
    }

    public void deletePropertyById(Long id){
        propertyRepository.deleteById(id);
    }

}
