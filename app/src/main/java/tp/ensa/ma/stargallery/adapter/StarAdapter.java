package tp.ensa.ma.stargallery.adapter;

import tp.ensa.ma.stargallery.R;
import tp.ensa.ma.stargallery.beans.Star;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StarAdapter extends RecyclerView.Adapter<StarAdapter.StarViewHolder> {

    private Context context;
    private List<Star> stars; // Liste actuelle affichée (filtrée ou non)
    private List<Star> originalStars; // Liste originale non filtrée

    public StarAdapter(Context context, List<Star> stars) {
        this.context = context;
        this.stars = stars;
        this.originalStars = new ArrayList<>(stars); // Faire une copie de la liste originale
    }

    @NonNull
    @Override
    public StarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.star_item, parent, false);
        return new StarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StarViewHolder holder, int position) {
        Star star = stars.get(position);
        holder.name.setText(star.getName());
        holder.stars.setRating(star.getStar()); // Affiche la note actuelle

        // Charger l'image à partir des ressources locales
        holder.img.setImageResource(star.getImgResId());

        // Définir un listener pour mettre à jour la note lorsque l'utilisateur interagit avec le RatingBar
        holder.stars.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            if (fromUser) {
                star.setStar(rating); // Mettre à jour la note dans l'objet Star
            }
        });

        // Ajouter un OnClickListener pour ouvrir un popup de notation au clic sur l'élément
        holder.itemView.setOnClickListener(v -> showRatingDialog(star, holder));

        // OnClickListener pour l'icône de partage
        holder.shareIcon.setOnClickListener(v -> shareStar(star));
    }

    // Méthode pour partager une star
    private void shareStar(Star star) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Découvrez cette star : " + star.getName() + " avec une note de " + star.getStar() + " étoiles !");
        sendIntent.setType("text/plain");
        context.startActivity(Intent.createChooser(sendIntent, "Partager via"));
    }

    // Méthode pour afficher une boîte de dialogue permettant de changer la note
    private void showRatingDialog(Star star, StarViewHolder holder) {
        // Créer une instance de l'AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflater le layout du dialog_rate_star.xml
        View dialogView = inflater.inflate(R.layout.dialog_rate_star, null);
        builder.setView(dialogView);

        // Récupérer les éléments de la boîte de dialogue
        TextView starName = dialogView.findViewById(R.id.star_name);
        ImageView starImage = dialogView.findViewById(R.id.star_image);
        RatingBar ratingBar = dialogView.findViewById(R.id.rating_bar);
        EditText numInput = dialogView.findViewById(R.id.num_input); // Champ de saisie de la note

        // Remplir les informations de l'acteur
        starName.setText(star.getName());
        starImage.setImageResource(star.getImgResId());
        ratingBar.setRating(star.getStar());

        // Définir les boutons Annuler et Valider
        builder.setNegativeButton("Annuler", (dialog, which) -> dialog.dismiss());
        builder.setPositiveButton("Valider", (dialog, which) -> {
            // Récupérer la note entrée par l'utilisateur
            String inputText = numInput.getText().toString();
            if (!inputText.isEmpty()) {
                int newRating = Integer.parseInt(inputText);

                // Vérifier que la note est entre 1 et 5
                if (newRating >= 1 && newRating <= 5) {
                    ratingBar.setRating(newRating); // Mettre à jour le RatingBar avec la nouvelle note
                    star.setStar(newRating);        // Mettre à jour la note dans l'objet Star
                    holder.stars.setRating(newRating); // Mettre à jour l'affichage des étoiles
                } else {
                    numInput.setError("Veuillez entrer une note entre 1 et 5");
                }
            }
            dialog.dismiss();
        });

        // Créer et afficher la boîte de dialogue
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return stars.size();
    }

    // Méthode pour filtrer les éléments en fonction de la recherche
    public void filter(String text) {
        List<Star> filteredList = new ArrayList<>();
        if (text.isEmpty()) {
            filteredList.addAll(originalStars); // Si la recherche est vide, afficher tous les éléments
        } else {
            text = text.toLowerCase();
            for (Star star : originalStars) {
                if (star.getName().toLowerCase().contains(text)) {
                    filteredList.add(star);
                }
            }
        }
        stars.clear();
        stars.addAll(filteredList);
        notifyDataSetChanged(); // Mettre à jour la vue avec la nouvelle liste filtrée
    }

    public static class StarViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        RatingBar stars;
        ImageView shareIcon; // Ajout de l'icône de partage

        public StarViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            stars = itemView.findViewById(R.id.stars);
            shareIcon = itemView.findViewById(R.id.iconshare); // Initialisation de l'icône de partage
        }
    }
}
