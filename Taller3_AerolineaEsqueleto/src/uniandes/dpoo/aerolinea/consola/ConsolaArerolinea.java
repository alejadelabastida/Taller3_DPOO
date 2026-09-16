package uniandes.dpoo.aerolinea.consola;


import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.exceptions.AeropuertoDuplicadoException;
import uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException;
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
        	System.out.println( " --- Prueba: cargar aerolinea y tiquetes --- " );
        	unaAerolinea = new Aerolinea( );
            // String archivo = this.pedirCadenaAlUsuario( "Digite el nombre del archivo json con la información de una aerolinea" );
            unaAerolinea.cargarAerolinea( "./datos/aerolinea.json", CentralPersistencia.JSON );
            unaAerolinea.cargarTiquetes( "./datos/tiquetes.json", CentralPersistencia.JSON );

            System.out.println( "Rutas: " + unaAerolinea.getRutas( ).size( ) );
            System.out.println( "Vuelos: " + unaAerolinea.getVuelos( ).size( ) );
            System.out.println( "Clientes: " + unaAerolinea.getClientes( ).size( ) );
            
            System.out.println( " --- Prueba: programar vuelo ---");
            unaAerolinea.programarVuelo( "2026-01-10", "4558", "AV001" );
            System.out.println( "Vuelo programado correctamente para el 2026-01-10." );
            System.out.println( "Total de vuelos despues de programar: " + unaAerolinea.getVuelos( ).size( ) );

            System.out.println( " --- Prueba: vender tiquetes --- " );
            unaAerolinea.agregarCliente( new ClienteNatural( "Carlos" ) );
            int valor = unaAerolinea.venderTiquetes( "Carlos", "2024-11-05", "4558", 2 );
            System.out.println( "Se le vendieron 2 tiquetes a Carlos por un valor total de " + valor );

            System.out.println( " --- Prueba: consultar saldo pendiente ---" );
            System.out.println( unaAerolinea.consultarSaldoPendienteCliente( "Carlos" ) );

            System.out.println( " --- Prueba: registrar vuelo realizado --- " );
            unaAerolinea.registrarVueloRealizado( "2024-11-05", "4558" );
            System.out.println( "Vuelo del 2024-11-05 registrado como realizado." );
            System.out.println( unaAerolinea.consultarSaldoPendienteCliente( "Carlos" ) );

            System.out.println( " --- Prueba: intentar sobrevender un vuelo ---" );
            try
            {
                unaAerolinea.agregarCliente( new ClienteNatural( "Diana" ) );
                unaAerolinea.venderTiquetes( "Diana", "2024-11-05", "4558", 10 );
                System.out.println( "ERROR: se esperaba una excepcion de sobreventa y no se lanzo" );
            }
            catch( VueloSobrevendidoException e )
            {
                System.out.println( "Excepcion capturada correctamente: " + e.getMessage( ) );
            }
            
            System.out.println( " --Prueba: intentar crear un aeropuerto con codigo repetido ---" );
            try
            {
                new Aeropuerto( "Otro nombre", "BOG", "Otra ciudad", 0, 0 );
                System.out.println( "ERROR: se esperaba una excepcion de aeropuerto duplicado y no se lanzo" );
            }
            catch( AeropuertoDuplicadoException e )
            {
                System.out.println( "Excepcion capturada correctamente: " + e.getMessage( ) );
            }
            
            System.out.println( " --- Prueba: salvar aerolinea y tiquetes ---" );
            unaAerolinea.salvarAerolinea( "./datos/aerolinea_salvada.json", CentralPersistencia.JSON );
            System.out.println( "Aerolinea guardada correctamente en ./datos/aerolinea_salvada.json" );
            unaAerolinea.salvarTiquetes( "./datos/tiquetes_salvados.json", CentralPersistencia.JSON );
            System.out.println( "Tiquetes guardados correctamente en ./datos/tiquetes_salvados.json" );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
    }

    public static void main( String[] args)
    {
        ConsolaArerolinea ca = new ConsolaArerolinea();
        ca.correrAplicacion();
    }
}
