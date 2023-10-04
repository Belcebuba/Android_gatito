package com.example.gatito;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;

import com.example.gatito.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private final List<int[]> combinationList= new ArrayList<>();
    private int[] boxPositions={0,0,0,0,0,0,0,0,0};
    private int playerTurn=1;
    private int totalSelectedBoxes=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        combinationList.add(new int[]{0,1,2});
        combinationList.add(new int[]{3,4,5});
        combinationList.add(new int[]{6,7,8});
        combinationList.add(new int[]{0,3,6});
        combinationList.add(new int[]{1,4,7});
        combinationList.add(new int[]{2,5,8});
        combinationList.add(new int[]{2,4,6});
        combinationList.add(new int[]{0,4,8});

        String getPlayerOneName=getIntent().getStringExtra("jugador 1");
        String getPlayerTwoName=getIntent().getStringExtra("jugador 2");

        binding.playerOneName.setText(getPlayerOneName);
        binding.playerTwoName.setText(getPlayerTwoName);

        binding.image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(0))
                {
                    performAction((ImageView) view,0);
                }
            }
        });

        binding.image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(1))
                {
                    performAction((ImageView) view,1);
                }
            }
        });
        binding.image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(2))
                {
                    performAction((ImageView) view,2);
                }
            }
        });
        binding.image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(3))
                {
                    performAction((ImageView) view,3);
                }
            }
        });
        binding.image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(4))
                {
                    performAction((ImageView) view,4);
                }
            }
        });
        binding.image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(5))
                {
                    performAction((ImageView) view,5);
                }
            }
        });
        binding.image7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(6))
                {
                    performAction((ImageView) view,6);
                }
            }
        });
        binding.image8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(7))
                {
                    performAction((ImageView) view,7);
                }
            }
        });
        binding.image9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(8))
                {
                    performAction((ImageView) view,8);
                }
            }
        });
    }
    private void performAction(ImageView imageView, int selectedBoxPosition) {
        Log.d("gatito", "Setting image at position: " + selectedBoxPosition + " for player: " + playerTurn);


    boxPositions[selectedBoxPosition] = playerTurn;
        if (playerTurn == 1) {
            imageView.setImageResource(R.drawable.ximage);
            if (checkResults())
            {
                ResultDialog resultDialog=new ResultDialog(MainActivity.this, binding.playerOneName.getText().toString()+
                        "es el ganador", MainActivity.this);
                resultDialog.setCancelable(false);
                resultDialog.show();

            }
            else if (totalSelectedBoxes==9)
            {
                ResultDialog resultDialog=new ResultDialog(MainActivity.this, "empate", MainActivity.this);
                resultDialog.setCancelable(false);
                resultDialog.show();

            }
            else
            {
                changePlayerTurn(2);
                totalSelectedBoxes++;
            }
        }
            else
            {
                imageView.setImageResource(R.drawable.oimage);
                if (checkResults())
                {
                    ResultDialog resultDialog=new ResultDialog(MainActivity.this, binding.playerTwoName.getText().toString()+
                            "es el ganador", MainActivity.this);
                    resultDialog.setCancelable(false);
                    resultDialog.show();

                }
                else if (totalSelectedBoxes==9)
                {
                    ResultDialog resultDialog=new ResultDialog(MainActivity.this, "empate", MainActivity.this);
                    resultDialog.setCancelable(false);
                    resultDialog.show();

                }

                else
                {
                    changePlayerTurn(1);
                    totalSelectedBoxes++;
                }
            }
        }

    private  void  changePlayerTurn(int currentPlayerTurn)
    {
        playerTurn= currentPlayerTurn;
        if (playerTurn==1)
        {
            binding.playerOneLayout.setBackgroundResource(R.drawable.bordenegro);
            binding.playerTwoLayout.setBackgroundResource(R.drawable.cajablanca);
        }
        else
        {
            binding.playerTwoLayout.setBackgroundResource(R.drawable.bordenegro);
            binding.playerOneLayout.setBackgroundResource(R.drawable.cajablanca);
        }

    }
    private  boolean checkResults()
    {
        boolean response= false;
        for (int i=0;i <combinationList.size();i++)
        {
            final  int[] combination = combinationList.get(i);
            if (boxPositions[combination[0]]==playerTurn && boxPositions[combination[1]]==playerTurn&&
            boxPositions[combination[2]]==playerTurn)
            {
                response=true;
            }
        }
        return response;
    }
    private  boolean isBoxSelectable(int boxPosition)
    {
        boolean response=false;
        if(boxPositions[boxPosition]==0)
        {
            response=true;
        }
        return response;


    }
    public   void  restartMatch()
    {
        boxPositions=new int[]{0,0,0,0,0,0,0,0,0};
        playerTurn=1;
        totalSelectedBoxes=1;
        binding.image1.setImageResource(R.drawable.cajablanca);
        binding.image2.setImageResource(R.drawable.cajablanca);
        binding.image3.setImageResource(R.drawable.cajablanca);
        binding.image4.setImageResource(R.drawable.cajablanca);
        binding.image5.setImageResource(R.drawable.cajablanca);
        binding.image6.setImageResource(R.drawable.cajablanca);
        binding.image7.setImageResource(R.drawable.cajablanca);
        binding.image8.setImageResource(R.drawable.cajablanca);
        binding.image9.setImageResource(R.drawable.cajablanca);
    }
}