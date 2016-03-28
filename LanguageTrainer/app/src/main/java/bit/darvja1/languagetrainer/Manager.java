package bit.darvja1.languagetrainer;


import java.util.Random;

public class Manager {
    public Question[] createQuestions(){
        Question[] questions = new Question[11];
        Question qApple = new Question("Apple","Apfel","Der","/drawable/apple");
        Question qCar = new Question("Car","Auto","Das","/drawable/car");
        Question qTree = new Question("Tree","Baum","Der","/drawable/tree");
        Question qDuck = new Question("Duck","Ente","Die","/drawable/duck");
        Question qHouse = new Question("House","Haus","Das","/drawable/house");
        Question qWitch = new Question("Witch","Hexe","Die","/drawable/witch");
        Question qCow = new Question("Cow","Kuh","Die","/drawable/cow");
        Question qMilk = new Question("Milk","Milch","Die","/drawable/milk");
        Question qSheep = new Question("Sheep","Schaf","Das","/drawable/sheep");
        Question qStreet = new Question("Street","Strasse","Die","/drawable/street");
        Question qChair = new Question("Chair","Stuhl","Der","/drawable/chair");

        questions[0] = qApple;
        questions[1] = qCar;
        questions[2] = qTree;
        questions[3] = qDuck;
        questions[4] = qHouse;
        questions[5] = qWitch;
        questions[6] = qCow;
        questions[7] = qMilk;
        questions[8] = qSheep;
        questions[9] = qStreet;
        questions[10] = qChair;

        return questions;
    }

    public Question[] shuffleQuestions(Question[] questions){
        Random rand = new Random();
        int position1 = 0;
        int position2 = 0;
        Question temp;

        for (int i = 0; i < 100; i++){
            position1 = rand.nextInt(questions.length);
            position2 = rand.nextInt(questions.length);

            temp = questions[position1];
            questions[position1] = questions[position2];
            questions[position2] = temp;
        }

        return questions;
    }
}
