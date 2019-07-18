package com.example.listactivitydetail;

import java.util.ArrayList;

public class FoodData {

    public static String[][] data = new String[][]{

            {"Rendang", "Rendang atau randang adalah masakan daging dengan bumbu rempah-rempah yang berasal dari Minangkabau. Masakan ini dihasilkan dari proses memasak yang dipanaskan berulang-ulang menggunakan santan sampai kuahnya kering sama sekali", "http://cdn2.tstatic.net/palu/foto/bank/images/berikut-resep-membuat-daging-rendang.jpg", "15000", "Jl Imam Bonjol Utara, Jakarta Barat"},
            {"Soto", "Soto, sroto, sauto, tauto, atau coto adalah makanan khas Indonesia seperti sop yang terbuat dari kaldu daging dan sayuran. Daging yang paling sering digunakan adalah daging sapi ", "https://cdns.klimg.com/merdeka.com/i/w/news/2019/05/09/1078693/670x335/5-cara-membuat-soto-mulai-dari-soto-daging-soto-ayam-sampai-soto-banjar.jpg", "12000", "Jl. Cemara Selatan No22, Jakarta Pusat"},
            {"Bakso","Bakso atau baso adalah jenis bola daging yang lazim ditemukan pada masakan Indonesia. Bakso umumnya dibuat dari campuran daging sapi giling dan tepung tapioka","https://cdn0-production-images-kly.akamaized.net/aDZbOOV2WeJCgCiBFgFQwoXJN64=/640x360/smart/filters:quality(75):strip_icc():format(jpeg)/kly-media-production/medias/2029093/original/066661300_1521949447-Bakso.jpg", "20000", "Jl Otista Raya, Jakarta Timur"},
            {"Ketoprak", "Ketoprak adalah salah satu jenis makanan khas Indonesia menggunakan ketupat yang mudah dijumpai.", "https://i.ytimg.com/vi/HCGjtH8uxsE/maxresdefault.jpg", "16000", "Jl. Danau Gilalang Seletan, Jakarta Utara"},
            {"Gado-Gado", "Gado-gado adalah salah satu makanan yang berasal dari Indonesia yang berupa sayur-sayuran yang direbus dan dicampur jadi satu,","https://cdn.idntimes.com/content-images/community/2018/04/big-slider-gado-gado-enak-dan-lezat-d15989179c334422677c80293a7b51c0_600x400.jpg", "14000", "Jl. Ir. Jacks Smith, Jakarta Utara"},
            {"Bebek Madura", "Nasi bebek adalah sajian khas Madura yang digilai pencinta pedas. Ada nasi, potongan bebek empuk dan siraman kuah hitam yang bikin lidah bergetar.", "https://merahputih.com/media/c2/35/cf/c235cfc7563d3d9e0cd53be85fc6af6c.jpg", "17000", "Jl.Anggrek Selatan, Jakarta Selatan"},
            {"Soto Banjar", "Soto Banjar adalah soto khas suku Banjar, Kalimantan Selatan dengan bahan utama ayam serta memiliki aroma harum rempah-rempah seperti kayu manis, biji pala, dan cengkih", "https://img-global.cpcdn.com/003_recipes/57b52ad2b52c11bc/751x532cq70/soto-banjar-foto-resep-utama.jpg","12000" ,"Jl. Bojong Gede, Jakarta Pusat"},
            {"Lontong Sayur", "Lontong sayur adalah lontong yang dikuaihi dengan sayur", "https://cdn2.tstatic.net/bangka/foto/bank/images/lontong-sayur.jpg", "12000", "Jl.Bougenville Timur, Jakarta Pusat"}
    };

    public static ArrayList<Food> getListData(){
        ArrayList<Food> list = new ArrayList<>();

        for(String[] aData : data){
            Food food = new Food();
            food.setName(aData[0]);
            food.setContent(aData[1]);
            food.setPhoto(aData[2]);
            food.setPrice(aData[3]);
            food.setAddress(aData[4]);

            list.add(food);
        }

        return list;
    }
}
