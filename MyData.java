public class MyData implements Data {

    // AF: (Category,NamePost,NumeroDiLike)=<Category,NamePost,NumeroDiLike>
    // RI: Category!=null && NamePost!=null && NumeroDiLike!=null

    private String Category;
    private String NamePost;
    private Integer NumeroDiLike;

    public MyData(String categoria, String nomepost, Integer numlike) throws NullPointerException {

        if (categoria == null)
            throw new NullPointerException("Invalid Category");
        if (nomepost == null)
            throw new NullPointerException("Invalid NamePost");
        if (numlike == null)
            throw new NullPointerException("Invalid NumeroDiLike");
        Category = categoria;
        NamePost = nomepost;
        NumeroDiLike = numlike;
    }

    public void Display() {
        System.out.println(getCategory() + ", " + getNamePost() + ", " + getLike());
    }

    // Associa una categoria al dato
    public void setCategory(String Categoria) throws NullPointerException {
        if (Categoria == null)
            throw new NullPointerException("Invalid Categoria");
        Category = Categoria;

    }

    // Restituisce il numero di like associati al dato
    public Integer getLike() {

        Integer NumeroLike = new Integer(NumeroDiLike);
        return NumeroLike;
    }

    // Restituisce la categoria del dato
    public String getCategory() {

        String categoriapost = new String(Category);
        return categoriapost;

    }

    // Incrementa il valore del numero dei like
    public void AddLike() {
        NumeroDiLike = NumeroDiLike + 1;
    }

    // Restituisce il nome del post
    public String getNamePost() {

        String NomeDelPost = new String(NamePost);
        return NomeDelPost;
    }
}