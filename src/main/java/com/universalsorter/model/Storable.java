package com.universalsorter.model;

/**
 * Маркерный интерфейс для классов, которые могут быть сохранены и восстановлены.
 */
public interface Storable {
    String serialize();

    Storable deserialize(String data);
}
