package uniandes.dpoo.aerolinea.consola;


import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.persistencia.CentralPersistencia;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteNatural;

public class ConsolaArerolinea extends ConsolaBasica
{
    private Aerolinea unaAerolinea;

    /**
     * Es un método que corre la aplicación y realmente no hace nada interesante: sólo muestra cómo se podría utilizar la clase Aerolínea para hacer pruebas.
     */
    public void correrAplicacion()
    {
        try
        {
            unaAerolinea = new Aerolinea( );
            // String archivo = this.pedirCadenaAlUsuario( "Digite el nombre del archivo json con la información de una aerolinea" );
            unaAerolinea.cargarAerolinea( "./datos/aerolinea.json", CentralPersistencia.JSON );
            unaAerolinea.cargarTiquetes( "./datos/tiquetes.json", CentralPersistencia.JSON );

            System.out.println( "Rutas: " + unaAerolinea.getRutas( ).size( ) );
            System.out.println( "Vuelos: " + unaAerolinea.getVuelos( ).size( ) );
            System.out.println( "Clientes: " + unaAerolinea.getClientes( ).size( ) );
           
            unaAerolinea.agregarCliente( new ClienteNatural( "Carlos" ) );
            int valor = unaAerolinea.venderTiquetes( "Carlos", "2024-11-05", "4558", 2 );
            System.out.println( "Valor de los tiquetes: " + valor );
            System.out.println( unaAerolinea.consultarSaldoPendienteCliente( "Carlos" ) );

            unaAerolinea.registrarVueloRealizado( "2024-11-05", "4558" );
            System.out.println( unaAerolinea.consultarSaldoPendienteCliente( "Carlos" ) );
        
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main( String[] args)
    {
        ConsolaArerolinea ca = new ConsolaArerolinea();
        ca.correrAplicacion();
    }
}
