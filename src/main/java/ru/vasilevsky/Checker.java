package ru.vasilevsky;

import ru.vasilevsky.document.*;

import java.io.InputStream;
import java.util.*;

public class Checker {
    private List<Integer> G = new ArrayList<>();
    private List<Boolean> real = new ArrayList<>();

    public void init(InputStream is) throws DocumentException {
        DocReaderImpl reader = new DocReaderImpl(is);

        List<String> names = new ArrayList<>(reader.getDocCount());
        {
            while (reader.hasNext()) {
                Document doc = reader.next();

                int i;
                if (!names.contains(doc.getName())) {
                    names.add(doc.getName());
                    real.add(true);
                    G.add(0);
                    i = G.size() - 1;
                } else {
                    i = names.indexOf(doc.getName());
                    real.set(i, true);
                }

                int x = 0;
                List<String> refs = doc.getRefs();
                for (String ref : refs) {
                    if (!names.contains(ref)) {
                        names.add(ref);
                        real.add(false);
                        G.add(0);
                    }
                    x |= 1 << names.indexOf(ref);
                }

                G.set(i, x);
            }
        }

        /*for (int i = 0; i < names.size(); i++) {
            List<String> refs = ds.get(i);
            for (String ref : refs) {
                if (names.indexOf(ref) > 0) {
                    G[i] |= 1 << names.indexOf(ref);
                }
            }
        }*/

    }

    public int countDeadLinks() {
        int c = 0;
        for (boolean r : real) {
            if (!r) c++;
        }
        return c;
    }

    public int countNotResolved() {
        for (int k = 0; k < G.size(); k++) {
            for (int i = 0; i < G.size(); i++) {
                int x = G.get(i);
                for (int j = 0; j < G.size(); j++) {
                    if ((G.get(i) & (1 << k)) > 0 && (G.get(k) & (1 << j)) > 0) x |= 1 << j;
                }
                G.set(i, x);
            }
        }

        int c = 0;
        for (int i = 1; i < G.size(); i++) {
            if ((G.get(0) & 1 << i) == 0 && real.get(i)) c++;
        }
        return c;
    }
}
