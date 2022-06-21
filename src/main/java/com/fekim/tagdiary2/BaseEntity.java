package com.fekim.tagdiary2;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

// BaseEntity를 상속받은 엔티티는 생성일과 수정일이 자동으로 DB에 등록됩니다.
@MappedSuperclass()
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
public class BaseEntity {

    //
    @CreatedDate
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "moddate" )
    private LocalDateTime modDate;

}
