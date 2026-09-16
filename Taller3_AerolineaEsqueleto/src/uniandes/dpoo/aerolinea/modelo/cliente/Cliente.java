package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.ArrayList;
import java.util.List;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public abstract class Cliente {
	private List<Tiquete> tiquetesSinUsar;
	private List<Tiquete> tiquetesUsados;
	public Cliente(){
        tiquetesSinUsar = new ArrayList<Tiquete>();
        tiquetesUsados = new ArrayList<Tiquete>();
    }
	
	public abstract String getTipoCliente();
	public abstract String getIdentificador();
	
	public void agregarTiquete(Tiquete tiquete)
    {
        this.tiquetesSinUsar.add(tiquete);
    }
	
	public int calcularValorTotalTiquetes(){
        int total = 0;
        for(Tiquete tiquete: tiquetesSinUsar){
            if(!tiquete.esUsado())
                total += tiquete.getTarifa();
        }
        return total;
    }
	
	public void usarTiquetes(Vuelo vuelo){
        List<Tiquete> aMover = new ArrayList<Tiquete>( );
        for(Tiquete tiquete: tiquetesSinUsar){
            if(tiquete.getVuelo().equals(vuelo))
                aMover.add( tiquete );
        }

        for(Tiquete tiquete : aMover){
            tiquete.marcarComoUsado();
            tiquetesSinUsar.remove(tiquete);
            tiquetesUsados.add(tiquete);
        }
    }

}
