package com.sean.kim.model.impl;

import com.sean.kim.model.BaseModel;

import javax.persistence.*;

/**
 * Created by seankim on 2016-09-26.
 */
@MappedSuperclass
public abstract class BaseModelImpl implements BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    public long getId()
    {
        return id;
    }
}
