package br.com.zup.file.generator.format.write;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface DocumentTypeWrite {
    ByteArrayInputStream write(List<?> objects) throws Exception;
}
