package com.mustafa.domain;

import com.mustafa.utility.enums.EStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Document
public class User extends BaseEntity {

    @Id
    private String id;
    private Long authId;
    private String username;
    private String email;
    private String photo;
    private String name;
    private String phone;
    private String about;

    @Builder.Default
    private EStatus status = EStatus.PENDING;

}
