import java.io.BufferedReader;
import java.io.File;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader;
        String land,area;
        List<String> filteredLands=new ArrayList<>();
        File csvFile = new File("result.txt");
        FileWriter fileWriter = new FileWriter(csvFile);
        List<String> easyTasks=new ArrayList<>();
        List<String> hardTasksMorty=new ArrayList<>();
        String scoreString;
        int scoreRick=0,scoreMorty=0, scoreSummer=0, scoreBeth=0, scoreJerry=0;
        String totalRick, totalMorty, totalSummer, totalBeth, totalJerry;
        List<String> totalScores=new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader("championship.txt"));
            String line = reader.readLine();


            while (line != null) {
                String[] splitStr = line.split("#");
                scoreString=splitStr[4];
                int number = Integer.parseInt(scoreString);
                if (Objects.equals(splitStr[3], "easy"))
                    easyTasks.add(splitStr[2]);
                if (Objects.equals(splitStr[1], "Morty Smith") && Objects.equals(splitStr[3], "hard") && Objects.equals(splitStr[5], "win"))
                    hardTasksMorty.add(splitStr[2]);

                if (Objects.equals(splitStr[1], "Rick Sanchez") && Objects.equals(splitStr[5], "win"))
                    scoreRick=scoreRick+number;
                if (Objects.equals(splitStr[1], "Morty Smith") && Objects.equals(splitStr[5], "win"))
                    scoreMorty=scoreMorty+number;
                if (Objects.equals(splitStr[1], "Summer Smith") && Objects.equals(splitStr[5], "win"))
                    scoreSummer=scoreSummer+number;
                if (Objects.equals(splitStr[1], "Beth Smith") && Objects.equals(splitStr[5], "win"))
                    scoreBeth=scoreBeth+number;
                if (Objects.equals(splitStr[1], "Jerry Smith") && Objects.equals(splitStr[5], "win"))
                    scoreJerry=scoreJerry+number;

                if (Objects.equals(splitStr[1], "Rick Sanchez") && Objects.equals(splitStr[5], "fail"))
                    scoreRick=scoreRick-2*number;
                if (Objects.equals(splitStr[1], "Morty Smith") && Objects.equals(splitStr[5], "fail"))
                    scoreMorty=scoreMorty-2*number;
                if (Objects.equals(splitStr[1], "Summer Smith") && Objects.equals(splitStr[5], "fail"))
                    scoreSummer=scoreSummer-2*number;
                if (Objects.equals(splitStr[1], "Beth Smith") && Objects.equals(splitStr[5], "fail"))
                    scoreBeth=scoreBeth-2*number;
                if (Objects.equals(splitStr[1], "Jerry Smith") && Objects.equals(splitStr[5], "fail"))
                    scoreJerry=scoreJerry-2*number;
                line = reader.readLine();
            }
            Collections.sort(easyTasks, Collections.reverseOrder());
            Collections.sort(hardTasksMorty);
            for (String s : easyTasks)
                System.out.println(s);
            System.out.println();
            for (String s : hardTasksMorty)
                System.out.println(s);

            String str=Integer.toString(scoreMorty);
            totalMorty="Morty Smith"+"&"+str;
            str=Integer.toString(scoreRick);
            totalRick="Rick Smith"+"&"+str;
            str=Integer.toString(scoreSummer);
            totalSummer="Summer Smith"+"&"+str;
            str=Integer.toString(scoreBeth);
            totalBeth="Beth Smith"+"&"+str;
            str=Integer.toString(scoreJerry);
            totalJerry="Jerry Smith"+"&"+str;
            totalScores.add(totalBeth);
            totalScores.add(totalRick);
            totalScores.add(totalJerry);
            totalScores.add(totalMorty);
            totalScores.add(totalSummer);
            totalScores.sort(Comparator.comparing(s -> Integer.parseInt(s.replaceAll("\\D", ""))));
            for (String s : totalScores){
                StringBuilder lineCsv = new StringBuilder();
                lineCsv.append(s.toString());
                lineCsv.append("\n");
                fileWriter.write(lineCsv.toString());
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileWriter.close();

    }
}