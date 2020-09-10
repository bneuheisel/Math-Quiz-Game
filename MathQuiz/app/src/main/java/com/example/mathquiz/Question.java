package com.example.mathquiz;

import java.util.Random;

public class Question {
    private int firstNumber;
    private int secondNumber;
    private int answer;

    //four possible choices
    private int [] answerArray;

    //four answer positions. Only one is correct
    private int answerPosition;

    //max value of firstNumber or secondNumber
    private int upperLimit;

    //Print out of the question
    private String mathQuestion;

    //generate random question
    public Question(int upperLimit){
        this.upperLimit = upperLimit;
        Random randNumberMaker = new Random();

        this.firstNumber = randNumberMaker.nextInt(upperLimit);
        this.secondNumber = randNumberMaker.nextInt(upperLimit);

        this.answer = this.firstNumber + this.secondNumber;
        this.mathQuestion = firstNumber + " + " + secondNumber + " = ";
        this.answerPosition = randNumberMaker.nextInt(4);

        this.answerArray = new int[] {0,1,2,3};
        this.answerArray[0] = answer + 1;
        this.answerArray[1] = answer +10;
        this.answerArray[2] = answer - 5;
        this.answerArray[3] = answer - 2;

        this.answerArray = shuffleArray(this.answerArray);

        answerArray[answerPosition] = answer;
    }
    private int[] shuffleArray(int[] array){
        int index, temp;
        Random randomNumberGenerator = new Random();

        for(int i = array.length - 1; i > 0; i--){
            index = randomNumberGenerator.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;

        }
        return array;
    }

    public int getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }

    public int getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(int secondNumber) {
        this.secondNumber = secondNumber;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int[] getAnswerArray() {
        return answerArray;
    }

    public void setAnswerArray(int[] answerArray) {
        this.answerArray = answerArray;
    }

    public int getAnswerPosition() {
        return answerPosition;
    }

    public void setAnswerPosition(int answerPosition) {
        this.answerPosition = answerPosition;
    }

    public int getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(int upperLimit) {
        this.upperLimit = upperLimit;
    }

    public String getMathQuestion() {
        return mathQuestion;
    }

    public void setMathQuestion(String mathQuestion) {
        this.mathQuestion = mathQuestion;
    }
}
