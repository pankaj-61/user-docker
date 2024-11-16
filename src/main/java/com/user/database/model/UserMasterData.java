package com.user.database.model;

import com.user.constant.Tables;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = Tables.USER_MASTER)
public class UserMasterData {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName = null;


    @Column(name = "email")
    private String email = null;

    @Column(name = "dial_code")
    private String dialCode = null;

    @Column(name = "phone_number")
    private String phoneNumber = null;

    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = true;

    @Column(name = "created_date")
    @Builder.Default
    private Long createdDate = new Date().getTime();


    @Column(name = "created_by")
    private String createdBy = null;

    @Column(name = "updated_date")
    private Long updatedDate = new Date().getTime();


    @Column(name = "updated_by")
    private String updatedBy = null;


}
