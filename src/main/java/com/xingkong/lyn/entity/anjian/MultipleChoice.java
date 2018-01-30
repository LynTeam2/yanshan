package com.xingkong.lyn.entity.anjian;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "aj_question_mc")
@EqualsAndHashCode(callSuper = false)
public class MultipleChoice extends Question implements Serializable {
    private static final long serialVersionUID = 1L;

    private String ChoiceA;

    private String ChoiceB;

    private String ChoiceC;

    private String ChoiceD;
}
