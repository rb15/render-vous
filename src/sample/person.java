package sample;

public class person {
    private String nom;
    private String prenom;
    private String date;
    private String ville;
    private Integer id;
    public person(String nom, String prenom, String date, String ville) {
        this.nom = nom;
        this.prenom = prenom;
        this.date = date;
        this.ville = ville;
    }
    public person(String nom, String prenom, String date, String ville,Integer id) {
        this.nom = nom;
        this.prenom = prenom;
        this.date = date;
        this.ville = ville;
        this.id=id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}