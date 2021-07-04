package ru.job4j.template;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@Ignore
public class GeneratorImplTest {
    @Test
    public void whenNormalTemplate() {
        Generator generator = new GeneratorImpl();
        String actual = generator.produce("I am a ${name}, Who are ${subject}?", Map.of(
                "name", "Petr Arsentev",
                "subject", "you"
        ));
        String expected = "I am a Petr Arsentev, Who are you?";
        assertThat(actual, is(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenKeyNoFound() {
        Generator generator = new GeneratorImpl();
        String actual = generator.produce("I am a ${name}, Who are ${subject}?", Map.of(
                "name", "Petr Arsentev",
                "subjectssssss", "you"
        ));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenKeyTooMany() {
        Generator generator = new GeneratorImpl();
        String actual = generator.produce("I am a ${name}, Who are ${subject}?", Map.of(
                "name", "Petr Arsentev",
                "subjectssssss", "you",
                "someKey", "someValue"
        ));
    }
}