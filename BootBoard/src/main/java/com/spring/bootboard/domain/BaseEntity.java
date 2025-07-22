package com.spring.bootboard.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})//엔티티가 데이터베이스에 추가되거나 변경될 때 자동으로 시간 값 지정 //프로젝트 설정에 @EnableJpaAuditing 추가
@Getter
public class BaseEntity {

    @CreatedDate    //Entity가 생성되어 저장될 때 시간이 자동으로 저장
    @Column(name = "regiDate", updatable = false)
    private LocalDateTime regiDate;

    @LastModifiedDate    //조회한 Entity의 값을 변경할 때 시간이 자동으로 저장
    @Column(name = "modiDate")
    private LocalDateTime modiDate;






}
