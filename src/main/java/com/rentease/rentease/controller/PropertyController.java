package com.rentease.rentease.controller;


import com.rentease.rentease.entity.Property;
import com.rentease.rentease.repository.PropertyRepository;
import com.rentease.rentease.service.PropertyService;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/properties")
public class PropertyController {
    private final PropertyService propertyService;
    private final PropertyRepository propertyRepository;
    public PropertyController(PropertyService propertyService, PropertyRepository propertyRepository) {
        this.propertyService = propertyService;
        this.propertyRepository = propertyRepository;
    }

    @PostMapping("/{ownerid}")
    public Property createProperty(@RequestBody Property property,@PathVariable Long ownerid ) {

        return propertyService.createProperty(property,ownerid);
    }
    @GetMapping("/owner/{ownerId}")
    public List<Property> getPropertiesByOwner(@PathVariable Long ownerId) {
        return propertyRepository.findByOwnerId(ownerId);
    }

    @GetMapping
    public List<Property> getAll(){
        return propertyService.getAll();
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id){
        return propertyService.getPropertyById(id)
                .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePropertyById(@PathVariable Long id){
        propertyService.deletePropertyById(id);
        return ResponseEntity.noContent().build();
    }
}
