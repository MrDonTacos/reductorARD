package reductorARD;

import java.util.ArrayList;
import java.util.List;

public class reductorARD {

	public ArrayList<String> ReductorAFD(int [][] quintupla, String eAceptacion[], int estados){
	String c0 = "";
	String c1="";
	String c2="";
	String transicion01 = "";
	String transicion02 = "";
		boolean estatus = true;
		String [][] matriz2;
		String [][] matrizPrueba;
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
					noAcepta.add(i);;
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
						noAcepta.add(i);
						contador++;
						break;
					}
				}
			}
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
		c0+=noAcepta.get(i);
	}
	else {
		c0+=noAcepta.get(i)+",";
	}
}



		/* 
		 * C0 = {Estado de no aceptación}
		 * C2 = {Estados de Aceptación}
		 */

		//Se crea la segunda matriz con los nuevos valores agrupados
		matriz2 = new String[estados][quintupla[0].length];
		
		for( int i=0; i< quintupla.length; i++)
		{
		    for(int j=0; j<quintupla[0].length; j++)
		    {
		    		for(int k=0; k<noAcepta.size(); k++)		
		    			if(quintupla[i][j] == noAcepta.get(k))
		    				matriz2[i][j] = String.valueOf(1); 
		    		for(int x=0; x<eAceptacion.length; x++)
		    			if(quintupla[i][j] == Integer.parseInt(eAceptacion[x]))
		    				matriz2[i][j] = String.valueOf(2);
		    }   			
		 }
		
		/*		_______
		 * 	q0|	Q1	Q0 
		 * 	q1| Q1  Q1
		 * 	q2|	Q0	Q1
		 * 
		 */
		for(int i=0; i<matriz2.length; i++)
		{
			boolean isAcepta = false;
			for(int r = 0; r<eAceptacion.length; r++)
				if(Integer.parseInt(eAceptacion[r]) == i)
					isAcepta = true;
			String estado = String.valueOf(i);
			transicion01 = "";
	    	for(int j=0; j<matriz2[0].length; j++)
	    		if (j == matriz2[0].length -1)
	    			transicion01 += matriz2[i][j];
	    		else
	    			transicion01 += matriz2[i][j] + "_";
	    	{
	    	for(int x=0; x<matriz2.length; x++) {
	    		if(i != x)
	    		{	    	
	    		boolean compararTransiciion = false;
	    		if(x== matriz2.length-1)
	    		{
	    			for(int z = 0; z<reducto.size(); z++) 
						  if(reducto.get(z).toString().equals(transicion01))
							  compararTransiciion = true; 
				  if(!compararTransiciion) 
				  {
				  tablatres.add(transicion01 + "," + isAcepta); 
				  reducto.add(transicion01 ); 
				  break;
				  }
	    		}
	    		transicion02 ="";
	    		boolean isNoAcepta = false;
				for(int r = 0; r<noAcepta.size(); r++)
					if(noAcepta.get(r) == x)
						isNoAcepta = true;
	    		
	    		for(int k=0; k<matriz2[0].length; k++)    
	    			if (k == matriz2[0].length -1)
	    				transicion02 += matriz2[x][k];
	    			else
	    				transicion02 += matriz2[x][k] + "_"; 
	    		
	    		//Si transicion01 no se encuentra dentro de eAcepta[] e
	    		if(transicion01.equals(transicion02) && isAcepta != isNoAcepta)
    			{
					  for(int z = 0; z<reducto.size(); z++) 
							  if(reducto.get(z).toString().equals(transicion01))
								  compararTransiciion = true; 
					  if(!compararTransiciion) 
					  {
					  tablatres.add(transicion01 + "," + isAcepta); 
					  reducto.add(transicion01 ); 
					  break;
					  }
					 
    			}
	    	}

    		}
	    	}
		}
		
		// = C1 {0,1,2,3}
		// = C0 {2,4} //SOLO DEBE REPRESENTAR DOS ESTADOS NO TRES COMO MUESTRA EL EJEMPLO
		// = C2 {5}
		// 			|_______________
		// Q0		|{0,1_2,3} 	= {C1, C1} 
		// Q1		|{2,4_5} 	= {C0, C2} 
		// Q2		|{2,4_5} 	= {C0, C2}
		
		
		
		for(int x=matriz2.length-1; x<matriz2.length; x++) {
    		boolean compararTransiciion = false;
    		transicion02 ="";
    		for(int k=0; k<matriz2[0].length; k++)    
    			if (k == matriz2[0].length -1)
    				transicion02 += matriz2[x][k];
    			else
    				transicion02 += matriz2[x][k] + "_"; 
    			for(int z = 0; z<reducto.size(); z++)
    				for(int r = 0; r<eAceptacion.length; r++)
    				if(reducto.get(z).toString() == transicion02 && x != Integer.parseInt(eAceptacion[r]))
    					compararTransiciion = true;
    			if(!compararTransiciion)
    			{    				
    			tablatres.add(String.valueOf(x));
    			reducto.add(transicion02);
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
			transicion01 = "";
	    	for(int j=0; j<matriz3[0].length; j++)
	    		if (j == matriz3[0].length -1)
	    			transicion01 += matriz3[i][j];
	    		else
	    			transicion01 += matriz3[i][j] + "_";
	    	for(int x=i+1; x<matriz3.length; x++) {
	    		boolean compararTransiciion = false;
	    		transicion02 ="";
	    		for(int k=0; k<matriz3[0].length; k++)    
	    			if (k == matriz3[0].length -1)
	    				transicion02 += matriz3[x][k];
	    			else
	    				transicion02 += matriz3[x][k] + "_"; 
	    		if(transicion01.equals(transicion02))
    			{
	    			for(int z = 0; z<reductoFinal.size(); z++)
	    				for(int r = 0; r<eAceptacion.length; r++)
	    				if(reductoFinal.get(z).toString() == transicion01 && i != Integer.parseInt(eAceptacion[r]))
	    					compararTransiciion = true;
	    			if(!compararTransiciion)
	    			{
	    			tablacuatro.add(estado+= "," +x);
	    			reductoFinal.add(transicion01);
	    			}
    			}
	    	}
		}
		
		for(int x=matriz3.length-1; x<matriz3.length; x++) {
    		boolean compararTransiciion = false;
    		transicion02 ="";
    		for(int k=0; k<matriz3[0].length; k++)    
    			if (k == matriz3[0].length -1)
    				transicion02 += matriz3[x][k];
    			else
    				transicion02 += matriz3[x][k] + "_"; 
    			for(int z = 0; z<reductoFinal.size(); z++)
    				for(int r = 0; r<eAceptacion.length; r++)
    				if(reductoFinal.get(z).toString() == transicion02 && x != Integer.parseInt(eAceptacion[r]))
    					compararTransiciion = true;
    			if(!compararTransiciion)
    			{    			
    			tablacuatro.add(String.valueOf(x));
    			reductoFinal.add(transicion02);
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
	
	
	
	public String analizarAutomata(ArrayList<String> abc, ArrayList<String> automata, ArrayList<String> Palabra, int tamaño)
	{
		String estado = "";
		int [][] quintupla = new int[automata.size()][tamaño];
		boolean isIn = false;
		int count = 0;
		int y = 0;
		int f =0;
		for (var arreglo : automata)
		{
				if(!arreglo.isEmpty())
				{					
						for(int j = 0; j< tamaño; j++)
							quintupla[f][j] = Integer.parseInt(arreglo.split("//w")[j]);
				}
				f++;
		}
		/*		___a___b__c__d__e___
		 *	0	|  1   0  2  1  3   abc
		 * 	1	|  2   4  1  0  2
		 * 	2	|  1   2  0  4  0
		 * 	3	|  1   1  1  4  0
		 * 	4	|  0   1  1  1  1
		 * 
		 * ab			0 
		 * a
		 */
		for(var item: Palabra) //abaca
		{
			count = 0;
			for(var item2: abc) // a, b, c
			{
				if(item == item2)
					for (int i=y;i<quintupla.length;i++ ) 
					{
						for (int j=count; j<quintupla[0].length; j++)
						{
							y = quintupla[i][j];
							estado = i + " , " +j;
							break;
						}
						
						break;
					}
				count++;
			}
		}
		
		return estado;
	}
	
	public String analizarAutomataNoReducido(ArrayList<String> abc, int[][] quintupla, ArrayList<String> Palabra) {
		int count=0;
		int y=0;
		String estado="";
		for(var item: Palabra) //abaca
		{
			count = 0;
			for(var item2: abc) // a, b, c
			{
				if(item.equals(item2))
					for (int i=y;i<quintupla.length;i++ ) 
					{
						for (int j=count; j<quintupla[0].length; j++)
						{
							y = quintupla[i][j];
							estado = i + " , " +j;
							break;
						}
						
						break;
					}
			
				count++;
		
			}
		}
		estado= String.valueOf(y);
		return estado;
		
		
		
	}
}
