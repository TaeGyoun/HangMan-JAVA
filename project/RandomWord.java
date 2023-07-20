package project;

import java.io.*;
import java.util.*;

public class RandomWord {
    ArrayList<String> saveWords = new ArrayList<>();

    String word;
    String levelStr = "";
    Scanner in = new Scanner(System.in);

    public void ExactRandWord() throws IOException {
        // 레벨에 따라 다른 텍스트 파일 읽어오기
        LevelSelect();
        File file = new File("C:/Java/project/words/" + levelStr);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        // 읽어온 텍스트 내용 담아내기
        ArrayList<String> words = new ArrayList<>();
        String str = "";
        while ((str = br.readLine()) != null) {
            words.add(str);
        }
        br.close();
        fr.close();

        // 담아낸 텍스트 단어 랜덤으로 출력
        int randNum = (int) (Math.random() * words.size());
        word = words.get(randNum);

        //단어 길이만큼 '-'로 가리기
        String hiddenWord = new String(new char[word.length()]).replace('\0', '-');

        //그림 함수 호출
        DrawHangMan draw = new DrawHangMan();

        int life = 5;
        int failCnt = 0;
        int susCnt = 0;
        while (true) {
            //창 지우기
            ClearScreen();
            draw.draw_man();

            System.out.println("단어 : " + hiddenWord + "(" + hiddenWord.length() + "글자)");
            System.out.println("목숨 : " + draw.getLife());
            System.out.print("알파벳 입력 : ");

            if (draw.getLife() == 0) {//실패
                System.out.println();
                System.out.println("정답 : " + word);
                System.out.println("실패!");
                System.out.println("틀린 횟수 : " + failCnt + " / 성공 횟수 : " + susCnt);
                FailNote();
            } 
            else if (!hiddenWord.contains("-") && draw.getLife() > 0) {//성공
                System.out.println();
                System.out.println("성공!");
                System.out.println("틀린 횟수 : " + failCnt + " / 성공 횟수 : " + susCnt);
                RestartGame();
            }

            char guess = in.next().charAt(0); // char 값 입력

            //입력한 값과 단어 비교
            if (word.contains(String.valueOf(guess))) {//문제 안에 입력한 값이 있다면
                susCnt++;
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == guess) {
                        hiddenWord = hiddenWord.substring(0, i) + guess + hiddenWord.substring(i + 1); // 입력한 값으로 변경
                    }
                }
            } 
            else {// 없다면
                life--;
                failCnt++;
                draw.setLife(life);
                draw.getLife();
            }
        }
    }

    public void LevelSelect() {
        // 난이도 설정
        int level = 0;

        System.out.print("난이도 선택(1. 쉬움 2. 노말 3. 어려움 4. 오답노트) : ");
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                level = sc.nextInt();
                if (level == 1) {
                    levelStr = "easyWords.txt";
                    break;
                } else if (level == 2) {
                    levelStr = "nomalWords.txt";
                    break;
                } else if (level == 3) {
                    levelStr = "hardWords.txt";
                    break;
                } else if (level == 4) {
                    if (saveWords.isEmpty()) {
                        ClearScreen();
                        System.out.println("저장된 오답노트 없음");
                        System.out.print("난이도 선택(1. 쉬움 2. 노말 3. 어려움 4. 오답노트) : ");
                    } else {
                        levelStr = "failNote";
                        break;
                    }
                }
            } catch (InputMismatchException e) {
                ClearScreen();
                sc = new Scanner(System.in);
                System.out.println("숫자 입력!!!");
                System.out.print("난이도 선택(1. 쉬움 2. 노말 3. 어려움 4. 오답노트) : ");
            }
        }
    }

    public void RestartGame() throws IOException {
        System.out.print("다시하기(y/n) : ");
        String choice = in.next();
        if (choice.equals("y")) {
            ClearScreen();
            this.ExactRandWord();
        } 
        else if (choice.equals("n")) {
            System.out.println("종료");
            System.exit(0);
        }
    }
    
    public void FailNote() throws IOException {
        System.out.print("실패 오답노트 추가?(y/n) or 오답노트보기(o): ");
        String choice = in.next();

        if (choice.equals("y")) {
            ClearScreen();
            saveWords.add(word);//틀린 단어 저장
            System.out.println("오답노트 : " + saveWords);

            //오답노트 파일로 만들기
            File file = new File("C:/Java/project/words/" + "failNote");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (String str : saveWords) {
                writer.write(str);
                writer.newLine();
            }
            writer.close();
            RestartGame();
        } else if (choice.equals("o")) {
            saveWords.add(word);// 틀린 단어 저장
            ClearScreen();

            if (saveWords.isEmpty()) {
                System.out.println("저장된 오답노트 없음");
                RestartGame();
            }

            System.out.println("저장된 오답노트 : " + saveWords);
            System.out.print("게임으로 돌아가겠습니까?(y/n) : ");

            String choice2 = in.next();
            if (choice2.equals("y")) {
                RestartGame();
            } else if (choice2.equals("n")) {
                System.out.println("종료");
                System.exit(0);
            }
        } else{
            RestartGame();
        }
    }
    
    public void ClearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}