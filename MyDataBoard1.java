import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Comparator;

public class MyDataBoard1<E extends Data> implements DataBoard<E> {

    // AF: <{Categorie_0,...,Categorie_k},password> tali che this.get(i)-> <{amico_1,...,amico_j},{dato_1,...,dato_h}>
    //  con 0<=i<=k
    // RI: passw!=null

    public class Coppia1 {
        private ArrayList<E> dati;
        private ArrayList<String> friends;

        public Coppia1() {
            dati = new ArrayList<>();
            friends = new ArrayList<>();
        }

        public ArrayList<E> getDati() {
            return dati;
        }

        public ArrayList<String> getFriends() {
            return friends;
        }
    }

    private String password;
    private ArrayList<String> Categorie;
    private ArrayList<Coppia1> Arrayliste;

    public MyDataBoard1(String passw) {
        if (passw == null)
            throw new NullPointerException("Invalid String p in Constructor");
        password = passw;
        Categorie = new ArrayList<String>();
        Arrayliste = new ArrayList<Coppia1>();
    }

    // Crea l’identità una categoria di dati
    @Override
    public void createCategory(String Category, String passw) throws NullPointerException, WrongPasswordException {
        if (Category == null)
            throw new NullPointerException("Invalid String Category in createCategory");
        if (passw == null)
            throw new NullPointerException("Invalid String passw in createCategory");
        if (!(password.equals(passw)))
            throw new WrongPasswordException("Wrong password");
        String NewCategory = Category;
        if (!Categorie.contains(Category)) {
            Categorie.add(NewCategory);
            Arrayliste.add(new Coppia1());
        }
    }

    // Rimuove l’identità una categoria di dati
    @Override
    public void removeCategory(String Category, String passw) throws NullPointerException, WrongPasswordException {
        if (Category == null)
            throw new NullPointerException("Invalid String Category in removeCategory");
        if (passw == null)
            throw new NullPointerException("Invalid String passw in removeCategory");
        if (!(password.equals(passw)))
            throw new WrongPasswordException("Wrong password");
        // .remove() rimuove dall'ArrayList l' argomento passato solo se presente
        int n = Categorie.indexOf(Category);
        if (n > 0) {
            Categorie.remove(n);
            Arrayliste.remove(n);
        }
    }

    // Aggiunge un amico ad una categoria di dati
    @Override
    public void addFriend(String Category, String passw, String friend)
            throws NullPointerException, WrongPasswordException, NoCategoryException {
        if (Category == null)
            throw new NullPointerException("Invalid String Category in addFriend");
        if (passw == null)
            throw new NullPointerException("Invalid String passw in addFriend");
        if (friend == null)
            throw new NullPointerException("Invalid String friend in addFriend");
        if (!(password.equals(passw)))
            throw new WrongPasswordException("Wrong password");
        if (!(Categorie.contains(Category)))
            throw new NoCategoryException("Category not found in collection");
        int n = Categorie.indexOf(Category);
        Arrayliste.get(n).getFriends().add(friend); // la add aggiunge solo se non e` gia presente
    }

    // rimuove un amico ad una categoria di dati
    @Override
    public void removeFriend(String Category, String passw, String friend)
            throws NullPointerException, WrongPasswordException, NoCategoryException {
        if (Category == null)
            throw new NullPointerException("Invalid String Category in removeFriend");
        if (passw == null)
            throw new NullPointerException("Invalid String passw in removeFriend");
        if (friend == null)
            throw new NullPointerException("Invalid String friend in removeFriend");
        if (!(password.equals(passw)))
            throw new WrongPasswordException("Wrong password");
        if (!(Categorie.contains(Category)))
            throw new NoCategoryException("Category not found in collection");
        int n = Categorie.indexOf(Category);
        Arrayliste.get(n).getFriends().remove(friend);
    }

    // Inserisce un dato in bacheca
    // se vengono rispettati i controlli di identità
    @Override
    public boolean put(String passw, E dato, String categoria)
            throws NullPointerException, WrongPasswordException, NoCategoryException {
        if (categoria == null)
            throw new NullPointerException("Invalid String categoria in put");
        if (passw == null)
            throw new NullPointerException("Invalid String passw in put");
        if (dato == null)
            throw new NullPointerException("Invalid dato in put");
        if (!(password.equals(passw)))
            throw new WrongPasswordException("Wrong Password");
        if (!(Categorie.contains(categoria)))
            throw new NoCategoryException("Category not found in collection");
        dato.setCategory(categoria);
        int n = Categorie.indexOf((categoria));
        return Arrayliste.get(n).getDati().add(dato);
    }

    // Ottiene una copia del del dato in bacheca
    // se vengono rispettati i controlli di identità
    @Override
    public E get(String passw, E dato)
            throws NullPointerException, WrongPasswordException, NoCategoryException, DataNotFoundException {

        if (passw == null)
            throw new NullPointerException("Invalid String passw in get");
        if (dato == null)
            throw new NullPointerException("Invalid dato in get");
        if (!(password.equals(passw)))
            throw new WrongPasswordException("Wrong password");
        String c = dato.getCategory();
        if (!Categorie.contains(c))
            throw new NoCategoryException("Category not found in collection");
        int n = Categorie.indexOf(c);
        if (!Arrayliste.get(n).getDati().contains(dato))
            throw new DataNotFoundException("Data not found in category");
        E copiadato = dato;
        return copiadato;
    }

    // Rimuove il dato dalla bacheca
    // se vengono rispettati i controlli di identità
    @Override
    public E remove(String passw, E dato) throws NullPointerException, WrongPasswordException {

        if (passw == null)
            throw new NullPointerException("Invalid String passw in remove");
        if (dato == null)
            throw new NullPointerException("Invalid dato in remove");
        if (!(password.equals(passw)))
            throw new WrongPasswordException("Wrong password");
        String categorydeldato = dato.getCategory();
        int n = Categorie.indexOf(categorydeldato);
        if (Arrayliste.get(n).getDati().remove(dato))
            return dato;
        else
            return null;
    }

    // Crea la lista dei dati in bacheca su una determinata categoria
    // se vengono rispettati i controlli di identità
    @Override
    public List<E> getDataCategory(String passw, String Category)
            throws NullPointerException, WrongPasswordException, NoCategoryException {

        if (passw == null)
            throw new NullPointerException("Invalid String passw in getDataCategory");
        if (Category == null)
            throw new NullPointerException("Invalid String Category in getDataCategory");
        if (!(password.equals(passw)))
            throw new WrongPasswordException("Wrong password");
        if (!(Categorie.contains(Category)))
            throw new NoCategoryException("Category not found in collection");
        int n = Categorie.indexOf(Category);
        List<E> DatiNellaCategoria = Arrayliste.get(n).getDati();
        return DatiNellaCategoria;
    }

    // restituisce un iteratore (senza remove) che genera tutti i dati in
    // bacheca ordinati rispetto al numero di like.
    @Override
    public Iterator<E> getIterator(String passw) throws NullPointerException, WrongPasswordException {
        if (passw == null)
            throw new NullPointerException("Invalid String passw in getIteretor");
        if (!(password.equals(passw)))
            throw new WrongPasswordException("Wrong password");
        return new NewIterator();
    }

    // Aggiunge un like a un dato
    @Override
    public void insertLike(String friend, E data)
            throws NullPointerException, DataNotFoundException, PermessDeniedException, NoCategoryException {

        if (friend == null)
            throw new NullPointerException("Invalid String friend in insertLike");
        if (data == null)
            throw new NullPointerException("Invalid data in insertLike");
        // controllo che la categoria associato al dato "data" sia presente nella
        // collezione
        String d = data.getCategory();
        if (!(Categorie.contains(d))) {
            throw new NoCategoryException("Category not found in collection");
        }

        // controllo che l'ArrayList identificato dalla categoria contenga il dato
        // "data"
        int n = Categorie.indexOf(d);
        if (!(Arrayliste.get(n).getDati().contains(data)))
            throw new DataNotFoundException("Data not found in collection");

        // controllo che friend sia iscritto alla categoria associata al dato
        if (!(Arrayliste.get(n).getFriends().contains(friend)))
            throw new PermessDeniedException("Friend is not in data's category");
        ArrayList<E> listadati = Arrayliste.get(n).getDati();
        listadati.get(listadati.indexOf(data)).AddLike();
    }

    // Legge un dato condiviso
    // restituisce un iteratore (senza remove) che genera tutti i dati in
    // bacheca condivisi.
    @Override
    public Iterator<E> getFriendIterator(String friend) throws NullPointerException, NoOneCategoryException {
        if (friend == null)
            throw new NullPointerException("Invalid String friend in getFriendIteretor");
        int I = 0;
        int trovato = 0;
        ArrayList<E> arraydati = new ArrayList<>();
        for (I = 0; I < Categorie.size(); I++) {
            if (Arrayliste.get(I).getFriends().contains(friend)) {
                trovato = trovato + 1;
                arraydati.addAll(Arrayliste.get(I).getDati());
            }
        }
        if (trovato == 0)
            throw new NoOneCategoryException("Friend non iscritto ad alcuna categoria");
        if (arraydati.size() == 0)
            return null;
        return new NewIteratorFriend(arraydati);
    }

    protected class NewComp implements Comparator<E> {

        // nuovo comparatore che fa ovverride del metodo compare secondo il metodo di
        // ordinamento scelto

        @Override
        public int compare(E dato1, E dato2) {
            if (dato1.getLike() > dato2.getLike())
                return -1;
            if (dato1.getLike() <= dato2.getLike())
                return 1;
            else
                return 0;
        }
    }

    protected class NewIterator implements Iterator<E> {

        ArrayList<E> listadati = new ArrayList<E>();
        int Index = 0;

        public NewIterator() {
            int I = 0;
            for (I = 0; I < Categorie.size(); I++) {
                listadati.addAll(Arrayliste.get(I).getDati());
            }
            listadati.sort(new NewComp()); // ordino la lista utilizzando il comparatore che mi sono definito
                                           // precedentemente

        }

        @Override
        public boolean hasNext() {
            return Index < listadati.size();
        }

        @Override
        public E next() {
            return listadati.get(Index++);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    protected class NewIteratorFriend implements Iterator<E> {

        ArrayList<E> listadatibacheca = new ArrayList<E>();
        int Index = 0;

        public NewIteratorFriend(ArrayList<E> A) {
            listadatibacheca = A;
        }

        @Override
        public boolean hasNext() {
            return Index < listadatibacheca.size();
        }

        @Override
        public E next() {
            return listadatibacheca.get(Index++);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}