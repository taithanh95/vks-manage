package com.bitsco.vks.manage.entities;

import com.bitsco.vks.common.constant.Constant;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = Constant.TABLE.LST_CONCLUSION)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Conclusion extends BaseEntity {
    @Column(name = "CONCID")
    @Id
    private String concId;

    @Column(name = "CONCNAME")
    private String content;

    @Column(name = "USERFOR")
    private String stage;

    @Column(name = "STATUS")
    private String status;
}
