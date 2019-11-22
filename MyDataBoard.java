import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Comparator;

public class MyDataBoard<E extends Data> implements DataBoard<E> {

    // AF:<{categoria_0,...,categoria_k},Password> tali che f(categoria_i) ->
    // <dato_0,...,dato_j> ||
    // {categoria_0,...,categoria_k} tali che f(categoria_i)-><amico_0,...,amico_j>
    // se e solo se Password
    // coincide con la Password del proprietario
    // con 0<=i<=k

    // RI: passw!=null && HashMap1!=null && for all i appartenenti ad Hashmap1.Keyset()
    // i!=null && for all i appartenenti ad Hahmap1.KeySet() HashMap1.get(i)!=null &&
    // for all i appartenenti ad HashMap1.KeySet HashMap1.get(i).ArrayList()!=null dove
    // HashMap1.get(i).ArrayList() restiruisce la lista ListaDati(L_1,...,L_n) tale che
    // for all 0<=j<=n get(Lj)!=null &&
    // HashMap2!=null && for all i appartenenti ad Hashmap2.Keyset() tale che
    // i!=null && for all i appartenenti ad Hahmap2.KeySet() HashMap2.get(i)!=null &&
    // for all i appartenenti ad HashMap2.KeySet HashMap2.get(i).ArrayList()!=null dove
    // HashMap2.get(i).ArrayList() restiruisce la lista ListaCategorie(L_1,...,L_n) tale che
    // for all 0<=j<=n get(Lj)!=null

    private String password;
    private HashMap<String, ArrayList<E>> CategorieDati;
    private HashMap<String, ArrayList<String>> CategorieListeAmici;
    private ArrayList<String> Arraycategorie;

    public MyDataBoard(String passw) throws NullPointerException {
        if (passw == null)
            throw new NullPointerException();
        password = passw;
        CategorieDati = new HashMap<>();
        CategorieListeAmici = new HashMap<>();
        Arraycategorie = new ArrayList<String>();
    }

    // Crea l’identità una categoria di dati
    @Override
    public void createCategory(String Category, String passw) throws NullPointerException, WrongPasswordException {
        if (Category == null)
            throw new NullPointerException();
        if (passw == null)
            throw new NullPointerException();
        if (!(password.equals(passw)))
            throw new WrongPasswordException();
        String NewCategory = Category;
        CategorieDati.putIfAbsent(NewCategory, new ArrayList<E>());
        CategorieListeAmici.putIfAbsent(NewCategory, new ArrayList<String>());
        Arraycategorie.add(Category);
    }

    // Rimuove l’identità una categoria di dati
    @Override
    public void removeCategory(String Category, String passw) throws NullPointerException, WrongPasswordException {
        if (Category == null)
            throw new NullPointerException();
        if (passw == null)
            throw new NullPointerException();
        if (!(password.equals(passw)))
            throw new WrongPasswordException();
        // .remove() rimuove dall'ArrayList l' argomento passato solo se presente
        CategorieDati.remove(Category);
        CategorieListeAmici.remove(Category);
        Arraycategorie.remove(Category);
    }

    // Aggiunge un amico ad una categoria di dati
    @Override
    public void addFriend(String Category, String passw, String friend)
            throws NullPointerException, WrongPasswordException, NoCategoryException {
        if (Category == null)
            throw new NullPointerException();
        if (passw == null)
            throw new NullPointerException();
        if (friend == null)
            throw new NullPointerException();
        if (!(password.equals(passw)))
            throw new WrongPasswordException();
        if (!(CategorieDati.containsKey(Category)))
            throw new NoCategoryException();
        CategorieListeAmici.get(Category).add(friend); // la add aggiunge solo se non e` gia presente
    }

    // rimuove un amico ad una categoria di dati
    @Override
    public void removeFriend(String Category, String passw, String friend)
            throws NullPointerException, WrongPasswordException, NoCategoryException {
        if (Category == null)
            throw new NullPointerException();
        if (passw == null)
            throw new NullPointerException();
        if (friend == null)
            throw new NullPointerException();
        if (!(password.equals(passw)))
            throw new WrongPasswordException();
        if (!(CategorieDati.containsKey(Category)))
            throw new NoCategoryException();
        CategorieListeAmici.get(Category).remove(friend);
    }

    // Inserisce un dato in bacheca
    // se vengono rispettati i controlli di identità
    @Override
    public boolean put(String passw, E dato, String categoria)
            throws NullPointerException, WrongPasswordException, NoCategoryException {
        if (categoria == null)
            throw new NullPointerException();
        if (passw == null)
            throw new NullPointerException();
        if (dato == null)
            throw new NullPointerException();
        if (!(password.equals(passw)))
            throw new WrongPasswordException();
        if (!(CategorieDati.containsKey(categoria)))
            throw new NoCategoryException();
        dato.setCategory(categoria);
        return CategorieDati.get(categoria).add(dato);
    }

    // Ottiene una copia del del dato in bacheca
    // se vengono rispettati i controlli di identità
    @Override
    public E get(String passw, E dato)
            throws NullPointerException, WrongPasswordException, NoCategoryException, DataNotFoundException {

        if (passw == null)
            throw new NullPointerException();
        if (dato == null)
            throw new NullPointerException();
        if (!(password.equals(passw)))
            throw new WrongPasswordException();

        String c = dato.getCategory();
        if (!(CategorieDati.containsKey(c)))
            throw new NoCategoryException();
        if (!(CategorieDati.get(c).contains(dato)))
            throw new DataNotFoundException();
        E copiadato = dato;
        return copiadato;
    }

    // Rimuove il dato dalla bacheca
    // se vengono rispettati i controlli di identità
    @Override
    public E remove(String passw, E dato) throws NullPointerException, WrongPasswordException {

        if (passw == null)
            throw new NullPointerException();
        if (dato == null)
            throw new NullPointerException();
        if (!(password.equals(passw)))
            throw new WrongPasswordException();
        String categorydeldato = dato.getCategory();
        if (CategorieDati.get(categorydeldato).remove(dato))
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
            throw new NullPointerException();
        if (Category == null)
            throw new NullPointerException();
        if (!(password.equals(passw)))
            throw new WrongPasswordException();
        if (!(CategorieDati.containsKey(Category)))
            throw new NoCategoryException();
        List<E> DatiNellaCategoria = CategorieDati.get(Category);
        return DatiNellaCategoria;
    }

    // restituisce un iteratore (senza remove) che genera tutti i dati in
    // bacheca ordinati rispetto al numero di like.
    @Override
    public Iterator<E> getIterator(String passw) throws NullPointerException, WrongPasswordException {
        if (passw == null)
            throw new NullPointerException();
        if (!(password.equals(passw)))
            throw new WrongPasswordException();
        return new NewIterator();
    }

    // Aggiunge un like a un dato
    @Override
    public void insertLike(String friend, E data)
            throws NullPointerException, DataNotFoundException, PermissionDeniedException, NoCategoryException {

        if (friend == null)
            throw new NullPointerException();
        if (data == null)
            throw new NullPointerException();
        // controllo che la categoria associato al dato "data" sia presente nella
        // collezione
        String d = data.getCategory();
        if (!(CategorieDati.containsKey(d))) {
            throw new NoCategoryException();
        }

        // controllo che l'ArrayList identificato dalla categoria contenga il dato
        // "data"
        if (!(CategorieDati.get(d).contains(data)))
            throw new DataNotFoundException();

        // controllo che friend sia iscritto alla categoria associata al dato
        if (!(CategorieListeAmici.get(d).contains(friend)))
            throw new PermissionDeniedException();
        ArrayList<E> listadati = CategorieDati.get(d);
        listadati.get(listadati.indexOf(data)).AddLike();
    }

    // Legge un dato condiviso
    // restituisce un iteratore (senza remove) che genera tutti i dati in
    // bacheca condivisi.
    @Override
    public Iterator<E> getFriendIterator(String friend) throws NullPointerException, NoOneCategoryException {
        if (friend == null)
            throw new NullPointerException();
        int I = 0;
        int trovato = 0;
        ArrayList<E> arraydati = new ArrayList<>();
        for (I = 0; I < Arraycategorie.size(); I++) {
            if ((CategorieListeAmici.get(Arraycategorie.get(I))).contains(friend)) {
                trovato = trovato + 1;
                arraydati.addAll(CategorieDati.get(Arraycategorie.get(I)));
            }
        }
        if (trovato == 0)
            throw new NoOneCategoryException();
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
            for (I = 0; I < Arraycategorie.size(); I++) {
                listadati.addAll(CategorieDati.get(Arraycategorie.get(I)));
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