package com.pagewatcher.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
public class AccountInformation {

    @Id
    private String id = UUID.randomUUID().toString();
    private String url;
    private String session;
    private String cookie;
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "infoDocument_id", referencedColumnName = "id")
    private InfoDocument infoDocument;
    private ControlSession controlSession;
}
