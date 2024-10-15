package tp.ensa.ma.stargallery.beans;
public class Star {
    private int id;
    private String name;
    private int imgResId; // Identifiant de la ressource de l'image
    private float star; // Note donnée par l'utilisateur
    private static int comp = 0;

    public Star(String name, int imgResId, float star) {
        this.id = ++comp;
        this.name = name;
        this.imgResId = imgResId;
        this.star = star; // Note initiale
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getImgResId() {
        return imgResId; // Récupérer l'ID de la ressource
    }

    public float getStar() {
        return star; // Récupérer la note
    }

    public void setStar(float star) {
        this.star = star; // Mettre à jour la note
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }

    public void setName(String name) {
        this.name = name;
    }

}
