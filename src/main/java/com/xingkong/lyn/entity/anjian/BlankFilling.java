package com.xingkong.lyn.entity.anjian;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "aj_question_bf")
@EqualsAndHashCode(callSuper = false)
public class BlankFilling extends Question implements Serializable {
    private static final long serialVersionUID = 1L;
}
