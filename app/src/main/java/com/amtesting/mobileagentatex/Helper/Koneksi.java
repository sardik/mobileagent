package com.amtesting.mobileagentatex.Helper;

/**
 * Created by Mr-AM on 5/26/2017.
 */

public class Koneksi {

    public String isi_koneksi()
    {
        String isi = "http://10.0.2.2/mobile_agen/";
        return isi;
    }
    public String MainUrl()
    {
        String isi = "http://10.0.2.2/mobile_agen/";
        return isi;
    }
    /*public String baseurl()
    {
        String isi = "http://10.0.2.2/mobile_agen/";
        return isi;
    }*/
    String baseurl = "http://192.168.6.12:8088/mobile_agen";
    String baseurl123 = "http://202.162.214.123:8088/mobile_agen";
    String baseurl194 = "http://192.168.6.194/mobile_agen";
    String baseurl14 = "http://43.252.144.14:81/mobile_agen";
    String baseurlaws = "http://api-mobile.atrixpress.id";
    String baseurlaws2 = "http://api-mobile.atex.co.id";
//	String baseurlaws2 = "http://43.252.144.14:81/mobile_agen";
    //String baseurl = "http://202.162.214.123:8088/mobile_agen/";
//	String baseurl = "http://agen.atex.co.id:8088/mobile_agen";


    public String open_map()
    {
//		String map_open = "http://192.168.6.12/mobile_agen/locations";
//		String map_open = "http://202.162.214.123:8088/mobile_agen/locations";
//		String map_open = baseurl + "/listpickup";
//		String map_open = baseurl14 + "/listpickup2/";
//		String map_open = baseurlaws + "/listpickup2/";
        String map_open = baseurlaws2 + "/listpickup2/";
        return map_open;
    }
    public String drop_point()
    {
//		String map_drop_point = "http://192.168.6.12/mobile_agen/locations_2";
//		String map_drop_point = "http://202.162.214.123:8088/mobile_agen/locations_2";
//		String map_drop_point = baseurl194 + "/locations_2";
//		String map_drop_point = baseurl14 + "/dropstore2/";
//		String map_drop_point = baseurlaws + "/dropstore2/";
        String map_drop_point = baseurlaws2 + "/dropstore2/";
        return map_drop_point;
    }
    public String koneksi_login()
    {
//		String login_user = "http://192.168.6.12/mobile_agen/login";
//		String login_user = "http://202.162.214.123:8088/mobile_agen/login";
//		String login_user = baseurl14 + "/login";
//		String login_user = baseurlaws + "/login";
        String login_user = baseurlaws2 + "/login";
        return login_user;
    }
    public String koneksi_booking()
    {
//		String booking = "http://192.168.6.12/mobile_agen/cekemail/";
//		String booking = "http://202.162.214.123:8088/mobile_agen/cekemail/";
//		String booking = baseurl14 + "/cekemail/";
//		String booking = baseurlaws + "/cekemail/";
        String booking = baseurlaws2 + "/cekemail/";
        return booking;
    }
    public String koneksi_booking_alamat()
    {
//		String booking_alamat = "http://192.168.6.12/mobile_agen/alamat";
//		String booking_alamat = "http://202.162.214.123:8088/mobile_agen/alamat";
//		String booking_alamat = baseurl14 + "/alamat";
//		String booking_alamat = baseurlaws + "/alamat";
        String booking_alamat = baseurlaws2 + "/alamat";
        return booking_alamat;
    }
    public String koneksi_detail_paket()
    {
//		String detail_paket = "http://192.168.6.12/mobile_agen/biaya";
//		String detail_paket = "http://202.162.214.123:8088/mobile_agen/biaya";
//		String detail_paket = baseurl14 + "/biaya";
//		String detail_paket = baseurlaws + "/biaya";
        String detail_paket = baseurlaws2 + "/biaya";
        return detail_paket;
    }
    public String koneksi_biaya()
    {
//		String biaya = "http://192.168.6.12/mobile_agen/booking";
//		String biaya = "http://202.162.214.123:8088/mobile_agen/booking";
//		String biaya = baseurl14 + "/booking";
//		String biaya = baseurlaws + "/booking";
        String biaya = baseurlaws2 + "/booking";
        return biaya;
    }
    public String koneksi_riwayat()
    {
//		String riwayat_transaksi = "http://192.168.6.12/mobile_agen/histori/";
//		String riwayat_transaksi = "http://202.162.214.123:8088/mobile_agen/histori/";
//		String riwayat_transaksi = baseurl14 + "/histori/";
//		String riwayat_transaksi = baseurlaws + "/histori/";
        String riwayat_transaksi = baseurlaws2 + "/histori/";
        return riwayat_transaksi;
    }
    public String koneksi_register()
    {
//		String register = "http://192.168.6.12/mobile_agen/register";
//		String register = "http://202.162.214.123:8088/mobile_agen/register/";
//		String register = baseurl14 + "/register";
//		String register = baseurlaws + "/register";
        String register = baseurlaws2 + "/register";
        return register;
    }
    public String koneksi_kota()
    {
//		String search_kota = "http://192.168.6.12/mobile_agen/kota";
//		String search_kota = "http://202.162.214.123:8088/mobile_agen/kota";
//		String search_kota = baseurl14 + "/kota";
//		String search_kota = baseurlaws + "/kota";
        String search_kota = baseurlaws2 + "/kota";
        return search_kota;
    }

    public String koneksi_kota2()
    {
//		String search_kota = "http://192.168.6.12/mobile_agen/kota";
//		String search_kota = "http://202.162.214.123:8088/mobile_agen/kota";
//		String search_kota2 = baseurl14 + "/kota2/";
//		String search_kota2 = baseurlaws + "/kota2/";
        String search_kota2 = baseurlaws2 + "/kota2/";
        return search_kota2;
    }
    public String koneksi_kecamatan()
    {
//		String search_kecamatan = "http://192.168.6.12/mobile_agen/kecamatan/";
//		String search_kecamatan = "http://202.162.214.123:8088/mobile_agen/kecamatan/";
//		String search_kecamatan = baseurl14 + "/kecamatan/";
//		String search_kecamatan = baseurlaws + "/kecamatan/";
        String search_kecamatan = baseurlaws2 + "/kecamatan/";
        return search_kecamatan;
    }
    public String koneksi_kecamatan2()
    {
//		String search_kecamatan = "http://192.168.6.12/mobile_agen/kecamatan/";
//		String search_kecamatan = "http://202.162.214.123:8088/mobile_agen/kecamatan/";
//		String search_kecamatan = baseurl14 + "/kecamatan2/";
//		String search_kecamatan = baseurlaws + "/kecamatan2/";
        String search_kecamatan = baseurlaws2 + "/kecamatan2/";
        return search_kecamatan;
    }
    public String koneksi_customer_contact()
    {
//		String customer_contact = "http://192.168.6.12/mobile_agen/listemailcustomer/";
//		String customer_contact = "http://202.162.214.123:8088/mobile_agen/listemailcustomer/";
//		String customer_contact = baseurl14 + "/listemailcustomer/";
//		String customer_contact = baseurlaws + "/listemailcustomer/";
        String customer_contact = baseurlaws2 + "/listemailcustomer/";
        return customer_contact;
    }
    public String koneksi_customer_contact2()
    {
//		String customer_contact = "http://192.168.6.12/mobile_agen/listemailcustomer/";
//		String customer_contact = "http://202.162.214.123:8088/mobile_agen/listemailcustomer/";
//		String customer_contact2 = baseurl14 + "/listemailcustomer2/";
//		String customer_contact2 = baseurlaws + "/listemailcustomer2/";
        String customer_contact2 = baseurlaws2 + "/listemailcustomer2/";
        return customer_contact2;
    }
    public String koneksi_customer_addcontact()
    {
//		String customer_contact = "http://192.168.6.12/mobile_agen/addcustomer/";
//		String customer_contact = "http://202.162.214.123:8088/mobile_agen/addcustomer/";
//		String customer_addcontact = baseurl14 + "/addcustomer";
//		String customer_addcontact = baseurlaws + "/addcustomer";
        String customer_addcontact = baseurlaws2 + "/addcustomer";
        return customer_addcontact;
    }
    public String koneksi_pickup_customer()
    {
//		String customer_contact = "http://192.168.6.12/mobile_agen/addcustomer/";
//		String customer_contact = "http://202.162.214.123:8088/mobile_agen/addcustomer/";
//		String pickup_customer = baseurl14 + "/pickup";
//		String pickup_customer = baseurlaws + "/pickup";
        String pickup_customer = baseurlaws2 + "/pickup";
        return pickup_customer;
    }
    public String koneksi_pickup_detail()
    {
//		String customer_contact = "http://192.168.6.12/mobile_agen/addcustomer/";
//		String customer_contact = "http://202.162.214.123:8088/mobile_agen/addcustomer/";
//		String pickup_detail = baseurl14 + "/detail_pickup/";
//		String pickup_detail = baseurlaws + "/detail_pickup/";
        String pickup_detail = baseurlaws2 + "/detail_pickup/";
        return pickup_detail;
    }
    public String koneksi_pickup_booking()
    {
//		String customer_contact = "http://192.168.6.12/mobile_agen/addcustomer/";
//		String customer_contact = "http://202.162.214.123:8088/mobile_agen/addcustomer/";
//		String pickup_booking = baseurl14 + "/pickup_booking";
//		String pickup_booking = baseurlaws + "/pickup_booking";
        String pickup_booking = baseurlaws2 + "/pickup_booking";
        return pickup_booking;
    }
    public String koneksi_pickup_booking2()
    {
//		String pickup_booking2 = baseurl14 + "/pickup_booking2";
//		String pickup_booking2 = baseurlaws + "/pickup_booking2";
        String pickup_booking2 = baseurlaws2 + "/pickup_booking2";
        return pickup_booking2;
    }
    public String koneksi_wallet()
    {
//		String wallet_saldo = baseurl14 + "/saldo_agen/";
//		String wallet_saldo = baseurlaws + "/saldo_agen/";
        String wallet_saldo = baseurlaws2 + "/saldo_agen/";
        return wallet_saldo;
    }
    public String koneksi_wallet_cash_in_date()
    {
//		String wallet_cash_in_detail_date = baseurl14 + "/agen_kredit/";
//		String wallet_cash_in_detail_date = baseurlaws + "/agen_kredit/";
        String wallet_cash_in_detail_date = baseurlaws2 + "/agen_kredit/";
        return wallet_cash_in_detail_date;
    }
    public String koneksi_wallet_cash_out_date()
    {
//		String wallet_cash_out_detail_date = baseurl14 + "/agen_debet/";
//		String wallet_cash_out_detail_date = baseurlaws + "/agen_debet/";
        String wallet_cash_out_detail_date = baseurlaws2 + "/agen_debet/";
        return wallet_cash_out_detail_date;
    }
    public String koneksi_wallet_all_in()
    {
//		String wallet_cash_all_in = baseurl14 + "/agen_wallet/";
//		String wallet_cash_all_in = baseurlaws + "/agen_wallet/";
        String wallet_cash_all_in = baseurlaws2 + "/agen_wallet/";
        return wallet_cash_all_in;
    }
    public String koneksi_cek_pickup()
    {
//		String cek_pickup = baseurl14 + "/cekpickup/";
//		String cek_pickup = baseurlaws + "/cekpickup/";
        String cek_pickup = baseurlaws2 + "/cekpickup/";
        return cek_pickup;
    }
    public String koneksi_cek_validasi_booking()
    {
//		String cek_validasi_booking = baseurl14 + "/cekbooking/";
//		String cek_validasi_booking = baseurlaws + "/cekbooking/";
        String cek_validasi_booking = baseurlaws2 + "/cekbooking/";
        return cek_validasi_booking;
    }
    public String koneksi_cek_version()
    {
//		String cek_version = baseurl14 + "/cekbooking/";
//		String cek_version = baseurlaws + "/cekbooking/";
        String cek_version = baseurlaws2 + "/version";
        return cek_version;
    }
}
