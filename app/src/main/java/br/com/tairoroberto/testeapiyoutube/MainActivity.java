package br.com.tairoroberto.testeapiyoutube;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.api.services.youtube.YouTube;


public class MainActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    //comando para ver a fingerkey do android do mau pc
    //keytool -list -v -keystore ~/.android/debug.keystore -alias androiddebugkey -storepass android -keypass android

    //Key de acesso a API do Youtube
    private static final String API_KEY = "AIzaSyD4Js_iq2RGckd0qKFktvBQVKHhjIvnHX4";

    //Id do video do youtube que será carregado
    //https://www.youtube.com/watch?v=VZoImzA5iO4
    private String id_video = "VZoImzA5iO4";

    private YouTubePlayerView youTubePlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        youTubePlayer = (YouTubePlayerView)findViewById(R.id.youTubePalyer);
        youTubePlayer.initialize(API_KEY,this);
    }

    public void callYouTube(View view){
        //Uri do video
        Uri uri = Uri.parse("https://www.youtube.com/watch?v=VZoImzA5iO4");

        //pega o paramentro que separa o id do video
        uri = Uri.parse("vnd.youtube:"+uri.getQueryParameter("v"));

        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //quando tem sucesso
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean loadAgain) {
        Log.i("Script","Teste com api do YouTube Raiz 1");
        //verfica se vide já foi carregado
        if (!loadAgain){
            Log.i("Script","Teste com api do YouTube Raiz 2");
            youTubePlayer.cueVideo(id_video);
        }
    }

    //quando falha
    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this,"onInitializationFailure()",Toast.LENGTH_SHORT).show();
    }
}
