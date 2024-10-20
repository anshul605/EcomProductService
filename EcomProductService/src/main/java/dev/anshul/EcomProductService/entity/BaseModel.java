package dev.anshul.EcomProductService.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.time.Instant;

@MappedSuperclass
public abstract class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Instant cretedAt; //number of milliseconds since 1st Jan, 1970 UTC and its latest one from the timestamp
    private Instant updatedAt;
}
