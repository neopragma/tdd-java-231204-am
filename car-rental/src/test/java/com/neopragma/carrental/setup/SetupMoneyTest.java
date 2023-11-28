package com.neopragma.carrental.setup;

import com.neopragma.carrental.util.Config;
import com.neopragma.carrental.util.CurrencyConverter;
import com.neopragma.carrental.util.I18n;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.money.Monetary;
import java.io.IOException;
import java.text.NumberFormat;

import static com.neopragma.carrental.Constants.CURRENCY_CODE_USD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SetupMoneyTest {

    @Mock
    CurrencyConverter currencyConverter;
    @Test
    public void test() throws IOException {
        Config.load();

        when(currencyConverter.convert(any(), anyString(), anyString()))
                .thenReturn(Money.of(1656.29, "EUR"));

        // monetary amount as stored in the database (pennies)
        long amountInCents = 212345;

        // get the default currency code from our config settings
        String defaultCurrencyCode = Config.valueOf("default.currency.code", CURRENCY_CODE_USD);

        // convert to a Money object for processing
        Money amountInUSD =
            Monetary.getAmountFactory(Money.class)
                    .setCurrency(defaultCurrencyCode)
                    .setNumber(amountInCents / 100.00)
                    .create();

        // set the locale to something other than the default
        I18n.setLocale("de", "DE");
        // get the currency code corresponding to the locale
        String localeCurrencyCode = NumberFormat.getCurrencyInstance(I18n.currentLocale()).getCurrency().getCurrencyCode();
        assertEquals("EUR",localeCurrencyCode);

        // call a currency conversion api via a helper class
        // do this only for the final rental agreement amount
        Money convertedAmount = currencyConverter.convert(amountInUSD, defaultCurrencyCode, localeCurrencyCode);
        convertedAmount = convertedAmount.with(Monetary.getDefaultRounding());

        // format the converted amount for display based on the current locale
        String formattedValue = NumberFormat
                .getCurrencyInstance(I18n.currentLocale())
                .format(convertedAmount.getNumberStripped().doubleValue());
        // formattedValue: 312e3635362c3239a020ac  \u00A0 is a "non-breaking space" or "number separator"
        // literal value : 312e3635362c32392020ac  \u0020 is a normal space
        assertEquals("1.656,29\u00A0€", formattedValue, "assertEquals");

    }
}
