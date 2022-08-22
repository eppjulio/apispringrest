package com.challenge.Eldar.models;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "usuario")
public class UsuarioTarjeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long Id;

    private double Monto;
    private boolean Valid;
    private double Tarifa;
    private String CardBrand;
    private String ExpiryDate;
    private String CardNumber;
    private String CardHolderName;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public double getMonto() {
        return Monto;
    }

    public void setMonto(double monto) {
        this.Monto = monto;
    }

    public double getTarifa() {
        return Tarifa;
    }

    public void setTarifa(double tarifa) {
        Tarifa = tarifa;
    }

    public String getCardBrand() {
        return CardBrand;
    }

    public void setCardBrand(String cardBrand) {
        CardBrand = cardBrand.toUpperCase();
    }

    public String getExpiryDate() {
        return ExpiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        ExpiryDate = expiryDate;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String cardNumber) {
        CardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return CardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        CardHolderName = cardHolderName;
    }

    public boolean getValid() {
        return Valid;
    }

    public void setValid() throws ParseException {
        String[] marcasAceptadas = {"VISA", "AMEX", "NARA"};
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yy");
        Date expiracion = simpleDateFormat.parse(ExpiryDate);
        boolean MontoValido = (Monto > 0) && (Monto < 1000);
        boolean BrandValido = CardBrand.equals(marcasAceptadas[0]) || CardBrand.equals(marcasAceptadas[1]) || CardBrand.equals(marcasAceptadas[2]);
        boolean ExpiryValido = expiracion.after(new Date());

        if (MontoValido && BrandValido && ExpiryValido) Valid = true;
    }
}
