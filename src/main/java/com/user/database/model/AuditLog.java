package com.user.database.model;

import com.user.constant.Tables;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = Tables.USER_MASTER)
public class AuditLog {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id")
    private Long id;

    @Column(name = "action")
    private String action;

    @Column(name = "details")
    private String details;

    @Column(name = "time")
    private Long time;

}
