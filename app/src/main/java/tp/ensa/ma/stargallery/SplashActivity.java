package tp.ensa.ma.stargallery;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash); // Assurez-vous que ce fichier XML existe

        // Lier l'ImageView à partir de la mise en page
        logo = findViewById(R.id.logo);

        // Appliquer les animations sur le logo
        startLogoAnimations();

        // Utiliser un Handler pour rediriger vers ListActivity après 5 secondes
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Démarrer ListActivity
                Intent intent = new Intent(SplashActivity.this, ListActivity.class);
                startActivity(intent);

                // Terminer l'activité actuelle
                finish();
            }
        }, 10000); // 10000 millisecondes = 5 secondes
    }

    // Méthode pour démarrer les animations sur le logo
    private void startLogoAnimations() {
        // Rotation de 360° en 8 secondes
        logo.animate().rotation(360f).setDuration(8000).start();

        // Mise à l'échelle à 50% en 8 secondes
        logo.animate().scaleX(0.5f).scaleY(0.5f).setDuration(8000).start();

        // Réduction progressive de la taille jusqu'à disparition (échelle à 0) et translation en même temps
        logo.animate()
                .translationYBy(1000f)  // Translation vers le bas de 1000 pixels
                .scaleX(0f)              // Réduction de la taille sur l'axe X jusqu'à 0
                .scaleY(0f)              // Réduction de la taille sur l'axe Y jusqu'à 0
                .setDuration(18000)       // Les deux animations dureront 18 secondes
                .start();

    }


    @Override
    protected void onPause() {
        super.onPause();
        // Terminer cette activité lorsqu'elle est mise en pause
        finish();
    }
}

