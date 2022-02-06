class Main
{
	public static void main(String[] args)
	{
		Tablero pantalla = new Tablero();
		pantalla.inizializar();
		do {
			pantalla.ponerFicha(pantalla);
		}while(pantalla.terminar(pantalla));
	}
}