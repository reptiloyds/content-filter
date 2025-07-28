package ru.naumov.contentfilter.handler;

import ru.naumov.contentfilter.output.buffer.FloatOutputBuffer;
import ru.naumov.contentfilter.output.buffer.IntegerOutputBuffer;
import ru.naumov.contentfilter.output.buffer.OutputBuffer;
import ru.naumov.contentfilter.output.buffer.StringOutputBuffer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LineHandler {
    private final List<OutputBuffer<?>> outputBuffers = new ArrayList<>();
    public final List<OutputBuffer<?>> readOnlyOutputBuffers = Collections.unmodifiableList(outputBuffers);

    public LineHandler() {
        outputBuffers.add(new IntegerOutputBuffer());
        outputBuffers.add(new FloatOutputBuffer());
        outputBuffers.add(new StringOutputBuffer());
    }

    public void handle(List<String> lines) {
        for (String line : lines) {
            handle(line);
        }
    }

    private void handle(String line) {
        for (OutputBuffer<?> outputBuffer : outputBuffers) {
            if (outputBuffer.tryAppendContent(line)) {
                return;
            }
        }

        //TODO: throw not handled exception
    }
}
