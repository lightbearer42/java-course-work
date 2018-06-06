package ru.vasilevsky.document;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocReaderImpl {
    private static final Pattern pattern = Pattern.compile("<(\\w+)\\s*(\\w+(='.*')?)*>");

    private int docCount = 0;

    private Scanner scanner;
    private Matcher matcher;

    private String curStr;
    private String prevStr;

    public DocReaderImpl(InputStream is) throws DocumentException {
        scanner = new Scanner(is);
        try {
            matcher = pattern.matcher("");
            docCount = scanner.nextInt();
        } catch (Exception e) {
            throw new DocumentException(e);
        }
    }

    public boolean hasNext() {
        do {
            prevStr = curStr;
            if (!scanner.hasNextLine()) return false;
            curStr = scanner.nextLine().toUpperCase();
            matcher = Pattern.compile("<HTML>").matcher(curStr);
        } while (!matcher.find());

        return true;
    }

    public Document next() throws DocumentException {
        Document document = new Document();
        document.setName(prevStr);

        List<String> refs = new ArrayList<>();
        m:
        do {
            matcher = Pattern.compile("<A\\s+HREF='(.*)'>|(<END>)").matcher(curStr);

            while (matcher.find()) {
                if (matcher.group(2) != null) {
                    break m;
                } else {
                    refs.add(matcher.group(1));
                }
            }

            prevStr = curStr;
            if (!scanner.hasNextLine()) break;
            curStr = scanner.nextLine().toUpperCase();
        } while (true);

        document.setRefs(refs);

        return document;
    }

    public int getDocCount() {
        return docCount;
    }
}
