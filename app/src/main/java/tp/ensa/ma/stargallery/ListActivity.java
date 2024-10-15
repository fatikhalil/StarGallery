package tp.ensa.ma.stargallery;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import tp.ensa.ma.stargallery.adapter.StarAdapter;
import tp.ensa.ma.stargallery.beans.Star;
import tp.ensa.ma.stargallery.service.StarService;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StarAdapter starAdapter;
    private StarService starService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Configuration de la Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // Configure la Toolbar comme Action Bar

        // Initialisation du service et chargement des étoiles
        starService = StarService.getInstance();
        loadStars();

        // Configuration du RecyclerView
        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        starAdapter = new StarAdapter(this, starService.findAll());
        recyclerView.setAdapter(starAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu); // Utiliser le bon menu ici

        // Initialiser la barre de recherche
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        // Ajout d'un listener pour détecter les changements de texte dans la barre de recherche
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                starAdapter.filter(query); // Filtrer la liste en fonction du texte soumis
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                starAdapter.filter(newText); // Filtrer la liste en fonction du texte saisi
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_share_app) {
            shareApplication();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Méthode pour partager l'application
    private void shareApplication() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Découvrez cette super application : [URL de votre application]");
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "Partager l'application via"));
    }

    // Méthode pour charger les stars
    private void loadStars() {
        starService.create(new Star("Cemre Baysal", R.drawable.act1, 0f));
        starService.create(new Star("Murat Yildirim", R.drawable.act2, 5.0f));
        starService.create(new Star("Afra Saracooglu", R.drawable.act3, 3.5f));
        starService.create(new Star("Seckin Ozdemir", R.drawable.act4, 2.5f));
        starService.create(new Star("Deniz Baysal", R.drawable.act5, 4.5f));
        starService.create(new Star("Baris Arduc", R.drawable.act6, 2.5f));
        starService.create(new Star("Burcu Ozberk", R.drawable.act7, 4.f));
        starService.create(new Star("Eda Ece", R.drawable.act8, 3f));
        starService.create(new Star("Kaan Urgancioglu", R.drawable.act9, 4.5f));
        starService.create(new Star("Hazal Filiz Kucukkose", R.drawable.act10, 0f));
    }
}
