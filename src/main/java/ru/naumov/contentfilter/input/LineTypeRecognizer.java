package ru.naumov.contentfilter.input;

import java.lang.reflect.Type;
import java.util.Optional;

public interface LineTypeRecognizer {
    Optional<Type> recognize(String line);
}
