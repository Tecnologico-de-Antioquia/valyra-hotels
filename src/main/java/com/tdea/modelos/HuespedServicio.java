package com.tdea.modelos;

import com.tdea.compartidos.enums.EstadoHuesped;
import com.tdea.compartidos.enums.Generos;
import com.tdea.compartidos.enums.MetodoPago;
import com.tdea.consola.CLI;

public class HuespedServicio {
    public static CLI consola = new CLI();
    private static ColaAtencion colaAtencion = ColaAtencion.getInstancia();
    private static ListaHuespedes listaHuespedes = ListaHuespedes.getInstancia();
    private static ColaMasaje colaMasaje = ColaMasaje.getInstancia();
    private static PagoServicio pagoServicio = PagoServicio.getInstancia();

    private String RED = "\u001B[31m";
    private String GREEN = "\u001B[32m";
    private String YELLOW = "\u001B[33m";
    private String BLUE = "\u001B[34m";
    private String PURPLE = "\u001B[35m";
    private String CYAN = "\u001B[36m";
    private String RESET = "\u001B[0m";

    public void agregarHuespedCola() {
        consola.mostrarTitulo("Agregar Huesped a la Cola", PURPLE);

        String documento = consola.leerTexto("Ingrese el documento del huesped");
        String nombre = consola.leerTexto("Ingrese el nombre del huesped");
        Integer edad = consola.leerNatural("Ingrese la edad del huesped");

        Generos genero = null;
        while (genero == null) {
            Integer generoAux = consola
                    .leerNatural("Ingrese el genero del huesped (1. Hombre, 2. Mujer, 3. Indeterminado)");
            if (generoAux == 1) {
                genero = Generos.Hombre;
            } else if (generoAux == 2) {
                genero = Generos.Mujer;
            } else if (generoAux == 3) {
                genero = Generos.Indeterminado;
            }
        }

        Huesped nuevoHuesped = new Huesped(documento, nombre, edad, genero);

        colaAtencion.agregarHuesped(nuevoHuesped);
        consola.mostrarTexto("\nEl huésped fue agregado a la cola de atención\n", GREEN);
    }

    public void checkInHuesped(HabitacionServicio habitacionServicio) {
        Huesped huesped = colaAtencion.atenderHuesped();

        if (huesped == null) {
            return;
        }

        consola.mostrarTitulo("Registrar Huesped (check-in)", PURPLE);

        habitacionServicio.asignarHabitacion(huesped);

        Integer noches = consola.leerEnteroPositivo("Ingrese el número de noches de estadía");
        huesped.setNoches(noches);
        huesped.setEstado(EstadoHuesped.hospedado);
        listaHuespedes.regitrarHuesped(huesped);

        consola.mostrarTexto("\nSe ha registrado el huésped correctamente\n", GREEN);
    }

    public void comprarServicio(ServicioServicio servicioServicio) {
        consola.mostrarTitulo("Adquirir un servicio", PURPLE);

        String documentoHuesped = consola.leerTexto("Ingrese el documento del huésped");
        Huesped huesped = listaHuespedes.buscarHuesped(documentoHuesped);

        if (huesped == null) {
            return;
        }

        if (huesped.getEstado() != EstadoHuesped.hospedado) {
            consola.mostrarTexto("\nEl huésped no se encuentra hospedado\n", RED);
            return;
        }

        servicioServicio.comprarServicio(huesped);
    }

    public void checkOutHuesped(HabitacionServicio habitacionServicio) {
        String documentoHuesped = consola.leerTexto("Ingrese el documento del huésped");
        Huesped huesped = listaHuespedes.buscarHuesped(documentoHuesped);

        if (huesped == null) {
            return;
        }

        if (huesped.getEstado() != EstadoHuesped.hospedado) {
            consola.mostrarTexto("\nEl huésped ya fue retirado del hotel\n", RED);
            return;
        }

        consola.mostrarTitulo("Retirar Huesped (check-out)", PURPLE);

        double totalHabitacion = huesped.getHabitacion().getTipoHabitacion().getPrecio() * huesped.getNoches();
        double totalServicios = huesped.getConsumos().getTotal();
        double subtotal = totalHabitacion + totalServicios;
        double porcentajeDescuento = calcularPorcentajeDescuento(huesped.getEdad());
        double descuento = subtotal * porcentajeDescuento;
        double totalPagar = subtotal - descuento;

        consola.mostrarTexto("---RESUMEN DE LA ESTADÍA---\n", CYAN);
        consola.mostrarTexto("Huésped: " + huesped.getNombre() + " - " + huesped.getDocumento());
        consola.mostrarTexto("Habitación: " + huesped.getHabitacion().getNumero() + " - "
                + huesped.getHabitacion().getTipoHabitacion());
        consola.mostrarTexto("Noches: " + huesped.getNoches());
        consola.mostrarTexto("Valor habitación: "
                + consola.formatearMoneda(huesped.getHabitacion().getTipoHabitacion().getPrecio()) + " por noche");
        consola.mostrarTexto("Total habitación: " + consola.formatearMoneda(totalHabitacion));
        mostrarConsumos(huesped);
        consola.mostrarTexto("Total servicios: " + consola.formatearMoneda(totalServicios));
        consola.mostrarTexto("Subtotal: " + consola.formatearMoneda(subtotal));
        consola.mostrarTexto("Descuento aplicado (" + (int) (porcentajeDescuento * 100) + "%): "
                + consola.formatearMoneda(descuento));
        consola.mostrarTexto("\nTOTAL A PAGAR: " + consola.formatearMoneda(totalPagar) + "\n", GREEN);

        MetodoPago metodoPago = seleccionarMetodoPago();
        pagoServicio.registrarPago(metodoPago);

        huesped.setSubtotal(subtotal);
        huesped.setDescuento(descuento);
        huesped.setTotalPagado(totalPagar);
        huesped.setMetodoPago(metodoPago);
        listaHuespedes.retirarHuesped(huesped);

        habitacionServicio.liberarHabitacion(huesped.getHabitacion());
        colaMasaje.retirarHuesped(huesped);

        consola.mostrarTexto("Pago registrado con método: " + metodoPago + "\n", GREEN);
        consola.mostrarTexto("La habitación quedó nuevamente disponible\n", GREEN);
    }

    private void mostrarConsumos(Huesped huesped) {
        if (huesped.getConsumos().getLista() == null) {
            consola.mostrarTexto("Servicios adquiridos: ninguno");
            return;
        }

        consola.mostrarTexto("Servicios adquiridos:");
        com.tdea.estructura.NodoSimple<ConsumoServicio> auxiliar = huesped.getConsumos().getLista();

        while (auxiliar != null) {
            ConsumoServicio consumo = auxiliar.getValor();
            consola.mostrarTexto("  - " + consumo.getDetalle() + " x" + consumo.getCantidad() + ": "
                    + consola.formatearMoneda(consumo.getMonto()));
            auxiliar = auxiliar.getSiguiente();
        }
    }

    private MetodoPago seleccionarMetodoPago() {
        while (true) {
            Integer opcion = consola.leerNatural(
                    "Seleccione el método de pago (1. Efectivo, 2. Tarjeta débito, 3. Tarjeta crédito)");

            if (opcion == 1) {
                return MetodoPago.Efectivo;
            } else if (opcion == 2) {
                return MetodoPago.Debito;
            } else if (opcion == 3) {
                return MetodoPago.Credito;
            }

            consola.mostrarTexto("\nOpción de pago inválida\n", RED);
        }
    }

    private double calcularPorcentajeDescuento(Integer edad) {
        if (edad < 5) {
            return 0.15;
        } else if (edad > 60) {
            return 0.25;
        }

        return 0.05;
    }
}
