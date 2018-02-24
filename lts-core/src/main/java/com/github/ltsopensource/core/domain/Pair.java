package com.github.ltsopensource.core.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Pair<Key, Value> implements Serializable{

    private static final long serialVersionUID = 1475971713300057708L;

    private Key key;

    private Value value;

    public Pair(Key key, Value value) {
        this.key = key;
        this.value = value;
    }
}

