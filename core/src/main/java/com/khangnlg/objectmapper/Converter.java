package com.khangnlg.objectmapper;

/*
*
* M: model
* E: entity
*
* */
public interface Converter<M, E> {

    E convertModelToEntity (M m);

    M convertEntityToModel(E e);

}
