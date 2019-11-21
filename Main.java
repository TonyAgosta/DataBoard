import java.util.List;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {

        MyDataBoard1<Data> Bacheca = new MyDataBoard1<Data>("PasswordProprietario");

        // TIPI DI CATEGORIE
        String C1 = "Foto";
        String C2 = "Video";
        String C3 = "Musica";
        String C4 = null;

        // PASSWORD
        String P1 = "PasswordProprietario";
        String P2 = "passworddiprova";
        String P3 = null;

        // AMICI
        String A1 = "Marco";
        String A2 = "Luigi";
        String A3 = "Tony";
        String A4 = null;

        // DATI CHE POSSONO ESSERE AGGIUNTI,RIMOSSI...
        MyData post1 = new MyData(C1, "Album", 0);
        MyData post2 = new MyData(C1, "Vacanze in montagna", 0);
        MyData post3 = null;
        MyData post4 = new MyData(C1, "Vacanze al mare", 0);
        MyData post5 = new MyData(C2, "Preferite", 4);
        MyData post6 = new MyData(C1, "Paesaggi", 3);

        try {
            Bacheca.createCategory(C1, P1);// creo la Categoria C1
            Bacheca.createCategory(C2, P1);// creo la Categoria C2
            Bacheca.createCategory(C3,P1);//creo la Categoria C3
            System.out.println("Sono state create le categorie: " + C1 + ", " + C2 +" e "+C3);

            // Bacheca.createCategory(C1, P2); //WrongPasswordException
            // Bacheca.createCategory(C1, P3);//NullPointerException,P3==null
            // Bacheca.createCategory(C4, P1); //NullPointerException,C4==null
        } catch (NullPointerException | WrongPasswordException e) {
            System.out.println(e);
        }

        try {
            Bacheca.removeCategory(C3, P1);
            System.out.println("E` stata rimossa la categoria:" + C3);
            // Bacheca.removeCategory(C3, P2);// WrongPasswordException
            // Bacheca.removeCategory(C4, P1);//NullPointerException,C4==null
            // Bacheca.removeCategory(C1, P3);//NullPointerException,P3==null
        } catch (NullPointerException | WrongPasswordException e) {
            System.out.println(e);
        }

        try {
            Bacheca.addFriend(C1, P1, A1);// aggiunge A1 alla categoria C3
            Bacheca.addFriend(C1, P1, A2);// aggiunge A2 alla categoria C3
            Bacheca.addFriend(C2,P1,A3);//aggiunge A3 alla categoria C2
            System.out.println("Sono stati aggiunti " + A1 + " e " + A2 + " alla categoria " + C1);
            System.out.println("E` stato aggiunto "+ A3 + " alla categoria " + C2);

            // Bacheca.addFriend(C3, P1, A3);//CategoryNotFoundException
            // Bacheca.addFriend(C1, P2, A3);//WrongPasswordException
            // Bacheca.addFriend(C4, P2, A1);//NullPointerException,C4==null
            // Bacheca.addFriend(C1, P1, A4);//NullPointerException,A4==null
            // Bacheca.addFriend(C1, P3, A2);//NullPointerException,P3==null

        } catch (NullPointerException | WrongPasswordException | NoCategoryException e) {
            System.out.println(e);
        }

        try {
            Bacheca.removeFriend(C1, P1, A2);// rimuove A2 da C1
            System.out.println("Dalla categoria " + C1 + " e` stato rimosso " + A2);
            // Bacheca.removeFriend(C1, P2, A1);// WrongPasswordException
            // Bacheca.removeFriend(C3, P1, A1);// CategoryNotFoundException
            // Bacheca.removeFriend(C4, P1, A1);//NullPointerException,C4==null
            // Bacheca.removeFriend(C1, P3, A1);//NullPointerException,P3==null
            // Bacheca.removeFriend(C1, P1, A4);//NullPointerException,A4==null
        } catch (NullPointerException | WrongPasswordException | NoCategoryException e) {
            System.out.println(e);
        }

        try {
            System.out.println("Alla categoria " + C1 + " sono stati aggiunti i seguenti dati:");
            if(Bacheca.put(P1, post1, C1)) post1.Display();// aggiunge il post1 alla categoria C1
            if(Bacheca.put(P1, post2, C1)) post2.Display();// aggiumge il post2 alla categoria C1
            if(Bacheca.put(P1, post5, C1)) post5.Display();// aggiunge il post5 alla categoria C1
            if(Bacheca.put(P1, post6, C1)) post6.Display();//aggiunge il post6 alla categoria C1
           
            System.out.println("");

            // Bacheca.put(P1, post1, C3);// CategoryNotFoundException
            // Bacheca.put(P2, post1, C3);// WrongPasswordException
            // Bacheca.put(P1,post1,C4);//NullPointerException,C4==null
            // Bacheca.put(P3,post1,C1); //NullPointerException,P3==null
            // Bacheca.put(P1,post3,C1);//NullPointerException,post3==null
        } catch (NullPointerException | WrongPasswordException | NoCategoryException e) {
            System.out.println(e);
        }

        try {

            Data provaget = Bacheca.get(P1, post1);
            System.out.println("Ho ottenuto la copia:");
            provaget.Display();
            System.out.println("");
            // Bacheca.get(P2, post1);// WrongPasswordException
            // Bacheca.get(P3, post1);//NullPointerException,P3==null
            // Bacheca.get(P1, post3);//NullPointerException,post3==null
        } catch (NullPointerException | WrongPasswordException | NoCategoryException | DataNotFoundException e) {
            System.out.println(e);
        }

        try {
            Data provaremove=Bacheca.remove(P1, post2);
            System.out.println("Ho rimosso dalla bacheca il dato:");
            provaremove.Display();
            System.out.println("");
            // Bacheca.remove(P2, post1);// WrongPasswordException
            // Bacheca.remove(P3, post1);//NullPointerException,P3==null
            // Bacheca.remove(P1, post3);//NullPointerException,post3==null

        } catch (NullPointerException | WrongPasswordException e) {
            System.out.println(e);
        }

        try {

            List<Data> provagetdatacategory = Bacheca.getDataCategory(P1, C1);
            System.out.println("Nella categoria " + C1 + " sono presenti i seguenti dati:");
            for (Data data : provagetdatacategory) {
                data.Display();
            }
            System.out.println("");
            // Bacheca.getDataCategory(P1, C3);// eccezione lanciata per category non
            // esistente
            // Bacheca.getDataCategory(P2, C1);// WrongPasswordException
            // Bacheca.getDataCategory(P3, C1);//NullPointerException,P3==null
            // Bacheca.getDataCategory(P1, C4);//NullPointerException,C4==null
        } catch (NullPointerException | WrongPasswordException | NoCategoryException e) {
            System.out.println(e);
        }

        try {

            Iterator<Data> provagetiterator = Bacheca.getIterator(P1);
            System.out.println("I dati ordinati per numero di like presenti in bacheca sono:");
            while (provagetiterator.hasNext()) {
                provagetiterator.next().Display();
            }
            System.out.println("");
            // Bacheca.getIterator(P2);// WrongPasswordException
            // Bacheca.getIterator(P3);//NullPointerException,P3==null
        } catch (NullPointerException | WrongPasswordException e) {
            System.out.println(e);
        }

        try {

            Bacheca.insertLike(A1, post1);// aggiunge un like al post1
            // Bacheca.insertLike(A3, post4);// DataNotFoundException
            // Bacheca.insertLike(A3, post1);// PermessDeniedeException
            // Bacheca.insertLike(A4, post1);// NullPointerException,A4==null
            // Bacheca.insertLike(A1, post3); // NullPointerException,post3==null
        } catch (NullPointerException | DataNotFoundException | PermessDeniedException | NoCategoryException e) {
            System.out.println(e);
        }

        try {
            Iterator<Data> provagetfrienditeretor = Bacheca.getFriendIterator(A1);
            System.out.println(A1 + " nella sua bacheca vede i seguenti dati:");
            while (provagetfrienditeretor.hasNext()) {
                provagetfrienditeretor.next().Display();
            }
            //Iterator<Data> prova=Bacheca.getFriendIterator(A3);
            //if(prova==null) System.out.println("null");
            // Bacheca.getFriendIterator(A3);// NoOneCategoryException
            // Bacheca.getFriendIterator(A4);//NullPointerException,A4==null

        } catch (NullPointerException | NoOneCategoryException e) {
            System.out.println(e);
        }

    }
}