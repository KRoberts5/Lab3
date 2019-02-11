package com.example.jsu.lab3;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.*;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> secretWords;

    private String unscrambledWord;
    private String scrambledWord;
    private int correctLetters;
    private int incorrectGuesses;
    private String usedChars;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        resetGameData();

        secretWords = new ArrayList<>();
        this.generateWords();
        this.generateScrambledWord();


    }

    private void generateWords(){
        secretWords.add("APPLE");
        secretWords.add("BANANA");
        secretWords.add("CHERRY");
        secretWords.add("DRAGON");
        secretWords.add("ELDER");
        secretWords.add("FREIGHT");
        secretWords.add("GRANDFATHER");
        secretWords.add("HEIGHT");
        secretWords.add("IGLOO");
        secretWords.add("JAPANESE");
        secretWords.add("KANGAROO");
        secretWords.add("LIBERATOR");
        secretWords.add("MANCHESTER");
        secretWords.add("NOBODY");
        secretWords.add("OCTAGON");
        secretWords.add("PERSON");
        secretWords.add("QUILT");
        secretWords.add("ROADSIDE");
        secretWords.add("SADDLE");
        secretWords.add("TURTLE");
        secretWords.add("UNDERSTAND");
        secretWords.add("VENTURE");
        secretWords.add("WISECRACK");
        secretWords.add("XYLOPHONE");
        secretWords.add("YESTERDAY");
        secretWords.add("ZEBRA");
    }

    private void generateScrambledWord(){
        int index = (int) (Math.random() * secretWords.size());
        unscrambledWord = secretWords.get(index);

        ArrayList<String> splitWord = new ArrayList(Arrays.asList(unscrambledWord.split("")));
        Collections.shuffle(splitWord);
        for(String c: splitWord)
            scrambledWord += c;

        TextView unusedCharOutput = (TextView) findViewById(R.id.unusedCharOutput);
        unusedCharOutput.setText(scrambledWord);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void submit(View v){

        EditText input = (EditText)findViewById(R.id.playerInput);
        CharSequence characters = input.getText();

        char letter = characters.charAt(0);

        if(Character.isLetter(letter)){
            letter = Character.toUpperCase(letter);

            if(unscrambledWord.charAt(correctLetters) == letter)
                addLetter(letter);
            else
                ++incorrectGuesses;
        }
        else
            ++incorrectGuesses;

        updateOutput();
        checkForWin();
    }

    private void addLetter(char letter){

        ++correctLetters;
        usedChars += letter;
    }

    private void startNewGame(){

        resetGameData();
        generateScrambledWord();
    }

    private void resetGameData(){
        correctLetters = 0;
        incorrectGuesses = 0;
        unscrambledWord = "";
        scrambledWord = "";
        usedChars = "";
    }

    private void updateOutput(){
        TextView usedOutput = (TextView)findViewById(R.id.usedCharOutput);
        usedOutput.setText(usedChars);

        TextView incorrectGuessOutput = (TextView) findViewById(R.id.incorrectGuessOutput);
        incorrectGuessOutput.setText("Incorrect Guesses: " + incorrectGuesses);

        EditText input = (EditText)findViewById(R.id.playerInput);
        input.setText("");
    }

    private void checkForWin(){
        if(correctLetters == unscrambledWord.length()){

            TextView usedChars = (TextView) findViewById(R.id.usedCharOutput);
            usedChars.setText("");

            TextView incorrectGuessOutput = (TextView) findViewById(R.id.incorrectGuessOutput);
            incorrectGuessOutput.setText("");

            TextView winOutput = (TextView)findViewById(R.id.winOutput);
            winOutput.setText("Congratulations! You guessed the word " + unscrambledWord + " with only " + incorrectGuesses + " incorrect guess(es)!");

            startNewGame();
        }
        else{
            TextView winOutput = (TextView) findViewById(R.id.winOutput);
            if(winOutput.getText().length() > 0)
                winOutput.setText("");
        }
    }
}
