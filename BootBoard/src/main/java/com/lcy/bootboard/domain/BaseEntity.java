package com.lcy.bootboard.domain;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})//엔티티가 데이터베이스에 추가되거나 변경될 때 자동으로 시간 값 지정 //프로젝트 설정에 @EnableJpaAuditing 추가
@Getter
public class BaseEntity {




}
