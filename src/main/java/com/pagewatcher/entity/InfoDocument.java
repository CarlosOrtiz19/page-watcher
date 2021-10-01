package com.pagewatcher.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data

@Entity
public class InfoDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private int tagLi;
    private int tagUl;
    private int tagA;
    private int tagDiv;
    private int tagSpam;
    private String email;
    private String errorMesagge;


}
