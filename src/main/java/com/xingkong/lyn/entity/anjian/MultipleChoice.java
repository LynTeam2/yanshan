package com.xingkong.lyn.entity.anjian;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "aj_question_mc")
@EqualsAndHashCode(callSuper = false)
public class MultipleChoice extends Question implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "choice_a")
    private String choiceA;

    @Column(name = "choice_b")
    private String choiceB;

    @Column(name = "choice_c")
    private String choiceC;

    @Column(name = "choice_d")
    private String choiceD;
}
