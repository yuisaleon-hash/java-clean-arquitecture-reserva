package com.reserva.hotel.domain.model;

import java.math.BigDecimal;
import com.reserva.hotel.domain.exception.BusinessRuleException;

public final class Precio {

    private final BigDecimal monto;
    private final String moneda;

    public Precio(BigDecimal monto, String moneda) {
        if (monto == null || monto.compareTo(BigDecimal.ZERO) < 0)
            throw new BusinessRuleException("El precio no puede ser negativo");
        if (moneda == null || moneda.isBlank())
            throw new BusinessRuleException("La moneda no puede estar vacía");
        this.monto  = monto;
        this.moneda = moneda.toUpperCase();
    }

    public Precio aplicarDescuento(int porcentaje) {
        if (porcentaje < 0 || porcentaje > 100)
            throw new BusinessRuleException("El descuento debe estar entre 0 y 100");

        BigDecimal factor = BigDecimal.ONE.subtract(
                BigDecimal.valueOf(porcentaje).divide(BigDecimal.valueOf(100))
        );

        return new Precio(monto.multiply(factor), moneda);
    }

    public BigDecimal getMonto()  { return monto; }
    public String getMoneda()     { return moneda; }
}