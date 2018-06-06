package com.xingkong.lyn.entity.anjian;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "aj_question_tf")
@EqualsAndHashCode(callSuper = false)
public class TrueFalse extends Question implements Serializable {
    private static final long serialVersionUID = 1L;
}
