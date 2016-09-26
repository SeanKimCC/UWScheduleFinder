package com.sean.kim.dao;

import com.sean.kim.model.ExamDetails;
import com.sean.kim.model.impl.ExamDetailsImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by seankim on 2016-09-26.
 */
@Repository
public class ExamDetailsDao extends BaseDao<ExamDetailsImpl, ExamDetails> {

    public ExamDetailsDao() {
        super(ExamDetailsImpl.class);
    }


}
