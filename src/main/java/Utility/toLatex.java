package Utility;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import org.apache.commons.collections4.map.MultiKeyMap;

public class toLatex {

  public static String mapToLatex(MultiKeyMap<String, Integer> mapa, String opis) {
    StringBuilder stringBuilder = new StringBuilder();
    List<String> locations = Arrays
        .asList("japan", "west-germany", "canada", "usa", "france", "uk");

    // create header
    stringBuilder.append("\\begin{tabular}{|c|c|c|c|c|c|c|}\n");
    stringBuilder.append("\\hline\n");
    StringJoiner joiner1 = new StringJoiner(" & ");

    joiner1.add(" X ");
    for (String location : locations) {
      joiner1.add(location);
    }

    stringBuilder.append(joiner1.toString());
    stringBuilder.append("\\\\\n");

    for (String location : locations) {
      // kreska
      stringBuilder.append("\\hline\n");

      StringJoiner joiner = new StringJoiner(" & ");
      joiner.add(location);
      for (String rowLocation : locations) {
        int value;
        try {
          value = mapa.get(location, rowLocation);
        } catch (NullPointerException e) {
          value = 0;
        }

        joiner.add(Integer.toString(value));
      }
      stringBuilder.append(joiner.toString());
      stringBuilder.append("\\\\\n");
    }
    stringBuilder.append("\\hline\n");
    stringBuilder.append("\\end{tabular}\n");
    stringBuilder.append("{\\raggedright ");
    stringBuilder.append(opis);
    stringBuilder.append(" \\par}");

    return stringBuilder.toString();
  }

  public static String mapToPercentLatex(MultiKeyMap<String, Integer> mapa, String opis) {
    StringBuilder stringBuilder = new StringBuilder();
    List<String> locations = Arrays
        .asList("japan", "west-germany", "canada", "usa", "france", "uk");
    DecimalFormat df = new DecimalFormat("#.00");

    // create header
    stringBuilder.append("\\begin{tabular}{|c|c|c|}\n");
    stringBuilder.append("\\hline\n");

    StringJoiner joiner1 = new StringJoiner(" & ");
    joiner1.add("Etykieta");
    joiner1.add("Wynik klasyfikacji");
    joiner1.add("procent");
    stringBuilder.append(joiner1.toString());
    stringBuilder.append("\\\\\n");

    for (String location : locations) {
      // kreska
      stringBuilder.append("\\hline\n");

      StringJoiner joiner = new StringJoiner(" & ");
      joiner.add(location);
      int licznosc = 0;

      for (String rowLocation : locations) {
        int value;
        try {
          licznosc += mapa.get(location, rowLocation);
        } catch (NullPointerException e) {

        }
      }
      try {
        joiner.add(mapa.get(location, location) + " / " + Integer.toString(licznosc));
      } catch (NullPointerException e) {
        joiner.add(0 + " / " + Integer.toString(licznosc));
      }

      try {
        joiner.add(
            df.format(((double) mapa.get(location, location) * 100d) / (double) licznosc) + "\\%");
      } catch (Exception e) {
        joiner.add("0%");
      }

      stringBuilder.append(joiner.toString());
      stringBuilder.append("\\\\\n");
    }
    stringBuilder.append("\\hline\n");
    stringBuilder.append("\\end{tabular}\n");
    stringBuilder.append("{\\raggedright ");
    stringBuilder.append(opis);
    stringBuilder.append(" \\par}");

    return stringBuilder.toString();
  }

}

/*

\begin{tabular}{|c|c|c|}
\hline
ala & ola & józia\\
\hline
janek & karol & zdzicho\\
\hline
grzesiek & czesiek & ktoś\\
\hline
ola & ania & zosia\\
\hline
\end{tabular}
\\
     {\raggedright This is where authors provide additional information about the data, including whatever notes are needed. \par}

 */