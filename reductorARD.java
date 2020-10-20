package reductorARD;

import java.util.ArrayList;
import java.util.List;

public class reductorARD {

	public ArrayList<String> ReductorAFD(int [][] quintupla, String eAceptacion[], int estados){
	String c0 = "";
	String c1="";
	String c2="";
	String perron1 = "";
	String perron2 = "";
		boolean estatus = true;
		String [][] matriz2;
		String [][] matriz3;
		int aceptacion [][];
		int transicion[];
		ArrayList<Integer> noAcepta = new ArrayList<Integer>();
		ArrayList<String> reducto = new ArrayList<String>();
		ArrayList<String> reductoFinal = new ArrayList<String>();
		ArrayList<String> tablatres = new ArrayList<String>();
		ArrayList<String> tablacuatro = new ArrayList<String>();
		
		//SE buscan los estados de no aceptación, se separan y se ponen en noAcepta
		for( int i=0; i< quintupla.length; i++)
		{
		    estatus = true;
		    for(int j=0; j<quintupla[0].length; j++)
		        if(j>0)
		        	for (int k = 0; k<eAceptacion.length; k++)
		        {
		        		// 
		        if(quintupla[i][j-1] != quintupla[i][j] ||quintupla[i][j] == Integer.parseInt(eAceptacion[k]) || Integer.parseInt(eAceptacion[k]) == i)
		            estatus = false;
		        }
		    if(estatus == true)
		        noAcepta.add(i);
		}
		
		//Se verifica si existen estados no aceptación y en caso de  que existan o no, se calculan los estados de transicion
		//Y se meten en un vector
		if(noAcepta.size() == 0) {
			int contador = 0;
			boolean tranc = false;
			aceptacion= new int [quintupla.length-1][quintupla[0].length];
			transicion=new int [estados - eAceptacion.length - noAcepta.size()];
			for(int i=0; i<estados;i++) {
				tranc = false;
				for(int j=0; j<eAceptacion.length; j++) {
					if(i==Integer.parseInt(eAceptacion[j])) {
						tranc = true;
					}
				}
				if(!tranc)
				{
					transicion[contador]=i;
					contador++;
				}
				
			}
		}
		else {
			int contador = 0;
			boolean tranc = false;
			aceptacion=quintupla;
			transicion= new int[estados- eAceptacion.length - noAcepta.size()];
			for(int i=0; i<estados; i++) {
				for(var item: noAcepta) {
				if(item == i)
					break;
					else if(i!=item) {
					for(int j=0; j<eAceptacion.length; j++) {
						if(i==Integer.parseInt(eAceptacion[j])) {
							tranc = true;
						}
					}
					if(!tranc)
					{
						transicion[contador]=i;
						contador++;
						break;
					}
				}
			}
			}
				
		}
		
		
		
	for(int i=0; i<transicion.length;i++) {
		if(i==transicion.length-1) {
			c0+=transicion[i];
		}
		else {
			c0+=transicion[i]+",";
		}
		
	}
	
	
	
for(int i=0; i<eAceptacion.length;i++) {

	
		if(i==eAceptacion.length-1) {
			c2+=eAceptacion[i];
		}
		else {
			c2+=eAceptacion[i]+",";
		}
	}
		
		


for(int i=0; i<noAcepta.size();i++) {

	
	if(i==noAcepta.size()-1) {
		c1+=noAcepta.get(i);
	}
	else {
		c1+=noAcepta.get(i)+",";
	}
}
		//Se crea la segunda matriz con los nuevos valores agrupados
		matriz2 = new String[estados][quintupla[0].length];
		
		for( int i=0; i< quintupla.length; i++)
		{
		    for(int j=0; j<quintupla[0].length; j++)
		    {
		    		for(int k=0; k<transicion.length; k++)		
		    			if(quintupla[i][j] == transicion[k])
		    				matriz2[i][j] = c0; 
		    		for(int z=0; z<noAcepta.size(); z++)
		    			if(quintupla[i][j] == noAcepta.get(z))
		    				matriz2[i][j] = c1;
		    		for(int x=0; x<eAceptacion.length; x++)
		    			if(quintupla[i][j] == Integer.parseInt(eAceptacion[x]))
		    				matriz2[i][j] = c2;
		    		}   			
		    	}
		//C0	  C3
		//C1{1,2} C2{0,3}1,2_0,3 
		//C1  	  C2   {1,2_0,3}

		for(int i=0; i<matriz2.length; i++)
		{
			String estado = String.valueOf(i);
			perron1 = "";
	    	for(int j=0; j<matriz2[0].length; j++)
	    		if (j == matriz2[0].length -1)
	    			perron1 += matriz2[i][j];
	    		else
	    			perron1 += matriz2[i][j] + "_";
	    	for(int x=i+1; x<matriz2.length; x++) {
	    		boolean isPerron = false;
	    		perron2 ="";
	    		for(int k=0; k<matriz2[0].length; k++)    
	    			if (k == matriz2[0].length -1)
	    				perron2 += matriz2[x][k];
	    			else
	    				perron2 += matriz2[x][k] + "_"; 
	    		if(perron1.equals(perron2))
    			{
	    			for(int z = 0; z<reducto.size(); z++)
	    				for(int r = 0; r<eAceptacion.length; r++)
	    				if(reducto.get(z).toString() == perron1 && i != Integer.parseInt(eAceptacion[r]))
	    					isPerron = true;
	    			if(!isPerron)
	    			{
	    			tablatres.add(estado+= "," +x);
	    			reducto.add(perron1);
	    			}
    			}
	    	}
		}
		
		// C1  C2	|_______________
		// C1  C2	|{0,1_2,3}
		// C0  C1	|{2,4_4}
		for(int x=matriz2.length-1; x<matriz2.length; x++) {
    		boolean isPerron = false;
    		perron2 ="";
    		for(int k=0; k<matriz2[0].length; k++)    
    			if (k == matriz2[0].length -1)
    				perron2 += matriz2[x][k];
    			else
    				perron2 += matriz2[x][k] + "_"; 
    			for(int z = 0; z<reducto.size(); z++)
    				for(int r = 0; r<eAceptacion.length; r++)
    				if(reducto.get(z).toString() == perron2 && x != Integer.parseInt(eAceptacion[r]))
    					isPerron = true;
    			if(!isPerron)
    			{    				
    			tablatres.add(String.valueOf(x));
    			reducto.add(perron2);
    			}
    	}
		
matriz3 = new String[estados][quintupla[0].length];
		
		for( int i=0; i< quintupla.length; i++)
		{
		    for(int j=0; j<quintupla[0].length; j++)
		    {
		    		for(var item : tablatres)
		    		{
		    		for(int k=0; k<item.split(",").length; k++)		
		    			if(quintupla[i][j] == Integer.parseInt(item.split(",")[k]))
		    			{		    				
		    				matriz3[i][j] = item;
		    				break;
		    			}
		    		}
		    		}   			
		    	}
		for(int i=0; i<matriz3.length; i++)
		{
			String estado = String.valueOf(i);
			perron1 = "";
	    	for(int j=0; j<matriz3[0].length; j++)
	    		if (j == matriz3[0].length -1)
	    			perron1 += matriz3[i][j];
	    		else
	    			perron1 += matriz3[i][j] + "_";
	    	for(int x=i+1; x<matriz3.length; x++) {
	    		boolean isPerron = false;
	    		perron2 ="";
	    		for(int k=0; k<matriz3[0].length; k++)    
	    			if (k == matriz3[0].length -1)
	    				perron2 += matriz3[x][k];
	    			else
	    				perron2 += matriz3[x][k] + "_"; 
	    		if(perron1.equals(perron2))
    			{
	    			for(int z = 0; z<reductoFinal.size(); z++)
	    				for(int r = 0; r<eAceptacion.length; r++)
	    				if(reductoFinal.get(z).toString() == perron1 && i != Integer.parseInt(eAceptacion[r]))
	    					isPerron = true;
	    			if(!isPerron)
	    			{
	    			tablacuatro.add(estado+= "," +x);
	    			reductoFinal.add(perron1);
	    			}
    			}
	    	}
		}
		
		for(int x=matriz3.length-1; x<matriz3.length; x++) {
    		boolean isPerron = false;
    		perron2 ="";
    		for(int k=0; k<matriz3[0].length; k++)    
    			if (k == matriz3[0].length -1)
    				perron2 += matriz3[x][k];
    			else
    				perron2 += matriz3[x][k] + "_"; 
    			for(int z = 0; z<reductoFinal.size(); z++)
    				for(int r = 0; r<eAceptacion.length; r++)
    				if(reductoFinal.get(z).toString() == perron2 && x != Integer.parseInt(eAceptacion[r]))
    					isPerron = true;
    			if(!isPerron)
    			{    			
    			tablacuatro.add(String.valueOf(x));
    			reductoFinal.add(perron2);
    			}
    	}
	/*
	q1 =	c0 c1   {q1}
	q2 =	c1 c2	{q2,q7}
	q3 =	c0 c0	{q3}
	q4 =	c1 c1	{q4,q6}
	q5 =	c2 c1	{q5}
	q6 =	c1 c1
	q7 =	c1 c2
	*/
	return reductoFinal;	
	}
}
