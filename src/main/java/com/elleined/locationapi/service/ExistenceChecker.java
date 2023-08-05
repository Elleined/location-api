package com.elleined.locationapi.service;

import java.util.Collection;

public interface ExistenceChecker<T> {
    boolean isAlreadyExists(T t);
    boolean isAlreadyExists(Collection<T> t);
}
