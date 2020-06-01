package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.QuizContract.*;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 15;



    private static QuizDbHelper instance;

    private SQLiteDatabase db;

    private QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context){
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_CATEGORIES_TABLE = " CREATE TABLE " +
                CategoriesTable.TABLE_NAME + "( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +

                ")";

        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable() {
        Category c1 = new Category("Kolory");
        insertCategory(c1);
        Category c2 = new Category("Zwierzęta");
        insertCategory(c2);
        Category c3 = new Category("Owoce");
        insertCategory(c3);
        Category c4 = new Category("Transport");
        insertCategory(c4);
        Category c5 = new Category("Warzywa");
        insertCategory(c5);
        Category c6 = new Category("Części ciała");
        insertCategory(c6);
        Category c7 = new Category("Sport");
        insertCategory(c7);
        Category c8 = new Category("Zawody");
        insertCategory(c8);
        Category c9 = new Category("Meble");
        insertCategory(c9);
        Category c10 = new Category("Ubrania");
        insertCategory(c10);
    }

    public void addCategory(Category category){
        db = getWritableDatabase();
        insertCategory(category);
    }

    public void addCategories(List<Category> categories){
        db = getWritableDatabase();

        for (Category category : categories){
            insertCategory(category);
        }
    }

    private void insertCategory(Category category) {
        ContentValues cv = new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(CategoriesTable.TABLE_NAME, null, cv);
    }

    private void fillQuestionsTable() {

        Question q1 = new Question("BLACK:",
                "Czarny", "Niebieski", "Pomarańczowy", 1,
                Question.DIFFICULTY_EASY, Category.KOLORY);
        insertQuestion(q1);

        Question q2 = new Question("GREY",
                "Zielony", "Szary", "Złoty", 2,
                Question.DIFFICULTY_EASY, Category.KOLORY);
        insertQuestion(q2);

        Question q3 = new Question("GREEN",
                "Brązowy", "Biały", "Zielony", 3,
                Question.DIFFICULTY_EASY, Category.KOLORY);
        insertQuestion(q3);

        Question q4 = new Question("SILVER",
                "Srebrny", "Fioletowy", "Brązowy", 1,
                Question.DIFFICULTY_EASY, Category.KOLORY);
        insertQuestion(q4);

        Question q5 = new Question("BROWN",
                "Brązowy", "Złoty", "Biały", 1,
                Question.DIFFICULTY_EASY, Category.KOLORY);
        insertQuestion(q5);

        Question q6 = new Question("ORANGE",
                "Czarny", "Pomarańczowy", "Złoty", 2,
                Question.DIFFICULTY_EASY, Category.KOLORY);
        insertQuestion(q6);

        Question q8 = new Question("WHITE",
                "Czarny", "Biały", "Żółty", 2,
                Question.DIFFICULTY_EASY, Category.KOLORY);
        insertQuestion(q8);

        Question q9 = new Question("YELLOW",
                "Złoty", "Żółty", "Zielony", 2,
                Question.DIFFICULTY_EASY, Category.KOLORY);
        insertQuestion(q9);

        Question q10 = new Question("PINK",
                "Różowy", "Biały", "Czarny", 1,
                Question.DIFFICULTY_EASY, Category.KOLORY);
        insertQuestion(q10);

        Question q11 = new Question("GOLDEN",
                "Biały", "Czarny", "Złoty", 3,
                Question.DIFFICULTY_EASY, Category.KOLORY);
        insertQuestion(q11);

        Question q12 = new Question("BLUE",
                "Niebieski", "Biały", "Czarny", 1,
                Question.DIFFICULTY_EASY, Category.KOLORY);
        insertQuestion(q12);

        Question q13 = new Question("RED",
                "Fioletowy", "Brązowy", "Czerwony", 3,
                Question.DIFFICULTY_EASY, Category.KOLORY);
        insertQuestion(q13);

        Question q7 = new Question("STEEL",
                "Stalowy", "Srebrny", "Onyksowy", 1,
                Question.DIFFICULTY_MEDIUM, Category.KOLORY);
        insertQuestion(q7);

        Question q14 = new Question("ONYX",
                "Antracytowy", "Onyksowy", "Popielaty", 2,
                Question.DIFFICULTY_MEDIUM, Category.KOLORY);
        insertQuestion(q14);

        Question q15 = new Question("JET BLACK",
                "Granatowy", "Czarny", "Kruczoczarny", 3,
                Question.DIFFICULTY_MEDIUM, Category.KOLORY);
        insertQuestion(q15);

        Question q16 = new Question("ASURE",
                "Lazurowy", "Turkusowy", "Szafirowy", 1,
                Question.DIFFICULTY_MEDIUM, Category.KOLORY);
        insertQuestion(q16);

        Question q17 = new Question("SAPPHIRE",
                "Błękitny", "Szafirowy", "Barwinkowy", 2,
                Question.DIFFICULTY_MEDIUM, Category.KOLORY);
        insertQuestion(q17);

        Question q18 = new Question("MAROON",
                "Fuksja", "Mahoniowy", "Bordowy", 3,
                Question.DIFFICULTY_MEDIUM, Category.KOLORY);
        insertQuestion(q18);

        Question q19 = new Question("OCHRE",
                "Ochra", "Indygo", "Bordowy", 1,
                Question.DIFFICULTY_MEDIUM, Category.KOLORY);
        insertQuestion(q19);

        Question q20 = new Question("CYAN",
                "Karminowy", "Niebieskozielony", "Antracytowy", 2,
                Question.DIFFICULTY_MEDIUM, Category.KOLORY);
        insertQuestion(q20);

        Question q21 = new Question("AQUAMRINE",
                "Szkarłatny", "Turkusowy", "Seledynowy", 3,
                Question.DIFFICULTY_MEDIUM, Category.WARZYWA);
        insertQuestion(q21);

        Question q22 = new Question("Scarlet",
                "Szkarłatny", "Bursztynowy", "Antracytowy", 1,
                Question.DIFFICULTY_MEDIUM, Category.KOLORY);
        insertQuestion(q22);

        Question q23 = new Question("GINGER",
                "Karmazynowy", "Rudy", "Turkusowy", 2,
                Question.DIFFICULTY_MEDIUM, Category.KOLORY);
        insertQuestion(q23);

        Question q24 = new Question("OLIVE GREEN",
                "Rudy", "Granatowy", "Oliwkowy", 3,
                Question.DIFFICULTY_MEDIUM, Category.KOLORY);
        insertQuestion(q24);

        Question q25 = new Question("MAHOGANY",
                "Mahoniowy", "B", "C", 1,
                Question.DIFFICULTY_HARD, Category.KOLORY);
        insertQuestion(q25);

        Question q26 = new Question("SANDY BROWN",
                "Kasztanowy", "Piaskowy", "Płowożółty", 2,
                Question.DIFFICULTY_HARD, Category.KOLORY);
        insertQuestion(q26);

        Question q27 = new Question("CHAMPAGNE",
                "Waniliowy", "Kremowy", "ALABASTER", 3,
                Question.DIFFICULTY_HARD, Category.KOLORY);
        insertQuestion(q27);

        Question q28 = new Question("ALABASTER",
                "Alabastrowy", "Kość słoniowa", "Beżowy", 1,
                Question.DIFFICULTY_HARD, Category.KOLORY);
        insertQuestion(q28);

        Question q29 = new Question("HONEYDEW",
                "Beżowy", "W kolorze spadzi", "Czarny", 2,
                Question.DIFFICULTY_HARD, Category.KOLORY);
        insertQuestion(q29);

        Question q30 = new Question("ASH",
                "A", "B", "Popielaty", 3,
                Question.DIFFICULTY_HARD, Category.KOLORY);
        insertQuestion(q30);

        Question q31 = new Question("ANTHRACITE",
                "Antracytowy", "Stalowy", "Srebrny", 1,
                Question.DIFFICULTY_HARD, Category.KOLORY);
        insertQuestion(q31);

        Question q32 = new Question("EMERALD",
                "Oliwkowy", "Szmaragdowy zielony", "Jadeitowy", 2,
                Question.DIFFICULTY_HARD, Category.KOLORY);
        insertQuestion(q32);

        Question q33 = new Question("PALE PINK",
                "Koralowy róż", "Jaskrawy róż", "Blady róż", 3,
                Question.DIFFICULTY_HARD, Category.KOLORY);
        insertQuestion(q33);

        Question q34 = new Question("TANGERINE",
                "Mandarynkowy", "Morelowy", "Rudy", 1,
                Question.DIFFICULTY_HARD, Category.KOLORY);
        insertQuestion(q34);

        Question q35 = new Question("APRICOT",
                "Dyniowy", "Morelowy", "Ceglasty", 2,
                Question.DIFFICULTY_HARD, Category.KOLORY);
        insertQuestion(q35);

        Question q36 = new Question("CARROT",
                "Ogórek", "Marchewka", "Pietruszka", 2,
                Question.DIFFICULTY_EASY, Category.WARZYWA);
        insertQuestion(q36);

        Question q37 = new Question("PARSLEY",
                "Sałata", "Por", "Pietruszka", 3,
                Question.DIFFICULTY_EASY, Category.WARZYWA);
        insertQuestion(q37);

        Question q38 = new Question("CUCUMBER",
                "Ogórek", "Pomidor", "Ziemniak", 1,
                Question.DIFFICULTY_EASY, Category.WARZYWA);
        insertQuestion(q38);

        Question q39 = new Question("LETTUCE",
                "Marchewka", "Sałata", "Rzepa", 2,
                Question.DIFFICULTY_EASY, Category.WARZYWA);
        insertQuestion(q39);

        Question q40 = new Question("CABBAGE",
                "Seler", "Kapusta", "Cebula", 3,
                Question.DIFFICULTY_EASY, Category.WARZYWA);
        insertQuestion(q40);

        Question q41 = new Question("TOMATO",
                "Pomidor", "Ziemniak", "Fasola", 1,
                Question.DIFFICULTY_EASY, Category.WARZYWA);
        insertQuestion(q41);

        Question q42 = new Question("POTATO",
                "Pomidor", "Ziemniak", "Czosnek", 2,
                Question.DIFFICULTY_EASY, Category.WARZYWA);
        insertQuestion(q42);

        Question q43 = new Question("BEET",
                "Kukurydza", "Seler", "Burak", 3,
                Question.DIFFICULTY_MEDIUM, Category.WARZYWA);
        insertQuestion(q43);

        Question q44 = new Question("PUMPKIN",
                "Dynia", "Grzyb", "Groch", 1,
                Question.DIFFICULTY_MEDIUM, Category.WARZYWA);
        insertQuestion(q44);

        Question q45 = new Question("LEEK",
                "Rzepa", "Por", "Grzyb", 2,
                Question.DIFFICULTY_MEDIUM, Category.WARZYWA);
        insertQuestion(q45);

        Question q46 = new Question("PEA",
                "Rzodkiewka", "Ogórek", "Groch", 3,
                Question.DIFFICULTY_MEDIUM, Category.WARZYWA);
        insertQuestion(q46);

        Question q47 = new Question("CORN",
                "Kukurydza", "Kalafior", "Rzepa", 1,
                Question.DIFFICULTY_EASY, Category.WARZYWA);
        insertQuestion(q47);

        Question q48 = new Question("GARLIC",
                "Rzodkiewka", "Czosnek", "Pomidor", 2,
                Question.DIFFICULTY_EASY, Category.WARZYWA);
        insertQuestion(q48);

        Question q49 = new Question("TURNIP",
                "Grzyb", "Burak", "Rzepa", 3,
                Question.DIFFICULTY_MEDIUM, Category.WARZYWA);
        insertQuestion(q49);

        Question q50 = new Question("RADISH",
                "Rzodkiewka", "Fasola", "Kukurydza", 1,
                Question.DIFFICULTY_EASY, Category.WARZYWA);
        insertQuestion(q50);

        Question q51 = new Question("ASPARAGUS",
                "Marchewka", "Szparagi", "Bakłażan", 2,
                Question.DIFFICULTY_MEDIUM, Category.WARZYWA);
        insertQuestion(q51);

        Question q52 = new Question("BRUSSELS SPROUT",
                "Brokuł", "Burak", "Brukselka", 3,
                Question.DIFFICULTY_MEDIUM, Category.WARZYWA);
        insertQuestion(q52);

        Question q53 = new Question("CHIVES",
                "Szczypiorek", "Czosnek", "Cukinia", 1,
                Question.DIFFICULTY_MEDIUM, Category.WARZYWA);
        insertQuestion(q53);

        Question q54 = new Question("CELERY",
                "Brokuł", "Seler", "Sałata", 2,
                Question.DIFFICULTY_MEDIUM, Category.WARZYWA);
        insertQuestion(q54);

        Question q55 = new Question("OLIVES",
                "Pietruszka", "Brokuł", "Oliwki", 3,
                Question.DIFFICULTY_MEDIUM, Category.WARZYWA);
        insertQuestion(q55);

        Question q56 = new Question("PEPPER",
                "Papryka", "Bakłażan", "Czosnek", 1,
                Question.DIFFICULTY_EASY, Category.WARZYWA);
        insertQuestion(q56);

        Question q57 = new Question("SOYA / SOY",
                "Bób", "Soja", "Cebula dymka", 2,
                Question.DIFFICULTY_HARD, Category.WARZYWA);
        insertQuestion(q57);

        Question q58 = new Question("BROAD BEAN",
                "Fasolka", "Soja", "Bób", 3,
                Question.DIFFICULTY_HARD, Category.WARZYWA);
        insertQuestion(q58);

        Question q59 = new Question("ARTICHOKE",
                "Karczoch", "Brukselka", "Cebula", 1,
                Question.DIFFICULTY_HARD, Category.WARZYWA);
        insertQuestion(q59);

        Question q60 = new Question("CHICORY",
                "A", "Cykoria", "C", 2,
                Question.DIFFICULTY_HARD, Category.WARZYWA);
        insertQuestion(q60);

        Question q61 = new Question("CRESS",
                "Szczypiorek", "Bób", "Rzeżucha", 3,
                Question.DIFFICULTY_HARD, Category.WARZYWA);
        insertQuestion(q61);

        Question q62 = new Question("DILL",
                "Koper Ogrodowy", "Fenkuł", "Chrzan", 1,
                Question.DIFFICULTY_HARD, Category.WARZYWA);
        insertQuestion(q62);

        Question q63 = new Question("FENNEL",
                "Koper ogrodowy", "Fenkuł", "Chrzan", 2,
                Question.DIFFICULTY_HARD, Category.WARZYWA);
        insertQuestion(q63);

        Question q64 = new Question("HORSERADISH",
                "Rzeżucha", "Bób", "Chrzan", 3,
                Question.DIFFICULTY_HARD, Category.WARZYWA);
        insertQuestion(q64);

        Question q65 = new Question("KALE",
                "Jarmuż", "Soczewica", "Czosnek", 1,
                Question.DIFFICULTY_HARD, Category.WARZYWA);
        insertQuestion(q65);

        Question q66 = new Question("LENTIL",
                "Rabarbar", "Soczewica", "Brukiew", 2,
                Question.DIFFICULTY_HARD, Category.WARZYWA);
        insertQuestion(q66);

        Question q67 = new Question("MARROW",
                "Pasternak", "Marchewka", "Kabaczek", 3,
                Question.DIFFICULTY_HARD, Category.WARZYWA);
        insertQuestion(q67);

        Question q68 = new Question("RHUBARB",
                "Rabarbar", "Brukiew", "Cytryna", 1,
                Question.DIFFICULTY_HARD, Category.WARZYWA);
        insertQuestion(q68);

        Question q69 = new Question("PARNIP",
                "Rzepa", "Paternak", "Cebula", 2,
                Question.DIFFICULTY_HARD, Category.WARZYWA);
        insertQuestion(q69);

        Question q70 = new Question("TURNIP",
                "Brukiew", "Batat", "Rzepa", 3,
                Question.DIFFICULTY_HARD, Category.WARZYWA);
        insertQuestion(q70);

        Question q71 = new Question("SWEET POTATO",
                "Batat", "Rzepa", "Rabarbar", 1,
                Question.DIFFICULTY_MEDIUM, Category.WARZYWA);
        insertQuestion(q71);

        Question q72 = new Question("APPLE",
                "Pomarańcza", "Jabłko", "Gruszka", 2,
                Question.DIFFICULTY_EASY, Category.OWOCE);
        insertQuestion(q72);

        Question q73 = new Question("Cherry",
                "Arbuz", "Kokos", "Wiśnia", 3,
                Question.DIFFICULTY_EASY, Category.OWOCE);
        insertQuestion(q73);

        Question q74 = new Question("PEAR",
                "Gruszka", "Ananas", "Winogrona", 1,
                Question.DIFFICULTY_EASY, Category.OWOCE);
        insertQuestion(q74);

        Question q75 = new Question("PINEAPPLE",
                "Arbuz", "Ananas", "Śliwka", 2,
                Question.DIFFICULTY_EASY, Category.OWOCE);
        insertQuestion(q75);

        Question q76 = new Question("STRAWBERRY",
                "Wiśnia", "Brzoskwinia", "Truskawka", 3,
                Question.DIFFICULTY_EASY, Category.OWOCE);
        insertQuestion(q76);

        Question q77 = new Question("GRAPES",
                "Winogrona", "Truskawka", "Cytryna", 1,
                Question.DIFFICULTY_MEDIUM, Category.OWOCE);
        insertQuestion(q77);

        Question q78 = new Question("ORANGE",
                "Jagoda", "Pomarańcza", "Poziomka", 2,
                Question.DIFFICULTY_EASY, Category.OWOCE);
        insertQuestion(q78);

        Question q79 = new Question("COCONUT",
                "Gruszka", "Arbuz", "Kokos", 3,
                Question.DIFFICULTY_EASY, Category.OWOCE);
        insertQuestion(q79);

        Question q80 = new Question("WATERMELON",
                "Arbuz", "Śliwka", "Porzeczka", 1,
                Question.DIFFICULTY_EASY, Category.OWOCE);
        insertQuestion(q80);

        Question q81 = new Question("ZEBRA",
                "Klacz", "Źrebak", "Zebra", 3,
                Question.DIFFICULTY_EASY, Category.ZWIERZETA);
        insertQuestion(q81);

        Question q82 = new Question("CROCODILE",
                "Krokodyl", "Aligator", "Dinozaur", 1,
                Question.DIFFICULTY_EASY, Category.ZWIERZETA);
        insertQuestion(q82);

        Question q83 = new Question("GOAT",
                "Koza", "Baran", "Kozioł", 1,
                Question.DIFFICULTY_MEDIUM, Category.WARZYWA);
        insertQuestion(q83);

        Question q84 = new Question("BUG",
                "Koń", "Robak", "Biedronka", 2,
                Question.DIFFICULTY_EASY, Category.ZWIERZETA);
        insertQuestion(q84);

        Question q85 = new Question("GIRAFFE",
                "Antylopa", "Gazela", "Żyrafa", 3,
                Question.DIFFICULTY_EASY, Category.ZWIERZETA);
        insertQuestion(q85);

        Question q86 = new Question("DOG",
                "Pies", "Robak", "Kot", 1,
                Question.DIFFICULTY_EASY, Category.ZWIERZETA);
        insertQuestion(q86);

        Question q87 = new Question("HORSE",
                "Koza", "Koń", "Źrebak", 2,
                Question.DIFFICULTY_EASY, Category.ZWIERZETA);
        insertQuestion(q87);

        Question q88 = new Question("ELEPHANT",
                "Mamut", "Tapir", "Słoń", 3,
                Question.DIFFICULTY_EASY, Category.ZWIERZETA);
        insertQuestion(q88);

        Question q89 = new Question("SNAKE",
                "Wąż", "Żmija", "Jaszczurka", 1,
                Question.DIFFICULTY_EASY, Category.ZWIERZETA);
        insertQuestion(q89);

        Question q90 = new Question("CAT",
                "Koń", "Kot", "Pies", 2,
                Question.DIFFICULTY_EASY, Category.ZWIERZETA);
        insertQuestion(q90);

        Question q91 = new Question("SHARK",
                "Wieloryb", "Orka", "Rekin", 3,
                Question.DIFFICULTY_EASY, Category.ZWIERZETA);
        insertQuestion(q91);

        Question q92 = new Question("BIRD",
                "Ptak", "Ssak", "Gad", 1,
                Question.DIFFICULTY_EASY, Category.ZWIERZETA);
        insertQuestion(q92);

        Question q93 = new Question("FROG",
                "Ropucha", "Żaba", "Żmija", 2,
                Question.DIFFICULTY_EASY, Category.ZWIERZETA);
        insertQuestion(q93);

        Question q94 = new Question("BEAR",
                "Koza", "Koala", "Niedźwiedź", 3,
                Question.DIFFICULTY_EASY, Category.ZWIERZETA);
        insertQuestion(q94);

        Question q95 = new Question("BUFFALO",
                "Bawół", "Byk", "Żubr", 1,
                Question.DIFFICULTY_EASY, Category.ZWIERZETA);
        insertQuestion(q95);

        Question q96 = new Question("BUTTERFLY",
                "Ważka", "Motyl", "Latające masło", 2,
                Question.DIFFICULTY_EASY, Category.ZWIERZETA);
        insertQuestion(q96);

        Question q97 = new Question("CAMEL",
                "Owca", "Kozioł", "Wielbłąd", 3,
                Question.DIFFICULTY_EASY, Category.ZWIERZETA);
        insertQuestion(q97);

        Question q98 = new Question("CHEETAH",
                "Gepard", "Pantera", "Jaguar", 1,
                Question.DIFFICULTY_EASY, Category.ZWIERZETA);
        insertQuestion(q98);

        Question q99 = new Question("COW",
                "Świnia", "Krowa", "Byk", 2,
                Question.DIFFICULTY_EASY, Category.ZWIERZETA);
        insertQuestion(q99);

        Question q100 = new Question("DOLPHIN",
                "Orka", "Dromader", "Delfin", 3,
                Question.DIFFICULTY_EASY, Category.ZWIERZETA);
        insertQuestion(q100);

        Question q101 = new Question("DUCK",
                "Kaczka", "Gęś", "Kura", 1,
                Question.DIFFICULTY_EASY, Category.ZWIERZETA);
        insertQuestion(q101);

        Question q102 = new Question("FISH",
                "Meduza", "Ryba", "Rekin", 2,
                Question.DIFFICULTY_EASY, Category.ZWIERZETA);
        insertQuestion(q102);

        Question q103 = new Question("FOX",
                "Kojot", "Wilk", "Lis", 3,
                Question.DIFFICULTY_EASY, Category.ZWIERZETA);
        insertQuestion(q103);

        Question q104 = new Question("GORILLA",
                "Goryl", "Szympans", "Orangutan", 1,
                Question.DIFFICULTY_EASY, Category.ZWIERZETA);
        insertQuestion(q104);

        Question q105 = new Question("HEN",
                "Kogut", "Kura", "Kaczka", 2,
                Question.DIFFICULTY_EASY, Category.ZWIERZETA);
        insertQuestion(q105);

        Question q106 = new Question("HIPPO",
                "Prosię", "Słoń morski", "Hipopotam", 3,
                Question.DIFFICULTY_EASY, Category.ZWIERZETA);
        insertQuestion(q106);

        Question q107 = new Question("IGUANA",
                "Iguana", "Waran", "Jaszczurka", 1,
                Question.DIFFICULTY_EASY, Category.ZWIERZETA);
        insertQuestion(q107);

        Question q108 = new Question("KOALA",
                "Dziobak", "Koala", "Kangur", 2,
                Question.DIFFICULTY_EASY, Category.ZWIERZETA);
        insertQuestion(q108);

        Question q109 = new Question("LION",
                "Pantera", "Tygrys", "Lew", 3,
                Question.DIFFICULTY_EASY, Category.ZWIERZETA);
        insertQuestion(q109);

        Question q110 = new Question("LIZARD",
                "Jaszczurka", "Iguana", "Smok", 1,
                Question.DIFFICULTY_EASY, Category.ZWIERZETA);
        insertQuestion(q110);

        Question q111 = new Question("MONKEY",
                "Lemur", "Małpa", "Goryl", 2,
                Question.DIFFICULTY_EASY, Category.ZWIERZETA);
        insertQuestion(q111);

        Question q112 = new Question("MARMOT",
                "Bóbr", "Surykatka", "Świstak", 3,
                Question.DIFFICULTY_MEDIUM, Category.ZWIERZETA);
        insertQuestion(q112);

        Question q113 = new Question("BABOON",
                "Pawian", "Szympans", "Lemur", 1,
                Question.DIFFICULTY_MEDIUM, Category.ZWIERZETA);
        insertQuestion(q113);

        Question q114 = new Question("FLEA",
                "Mucha", "Pchła", "Kleszcz", 2,
                Question.DIFFICULTY_MEDIUM, Category.ZWIERZETA);
        insertQuestion(q114);

        Question q115 = new Question("MOOSE",
                "Sarna", "Jeleń", "Łoś", 3,
                Question.DIFFICULTY_MEDIUM, Category.ZWIERZETA);
        insertQuestion(q115);

        Question q116 = new Question("WHALE",
                "Wieloryb", "Delfin", "Krowa morska", 1,
                Question.DIFFICULTY_MEDIUM, Category.ZWIERZETA);
        insertQuestion(q116);

        Question q117 = new Question("HAMSTER",
                "Świnka morska", "Chomik", "Mysz", 2,
                Question.DIFFICULTY_MEDIUM, Category.ZWIERZETA);
        insertQuestion(q117);

        Question q118 = new Question("HOG",
                "Świnia", "Dzik", "Wieprz", 3,
                Question.DIFFICULTY_MEDIUM, Category.ZWIERZETA);
        insertQuestion(q118);

        Question q119 = new Question("PENGUIN",
                "Pingwin", "Dziobak", "Kaczka", 1,
                Question.DIFFICULTY_MEDIUM, Category.ZWIERZETA);
        insertQuestion(q119);

        Question q120 = new Question("VIPER",
                "Jaszczurka", "Żmija", "Wąż", 2,
                Question.DIFFICULTY_MEDIUM, Category.ZWIERZETA);
        insertQuestion(q120);

        Question q121 = new Question("TURTLE",
                "Żmija", "Krokodyl", "Żółw", 3,
                Question.DIFFICULTY_MEDIUM, Category.ZWIERZETA);
        insertQuestion(q121);

        Question q122 = new Question("BISON",
                "Bizon", "Bawół", "Żubr", 1,
                Question.DIFFICULTY_MEDIUM, Category.ZWIERZETA);
        insertQuestion(q122);

        Question q123 = new Question("BEETLE",
                "Gąsienica", "Żuk", "C", 2,
                Question.DIFFICULTY_MEDIUM, Category.ZWIERZETA);
        insertQuestion(q123);

        Question q124 = new Question("CRANE",
                "Kruk", "Paw", "Żuraw", 3,
                Question.DIFFICULTY_MEDIUM, Category.ZWIERZETA);
        insertQuestion(q124);

        Question q125 = new Question("GRUB",
                "Pędrak", "Larwa", "Dżdżownica", 1,
                Question.DIFFICULTY_MEDIUM, Category.ZWIERZETA);
        insertQuestion(q125);

        Question q126 = new Question("BEAVER",
                "Wiewiórka", "Bóbr", "Borsuk", 2,
                Question.DIFFICULTY_MEDIUM, Category.ZWIERZETA);
        insertQuestion(q126);

        Question q127 = new Question("BULL",
                "Bizon", "Bawół", "Byk", 3,
                Question.DIFFICULTY_MEDIUM, Category.ZWIERZETA);
        insertQuestion(q127);

        Question q128 = new Question("EAGLE",
                "Orzeł", "Sęp", "Żuraw", 1,
                Question.DIFFICULTY_MEDIUM, Category.ZWIERZETA);
        insertQuestion(q128);

        Question q129 = new Question("SALMON",
                "Sum", "Łosoś", "Szczupak", 2,
                Question.DIFFICULTY_MEDIUM, Category.ZWIERZETA);
        insertQuestion(q129);

        Question q130 = new Question("MOLE",
                "Borsuk", "Szczur", "Kret", 3,
                Question.DIFFICULTY_MEDIUM, Category.ZWIERZETA);
        insertQuestion(q130);

        Question q131 = new Question("ALLIGATOR",
                "Aligator", "Krokodyl", "Dinozaur", 1,
                Question.DIFFICULTY_MEDIUM, Category.ZWIERZETA);
        insertQuestion(q131);

        Question q132 = new Question(".BADGER",
                "Łasica", "Borsuk", "Fretka", 2,
                Question.DIFFICULTY_MEDIUM, Category.ZWIERZETA);
        insertQuestion(q132);

        Question q133 = new Question("TOAD",
                "Kijanka", "Żaba", "Ropucha", 3,
                Question.DIFFICULTY_HARD, Category.ZWIERZETA);
        insertQuestion(q133);

        Question q134 = new Question("MANATEE",
                "Krowa morska", "Foka", "Wieloryb", 1,
                Question.DIFFICULTY_HARD, Category.ZWIERZETA);
        insertQuestion(q134);

        Question q135 = new Question("POLAR BEAR",
                "Miś koala", "Niedźwiedź polarny", "Niedźwiedź Grizzly", 2,
                Question.DIFFICULTY_HARD, Category.ZWIERZETA);
        insertQuestion(q135);

        Question q136 = new Question("DRAGONFLY",
                "Smok", "Motyl", "Ważka", 3,
                Question.DIFFICULTY_HARD, Category.ZWIERZETA);
        insertQuestion(q136);

        Question q137 = new Question("EARTHWORM",
                "Dżdżownica", "Pędrak", "Stonoga", 1,
                Question.DIFFICULTY_HARD, Category.ZWIERZETA);
        insertQuestion(q137);

        Question q138 = new Question("OCTOPUS",
                "Meduza", "Ośmiornica", "Kałamarnica", 2,
                Question.DIFFICULTY_HARD, Category.ZWIERZETA);
        insertQuestion(q138);

        Question q139 = new Question("HARE",
                "Chomik", "Królik", "Zając", 3,
                Question.DIFFICULTY_HARD, Category.ZWIERZETA);
        insertQuestion(q139);

        Question q140 = new Question("WILDCAT",
                "Żbik", "Ryś", "Tygrys", 1,
                Question.DIFFICULTY_HARD, Category.ZWIERZETA);
        insertQuestion(q140);

        Question q141 = new Question("LYNX",
                "Jaguar", "Ryś", "Pantera", 2,
                Question.DIFFICULTY_HARD, Category.ZWIERZETA);
        insertQuestion(q141);

        Question q142 = new Question("SQUIRREL",
                "Szynszyl", "Królik", "Wiewiórka", 3,
                Question.DIFFICULTY_HARD, Category.ZWIERZETA);
        insertQuestion(q142);

        Question q143 = new Question("JACKAL",
                "Szakal", "Wilk", "Owczarek", 1,
                Question.DIFFICULTY_HARD, Category.ZWIERZETA);
        insertQuestion(q143);

        Question q144 = new Question("TREEFROG",
                "Żaba", "Rzekotka drzewna", "Ropucha", 2,
                Question.DIFFICULTY_HARD, Category.ZWIERZETA);
        insertQuestion(q144);

        Question q145 = new Question("SQUID",
                "Meduza", "Ślimak", "Kałamarnica", 3,
                Question.DIFFICULTY_HARD, Category.ZWIERZETA);
        insertQuestion(q145);

        Question q146 = new Question("BADGER",
                "Borsuk", "Wydra", "Łasica", 1,
                Question.DIFFICULTY_HARD, Category.ZWIERZETA);
        insertQuestion(q146);

        Question q147 = new Question("WEASEL",
                "Skunks", "Łasica", "Opos", 2,
                Question.DIFFICULTY_HARD, Category.ZWIERZETA);
        insertQuestion(q147);

        Question q148 = new Question("OTTER",
                "Świnka morska", "Fretka", "Wydra", 3,
                Question.DIFFICULTY_HARD, Category.ZWIERZETA);
        insertQuestion(q148);

        Question q149 = new Question("PLANE",
                "Samolot", "Samochód", "Łódź", 1,
                Question.DIFFICULTY_EASY, Category.TRANSPORT);
        insertQuestion(q149);

        Question q150 = new Question("BIKE",
                "Wrotki", "Rower", "Deskorolka", 2,
                Question.DIFFICULTY_EASY, Category.TRANSPORT);
        insertQuestion(q150);

        Question q151 = new Question("BOAT",
                "Samolot", "Żaglówka", "Łódź", 3,
                Question.DIFFICULTY_EASY, Category.TRANSPORT);
        insertQuestion(q151);

        Question q152 = new Question("BUS",
                "Autobus", "Samochód", "Samochód dostawczy", 1,
                Question.DIFFICULTY_EASY, Category.TRANSPORT);
        insertQuestion(q152);

        Question q153 = new Question("CAR",
                "Wrotki", "Samochód", "Autobus", 2,
                Question.DIFFICULTY_EASY, Category.TRANSPORT);
        insertQuestion(q153);

        Question q154 = new Question("TRUCK",
                "Autobus", "Samochód dostawczy", "Ciężarówka", 3,
                Question.DIFFICULTY_EASY, Category.TRANSPORT);
        insertQuestion(q154);

        Question q155 = new Question("MOTORCYCLE",
                "Motocykl", "Rower", "Hulajnoga", 1,
                Question.DIFFICULTY_EASY, Category.TRANSPORT);
        insertQuestion(q155);

        Question q156 = new Question("TAXI",
                "Samochód", "Taksówka", "Ciężarówka", 2,
                Question.DIFFICULTY_EASY, Category.TRANSPORT);
        insertQuestion(q156);

        Question q157 = new Question("TRAIN",
                "Autobus", "Metro", "Pociąg", 3,
                Question.DIFFICULTY_EASY, Category.TRANSPORT);
        insertQuestion(q157);

        Question q158 = new Question("SUBWAY",
                "Metro", "Skuter", "Pociąg", 1,
                Question.DIFFICULTY_EASY, Category.TRANSPORT);
        insertQuestion(q158);

        Question q159 = new Question("SCOOTER",
                "Wrotki", "Skuter", "Motocykl", 2,
                Question.DIFFICULTY_EASY, Category.TRANSPORT);
        insertQuestion(q159);

        Question q160 = new Question("YACHT",
                "Rower wodny", "Statek", "Żaglówka", 3,
                Question.DIFFICULTY_EASY, Category.TRANSPORT);
        insertQuestion(q160);

        Question q161 = new Question("TRAM",
                "Tramwaj", "Lokomotywa", "Pociąg", 1,
                Question.DIFFICULTY_MEDIUM, Category.TRANSPORT);
        insertQuestion(q161);

        Question q162 = new Question("FAST TRAIN",
                "Ciężarówka", "Pociąg pospieszny", "Tramwaj", 2,
                Question.DIFFICULTY_MEDIUM, Category.TRANSPORT);
        insertQuestion(q162);

        Question q163 = new Question("PASSENGER TRAIN",
                "Metro", "Pociąg pospieszny", "Pociąg osobowy", 3,
                Question.DIFFICULTY_MEDIUM, Category.TRANSPORT);
        insertQuestion(q163);

        Question q164 = new Question("PICK-UP",
                "Furgonetka", "Ciężarówka", "Samochód dostawczy", 1,
                Question.DIFFICULTY_MEDIUM, Category.TRANSPORT);
        insertQuestion(q164);

        Question q165 = new Question("DELIVERY VAN",
                "Samochód ciężarowy", "Samochód dostawczy", "Samochód osobowy", 2,
                Question.DIFFICULTY_MEDIUM, Category.TRANSPORT);
        insertQuestion(q165);

        Question q166 = new Question("MOTORBOAT",
                "Łódź", "Skuter wodny", "Motorówka", 3,
                Question.DIFFICULTY_MEDIUM, Category.TRANSPORT);
        insertQuestion(q166);

        Question q167 = new Question("SUBMARINE",
                "Łódź podwodna", "Łódź", "Motorówka", 1,
                Question.DIFFICULTY_MEDIUM, Category.TRANSPORT);
        insertQuestion(q167);

        Question q168 = new Question("SHIP",
                "Motorówka", "Statek", "Łódź podwodna", 2,
                Question.DIFFICULTY_MEDIUM, Category.TRANSPORT);
        insertQuestion(q168);

        Question q169 = new Question("JET PLANE",
                "Samolot", "Statek kosmiczny", "Odrzutowiec", 3,
                Question.DIFFICULTY_MEDIUM, Category.TRANSPORT);
        insertQuestion(q169);

        Question q170 = new Question("VEHICLE",
                "Pojazd", "Statek", "Samochód", 1,
                Question.DIFFICULTY_MEDIUM, Category.TRANSPORT);
        insertQuestion(q170);

        Question q171 = new Question("SPACESHIP",
                "Łódź podwodna", "Statek kosmiczny", "Statek", 2,
                Question.DIFFICULTY_MEDIUM, Category.TRANSPORT);
        insertQuestion(q171);

        Question q172 = new Question("AMBULANCE",
                "Taksówka", "Samochód policyjny", "Karetka pogotowia", 3,
                Question.DIFFICULTY_MEDIUM, Category.TRANSPORT);
        insertQuestion(q172);

        Question q173 = new Question("LIFEBOAT",
                "Łódź ratunkowa", "Łódź podwodna", "Statek kosmiczny", 1,
                Question.DIFFICULTY_MEDIUM, Category.TRANSPORT);
        insertQuestion(q173);

        Question q174 = new Question("POLICE CAR",
                "Samochód dostawczy", "Radiowóz policyjny", "Samochód ciężarowy", 2,
                Question.DIFFICULTY_MEDIUM, Category.TRANSPORT);
        insertQuestion(q174);

        Question q175 = new Question("LOCOMOTIVE",
                "Pociąg", "Środek transportu", "Lokomotywa", 3,
                Question.DIFFICULTY_MEDIUM, Category.TRANSPORT);
        insertQuestion(q175);

        Question q176 = new Question("TRAILER",
                "Przyczepa", "Ciężarówka", "Samochód dostawczy", 1,
                Question.DIFFICULTY_MEDIUM, Category.TRANSPORT);
        insertQuestion(q176);

        Question q177 = new Question("ELECTRIC CAR",
                "Samochód spalinowy", "Samochód elektryczny", "Tramwaj", 2,
                Question.DIFFICULTY_MEDIUM, Category.TRANSPORT);
        insertQuestion(q177);

        Question q178 = new Question("COMBUSTION VEHICLE",
                "Samochód elektryczny", "Lokomotywa", "Pojazd spalinowy", 3,
                Question.DIFFICULTY_MEDIUM, Category.TRANSPORT);
        insertQuestion(q178);

        Question q179 = new Question("TANK",
                "Tankowiec", "Czołg", "Cysterna", 1,
                Question.DIFFICULTY_MEDIUM, Category.TRANSPORT);
        insertQuestion(q179);

        Question q180 = new Question("TANK TRUCK",
                "Czołg", "Cysterna", "Ciężarówka", 2,
                Question.DIFFICULTY_MEDIUM, Category.TRANSPORT);
        insertQuestion(q180);

        Question q181 = new Question("AIRSHIP",
                "Odrzutowiec", "Samolot", "Sterowiec", 3,
                Question.DIFFICULTY_MEDIUM, Category.TRANSPORT);
        insertQuestion(q181);

        Question q182 = new Question("ROCKET",
                "Rakieta", "Wahadłowiec kosmiczny", "Statek kosmiczny", 1,
                Question.DIFFICULTY_MEDIUM, Category.TRANSPORT);
        insertQuestion(q182);

        Question q183 = new Question("TRACTOR",
                "Ciężarówka", "Traktor", "Pociąg", 2,
                Question.DIFFICULTY_MEDIUM, Category.TRANSPORT);
        insertQuestion(q183);

        Question q184 = new Question("COMBINE-HARVESTER",
                "Statek kosmiczny", "Traktor", "Kombajn", 3,
                Question.DIFFICULTY_MEDIUM, Category.TRANSPORT);
        insertQuestion(q184);

        Question q185 = new Question("MERCHANT VESSEL",
                "Statek handlowy", "Statek pasażerski", "Lotniskowiec", 1,
                Question.DIFFICULTY_HARD, Category.TRANSPORT);
        insertQuestion(q185);

        Question q186 = new Question("CARGO SHIP",
                "Statek handlowy", "Statek towarowy", "Łódź", 2,
                Question.DIFFICULTY_HARD, Category.TRANSPORT);
        insertQuestion(q186);

        Question q187 = new Question("CONTAINER VESSEL",
                "Statek handlowy", "Lotniskowiec", "Kontenerowiec", 3,
                Question.DIFFICULTY_HARD, Category.TRANSPORT);
        insertQuestion(q187);

        Question q188 = new Question("OIL TANKER",
                "Tankowiec", "Statek pasażerski", "Kontenerowiec", 1,
                Question.DIFFICULTY_HARD, Category.TRANSPORT);
        insertQuestion(q188);

        Question q189 = new Question("BARGE",
                "Łódź", "Barka", "Statek handlowy", 2,
                Question.DIFFICULTY_HARD, Category.TRANSPORT);
        insertQuestion(q189);

        Question q190 = new Question("PASSENGER SHIP",
                "Barka", "Statek towarowy", "Statek pasażerski", 3,
                Question.DIFFICULTY_HARD, Category.TRANSPORT);
        insertQuestion(q190);

        Question q191 = new Question("ICEBRAKER",
                "Lodołamacz", "Barka", "Prom", 1,
                Question.DIFFICULTY_HARD, Category.TRANSPORT);
        insertQuestion(q191);

        Question q192 = new Question("CRUISE SHIP",
                "Lotniskowiec", "Statek wycieczkowy", "Statek pasażerski", 2,
                Question.DIFFICULTY_HARD, Category.TRANSPORT);
        insertQuestion(q192);

        Question q193 = new Question("FERRYBOAT",
                "Lodołamacz", "Statek pasażerski", "Prom", 3,
                Question.DIFFICULTY_HARD, Category.TRANSPORT);
        insertQuestion(q193);

        Question q194 = new Question("BIPLANE",
                "Dwupłatowiec", "Odrzutowiec", "Szybowiec", 1,
                Question.DIFFICULTY_HARD, Category.TRANSPORT);
        insertQuestion(q194);

        Question q195 = new Question("GLIDER",
                "Dwupłatowiec", "Szybowiec", "Samolot transportowy", 2,
                Question.DIFFICULTY_HARD, Category.TRANSPORT);
        insertQuestion(q195);

        Question q196 = new Question("HELICOPTER",
                "Sterowiec", "Odrzutowiec", "HELICOPTER", 3,
                Question.DIFFICULTY_HARD, Category.TRANSPORT);
        insertQuestion(q196);

        Question q197 = new Question("CABRIOLET",
                "Kabriolet", "Miniwan", "Pick-up", 1,
                Question.DIFFICULTY_HARD, Category.TRANSPORT);
        insertQuestion(q197);

        Question q198 = new Question("LIMOUSINE",
                "Miniwan", "Limuzyna", "Kabriolet", 2,
                Question.DIFFICULTY_HARD, Category.TRANSPORT);
        insertQuestion(q198);

        Question q199 = new Question("RACE CAR",
                "Limuzyna", "Łódź wyścigowa", "Samochód wyścigowy", 3,
                Question.DIFFICULTY_HARD, Category.TRANSPORT);
        insertQuestion(q199);

        Question q200 = new Question("JET SKI",
                "Skuter wodny", "Samolot odrzutowy", "C", 1,
                Question.DIFFICULTY_HARD, Category.TRANSPORT);
        insertQuestion(q200);

        Question q201 = new Question("SNOWCAT",
                "Skuter śnieżny", "Ratrak", "Lodołamacz", 2,
                Question.DIFFICULTY_HARD, Category.TRANSPORT);
        insertQuestion(q201);

        Question q202 = new Question("GARBAGE TRUCK",
                "Wóz strażacki", "Ciężarówka", "Śmieciarka", 3,
                Question.DIFFICULTY_HARD, Category.TRANSPORT);
        insertQuestion(q202);

        Question q203 = new Question("FORKLIFT",
                "Wózek widłowy", "Spychacz", "Ratrak", 1,
                Question.DIFFICULTY_HARD, Category.TRANSPORT);
        insertQuestion(q203);

        Question q204 = new Question("FIRE TRUCK",
                "Odśnieżarka", "Wóz strażacki", "Śmieciarka", 2,
                Question.DIFFICULTY_HARD, Category.TRANSPORT);
        insertQuestion(q204);

        Question q205 = new Question("SNOWBLOWER",
                "Skuter wodny", "Ratrak", "Odśnieżarka", 3,
                Question.DIFFICULTY_HARD, Category.TRANSPORT);
        insertQuestion(q205);

        Question q206 = new Question("SKATEBOARD",
                "Deskorolka", "Przyczepa", "Rolki", 1,
                Question.DIFFICULTY_EASY, Category.TRANSPORT);
        insertQuestion(q206);

        Question q207 = new Question("ROLLERS",
                "Rower", "Rolki", "Hulajnoga", 2,
                Question.DIFFICULTY_EASY, Category.TRANSPORT);
        insertQuestion(q207);

        Question q208 = new Question("FRECKLES",
                "Policzki", "Brew", "Piegi", 3,
                Question.DIFFICULTY_EASY, Category.CZESCI_CIALA);
        insertQuestion(q208);

        Question q209 = new Question("EYE",
                "Oko", "Ucho", "Nos", 1,
                Question.DIFFICULTY_EASY, Category.CZESCI_CIALA);
        insertQuestion(q209);

        Question q210 = new Question("FACE",
                "Ręka", "Twarz", "Głowa", 2,
                Question.DIFFICULTY_EASY, Category.CZESCI_CIALA);
        insertQuestion(q210);

        Question q211 = new Question("NOSE",
                "Ramię", "Palec", "Nos", 3,
                Question.DIFFICULTY_EASY, Category.CZESCI_CIALA);
        insertQuestion(q211);

        Question q212 = new Question("TEETH",
                "Zęby", "Włosy", "Paznokcie", 1,
                Question.DIFFICULTY_EASY, Category.CZESCI_CIALA);
        insertQuestion(q212);

        Question q213 = new Question("ARM",
                "Dłoń", "Ramię", "Łokieć", 2,
                Question.DIFFICULTY_EASY, Category.CZESCI_CIALA);
        insertQuestion(q213);

        Question q214 = new Question("BELLY",
                "Noga", "Klatka piersiowa", "Brzuch", 3,
                Question.DIFFICULTY_EASY, Category.CZESCI_CIALA);
        insertQuestion(q214);

        Question q215 = new Question("LEG",
                "Noga", "Ręka", "Tułów", 1,
                Question.DIFFICULTY_EASY, Category.CZESCI_CIALA);
        insertQuestion(q215);

        Question q216 = new Question("FOOT",
                "Noga", "Stopa", "Ręka", 2,
                Question.DIFFICULTY_EASY, Category.CZESCI_CIALA);
        insertQuestion(q216);

        Question q217 = new Question("HAND",
                "Stopa", "Palec", "Dłoń", 3,
                Question.DIFFICULTY_EASY, Category.CZESCI_CIALA);
        insertQuestion(q217);

        Question q218 = new Question("MOUTH",
                "Usta", "Nos", "Twarz", 1,
                Question.DIFFICULTY_EASY, Category.CZESCI_CIALA);
        insertQuestion(q218);

        Question q219 = new Question("EAR",
                "Oko", "Ucho", "Usta", 2,
                Question.DIFFICULTY_EASY, Category.CZESCI_CIALA);
        insertQuestion(q219);

        Question q220 = new Question("FINGER",
                "Ramię", "Dłoń", "Palec", 3,
                Question.DIFFICULTY_EASY, Category.CZESCI_CIALA);
        insertQuestion(q220);

        Question q221 = new Question("BOTTOM",
                "Pośladki", "Plecy", "Brzuch", 1,
                Question.DIFFICULTY_EASY, Category.CZESCI_CIALA);
        insertQuestion(q221);

        Question q222 = new Question("NECK",
                "Tułów", "Szyja", "Plecy", 2,
                Question.DIFFICULTY_EASY, Category.CZESCI_CIALA);
        insertQuestion(q222);

        Question q223 = new Question("TONGUE",
                "Twarz", "Usta", "Język", 3,
                Question.DIFFICULTY_EASY, Category.CZESCI_CIALA);
        insertQuestion(q223);

        Question q224 = new Question("HEAD",
                "Czoło", "Głowa", "Twarz", 1,
                Question.DIFFICULTY_EASY, Category.CZESCI_CIALA);
        insertQuestion(q224);

        Question q225 = new Question("BACK",
                "Brzuch", "Plecy", "Pośladka", 2,
                Question.DIFFICULTY_EASY, Category.CZESCI_CIALA);
        insertQuestion(q225);

        Question q226 = new Question("SHOULDER",
                "Nogi", "Ramię", "Nadgarstek", 3,
                Question.DIFFICULTY_EASY, Category.CZESCI_CIALA);
        insertQuestion(q226);

        Question q227 = new Question("CHEST",
                "Brzuch", "Klatka piersiowa", "Czoło", 1,
                Question.DIFFICULTY_EASY, Category.CZESCI_CIALA);
        insertQuestion(q227);

        Question q228 = new Question("CHEEK",
                "Broda", "Policzek", "Czoło", 2,
                Question.DIFFICULTY_EASY, Category.CZESCI_CIALA);
        insertQuestion(q228);

        Question q229 = new Question("WRIST",
                "Łokieć", "Ręka", "Nadgarstek", 3,
                Question.DIFFICULTY_MEDIUM, Category.CZESCI_CIALA);
        insertQuestion(q229);

        Question q230 = new Question("ELBOW",
                "Łokieć", "Pięta", "Kolano", 1,
                Question.DIFFICULTY_MEDIUM, Category.CZESCI_CIALA);
        insertQuestion(q230);

        Question q231 = new Question("HEEL",
                "Palec", "Pięta", "Noga", 2,
                Question.DIFFICULTY_MEDIUM, Category.CZESCI_CIALA);
        insertQuestion(q231);

        Question q232 = new Question("TOE",
                "stopa", "Palec u ręki", "Palec u nogi", 3,
                Question.DIFFICULTY_MEDIUM, Category.CZESCI_CIALA);
        insertQuestion(q232);

        Question q233 = new Question("FOREARM",
                "Przedramię", "Ramię", "Ręka", 1,
                Question.DIFFICULTY_MEDIUM, Category.CZESCI_CIALA);
        insertQuestion(q233);

        Question q234 = new Question("BONE",
                "Klatka piersiowa", "Kość", "Płuca", 2,
                Question.DIFFICULTY_MEDIUM, Category.CZESCI_CIALA);
        insertQuestion(q234);

        Question q235 = new Question("NAIL",
                "Noaskórek", "Kręgosłup", "Paznokieć", 3,
                Question.DIFFICULTY_MEDIUM, Category.CZESCI_CIALA);
        insertQuestion(q235);

        Question q236 = new Question("SPINE",
                "Kręgosłup", "Żebra", "Płuca", 1,
                Question.DIFFICULTY_MEDIUM, Category.CZESCI_CIALA);
        insertQuestion(q236);

        Question q237 = new Question("PALM",
                "Ręka", "Dłoń", "Palec", 2,
                Question.DIFFICULTY_MEDIUM, Category.CZESCI_CIALA);
        insertQuestion(q237);

        Question q238 = new Question("KIDNEY",
                "Żołądek", "Serce", "Nerka", 3,
                Question.DIFFICULTY_MEDIUM, Category.CZESCI_CIALA);
        insertQuestion(q238);

        Question q239 = new Question("LIVER",
                "Wątroba", "Jelita", "Żołądek", 1,
                Question.DIFFICULTY_MEDIUM, Category.CZESCI_CIALA);
        insertQuestion(q239);

        Question q240 = new Question("STOMACH",
                "Płuca", "Żołądek", "Gardło", 2,
                Question.DIFFICULTY_MEDIUM, Category.CZESCI_CIALA);
        insertQuestion(q240);

        Question q241 = new Question("Heart",
                "Pęcherz", "Nerka", "Serce", 3,
                Question.DIFFICULTY_MEDIUM, Category.CZESCI_CIALA);
        insertQuestion(q241);

        Question q242 = new Question("HIP",
                "Biodro", "Noga", "Brzuch", 1,
                Question.DIFFICULTY_MEDIUM, Category.CZESCI_CIALA);
        insertQuestion(q242);

        Question q243 = new Question("CALF",
                "Pięta", "Łydka", "Palec", 2,
                Question.DIFFICULTY_MEDIUM, Category.CZESCI_CIALA);
        insertQuestion(q243);

        Question q244 = new Question("FOREHEAD",
                "Twarz", "Głowa", "Czoło", 3,
                Question.DIFFICULTY_MEDIUM, Category.CZESCI_CIALA);
        insertQuestion(q244);

        Question q245 = new Question("NOSTRIL",
                "Nozdrze", "Nos", "Policzek", 1,
                Question.DIFFICULTY_MEDIUM, Category.CZESCI_CIALA);
        insertQuestion(q245);

        Question q246 = new Question("VEIN",
                "Obojczyk", "Żyła", "Płuco", 2,
                Question.DIFFICULTY_MEDIUM, Category.CZESCI_CIALA);
        insertQuestion(q246);

        Question q247 = new Question("JAW",
                "Ząb", "Twarz", "Szczęka", 3,
                Question.DIFFICULTY_MEDIUM, Category.CZESCI_CIALA);
        insertQuestion(q247);

        Question q248 = new Question("FIST",
                "Pięść", "Dłoń", "Ręka", 1,
                Question.DIFFICULTY_MEDIUM, Category.CZESCI_CIALA);
        insertQuestion(q248);

        Question q249 = new Question("WRINKLES",
                "Czoło", "Zmarszczki", "Piegi", 2,
                Question.DIFFICULTY_MEDIUM, Category.CZESCI_CIALA);
        insertQuestion(q249);

        Question q250 = new Question("BIRTHMARK",
                "Piegi", "Zmarszczki", "Znamię", 3,
                Question.DIFFICULTY_MEDIUM, Category.CZESCI_CIALA);
        insertQuestion(q250);

        Question q251 = new Question("THUMB",
                "Kciuk", "Palec", "Dłoń", 1,
                Question.DIFFICULTY_HARD, Category.CZESCI_CIALA);
        insertQuestion(q251);

        Question q252 = new Question("INTESTINES",
                "Żołądek", "Jelita", "Nerka", 2,
                Question.DIFFICULTY_HARD, Category.CZESCI_CIALA);
        insertQuestion(q252);

        Question q253 = new Question("BLADDER",
                "Wyrostek", "Jelita", "Pęcherz", 3,
                Question.DIFFICULTY_HARD, Category.CZESCI_CIALA);
        insertQuestion(q253);

        Question q254 = new Question("APPENDIX",
                "Wyrostek", "Jelita", "Płuca", 1,
                Question.DIFFICULTY_HARD, Category.CZESCI_CIALA);
        insertQuestion(q254);

        Question q255 = new Question("SPLEEN",
                "Gardło", "Śledziona", "Nerka", 2,
                Question.DIFFICULTY_HARD, Category.CZESCI_CIALA);
        insertQuestion(q255);

        Question q256 = new Question("ARTERY",
                "Aorta", "Żebro", "Tętnica", 3,
                Question.DIFFICULTY_HARD, Category.CZESCI_CIALA);
        insertQuestion(q256);

        Question q257 = new Question("BRONCHUS",
                "Oskrzela", "Przełyk", "Dwunastnica", 1,
                Question.DIFFICULTY_HARD, Category.CZESCI_CIALA);
        insertQuestion(q257);

        Question q258 = new Question("CORONARY VESSELS",
                "Naczynia krwionośne", "Naczynia wieńcowe", "Pęcherzyk żółciowy", 2,
                Question.DIFFICULTY_HARD, Category.CZESCI_CIALA);
        insertQuestion(q258);

        Question q259 = new Question("DUODENUM",
                "Żołądek", "Trzynastnica", "Dwunastnica", 3,
                Question.DIFFICULTY_HARD, Category.CZESCI_CIALA);
        insertQuestion(q259);

        Question q260 = new Question("PALATE",
                "Podniebienie", "Gardło", "Przepona", 1,
                Question.DIFFICULTY_HARD, Category.CZESCI_CIALA);
        insertQuestion(q260);

        Question q261 = new Question("LARYNX",
                "Przełyk", "Krtań", "Dwunastnica", 2,
                Question.DIFFICULTY_HARD, Category.CZESCI_CIALA);
        insertQuestion(q261);

        Question q262 = new Question("PULSE",
                "Ciśnienie", "Biodro", "Tętno", 3,
                Question.DIFFICULTY_HARD, Category.CZESCI_CIALA);
        insertQuestion(q262);

        Question q263 = new Question("PANCREAS",
                "Trzustka", "Jelita", "Pęcherz", 1,
                Question.DIFFICULTY_HARD, Category.CZESCI_CIALA);
        insertQuestion(q263);

        Question q264 = new Question("SPINAL CORD",
                "Kręgosłup", "Rdzeń kręgowy", "Żebro", 2,
                Question.DIFFICULTY_HARD, Category.CZESCI_CIALA);
        insertQuestion(q264);

        Question q265 = new Question("TENDON",
                "Kręgosłup", "Tarczyca", "Ścięgno", 3,
                Question.DIFFICULTY_HARD, Category.CZESCI_CIALA);
        insertQuestion(q265);

        Question q266 = new Question("TRACHEA",
                "Tchawica", "Prostata", "Trzustka", 1,
                Question.DIFFICULTY_HARD, Category.CZESCI_CIALA);
        insertQuestion(q266);

        Question q267 = new Question("ADIPOSE TISSUE",
                "Tkanka mięśniowa", "Tkanka tłuszczowa", "Tkanka kostna", 2,
                Question.DIFFICULTY_HARD, Category.CZESCI_CIALA);
        insertQuestion(q267);

        Question q268 = new Question("ESOPHAGUS",
                "Migdałek", "Gardło", "Przełyk", 3,
                Question.DIFFICULTY_HARD, Category.CZESCI_CIALA);
        insertQuestion(q268);

        Question q269 = new Question("SALIVARY GLANDS",
                "Gruczoły ślinowe", "Gruczoły potowe", "Gruczoły łojowe", 1,
                Question.DIFFICULTY_HARD, Category.CZESCI_CIALA);
        insertQuestion(q269);

        Question q270 = new Question("ORAL CAVITY",
                "Podniebienie", "Jama ustna", "Przełyk", 2,
                Question.DIFFICULTY_HARD, Category.CZESCI_CIALA);
        insertQuestion(q270);

        Question q271 = new Question("SINUS",
                "Węzeł chłonny", "Nozdrza", "Zatoka", 3,
                Question.DIFFICULTY_HARD, Category.CZESCI_CIALA);
        insertQuestion(q271);

        Question q272 = new Question("TONSIL",
                "Migdałek", "Zatoka", "Trzemiączko", 1,
                Question.DIFFICULTY_HARD, Category.CZESCI_CIALA);
        insertQuestion(q272);

        Question q273 = new Question("SWEAT GLANDS",
                "Gruczoły łojowe", "Gruczoły potowe", "Gruczoły ślinowe", 2,
                Question.DIFFICULTY_HARD, Category.CZESCI_CIALA);
        insertQuestion(q273);

        Question q274 = new Question("SEBACEOUS GLANDS",
                "Gruczoły potowe", "Gruczoły ślinowe", "Gruczoły łojowe", 3,
                Question.DIFFICULTY_HARD, Category.CZESCI_CIALA);
        insertQuestion(q274);

        Question q275 = new Question("SCAB",
                "Strup", "Siniak", "Rana", 1,
                Question.DIFFICULTY_HARD, Category.CZESCI_CIALA);
        insertQuestion(q275);

        Question q276 = new Question("PEACH",
                "Kokos", "Brzoskwinia", "Wiśnia", 2,
                Question.DIFFICULTY_EASY, Category.OWOCE);
        insertQuestion(q276);

        Question q277 = new Question("CURRANT",
                "Ananas", "Banan", "Porzeczka", 3,
                Question.DIFFICULTY_EASY, Category.OWOCE);
        insertQuestion(q277);

        Question q278 = new Question("PLUM",
                "Śliwka", "Truskawka", "Jabłko", 1,
                Question.DIFFICULTY_EASY, Category.OWOCE);
        insertQuestion(q278);

        Question q279 = new Question("GOOSEBERRY",
                "Ananas", "Agrest", "Cytryna", 2,
                Question.DIFFICULTY_EASY, Category.OWOCE);
        insertQuestion(q279);

        Question q280 = new Question("BERRY",
                "A", "B", "Jagoda", 3,
                Question.DIFFICULTY_EASY, Category.OWOCE);
        insertQuestion(q280);

        Question q281 = new Question("BANANA",
                "Banan", "Malina", "Gruszka", 1,
                Question.DIFFICULTY_EASY, Category.OWOCE);
        insertQuestion(q281);

        Question q282 = new Question("WILD STRAWBERRY",
                "Ananas", "Poziomka", "Cytryna", 2,
                Question.DIFFICULTY_EASY, Category.OWOCE);
        insertQuestion(q282);

        Question q283 = new Question("RASPBERRY",
                "Porzeczka", "Orzech ziemny", "Malina", 3,
                Question.DIFFICULTY_EASY, Category.OWOCE);
        insertQuestion(q283);

        Question q284 = new Question("LEMON",
                "Cytryna", "Jagoda", "Kokos", 1,
                Question.DIFFICULTY_EASY, Category.OWOCE);
        insertQuestion(q284);

        Question q285 = new Question("PEANUT",
                "Ananas", "Orzech ziemny", "Cytryna", 2,
                Question.DIFFICULTY_EASY, Category.OWOCE);
        insertQuestion(q285);

        Question q286 = new Question("APRICOT",
                "Mango", "Morela", "Kokos", 2,
                Question.DIFFICULTY_MEDIUM, Category.OWOCE);
        insertQuestion(q286);

        Question q287 = new Question("BLACKBERRY",
                "Klementynka", "Pomarancza", "Borówka", 3,
                Question.DIFFICULTY_MEDIUM, Category.OWOCE);
        insertQuestion(q287);

        Question q288 = new Question("FIG",
                "Figa", "Banan", "Limonka", 1,
                Question.DIFFICULTY_MEDIUM, Category.OWOCE);
        insertQuestion(q288);

        Question q289 = new Question("DATE",
                "Randka", "Aktyl", "Mango", 2,
                Question.DIFFICULTY_MEDIUM, Category.OWOCE);
        insertQuestion(q289);

        Question q290 = new Question("MULBERRY",
                "Arbuz", "Słonecznik", "Morwa", 3,
                Question.DIFFICULTY_MEDIUM, Category.OWOCE);
        insertQuestion(q290);

        Question q291 = new Question("TAMARIND",
                "Tamaryndowiec", "Brzoskwinia", "Cytryna", 1,
                Question.DIFFICULTY_MEDIUM, Category.OWOCE);
        insertQuestion(q291);

        Question q292 = new Question("QUINCE",
                "Ananas", "Pigwa", "Arbuz", 2,
                Question.DIFFICULTY_MEDIUM, Category.OWOCE);
        insertQuestion(q292);

        Question q293 = new Question("REDCURRANT",
                "Arbuz", "Słonecznik", "Czerwona porzeczka", 3,
                Question.DIFFICULTY_MEDIUM, Category.OWOCE);
        insertQuestion(q293);

        Question q294 = new Question("SUNFLOWER",
                "Słonecznik", "Banan", "Gruszka", 1,
                Question.DIFFICULTY_MEDIUM, Category.OWOCE);
        insertQuestion(q294);

        Question q295 = new Question("BLACKCURRANT",
                "Ananas", "Czarna porzeczka", "Jeżyna", 2,
                Question.DIFFICULTY_MEDIUM, Category.OWOCE);
        insertQuestion(q295);

        Question q296 = new Question("HAZELNUT",
                "Arbuz", "Malina", "Orzech laskowy", 3,
                Question.DIFFICULTY_HARD, Category.OWOCE);
        insertQuestion(q296);

        Question q297 = new Question("KIWIFRUIT",
                "Kiwi", "Nektarynka", "Cytryna", 1,
                Question.DIFFICULTY_HARD, Category.OWOCE);
        insertQuestion(q297);

        Question q298 = new Question("LIME",
                "Jabłko", "Limonka", "Figa", 2,
                Question.DIFFICULTY_MEDIUM, Category.WARZYWA);
        insertQuestion(q298);

        Question q299 = new Question("RAISINS",
                "Papaja", "Marakuja", "Rodzynki", 3,
                Question.DIFFICULTY_HARD, Category.OWOCE);
        insertQuestion(q299);

        Question q300 = new Question("PAPAYA",
                "Papaja", "Marakuja", "Nektarynka", 1,
                Question.DIFFICULTY_HARD, Category.OWOCE);
        insertQuestion(q300);

        Question q301 = new Question("POMEGRANATE",
                "Orzech włoski", "Granat", "Pigwa", 2,
                Question.DIFFICULTY_HARD, Category.OWOCE);
        insertQuestion(q301);

        Question q302 = new Question("ROSE HIP",
                "Pigwa", "Banan", "Owoc dzikiej róży", 3,
                Question.DIFFICULTY_HARD, Category.OWOCE);
        insertQuestion(q302);

        Question q303 = new Question("PERSIMMON",
                "Persymona", "Banan", "Gruszka", 1,
                Question.DIFFICULTY_HARD, Category.OWOCE);
        insertQuestion(q303);

        Question q304 = new Question("ALMOND",
                "Morwa", "Migdał", "Morela", 2,
                Question.DIFFICULTY_HARD, Category.OWOCE);
        insertQuestion(q304);

        Question q305 = new Question("DRAGON FRUIT",
                "Arbuz", "Poziomka", "Pitaja", 3,
                Question.DIFFICULTY_HARD, Category.OWOCE);
        insertQuestion(q305);

        Question q306 = new Question("CHAIR",
                "Krzesło", "Biurko", "Telewizor", 1,
                Question.DIFFICULTY_EASY, Category.MEBLE);
        insertQuestion(q306);

        Question q307 = new Question("ARMCHAIR",
                "Krzesło", "Fotel", "Kanapa", 2,
                Question.DIFFICULTY_EASY, Category.MEBLE);
        insertQuestion(q307);

        Question q308 = new Question("TABLE",
                "Tablica", "Szafa", "Stół", 3,
                Question.DIFFICULTY_EASY, Category.MEBLE);
        insertQuestion(q308);

        Question q309 = new Question("WARDROBE",
                "Szafa", "Lodówka", "Łóżko", 1,
                Question.DIFFICULTY_EASY, Category.MEBLE);
        insertQuestion(q309);

        Question q310 = new Question("SOFA",
                "Łóżko", "Kanapa", "Biurko", 2,
                Question.DIFFICULTY_EASY, Category.MEBLE);
        insertQuestion(q310);

        Question q311 = new Question("FRIDGE",
                "Zamrażalka", "Drzwi", "Lodówka", 3,
                Question.DIFFICULTY_EASY, Category.MEBLE);
        insertQuestion(q311);

        Question q312 = new Question("BED",
                "Łóżko", "Kredens", "Wanna", 1,
                Question.DIFFICULTY_EASY, Category.MEBLE);
        insertQuestion(q312);

        Question q313 = new Question("WASHBASIN",
                "Schody", "Umywalka", "Okno", 2,
                Question.DIFFICULTY_EASY, Category.MEBLE);
        insertQuestion(q313);

        Question q314 = new Question("SINK",
                "Okno", "Kuchenka", "Zlew", 3,
                Question.DIFFICULTY_EASY, Category.MEBLE);
        insertQuestion(q314);

        Question q315 = new Question("CUPBOARD",
                "Kredens", "Fotel", "Umywalka", 1,
                Question.DIFFICULTY_EASY, Category.MEBLE);
        insertQuestion(q315);

        Question q316 = new Question("TELEVISION SET",
                "Drzwi", "Telewizor", "Kuchenka", 2,
                Question.DIFFICULTY_EASY, Category.MEBLE);
        insertQuestion(q316);

        Question q317 = new Question("COOKER",
                "Schody", "Stół", "Kuchenka", 3,
                Question.DIFFICULTY_EASY, Category.MEBLE);
        insertQuestion(q317);

        Question q318 = new Question("DOOR",
                "Drzwi", "Krzesło", "LALKA", 1,
                Question.DIFFICULTY_EASY, Category.MEBLE);
        insertQuestion(q318);

        Question q319 = new Question("Window",
                "Telewizor", "Okno", "Umywalka", 2,
                Question.DIFFICULTY_EASY, Category.MEBLE);
        insertQuestion(q319);

        Question q320 = new Question("Stairs",
                "Kanapa", "Fotel", "Schody", 3,
                Question.DIFFICULTY_EASY, Category.MEBLE);
        insertQuestion(q320);

        Question q321 = new Question("LAMP",
                "Lampa", "Kanapa", "Kredens", 1,
                Question.DIFFICULTY_MEDIUM, Category.MEBLE);
        insertQuestion(q321);

        Question q322 = new Question("Carpet",
                "Lampa", "Dywan", "Lustro", 2,
                Question.DIFFICULTY_MEDIUM, Category.MEBLE);
        insertQuestion(q322);

        Question q323 = new Question("MIRROR",
                "Lodówka", "Zegar", "Lustro", 3,
                Question.DIFFICULTY_MEDIUM, Category.MEBLE);
        insertQuestion(q323);

        Question q324 = new Question("Shelf",
                "Półka", "Szafa", "Kredens", 1,
                Question.DIFFICULTY_MEDIUM, Category.MEBLE);
        insertQuestion(q324);

        Question q325 = new Question("BOOKSHELF",
                "Komoda", "Półka na książki", "Półka", 2,
                Question.DIFFICULTY_MEDIUM, Category.MEBLE);
        insertQuestion(q325);

        Question q326 = new Question("CHANDELIER",
                "Żyrandol", "Stolik do kawy", "Komoda", 1,
                Question.DIFFICULTY_MEDIUM, Category.MEBLE);
        insertQuestion(q326);

        Question q327 = new Question("CHEST OF DRAWERS",
                "Półka", "Komoda", "Stolik do kawy", 2,
                Question.DIFFICULTY_MEDIUM, Category.MEBLE);
        insertQuestion(q327);

        Question q328 = new Question("BLINDS",
                "Dekoracje", "Dywanik", "Żaluzje", 3,
                Question.DIFFICULTY_MEDIUM, Category.MEBLE);
        insertQuestion(q328);

        Question q329 = new Question("CURTAINS",
                "Zasłony", "Żaluzje", "Plakat", 1,
                Question.DIFFICULTY_MEDIUM, Category.MEBLE);
        insertQuestion(q329);

        Question q330 = new Question("BENCH",
                "Fotel", "Ławka", "Krzesło", 2,
                Question.DIFFICULTY_HARD, Category.MEBLE);
        insertQuestion(q330);

        Question q331 = new Question("CRADLE",
                "Biurko", "Kredens", "Kołyska", 3,
                Question.DIFFICULTY_HARD, Category.MEBLE);
        insertQuestion(q331);

        Question q332 = new Question("FOOTREST",
                "Podnóżek", "Hamak", "Lampa", 1,
                Question.DIFFICULTY_HARD, Category.MEBLE);
        insertQuestion(q332);

        Question q333 = new Question("MATTERESS",
                "Lustro", "Materac", "Dywanik", 2,
                Question.DIFFICULTY_MEDIUM, Category.MEBLE);
        insertQuestion(q333);

        Question q334 = new Question("VANITY",
                "Materac", "Lustro", "Toaletka", 3,
                Question.DIFFICULTY_HARD, Category.MEBLE);
        insertQuestion(q334);

        Question q335 = new Question("WATERBED",
                "Łóżko wodne", "Łóżko", "Szafa", 1,
                Question.DIFFICULTY_HARD, Category.MEBLE);
        insertQuestion(q335);

        Question q336 = new Question("DISHWASHER",
                "Lodówka", "Zmywarka", "Zlew", 2,
                Question.DIFFICULTY_HARD, Category.MEBLE);
        insertQuestion(q336);

        Question q337 = new Question("STOVE",
                "Pralka", "Stół", "Kuchenka", 3,
                Question.DIFFICULTY_HARD, Category.MEBLE);
        insertQuestion(q337);

        Question q338 = new Question("AIR CONDITIONING",
                "Klimatyzacja", "Odświeżacz powietrza", "Wiatrak", 1,
                Question.DIFFICULTY_HARD, Category.MEBLE);
        insertQuestion(q338);

        Question q339 = new Question("VIDEO GAME CONSOLE",
                "Radio", "Konsola do gier", "Odtwarzacz DVD", 2,
                Question.DIFFICULTY_HARD, Category.MEBLE);
        insertQuestion(q339);

        Question q340 = new Question("FIREPLACE",
                "Biurko", "Fotel", "Kominek", 3,
                Question.DIFFICULTY_HARD, Category.MEBLE);
        insertQuestion(q340);

        Question q341 = new Question("SHOWER",
                "Prysznic", "Wieszak na ręcznik", "Komin", 1,
                Question.DIFFICULTY_HARD, Category.MEBLE);
        insertQuestion(q341);

        Question q342 = new Question("SKIRT",
                "Skarpetka", "Spódnica", "Sukienka", 2,
                Question.DIFFICULTY_EASY, Category.UBRANIA);
        insertQuestion(q342);

        Question q343 = new Question("SHORTS",
                "Buty", "Podkoszulka", "Spodenki", 3,
                Question.DIFFICULTY_EASY, Category.UBRANIA);
        insertQuestion(q343);

        Question q344 = new Question("SHOES",
                "Buty", "But", "Spodenki", 1,
                Question.DIFFICULTY_EASY, Category.UBRANIA);
        insertQuestion(q344);

        Question q345 = new Question("TIE",
                "Muszka", "Krawat", "Opaska", 2,
                Question.DIFFICULTY_EASY, Category.UBRANIA);
        insertQuestion(q345);

        Question q346 = new Question("FUR COAT",
                "Kurtka", "Kożuch", "Futro", 3,
                Question.DIFFICULTY_EASY, Category.UBRANIA);
        insertQuestion(q346);

        Question q347 = new Question("SUIT",
                "Garnitur", "Stanik", "Buty", 1,
                Question.DIFFICULTY_EASY, Category.UBRANIA);
        insertQuestion(q347);

        Question q348 = new Question("BOW TIE",
                "Buty", "Muszka", "Krawat", 2,
                Question.DIFFICULTY_EASY, Category.UBRANIA);
        insertQuestion(q348);

        Question q349 = new Question("DENIM JACKET",
                "Koszula", "Sweter", "Kurtka dżinsowa", 3,
                Question.DIFFICULTY_EASY, Category.UBRANIA);
        insertQuestion(q349);

        Question q350 = new Question("Trainers",
                "Buty sportowe", "Kozaki", "Szpilki", 1,
                Question.DIFFICULTY_EASY, Category.UBRANIA);
        insertQuestion(q350);

        Question q351 = new Question("CAP",
                "Szalik", "Czapka", "Kubek", 2,
                Question.DIFFICULTY_EASY, Category.UBRANIA);
        insertQuestion(q351);

        Question q352 = new Question("HAT",
                "Kapelusz", "Szalik", "Koszula", 1,
                Question.DIFFICULTY_MEDIUM, Category.UBRANIA);
        insertQuestion(q352);

        Question q353 = new Question("JUMPER",
                "Rękawiczki", "Sweter", "Szalik", 2,
                Question.DIFFICULTY_MEDIUM, Category.UBRANIA);
        insertQuestion(q353);

        Question q354 = new Question("SCARF",
                "Sweter", "Rękawiczki", "Szalik", 3,
                Question.DIFFICULTY_MEDIUM, Category.UBRANIA);
        insertQuestion(q354);

        Question q355 = new Question("TRACKSUIT",
                "Dres", "Garnitur", "Bluzka", 1,
                Question.DIFFICULTY_MEDIUM, Category.UBRANIA);
        insertQuestion(q355);

        Question q356 = new Question("SHIRT",
                "Buty", "Koszula", "Kapcie", 2,
                Question.DIFFICULTY_MEDIUM, Category.UBRANIA);
        insertQuestion(q356);

        Question q357 = new Question("SLIPPERS",
                "Piżama", "Skarpetki", "Kapcie", 3,
                Question.DIFFICULTY_MEDIUM, Category.UBRANIA);
        insertQuestion(q357);

        Question q358 = new Question("GLOVES",
                "Rękawiczki", "Skarpetki", "Szalik", 1,
                Question.DIFFICULTY_MEDIUM, Category.UBRANIA);
        insertQuestion(q358);

        Question q359 = new Question("SOCKS",
                "Rękawiczki", "Skarpetki", "Szalik", 2,
                Question.DIFFICULTY_MEDIUM, Category.UBRANIA);
        insertQuestion(q359);

        Question q360 = new Question(".Medium: B is correct",
                "A", "B", "C", 2,
                Question.DIFFICULTY_MEDIUM, Category.WARZYWA);
        insertQuestion(q360);

        Question q361 = new Question("JEANS",
                "Dresy", "Czapka", "Dżinsy", 3,
                Question.DIFFICULTY_MEDIUM, Category.UBRANIA);
        insertQuestion(q361);

        Question q362 = new Question("BOOT",
                "Kozak", "Szalik", "Czapka", 1,
                Question.DIFFICULTY_MEDIUM, Category.UBRANIA);
        insertQuestion(q362);

        Question q363 = new Question("SWIMMING COSTUME",
                "Skarpetki", "Kostium kąpielowy", "Krawat", 2,
                Question.DIFFICULTY_HARD, Category.UBRANIA);
        insertQuestion(q363);

        Question q364 = new Question("TIGHTS",
                "Pończochy", "Podkolanówki", "Rajstopy", 3,
                Question.DIFFICULTY_HARD, Category.UBRANIA);
        insertQuestion(q364);

        Question q365 = new Question("COLLAR",
                "Kołnierz", "Bluzka", "Polar", 1,
                Question.DIFFICULTY_HARD, Category.UBRANIA);
        insertQuestion(q365);

        Question q366 = new Question("HEEL",
                "Pulower", "Obcas", "Kurtka", 2,
                Question.DIFFICULTY_HARD, Category.UBRANIA);
        insertQuestion(q366);

        Question q367 = new Question("PANTS",
                "Koszulka", "Skarpetki", "Majtki", 3,
                Question.DIFFICULTY_HARD, Category.UBRANIA);
        insertQuestion(q367);

        Question q368 = new Question("PULLOVER",
                "Pulower", "Sweter", "Dres", 1,
                Question.DIFFICULTY_HARD, Category.UBRANIA);
        insertQuestion(q368);

        Question q369 = new Question("SLEEVE",
                "Czapka", "Rękaw", "Nauszniki", 2,
                Question.DIFFICULTY_HARD, Category.UBRANIA);
        insertQuestion(q369);

        Question q370 = new Question("SWIMMING TRUNKS",
                "Dres", "Fartuch", "Kąpielówki", 3,
                Question.DIFFICULTY_HARD, Category.UBRANIA);
        insertQuestion(q370);

        Question q371 = new Question("UNDERWEAR",
                "Bielizna", "Gorset", "Fartuch", 1,
                Question.DIFFICULTY_HARD, Category.UBRANIA);
        insertQuestion(q371);

        Question q372 = new Question("VEST",
                "Mundur", "Kamizelka", "Sweter", 2,
                Question.DIFFICULTY_HARD, Category.UBRANIA);
        insertQuestion(q372);

        Question q373 = new Question("BELT",
                "Buty", "Szelki", "Pasek", 3,
                Question.DIFFICULTY_HARD, Category.UBRANIA);
        insertQuestion(q373);

        Question q374 = new Question("COOK",
                "Kucharz", "Kelner", "Trener", 1,
                Question.DIFFICULTY_EASY, Category.ZAWODY);
        insertQuestion(q374);

        Question q375 = new Question("DOCTOR",
                "Nauczyciel", "Lekarz", "Prezydent", 2,
                Question.DIFFICULTY_EASY, Category.ZAWODY);
        insertQuestion(q375);

        Question q376 = new Question("DRIVER",
                "Inżynier", "Dentysta", "Kierowca", 3,
                Question.DIFFICULTY_EASY, Category.ZAWODY);
        insertQuestion(q376);

        Question q377 = new Question("TEACHER",
                "Nauczyciel/ka", "Inżynier", "Kierownik", 1,
                Question.DIFFICULTY_EASY, Category.ZAWODY);
        insertQuestion(q377);

        Question q378 = new Question("WAITER/WAITRESS",
                "Monter", "Kelner/ka", "Kucharz", 2,
                Question.DIFFICULTY_EASY, Category.ZAWODY);
        insertQuestion(q378);

        Question q379 = new Question("ACTOR/ACTRES",
                "Piosenkarz/rka", "Atysta/ka", "Aktor/ka", 3,
                Question.DIFFICULTY_EASY, Category.ZAWODY);
        insertQuestion(q379);

        Question q380 = new Question("BUSINESSMAN/BUSINESSWOMAN",
                "Biznesmen/ka", "Dyrektor/ka", "Recepcjonista/ka", 1,
                Question.DIFFICULTY_EASY, Category.ZAWODY);
        insertQuestion(q380);

        Question q381 = new Question("CAR MECHANIC",
                "Kierowca", "Mechanik samochodowy", "Kucharz", 2,
                Question.DIFFICULTY_EASY, Category.ZAWODY);
        insertQuestion(q381);

        Question q382 = new Question("CHEF",
                "Kierownik", "Kucharz", "Szef kuchni", 3,
                Question.DIFFICULTY_EASY, Category.ZAWODY);
        insertQuestion(q382);

        Question q383 = new Question("CLEANER",
                "Sprzątacz/ka", "Dentysta/ka", "Pielęgniarz/ka", 1,
                Question.DIFFICULTY_EASY, Category.ZAWODY);
        insertQuestion(q383);

        Question q384 = new Question("DANCER",
                "Artysta/ka", "Tancerz/ka", "Piłkarz/ka", 2,
                Question.DIFFICULTY_EASY, Category.ZAWODY);
        insertQuestion(q384);

        Question q385 = new Question("DENTIST",
                "Pielęgniarz/ka", "Lekarz/ka", "Dentysta/ka", 3,
                Question.DIFFICULTY_EASY, Category.ZAWODY);
        insertQuestion(q385);

        Question q386 = new Question("ENGINEER",
                "Inżynier", "Mechanik samochodowy", "Spawacz", 1,
                Question.DIFFICULTY_EASY, Category.ZAWODY);
        insertQuestion(q386);

        Question q387 = new Question("FARMER",
                "Opiekun/ka zwierząt", "Rolnik", "Malarz/ka", 2,
                Question.DIFFICULTY_EASY, Category.ZAWODY);
        insertQuestion(q387);

        Question q388 = new Question("FOOTBALLER",
                "Koszykarz", "Siatkarz", "Piłkarz", 3,
                Question.DIFFICULTY_EASY, Category.ZAWODY);
        insertQuestion(q388);

        Question q389 = new Question("TOUR GUIDE",
                "Przewodnik wycieczek", "Fotograf", "Pilot", 1,
                Question.DIFFICULTY_EASY, Category.ZAWODY);
        insertQuestion(q389);

        Question q390 = new Question("PHOTOGRAPHER",
                "Malarz/ka", "Fotograf", "Artysta/ka", 2,
                Question.DIFFICULTY_EASY, Category.ZAWODY);
        insertQuestion(q390);

        Question q391 = new Question("WRITER",
                "Lektor", "Redaktor/ka", "Pisarz/ka", 3,
                Question.DIFFICULTY_EASY, Category.ZAWODY);
        insertQuestion(q391);

        Question q392 = new Question("ARTIST",
                "Artysta/ka", "Malarz/ka", "Tancerz/ka", 1,
                Question.DIFFICULTY_EASY, Category.ZAWODY);
        insertQuestion(q392);

        Question q393 = new Question("SINGER",
                "Muzyk", "Piosenkarz/ka", "Aktor/ka", 2,
                Question.DIFFICULTY_EASY, Category.ZAWODY);
        insertQuestion(q393);

        Question q394 = new Question("POLICE OFFICER",
                "Strażak", "Ratownik", "Funkcjonariusz/ka policji", 3,
                Question.DIFFICULTY_EASY, Category.ZAWODY);
        insertQuestion(q394);

        Question q395 = new Question("MANAGER",
                "Menadżer/ka", "Szeg kuchni", "Handlowiec", 1,
                Question.DIFFICULTY_EASY, Category.ZAWODY);
        insertQuestion(q395);

        Question q396 = new Question("SECRETARY",
                "Handlowiec", "Sekretarz/ka", "Przedsiębiorca", 2,
                Question.DIFFICULTY_EASY, Category.ZAWODY);
        insertQuestion(q396);

        Question q397 = new Question("PILOT",
                "Kapitan", "Kierowca", "Pilot", 3,
                Question.DIFFICULTY_EASY, Category.ZAWODY);
        insertQuestion(q397);

        Question q398 = new Question("NURSE",
                "Pielęgniarz/ka", "Lekarz/ka", "Ordynator/ka", 1,
                Question.DIFFICULTY_EASY, Category.ZAWODY);
        insertQuestion(q398);

        Question q399 = new Question("TAXI DRIVER",
                "Kierowca ciężarówki", "Kierowca taksówki", "Kierowca wyścigowy", 2,
                Question.DIFFICULTY_EASY, Category.ZAWODY);
        insertQuestion(q399);

        Question q400 = new Question("RECEPTIONIST",
                "Pisarz/ka", "Sekretarz/ka", "Recepcjonista/ka", 3,
                Question.DIFFICULTY_EASY, Category.ZAWODY);
        insertQuestion(q400);

        Question q401 = new Question("VET",
                "Lekarz", "Weterynarz", "Pielęgniarz/ka", 2,
                Question.DIFFICULTY_EASY, Category.ZAWODY);
        insertQuestion(q401);

        Question q402 = new Question("BAKER",
                "Kucharz/ka", "Piekarz", "Szef kuchni", 2,
                Question.DIFFICULTY_EASY, Category.ZAWODY);
        insertQuestion(q402);

        Question q403 = new Question("HAIRDRESSER",
                "Projektant/ka", "Stylista/ka", "Fryzjer/ka", 3,
                Question.DIFFICULTY_EASY, Category.ZAWODY);
        insertQuestion(q403);

        Question q404 = new Question("SOLDIER",
                "Żołnierz", "Spawacz", "Generał", 1,
                Question.DIFFICULTY_EASY, Category.ZAWODY);
        insertQuestion(q404);

        Question q405 = new Question("TRAINER",
                "Sportowiec", "Trener", "Pikarz", 2,
                Question.DIFFICULTY_EASY, Category.ZAWODY);
        insertQuestion(q405);

        Question q406 = new Question("POSTMAN",
                "Akwizytor", "Windykator", "Listonosz", 3,
                Question.DIFFICULTY_MEDIUM, Category.ZAWODY);
        insertQuestion(q406);

        Question q407 = new Question("ARCHITECT",
                "Architekt/ka", "Brygadzista/ka", "Inspektor/ka", 1,
                Question.DIFFICULTY_MEDIUM, Category.ZAWODY);
        insertQuestion(q407);

        Question q408 = new Question("JOURNALIST",
                "Prezenter/ka telewiyjna", "Dziennikarz/ka", "Wydawca", 2,
                Question.DIFFICULTY_MEDIUM, Category.ZAWODY);
        insertQuestion(q408);

        Question q409 = new Question("DETECTIVE",
                "Inspektor", "Policjant", "Detektyw", 3,
                Question.DIFFICULTY_MEDIUM, Category.ZAWODY);
        insertQuestion(q409);

        Question q410 = new Question("PRIEST",
                "Ksiądz", "Biskup", "Papierz", 1,
                Question.DIFFICULTY_MEDIUM, Category.ZAWODY);
        insertQuestion(q410);

        Question q411 = new Question("DISC JOCKEY",
                "Kompozytor", "Didżej", "Piosenkarz/ka", 2,
                Question.DIFFICULTY_MEDIUM, Category.ZAWODY);
        insertQuestion(q411);

        Question q412 = new Question("FIREFIGHTER",
                "Policjant", "Ratownik", "Strażak", 3,
                Question.DIFFICULTY_MEDIUM, Category.ZAWODY);
        insertQuestion(q412);

        Question q413 = new Question("GUARD",
                "Strażnik", "Ochroniarz osobisty", "Policjant", 1,
                Question.DIFFICULTY_MEDIUM, Category.ZAWODY);
        insertQuestion(q413);

        Question q414 = new Question("SCIENTIST",
                "Lekarz/ka", "Naukowiec", "Archeolog", 2,
                Question.DIFFICULTY_MEDIUM, Category.ZAWODY);
        insertQuestion(q414);

        Question q415 = new Question("MODEL",
                "Wydawca", "Projektant/ka mody", "Model/ka", 3,
                Question.DIFFICULTY_MEDIUM, Category.ZAWODY);
        insertQuestion(q415);

        Question q416 = new Question("BARMAN",
                "Barman/ka", "Kelner/ka", "Kucharz/ka", 1,
                Question.DIFFICULTY_MEDIUM, Category.ZAWODY);
        insertQuestion(q416);

        Question q417 = new Question("BUILDER",
                "Inżynier", "Budowniczy", "Brygadzista", 2,
                Question.DIFFICULTY_MEDIUM, Category.ZAWODY);
        insertQuestion(q417);

        Question q418 = new Question("ACCOUNTANT",
                "Doradca prawny", "Sekretarz/ka", "Księgowy/a", 3,
                Question.DIFFICULTY_MEDIUM, Category.ZAWODY);
        insertQuestion(q418);

        Question q419 = new Question("DIRECTOR",
                "Reżyser", "Dyrektor", "Menadżer", 1,
                Question.DIFFICULTY_MEDIUM, Category.ZAWODY);
        insertQuestion(q419);

        Question q420 = new Question("BABYSITTER",
                "Położna", "Opiekunka do dziecka", "Przedszkolanka", 2,
                Question.DIFFICULTY_MEDIUM, Category.ZAWODY);
        insertQuestion(q420);

        Question q421 = new Question("BARBER",
                "Projektant mody męskiej", "Fryzjer damski", "Fryzjer męski", 3,
                Question.DIFFICULTY_MEDIUM, Category.ZAWODY);
        insertQuestion(q421);

        Question q422 = new Question("PHARMACIST",
                "Farmaceuta/ka", "Lekarz/ka", "Ordynator", 1,
                Question.DIFFICULTY_MEDIUM, Category.ZAWODY);
        insertQuestion(q422);

        Question q423 = new Question("GARDENER",
                "Pracownik zoo", "Ogrodnik", "Kwiaciarz/ka", 2,
                Question.DIFFICULTY_MEDIUM, Category.ZAWODY);
        insertQuestion(q423);

        Question q424 = new Question("LIFEGUARD",
                "Strażnik", "Ochroniarz osobisty", "Ratownik", 3,
                Question.DIFFICULTY_MEDIUM, Category.ZAWODY);
        insertQuestion(q424);

        Question q425 = new Question("BANKER",
                "Bankier", "Sekretarz/ka", "Komornik", 1,
                Question.DIFFICULTY_MEDIUM, Category.ZAWODY);
        insertQuestion(q425);

        Question q426 = new Question("SHOEMAKER",
                "Krawiec", "Szewc", "Projektant/ka", 2,
                Question.DIFFICULTY_MEDIUM, Category.ZAWODY);
        insertQuestion(q426);

        Question q427 = new Question("PLUMBER",
                "Dekarz", "Inżynier", "Hydraulik", 3,
                Question.DIFFICULTY_MEDIUM, Category.ZAWODY);
        insertQuestion(q427);

        Question q428 = new Question("CRITIC",
                "Krytyk", "Sędzia", "Polityk", 1,
                Question.DIFFICULTY_MEDIUM, Category.ZAWODY);
        insertQuestion(q428);

        Question q429 = new Question("COPYWRITER",
                "Projektant/ka", "Autor tekstów reklamowych", "Pisarz/ka", 2,
                Question.DIFFICULTY_MEDIUM, Category.ZAWODY);
        insertQuestion(q429);

        Question q430 = new Question("PSYCHOLOGIST",
                "Trener/ka", "Pracownik fizyczny", "Psycholog", 3,
                Question.DIFFICULTY_MEDIUM, Category.ZAWODY);
        insertQuestion(q430);

        Question q431 = new Question("EDITOR",
                "Wydawca", "Autor tekstów reklamowych", "Informatyk", 1,
                Question.DIFFICULTY_MEDIUM, Category.ZAWODY);
        insertQuestion(q431);

        Question q432 = new Question("INVESTIGATOR",
                "Detektyw", "Śledczy", "Policjant/ka", 2,
                Question.DIFFICULTY_MEDIUM, Category.ZAWODY);
        insertQuestion(q432);

        Question q433 = new Question("CONDUCTOR",
                "Inżynier", "Przewodnik", "Dyrygent", 3,
                Question.DIFFICULTY_MEDIUM, Category.ZAWODY);
        insertQuestion(q433);

        Question q434 = new Question("LIBRARIAN",
                "Bibliotekarz/ka", "Księgowy/a", "Sekretarz/ka", 1,
                Question.DIFFICULTY_MEDIUM, Category.ZAWODY);
        insertQuestion(q434);

        Question q435 = new Question("JUDGE",
                "Krytyk", "Sędzia", "Agent ubezpieczeniowy", 2,
                Question.DIFFICULTY_MEDIUM, Category.ZAWODY);
        insertQuestion(q435);

        Question q436 = new Question("FLIGHT ATTENDANT",
                "Mechanik samolotów", "Pilot", "Steward/essa", 3,
                Question.DIFFICULTY_MEDIUM, Category.ZAWODY);
        insertQuestion(q436);

        Question q437 = new Question("INSURANCE AGENT",
                "Agent ubezpieczeń", "Agent specjalny", "Śledczy", 1,
                Question.DIFFICULTY_HARD, Category.ZAWODY);
        insertQuestion(q437);

        Question q438 = new Question("WELDER",
                "Tynkarz", "Spawacz", "Pracownik fabryki", 2,
                Question.DIFFICULTY_HARD, Category.ZAWODY);
        insertQuestion(q438);

        Question q439 = new Question("STUNTMAN",
                "Analityk", "Aktor/ka", "Kaskader", 3,
                Question.DIFFICULTY_HARD, Category.ZAWODY);
        insertQuestion(q439);

        Question q440 = new Question("CARPENTER",
                "Cieśla", "Spawacz", "Kowal", 1,
                Question.DIFFICULTY_HARD, Category.ZAWODY);
        insertQuestion(q440);

        Question q441 = new Question("BLACKSMITH",
                "Agent ubezpieczeniowy", "Kowal", "Kominiarz", 2,
                Question.DIFFICULTY_HARD, Category.ZAWODY);
        insertQuestion(q441);

        Question q442 = new Question("WATCHMAKER",
                "Ślusarz", "Serwisant AGD", "Zegarmistrz", 3,
                Question.DIFFICULTY_HARD, Category.ZAWODY);
        insertQuestion(q442);

        Question q443 = new Question("TAILOR",
                "Krawiec męski", "Krawiec damski", "Szewc", 1,
                Question.DIFFICULTY_HARD, Category.ZAWODY);
        insertQuestion(q443);

        Question q444 = new Question("LOCKSMITH",
                "Kowal", "Ślusarz", "Cieśla", 2,
                Question.DIFFICULTY_HARD, Category.ZAWODY);
        insertQuestion(q444);

        Question q445 = new Question("SOLICITOR",
                "Prokurator", "Doradca zawodowy", "Adwokat", 3,
                Question.DIFFICULTY_HARD, Category.ZAWODY);
        insertQuestion(q445);

        Question q446 = new Question("BLUE-COLLAR WORKER",
                "Pracownik fizyczny", "Pracownik fabryki", "Spawacz", 1,
                Question.DIFFICULTY_HARD, Category.ZAWODY);
        insertQuestion(q446);

        Question q447 = new Question("WELDER",
                "Kaskader", "Spawacz", "Kominiarz", 2,
                Question.DIFFICULTY_HARD, Category.ZAWODY);
        insertQuestion(q447);

        Question q448 = new Question("FURRIER",
                "Złotnik", "Krawiec", "Kuśnierz", 3,
                Question.DIFFICULTY_HARD, Category.ZAWODY);
        insertQuestion(q448);

        Question q449 = new Question("BAILIFF",
                "Komornik", "Akwizytor", "Windykator", 1,
                Question.DIFFICULTY_HARD, Category.ZAWODY);
        insertQuestion(q449);

        Question q450 = new Question("TURNER",
                "Dekarz", "Tokarz", "Kuśnierz", 2,
                Question.DIFFICULTY_HARD, Category.ZAWODY);
        insertQuestion(q450);

        Question q451 = new Question("DEBT COLLECTOR",
                "Komornik", "Makler giełdowy", "Windykator", 3,
                Question.DIFFICULTY_HARD, Category.ZAWODY);
        insertQuestion(q451);

        Question q452 = new Question("UNDERTAKER",
                "Grabarz", "Ministrant", "Arcybiskup", 1,
                Question.DIFFICULTY_HARD, Category.ZAWODY);
        insertQuestion(q452);

        Question q453 = new Question("FOREMAN",
                "Pracownik fizyczny", "Brygadzista", "Tkacz", 2,
                Question.DIFFICULTY_HARD, Category.ZAWODY);
        insertQuestion(q453);

        Question q454 = new Question("STOCKBROKER",
                "Bankier", "Prawnik", "Makler giełdowy", 3,
                Question.DIFFICULTY_HARD, Category.ZAWODY);
        insertQuestion(q454);

        Question q455 = new Question("WEAVER",
                "Tkacz", "Szwacz", "Szewc", 1,
                Question.DIFFICULTY_HARD, Category.ZAWODY);
        insertQuestion(q455);

        Question q456 = new Question("REFUSE COLLECTOR",
                "Poborca podatkowy", "Śmieciarz", "Sprzątacz", 2,
                Question.DIFFICULTY_HARD, Category.ZAWODY);
        insertQuestion(q456);

        Question q457 = new Question("ROOFER",
                "Tynkarz", "Tokarz", "Dekarz", 3,
                Question.DIFFICULTY_HARD, Category.ZAWODY);
        insertQuestion(q457);

        Question q458 = new Question("GOLDSMITH",
                "Złotnik", "Jubiler", "Zegarmistrz", 1,
                Question.DIFFICULTY_HARD, Category.ZAWODY);
        insertQuestion(q458);

        Question q459 = new Question("GRAPHIC DESIGNER",
                "Informatyk", "Grafik", "Rysownik", 2,
                Question.DIFFICULTY_HARD, Category.ZAWODY);
        insertQuestion(q459);

        Question q460 = new Question("FORESTER",
                "Leśniczy", "Drwal", "Leśnik", 3,
                Question.DIFFICULTY_HARD, Category.ZAWODY);
        insertQuestion(q460);

        Question q461 = new Question("SURGEON",
                "Chirurg", "Pediatra", "Laryngolog", 1,
                Question.DIFFICULTY_HARD, Category.ZAWODY);
        insertQuestion(q461);

        Question q462 = new Question("MAID",
                "Sprzątacz/ka", "Pokojówka", "Recepcjonista/ka", 2,
                Question.DIFFICULTY_HARD, Category.ZAWODY);
        insertQuestion(q462);

        Question q463 = new Question("GLAZIER",
                "Murarz", "Hydraulik", "Szklarz", 3,
                Question.DIFFICULTY_HARD, Category.ZAWODY);
        insertQuestion(q463);

        Question q464 = new Question("BRICKLAYER",
                "Murarz", "Cieśla", "Tynkarz", 1,
                Question.DIFFICULTY_HARD, Category.ZAWODY);
        insertQuestion(q464);

        Question q465 = new Question("MIDWIFE",
                "Pielęgniarz/ka", "Położna", "Chirurg", 2,
                Question.DIFFICULTY_HARD, Category.ZAWODY);
        insertQuestion(q465);

        Question q466 = new Question("MINER",
                "Hutnik", "Garncarz", "Górnik", 3,
                Question.DIFFICULTY_HARD, Category.ZAWODY);
        insertQuestion(q466);

        Question q467 = new Question("OPTICIAN",
                "Okulista", "Laryngolog", "Chirurg plastyczny", 1,
                Question.DIFFICULTY_HARD, Category.ZAWODY);
        insertQuestion(q467);

        Question q468 = new Question("AEROBICS",
                "Joga", "Aerobik", "Dżogging", 2,
                Question.DIFFICULTY_EASY, Category.SPORT);
        insertQuestion(q468);

        Question q469 = new Question("ARCHERY",
                "Strzelanie", "Rzut oszczepem", "Łucznictwo", 3,
                Question.DIFFICULTY_EASY, Category.SPORT);
        insertQuestion(q469);

        Question q470 = new Question("ATHLETICS",
                "Lekkoatletyka", "Zapasy", "Maraton", 1,
                Question.DIFFICULTY_EASY, Category.SPORT);
        insertQuestion(q470);

        Question q471 = new Question("BADMINTON",
                "Tenis ziemny", "Badminton", "Tenis stołowy", 2,
                Question.DIFFICULTY_EASY, Category.SPORT);
        insertQuestion(q471);

        Question q472 = new Question("BASEBALL",
                "A", "B", "C", 3,
                Question.DIFFICULTY_EASY, Category.SPORT);
        insertQuestion(q472);

        Question q473 = new Question("BASKETBALL",
                "Koszykówka", "Siatkówka", "Piłka ręczna", 1,
                Question.DIFFICULTY_EASY, Category.SPORT);
        insertQuestion(q473);

        Question q474 = new Question("BOXING",
                "Sumo", "Boks", "Dżudo", 2,
                Question.DIFFICULTY_EASY, Category.SPORT);
        insertQuestion(q474);

        Question q475 = new Question("BRIDGE",
                "Chińczyk", "Szachy", "Brydż", 3,
                Question.DIFFICULTY_EASY, Category.SPORT);
        insertQuestion(q475);

        Question q476 = new Question("CAR RACING",
                "Wyścigi samochodowe", "Żużel", "Wyścigi konno", 1,
                Question.DIFFICULTY_EASY, Category.SPORT);
        insertQuestion(q476);

        Question q477 = new Question("CHESS",
                "Warcaby", "Szachy", "Chińczyk", 2,
                Question.DIFFICULTY_EASY, Category.SPORT);
        insertQuestion(q477);

        Question q478 = new Question("COMBAT SPORTS",
                "Sporty motorowe", "Sporty wodne", "Sporty walki", 3,
                Question.DIFFICULTY_EASY, Category.SPORT);
        insertQuestion(q478);

        Question q479 = new Question("CRICKET",
                "Krykiet", "Rugby", "Golf", 1,
                Question.DIFFICULTY_EASY, Category.SPORT);
        insertQuestion(q479);

        Question q480 = new Question("CYCLING",
                "Łyżwiarstwo", "Kolarstwo", "Kajakarstwo", 2,
                Question.DIFFICULTY_EASY, Category.SPORT);
        insertQuestion(q480);

        Question q481 = new Question("DARTS",
                "Rzut oszczepem", "Bierki", "Rzutki", 3,
                Question.DIFFICULTY_EASY, Category.SPORT);
        insertQuestion(q481);

        Question q482 = new Question("DISCUS",
                "Rzut dyskiem", "Rzut młotem", "Rzut oszczepem", 1,
                Question.DIFFICULTY_EASY, Category.SPORT);
        insertQuestion(q482);

        Question q483 = new Question("DIVING",
                "Pływanie", "Nurkowanie", "Narty wodne", 2,
                Question.DIFFICULTY_EASY, Category.SPORT);
        insertQuestion(q483);

        Question q484 = new Question("GOLF",
                "Tenis", "Krykiet", "Golf", 3,
                Question.DIFFICULTY_EASY, Category.SPORT);
        insertQuestion(q484);

        Question q485 = new Question("GYMNASTICS",
                "Gimnastyka", "Aerobik", "Dżoging", 1,
                Question.DIFFICULTY_EASY, Category.SPORT);
        insertQuestion(q485);

        Question q486 = new Question("FOOTBAL",
                "Koszykówka", "Piłka nożna", "Piłka ręczna", 2,
                Question.DIFFICULTY_EASY, Category.SPORT);
        insertQuestion(q486);

        Question q487 = new Question("FENCING",
                "Sumo", "Zapasy", "Szermierka", 3,
                Question.DIFFICULTY_EASY, Category.SPORT);
        insertQuestion(q487);

        Question q488 = new Question("HAMMER THROWING",
                "Rzut młotem", "Rzut oszczepem", "Rzut dyskiem", 1,
                Question.DIFFICULTY_EASY, Category.SPORT);
        insertQuestion(q488);

        Question q489 = new Question("HORSE RACING",
                "Wyścigi psich zaprzęgów", "Wyścigi konne", "Wyścigi samochodowe", 2,
                Question.DIFFICULTY_MEDIUM, Category.SPORT);
        insertQuestion(q489);

        Question q490 = new Question("INDIVIDUAL SPORTS",
                "Sporty drużynowe", "Sporty drużynowo indywidualne", "Sporty indywidualne", 3,
                Question.DIFFICULTY_MEDIUM, Category.SPORT);
        insertQuestion(q490);

        Question q491 = new Question("TEAM SPORTS",
                "Sporty drużynowe", "Sporty indywidualne", "Sporty drużynowo indywidualne", 1,
                Question.DIFFICULTY_MEDIUM, Category.SPORT);
        insertQuestion(q491);

        Question q492 = new Question("LONG JUMP",
                "Długi skok", "Skok w dal", "Skok wzwyż", 2,
                Question.DIFFICULTY_MEDIUM, Category.SPORT);
        insertQuestion(q492);

        Question q493 = new Question("HIGH JUMP",
                "Wysoki skok", "Skok w dal", "Skok wzwyż", 3,
                Question.DIFFICULTY_MEDIUM, Category.SPORT);
        insertQuestion(q493);

        Question q494 = new Question("RELAY RACE",
                "Sztafeta", "Bieg z przeszkodami", "Maraton", 1,
                Question.DIFFICULTY_MEDIUM, Category.SPORT);
        insertQuestion(q494);

        Question q495 = new Question("LONG-DISTANCE RUNNING",
                "Bieg krótkodystansowy", "Bieg długodystansowy", "Maraton", 2,
                Question.DIFFICULTY_MEDIUM, Category.SPORT);
        insertQuestion(q495);

        Question q496 = new Question("SAILING",
                "Narty wodne", "Pływanie na desce", "Żeglarstwo", 3,
                Question.DIFFICULTY_MEDIUM, Category.SPORT);
        insertQuestion(q496);

        Question q497 = new Question("ICE-SKATING",
                "Łyżwiarstwo", "Hokej", "Pływanie", 1,
                Question.DIFFICULTY_MEDIUM, Category.SPORT);
        insertQuestion(q497);

        Question q498 = new Question("SKI JUMPING",
                "Jazda na nartach", "Skoki narciarskie", "Łyżwiarstwo", 2,
                Question.DIFFICULTY_MEDIUM, Category.SPORT);
        insertQuestion(q498);

        Question q499 = new Question("SKIING",
                "Skoki narciarskie", "Hokej", "Jazda na nartach", 3,
                Question.DIFFICULTY_MEDIUM, Category.SPORT);
        insertQuestion(q499);

        Question q500 = new Question("SWIMMING",
                "Pływanie", "Narty wodne", "Nurkowanie", 1,
                Question.DIFFICULTY_MEDIUM, Category.SPORT);
        insertQuestion(q500);

        Question q501 = new Question("VOLLEYBALL",
                "Koszykówka", "Siatkówka", "Piłka ręczna", 2,
                Question.DIFFICULTY_MEDIUM, Category.SPORT);
        insertQuestion(q501);

        Question q502 = new Question("WEIGHTLIFTING",
                "Rzut młotem", "Zapasy", "Podnoszenie ciężarów", 3,
                Question.DIFFICULTY_MEDIUM, Category.SPORT);
        insertQuestion(q502);

        Question q503 = new Question("WINDSURFING",
                "Pływanie na desce", "Narty wodne", "Nurkowanie", 1,
                Question.DIFFICULTY_MEDIUM, Category.SPORT);
        insertQuestion(q503);

        Question q504 = new Question("WRESTLING",
                "Boks", "Zapasy", "Sumo", 2,
                Question.DIFFICULTY_MEDIUM, Category.SPORT);
        insertQuestion(q504);

        Question q505 = new Question("YOGA",
                "Aerobik", "Dżoging", "Joga", 3,
                Question.DIFFICULTY_MEDIUM, Category.SPORT);
        insertQuestion(q505);

        Question q506 = new Question("ARCHERY",
                "Łucznictwo", "Rzut dyskiem", "Strzelectwo", 1,
                Question.DIFFICULTY_MEDIUM, Category.SPORT);
        insertQuestion(q506);

        Question q507 = new Question("BILLIARD",
                "Kręgle", "Bilard", "Rzutki", 2,
                Question.DIFFICULTY_MEDIUM, Category.SPORT);
        insertQuestion(q507);

        Question q508 = new Question("BUNGEE JUMPING",
                "Akrobacje spadochronowe", "Skok wzwyż", "Skoki na bungee", 3,
                Question.DIFFICULTY_MEDIUM, Category.SPORT);
        insertQuestion(q508);

        Question q509 = new Question("CANOEING",
                "Kajakarstwo", "Żeglarstwo", "Wioślarstwo", 1,
                Question.DIFFICULTY_MEDIUM, Category.SPORT);
        insertQuestion(q509);

        Question q510 = new Question("CRICKET",
                "Rugby", "Krykiet", "Golf", 2,
                Question.DIFFICULTY_HARD, Category.SPORT);
        insertQuestion(q510);

        Question q511 = new Question("HANDBALL",
                "Koszykówka", "Siatkówka", "Piłka ręczna", 3,
                Question.DIFFICULTY_HARD, Category.SPORT);
        insertQuestion(q511);

        Question q512 = new Question("HANG-GLIDING",
                "Lotniarstwo", "Latanie szybowcem", "Latanie samolotem", 1,
                Question.DIFFICULTY_HARD, Category.SPORT);
        insertQuestion(q512);

        Question q513 = new Question("ICE-HOCKEY",
                "Bobslej", "Hokej", "Łyżwiarstwo", 2,
                Question.DIFFICULTY_HARD, Category.SPORT);
        insertQuestion(q513);

        Question q514 = new Question("PARACHUTING",
                "Paralotniarstwo", "Akrobacje spadochronowe", "Spadochroniarstwo", 3,
                Question.DIFFICULTY_HARD, Category.SPORT);
        insertQuestion(q514);

        Question q515 = new Question("RUGBY",
                "Rugby", "Krykiet", "Baseball", 1,
                Question.DIFFICULTY_HARD, Category.SPORT);
        insertQuestion(q515);

        Question q516 = new Question("ROWING",
                "Pływanie", "Wioślarstwo", "Kajakarstwo", 2,
                Question.DIFFICULTY_HARD, Category.SPORT);
        insertQuestion(q516);

        Question q517 = new Question("MARATHON",
                "Lekkoatletyka", "Triatlon", "Maraton", 3,
                Question.DIFFICULTY_HARD, Category.SPORT);
        insertQuestion(q517);

        Question q518 = new Question("SKATEBOARDING",
                "Jazda na deskorolce", "Pływanie na desce", "Jazda na wrotkach", 1,
                Question.DIFFICULTY_HARD, Category.SPORT);
        insertQuestion(q518);

        Question q519 = new Question("INDOOR SPORTS",
                "Sporty na świeżym powietrzu", "Sporty halowe", "Sporty ekstremalne", 2,
                Question.DIFFICULTY_HARD, Category.SPORT);
        insertQuestion(q519);

        Question q520 = new Question("OUTDOOR SPORTS",
                "Sporty halowe", "Sporty ekstremalne", "Sporty na świeżym powietrzu", 3,
                Question.DIFFICULTY_HARD, Category.SPORT);
        insertQuestion(q520);

        Question q521 = new Question("ROLLERBLADING",
                "Jazda na rolkach", "Jazda na deskorolce", "Łyżwiarstwo", 1,
                Question.DIFFICULTY_HARD, Category.SPORT);
        insertQuestion(q521);

        Question q522 = new Question("SKY SURFING",
                "Spadochroniarstwo", "Podniebne akrobacje na desce", "Pływanie na desce", 2,
                Question.DIFFICULTY_HARD, Category.SPORT);
        insertQuestion(q522);

        Question q523 = new Question("OBSTACKLE-RACE",
                "Maraton", "Bieg przez płotki", "Bieg z przeszkodami", 3,
                Question.DIFFICULTY_HARD, Category.SPORT);
        insertQuestion(q523);

        Question q524 = new Question("SHOT PUT",
                "Pchnięcie kulą", "Rzut dyskiem", "Rzut młotem", 1,
                Question.DIFFICULTY_HARD, Category.SPORT);
        insertQuestion(q524);

        Question q525 = new Question("SPEEDWAY",
                "Rajdy samochodowe", "Żużel", "Wyścigi NASCAR", 2,
                Question.DIFFICULTY_HARD, Category.SPORT);
        insertQuestion(q525);

        Question q526 = new Question("JAVELIN",
                "Łucznictwo", "Pchnięcie kulą", "Rzut oszczepem", 3,
                Question.DIFFICULTY_HARD, Category.SPORT);
        insertQuestion(q526);

        Question q527 = new Question("HURDLES",
                "Bieg przez płotki", "Bieg z przeszkodami", "Triatlon", 1,
                Question.DIFFICULTY_HARD, Category.SPORT);
        insertQuestion(q527);

        Question q528 = new Question("TUG-OF-WAR",
                "Podnoszenie ciężarów", "Przeciąganie liny", "Przeciąganie ciężarów", 2,
                Question.DIFFICULTY_HARD, Category.SPORT);
        insertQuestion(q528);

        Question q529 = new Question("DECATHLON",
                "Maraton", "Triatlon", "Dziesięciobój", 3,
                Question.DIFFICULTY_HARD, Category.SPORT);
        insertQuestion(q529);

        Question q530 = new Question("SKYDIVING",
                "Akrobacje spadochronowe", "Podniebne akrobacje na desce", "Latanie szybowcem", 1,
                Question.DIFFICULTY_HARD, Category.SPORT);
        insertQuestion(q530);
    }

    public void addQuestion(Question question){
        db = getWritableDatabase();
        insertQuestion(question);
    }

    public void addQuestions(List<Question> questions){
        db = getWritableDatabase();

        for (Question question : questions)
        {
            insertQuestion(question);
        }
    }

    private void insertQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME, null);

        if (c.moveToFirst()){
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            }while (c.moveToNext());
        }

        c.close();
        return categoryList;
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME,null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(int categoryID, String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuestionsTable.COLUMN_CATEGORY_ID + " = ? " +  " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};

        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

}
