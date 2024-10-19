package com.google.mediapipe.examples.gesturerecognizer;

import android.util.Log;

import net.java.sen.SenFactory;
import net.java.sen.StringTagger;
import net.java.sen.dictionary.Token;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Hurigana {

        StringBuilder huri = new StringBuilder();
        StringBuilder read = new StringBuilder();

        Hurigana(String mushi) throws IOException {
                try {
                        // 判別する虫の名前とそれぞれの範囲
                        String[] name = { "アメンボ", "アリ", "オサムシ", "ガ", "カゲロウ", "カブトムシ", "カマキリ", "カミキリムシ", "カメムシ", "カワゲラ",
                                        "クモ",
                                        "クワガタムシ", "ゲンゴロウ", "コガネムシ", "ゴキブリ", "セミ", "ゾウムシ", "タガメ", "タマムシ", "ダンゴムシ", "チョウ",
                                        "テントウムシ", "トンボ", "ナナフシ", "ハサミムシ", "ハチ", "バッタ", "ハンミョウ", "ホタル" };
                        int[] hajime = { 4, 2, 1, 0, -1, 5, -1, 0, 2, -1, -1, -1, 9, -1, 0, -1, 2, 8, -1, -1, -1, 1, -1,
                                        -1, 1,
                                        -1, -1, -1, -1 };
                        int[] owari = { 9, 4, 4, 3, 1, 7, 2, 3, 6, 3, 2, 2, 11, 3, 3, 2, 4, 10, 2, 2, 2, 4, 3, 1, 4, 2,
                                        2, 2,
                                        2 };

                        // nameのどれにも当てはまらなかったら時の値
                        int hanni1 = 0;
                        int hanni2 = 3;

                        // nameと一致するか確認
                        for (int i = 0; i < name.length; i++) {
                                if (mushi.equals(name[i])) {
                                        hanni1 = hajime[i];
                                        hanni2 = owari[i];
                                }
                        }

                        // Wikipediaのページを取得
                        Document document = Jsoup.connect("https://ja.wikipedia.org/wiki/" + mushi).get();
                        Log.d("hurigana", document.toString());

                        // #mw-content-textの下にあるpタグを取得
                        Elements elements = document.select("#mw-content-text p");

                        int count = 0;
                        String s = "";
                        // 範囲内ののテキストを処理
                        for (Element element : elements) {
                                if ((count > hanni1) && (count < hanni2)) {
                                        s = s + element.text();
                                }
                                count++;
                        }

                        // 文章中に含まれる、[1]などを排除
                        String delete = "";
                        int front = 0;
                        for (int j = 0; j < s.length(); j++) {
                                if (s.charAt(j) == '[') {
                                        front = 1;
                                }
                                if (front == 0) {
                                        delete = delete + s.charAt(j);
                                }
                                if (s.charAt(j) == ']') {
                                        front = 0;
                                }
                        }

                        // この3行で解析
                        StringTagger tagger = SenFactory.getStringTagger(null);
                        List<Token> tokens = new ArrayList<Token>();
                        tagger.analyze(delete, tokens);

                        for (Token token : tokens) {
                                String moto = token.getSurface(); // 文章にあった通りの形
                                String yomi = token.getMorpheme().getReadings().toString();// 読み方を取得
                                yomi = yomi.substring(1, yomi.length() - 1); // []がつくため外す

                                // 読み方が複数ある場合に対応させる
                                int yomiCount = 0;
                                String str = "";
                                for (int j = 0; j < yomi.length(); j++) {
                                        yomiCount++;
                                        if (yomi.charAt(j) == ',') {
                                                break;
                                        }
                                }
                                if (yomi.length() != yomiCount) {
                                        for (int j = 0; j < yomiCount - 1; j++) {
                                                str = str + yomi.charAt(j);
                                        }
                                        yomi = str;
                                }

                                // 読み方はカタカナなのでひらがなに直したものも用意
                                String hira;
                                StringBuilder sb = new StringBuilder();
                                for (int j = 0; j < yomi.length(); j++) {
                                        char c = yomi.charAt(j);
                                        if ((0x30A1 <= c) && (c <= 0x30F3)) { // カタカナか判別
                                                c -= 0x0060;
                                        }
                                        sb.append(c);
                                }
                                hira = sb.toString();

                                if (yomi.length() == 0) {
                                        huri.append(moto);
                                        read.append(moto);
                                } else {
                                        // 文字を一つずつ判定し、漢字だったらRubyタグをつけて出力
                                        int countHira = 0; // hiraの現在のインデックスを表す
                                        int start = -1; // 漢字がはじまるところのインデックスを記録
                                        StringBuilder kanji = new StringBuilder(); // 漢字部分を保持する
                                        for (int j = 0; j < moto.length(); j++) { // 1文字ずつ判別
                                                char motoC = moto.charAt(j);
                                                if ((motoC >= 0x4E00) && (motoC <= 0x9FFF)) { // 漢字かを判別
                                                        if (kanji.length() == 0) { // kanjijの長さが0だったらstartをiにする
                                                                start = j;
                                                        }
                                                        kanji.append(motoC); // kanjiは漢字部分を保持するためmotoCをkanjiに追加
                                                } else { // 漢字ではない場合
                                                         // kanjiの長さが0より大きい場合、ふりがなをつける
                                                        if (kanji.length() > 0) {
                                                                // カタカナをひらがなに変換
                                                                char hiraC = motoC;
                                                                if ((motoC >= '\u30A1')
                                                                                && (motoC <= '\u30F6')) { // カタカナの範囲
                                                                        hiraC -= 0x60;
                                                                } else if ((motoC >= '\u30FD')
                                                                                && (motoC <= '\u30FE')) { // カタカナの繰り返し記号の範囲
                                                                        hiraC -= 0x60;
                                                                }
                                                                for (; countHira < hira.length(); countHira++) {
                                                                        if (hiraC == hira.charAt(countHira)) { // hiraCとhiraのcount番目の文字が一致したらfor文を抜け出す
                                                                                break;
                                                                        }
                                                                }
                                                                // kanjiをString型に変換したものとhiraのstartからcountの間の文字列
                                                                huri.append("{" + kanji + ";"
                                                                                + hira.substring(start,
                                                                                                countHira)
                                                                                + "}");
                                                                read.append(hira.substring(start, countHira));
                                                                // 次の感じに備えてkanjiとstartを初期状態にする
                                                                kanji = new StringBuilder();
                                                                start = -1;
                                                        }
                                                        // 漢字ではないためそのまま出力
                                                        huri.append(moto.charAt(j));
                                                        read.append(moto.charAt(j));
                                                        countHira++;
                                                }
                                        }
                                        // kanjiの長さが0より大きい場合、for文で残った部分を出力
                                        if (kanji.length() > 0) {
                                                huri.append("{" + kanji + ";" + hira.substring(start)
                                                                + "}");
                                                read.append(hira.substring(start));
                                                kanji = new StringBuilder();
                                        }
                                }
                        }
                } catch (IOException e) {
                        Log.e("確認", "これはうまくいっていない場合出力されます");
                        e.printStackTrace();
                }

        }

        public String huriganagaesi() {
                Log.d("hurigana", huri.toString());
                return huri.toString();
        }

        public String readText() {
                String text = read.toString();
                StringBuilder string = new StringBuilder();
                boolean inParentheses = false; // 括弧内かどうか判定する
                char c;
                for (int i = 0; i < text.length(); i++) {
                        c = read.charAt(i);
                        if (c == '（') { // 括弧内である
                                inParentheses = true;
                        }
                        if (c == '）') { // 括弧外の時
                                inParentheses = false;
                                i++;
                                c = read.charAt(i);
                        }
                        if (!inParentheses && (c != '）') && (c != '{') && (c != '}') && (c != '「') && (c != '」')
                                        && (c != '『') && (c != '』')) {
                                string.append(c);
                        }
                }
                return string.toString();
        }
}
