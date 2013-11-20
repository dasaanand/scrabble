package scrabble.pck;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class scrabble extends Activity {
    /** Called when the activity is first created. */
	
	//static final int BluetoothAvailability = 1;
	byte MAX_DIMN = 11;
	public byte i_v_newBoard [][] = new byte[MAX_DIMN][MAX_DIMN];
	
	                                                  
	private ImageButton playB, passB, rackB;
	private Integer[] cellid = { 
			R.drawable.w3 ,R.drawable.l1, R.drawable.l2, R.drawable.l1, R.drawable.l1 ,R.drawable.w3, R.drawable.l1, 
			R.drawable.l1, R.drawable.l2, R.drawable.l1, R.drawable.w3,
			R.drawable.l1, R.drawable.w2, R.drawable.l1, R.drawable.l1, R.drawable.l3, R.drawable.l1, R.drawable.l3,
			R.drawable.l1, R.drawable.l1, R.drawable.w2, R.drawable.l1,
			R.drawable.l2, R.drawable.l1, R.drawable.w2, R.drawable.l1, R.drawable.l1, R.drawable.l2, R.drawable.l1, 
			R.drawable.l1, R.drawable.w2, R.drawable.l1, R.drawable.l2,
			R.drawable.l1, R.drawable.l1, R.drawable.l1, R.drawable.l3, R.drawable.l1, R.drawable.l1, R.drawable.l1, 
			R.drawable.l3, R.drawable.l1, R.drawable.l1, R.drawable.l1, 
			R.drawable.l1, R.drawable.l3, R.drawable.l1, R.drawable.l1, R.drawable.l2, R.drawable.l1, R.drawable.l2,
			R.drawable.l1, R.drawable.l1, R.drawable.l3, R.drawable.l1,
			R.drawable.w3, R.drawable.l1, R.drawable.l2, R.drawable.l1, R.drawable.l1, R.drawable.star, R.drawable.l1, 
			R.drawable.l1, R.drawable.l2, R.drawable.l1, R.drawable.w3,
			R.drawable.l1, R.drawable.l3, R.drawable.l1, R.drawable.l1, R.drawable.l2, R.drawable.l1, R.drawable.l2,
			R.drawable.l1, R.drawable.l1, R.drawable.l3, R.drawable.l1,
			R.drawable.l1, R.drawable.l1, R.drawable.l1, R.drawable.l3, R.drawable.l1, R.drawable.l1, R.drawable.l1, 
			R.drawable.l3, R.drawable.l1, R.drawable.l1, R.drawable.l1, 
			R.drawable.l2, R.drawable.l1, R.drawable.w2, R.drawable.l1, R.drawable.l1, R.drawable.l2, R.drawable.l1, 
			R.drawable.l1, R.drawable.w2, R.drawable.l1, R.drawable.l2,
			R.drawable.l1, R.drawable.w2, R.drawable.l1, R.drawable.l1, R.drawable.l3, R.drawable.l1, R.drawable.l3,
			R.drawable.l1, R.drawable.l1, R.drawable.w2, R.drawable.l1,
			R.drawable.w3 ,R.drawable.l1, R.drawable.l2, R.drawable.l1, R.drawable.l1 ,R.drawable.w3, R.drawable.l1, 
			R.drawable.l1, R.drawable.l2, R.drawable.l1, R.drawable.w3,
			};
   
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        /**** Play pass button activity generation ****/
        playB = (ImageButton) findViewById(R.id.playButton);
        passB = (ImageButton) findViewById(R.id.passButton);
        rackB = (ImageButton) findViewById(R.id.rack);
        
        final PlayPassOver playPassObj = new PlayPassOver();
 
        
        playB.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) {
                // Perform action on click
            	playPassObj.onPlay();
            	}
        });
        
        passB.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) {
                // Perform action on click
            	playPassObj.onPlay();
            	}
        });
       
        rackB.setOnClickListener(new View.OnClickListener() 
        {			
			public void onClick(View v) {
				// rack button
		    	Context context = getApplicationContext();
            	Toast.makeText(context, "Rack should be displayed", Toast.LENGTH_LONG).show();
            	

			}
		});
        
        
        /**** Board cell activity generation ****/
        GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new CellAdapter(this)); 
    }
    
    
    
    public class CellAdapter extends BaseAdapter 
    {
        private Context context;
        final WordFormation wordFormationObj = new WordFormation();

        public CellAdapter(Context c) 
        {
            context = c;
        }
 
        //---returns the number of images---
        public int getCount() 
        {
            return cellid.length;
        }
 
        //---returns the ID of an item--- 
        public Object getItem(int position) 
        {
            return position;
        }
 
        public long getItemId(int position) {
            return position;
        }
 
        //---returns an ImageView view---
        public View getView(final int position, View convertView, ViewGroup parent) 
        {
            final ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(context);
                imageView.setLayoutParams(new GridView.LayoutParams(28, 30));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(1, 0, 1, 0);
                
                imageView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                    	 //Toast.makeText(getBaseContext(), "pic" + (position + 1) + " selected",  Toast.LENGTH_SHORT).show();
                    	 wordFormationObj.position(position+1, imageView);
                    	 }
                });
            } else {
                imageView = (ImageView) convertView;
            }
            imageView.setImageResource(cellid[position]);
            
            return imageView;
        }
    } 
       
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.newGame:
            	Toast.makeText(this, "You pressed the NewGame!", Toast.LENGTH_LONG).show();
        	//	showDialog(BluetoothAvailability);
            	
            	BluetoothValidator bv = new BluetoothValidator();
            	bv.isBluetoothAdapterAvailable();

            	break;
            case R.id.quitGame:     
            	Toast.makeText(this, "You pressed the Quit Game!", Toast.LENGTH_LONG).show();
            	finish();
                break;
            case R.id.dictionary: 
            	Toast.makeText(this, "You pressed the Dictionary!", Toast.LENGTH_LONG).show();
                break;
            case R.id.help: 
            	Toast.makeText(this, "You pressed the help!", Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }
    
     int cell_click=0;
    
    public class WordFormation {
   	 Context context = getApplicationContext();
     int flag=0;
    
     final String alpha[]={"d","r","p","o","e","s","a"};
     final CharSequence[] items = {alpha[0],alpha[1],alpha[2],alpha[3],alpha[4],alpha[5],alpha[6] };
     int exist;
 	//Set its title

 	//builder(context).setTitle("Pick an item");
    	
    	public void position (int pos, final ImageView v1) {
    	final	int x,y;
       	 
    	int row = pos / 11;
    	int col = pos % 11;
		//Toast.makeText(context, "row" + row + "col" + col + " selected",  Toast.LENGTH_SHORT).show();

    	col--;
    	if(col == -1)
    	{
    		col = 10;
    		row --;
    	}
    	x = row;
    	y = col;
    	//Toast.makeText(context, "pic" + pos + " selected",  Toast.LENGTH_SHORT).show();
       	 
       		 
         final AlertDialog.Builder builder = new AlertDialog.Builder(scrabble.this);

     	//Set its title

     	builder.setTitle("Pick an item");
       	 
		// rack button
		builder.setItems(items, new DialogInterface.OnClickListener() {

    		// Click listener

    		public void onClick(DialogInterface dialog, int item) {
    			String ascii;
    			char c;
    	        Toast.makeText(getApplicationContext(),items[item], Toast.LENGTH_SHORT).show();
    	        
    	        if(i_v_newBoard[x][y] == 0)		//new cell
    	        {
    	        	  if(items[item]=="a"){
    	    	        	ascii="A";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.a);
    	    	        }
    	    	        
    	    	        if(items[item]=="b"){
    	    	        	ascii="B";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.b);
    	    	        }
    	    	        if(items[item]=="c"){
    	    	        	ascii="C";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.c);
    	    	        }
    	    	        if(items[item]=="d"){
    	    	        	ascii="D";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.d);
    	    	        }
    	    	        if(items[item]=="e"){
    	    	        	ascii="E";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.e);
    	    	        }
    	    	        if(items[item]=="f"){
    	    	        	ascii="F";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.f);
    	    	        }
    	    	        if(items[item]=="g"){
    	    	        	ascii="G";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.g);
    	    	        }
    	    	        if(items[item]=="h"){
    	    	        	ascii="H";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.h);
    	    	        }
    	    	        if(items[item]=="i"){
    	    	        	ascii="I";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.i);
    	    	        }
    	    	        if(items[item]=="j"){
    	    	        	ascii="J";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.j);
    	    	        }
    	    	        if(items[item]=="k"){
    	    	        	ascii="K";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.k);
    	    	        }
    	    	        if(items[item]=="l"){
    	    	        	ascii="L";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.l);
    	    	        }
    	    	        if(items[item]=="m"){
    	    	        	ascii="M";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.m);
    	    	        }
    	    	        if(items[item]=="n"){
    	    	        	ascii="N";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.n);
    	    	        }
    	    	        if(items[item]=="o"){
    	    	        	ascii="O";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.o);
    	    	        }
    	    	        if(items[item]=="p"){
    	    	        	ascii="P";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.p);
    	    	        }
    	    	        if(items[item]=="q"){
    	    	        	ascii="Q";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.q);
    	    	        }
    	    	        if(items[item]=="r"){
    	    	        	ascii="R";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.r);
    	    	        }
    	    	        if(items[item]=="s"){
    	    	        	ascii="S";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.s);
    	    	        }
    	    	        if(items[item]=="t"){
    	    	        	ascii="T";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.t);
    	    	        }
    	    	        if(items[item]=="u"){
    	    	        	ascii="U";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.u);
    	    	        }
    	    	        if(items[item]=="v"){
    	    	        	ascii="V";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.v);
    	    	        }
    	    	        if(items[item]=="w"){
    	    	        	ascii="W";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.w);
    	    	        }
    	    	        if(items[item]=="x"){
    	    	        	ascii="X";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.x);
    	    	        }
    	    	        if(items[item]=="y"){
    	    	        	ascii="Y";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.y);
    	    	        }
    	    	        if(items[item]=="z"){
    	    	        	ascii="Z";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.z);
    	    	        }
    	    	        items[item]=" ";
    	        }
    	        else
    	        {
    	        	if(items[item] != " ")			//replacement with new character
    	        	{
    	        		byte[] bytes = new byte[1];
	        			bytes[0] = (byte) i_v_newBoard[x][y];
	        			String letter = new String(bytes);
	        			letter = letter.toLowerCase();
	    	        	
    	        		if(items[item]=="a"){
    	    	        	ascii="A";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.a);
    	    	        }
    	    	        
    	    	        if(items[item]=="b"){
    	    	        	ascii="B";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.b);
    	    	        }
    	    	        if(items[item]=="c"){
    	    	        	ascii="C";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.c);
    	    	        }
    	    	        if(items[item]=="d"){
    	    	        	ascii="D";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.d);
    	    	        }
    	    	        if(items[item]=="e"){
    	    	        	ascii="E";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.e);
    	    	        }
    	    	        if(items[item]=="f"){
    	    	        	ascii="F";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.f);
    	    	        }
    	    	        if(items[item]=="g"){
    	    	        	ascii="G";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.g);
    	    	        }
    	    	        if(items[item]=="h"){
    	    	        	ascii="H";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.h);
    	    	        }
    	    	        if(items[item]=="i"){
    	    	        	ascii="I";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.i);
    	    	        }
    	    	        if(items[item]=="j"){
    	    	        	ascii="J";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.j);
    	    	        }
    	    	        if(items[item]=="k"){
    	    	        	ascii="K";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.k);
    	    	        }
    	    	        if(items[item]=="l"){
    	    	        	ascii="L";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.l);
    	    	        }
    	    	        if(items[item]=="m"){
    	    	        	ascii="M";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.m);
    	    	        }
    	    	        if(items[item]=="n"){
    	    	        	ascii="N";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.n);
    	    	        }
    	    	        if(items[item]=="o"){
    	    	        	ascii="O";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.o);
    	    	        }
    	    	        if(items[item]=="p"){
    	    	        	ascii="P";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.p);
    	    	        }
    	    	        if(items[item]=="q"){
    	    	        	ascii="Q";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.q);
    	    	        }
    	    	        if(items[item]=="r"){
    	    	        	ascii="R";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.r);
    	    	        }
    	    	        if(items[item]=="s"){
    	    	        	ascii="S";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.s);
    	    	        }
    	    	        if(items[item]=="t"){
    	    	        	ascii="T";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.t);
    	    	        }
    	    	        if(items[item]=="u"){
    	    	        	ascii="U";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.u);
    	    	        }
    	    	        if(items[item]=="v"){
    	    	        	ascii="V";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.v);
    	    	        }
    	    	        if(items[item]=="w"){
    	    	        	ascii="W";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.w);
    	    	        }
    	    	        if(items[item]=="x"){
    	    	        	ascii="X";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.x);
    	    	        }
    	    	        if(items[item]=="y"){
    	    	        	ascii="Y";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.y);
    	    	        }
    	    	        if(items[item]=="z"){
    	    	        	ascii="Z";
    	    	        	c=ascii.charAt(0);
    	    	        	i_v_newBoard[x][y]=(byte)c;
    	    	        	v1.setImageResource(R.drawable.z);
    	    	        }
	    	        	items[item] = letter;
	    	    		Toast.makeText(context, "letter:"+ letter,  Toast.LENGTH_SHORT).show();


    	        	}
    	        	else
    	        	{
    	        		byte[] bytes = new byte[1];
	        			bytes[0] = (byte) i_v_newBoard[x][y];
	        			String letter = new String(bytes);
	        			letter = letter.toLowerCase();
	    	        	items[item] = letter;
	    	        	i_v_newBoard[x][y]=0;

	    	        	
	    	        	switch(x)
	    	        	{
	    	        		case 1:
	    	        		case 10:
	    	        			switch(y)
	    	        			{
	    	        				case 0:
	    	        				case 5:
	    	        				case 10:
	    	    	    	        	v1.setImageResource(R.drawable.w3);
	    	    	    	        	break;
	    	        				case 1:
	    	        				case 3:
	    	        				case 4:
	    	        				case 6:
	    	        				case 7:
	    	        				case 9:
	    	    	    	        	v1.setImageResource(R.drawable.l1);
	    	    	    	        	break;
	    	        				case 2:
	    	        				case 8:
	    	    	    	        	v1.setImageResource(R.drawable.l2);
	    	    	    	        	break;
	    	        			}
	    	        	}
	    	        	
    	        	}
    	        }
    	      
    	    }
    		
    	});

     	
     	
    	AlertDialog alert = builder.create();

    	//display dialog box

    	alert.show();
       	 
       	/* new AlertDialog.Builder(scrabble.this) 
         .setTitle("Error")
         .setMessage("RB Selected....")
         .setPositiveButton("OK", new DialogInterface.OnClickListener()
         {
                 public void onClick(DialogInterface dialog, int whichButton)
                 {
                         setResult(RESULT_OK);
                 }
         })
         .show();*/
    	}		
    	
    }
    
    
    public class BluetoothValidator {
    	private static final int REQUEST_ENABLE_BT = 0;

		public void isBluetoothAdapterAvailable() {
    		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    		if (mBluetoothAdapter == null) 
    		{
    			  // Device does not support Bluetooth
            	new AlertDialog.Builder(scrabble.this)
            	.setTitle("Bluetooth Alert!")
            	.setMessage("This device dosn't support Bluetooth")
            	.setNegativeButton("Ok",
            	new DialogInterface.OnClickListener() {
            	public void onClick(DialogInterface dialog,
            	int which) {
            	}

            	}).show();
    		}
    		else
    		{
            	if (!mBluetoothAdapter.isEnabled()) {
            	    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            	    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            	}
    		}
    	}
    }
    
    

    public class Player 
    {
        	
    /*	public static void main(String args[]) throws java.io.IOException
    	{
    		String Player_one = "Player 1";
    		String Player_two = "Player 2";
    		Player Player_1 = new Player(Player_one);
    		Player Player_2 = new Player(Player_two);
    		Player_1.v_f_knowYourOpponent(Player_2);
    		Player_2.v_f_knowYourOpponent(Player_1);
     
        	Player_2.i_v_newBoard[5][5] = 84;
        	Player_2.i_v_newBoard[6][5] = 84;    	    	    	    	
        	Player_2.i_v_newBoard[7][5] = 67;
    		Player_2.onPlay();

        	Player_1.i_v_newBoard[5][2] = 65;
        	Player_1.i_v_newBoard[5][3] = 84;    	    	
    	   	Player_1.i_v_newBoard[5][4] = 83;
    		Player_1.onPlay();


    	}
    */
    	Context context = getApplicationContext();
        
        byte MAX_DIMN = 11;

    	public int  i_v_myScore = 0;
        public byte i_v_newBoard [][] = new byte[MAX_DIMN][MAX_DIMN] ;

        byte i_v_oldBoard [][] = new byte[MAX_DIMN][MAX_DIMN] ;
        byte i_v_premiumBoard [][] = new byte[MAX_DIMN][MAX_DIMN] ;
        byte i_v_faceValueOf [] = new byte[26] ;
      	int  i_v_hisScore = 0 ;
       	byte i_v_hisStatus = 0 ;

    	boolean b_v_playingForFirstTime = true ;	

    	Player Opponent;

       	String c_v_word = new String() ;
       	public String c_v_myDesig ; 

        void v_f_restoreNewBoard()
        {
    		byte i=0,j=0;
    		for ( i = 0 ; i < MAX_DIMN ; ++i)
    			for ( j = 0 ; j < MAX_DIMN ; ++j)
    				i_v_newBoard[i][j] = i_v_oldBoard[i][j];
        }

    	public void onPass() 
    	{
        	Toast.makeText(context, "Pass !", Toast.LENGTH_SHORT).show();
    	    this.v_f_restoreNewBoard();
    	    
    		/**/ // System.out.println(this.c_v_myDesig + " Passed !");
    		
    		Opponent.v_f_update(i_v_newBoard,i_v_myScore,(byte)2,b_v_playingForFirstTime);
        }
        
        public void v_f_knowYourOpponent(Player Opponent)
        {
        	this.Opponent = Opponent;
        }
        
        public void v_f_update(byte i_v_newBoard [][], int i_v_hisScore, byte i_v_hisStatus, boolean b_v_playingForFirstTime)
        {
        	this.i_v_hisScore = i_v_hisScore;
        	this.i_v_hisStatus = i_v_hisStatus;
        	this.b_v_playingForFirstTime = b_v_playingForFirstTime;

        	byte i = 0 ,j = 0;
       	  	for ( i = 0 ; i < MAX_DIMN ; ++i )
        	{
        		for ( j = 0 ; j < MAX_DIMN ; ++j )
        		{
        			this.i_v_oldBoard[i][j] = i_v_newBoard[i][j] ;
        		}
        	}
        	
        	if (i_v_hisStatus == 2 )
        	{
    	    	Toast.makeText(context, "Opponent Passed !", Toast.LENGTH_SHORT).show();
       			// // System.out.println(Opponent.c_v_myDesig + " Passed !");
    	    }
    	    else if (i_v_hisStatus == 0 )
        	{
    	    	Toast.makeText(context, "Opponent Played !", Toast.LENGTH_SHORT).show();
       			// // System.out.println(Opponent.c_v_myDesig + " Played !");
    	    }
    		else;	
        }
        
        Player (String c_v_myDesig)																					// Constructor
        {
        	byte i = 0 ,j = 0;
       		byte i_v_centerCol = (byte) ((MAX_DIMN-1)/2) ;
       		byte i_v_centerRow = (byte) ((MAX_DIMN-1)/2) ;

        	for ( i = 0 ; i < MAX_DIMN ; ++i )
        	{
        		for ( j = 0 ; j < MAX_DIMN ; ++j )
        		{
        			i_v_premiumBoard[i][j] = i_v_newBoard[i][j] = i_v_oldBoard[i][j] = 0 ;
        		}
        	}
    	
    		this.c_v_myDesig = c_v_myDesig;
    	    
    	    // Row 0, Col 0 to 5
    	    i_v_premiumBoard[0][0] = -3;
    	    i_v_premiumBoard[0][2] = 2;
    	    i_v_premiumBoard[0][5] = -3;
    	    
    	    // Row 1, Col 0 to 5
    	    i_v_premiumBoard[1][1] = -2;
    	    i_v_premiumBoard[1][4] = 3;
    	    
       	    // Row 2, Col 0 to 5
    	    i_v_premiumBoard[2][0] = 2;
    	    i_v_premiumBoard[2][2] = -2;
    	    i_v_premiumBoard[2][5] = 2;
    	    
    	    // Row 3, Col 0 to 5
    	    i_v_premiumBoard[3][3] = 3;
    	    
    	    // Row 4, Col 0 to 5
    	    i_v_premiumBoard[4][1] = 3;
    	    i_v_premiumBoard[4][4] = 2;
    	    
    	    // Row 5, Col 0 to 5
    	    i_v_premiumBoard[5][0] = -3;
    	    i_v_premiumBoard[5][2] = 2;
    	    	    
    	    for ( i = 0 ;i <= i_v_centerRow ; ++i )
    	    {
    	    	    for ( j = 0 ;j <= i_v_centerCol ; ++j )
    	    	    {
    	    	    	i_v_premiumBoard[i][2*i_v_centerCol-j] = i_v_premiumBoard[i][j];				// Column Reflection
    	    	    	i_v_premiumBoard[2*i_v_centerRow-i][j] = i_v_premiumBoard[i][j];				// Row Reflection
    	    	    }
    	   	}
    	    
        	i_v_faceValueOf[0]= 1;//A
        	i_v_faceValueOf[1]= 3;//B
        	i_v_faceValueOf[2]= 3;//C
        	i_v_faceValueOf[3]= 2;//D
        	i_v_faceValueOf[4]= 1;//E
        	i_v_faceValueOf[5]= 4;//F
        	i_v_faceValueOf[6]= 2;//G
        	i_v_faceValueOf[7]= 4;//H
        	i_v_faceValueOf[8]= 1;//I
        	i_v_faceValueOf[9]= 8;//J
        	i_v_faceValueOf[10]= 5;//K
        	i_v_faceValueOf[11]= 1;//L
        	i_v_faceValueOf[12]= 3;//M
        	i_v_faceValueOf[13]= 1;//N
        	i_v_faceValueOf[14]= 1;//O
        	i_v_faceValueOf[15]= 3;//P
        	i_v_faceValueOf[16]= 10;//Q
        	i_v_faceValueOf[17]= 1;//R
        	i_v_faceValueOf[18]= 1;//S
        	i_v_faceValueOf[19]= 1;//T
        	i_v_faceValueOf[20]= 1;//U
        	i_v_faceValueOf[21]= 4;//V
        	i_v_faceValueOf[22]= 4;//W
        	i_v_faceValueOf[23]= 8;//X
        	i_v_faceValueOf[24]= 4;//Y
        	i_v_faceValueOf[25]= 10;//Z
        	
        }

        public int onPlay() 
        {
        	// Return 0 - Success
        	// Return 1 - Failure
        	// Return 2 - Pass
        		
        	byte i_v_colFlag = -1;																		// 0 if word is not along a Column else Column 
        	byte i_v_rowFlag = -1;																		// 0 if word is not along a Row else Row
        	byte i_v_tileArray [] ;																		// List of new tiles
        	byte i_v_tileArrayLength = 0;																// Length of List
     
        	String c_v_discWord = new String() ;
        		
      		boolean b_v_gap = false ;
       		boolean b_v_4conn = false ;
    		   		
       		byte i_v_diff = 0;
       		byte i_v_centerCol = 0 ;
       		byte i_v_centerRow = 0 ;

       		byte i_v_startRowOfMainWord = 0 , i_v_endRowOfMainWord = 0;
       		byte i_v_startColOfMainWord = 0, i_v_endColOfMainWord = 0;
       		byte i_v_startRowOfDiscWord = 0, i_v_endRowOfDiscWord = 0;
       		byte i_v_startColOfDiscWord = 0, i_v_endColOfDiscWord = 0;

       		byte i = 0 , j = 0 ;																		// Loop variables
        		
       		// Subtract oldBoard from newBoard
       		// Determine the number of new tiles places on the board
       		for (i=0;i<MAX_DIMN;++i)
       		{
       			for (j=0;j<MAX_DIMN;++j)
       			{
       				if (i_v_newBoard[i][j] - i_v_oldBoard[i][j] > 0)
       				{					    	
       					if ( i_v_rowFlag != -1 && i_v_colFlag == -1 )									// Third and onwards
       					{
       						if ( i == i_v_rowFlag )
       							++i_v_tileArrayLength;
       						else
       						{
    					    	Toast.makeText(context, "Place your tiles along same row or column.", Toast.LENGTH_SHORT).show();
    					    	// System.out.println("Place your tiles along same row or column.");
       							this.v_f_restoreNewBoard(); 
       							return(1);
       						}
       					}
       					else if ( i_v_rowFlag == -1 && i_v_colFlag != -1 )								// Third and onwards
       					{					    	
       						if ( j == i_v_colFlag )
       							++i_v_tileArrayLength;
       						else
       						{
    					    	Toast.makeText(context, "Place your tiles along same row or column.", Toast.LENGTH_SHORT).show();
    					    	// System.out.println("Place your tiles along same row or column.");
       							this.v_f_restoreNewBoard();
       							return(1);
       						}
       					}
       					else if (i_v_rowFlag != -1 && i_v_colFlag != -1)								// Second encounter
       					{					    	
       						if ( i_v_rowFlag == i )
       							i_v_colFlag = -1;
       						else if (i_v_colFlag == j )
       							i_v_rowFlag = -1;
       						else
       						{
    					    	Toast.makeText(context, "Place your tiles along same row or column.", Toast.LENGTH_SHORT).show();
    					    	// System.out.println("Place your tiles along same row or column.");
       							this.v_f_restoreNewBoard(); 
       							return(1);
       						}
    						++i_v_tileArrayLength;
       					}
       					else if (i_v_colFlag == -1 && i_v_rowFlag == -1)								// First encounter
       					{					    	
       						i_v_rowFlag = i;
       						i_v_colFlag = j;
    						++i_v_tileArrayLength;
       					}
       					else;
       				}
       			}
       		}

       		// Proceed to store uncommon co-ordinate of the new cells
       		i_v_tileArray = new byte [i_v_tileArrayLength];					    	
       		i_v_tileArrayLength = 0 ;
       		if ( i_v_rowFlag != -1 && i_v_colFlag == -1)
       		{
       			for ( i = i_v_rowFlag, j = 0; j < MAX_DIMN ; ++j )
       			{
       				if ( i_v_newBoard[i][j] - i_v_oldBoard[i][j] > 0 )
       				{
       					i_v_tileArray[i_v_tileArrayLength] = j;
       					++i_v_tileArrayLength;
       				}
       			}
       		}
       		else if ( i_v_rowFlag == -1 && i_v_colFlag != -1 )
       		{
       			for ( j = i_v_colFlag, i = 0; i < MAX_DIMN ; ++i )
       			{
       				if ( i_v_newBoard[i][j] - i_v_oldBoard[i][j] > 0 )
       				{
       					i_v_tileArray[i_v_tileArrayLength] = i;
       					++i_v_tileArrayLength;
       				}
       			}
       		}
       		else if ( i_v_rowFlag == -1 && i_v_colFlag == -1 )
       		{
    			this.onPass();
    			return 2;
       		}
    		else
    		{
    			if (b_v_playingForFirstTime == true)
    			{
    		    	Toast.makeText(context, "First word must be atleast 2 letters long.", Toast.LENGTH_SHORT).show();
    				// System.out.println("First word must be atleast 2 letters long.");
    				this.v_f_restoreNewBoard(); 
    				return 1;
    			}
    			else
    			{
       					i_v_centerCol = i_v_colFlag;
       					i_v_centerRow = i_v_rowFlag;
      					
       					// Check 4 - connected neighbours
       					if ( i_v_oldBoard[i_v_centerRow][i_v_centerCol - 1] != 0 && i_v_centerCol >= 0)	// Row intention
       					{
    	   					i_v_tileArray[i_v_tileArrayLength] = i_v_colFlag;
       						i_v_colFlag = -1 ;
       						b_v_4conn = true;
       					}
       					else if ( i_v_oldBoard[i_v_centerRow][i_v_centerCol + 1] != 0 && i_v_centerCol <= MAX_DIMN - 1)	// Row intention
       					{
    	   					i_v_tileArray[i_v_tileArrayLength] = i_v_colFlag;   					
       						i_v_colFlag = -1 ;   					
       						b_v_4conn = true;
       					}
       					else if ( i_v_oldBoard[i_v_centerRow - 1][i_v_centerCol] != 0 && i_v_centerRow >= 0)	// Column intention
       					{
    	   					i_v_tileArray[i_v_tileArrayLength] = i_v_rowFlag;
       						i_v_rowFlag = -1 ;   					
       						b_v_4conn = true;
       					}
       					else if ( i_v_oldBoard[i_v_centerRow + 1][i_v_centerCol] != 0 && i_v_centerRow <= MAX_DIMN - 1)	// Column intention
       					{
    	   					i_v_tileArray[i_v_tileArrayLength] = i_v_rowFlag;   					
       						i_v_rowFlag = -1 ;
       						b_v_4conn = true;
       					}
       					else
       					{
    				    	Toast.makeText(context, "New words must be adjacent to existing words.", Toast.LENGTH_SHORT).show();
    						// System.out.println("New words must be adjacent to existing words.");
    						this.v_f_restoreNewBoard(); 
    						return 1;
       					}
       					++i_v_tileArrayLength;
    			}
    		}   		

    /************************************************************************************************************************************************/

       		if ( i_v_rowFlag != -1 && i_v_colFlag == -1)												// Word is along a Row
       		{
       			for ( i=0; i < i_v_tileArrayLength ; ++i)
       			{
       				c_v_word = c_v_word + (char) i_v_newBoard[i_v_rowFlag][i_v_tileArray[i]];			// Retrieve alphabet from newBoard
       		
       				if (i+1 < i_v_tileArrayLength)
       				{
       					i_v_diff = (byte) (i_v_tileArray[i+1] - i_v_tileArray[i]) ;						// Check for 'consecutive' property
       					
       					if ( i_v_diff != 1)
       					{
       						b_v_gap = true;																// Record there is a gap
       						for (j = (byte) (i_v_tileArray[i] + 1); j != i_v_tileArray[i+1]; ++j)
       						{
       							if ( i_v_newBoard[i_v_rowFlag][j] != 0 )
       								c_v_word = c_v_word + (char) i_v_newBoard[i_v_rowFlag][j];			// Retrieve words in the gap and store
       							else
       							{

    						    	Toast.makeText(context, "Gaps aren't allowed mate !!", Toast.LENGTH_SHORT).show();
    						    	// System.out.println("Gaps aren't allowed mate !!");
       								this.v_f_restoreNewBoard(); 
       								return(1);															// Gap recorded but not filled
       							}
       						}
       					}
       				}	
       			}
       			
       			if (b_v_gap == false)																	// Gap not recorded, checking for 4 - conn
       			{

    				if (b_v_playingForFirstTime == false)
    				{
    	   				i=0;
    	   				while (i < i_v_tileArrayLength && b_v_4conn == false)
    	   				{
    	   					i_v_centerCol = i_v_tileArray[i];
    	   					i_v_centerRow = i_v_rowFlag;
       					
    	   					// Check 4 - connected neighbours
    	   					if ( i_v_oldBoard[i_v_centerRow][i_v_centerCol - 1] != 0 && i_v_centerCol >= 0)
    	   						b_v_4conn = true;
    	   					else if ( i_v_oldBoard[i_v_centerRow][i_v_centerCol + 1] != 0 && i_v_centerCol <= MAX_DIMN - 1)
    	   						b_v_4conn = true;
    	   					else if ( i_v_oldBoard[i_v_centerRow - 1][i_v_centerCol] != 0 && i_v_centerRow >= 0)
    	   						b_v_4conn = true;
    	   					else if ( i_v_oldBoard[i_v_centerRow + 1][i_v_centerCol] != 0 && i_v_centerRow <= MAX_DIMN - 1)					
    	   						b_v_4conn = true;
    	   					else;
       					
    	   					++i;
    	   				}
    				}
    				else																				// Playing for the first time
    				{
    					if (i_v_newBoard[(MAX_DIMN-1)/2][(MAX_DIMN-1)/2] != 0)							// Center cell must be occupied...
    						b_v_4conn = true;
    				}
       			}
       			else
       			{
       				b_v_4conn = true ;																	// Gap recorded and filled
       			}
       			
       			if (b_v_4conn == true)
       			{
       				i = (byte) (i_v_tileArray[0] - 1) ;
       				while (i >=0 && i_v_newBoard [i_v_rowFlag][i]!=0)									// Travel Left
       				{
       					c_v_word = (char)i_v_newBoard [i_v_rowFlag][i] + c_v_word;
       					--i;
       				}
       				++i;
       				i_v_startColOfMainWord = i;
       				
       				i = (byte) (i_v_tileArray[i_v_tileArrayLength-1] + 1) ;								// Travel Right
       				while (i <=MAX_DIMN - 1 && i_v_newBoard [i_v_rowFlag][i]!=0)
       				{
       					c_v_word = c_v_word + (char)i_v_newBoard [i_v_rowFlag][i];
       					++i;
       				}
       				--i;
       				i_v_endColOfMainWord = i;
       			}
       			else																					// Gap not recorded and no 4 - conn either
       			{
    				if (b_v_playingForFirstTime == false)
    				{
    			    	Toast.makeText(context, "New words must be adjacent to existing words.", Toast.LENGTH_SHORT).show();
    					// System.out.println("New words must be adjacent to existing words.");
    				}
    				else
    				{
    			    	Toast.makeText(context, "Center cell must be consumed.", Toast.LENGTH_SHORT).show();
    					// System.out.println("Center cell must be consumed.");
    				}
       				this.v_f_restoreNewBoard(); 
       				return(1);
       			}
       			
       			if ( b_f_validateWord ( c_v_word ) == true )
       			{
       				// Travel Vertically to discover new words & Validate them
    				if (b_v_playingForFirstTime == false)
    				{
    	   				for ( i = 0; i < i_v_tileArrayLength ; ++i )
    	   				{
    	   					c_v_discWord = (char)i_v_newBoard[i_v_rowFlag][i_v_tileArray[i]] + c_v_discWord;
       					
    	   					j = (byte)(i_v_rowFlag - 1);
    	   					while ( j >=0 && i_v_newBoard[j][i_v_tileArray[i]] != 0)					// Travel up for each new tile placed horiz.
    	   					{
    	   						c_v_discWord = (char)i_v_newBoard[j][i_v_tileArray[i]] + c_v_discWord ;
    	   						--j;
    	   					}
    	   					++j;
    	   					i_v_startRowOfDiscWord = j;
    	   					i_v_startColOfDiscWord = i_v_tileArray[i];
       					
    	   					j = (byte)(i_v_rowFlag + 1);
    	   					while ( j <=MAX_DIMN - 1 && i_v_newBoard[j][i_v_tileArray[i]] != 0)			// Travel down for each new tile placed horiz.
    	   					{
    	   						c_v_discWord =  c_v_discWord + (char)i_v_newBoard[j][i_v_tileArray[i]] ;
    	   						++j;
    	   					}
    	   					--j;
    	   					i_v_endRowOfDiscWord = j;
    	   					i_v_endColOfDiscWord = i_v_tileArray[i];
        					
        					if (i_v_startColOfDiscWord != i_v_endColOfDiscWord)
        					{
    		   					if ( b_f_validateWord ( c_v_discWord ) == true)
    		   					{
    								i_v_myScore += i_f_calcScore (i_v_startRowOfDiscWord, i_v_startColOfDiscWord, i_v_endRowOfDiscWord, i_v_endColOfDiscWord);
    		   						c_v_discWord = new String();
    		   					}
    		   					else
    		   					{
    						    	Toast.makeText(context, "That's an invalid word there bro !!", Toast.LENGTH_SHORT).show();
    								// System.out.println("That's an invalid word there bro !!");
    		   						this.v_f_restoreNewBoard(); 
    		   						return(1);															// Encountered invalid word
    		   					}
    		   				}
    	   				}
    	   				// Calculate total Score
    	   				i_v_myScore += i_f_calcScore (i_v_rowFlag, i_v_startColOfMainWord, i_v_rowFlag, i_v_endColOfMainWord);
       				}
    				else
    				{
    	   				i_v_myScore += i_f_calcScore (i_v_rowFlag, i_v_startColOfMainWord,  i_v_rowFlag, i_v_endColOfMainWord);
    					b_v_playingForFirstTime = false;												// Playing for 1st time with a word length >= 2
    	   			}

       							
       				for ( i = 0 ; i < MAX_DIMN ; ++i)
       				{
       					for ( j = 0 ; j < MAX_DIMN ; ++j)
       					{
       						i_v_oldBoard[i][j] = i_v_newBoard[i][j];
       					}
       				}
       				
       				// Send via bluetooth
       				Toast.makeText(context, "Turn played successfully !", Toast.LENGTH_SHORT).show();
    				// System.out.println(this.c_v_myDesig + " played successfully !");
    				// System.out.println("Score : " + this.i_v_myScore);
    				Opponent.v_f_update(i_v_newBoard,i_v_myScore,(byte)0,b_v_playingForFirstTime);
       				return 0;																			// Successful
       			}
       			else
       			{
    		    	Toast.makeText(context, "That's an invalid word bro !!", Toast.LENGTH_SHORT).show();
    				// System.out.println("That's an invalid word bro !!");
       				this.v_f_restoreNewBoard(); 
       				return(1);																			// Encountered invalid word
       			}
       		}

    /************************************************************************************************************************************************/

       		else if ( i_v_rowFlag == -1 && i_v_colFlag != -1 )											// Word is along a Column
       		{
       			for ( i=0; i < i_v_tileArrayLength ; ++i)
       			{
       				c_v_word = c_v_word + (char) i_v_newBoard[i_v_tileArray[i]][i_v_colFlag];			// Retrieve alphabet from newBoard
       		
       				if (i+1 < i_v_tileArrayLength)
       				{
       					i_v_diff = (byte) (i_v_tileArray[i+1] - i_v_tileArray[i]) ;						// Check for 'consecutive' property
       					
       					if ( i_v_diff != 1)
       					{
       						b_v_gap = true;																// Record there is a gap
       						for (j = (byte) (i_v_tileArray[i] + 1); j != i_v_tileArray[i+1]; ++j)
       						{
       							if ( i_v_newBoard[j][i_v_colFlag] != 0 )
       								c_v_word = c_v_word + (char) i_v_newBoard[j][i_v_colFlag];			// Retrieve words in the gap and store
       							else
       							{
    						    	Toast.makeText(context, "Gaps aren't allowed mate !!", Toast.LENGTH_SHORT).show();
    						    	// System.out.println("Gaps aren't allowed mate !!");
       								this.v_f_restoreNewBoard(); 
       								return(1);															// Gap recorded but not filled
       							}
       						}
       					}
       				}	
       			}
       			
       			if (b_v_gap == false)																	// Gap not recorded, checking for 4 - conn
       			{
    				if (b_v_playingForFirstTime == false)
    				{
    	   				i=0;
    	   				while(i < i_v_tileArrayLength && b_v_4conn == false)
    	   				{
    	   					i_v_centerCol = i_v_colFlag;
    	   					i_v_centerRow = i_v_tileArray[i];
       					
    	   					// Check 4 - connected neighbours
    	   					if ( i_v_oldBoard[i_v_centerRow][i_v_centerCol - 1] != 0 && i_v_centerCol >= 0)
    	   						b_v_4conn = true;
    	   					else if ( i_v_oldBoard[i_v_centerRow][i_v_centerCol + 1] != 0 && i_v_centerCol <= MAX_DIMN - 1)
    	   						b_v_4conn = true;
    	   					else if ( i_v_oldBoard[i_v_centerRow - 1][i_v_centerCol] != 0 && i_v_centerRow >= 0)
    	   						b_v_4conn = true;
    	   					else if ( i_v_oldBoard[i_v_centerRow + 1][i_v_centerCol] != 0 && i_v_centerRow <= MAX_DIMN - 1)					
    	   						b_v_4conn = true;
    	   					else;
    	   					
    	   					++i;
    	   				}
    				}
    				else																				// Playing for the first time
    				{
    					if (i_v_newBoard[(MAX_DIMN-1)/2][(MAX_DIMN-1)/2] != 0)							// Center cell must be occupied...
    						b_v_4conn = true;
    				}
       			}
       			else																					// Gap recorded and filled
       			{
       				b_v_4conn = true;
       			}
       				
       			if (b_v_4conn == true)
       			{
       				i = (byte) (i_v_tileArray[0] - 1) ;
       				while (i >=0 && i_v_newBoard [i][i_v_colFlag]!=0)									// Travel Up
       				{
       					c_v_word = (char)i_v_newBoard[i][i_v_colFlag] + c_v_word;
       					--i;
       				}
       				++i;
       				i_v_startRowOfMainWord = i;
       				
       				i = (byte) (i_v_tileArray[i_v_tileArrayLength-1] + 1) ;
       				while (i <=MAX_DIMN - 1 && i_v_newBoard [i][i_v_colFlag]!=0)						// Travel Down
       				{
       					c_v_word = c_v_word + (char)i_v_newBoard[i][i_v_colFlag];
       					++i;
       				}				
       				--i;
       				i_v_endRowOfMainWord = i;
       			}
       			else																					// Gap not recorded and no 4 - conn either
       			{
    				if (b_v_playingForFirstTime == false)
    				{
    			    	Toast.makeText(context, "New words must be adjacent to existing words.", Toast.LENGTH_SHORT).show();
    					// System.out.println("New words must be adjacent to existing words.");
    				}
    				else
    				{
    			    	Toast.makeText(context, "Center cell must be consumed.", Toast.LENGTH_SHORT).show();
    					// System.out.println("Center cell must be consumed.");
    				}
       				this.v_f_restoreNewBoard(); 
       				return(1);
       			}
       			   			
       			if ( b_f_validateWord ( c_v_word ) == true )
       			{
       				// Travel Horizontally to discover new words & Validate them
    				if (b_v_playingForFirstTime == false)
    				{
    	   				for ( i = 0; i < i_v_tileArrayLength ; ++i )
    	   				{
    	   					c_v_discWord = (char)i_v_newBoard[i_v_tileArray[i]][i_v_colFlag] + c_v_discWord;
       					
    	   					j = (byte)(i_v_colFlag - 1);
    	   					while ( j >=0 && i_v_newBoard[i_v_tileArray[i]][j] != 0)					// Travel left for each new tile placed vert
    	   					{
    	   						c_v_discWord = (char)i_v_newBoard[i_v_tileArray[i]][j] + c_v_discWord ;
    	   						--j;
    	   					}
    	   					++j;
    	   					i_v_startRowOfDiscWord = i_v_tileArray[i];
    	   					i_v_startColOfDiscWord = j;
       					
    	   					j = (byte)(i_v_colFlag + 1);
    	   					while ( j <=MAX_DIMN - 1 && i_v_newBoard[i_v_tileArray[i]][j] != 0)			// Travel right for each new tile placed vert.
    	   					{
    	   						c_v_discWord =  c_v_discWord + (char)i_v_newBoard[i_v_tileArray[i]][j] ;
    	   						++j;
    	   					}
    	   					--j;
    	   					i_v_endRowOfDiscWord = i_v_tileArray[i];
    	   					i_v_endColOfDiscWord = j;
    	    					
    	   					if (i_v_startRowOfDiscWord != i_v_endRowOfDiscWord)
    	   					{
    		   					if ( b_f_validateWord ( c_v_discWord ) == true )
       							{
       								i_v_myScore += i_f_calcScore (i_v_startRowOfDiscWord, i_v_startColOfDiscWord, i_v_endRowOfDiscWord, i_v_endColOfDiscWord);
       								c_v_discWord = new String();
       							}
       							else
       							{
    						    	Toast.makeText(context, "That's an invalid word bro !!", Toast.LENGTH_SHORT).show();
    								// System.out.println("That's an invalid word there bro !!");				    	
       								this.v_f_restoreNewBoard(); 
       								return(1);															// Encountered invalid word
       							}
       						}
       					}
    	   				// Calculate total Score
    	   				i_v_myScore += i_f_calcScore (i_v_startRowOfMainWord, i_v_colFlag,  i_v_endRowOfMainWord, i_v_colFlag);
       				}
    				else
    				{
    	   				i_v_myScore += i_f_calcScore (i_v_startRowOfMainWord, i_v_colFlag,  i_v_endRowOfMainWord, i_v_colFlag);
    					b_v_playingForFirstTime = false;												// Playing for 1st time with a word length >= 2
    	   			}
       							
       				for ( i = 0 ; i < MAX_DIMN ; ++i)
       				{
       					for ( j = 0 ; j < MAX_DIMN ; ++j)
       					{
       						i_v_oldBoard[i][j] = i_v_newBoard[i][j];
       					}
       				}
       				// Send via bluetooth
       				Toast.makeText(context, "Turn played successfully !", Toast.LENGTH_SHORT).show();
    				// System.out.println(this.c_v_myDesig + " played successfully !"); 
    				// System.out.println("Score : " + this.i_v_myScore);  	
    				Opponent.v_f_update(i_v_newBoard,i_v_myScore,(byte)0,b_v_playingForFirstTime);
       				return 0;																			// Successful
       			}
       			else
       			{
    		    	Toast.makeText(context, "That's an invalid word bro !!", Toast.LENGTH_SHORT).show();
    				// System.out.println("That's an invalid word bro !!");		    	
       				this.v_f_restoreNewBoard(); 
       				return(1);																			// Encountered invalid word
       			}
       		}

    /*************************************************************************************************************************************************/

        	else
    	   	{
       			this.onPass();
       			return 2;																				// Pass
       		}

    /*************************************************************************************************************************************************/
    	}
        	
       	boolean b_f_validateWord ( String c_v_word )
       	{
       		// @Neha and Prat : INSERT YOUR CODE HERE AND REMOVE THIS DEFAULT this.v_f_restoreNewBoard(); return STATEMENT
       		return true;
       	}
       	
       	int i_f_calcScore (byte i_v_startRow, byte i_v_startCol, byte i_v_endRow, byte i_v_endCol)
       	{
    		int i_v_sum = 0 ;
    		int i_v_multiplier = 1 ;
    		byte i_v_letter ;
    		byte i_v_newTileCount = 0;

    		while(i_v_startCol != i_v_endCol)															// Word is along a Row
    		{
    			if (i_v_oldBoard[i_v_startRow][i_v_startCol] != 0)
    			{
    				// Add face value
    				i_v_letter = (byte) (i_v_oldBoard[i_v_startRow][i_v_startCol] - 65) ;
    				i_v_sum += i_v_faceValueOf[i_v_letter];
    			}
    			else
    			{
    				i_v_letter = (byte) (i_v_newBoard[i_v_startRow][i_v_startCol] - 65) ;
    				++i_v_newTileCount;
    				
    				if (i_v_premiumBoard[i_v_startRow][i_v_startCol] == 2)
    				{
    					// Add twice the face value
    					i_v_sum += 2*i_v_faceValueOf[i_v_letter];
    				}
    				else if (i_v_premiumBoard[i_v_startRow][i_v_startCol] == 3)
    				{
    					// Add thrice the face value
    					i_v_sum += 3*i_v_faceValueOf[i_v_letter];
    				}
    				else if (i_v_premiumBoard[i_v_startRow][i_v_startCol] == -2)
    				{
    					// Add face value
    					i_v_sum += i_v_faceValueOf[i_v_letter];
    					// Increase double word count
    					i_v_multiplier *= 2;
    				}
    				else if (i_v_premiumBoard[i_v_startRow][i_v_startCol] == -3)
    				{
    					// Add face value
    					i_v_sum += i_v_faceValueOf[i_v_letter];
    					// Increase triple word count
    					i_v_multiplier *= 3;
    				}
    				else
    					i_v_sum += i_v_faceValueOf[i_v_letter];
    			}
    			++i_v_startCol;
    		}
    		while(i_v_startRow != i_v_endRow)															// Word is along a Column
    		{
    			if (i_v_oldBoard[i_v_startRow][i_v_startCol] != 0)
    			{
    				// Add face value
    				i_v_letter = (byte) (i_v_oldBoard[i_v_startRow][i_v_startCol] - 65) ;
    				i_v_sum += i_v_faceValueOf[i_v_letter];
    			}
    			else
    			{
    				i_v_letter = (byte) (i_v_newBoard[i_v_startRow][i_v_startCol] - 65) ;
    				++i_v_newTileCount;		

    				if (i_v_premiumBoard[i_v_startRow][i_v_startCol] == 2)
    				{
    					// Add twice the face value
    					i_v_sum += 2*i_v_faceValueOf[i_v_letter];
    				}
    				else if (i_v_premiumBoard[i_v_startRow][i_v_startCol] == 3)
    				{
    					// Add thrice the face value
    					i_v_sum += 3*i_v_faceValueOf[i_v_letter];
    				}
    				else if (i_v_premiumBoard[i_v_startRow][i_v_startCol] == -2)
    				{
    					// Add face value
    					i_v_sum += i_v_faceValueOf[i_v_letter];
    					// Increase double word count
    					i_v_multiplier *= 2;
    				}
    				else if (i_v_premiumBoard[i_v_startRow][i_v_startCol] == -3)
    				{
    					// Add face value
    					i_v_sum += i_v_faceValueOf[i_v_letter];
    					// Increase triple word count
    					i_v_multiplier *= 3;
    				}
    				else
    					i_v_sum += i_v_faceValueOf[i_v_letter];
    			}
    			++i_v_startRow;
    		}

    		if (i_v_oldBoard[i_v_startRow][i_v_startCol] != 0)											// The last cell gets left over so consider it 
    		{
    			// Add face value
    			i_v_letter = (byte) (i_v_oldBoard[i_v_startRow][i_v_startCol] - 65) ;
    			i_v_sum += i_v_faceValueOf[i_v_letter];
    		}
    		else
    		{
    			i_v_letter = (byte) (i_v_newBoard[i_v_startRow][i_v_startCol] - 65) ;
    			++i_v_newTileCount;
    			
    			if (i_v_premiumBoard[i_v_startRow][i_v_startCol] == 2)
    			{
    				// Add twice the face value
    				i_v_sum += 2*i_v_faceValueOf[i_v_letter];
    			}
    			else if (i_v_premiumBoard[i_v_startRow][i_v_startCol] == 3)
    			{
    				// Add thrice the face value
    				i_v_sum += 3*i_v_faceValueOf[i_v_letter];
    			}
    			else if (i_v_premiumBoard[i_v_startRow][i_v_startCol] == -2)
    			{
    				// Add face value
    				i_v_sum += i_v_faceValueOf[i_v_letter];
    				// Increase double word count
    				i_v_multiplier *= 2;
    			}
    			else if (i_v_premiumBoard[i_v_startRow][i_v_startCol] == -3)
    			{
    				// Add face value
    				i_v_sum += i_v_faceValueOf[i_v_letter];
    				// Increase triple word count
    				i_v_multiplier *= 3;
    			}
    			else
    				i_v_sum += i_v_faceValueOf[i_v_letter];
    		}

    		if (b_v_playingForFirstTime == true)
    			i_v_multiplier *= 2;
    			
    		i_v_sum *= i_v_multiplier;
    		
    		if (i_v_newTileCount == 7)
    			i_v_sum += 50;
    		
    		return i_v_sum;
    	}
    }

    
    
    public class PlayPassOver
    {
    	String Player_one = "Player 1";
    	String Player_two = "Player 2";
    	Player Player_1 = new Player(Player_one);
    	Player Player_2 = new Player(Player_two);
    	
        byte MAX_DIMN = 11;
        byte player = 1;
        public byte i_v_newBoard [][] = new byte[MAX_DIMN][MAX_DIMN] ;


    	// public int i_v_player_1_score ;
    	byte i_v_player_1_passCount = 0;
    	// public int i_v_player_2_score ;
    	byte i_v_player_2_passCount = 0;

    	Context context = getApplicationContext();
    	
    	/*public static void main(String args[]) throws java.io.IOException
    	{
    		PlayPassOver playPassObj = new PlayPassOver ();
    	   	playPassObj.i_v_newBoard[5][5] = 84;
        	playPassObj.i_v_newBoard[6][5] = 84;    	    	    	    	
        	playPassObj.i_v_newBoard[7][5] = 67;
    		playPassObj.onPlay();

        	playPassObj.i_v_newBoard[5][2] = 65;
        	playPassObj.i_v_newBoard[5][3] = 84;    	    	
    	   	playPassObj.i_v_newBoard[5][4] = 83;
    		playPassObj.onPlay();

    	}*/
    	
    	public void onPlay()
    	{
    		Player_1.v_f_knowYourOpponent(Player_2);
    		Player_2.v_f_knowYourOpponent(Player_1);

    	  	byte i = 0 ,j = 0;
    	  	byte i_v_returnValue = 0;
    		if (player == 1)
    		{
    	   	  	for ( i = 0 ; i < MAX_DIMN ; ++i )
    	    	{
    	    		for ( j = 0 ; j < MAX_DIMN ; ++j )
    	    		{
    	    			Player_1.i_v_newBoard[i][j] = this.i_v_newBoard[i][j] ;
    	    		}
    	    	}
    	    	
    	    	i_v_returnValue = (byte)Player_1.onPlay();
    		    
    		    if (i_v_returnValue == 0)
    		    {
    		    	// i_v_player_1_score = Player_1.i_v_myScore;
    		    	i_v_player_1_passCount = 0;
    		    	// System.out.println("Player 1 Score : " + i_v_player_1_score);
    		    	player = 2;
    		    }
    		    else if (i_v_returnValue == 1)
    		    {
    		    	// Error
    		    }
    		    else if (i_v_returnValue == 2)
    		    {
      		    	++i_v_player_1_passCount;
    		    	player = 2;
    		    }


    	    }
    	    else
    	    {
    	   	  	for ( i = 0 ; i < MAX_DIMN ; ++i )
    	    	{
    	    		for ( j = 0 ; j < MAX_DIMN ; ++j )
    	    		{
    	    			Player_2.i_v_newBoard[i][j] = this.i_v_newBoard[i][j] ;
    	    		}
    	    	}

    	    	i_v_returnValue = (byte)Player_2.onPlay();
    		    
    		    if (i_v_returnValue == 0)
    		    {
    		    	// i_v_player_2_score = Player_2.i_v_myScore;
    		    	i_v_player_2_passCount = 0;
    		    	// System.out.println("Player 2 Score : " + i_v_player_2_score);
    		    	player = 1;
    		    }
    		    else if (i_v_returnValue == 1)
    		    {
    		    	// Error
    		    }
    		    else if (i_v_returnValue == 2)
    		    {
      		    	++i_v_player_2_passCount;
    		    	player = 1;
    		    }
    	    }
    	    
    	    if (i_v_player_1_passCount == 2 && i_v_player_2_passCount == 2)
    	    {
    	    	Toast.makeText(context, "Both Passed. Game Over!", Toast.LENGTH_SHORT).show();
    			// finish();
       			// System.out.println("Both Passed. Game Over!");
    	    }
    	 }
    }
    	  




}



