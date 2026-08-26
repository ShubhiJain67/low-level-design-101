package structural.adapter.sample.chargers;

// Another existing class with a different incompatible interface — can't be changed.
public class LegacyMicroUsbCharger {
    public void plugMicroUsb() {
        System.out.println("Charging via legacy Micro-USB cable");
    }
}
