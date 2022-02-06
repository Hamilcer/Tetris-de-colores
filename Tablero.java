import java.util.Scanner;

public class Tablero
{
    Ficha[][] tablero;

    public Tablero(){
        tablero = new Ficha[6][7];
    }

    public Ficha[][] inizializar(){
        for(int i=0;i<tablero.length;i++){
            for(int j=0;j<tablero[i].length;j++){
                tablero[i][j]=new Ficha();
            }
        }
        return tablero;
    }

    public int getRowsLength(){
        return tablero.length;
    }

    public int getColumnsLength(){
        return tablero[0].length;
    }
    
    public void ponerFicha(Tablero pantalla){
        
        System.out.println("///////////////////////////////////////////////////////////////");
        pantalla.imprimir(pantalla);
        
        int userNum;
        int rand = (int)Math.floor(Math.random()*4);
        String randomcolor = Ficha.colors[rand];
        System.out.println("Color: ");
        System.out.println(randomcolor);
        
        System.out.println("Introduce la columna: ");
        userNum = leerNum(pantalla);        
        
        //System.out.println(userNum);
        
        for(int i=pantalla.getRowsLength()-1;i>=0;i--){
            if(pantalla.tablero[i][userNum].color=="Blanco"){
                pantalla.tablero[i][userNum].color = randomcolor;
                break;
            }
            if(i==0){
                System.out.println("Elige una columna que no este llena: ");
                userNum = leerNum(pantalla);
                i=pantalla.getRowsLength();
            }
        }        
        
    }
    
    public void imprimir(Tablero pantalla){
		for(int i=0;i<pantalla.getRowsLength();i++){
            for(int j=0;j<pantalla.getColumnsLength();j++){
                System.out.print(pantalla.tablero[i][j].color + "|");
            }
			System.out.println();
			System.out.println("-----------------------------------------------");
        }
	}

    public int leerNum(Tablero pantalla){
        Scanner sc = new Scanner(System.in);
        int userNum;
        userNum = sc.nextInt();
        while(userNum<0 || userNum>pantalla.getColumnsLength()){
            System.out.println("Introduce una columna valida: ");
            userNum = sc.nextInt();
        }
        userNum-=1;
        return userNum;
    }

    public void eliminar(Tablero pantalla){
        // flag sirve para evitar que despues de eliminar queden mas fichas por eliminar 
        boolean flag = true;
        //mirar horizontalmente
        for(int i=0;i<pantalla.getRowsLength();i++){
            for(int j=0;j<pantalla.getColumnsLength()-4;j++){
                Ficha[][] m = pantalla.tablero;
                if(m[i][j].color==m[i][j+1].color && m[i][j].color==m[i][j+2].color && m[i][j].color==m[i][j+3].color){
                    m[i][j].color ="Blanco";
                    m[i][j+1].color ="Blanco";
                    m[i][j+2].color ="Blanco";
                    m[i][j+3].color ="Blanco";
                }
            }
        }
    }

    public void moverAbajo(Tablero pantalla){
        Ficha[][] m = pantalla.tablero;
        for(int i=pantalla.getRowsLength()-2;i>=0;i--){
            for(int j=0;j<pantalla.getColumnsLength();j++){
                if(m[i][j].color != "Blanco"){
                    for(int k=pantalla.getRowsLength()-1;k>j;k++){
                        if(m[k][j].color=="Blanco"){
                            m[k][j].color = m[i][j].color;
                        }
                    }
                }
            }
        }

    }

	public boolean terminar(Tablero pantalla){
        int countWin = 0;
        int countLose = 0;
        for(int i=0;i<pantalla.getRowsLength();i++){
            for(int j=0;j<pantalla.getColumnsLength();j++){
                if(pantalla.tablero[i][j].color=="Blanco"){
                    countWin+=1;
                }
                if(pantalla.tablero[i][j].color!="Blanco"){
                    countLose+=1;
                }
            }
        }
        if(countWin==pantalla.getRowsLength()*pantalla.getColumnsLength()){
            System.out.println("Ganaste!!");
            return false;
        }else if(countLose==pantalla.getRowsLength()*pantalla.getColumnsLength()){
            System.out.println("Perdiste :(");
            return false;
        }else{
            return true;
        }
	}

}