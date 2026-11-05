package com.tdea.modelos;

import com.tdea.consola.CLI;

public class ServicioServicio {
    public static CLI consola = new CLI();
    private static ListaServicios listaServicios = ListaServicios.getInstancia();
    private static PilaServicios pilaServicios = PilaServicios.getInstancia();
    private static ColaMasaje colaMasaje = ColaMasaje.getInstancia();

    private String RED = "\u001B[31m";
    private String GREEN = "\u001B[32m";
    private String YELLOW = "\u001B[33m";
    private String BLUE = "\u001B[34m";
    private String PURPLE = "\u001B[35m";
    private String CYAN = "\u001B[36m";
    private String RESET = "\u001B[0m";

    public void crearServicios() {
        Servicio restauranteServicio = new Servicio("RES", "Restaurante", 10000);
        Servicio lavanderiaServicio = new Servicio("LAV", "Lavandería", 2000);
        Servicio masajeServicio = new Servicio("MAS", "Masaje", 25000);
        Servicio cajaFuerteServicio = new Servicio("CAJ", "Caja fuerte", 15000);
        Servicio vehiculoServicio = new Servicio("VEH", "Alquiler de vehículo", 30000);

        listaServicios.crearServicio(restauranteServicio);
        listaServicios.crearServicio(lavanderiaServicio);
        listaServicios.crearServicio(masajeServicio);
        listaServicios.crearServicio(cajaFuerteServicio);
        listaServicios.crearServicio(vehiculoServicio);
    }

    public void comprarServicio(Huesped huesped) {
        listaServicios.mostrarListaServicios();

        String codigoServicio = consola.leerTexto("Ingrese el código del servicio");
        Servicio servicio = listaServicios.buscarServicio(codigoServicio);

        if (servicio == null) {
            return;
        }

        Integer cantidad = 1;
        String detalle = servicio.getNombre();
        double monto = servicio.getPrecio();

        if (servicio.getCodigo().equalsIgnoreCase("RES")) {
            Integer opcionComida = 0;

            while (opcionComida < 1 || opcionComida > 3) {
                opcionComida = consola.leerNatural(
                        "Seleccione la comida (1. Desayuno $10000, 2. Almuerzo $12000, 3. Cena $14000)");
            }

            double precioComida = 10000;

            if (opcionComida == 2) {
                precioComida = 12000;
                detalle = "Restaurante - Almuerzo";
            } else if (opcionComida == 3) {
                precioComida = 14000;
                detalle = "Restaurante - Cena";
            } else {
                detalle = "Restaurante - Desayuno";
            }

            cantidad = consola.leerEnteroPositivo("Ingrese la cantidad de platos");
            monto = precioComida * cantidad;
        } else if (servicio.getCodigo().equalsIgnoreCase("LAV")) {
            cantidad = consola.leerEnteroPositivo("Ingrese la cantidad de prendas");
            detalle = "Lavandería - " + cantidad + " prenda(s)";
            monto = servicio.getPrecio() * cantidad;
        } else if (servicio.getCodigo().equalsIgnoreCase("MAS")) {
            if (!colaMasaje.agregarHuesped(huesped)) {
                return;
            }

            cantidad = 1;
            detalle = "Masaje - 1 persona";
            monto = servicio.getPrecio();
        } else if (servicio.getCodigo().equalsIgnoreCase("CAJ")) {
            cantidad = 1;
            detalle = "Caja fuerte";
            monto = servicio.getPrecio();
        } else if (servicio.getCodigo().equalsIgnoreCase("VEH")) {
            cantidad = consola.leerEnteroPositivo("Ingrese la cantidad de días de alquiler");
            detalle = "Alquiler de vehículo - " + cantidad + " día(s)";
            monto = servicio.getPrecio() * cantidad;
        }

        ConsumoServicio nuevoConsumo = new ConsumoServicio(huesped, servicio, cantidad, detalle, monto);
        huesped.getConsumos().agregarConsumo(nuevoConsumo);
        pilaServicios.registrarConsumo(nuevoConsumo);

        consola.mostrarTexto("\nServicio registrado: " + detalle + " - "
                + consola.formatearMoneda(monto) + "\n", GREEN);
    }
}
