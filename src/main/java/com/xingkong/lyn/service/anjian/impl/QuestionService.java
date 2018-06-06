package com.xingkong.lyn.service.anjian.impl;

import com.xingkong.lyn.entity.anjian.*;
import com.xingkong.lyn.repository.anjian.BlankFillingRepository;
import com.xingkong.lyn.repository.anjian.MultipleChoiceRepository;
import com.xingkong.lyn.repository.anjian.SimpleChoiceRepository;
import com.xingkong.lyn.repository.anjian.TrueFalseRepository;
import com.xingkong.lyn.service.anjian.IQuestion;
import com.xingkong.lyn.util.PinyinUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class QuestionService implements IQuestion {
    @Resource
    private TrueFalseRepository trueFalseDao;
    @Resource
    private BlankFillingRepository blankFillingDao;
    @Resource
    private SimpleChoiceRepository simpleChoiceDao;
    @Resource
    private MultipleChoiceRepository multipleChoiceDao;


    @Override
    public List<TrueFalse> findNewtf(Date updateTime) {
        return trueFalseDao.findAllByUpdateTimeAfter(updateTime);
    }

    @Override
    public List<BlankFilling> findNewbf(Date updateTime) {
        return blankFillingDao.findAllByUpdateTimeAfter(updateTime);
    }

    @Override
    public List<SimpleChoice> findNewsc(Date updateTime) {
        return simpleChoiceDao.findAllByUpdateTimeAfter(updateTime);
    }

    @Override
    public List<MultipleChoice> findNewmc(Date updateTime) {
        return multipleChoiceDao.findAllByUpdateTimeAfter(updateTime);
    }

    @Override
    public Page<TrueFalse> findtfList(Pageable pageable, String query) {
        return StringUtils.isBlank(query)?trueFalseDao.findAll(pageable):trueFalseDao.findByUidLike("%" + query + "%", pageable);
    }

    @Override
    public Page<BlankFilling> findbfList(Pageable pageable) {
        return blankFillingDao.findAll(pageable);
    }

    @Override
    public Page<SimpleChoice> findscList(Pageable pageable, String query) {
        return StringUtils.isBlank(query)?simpleChoiceDao.findAll(pageable):simpleChoiceDao.findByUidLike("%" + query + "%", pageable);
    }

    @Override
    public Page<MultipleChoice> findmcList(Pageable pageable, String query) {
        return StringUtils.isBlank(query)?multipleChoiceDao.findAll(pageable):multipleChoiceDao.findByUidLike("%" + query + "%", pageable);
    }

    @Override
    public boolean addQuestion(Question question) {
        if (question instanceof TrueFalse) {
            TrueFalse entity = trueFalseDao.saveAndFlush((TrueFalse) question);
            entity.setUid(PinyinUtil.converterToFirstSpell(entity.getAjType()) + entity.getQuestionType() + entity.getId());
            trueFalseDao.saveAndFlush(entity);
        } else if (question instanceof BlankFilling) {
            blankFillingDao.saveAndFlush((BlankFilling) question);
        } else if (question instanceof SimpleChoice) {
            SimpleChoice entity = simpleChoiceDao.saveAndFlush((SimpleChoice) question);
            entity.setUid(PinyinUtil.converterToFirstSpell(entity.getAjType()) + entity.getQuestionType() + entity.getId());
            simpleChoiceDao.saveAndFlush(entity);
        } else if (question instanceof MultipleChoice) {
            MultipleChoice entity = multipleChoiceDao.saveAndFlush((MultipleChoice) question);;
            entity.setUid(PinyinUtil.converterToFirstSpell(entity.getAjType()) + entity.getQuestionType() + entity.getId());
            multipleChoiceDao.saveAndFlush(entity);
        }
        return true;
    }

    @Override
    public boolean updateQuestion(Question question) {
        if (question instanceof TrueFalse) {
            trueFalseDao.saveAndFlush((TrueFalse) question);
        } else if (question instanceof BlankFilling) {
            blankFillingDao.saveAndFlush((BlankFilling) question);
        } else if (question instanceof SimpleChoice) {
            simpleChoiceDao.saveAndFlush((SimpleChoice) question);
        } else if (question instanceof MultipleChoice) {
            multipleChoiceDao.saveAndFlush((MultipleChoice) question);
        }
        return true;
    }

    @Override
    public boolean deleteList(String questionType, List<Long> ids) {
        switch (questionType) {
            case "tf" :
                trueFalseDao.deleteInBatch(trueFalseDao.findAll(ids));
                break;
            case "bf" :
                blankFillingDao.deleteInBatch(blankFillingDao.findAll(ids));
                break;
            case "sc" :
                simpleChoiceDao.deleteInBatch(simpleChoiceDao.findAll(ids));
            case "mc" :
                multipleChoiceDao.deleteInBatch(multipleChoiceDao.findAll(ids));
        }
        return true;
    }

    @Override
    public TrueFalse findtf(Long id) {
        return trueFalseDao.findOne(id);
    }

    @Override
    public BlankFilling findbf(Long id) {
        return blankFillingDao.findOne(id);
    }

    @Override
    public SimpleChoice findsc(Long id) {
        return simpleChoiceDao.findOne(id);
    }

    @Override
    public MultipleChoice findmc(Long id) {
        return multipleChoiceDao.findOne(id);
    }

    @Override
    public List<TrueFalse> findNewtf() {
        List<TrueFalse> list = trueFalseDao.findAll();
//        list.forEach(item -> {
//            item.setUid(item.getQuestionType() + item.getId());
//        });
        return list;
    }

    @Override
    public List<BlankFilling> findNewbf() {
        return blankFillingDao.findAll();
    }

    @Override
    public List<SimpleChoice> findNewsc() {
        List<SimpleChoice> list = simpleChoiceDao.findAll();
//        list.forEach(item -> {
//            item.setUid(item.getQuestionType() + item.getId());
//        });
        return list;
    }

    @Override
    public List<MultipleChoice> findNewmc() {
        List<MultipleChoice> list = multipleChoiceDao.findAll();
//        list.forEach(item -> {
//            item.setUid(item.getQuestionType() + item.getId());
//        });
        return list;
    }
}
