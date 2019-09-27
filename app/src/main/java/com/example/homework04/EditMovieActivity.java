package com.example.homework04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

public class EditMovieActivity extends AppCompatActivity {

    private Spinner genreSpinner;
    private static   String[] paths = {"Action", "Animation", "Comedy", "Documentary", "Family", "Horror",
            " Crime and Others"};
    private String movieName , movieDescription , movieGenre , movieImdb ;
    private int  movieRating , movieYear ;
    private int genrePosition ;
    private SeekBar movieRatingSeekBar ;
    private EditText movieNameEditText , movieDescriptionEditText , movieYearEditText , movieImdbEditText ;
    private Button saveChanges_button ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);

        setTitle("Edit Movie");

        Movie movie =(Movie) getIntent().getExtras().getSerializable(MainActivity.EDIT_MOVIE);

        //finding the genre position

        for(int i = 0 ; i < paths.length ; i++)
        {
            if(paths[i].equals(movie.genre)) genrePosition = i ;
            Toast.makeText(getApplicationContext() , "selected"+genrePosition+"",Toast.LENGTH_SHORT).show();

        }


        saveChanges_button = findViewById(R.id.button_saveChanges);
        genreSpinner = findViewById(R.id.spinner_genreDropDown);
        movieNameEditText = findViewById(R.id.editText_Name);
        movieDescriptionEditText = findViewById(R.id.editText2_description);
        movieYearEditText = findViewById(R.id.editText3_Year);
        movieImdbEditText = findViewById(R.id.editText4_ImdbLink);

        movieRatingSeekBar = findViewById(R.id.seekBar_Rating);
        movieRatingSeekBar.setMax(5);

        //setting default values from movies object

        movieNameEditText.setText(movie.name);
        movieDescriptionEditText.setText(movie.description);
        movieYearEditText.setText(movie.year+"") ;
        movieImdbEditText.setText(movie.imDb);
        movieRatingSeekBar.setProgress(movie.rating);





        //DropDown logic
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(EditMovieActivity.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genreSpinner.setAdapter(adapter);
        genreSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext() , position+"",Toast.LENGTH_SHORT).show();
                movieGenre = paths[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        genreSpinner.setSelection(genrePosition);

        //rating seekbar

        movieRatingSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                movieRating = progress ;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        //Save changes button

        //addMovie button

        saveChanges_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //take values from form
                movieName = movieNameEditText.getText().toString();
                movieDescription = movieDescriptionEditText.getText().toString();
                movieYear = Integer.parseInt(movieYearEditText.getText().toString());
                movieImdb = movieImdbEditText.getText().toString();
                Log.d("Change " , new Movie(movieName,movieDescription,movieGenre,movieRating,movieYear,movieImdb)+"");

                Intent goBackIntent = new Intent();
                goBackIntent.putExtra(MainActivity.EDIT_MOVIE , new Movie(movieName,movieDescription,movieGenre,movieRating,movieYear,movieImdb));
                setResult(RESULT_OK , goBackIntent);
                finish();

            }
        });

    }
}
