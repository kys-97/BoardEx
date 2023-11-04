package com.example.boardex.data.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
//BaseTimeEntity가 JPA 엔티티의 공통 매핑 정보를 포함하고 있는 클래스임을 의미함
@MappedSuperclass
//추상클래스
public abstract class BaseTimeEntity {

    //생성일시
    @Column
    private LocalDateTime createdDate;
    //수정일시
    @Column
    private LocalDateTime modifiedDate;
    //PrePersist
    //JPA entity가 insert 되기 전에 실행할 메서드
    @PrePersist
    public void prePersist() {
        this.createdDate = LocalDateTime.now();
    }

    //JPA entity가 update 되기 전에 실행할 메서드
    @PreUpdate
    public void preUpdate() {
        this.modifiedDate = LocalDateTime.now();
    }
}
