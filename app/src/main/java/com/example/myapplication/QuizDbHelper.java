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
    private static final int DATABASE_VERSION = 13;



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
