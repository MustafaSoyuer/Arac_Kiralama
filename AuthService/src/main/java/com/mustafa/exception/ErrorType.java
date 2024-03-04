package com.mustafa.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {

    INTERNAL_SERVER_ERROR(1000, "Sunucu Hatasi",HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST (1001,"Parametre hatasi", HttpStatus.BAD_REQUEST),
    LOGIN_ERROR(2000,"Kullanici adi veya sifre hatalidir...",HttpStatus.BAD_REQUEST),
    USERNAME_DUPLICATE(2001,"Böyle bir kullanici adi sistemde mevcut...", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(2003,"Böyle bir kullanici bulunamadi...",HttpStatus.BAD_REQUEST),
    ACTIVATION_CODE_ERROR(4113,"Aktivasyon kod hatasi..." ,HttpStatus.BAD_REQUEST ),
    INVALID_TOKEN(4114,"Geçersiz token" ,HttpStatus.BAD_REQUEST),
    TOKEN_NOT_CREATED(4115,"Token olusturulamadi..." , HttpStatus.BAD_REQUEST ),
    ACCOUNT_NOT_ACTIVE(4116,"Hesabiniz aktif degildir..." , HttpStatus.FORBIDDEN ),
    ROLE_NOT_FOUND(4117,"ROL BULUNAMADI" ,HttpStatus.BAD_REQUEST),
    USER_NOT_CREATED(4118,"Kullanici profili olusturulamadi...",HttpStatus.BAD_REQUEST);



    private int code;
    private String message;
    private HttpStatus httpStatus;
}
