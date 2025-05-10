package com.practice.hibernatesample.model;

public record ItemRecord(Long id, String title,CategoryRecord category, double price ) {
}
