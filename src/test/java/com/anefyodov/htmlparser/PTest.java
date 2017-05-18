package com.anefyodov.htmlparser;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class PTest {

    public static final String PATH = "src/test/resources/file.fil";
    public static final String PR_PATH = "src/test/resources/pr.fil";
    public static final Charset ENCODING = Charset.forName("UTF-8");

    @Test
    public void shouldReadFile() throws IOException {
        //System.out.println(Paths.get(".").toAbsolutePath().normalize().toString());
        final String content = readFile(PATH, ENCODING);
        assertThat(content).isNotNull();
        //System.out.println(content);
    }

    @Test
    public void shouldGetPreviousPageLink() throws IOException {
        final String content = readFile(PATH, ENCODING);
        final ListParser listParser = new ListParser(content);
        Optional<String> link = listParser.getPreviousLink();
        assertThat(link).isPresent();
        assertThat(link.orElse("")).isNotEmpty();
        System.out.println(link.orElse(""));
    }

    @Test
    public void shouldGetNextPageLink() throws IOException {
        final String content = readFile(PATH, ENCODING);
        final ListParser listParser = new ListParser(content);
        Optional<String> link = listParser.getNextLink();
        assertThat(link).isPresent();
        assertThat(link.orElse("")).isNotEmpty();
        System.out.println(link.orElse(""));
    }

    @Test
    public void shouldGetList() throws IOException {
        final String content = readFile(PATH, ENCODING);
        final ListParser listParser = new ListParser(content);
        List<Product> list = listParser.getProductList();
        Assertions.assertThat(list).isNotNull();
        Assertions.assertThat(list).hasSize(50);
        System.out.println(list);
    }

    @Test
    public void shouldGetIngr() throws IOException {
        final String content = readFile(PR_PATH, ENCODING);
        final ProduсtParser produсtParser = new ProduсtParser(content);
        final List<Ingr> ingrList = produсtParser.getIngrList();
        Assertions.assertThat(ingrList).isNotNull();
        Assertions.assertThat(ingrList).hasSize(14);
        System.out.println(ingrList);
    }

    static String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
